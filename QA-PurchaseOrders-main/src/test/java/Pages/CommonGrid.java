package Pages;

import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonGrid {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;
    private final HashMap<String, String> gridMap = new HashMap<>();

    public CommonGrid(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
        initializeMaps();
    }

    @FindBy(how = How.XPATH, using = "//thead/tr[1]/th[3]/span[1]/div[1]/ul[1]/li[1]/div[1]/input[1]")
    private WebElement fieldSearchInitiator;
    @FindBy(how = How.XPATH, using = "//thead/tr[1]/th[4]/span[1]/div[1]/ul[1]/li[1]/div[1]/input[1]")
    public  WebElement fieldSearchStatus;

    /**
     * Navigates to the indicated grid tab
     *
     * @param tabName The name of the tab as it displays on the page
     * @return Returns <code>true</code> if tab could be successfully navigated to, otherwise returns <code>false</code>
     */
    public boolean gridTab(String tabName) {
        String tabFix = tabName.toLowerCase().replaceAll("\\s+", "");
        WebElement tab = driver.findElement(By.id(gridMap.get(tabFix + "tab")));
        BaseUtil.pageLoaded();
        try {
            tab.click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), tab);
        }
        BaseUtil.pageLoaded();
        return true;

    }

    WebElement gridPageNum;
    WebElement totalPages;

    /**
     * Returns either the number of pages in a specific grid, or what current page of
     * the grid is currently being displayed
     *
     * @param tabName The name of the tab as it displays on the page
     * @param result  Can be "current" or "total" depending on what return is expected
     * @return Returns an int
     */
    public int gridPageNumber(String tabName, String result) {
        String tabNameFixed = tabName.replaceAll("\\s+", "").toLowerCase();

        if (driver.findElement(By.id(gridMap.get(tabNameFixed) + "_paginate")).getAttribute("style").equals("display: none;")) {
            return 0;
        }
        try {
            gridPageNum = driver.findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
            totalPages = driver.findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-total"));
        } catch (NoSuchElementException e) {
            return -1;
        }

        String pageNum = gridPageNum.getAttribute("value");
        if (result.equals("current")) {
            return Integer.parseInt(pageNum);
        } else if (result.equals("total")) {
            return Integer.parseInt(totalPages.getText());
        }

        return -1;
    }

    /**
     * Navigates to a random page in the current grid tab.
     * Requires there be a field to enter numbers in the grid pagination section to work
     *
     * @param tabName Name of the tab as it displays on the page
     * @return returns the page number from the randomly selected page as an int
     */
    public int gridRandomPage(String tabName) {
        int totalPages = gridPageNumber(tabName, "total");
        String tabNameFixed = tabName.replaceAll("\\s+", "").toLowerCase();
        WebElement pageNum = driver.findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
        System.out.println("Total number of available pages is: " + totalPages);
        if (totalPages == 1) {
            return totalPages;
        } else {
            int randomPage = (int) (Math.random() * (totalPages - 1)) + 1;
            pageNum.click();
            pageNum.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
            pageNum.sendKeys(Integer.toString(randomPage));
            pageNum.sendKeys(Keys.ENTER);
            return gridPageNumber(tabName, "current");
        }

    }

    /**
     * Navigates to the next page of the current grid tab if it exists
     *
     * @param tabName Name of the tab as it displays on the page
     * @return Returns <code>true</code> if it is possible to navigate to the next page, otherwise returns <code>false</code>
     */
    public boolean gridNextPage(String tabName) {
        WebElement nextBtn;
        if (gridPageNumber(tabName, "current") < 0) {
            nextBtn = driver.findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", "").toLowerCase()) + "_next"));
            if (nextBtn.getAttribute("class").contains("disabled")) {
                return false;
            } else {
                nextBtn.findElement(By.tagName("a")).click();
                return true;
            }
        } else {
            nextBtn = driver.findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", "").toLowerCase()) + "_paginate .next"));
            if (nextBtn.getAttribute("class").contains("disabled")) {
                return false;
            } else {
                nextBtn.click();
                return true;
            }
        }

    }

    /**
     * Navigates to the previous page of the current grid tab if it exists
     *
     * @param tabName Name of the tab as it displays on the page
     * @return Returns <code>true</code> if it is possible to navigate to the previous page, otherwise returns <code>false</code>
     */
    public boolean gridPrevPage(String tabName) {
        WebElement prevBtn;
        if (gridPageNumber(tabName, "current") < 0) {
            prevBtn = driver.findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", "").toLowerCase()) + "_previous"));
            if (prevBtn.getAttribute("class").contains("disabled")) {
                return false;
            } else {
                prevBtn.findElement(By.tagName("a")).click();
                return true;
            }
        } else {
            prevBtn = driver.findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", "").toLowerCase()) + "_paginate .prev"));
            if (prevBtn.getAttribute("class").contains("disabled")) {
                return false;
            } else {
                prevBtn.click();
                return true;
            }
        }

    }

    /**
     * Selects an option from the "Viewing" drop down of the grid header that changes the number of rows visible
     * for the current grid tab
     *
     * @param tabName Name of the tab as it displays on the page
     * @param option  Number of rows to display
     */
    public void gridViewSelection(String tabName, String option) {
        System.out.println("Selecting " + option + " from Viewing dropdown");
        WebElement dropDown = driver.findElement(By.xpath("//*[@id='" + gridMap.get(tabName.toLowerCase().replaceAll("\\s+", "")) + "_length']/label/select"));
        Select rows = new Select(dropDown);
        dropDown.click();
        rows.selectByVisibleText(option);
    }

    /**
     * Returns the count of total records in the current grid tab
     *
     * @param tabName Name of the tab as it displays on the page
     * @return Returns the total number of records as an int
     */
    public int gridRecordNumber(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        int recordCount;

        String records = driver.findElement(By.id(currentTab + "_info")).getText();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < records.length(); i++) {
            if (Character.isDigit(records.charAt(i))) {
                stringBuilder.append(records.charAt(i));
            }
        }
        if (stringBuilder.length() == 0) {
            return 0;
        }

        recordCount = Integer.parseInt(stringBuilder.toString());

        return recordCount;
    }

    /**
     * Returns the number of rows being displayed in the current grid tab
     *
     * @param tabName Name of the tab as it displays on the page
     * @return The number of visible rows as an int
     */
    public int gridRowCount(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='" + currentTab + "']/tbody/tr"));
        return rows.size();

    }

    public int gridRowCount() {
        List<WebElement> rows = getGridElement().findElements(By.xpath("tbody/tr"));
        return rows.size();

    }

    /**
     * Gets the table element for the currently visible grid on the page.
     *
     * @return Returns WebElement with the ID of the currently visible grid
     */
    public WebElement getGridElement() {

        List<WebElement> grids = driver.findElements(By.xpath("//table[@id]"));
        for (WebElement tblEle : grids) {
            if (tblEle.isDisplayed() && tblEle.getAttribute("id").length() > 0) {
                return tblEle;
            }
        }
        return null;
    }

    /**
     * Will search the page for the specified grid header
     *
     * @param tab    The current tab being displayed
     * @param header The name of the header to be located
     * @return Returns <code>true</code> if header is found in grid, otherwise returns <code>false</code>
     */
    public boolean gridHeaderFind(String tab, String header) {
        String tabName = tab.replaceAll("\\s", "").toLowerCase();
        try {
            return driver.findElement(By.xpath("//table[@id='" + gridMap.get(tabName) + "']/descendant::th[contains(@aria-label, '" + header + "')]")).isDisplayed();
//            return driver.findElement(By.xpath("//table[@id='"+gridMap.get(tabName)+"']/descendant::th[normalize-space(@aria-label)='"+header+"']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Sort the column on the grid
     *
     * @param headerName <code>String</code> Name of the grid header to be sorted
     * @param sort       <code>String</code> Choose whether to sort by Ascending or Descending
     * @return <code>boolean</code> Returns true if sorting was successful, otherwise returns false
     */
    public boolean gridHeaderSort(String headerName, String sort) {
        String option = sort.equalsIgnoreCase("ascending") ? "filterHeader sorting_asc" : "filterHeader sorting_desc";
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath("" +
                "//*[contains(@class, 'filterHeader sorting')][normalize-space()='" + headerName + "']"));

        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        }

        if (selectedHeader == null) {
            return false;
        }
        String classInfo;
        selectedHeader.click();
        BaseUtil.pageLoaded();
        classInfo = selectedHeader.getAttribute("class");
        if (!classInfo.equals(option)) {
            selectedHeader.click();
            BaseUtil.pageLoaded();
            classInfo = selectedHeader.getAttribute("class");
            if (!classInfo.equals(option)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This is where you place the associations of the tab names to their locator IDs.
     * Two locators will be needed for each tab; the ID of the tab and the ID of the grid.
     */
    private void initializeMaps() {
        // Purchase Orders tabs
        gridMap.put("requeststab", "tabbtnRequests");
        gridMap.put("requests", "dtRequests");
        gridMap.put("purchaseorderstab", "tabbtnPurchaseOrders");
        gridMap.put("purchaseorders", "dtPurchaseOrder");
        gridMap.put("pendingapprovaltab", "tabbtnApproval");
        gridMap.put("pendingapproval", "dtApproval");
        // Policy Checking tabs
        gridMap.put("openpoliciestab", "tabbtnopenpolicies");
        gridMap.put("openpolicies", "dtopenpolicies");
        gridMap.put("completedpoliciestab", "tabbtncompletedpolicies");
        gridMap.put("completedpolicies", "dtcompletedpolicies");
        gridMap.put("discardedpoliciestab", "tabbtndiscardedpolicies");
        gridMap.put("discardedpolicies", "dtdiscardedpolicies");
        gridMap.put("timerecopen", "dtTimeRecord");
        gridMap.put("timerecord", "dtTimeRecord");
        // Policy Checking Work Order tabs
        gridMap.put("attachmentstab", "attachments_sects");
        gridMap.put("attachments", "dtattachments");
        gridMap.put("emailtab", "Email_sects");
        gridMap.put("emails", "dtemails");
        gridMap.put("historytab", "history_sects");
        gridMap.put("history", "dthistory");
        gridMap.put("timerecords", "dttimerecords");

        gridMap.put("open", "dtprocessing");
        gridMap.put("opentab", "tabbtnprocessing");
        // Certificate Issuance tabs
        gridMap.put("inprocess", "dtprocessing");
        gridMap.put("inprocesstab", "tabbtnprocessing");
        gridMap.put("completed", "dtcompleted");
        gridMap.put("completedtab", "tabbtncompleted");
        gridMap.put("discarded", "dtdiscarded");
        gridMap.put("discardedtab", "tabbtndiscarded");
        gridMap.put("incomingresponsestab", "tabbtn-incoming-responses");
        gridMap.put("incomingresponses", "dt-incoming-responses");
        gridMap.put("importantinstructiontab", "tabbtn-important-instruction");
        gridMap.put("importantinstruction", "dt-important-instruction");
        gridMap.put("tobediscardedtab", "tabbtn-tobeDiscarded");
        gridMap.put("tobediscarded", "dt-tobeDiscarded");

        gridMap.put("exceptionstab", "tabbtnException");
        gridMap.put("exceptions", "dtException");
        // PMA Tabs
        gridMap.put("opportunitytab", "tabbtnopportunity");
        gridMap.put("opportunity", "dtopportunity");
        gridMap.put("businesstab", "tabbtnbusiness");
        gridMap.put("business", "dtbusiness");
        gridMap.put("importedleadstab", "tabbtnimportedleads");
        gridMap.put("importedleads", "dtimportedleads");

        // Implementations tabs
        gridMap.put("implementations", "dt-implementations");
        gridMap.put("implementationstab", "tab-btn-implementations");

        // Print Shop Tabs
        gridMap.put("processingtab", "tabbtnprocessing");
        gridMap.put("processing", "dtprocessing");
        gridMap.put("printedtab", "tabbtnprinted");
        gridMap.put("printed", "dtprinted");
    }

    /**
     * Will enter text into a text input type grid header or return the current text visible in the header.
     *
     * @param headerName The name of the header as it displays on the page
     * @param input      The text to be input
     * @return When <b>input</b> is <code>null</code> returns the current text in the header, otherwise returns <code>null</code>
     */
    public String gridHeaderField(String headerName, String input) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath("" +
                "//*[@class='filterHeader sorting'][normalize-space()='" + headerName + "']/following::input[1]"));


        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        }

        if (input == null) {
            assert selectedHeader != null;
            if (selectedHeader.getAttribute("value") == null) {
                return selectedHeader.getText();
            }
            return selectedHeader.getAttribute("value");

        }

        assert selectedHeader != null;
        try {
            selectedHeader.click();

        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        selectedHeader.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedHeader.sendKeys(input);
        selectedHeader.click();
        return null;
    }

    /**
     * Finds the <code>WebElement</code> of the requested selector type header.
     *
     * @param headerName Name of the header as it displays on the page
     * @return Returns the selector's <code>WebElement</code>
     */
    public WebElement gridHeaderSelector(String headerName) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath("//*[@class='filterHeader sorting'][normalize-space()='" + headerName + "']/following::button[1]"));

        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;

                    }
                }
            }
        }

        return selectedHeader;
    }

    /**
     * Selects an option from a selector (drop down) grid header.
     *
     * @param headerName Name of the header as it displays on the page
     * @param selection  Name of the selection desired
     * @return Returns <code>true</code> if selection was possible, otherwise returns <code>false</code>
     */
    public boolean gridHeaderSelectorSelect(String headerName, String selection) {
        WebElement selectedHeader = gridHeaderSelector(headerName);

        if (selectedHeader == null) {
            return false;
        }

        try {
            selectedHeader.click();

        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }
        try {
            selectedHeader.findElement(By.xpath("following-sibling::ul/li/descendant::input[@value='" + selection + "'] ")).click();
        } catch (NoSuchElementException e) {
            return false;
        }

        selectedHeader.click();

        return true;

    }

    /**
     * Returns currently selected items from a selector (drop down) type header in the grid.
     *
     * @param headerName Header name as it displays on the page
     * @return Returns an ArrayList of Strings containing all of the current selections
     */
    public ArrayList<String> gridHeaderSelectorRead(String headerName) {
        ArrayList<String> selections = new ArrayList<>();
        WebElement selectedHeader = gridHeaderSelector(headerName);
        if (selectedHeader == null) {
            return null;
        }
        selectedHeader.click();
        wait.until(ExpectedConditions.visibilityOf(selectedHeader.findElement(By.xpath("following-sibling::ul"))));
        List<WebElement> active = selectedHeader.findElements(By.xpath("following::li[@class='active']"));
        if (active.size() > 0) {
            for (WebElement element : active) {
                selections.add(element.getText().trim());
            }
        } else {
            selectedHeader.click();
            return null;
        }

        selectedHeader.click();
        return selections;
    }

    /**
     * Gets the <code>WebElement</code> of the specified date picker type grid header.
     *
     * @param header Header name as it displays on the page
     * @param fromTo The "from" or "to" date picker for that header
     * @return Returns the <code>WebElement</code> of either the "from" or "to" picker
     */
    public WebElement gridHeaderDate(String header, String fromTo) {
        WebElement selectedHeader = null;
//        List<WebElement> headers = driver.findElements(By.xpath(
//                "//th[normalize-space(@aria-label)='"+header+"']/descendant::input[contains(@placeholder, '"+fromTo+"')]"));
        List<WebElement> headers = driver.findElements(By.xpath(
                "//th[contains(@aria-label, '" + header + "')]/descendant::input[contains(@placeholder, '" + fromTo + "')]"));

        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        } else {
            return null;
        }
        return selectedHeader;
    }

    /**
     * Sets the date for either the "from" or "to" option of a grid header date picker.
     * Use this option if the picker does not have a time selection.
     *
     * @param header Name of the header as it displays on the page
     * @param fromTo Either "from" or "to" depending on which option is to be used
     * @param day    The day without any leading zero (e.g., "5" not "05")
     * @param month  The full text name of the month with proper capitalization (e.g., "August")
     * @param year   The four-digit year (e.g., "2021")
     * @return Returns <code>true</code> if picking the date was successful, otherwise returns <code>false</code>
     */
    public boolean gridHeaderDateSelect(String header, String fromTo, String day, String month, String year) {
        WebElement selectedHeader = gridHeaderDate(header, fromTo);

        if (selectedHeader == null) {
            return false;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(selectedHeader)).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        BaseUtil.pageLoaded();

        WebElement pickerMonthYear = driver.findElement(By.xpath("//div[@class='datepicker-days']/descendant::th[@class='datepicker-switch']"));
        WebElement pickerYear = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='datepicker-switch']"));
        WebElement pickerNext = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='next']"));
        WebElement pickerPrev = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='prev']"));

        String curMY = pickerMonthYear.getText().replaceAll("\\s+", "");
        String curMonth = curMY.replaceAll("\\d", "");
        String curYear = curMY.replaceAll("\\D", "");

        if (!month.equalsIgnoreCase(curMonth) || !year.equalsIgnoreCase(curYear)) {
            pickerMonthYear.click();
            wait.until(ExpectedConditions.visibilityOf(pickerYear));
            if (!pickerYear.getText().equalsIgnoreCase(year)) {
                int myYr = Integer.parseInt(year);
                long startTime = System.currentTimeMillis();
                while (!pickerYear.getText().equalsIgnoreCase(year) && (System.currentTimeMillis() - startTime) < 30000) {
                    int pickYr = Integer.parseInt(pickerYear.getText());
                    if (pickYr < myYr) {
                        pickerNext.click();
                    } else {
                        pickerPrev.click();
                    }
                }

            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='datepicker-months']/descendant::span[contains(@class, 'month') " +
                            "and text()='" + month.substring(0, 3) + "']"))).click();


        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='datepicker-days']" +
                "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()='" + day + "']"))).click();

        return true;

    }

    /**
     * Sets the date for either the "from" or "to" option of a grid header date picker.
     * Use this option if the date picker requires time to be selected.
     *
     * @param header     Name of the header as it displays on the page
     * @param selections A Map of Strings containing selections for
     *                   "Day", "Month", "Year", "Time", "AM / PM", and "From / To"
     * @return Returns <code>true</code> if date selection successful, otherwise returns <code>false</code>
     */
    public boolean gridHeaderDateSelect(String header, Map<String, String> selections) {
        WebElement selectedHeader = gridHeaderDate(header, selections.get("From / To"));

        if (selectedHeader == null) {
            return false;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(selectedHeader)).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        BaseUtil.pageLoaded();

        WebElement pickerMonthYear = null;
        List<WebElement> pMY = driver.findElements(By.xpath("//div[@class='datetimepicker-days']/descendant::th[@class='switch']"));
        for (WebElement ele : pMY) {
            if (ele.isDisplayed()) {
                pickerMonthYear = ele;
            }
        }
        String hour = selections.get("Time").replaceAll("(:\\d\\d)", "");
        WebElement pickerYear = null;
        WebElement pickerNext = null;
        WebElement pickerPrev = null;

        assert pickerMonthYear != null;
        String curMY = pickerMonthYear.getText().replaceAll("\\s+", "");
        String curMonth = curMY.replaceAll("\\d", "");
        String curYear = curMY.replaceAll("\\D", "");

        if (!selections.get("Month").equalsIgnoreCase(curMonth) || !selections.get("Year").equalsIgnoreCase(curYear)) {
            pickerMonthYear.click();
            List<WebElement> pY = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='switch']"));
            for (WebElement ele : pY) {
                if (ele.isDisplayed()) {
                    pickerYear = ele;
                }
            }
            List<WebElement> pN = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='next']"));
            for (WebElement ele : pN) {
                if (ele.isDisplayed()) {
                    pickerNext = ele;
                }
            }
            List<WebElement> pP = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='prev']"));
            for (WebElement ele : pP) {
                if (ele.isDisplayed()) {
                    pickerPrev = ele;
                }
            }
            assert pickerYear != null;
            assert pickerPrev != null;
            assert pickerNext != null;
            if (!pickerYear.getText().equalsIgnoreCase(selections.get("Year"))) {
                int myYr = Integer.parseInt(selections.get("Year"));
                long startTime = System.currentTimeMillis();
                while (!pickerYear.getText().equalsIgnoreCase(selections.get("Year")) && (System.currentTimeMillis() - startTime) < 30000) {
                    int pickYr = Integer.parseInt(pickerYear.getText());
                    if (pickYr < myYr) {
                        pickerNext.click();
                    } else {
                        pickerPrev.click();
                    }
                }

            }
            List<WebElement> pM = driver.findElements(By.xpath(
                    "//div[@class='datetimepicker-months']/descendant::span[contains(@class, 'month') " +
                            "and text()='" + selections.get("Month").substring(0, 3) + "']"));
            for (WebElement ele : pM) {
                if (ele.isDisplayed()) {
                    ele.click();
                    break;
                }
            }

        }
        List<WebElement> pD = driver.findElements(By.xpath("//div[@class='datetimepicker-days']" +
                "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()='" + selections.get("Day") + "']"));
        for (WebElement ele : pD) {
            if (ele.isDisplayed()) {
                ele.click();
            }
        }
        List<WebElement> pH = driver.findElements(By.xpath(
                "//div[@class='datetimepicker-hours']/descendant::legend[normalize-space()='" + selections.get("AM / PM") + "']/following-sibling::span[normalize-space()='" + hour + "']"));
        for (WebElement ele : pH) {
            if (ele.isDisplayed()) {
                ele.click();
                break;
            }
        }
        List<WebElement> pMi = driver.findElements(By.xpath(
                "//div[@class='datetimepicker-minutes']/descendant::legend[normalize-space()='" + selections.get("AM / PM") + "']/following-sibling::span[normalize-space()='" + selections.get("Time") + "']"));
        for (WebElement ele : pMi) {
            if (ele.isDisplayed()) {
                ele.click();
                break;
            }
        }

        return true;

    }

    /**
     * Finds and returns the WebElement of the grid cell that matches the requested column and row.
     *
     * @param tableRow    This is the row of the grid being requested. Set this to either the text in the first cell of the row
     *                    being requested (such as the work order number in purchase orders)
     *                    or the number of the row (such as "row 3")
     * @param tableColumn This is the column of the grid being requested. Set this to the header name for the column
     * @return Returns the WebElement of the matching column and row cell of the grid.
     */
    public WebElement gridEntry(String tableRow, String tableColumn) {
        WebElement table = getGridElement();
        WebElement column = null;
        WebElement row = null;
        String rowPath;
        String colPath;
        String title = table.getAttribute("id");

        assert table != null;
        column = table.findElement(By.xpath("thead/descendant::*[normalize-space()='" + tableColumn + "']"));


        assert column != null;
        colPath = generateXPATH(column, "");
        Pattern colP = Pattern.compile("(?<=th\\[)(\\d+)(?=\\])");
        assert colPath != null;
        Matcher colM = colP.matcher(colPath);
        while (colM.find()) {
            colPath = colM.group();
        }

        if (tableRow.toLowerCase().contains("row")) {
            rowPath = tableRow.replaceAll("\\D", "");
        } else {
            row = table.findElement(By.xpath("tbody/descendant::td[normalize-space()='" + tableColumn + "']"));

            assert row != null;
            rowPath = generateXPATH(row, "");
            Pattern rowP = Pattern.compile("(?<=tr\\[)(\\d+)(?=\\])");
            assert rowPath != null;
            Matcher rowM = rowP.matcher(rowPath);
            while (rowM.find()) {
                rowPath = rowM.group();
            }
        }

        List<WebElement> results = driver.findElements(By.xpath("" +
                "//*[@id='" + title + "']/tbody/tr[" + rowPath + "]/td[" + colPath + "]"));
        for (WebElement resElement : results) {
            if (resElement.isDisplayed()) {
                return resElement;
            }
        }

        return null;
    }

    public String gridOpenItem(String tabName, String column) {
        int rows = gridRowCount(tabName);
        return "rows in the grid called from gridOpenItem: " + rows;
    }

    public String generateXPATH(WebElement childElement, String current) {
        String childTag = childElement.getTagName();
        if (childTag.equals("html")) {
            return "/html[1]" + current;
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for (int i = 0; i < childrenElements.size(); i++) {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if (childTag.equals(childrenElementTag)) {
                count++;
            }
            if (childElement.equals(childrenElement)) {
                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
            }
        }
        return null;
    }

    public void clickErrorHandle(String error, WebElement target) {
        String xPath = "";
        String selector = "";
        Pattern element = Pattern.compile("(?<=click: \\<)(.*?)(?=\\s*\\>)");
        Pattern type = Pattern.compile("^\\w+");
        Pattern tag = Pattern.compile("\\w+=+\"(.*?)\"");
        Matcher eleM = element.matcher(error);
        while (eleM.find()) {
            selector = eleM.group();
        }

        Matcher typeM = type.matcher(selector);
        while (typeM.find()) {
            xPath += "//" + typeM.group();
        }

        Matcher tagM = tag.matcher(selector);
        while (tagM.find()) {
            xPath += "[@" + tagM.group() + "]";
        }
        List<WebElement> blockers = driver.findElements(By.xpath(xPath));
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(blockers));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
        } catch (TimeoutException ignored) {

        }

        target.click();
    }

    public String verifyBackGroundColor(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        try {
            WebElement gridFirstRow = driver.findElement(By.xpath("//*[@id='" + currentTab + "']/tbody/tr[1]"));
            return gridFirstRow.getCssValue("background-color");
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Was unable to find the first row in the " + currentTab + " grid.");
            return null;
        }
    }

    public void clickResetButton() {
        List<WebElement> resetButtons = driver.findElements(By.className("search-clear"));
        for (WebElement button : resetButtons) {
            if (button.isDisplayed()) {
                button.click();
                break;
            }
        }

    }
    public void waitForFilter(String originalTopRecordID, String header) {
        waitForFilter(originalTopRecordID, header, 3);
    }
    public void waitForFilter(String originalTopRecordID, String header, long waitLength) {
        System.out.println("Waiting for the Grid to be resorted.");
        waitLength = waitLength * 1000; // converting from seconds to milliseconds
        long startTime = System.currentTimeMillis();
        try {
            while (gridEntry("row 1", header).getText().equals(originalTopRecordID) && (System.currentTimeMillis() - startTime) < waitLength) {
                try {
                    Thread.sleep(500); //check table every half second to see if it has been filtered.
                } catch (InterruptedException ignored) {
                    System.out.println("Sleep was interrupted.");
                    //break;
                }
            }
        } catch (StaleElementReferenceException e) {
            // This happens when no entries are displayed.
            System.out.println("Grid has no entries.");
            return;
        }

        if (gridEntry("row 1", header).getText().equals(originalTopRecordID)) {
            System.out.println("Grid filter wait timed out. Top record is the same after filtering.");
        } else {
            System.out.println("Grid has been sorted.");
        }
    }

    /**
     * Sets the page in the requested grid to the requested number
     *
     * @param tabName Name of the tab as it displays on the page
     * @param pageNum The page number to be navigated to
     * @return Returns the new page number if it was successful, -1 if the requested page does not exist, 0 if the current page is the same as the requested page
     */
    public int gridSetPage(String tabName, int pageNum) {
        int totalPages = gridPageNumber(tabName, "total");
        int currPage = gridPageNumber(tabName, "current");
        String tabNameFixed = tabName.replaceAll("\\s+", "").toLowerCase();
        WebElement numField = driver.findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
        if (totalPages < pageNum) return -1;
        if (currPage == pageNum) return 0;
        numField.click();
        numField.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        numField.sendKeys(Integer.toString(pageNum));
        numField.sendKeys(Keys.ENTER);
        BaseUtil.pageLoaded();
        return gridPageNumber(tabName, "current");

    }

    public String topRowText() {
        // used to verify an empty table
        String title = null;
        List<WebElement> titles = driver.findElements(By.xpath("//table[@id]"));
        for (WebElement ttlEle : titles) {
            if (ttlEle.isDisplayed() && ttlEle.getAttribute("id").length() > 0) {
                title = ttlEle.getAttribute("id");
                break;
            }
        }
        return driver.findElement(By.xpath("//*[@id='" + title + "']/tbody/tr/td")).getText();
    }
    public void verifySearchFieldEmpty(){
        String search= fieldSearchStatus.getAttribute("value");
        Boolean condition=search.isEmpty();
        Assert.assertTrue(condition, "Search Field is not empty");
        System.out.println("Search field is empty");
    }
    public void enteringSearchField(String Initiator_Name){
        fieldSearchInitiator.sendKeys(Initiator_Name);
    }
    public void searchingStatus(String Status_Value){
        fieldSearchStatus.sendKeys(Status_Value);
    }
}
