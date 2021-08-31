package Pages;

import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Base.BaseUtil.currentTab;
import static Base.BaseUtil.pageLoaded;

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

    public boolean gridTab(String tabName) {
        String tabFix = tabName.toLowerCase().replaceAll("\\s+", "");
        WebElement tab = driver.findElement(By.id(gridMap.get(tabFix + "tab")));
        BaseUtil.pageLoaded();
        try {
            js.executeScript("arguments[0].click();",tab);
            //tab.click(); /*This Was giving element not clickable message , so used javascript executor*/
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), tab);
        }
        BaseUtil.pageLoaded();
        return true;

    }

    WebElement gridPageNum;
    WebElement totalPages;


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


    public void gridViewSelection(String tabName, String option) {
        System.out.println("Selecting " + option + " from Viewing dropdown");
        WebElement dropDown = driver.findElement(By.xpath("//*[@id='" + gridMap.get(tabName.toLowerCase().replaceAll("\\s+", "")) + "_length']/label/select"));
        Select rows = new Select(dropDown);
        dropDown.click();
        rows.selectByVisibleText(option);
    }


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


    public int gridRowCount(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='" + currentTab + "']/tbody/tr"));
        return rows.size();
    }

    /**
     * Will search the page for the specified grid header
     *
     * @param tab
     * @param header
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
                "//*[contains(@class, 'filterHeader')][normalize-space()='" + headerName + "']"));

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

    private void initializeMaps() {
        gridMap.put("opentab", "tabbtnprocessing");
        gridMap.put("open", "dtprocessing");
        gridMap.put("completedtab", "tabbtncompleted");
        gridMap.put("completed", "dtcompleted");
        gridMap.put("discardedtab", "tabbtndiscarded");
        gridMap.put("discarded", "dtdiscarded");
        gridMap.put("importantinstructionstab", "tabbtnImpInsGrid");
        gridMap.put("importantinstructions", "dtimpIns");
        gridMap.put("advancedsearch", "dt-AdvanceSearch");
        gridMap.put("advancedsearchtab", "advanceSearchtab");

        // Loss Runs Work Order tabs
        gridMap.put("order/reminderdatestab", "Accct_sects");
        gridMap.put("yeardetailstab", "QA_sects");
        gridMap.put("attachmentstab", "attachments_sects");
        gridMap.put("attachments", "dtattachments");
        gridMap.put("emailtab", "Email_sects");
        gridMap.put("emails", "dtemails");
        gridMap.put("historytab", "history_sects");
        gridMap.put("history", "dthistory");
        gridMap.put("timerecordstab", "timerecords_sects");


        // Time Records Tabs
        gridMap.put("timerecords", "dttimerecords");
        gridMap.put("timerecord", "dtTimeRecord");
        gridMap.put("timerecordopen","dtTimeRecordOpen");
        gridMap.put("timerecordadmin","dtTimeRecordAdmin");

        // Endorsement Processing reset button
        gridMap.put("openreset", "processing");
        gridMap.put("completedreset", "completed");
        gridMap.put("discardedreset", "discarded");
        gridMap.put("important instructionsreset", "impIns");


        // Certificate Issuance tabs
        gridMap.put("inprocess", "dtprocessing");
        gridMap.put("inprocesstab", "tabbtnprocessing");
        gridMap.put("completed", "dtcompleted");
        gridMap.put("completedtab", "tabbtncompleted");
        gridMap.put("discarded", "dtdiscarded");
        gridMap.put("discardedtab", "tabbtndiscarded");
        gridMap.put("incomingresponsestab", "tabbtn-incoming-responses");
        gridMap.put("incomingresponses", "dt-incoming-responses");
        gridMap.put("importantinstructionstab", "tabbtnImpInsGrid");
        gridMap.put("importantinstructions", "dt-important-instruction");
        gridMap.put("tobediscardedtab", "tabbtn-tobeDiscarded");
        gridMap.put("tobediscarded", "dt-tobeDiscarded");

        gridMap.put("exceptionstab", "tabbtnException");
        gridMap.put("exceptions", "dtException");

    }

    public String gridHeaderField(String headerName, String input) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath(
                "//*[contains(@class, 'filterHeader') and normalize-space()='" + headerName + "']/following::input[1]"));

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
     * This method was made for the Hours column in the Time Rec Admin grid, which has two fields that need to be filled with the same value.
     * @param headerName
     * @param input
     * @return
     */
    public void gridHeaderFields(String headerName, String input) {
        List <WebElement> selectedHeaders = null;
        List<WebElement> headers = driver.findElements(By.xpath(
                "//*[contains(@class, 'filterHeader') and normalize-space()='"+ headerName +"']/../descendant::input"));
        assert !headers.isEmpty();

        for(WebElement selectedHeader: headers){
            if(selectedHeader.isDisplayed()) {
                try {
                    selectedHeader.click();

                } catch (ElementClickInterceptedException e) {
                    clickErrorHandle(e.toString(), selectedHeader);
                }

                selectedHeader.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
                selectedHeader.sendKeys(input);
                selectedHeader.click();
            }
        }
    }

    public WebElement gridHeaderSelector(String headerName) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath("//*[contains(@class, 'filterHeader') and normalize-space()='" + headerName + "']/..//button"));

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
                        break;
                    }
                }
            }
        } else {
            return null;
        }
        return selectedHeader;
    }

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

        // Look for a visible date picker. Find the current displayed month and year
        WebElement pickerMonthYear = null;
        List<WebElement> pMY = driver.findElements(By.xpath("//div[@class='datetimepicker-days']/descendant::th[@class='switch']"));
        pMY.addAll(driver.findElements(By.xpath("//div[@class='datepicker-days']/descendant::th[@class='datepicker-switch']"))); //This is for the End Time header in the Time Rec Admin table.
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

        // If the currently displayed month or year does not match the desired, find the arrow buttons or year and click.
        if (!selections.get("Month").equalsIgnoreCase(curMonth) || !selections.get("Year").equalsIgnoreCase(curYear)) {
            pickerMonthYear.click();
            List<WebElement> pY = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='switch']"));
            pY.addAll(driver.findElements(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='datepicker-switch']"))); //This is for the End Time header in the Time Rec Admin table.
            for (WebElement ele : pY) {
                if (ele.isDisplayed()) {
                    pickerYear = ele;
                }
            }
            List<WebElement> pN = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='next']"));
            pN.addAll(driver.findElements(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='next']"))); //This is for the End Time header in the Time Rec Admin table.
            for (WebElement ele : pN) {
                if (ele.isDisplayed()) {
                    pickerNext = ele;
                }
            }
            List<WebElement> pP = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='prev']"));
            pP.addAll(driver.findElements(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='prev']"))); //This is for the End Time header in the Time Rec Admin table.
            for (WebElement ele : pP) {
                if (ele.isDisplayed()) {
                    pickerPrev = ele;
                }
            }
            assert pickerYear != null;
            assert pickerPrev != null;
            assert pickerNext != null;

            // Navigate forward or backward through years to the correct year.
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

            // Find and click the correct month.
            List<WebElement> pM = driver.findElements(By.xpath(
                    "//div[@class='datetimepicker-months']/descendant::span[contains(@class, 'month') " +
                            "and text()='" + selections.get("Month").substring(0, 3) + "']"));
            pM.addAll(driver.findElements(By.xpath(
                    "//div[@class='datepicker-months']/descendant::span[contains(@class, 'month') " +
                            "and text()='" + selections.get("Month").substring(0, 3) + "']")));
            for (WebElement ele : pM) {
                if (ele.isDisplayed()) {
                    ele.click();
                    break;
                }
            }

        }

        // Now viewing the correct month+year in the date picker. Click the correct day.
        List<WebElement> pD = driver.findElements(By.xpath("//div[@class='datetimepicker-days']" +
                "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()='" + selections.get("Day") + "']"));
        pD.addAll(driver.findElements(By.xpath("//div[@class='datepicker-days']/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()='" + selections.get("Day") + "']")));//This is for the End Time header in the Time Rec Admin table.
        for (WebElement ele : pD) {
            if (ele.isDisplayed()) {
                ele.click();
            }
        }

        // If a time needs to be selected, it will be found and clicked.
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
     * Finds and returns the text in the grid cell that matches the requested column and row.
     *
     * @param tableRow    This is the row of the grid being requested. Set this to either the text in the first cell of the row
     *                    being requested (such as the work order number in purchase orders)
     *                    or the number of the row (such as "row 3")
     * @param tableColumn This is the column of the grid being requested. Set this to the header name for the column
     * @return Returns the text in the matching column and row cell of the grid.
     */
    public WebElement gridEntry(String tableRow, String tableColumn) {
        WebElement column = null;
        WebElement row = null;
        String rowPath;
        String colPath;
        String title = null;

        List<WebElement> titles = driver.findElements(By.xpath("//table[@id]"));
        for (WebElement ttlEle : titles) {
            if (ttlEle.isDisplayed() && ttlEle.getAttribute("id").length() > 0) {
                title = ttlEle.getAttribute("id");
                break;
            }
        }

        List<WebElement> setCol = driver.findElements(By.xpath(
                "//th/div[normalize-space()=\"" + tableColumn + "\"]"));
        for (WebElement colElement : setCol) {
            if (colElement.isDisplayed()) {
                column = colElement;
                break;
            }
        }

        assert column != null;
        colPath = generateXPATH(column, "");
        Pattern colP = Pattern.compile("(?<=th\\[)(\\d+)(?=\\])");
        assert colPath != null;
        Matcher colM = colP.matcher(colPath);
        while (colM.find()) {
            colPath = colM.group();
        }

        if (tableRow.toLowerCase().contains("row")) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tableRow.length(); i++) {
                if (Character.isDigit(tableRow.charAt(i))) {
                    stringBuilder.append(tableRow.charAt(i));
                }

            }
            rowPath = stringBuilder.toString();
        } else {
            List<WebElement> setRow = driver.findElements(By.xpath("" +
                    "//td[normalize-space()='" + tableRow + "']"));
            for (WebElement rowElement : setRow) {
                if (rowElement.isDisplayed()) {
                    row = rowElement;
                    break;
                }
            }

            //Console reporting for error checking/fixing.
            if (row == null) {
                System.out.println("Row " + tableRow + " does not exist.");
            }

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

    public String gridHeaderFieldRead(String headerName) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath(
                "//*[contains(@class, 'filterHeader') and normalize-space()='" + headerName + "']/following::input[1]"));

        if (headers.size() == 0) {
            System.out.println("Header not found.");
            return null;
        }
        return headers.get(0).getAttribute("value");
    }

    /**
     * This method waits for the top row of the grid to be different, indicating that the table has been filtered.
     * This will time out if filtering for a record already in the top row.
     * This is an overloaded method to use a default wait value when waiting for a sort
     * Some columns need more time to sort, so a longer wait time can be passed as an argument.
     * @param originalTopRecordID is a string containing the number of the starting/old top record.
     * @param header is the header to check against. Usually this is the WO #, but not all grids have good WO # columns (eg. Time Records)
     */
    public void waitForFilter(String originalTopRecordID, String header) {
        waitForFilter(originalTopRecordID, header, 3000);
    }

    /**
     * This method waits for the top row of the grid to be different, indicating that the table has been filtered.
     * This will time out if filtering for a record already in the top row.
     * @param originalTopRecordID is a string containing the number of the starting/old top record.
     * @param header is the header to check against. Usually this is the WO #, but not all grids have good WO # columns (eg. Time Records)
     * @param waitLength is how long to wait (in milliseconds) for a column to sort before timing out.
     * */
    public void waitForFilter(String originalTopRecordID, String header, long waitLength) {
        System.out.println("Waiting for the Grid to be resorted.");
        long startTime = System.currentTimeMillis();
        try {
            while (gridEntry("row 1", header).getText().equals(originalTopRecordID) && (System.currentTimeMillis() - startTime) < waitLength) {
                try {
                    Thread.sleep(1000); //check table every half second to see if it has been filtered.
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

    public void clickTopWorkOrder(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        try {
            driver.findElement(By.xpath("//*[@id='" + currentTab + "']/tbody/tr[1]/td[1]/a")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.xpath("//*[@id='" + currentTab + "']/tbody/tr/td[1]/a")).click();
        }
    }


    public void verifyBackGroundColor(String colour) throws Exception {
        // Todo: move expected colour RGB values to a properties file for better management.
        WebElement topRow = driver.findElement(By.xpath("//*[@id=\"" + gridMap.get(currentTab.toLowerCase().replaceAll("\\s+", ""))+ "\"]/tbody/tr[1]"));
        String cssColour = topRow.getCssValue("background-color");
        System.out.println("Verifying Color in Grid row  " + cssColour);
        switch (colour) {
            case "White" :
                Assert.assertEquals("rgba(249, 249, 249, 1)", cssColour, "The colour did not match.");
            case "Rush - Yellow" :
                Assert.assertEquals("rgba(255, 255, 102, 1)", cssColour, "The colour did not match.");
            case "AGS Insurance - Green" :
                Assert.assertEquals("rgba(0, 100, 0, 1)", cssColour);
            case "donut Account -Light Green" :
                Assert.assertEquals("rgba(153, 204, 51, 1)", cssColour, "The colour did not match.");
            case "DueDateToday -Purple" :
                Assert.assertEquals("rgba(206, 84, 200, 1)", cssColour, "The colour did not match.");
            case "Cancel -Blue" :
                Assert.assertEquals("rgba(103, 200, 255, 1)", cssColour, "The colour did not match.");//#67c8ff
            case "SLA -Red" :
                Assert.assertEquals("rgba(238, 67, 67, 1)", cssColour, "The colour did not match.");
            case "Time Rec Open Orange" :
                Assert.assertEquals("rgba(246, 169, 3, 1)", cssColour, "The colour did not match.");
            case "Time Rec Open Green":
                Assert.assertEquals("rgba(132, 207, 10, 1)", cssColour, "The colour did not match.");
            default :
                Assert.fail("Colour: " + colour + " was not in the switch statement.");
        }
    }

    public String getIndicatorMouseoverText(String colour, String currentTab) {
        // there are new ids that are not in the gridMap, and I'm not sure how best to distinguish them, so for now, we have a mini tab map here.
        switch (currentTab){
            case "Open Policies":
                currentTab = "tabopenpolicies";
                break;
            case "Completed Policies":
                currentTab = "tabcompletedpolicies";
                break;
            case "Discarded Policies":
                currentTab = "tabdiscardedpolicies";
                break;
            default:
        }

       List <WebElement> allColourBoxes = new ArrayList();

        // each box has its own class that does not follow a convention, so we need to use a switch statement to find them.
        switch (colour.toLowerCase()) {
            case "white":// There only class the white box has is "indicator," which is not unique in the DOM.
                allColourBoxes = driver.findElements(By.xpath("//*[@id=\""+currentTab+"\"]/div/div[1]/div/span[2]/div"));
                break;
            case "yellow":
                allColourBoxes = driver.findElements(By.className("yellow-row"));
                break;
            case "green":
                allColourBoxes = driver.findElements(By.className("green-row"));
                break;
            case "light green":
                allColourBoxes = driver.findElements(By.className("LightGreen"));
                break;
            case "purple":
                allColourBoxes = driver.findElements(By.className("dark-pink"));
                break;
            case "red":
                allColourBoxes = driver.findElements(By.className("red-grid"));
                break;
            case "blue":// There only classes the blue box has are "indicator" and "blue" which are not unique in the DOM.
                allColourBoxes = driver.findElements(By.xpath("//*[@id=\""+currentTab+"\"]/div/div[1]/div/span[8]/div"));
                break;
            default:
                return "Colour not found in switch statement.";
        }
        for( WebElement colourBox: allColourBoxes){
            if(colourBox.isDisplayed()){
                return colourBox.getAttribute("title");
            }
        }
        return "Text not found";
    }

    /**
     * Used to sum all (numerical) values of a single column in a table.
     * @return The combined total of all values in the column.
     */
    public float getCumulative(String header) {
        float runningTotal = 0;
        do {
            pageLoaded();
            for (int i = 1; i <= gridRowCount(currentTab); i++) {
                runningTotal += Float.parseFloat(gridEntry("row " + Integer.toString(i), header).getText());
            }
        } while(gridNextPage(currentTab));

        return runningTotal;
    }

    public boolean openWorkOrder(String button) {
        try {
            WebElement target = driver.findElement(By.xpath("//a[.//text()[normalize-space()='" + button + "']]"));
            target.click();
            System.out.println("Opening WO#:  " + button );
            return true;
        } catch ( Exception e) {
            System.out.println("Cannot find WO#: " + button);
            return false;
        }
    }
    public void clickResetButton() {
        List<WebElement> resetButtons = driver.findElements(By.className("search-clear"));
        for (WebElement button : resetButtons) {
            if (button.isDisplayed()) {
                js.executeScript("arguments[0].click();",button);
                break;
            }
        }
    }
}
