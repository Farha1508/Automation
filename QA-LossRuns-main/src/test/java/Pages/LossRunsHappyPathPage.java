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

import java.util.HashMap;
import java.util.List;

public class LossRunsHappyPathPage extends BaseUtil {
    private final WebDriver driver;
    private final HashMap<String, String> gridMap = new HashMap<>();

    public LossRunsHappyPathPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
        js = (JavascriptExecutor) driver;
        initializeMaps();
        PageFactory.initElements(driver, this);
    }

    // Login Page
    @FindBy(how = How.ID, using = "email")
    public WebElement txtEmail;
    @FindBy(how = How.ID, using = "password")
    public WebElement txtPassword;
    @FindBy(how = How.ID, using = "submit")
    public WebElement btnSignIn;


    // CLick on Sign-in Button
    public void SignInButton() {
        btnSignIn.sendKeys(Keys.ENTER);
    }

    // Click on Setup tile
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Work Order Tracking')]")
    public WebElement link;

    public void wotLink() {
        link.click();
    }

    @FindBy(how = How.ID, using = "companyStartId")
    public WebElement selCompany;

    public void pickClient(String Service, String Company) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(selCompany));
        Select se = new Select(selCompany);
        se.selectByVisibleText(Company);

        /*Sometime it gives me 'StaleElementReferenceException: stale element reference: element is not attached to the page document'
        and the scripts fails , so to handle this  If the DOM changes between the find and click, it will try again. The idea is that
        if it failed and I try again immediately the second attempt will succeed.*/
        int attempts = 0;
        while (attempts < 2) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText(Company + " - " + Service))).click();
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    /*..........Verify user is on Homepage...................*/
    public void home_page() {
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("#home"));
    }


    /**
     * Verify Patra logo
     **/
    @FindBy(how = How.XPATH, using = "//*[@id=\"index\"]/img")
    public WebElement logo;

    public String verify_logo() {
        String img = logo.getAttribute("alt");
        return img;
    }

    public boolean click_logo() {
        logo.click();
        return true;
    }

    /* ------Size of table and pagination */
    @FindBy(how = How.ID, using = "dtprocessing")
    public WebElement pagination;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtprocessing_length\"]/label/select")
    public WebElement pValue;

    public void pagin(String Pagevalue) {
        pValue.sendKeys(Pagevalue);
    }

    public void pagin1() {
        List<WebElement> TotalRowsList = pagination.findElements(By.tagName("tr"));
        System.out.println("Total no of rows in table:" + (TotalRowsList.size() - 1));
    }

    /*-------------Successful logout from application-----------*/
    @FindBy(how = How.XPATH, using = "//img[@class= \"dropdown-toggle dropdown-togglePoHghtStyl\"]")
    public static WebElement usericon;

    @FindBy(how = How.ID, using = "logoutTimer")
    public static WebElement logoutbtn;

    @FindBy(how = How.XPATH, using = "//div[1][@class=\"alert alert-success\"]")
    public static WebElement logoutMsg;


    public void logout() {
        usericon.click();
        logoutbtn.click();
    }

    public void verifylogoutMsg() {
        String SuccessMsg = logoutMsg.getText();
        System.out.println(logoutMsg);
        Assert.assertEquals(SuccessMsg, "You are now logged out");
    }

    /*-------------Reset button-------------------*/
    @FindBy(how = How.XPATH, using = "//div[@class='btn btn-sm red btn-outline search-clear']")
    public static WebElement ResetButton;

    public void resetButton() {
        ResetButton.click();
    }

    /*----------------Top Level Navigation - Reports page------------------*/
    public static WebElement reports;

    public void reportsTab() {
        reports.click();
    }

    public void verifyReportPage() {
        String pagetitle = driver.getTitle();
        Assert.assertEquals(pagetitle, "Loss Runs - Report Manager");
    }

    /*-------------Verify Records Open tale--------------*/
    @FindBy(how = How.XPATH, using = "//table[@id=\"dtTimeRecordOpen\"]/tbody/tr/td[5]/div")
    public static WebElement timeRecordsOpenTable;

    public void verifyTimeRecordsOpenTable() {
        String data = timeRecordsOpenTable.getText();
        Assert.assertEquals(data, "Count");
    }

    //Global Search//
    @FindBy(how = How.ID, using = "typeaheadValue")
    public static WebElement globalSearch;
    @FindBy(how = How.XPATH, using = "//strong[@class='tt-highlight']")
    public static List<WebElement> suggestedResult;
    @FindBy(how = How.XPATH, using = "//div[@class='tt-dataset tt-dataset-Lossruns']/p")
    public static WebElement noRecordExist;

    public void GlobalSearch(String option) throws InterruptedException {
        globalSearch.sendKeys(option);
        Thread.sleep(2000);
        if (suggestedResult.size() != 0) {
            String value = suggestedResult.get(0).getText();
            Assert.assertTrue(value.contains(option));
            suggestedResult.get(0).click();
        } else {
            String value1 = noRecordExist.getText();
            Assert.assertEquals(value1, "Record Doesn't Exist.");
        }
    }

    /*...........Add Work Order Button.........*/
    @FindBy(how = How.ID, using = "addWOrkOrder")
    public WebElement clickAddWO;

    public void clickAddWOButton() {
        clickAddWO.click();
    }

    @FindBy(how = How.ID, using = "btnOrderRemindedRow")
    public WebElement clickAddOrder;

    public void clickAddOrderButton() {
        clickAddOrder.click();
    }

    /*...........Add Work Order page action Button.........*/
    public void action_buttons(String name) {
        WebElement actionbttn = driver.findElement(By.xpath("//*[contains(@id,'work')][.//text()[normalize-space()='" + name + "']]"));
        actionbttn.click();
    }

    /*...........Action button on  Work Order details page .........*/
    public void attachement_bttn() {
        WebElement actionbttn = driver.findElement(By.id("Attachment_event"));
        actionbttn.click();
    }

    public void Rush_action_buttons(String name) {
        WebElement rush_bttn = driver.findElement(By.id("mRush"));
        rush_bttn.click();
    }

    /*..........Verify user is on WO Details page...................*/
    @FindBy(how = How.ID, using = "work_entry_cancel")
    public static WebElement CancelchangesBttn;

    public void WO_Details_Page() {
        String text = CancelchangesBttn.getText();
        Assert.assertEquals(text, "Cancel Changes");
    }

    /*...........Add Work order pop up page..........*/
    @FindBy(how = How.ID, using = "StatusID")
    public WebElement statusOption;
    @FindBy(how = How.ID, using = "status_error")
    public WebElement validation_Message;

    public void status_Value(String option) {
        Select se = new Select(statusOption);
        se.selectByVisibleText(option);
    }

    public void message_validate() {
        String text = validation_Message.getText();
        Assert.assertEquals(text, "Select Status");
    }

    public void wo_error_Message(String msg) {
        WebElement ele = driver.findElement(By.xpath("//div[@class ='alertBlock']/div[2]"));
        Assert.assertTrue(ele.getText().contains(msg));
    }

    /*Work Order Entry tab fields*/
    public void addWO_Tabs(String tabName) {
        WebElement el = driver.findElement(By.xpath("//a[.//text()[normalize-space()='" + tabName + "']]"));
        el.click();
    }

    /*-----------------Wo Details Page tabs--------------------*/
    public void details_Page_Tabs(String tabname) {
        WebElement tab = driver.findElement(By.xpath("//a[.//text()[normalize-space()='" + tabname + "']]"));
        tab.click();
        pageScroll(tab);
    }

    public void pageScroll(WebElement Element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        js.executeScript("window.scrollBy(0,4000)");
    }

    public void Add_Order() throws InterruptedException {
        WebElement ele = driver.findElement(By.id("btnOrderRemindedRow"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        pageScroll(ele);
        js.executeScript("arguments[0].click()", ele);
        Thread.sleep(3000);
    }

    public boolean carrierName(String fieldName, String selection) {
        System.out.println("Selecting " + selection + "option from " + fieldName + "drop-down field");
        String tabFix = currentTab.toLowerCase().replaceAll("\\s+", "");
        String fieldFix = fieldName.toLowerCase().replaceAll("\\s+", "");
        try {
            WebElement e2 = driver.findElement(By.xpath("//*[@id= '" + gridMap.get(tabFix) + "']//select[contains(@id , '" + gridMap.get(fieldFix) + "')]"));
            Select list = new Select(e2);
            list.selectByVisibleText(selection);
            return true;

        } catch (NoSuchElementException e) {
            System.out.println("No data available in table");
            return false;
        }

    }

    public boolean yearDetailInput(String headerName, String input) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath("" +
                "//*[@placeholder ='" + headerName + "']"));


        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        }

        assert selectedHeader != null;
        try {
            selectedHeader.click();

        } catch (ElementClickInterceptedException e) {
            return false;
        }

        selectedHeader.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedHeader.sendKeys(input);
        BaseUtil.pageLoaded();
        selectedHeader.sendKeys(Keys.ENTER);
        selectedHeader.click();
        return true;
    }

    public boolean tableCheckbox(String tab, String chbxName) {
        try {
            String tabName = tab.replaceAll("\\s", "").toLowerCase();
            String fieldname = chbxName.replaceAll("\\s", "").toLowerCase();
            WebElement chekBoxBttn = driver.findElement(By.xpath("//table[@id='" + gridMap.get(tabName) + "']//following::input[@type='checkbox' and contains(@id ,'" + gridMap.get(fieldname) + "')]"));
            chekBoxBttn.click();
        } catch (ElementClickInterceptedException e) {
        }
        return true;
    }

    public boolean verifyTableCheckbox(String tab, String chbxName) {
        try {
            String tabName = tab.replaceAll("\\s", "").toLowerCase();
            String fieldname = chbxName.replaceAll("\\s", "").toLowerCase();
            WebElement chekBoxBttn = driver.findElement(By.xpath("//table[@id='" + gridMap.get(tabName) + "']//following::input[@type='checkbox' and contains(@id ,'" + gridMap.get(fieldname) + "')]"));
            return chekBoxBttn.isSelected();
        } catch (ElementClickInterceptedException e) {
            return false;
        }
    }

    public String verifyRow() {
        String tabFix = currentTab.toLowerCase().replaceAll("\\s+", "");
        String noRecordMessage = driver.findElement(By.xpath("//*[@id= '" + gridMap.get(tabFix) + "']/tbody/tr/td")).getText();
        return noRecordMessage;
    }

    public boolean deleteOrder(String tab) {
        try {
            String tabName = tab.replaceAll("\\s", "").toLowerCase();
            js.executeScript("window.scrollBy(0,500)");
            List<WebElement> deleteIcon = driver.findElements(By.xpath("//table[@id='" + gridMap.get(tabName) + "']//following::span[contains(@data-name,'delete')]"));
            int noOfIcon = deleteIcon.size();
            deleteIcon.get(noOfIcon - 1).click();
            wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
            confirmButton.click();
        } catch (ElementClickInterceptedException e) {
        }
        return true;
    }

    public boolean commonCheckBox(String checkBox) {
        List<WebElement> checkBoxes1 = driver.findElements(By.xpath(
                "//label[text()[normalize-space()=\"" + checkBox + "\"]]/span[@class=\"checkmark\"]"));
        List<WebElement> checkBoxes2 = driver.findElements(By.xpath(
                "//label[text()[normalize-space()=\"" + checkBox + "\"]]/following-sibling::*//span[@class=\"checkmark\"]"));

        if (checkBoxes1.size() > 0) {
            for (WebElement chkbx : checkBoxes1) {
                if (chkbx.isDisplayed() && chkbx.isEnabled()) {
                    try {
                        chkbx.click();
                    } catch (ElementClickInterceptedException e) {

                    }
                    return true;
                }
            }
        } else if (checkBoxes2.size() > 0) {
            for (WebElement chkbx : checkBoxes2) {
                if (chkbx.isDisplayed() && chkbx.isEnabled()) {
                    try {
                        chkbx.click();
                    } catch (ElementClickInterceptedException e) {

                    }

                    return true;
                }
            }
        }
        return false;
    }


    public WebElement commonField(String field) {
        WebElement selectedField = null;
        List<WebElement> fields = driver.findElements(By.xpath(
                "//label[.//text()[normalize-space()=\"" + field + "\"]]/following-sibling::input"));
        List<WebElement> fields2 = driver.findElements(By.xpath("//label[translate(.,'\u00A0','') =\"" + field + "\"]/following-sibling::input"));


        if (fields.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedField == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                for (WebElement fld : fields) {
                    if (fld.isDisplayed()) {
                        selectedField = fld;
                        break;
                    }
                }
            }
        } else if (fields2.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedField == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                for (WebElement fld : fields2) {
                    if (fld.isDisplayed()) {
                        selectedField = fld;
                        break;
                    }
                }
            }
        }
        return selectedField;
    }

    public boolean commonFieldEnter(String field, String text) {
        WebElement selectedField = commonField(field);

        if (selectedField == null) {
            return false;
        }

        selectedField.click();
        selectedField.clear();
        selectedField.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedField.sendKeys(text);

        return true;
    }

    public WebElement commonDropDown(String dropDown) {
        WebElement selectedList = null;
        List<WebElement> lists = driver.findElements(By.xpath(
                "//label[.//text()[normalize-space()=\"" + dropDown + "\"]]/following-sibling::select"));

        if (lists.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedList == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement lst : lists) {
                    if (lst.isDisplayed()) {
                        selectedList = lst;
                    }
                }
            }
        }

        return selectedList;
    }

    public boolean commonDropDownSelect(String dropDown, String selection) {
        WebElement selectedList = commonDropDown(dropDown);

        if (selectedList == null) {
            return false;
        }

        try {
            Select list = new Select(selectedList);
            list.selectByVisibleText(selection);
        } catch (NoSuchElementException e) {
            return false;
        }

        return true;
    }

    public WebElement commonTextArea(String textArea) {
        WebElement selectedTextArea = null;
        List<WebElement> textAreas = driver.findElements(By.xpath(
                "//label[.//text()[normalize-space()=\"" + textArea + "\"]]/following-sibling::textarea"));

        if (textAreas.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedTextArea == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement txtA : textAreas) {
                    if (txtA.isDisplayed()) {
                        selectedTextArea = txtA;
                    }
                }
            }
        }
        return selectedTextArea;
    }

    public boolean commonTextAreaEnter(String textArea, String text) {
        WebElement selectedTextArea = commonTextArea(textArea);

        if (selectedTextArea == null) {
            return false;
        }

        selectedTextArea.click();
        selectedTextArea.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedTextArea.sendKeys(text);
        return true;
    }

    /* Upload a file */
    @FindBy(how = How.ID, using = "valSummary")
    public static WebElement errorMessage;
    @FindBy(how = How.ID, using = "UploadFile1")
    public static WebElement uploadFile1;
    @FindBy(how = How.ID, using = "UploadFile2")
    public static WebElement uploadFile2;
    @FindBy(how = How.ID, using = "btnSave2")
    public static WebElement saveFile;
    @FindBy(how = How.XPATH, using = "//div[@class= 'bot']/input[2]")
    public static WebElement CancelUpload;

    public void verifyErrorMessage() {
        String ErrorMsg = errorMessage.getText();
        System.out.println(ErrorMsg);
        Assert.assertEquals(ErrorMsg, "Errors:\n" +
                "* You must select a file to upload");
    }

    public void FileUpload(String filename) {
        uploadFile1.sendKeys(attachLocation + "/" + filename + ".png");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
    }

    public void saveFileUpload() {
        saveFile.click();
    }

    public void cancelFileUpload() {
        CancelUpload.click();
    }

    public void multipleFileUpload(String filename1, String filename2) {
        uploadFile1.sendKeys(attachLocation + "/" + filename1 + ".png");
        uploadFile2.sendKeys(attachLocation + "/" + filename2 + ".png");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
    }

    /* Navigate to Attachment tab and verify the file */
    @FindBy(how = How.ID, using = "Attachment_refresh")
    public static WebElement refreshbttn;
    @FindBy(how = How.XPATH, using = "//tr[1]/td[@class='sorting_1']/a")
    public static WebElement VerifyAttachedDoc1;
    @FindBy(how = How.XPATH, using = "//tr[2]/td[@class='sorting_1']/a")
    public static WebElement VerifyAttachedDoc2;

    public void verifyAttachment(String filename) {
        js.executeScript("window.scrollBy(0,500)");
        String DocName = VerifyAttachedDoc1.getText();
        Assert.assertTrue(DocName.contains(filename));
    }

    public void verifyMultipleAttachments(String filename1, String filename2) {
        String DocName1 = VerifyAttachedDoc1.getText();
        String DocName2 = VerifyAttachedDoc2.getText();
        Assert.assertTrue(DocName1.contains(filename1));
        Assert.assertTrue(DocName2.contains(filename2));
    }

    @FindBy(how = How.XPATH, using = "//span[@class='glyphicon glyphicon-trash']")
    public static WebElement deleteBttn;
    @FindBy(how = How.XPATH, using = "//div[@class='jconfirm-buttons']/button[1]")
    public static WebElement confirmButton;

    public void deleteAttachment() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        js.executeScript("arguments[0].click();", deleteBttn);
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();

    }


    public boolean woDetailHeaderFind(String tab, String header) {
        String tabName = tab.replaceAll("\\s", "").toLowerCase();
        try {
            WebElement headerName = driver.findElement(By.xpath("//table[@id='" + gridMap.get(tabName) + "']/descendant::th[normalize-space(text())='" + header + "']"));
            if (headerName.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean woDetailFieldsFind(String tab, String header) {
        String tabName = tab.replaceAll("\\s", "").toLowerCase();
        try {
            WebElement field = driver.findElement(By.xpath("//div[@id='" + gridMap.get(tabName + "tab") + "']/descendant::label[normalize-space(text())='" + header + "']"));
            if (field.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtprocessing\"]/tbody/tr[1]")
    public static WebElement gridFirstRow;

    public void verifyBackGroundColor(String colour) throws Exception {
        // Todo: move expected colour RGB values to a properties file for better management.
        String cssColour = gridFirstRow.getCssValue("background-color");
        System.out.println("Verifying Color in Grid row  " + cssColour);
        switch (colour) {
            case "White":
                Assert.assertEquals("rgba(249, 249, 249, 1)", cssColour, "The colour did not match.");
                break;
            case "Rush - Yellow":
                Assert.assertEquals("rgba(255, 255, 102, 1)", cssColour, "The colour did not match.");
                break;
            default:
                Assert.fail("Colour: " + colour + " was not in the switch statement. ");
        }
    }

    private void initializeMaps() {

        // Sert Details tab on details page
        gridMap.put("order/reminderdates", "tableOrderReminded");
        gridMap.put("order/reminderdatestab", "reminderdates");
        gridMap.put("yeardetails", "tableYearOrder");

        gridMap.put("policyyear", "PolicyYearID");
        gridMap.put("carrier", "CarrierID");
        gridMap.put("done", "isReminderDone");
        gridMap.put("broken", "yrDetIsBrokerOfRecord");
        gridMap.put("amresponsepending", "yrDetIsAMResponsePending");


    }
}
