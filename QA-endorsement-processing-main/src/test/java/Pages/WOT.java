package Pages;

import Base.BaseUtil;
import Base.KpiClass;
import Base.NodeApp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WOT extends NodeApp {
    private HashMap<String, String> gridMap = new HashMap<>();
    private final WebDriverWait wait;
    private final WebDriver driver;

    public WOT(WebDriver driver, JavascriptExecutor js) {

        super(driver, js);
        initializeMaps();
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
    }

    @FindBy(how = How.ID, using = "companyStartId")
    private WebElement companySelector;
    @FindBy(how = How.XPATH, using = "//button[@data-id=\"serviceStartId\"]")
    private WebElement serviceSelector;
    @FindBy(how = How.ID, using = "typeaheadValue")
    private WebElement globalSearch;
    @FindBy(how = How.CLASS_NAME, using = "tt-suggestion tt-selectable") //Global Search link select
    public WebElement linkGlobalSearchSelection;
    @FindBy(how = How.ID, using = "startTime_err")
    public WebElement timeAdmin_FromDateError;
    @FindBy(how = How.ID, using = "startTime_err")
    public WebElement timeAdmin_ToDateError;
    @FindBy(how = How.ID, using = "SLAMissedReason_error")
    private WebElement SLAMissedReasonErrMsg;


    @Override
    public boolean onCorrectPage() {
        pageLoaded();
        Select company = new Select(companySelector);
        String selectedComp = company.getFirstSelectedOption().getText().trim();
        String selectedServ = serviceSelector.getText().trim();
        return selectedComp.equals("Select Company")
                && selectedServ.equals("Select Service");

    }

    @Override
    public KpiClass addKpi(int number) {
        return null;
    }

    @Override
    public boolean gridTab(String tabName) {
        String tabFix = tabName.toLowerCase().replaceAll("\\s+", "");
        WebElement tab = super.getDriver().findElement(By.id(gridMap.get(tabFix + "tab")));


        try {
            if (!tab.getAttribute("class").contains("active")) {
                while(!tab.getAttribute("class").contains("active")) {
                    try{
                        tab.click();
                    } catch (ElementClickInterceptedException e) {
                        clickErrorHandle(e.toString(), tab);
                    }
                }

            } else {
                System.out.println(tabName + " is already the current tab.");
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        super.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\""+gridMap.get(tabFix)+"\"]/thead//tr/th/div")));

        return true;

    }

    WebElement gridPageNum;
    WebElement totalPages;

    @Override
    public int gridPageNumber(String tabName, String result) {
        String tabNameFixed = tabName.replaceAll("\\s+", ""). toLowerCase();

        if(super.getDriver().findElement(By.id(gridMap.get(tabNameFixed) + "_paginate")).getAttribute("style").equals("display: none;")) {
            return 0;
        }
        try{
            gridPageNum = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
            totalPages = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-total"));
        } catch (NoSuchElementException e) {
            return -1;
        }

        String pageNum = gridPageNum.getAttribute("value");
        if(result.equals("current")) {
            return Integer.parseInt(pageNum);
        } else if(result.equals("total")) {
            return Integer.parseInt(totalPages.getText());
        }

        return -1;
    }

    @Override
    public int gridRandomPage(String tabName) {
        int totalPages = gridPageNumber(tabName, "total");
        String tabNameFixed = tabName.replaceAll("\\s+", ""). toLowerCase();
        WebElement pageNum = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
        System.out.println("Total number of available pages is: " + totalPages);
        if(totalPages == 1){
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

    @Override
    public boolean gridNextPage(String tabName) {
        WebElement nextBtn = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_next"));
        if(nextBtn.getAttribute("class").contains("disabled")) {
            return false;
        } else {
            nextBtn.findElement(By.tagName("a")).click();
            return true;
        }
    }

    @Override
    public boolean gridPrevPage(String tabName) {
        WebElement prevBtn = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_previous"));
        if(prevBtn.getAttribute("class").contains("disabled")) {
            return false;
        } else {
            prevBtn.findElement(By.tagName("a")).click();
            return true;
        }
    }

    @Override
    public void gridViewSelection(String tabName, String option) {
        System.out.println("Selecting " + option + " from Viewing dropdown");
        WebElement dropDown = super.getDriver().findElement(By.xpath("//*[@id=\""+gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""))+"_length\"]/label/select"));
        Select rows = new Select(dropDown);
        dropDown.click();
        rows.selectByVisibleText(option);
    }

    @Override
    public int gridRecordNumber(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        int recordCount;

        String records = super.getDriver().findElement(By.id(currentTab + "_info")).getText();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < records.length(); i++) {
            if(Character.isDigit(records.charAt(i))) {
                stringBuilder.append(records.charAt(i));
            }
        }
        if(stringBuilder.length() == 0) {
            return 0;
        }

        recordCount = Integer.parseInt(stringBuilder.toString());

        return recordCount;
    }

    @Override
    public int gridRowCount(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));

        List<WebElement> rows = super.getDriver().findElements(By.xpath("//*[@id=\""+currentTab+"\"]/tbody/tr"));
        return rows.size();

    }

    private void initializeMaps() {
        gridMap.put("openpolicies", "dtopenpolicies");
        gridMap.put("openpoliciestab", "tabbtnopenpolicies");
        gridMap.put("open", "dtprocessing");
        gridMap.put("opentab", "tabbtnprocessing");
        gridMap.put("completed", "dtcompleted");
        gridMap.put("completedtab", "tabbtncompleted");
        gridMap.put("discarded", "dtdiscarded");
        gridMap.put("discardedtab", "tabbtndiscarded");
    }

   public void doGlobalSearch(String txt) {
        globalSearch.sendKeys(txt);
   }

   public void selectFirstGlobalSearchResult() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tt-highlight")));
        WebElement result = driver.findElement(By.className("tt-highlight"));
        result.click();

   }

   public boolean confirmExpectedWOOpen(String txt) {
        boolean confirmation = false;
        String WO = driver.findElement(By.xpath("//label[contains(text(),'WO #')]/span[1]")).getText();
        if (WO.equals(txt)) {
            confirmation = true;
            System.out.println("Expected WO is " + txt + " and actual WO is " + WO);
        }
        return confirmation;
   }

   public boolean confirmGlobalSearchError() {
        boolean confirmation = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='tt-dataset tt-dataset-endorsement']/p")));
            String error = driver.findElement(By.xpath("//div[@class='tt-dataset tt-dataset-endorsement']/p")).getText();
            if (error.equals("Record Doesn't Exist.")) {
                confirmation = true;
                System.out.println("Expected error is present");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Cannot find the Global Search");
        }
        return confirmation;
   }

    public boolean errorWarningDisplayed(String fieldName) {
        // todo: make this more robust than a switch statement
        switch (fieldName) {
            case "To":
                return timeAdmin_ToDateError.isDisplayed() && timeAdmin_ToDateError.getText().contains("Invalid date format in Start Time From field.");
            case "From":
                return timeAdmin_FromDateError.isDisplayed() && timeAdmin_FromDateError.getText().contains("Invalid date format in Start Time From field.");
            case "SLA Missed Reason":
                return SLAMissedReasonErrMsg.isDisplayed() && SLAMissedReasonErrMsg.getText().contains("Please select reason");
            default:
                System.out.println("Could not find field.");
                return false;
        }
    }

    public void clickTopTimeRecEdit() {
        // These edit icons only appear in the Time Rec Open and Time Rec Admin tables.
        // Each edit icon in Time Rec Open has an ID value = edit_record
        // Each edit icon in Time Rec Admin has an ID value = edit_timerecord
        // We just search for the first visible element (the top row), and click it.
        List<WebElement> editIcons = getDriver().findElements(By.id("edit_record"));
        editIcons.addAll(getDriver().findElements(By.id("edit_timerecord")));
        if (!editIcons.isEmpty()) {
            getWait().until(ExpectedConditions.elementToBeClickable(editIcons.get(0)));
            editIcons.get(0).click();
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("TimerecordDetails")));
        } else {
            System.out.println("No element to click.");
        }
    }

    public boolean cannotSelectDate(String datePicker, Map<String, String> selections) {
        WebElement selectedPicker = commonDate(datePicker);

        if (selectedPicker == null) {
            System.out.println("Could not find date picker \"" + datePicker + "\".");
            return false;
        }
        WebElement pickerMonthYear = null;

        try {
            // added in this loop because sometimes the datepicker was not being displayed (eg. C2698 in RowColours.feature)
            long waitLength = 3000; // Wait up to 3 seconds, but maybe this value could be set somewhere else.
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < waitLength) {
                getWait().until(ExpectedConditions.visibilityOf(selectedPicker)).click();
                Thread.sleep(200);
                BaseUtil.pageLoaded();
                List<WebElement> pMY = getDriver().findElements(By.xpath("//div[@class='datepicker-days']/descendant::th[@class='datepicker-switch']"));
                for (WebElement ele : pMY) {
                    if (ele.isDisplayed()) {
                        pickerMonthYear = ele;
                        break;
                    }
                }
            }

        } catch (ElementClickInterceptedException | InterruptedException e) {
            clickErrorHandle(e.toString(), selectedPicker);
        }

        BaseUtil.pageLoaded();

        String hour = selections.get("Time").replaceAll("(:\\d\\d)", "");
        WebElement pickerYear = null;
        WebElement pickerNext = null;
        WebElement pickerPrev = null;

        assert pickerMonthYear != null;
        String curMY = pickerMonthYear.getText().replaceAll("\\s+", "");
        String curMonth = curMY.replaceAll("\\d", "");
        String curYear = curMY.replaceAll("\\D", "");

        try {

            if (!selections.get("Month").equalsIgnoreCase(curMonth) || !selections.get("Year").equalsIgnoreCase(curYear)) {
                pickerMonthYear.click();
                List<WebElement> pY = getDriver().findElements(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='datepicker-switch']"));
                for (WebElement ele : pY) {
                    if (ele.isDisplayed()) {
                        pickerYear = ele;
                    }
                }
                List<WebElement> pN = getDriver().findElements(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='next']"));
                for (WebElement ele : pN) {
                    if (ele.isDisplayed()) {
                        pickerNext = ele;
                    }
                }
                List<WebElement> pP = getDriver().findElements(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='prev']"));
                for (WebElement ele : pP) {
                    if (ele.isDisplayed()) {
                        pickerPrev = ele;
                    }
                }
                assert pickerYear != null;

                if (!pickerYear.getText().equalsIgnoreCase(selections.get("Year"))) {
                    assert pickerPrev != null;
                    assert pickerNext != null;
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
                List<WebElement> pM = getDriver().findElements(By.xpath(
                        "//div[@class='datepicker-months']/descendant::span[contains(@class, 'month') " +
                                "and text()='" + selections.get("Month").substring(0, 3) + "']"));
                for (WebElement ele : pM) {
                    if (ele.isDisplayed()) {
                        if (ele.getAttribute("class").contains("disabled")) {
                            return true;
                        }
                        ele.click();
                        break;
                    }
                }

            }
            List<WebElement> pD = getDriver().findElements(By.xpath("//div[@class='datepicker-days']" +
                    "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                    "not(contains(@class, 'old'))][text()='" + selections.get("Day") + "']"));
            for (WebElement ele : pD) {
                if (ele.getAttribute("class").contains("disabled")) {
                    return true;
                }
                if (ele.isDisplayed()) {
                    ele.click();
                }
            }
            List<WebElement> pH = getDriver().findElements(By.xpath(
                    "//div[@class='datepicker-hours']/descendant::legend[normalize-space()='" + selections.get("AM / PM") + "']/following-sibling::span[normalize-space()='" + hour + "']"));
            for (WebElement ele : pH) {
                if (ele.isDisplayed()) {
                    if (ele.getAttribute("class").contains("disabled")) {
                        return true;
                    }
                    ele.click();
                    break;
                }
            }
            List<WebElement> pMi = getDriver().findElements(By.xpath(
                    "//div[@class='datepicker-minutes']/descendant::legend[normalize-space()='" + selections.get("AM / PM") + "']/following-sibling::span[normalize-space()='" + selections.get("Time") + "']"));
            for (WebElement ele : pMi) {
                if (ele.isDisplayed()) {
                    if (ele.getAttribute("class").contains("disabled")) {
                        return true;
                    }
                    ele.click();
                    break;
                }
            }
        } catch (ElementNotInteractableException e) {
            return true;
        }
        return false;
    }
}
