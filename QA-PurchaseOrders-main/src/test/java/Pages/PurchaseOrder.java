package Pages;

import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseOrder {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private static String passingstringvalue = "";
    private final WebDriverWait wait;


    public PurchaseOrder(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(this.driver, 15);


    }


    @FindBy(xpath = "//*[@id=\"index-root\"]/span[normalize-space() = 'Purchase Order']")
    private WebElement pageName;
    @FindBy(id = "addPORequest")
    private WebElement addRequestBtn;

    // Purchase Officer role only elements
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add Quote')]")
    private WebElement addQuoteBtn;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/div/ul/li[3]/a")
    private WebElement clickOnByPurchaseOfficer;

    @FindBy(how = How.XPATH, using = "//input[@id='quote_date']")
    private WebElement quoDate;
    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[8]/div[1]/table[1]/tbody[1]/tr[4]/td[5]")
    private WebElement quotedateselection;
    @FindBy(how = How.XPATH, using = "//form[@id='Add_Quotes']/div[2]/div/div[2]/input")
    private WebElement quoTitle;
    @FindBy(how = How.XPATH, using = "//input[@id='quote_vendor']")
    private WebElement quoVendor;
    @FindBy(how = How.XPATH, using = "//input[@id='quotedprice0']")
    private WebElement quoPrice;
    @FindBy(how = How.XPATH, using = "//input[@id='total_price0']")
    private WebElement totPrice;
    @FindBy(how = How.XPATH, using = "//input[@id='request_title']")
    private WebElement woTitle;
    @FindBy(how = How.XPATH, using = "//select[@id='request_location']")
    private WebElement location;
    @FindBy(how = How.XPATH, using = "//select[@id='request_Department']")
    private WebElement department;
    @FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[5]/center[1]/button[1]")
    private WebElement deleteBtn;
    @FindBy(how = How.XPATH, using = "//button[@id='quotes_approve']")
    private WebElement approveBtn;
    @FindBy(how = How.XPATH, using = "//button[@id='quotes_send_back']")
    private WebElement sendbackBtn;
    @FindBy(how = How.XPATH, using = "//button[@id='quotes_reject']")
    private WebElement rejectQuoteBtn;
    @FindBy(how = How.XPATH, using = "//button[@id='quotes_submit']")
    private WebElement submitBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td[4]")
    private WebElement gridRequestsStatus;
    @FindBy(how = How.XPATH, using = "//table[@id='dtApproval']/tbody/tr/td/input")
    private WebElement gridPendingApprovalCheckBox;
    @FindBy(how = How.ID, using = "btnApprove")
    private WebElement gridApproveBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"btnApprove\"][2]")
    private WebElement gridSendBackBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"btnApprove\"][3]")
    private WebElement gridRejectBtn;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='By Initiator']")
    private WebElement clickOnByInitiator;

    public boolean onCorrectPage() {
        try {
            return pageName.isDisplayed();
        } catch (NoSuchElementException ignored) {

        }
        return false;
    }

    public void addTableEntry(String tableName, Map<String, String> entries) {
        WebElement table = driver.findElement(By.xpath("//div[normalize-space()=\"" + tableName + "\"]/ancestor::div[@class=\"table-title\"]/following-sibling::table"));
        int rows = table.findElements(By.xpath("tbody/tr")).size();
        System.out.println("Size of rows: " + rows);
        driver.findElement(By.xpath("//*[@id=\"WorkOrderDetails\"]/div[2]/div/div/div[2]/button")).click();
        BaseUtil.pageLoaded();
        entries.forEach((key, value) -> {
            String header = generateXPATH(table.findElement(By.xpath("thead/tr/th[normalize-space()=\"" + key + "\"]")), "");
            Pattern hdrPat = Pattern.compile("(?<=th\\[)(\\d+)(?=\\])");
            Matcher hdrM = hdrPat.matcher(header);
            while (hdrM.find()) {
                header = hdrM.group();
            }
            System.out.println("Entering \"" + value + "\" into " + key + " column of " + tableName + " table");
            table.findElement(By.xpath("tbody/tr[" + (rows + 1) + "]/td[" + header + "]/input")).sendKeys(value);

        });
        table.findElement(By.xpath("tbody/tr[1]/td[4]/a[@class=\"add\"]")).click();


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

    // To add quotes by purchase officer
    public void clickAddQuote() {
        try {
            addQuoteBtn.click();
            clickOnByPurchaseOfficer.click();
        } catch (Exception e) {
        }
    }

    // To add quotes by Initiator
    public boolean clickAddQuoteByInitiator() {
if (addQuoteBtn.isDisplayed() && addQuoteBtn.isEnabled()) {
            try {
                js.executeScript("arguments[0].click()", addQuoteBtn);
            } catch (ElementClickInterceptedException e) {
                BaseUtil.commonForm.clickErrorHandle(e.toString(), addQuoteBtn);
            }
    if (clickOnByInitiator.isDisplayed() && clickOnByInitiator.isEnabled()) {
    try {
        js.executeScript("arguments[0].click()", clickOnByInitiator);
    } catch (ElementClickInterceptedException e) {
        BaseUtil.commonForm.clickErrorHandle(e.toString(), clickOnByInitiator);
    }}
            return true;
        }
        return false;
    }

    public void verifyemptyfields() {

        String title = woTitle.getAttribute("value");
        String loc = location.getAttribute("value");
        String dep = department.getAttribute("value");

        boolean condition = title.isEmpty() && loc.isEmpty() && dep.isEmpty();
        Assert.assertTrue(condition, "Input fields are not empty");
        System.out.println("Input fields are empty");
    }

    public void verifyDeleteButton() {
        Boolean condition = deleteBtn.isEnabled();
        Assert.assertTrue(condition, "delete box disabled");
        System.out.println("Good delete box enabled");
    }


    public void enterQuoteDate() {
        wait.until(ExpectedConditions.visibilityOf(quoDate)).click();
        quotedateselection.click();
    }

    public void enterQuoteDetails(DataTable dataTable) {
        for (Map<Object, Object> data : dataTable.asMaps(String.class, String.class)) {

            quoTitle.sendKeys((String) data.get("QuoteTitle"));
            quoVendor.sendKeys((String) data.get("QuoteVendor"));
            quoPrice.sendKeys((String) data.get("QuotedPrice"));
        }
    }

    public void clickTotalPriceBox() {
        totPrice.click();
    }

    public boolean verifyValidationmessage(String validation_message) {
        try {
            return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + validation_message + "')]"))).isDisplayed();
        } catch (TimeoutException ignored) {
        }
        return false;
    }
    public boolean RequestsTabStatusCheck(String status) {
        try {
        String gridStatus = gridRequestsStatus.getText().toLowerCase();
    return status.toLowerCase().equals(gridStatus);}
catch (Exception e){

}
        return false;
    }
    // Approve through Pending Approval grid
    public boolean ChangeStatusFromGrid(String status){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(gridPendingApprovalCheckBox));
        try {
            gridPendingApprovalCheckBox.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            gridPendingApprovalCheckBox.click();
        }

        System.out.println("Changing status of WO# " + passingstringvalue + " to " + status.toUpperCase());

        switch (status.toLowerCase()) {
            case "approve":
                if (gridApproveBtn.isDisplayed() && gridApproveBtn.isEnabled()) {
                    try {
                        js.executeScript("arguments[0].click()", gridApproveBtn);
                    } catch (ElementClickInterceptedException e) {
                        BaseUtil.commonForm.clickErrorHandle(e.toString(), gridApproveBtn);
                    }
                    return true;
                }
                break;
            case "send back":
                if (gridSendBackBtn.isDisplayed() && gridSendBackBtn.isEnabled()) {
                    try {
                        js.executeScript("arguments[0].click()", gridSendBackBtn);
                    } catch (ElementClickInterceptedException e) {
                        BaseUtil.commonForm.clickErrorHandle(e.toString(), gridSendBackBtn);
                    }
                    return true;
                }
                break;
            case "reject":
                if (gridRejectBtn.isDisplayed() && gridRejectBtn.isEnabled()) {
                    try {
                        js.executeScript("arguments[0].click()", gridRejectBtn);
                    } catch (ElementClickInterceptedException e) {
                        BaseUtil.commonForm.clickErrorHandle(e.toString(), gridRejectBtn);
                    }
                    return true;
                }
                break;
        }
        return false;
    }


    @FindBy(how = How.XPATH, using = "//*[@id=\"dtquotes\"]/tbody/tr[1]/td[5]/center")
    private WebElement ApprovalStatus1;

    //To verify quote1 approval status
    public boolean verifyQuote1Status() {
        try {
wait.until(ExpectedConditions.visibilityOf(ApprovalStatus1));
            String approvalStatus1 = ApprovalStatus1.getText();
            System.out.println("Current quote Approval status is showing as:" + approvalStatus1);
        } catch (Exception e) {
        }
        return false;
    }

    @FindBy(how = How.XPATH, using = "//tbody/tr[2]/td[5]/center")
    private WebElement ApprovalStatus2;

    //To verify quote1 approval status
    public boolean verifyQuote2Status() {
        try {
            wait.until(ExpectedConditions.visibilityOf(ApprovalStatus1));
            String approvalStatus2 = ApprovalStatus2.getText();
            System.out.println("Approval status is showing as :" + approvalStatus2);
        } catch (Exception e) {
        }
        return false;
    }
    public boolean verifyApproveButtonDisabled(){
       try {
           return approveBtn.isEnabled();
       }
       catch (Exception e)
       {
       }
       return false;
    }
    public boolean verifySendBackButtonDisabled(){
        try {
            return sendbackBtn.isEnabled();
        }
        catch (Exception e)
        {
        }
        return false;
    }
    public boolean verifyRejectButtonDisabled(){
        try {
            return rejectQuoteBtn.isEnabled();
        }
        catch (Exception e)
        {
        }
        return false;
    }
    public boolean verifySubmitButtonDisabled(){
        try {
            return submitBtn.isEnabled();
        }
        catch (Exception e)
        {
        }
        return false;
    }
    @FindBy(how = How.ID, using = "po_VendorRefNo")
    private WebElement vendorRefNo;
    @FindBy(how = How.ID, using = "po_vendorAddress")
    private WebElement vendorAddressTextBox;
    @FindBy(how = How.ID, using = "item_partno0")
    private WebElement partNoTextBox;

    //Entering Purchase Order required details
    public void enterPurchaseOrderDetails(DataTable dataTable) {
        for (Map<Object, Object> data : dataTable.asMaps(String.class, String.class)) {

            vendorRefNo.sendKeys((String) data.get("VendorReferenceNumber"));
            vendorAddressTextBox.sendKeys((String) data.get("VendorAddress"));
            partNoTextBox.sendKeys((String) data.get("PartNo"));
        }
    }
    @FindBy(how = How.ID, using = "WoNumber1")
    private WebElement workOrderNumber;

    //To get the created purchase order number.
    public void getCreatedPONumber() {
        String PONumber = workOrderNumber.getText();
        System.out.println("Created PO # is  :" + PONumber);
    }
}

