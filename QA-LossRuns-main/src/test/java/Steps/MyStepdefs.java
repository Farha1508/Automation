package Steps;

import Base.BaseUtil;
import Pages.GridFunctions;
import Pages.KPITrackingPage;
import Pages.LossRunsHappyPathPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class MyStepdefs extends BaseUtil {
    private String currentCompany;
    private String currentService;

    // Variables to track location and movement
    private String currentLogin;

    public MyStepdefs() {
    }

    private final LossRunsHappyPathPage page = new LossRunsHappyPathPage(driver);
    private final KPITrackingPage kpitrackinpage = new KPITrackingPage(driver);
    private final GridFunctions commonGrid = new GridFunctions(driver);

    @And("^Now clicked on Sign In button$")
    public void now_clicked_on_Sign_In_button() throws Throwable {
        System.out.println("Clicks Sign In button\n");
        page.SignInButton();
        BaseUtil.pageLoaded();
    }

    @Then("^I click on WO tile$")
    public void setUpTile() throws InterruptedException, IOException {
        page.wotLink();
        BaseUtil.pageLoaded();
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    @When("I open {string} for company {string}")
    public void iOpenForCompany(String service, String company) throws InterruptedException, IOException {
        page.pickClient(service, company);
        currentCompany = company;
        currentService = service;
    }

    @Then("The page for the selected company and service will be displayed")
    public void thePageForTheSelectedCompanyAndServiceWillBeDisplayed() throws InterruptedException {
        WebElement companySelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("changecompany")));
        Select company = new Select(companySelector);
        BaseUtil.pageLoaded();
        String companyName = company.getFirstSelectedOption().getText();
        System.out.println("Selected company is: " + companyName);
        WebElement serviceSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("changeservice")));
        Select service = new Select(serviceSelector);
        String serviceName = service.getFirstSelectedOption().getText();
        System.out.println("Selected service is: " + serviceName);
        Assert.assertEquals(companyName, currentCompany, "Company on page (" + companyName + ") does not match previously selected company(" + currentCompany + ")");
        Assert.assertEquals(serviceName, currentService, "Service on page (" + serviceName + ") does not match previously selected service (" + currentService + ")");
        System.out.println("Verified current page is the " + serviceName + " page for " + companyName);
    }


    @When("I click on Reports Tab")
    public void iClickOnReportsTab() throws Throwable {
        page.reportsTab();
        BaseUtil.pageLoaded();
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    @And("I verify the {string} is changed to {string}")
    public void iVerifyTheIsChangedTo(String fieldName, String value) {
        Assert.assertTrue(commonForm.commonDropDownRead(fieldName).equals(value), value + " did not match with the value selected in the drop dowm");
    }


    @And("I verify the records table")
    public void iVerifyTheRecordsTable() throws Throwable {
        page.verifyTimeRecordsOpenTable();
    }

    @Then("I should be redirected to Reports page")
    public void iShouldBeRedirectedToReportsPage() throws Throwable {
        page.verifyReportPage();
    }


    @Then("Enter {string} Work order Id under Global search field and verify")
    public void enterWorkOrderIdUnderGlobalSearchFieldAndVerify(String woId) throws InterruptedException, IOException {
        page.GlobalSearch(woId);
        BaseUtil.pageLoaded();
    }

    @When("I Click on Add Work Order button")
    public void clickOnAddWorkOrderButton() throws InterruptedException, IOException {
        page.clickAddWOButton();
        BaseUtil.pageLoaded();
    }

    @And("Click on {string} button")
    public void clickOnButton(String bttnName) throws InterruptedException, IOException {
        page.action_buttons(bttnName);
        System.out.println("Clicked on " + bttnName + " button");
        BaseUtil.pageLoaded();
    }

    @Then("WO should be created and WO Details page should be displayed")
    public void woShouldBeCreatedAndWODetailsPageShouldBeDisplayed() throws IOException {
        page.WO_Details_Page();
    }

    @Then("Select Status as {string}")
    public void selectStatusAsSelectStatus(String option) throws IOException {
        page.status_Value(option);
    }

    @Then("Validation message is displayed")
    public void validationMessageIsDisplayed() throws IOException {
        page.message_validate();
    }

    @Then("I Click on {string} tab")
    public void iClickOnTab(String tabname) throws InterruptedException, IOException {
        page.addWO_Tabs(tabname);
        js.executeScript("window.scrollBy(0,500)");
        BaseUtil.pageLoaded();
    }

    @Then("I enter values in fields")
    public void enterValuesUnderWorkOrderEntryTab(List<Map<String, String>> table) throws IOException {
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(page.commonFieldEnter(key, value), "Could not find " + key + " field!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }


    @Then("I select values from the drop down")
    public void iSelectValuesFromTheDropDown(List<Map<String, String>> table) throws InterruptedException, IOException {
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            System.out.println("Selecting " + value + " from " + key + " drop down");
            Assert.assertTrue(page.commonDropDownSelect(key, value), "Could not find " + key + " drop down");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @Then("I enter values in text area")
    public void iEnterValuesInTextArea(List<Map<String, String>> table) throws IOException {
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(page.commonTextAreaEnter(key, value), "Could not find " + key + " text area!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @Then("Click on Add Order button")
    public void clickOnAddOrderButton() throws InterruptedException, IOException {
        page.Add_Order();
    }

    @And("Click on {string} button on Details page")
    public void clickOnButtonOnDetailsPage(String bttn) {
        BaseUtil.pageLoaded();
        System.out.println("Clicking on " + bttn + " button");
        page.attachement_bttn();

    }

    @When("I upload an attachment")
    public void iUploadAnAttachment() {
        if (!commonPage.commonButton("Add Attachments") && !commonPage.commonButton("Add Attachment")) {
            driver.findElement(By.linkText("Add Attachments")).click();
        }
        commonPage.pageLoaded();
        Set<String> handles = driver.getWindowHandles();
        if (handles.size() > 1) {
            String parentWindow = driver.getWindowHandle();

            for (String windowHandle : handles) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);
                }
            }
            WebElement element = driver.findElement(By.id("UploadFile1"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
            element.sendKeys(attachLocation);
            driver.findElement(By.id("btnSave")).click();


            driver.switchTo().window(parentWindow);
            wait.until(ExpectedConditions.numberOfWindowsToBe(1));
        } else if (driver.findElements(By.id("addAttachment")).size() >= 1) {
            driver.findElement(By.id("attach_file")).sendKeys(attachLocation);
            driver.findElement(By.id("attachment_submit")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addAttachment")));
        } else {
            driver.findElement(By.id("filename")).sendKeys(attachLocation);
        }
        commonPage.pageLoaded();
    }

    @When("I cancel an attachment")
    public void iCancelAnAttachment() {
        if (!commonPage.commonButton("Add Attachments") && !commonPage.commonButton("Add Attachment")) {
            driver.findElement(By.linkText("Add Attachments")).click();
        }
        commonPage.pageLoaded();
        Set<String> handles = driver.getWindowHandles();
        if (handles.size() > 1) {
            String parentWindow = driver.getWindowHandle();

            for (String windowHandle : handles) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);
                }
            }
            WebElement element = driver.findElement(By.id("UploadFile1"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
            element.sendKeys(attachLocation);
            driver.findElement(By.xpath("//input[@value='Cancel']//parent::div[@class='bot']")).click(); //It will click on cancel button


            driver.switchTo().window(parentWindow);
            wait.until(ExpectedConditions.numberOfWindowsToBe(1));
        } else if (driver.findElements(By.id("addAttachment")).size() >= 1) {
            driver.findElement(By.id("attach_file")).sendKeys(attachLocation);
            driver.findElement(By.id("attachment_close")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addAttachment")));
        } else {
            driver.findElement(By.id("filename")).sendKeys(attachLocation);
        }
        commonPage.pageLoaded();
    }

    @Then("The file will be displayed in the Attachments grid")
    public void theFileWillBeDisplayedInTheAttachmentsGrid() {
        try {
            driver.findElement(By.linkText("Attachments")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Attachments")));
        }
        /* Once a file is successfully uploaded , than to focus on the uploaded file under Attachment tab , Scroll is being
         used belo  */
        js.executeScript("window.scrollBy(0,500)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(attachName))).click();
        System.out.println("File " + attachName + " found in Attachment grid");

        Set<String> handles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();

        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }

        String download = driver.getCurrentUrl();

        if (download.contains(attachName)) {
            System.out.println("Attachment URL verified as " + download);
        }

        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format((new Date()));
        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + "Loss Run opened attachment" + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + "Loss Run opened attachment" + dateString + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.switchTo().window(parentWindow);

    }

    @Then("The file will not be displayed in the Attachments grid")
    public void theFileWillNotBeDisplayedInTheAttachmentsGrid() {
        try {
            if (driver.getCurrentUrl().contains("#attachments")) { //After delete the page is not reloaded and it remains on Attachments tab only , so added a IF statement
                System.out.println("Attachements tab is already selected");
            } else
                driver.findElement(By.linkText("Attachments")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Attachments")));
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='No data available in table']")));
    }

    @Then("I click on {string} button")
    public void iClickOnRushButton(String bttn) throws IOException {
        page.Rush_action_buttons(bttn);
    }

    @Then("^Now switch to new tab opened$")
    public void now_switch_to_new_tab_opened() throws Throwable {
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
        BaseUtil.pageLoaded();
    }

    @Then("^Verify the Error message if no file is uploaded$")
    public void verifyTheErrorMessageIfNoFileIsUploaded() throws Throwable {
        page.verifyErrorMessage();
    }

    @Then("^Select a document to upload$")
    public void select_a_doc_to_upload() throws Throwable {
        page.FileUpload("sample");
        BaseUtil.pageLoaded();
    }

    @Then("^Switch back to previous tab$")
    public void switch_back_to_previous_tab() throws Throwable {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.navigate().refresh();
    }

    @Then("^Verify the Attachment file$")
    public void verify_the_Attachment_file() throws Throwable {
        page.verifyAttachment("Image1");
    }

    @Then("^Delete the Attachment file$")
    public void delete_the_Attachment_file() throws Throwable {
        page.deleteAttachment();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Attachments"))));
    }

    @And("I cannot delete the attachment")
    public void iCannotDeleteTheAttachment() {
        System.out.println("Checking that the delete action is unavailable.");
        Assert.assertTrue(driver.findElements(By.id("attachment_delete")).isEmpty());
    }


    @When("I delete the attachment")
    public void iDeleteTheAttachment() throws InterruptedException {
        System.out.println("Checking that the delete action is available.");
        int numberOfAttachments = driver.findElements(By.id("attachment_delete")).size();
        while (numberOfAttachments > 0) {
            System.out.println("Deleting file.");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("attachment_delete")));
            driver.findElement(By.id("attachment_delete")).click();
            BaseUtil.pageLoaded();
            commonForm.commonButtonClick("confirm");
            BaseUtil.pageLoaded();
            // After confirming a delete, the user is redirected to the Details tab. Wait for a Details tab element to be visible before continuing.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pc_Comments")));//Nothing special about this element. Just unique to Detail tab.
            driver.findElement(By.linkText("Attachments")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("th-LinkWOAttachments")));
            numberOfAttachments = driver.findElements(By.id("attachment_delete")).size();
        }
        Assert.assertTrue(driver.findElements(By.id("attachment_delete")).isEmpty());
    }

    @Then("^Select multiple document to upload$")
    public void selectMultipleDocToUploadAndClickOnSaveButton() throws InterruptedException, IOException {
        page.multipleFileUpload("Image2", "Image3");
    }

    @Then("^Verify the multiple Attachment file$")
    public void verify_the_multiple_Attachment_file() throws Throwable {
        page.verifyMultipleAttachments("Image2", "Image3");
        BaseUtil.pageLoaded();
    }


    @Then("validate patra logo on the homepage")
    public void validatePatraLogoOnTheHomepage() throws IOException {
        String patra_logo = page.verify_logo();
        Assert.assertEquals(patra_logo, "Logo", "Company Logo on homepage is not displayed");
        System.out.println("Verified Patra Logo on the Homepage");

    }

    @When("I Click on Patra logo")
    public void iClickOnPatraLogo() throws IOException, InterruptedException {
        page.click_logo();
        BaseUtil.pageLoaded();
    }

    @And("Verify the following columns are present")
    public void verifyTheFollowingColumnsArePresent(List<String> table) {
        //Need atomic boolean to work with Java lambda expressions.
        AtomicBoolean elementExists = new AtomicBoolean(false);
        table.forEach(value -> {
            System.out.println("Checking the existance of: " + value);
            if (page.woDetailHeaderFind(currentTab, value)) {
                elementExists.set(true);
                System.out.println(value + " header verified on grid");
            } else {
                elementExists.set(!driver.findElements(By.linkText(value)).isEmpty());
            }
            Assert.assertTrue(elementExists.get(), "Could not find " + value + " field!");
        });
    }

    @Then("Validate the below fields are displayed")
    public void validateTheBelowFieldsAreDisplayedUnderTab(List<String> table) {
        //Need atomic boolean to work with Java lambda expressions.
        AtomicBoolean elementExists = new AtomicBoolean(false);
        table.forEach(value -> {
            if (page.woDetailFieldsFind(currentTab, value)) {
                elementExists.set(true);
                System.out.println(value + " header verified on grid");
            } else {
                elementExists.set(!driver.findElements(By.linkText(value)).isEmpty());
            }
            Assert.assertTrue(elementExists.get(), "Could not find " + value + " field!");
        });
    }

    @Then("I Click on {string} Checkbox")
    public void iClickOnCheckbox(String checkboxname) {
        page.commonCheckBox(checkboxname);
    }

    @And("Verify Status field is changed to {string}")
    public void verifyStatusFieldIsChangedToReportsReviewed(String dropdwn) {
        WebElement status = driver.findElement(By.id("StatusID"));
        Select se = new Select(status);
        Assert.assertEquals(se.getFirstSelectedOption().getText(), dropdwn);
    }

    @Then("Add WO page should be closed and homepage should be displayed")
    public void addWOPageShouldBeClosedAndHomepageShouldBeDisplayed() {
        page.home_page();
    }

    @Then("Reload the page")
    public void reloadThePage() throws InterruptedException {
        driver.navigate().refresh();
        BaseUtil.pageLoaded();
    }

    @And("Verify the error message {string} is displayed")
    public void verifyTheErrorMessageIfMandatoryFieldsAreEmpty(String message) {
        page.wo_error_Message(message);
    }

    @Then("Click on delete icon")
    public void clickOnDeleteIcon() throws InterruptedException {
        page.deleteOrder(currentTab);
        BaseUtil.pageLoaded();

    }

    @And("I get the new work order number from the confirmation modal")
    public void iGetTheNewWorkOrderNumberFromTheConfirmationModal() {
        valueStore.put("workOrder", kpitrackinpage.getWonumber());
    }

    @When("I search the stored work order number")
    public void iSearchTheStoredWorkOrderNumber() throws InterruptedException {
        System.out.println("Searching for work order: " + valueStore.get("workOrder"));
        String currentRow1 = commonGrid.gridEntry("row 1", "Work Order #").getText();
        commonGrid.gridHeaderField("Work Order #", valueStore.get("workOrder"));
        commonGrid.waitForFilter(currentRow1);
        BaseUtil.pageLoaded();
    }

    @When("I click on the top work order link")
    public void iClickOnTheTopWorkOrderLink() {
        commonGrid.clickTopWorkOrder(currentTab);
        BaseUtil.pageLoaded();
    }

    @And("I Verified the Work order back ground {string} color")
    public void iVerifiedTheWorkOrderBackGroundColor(String scenarioName) throws Exception {
        page.verifyBackGroundColor(scenarioName);
    }

    @And("I create a random string")
    public void iCreateRandom() {
        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890";
        String s = "";
        for (int i = 0; i < 8; i++) {
            s = s + alphabet.charAt(r.nextInt(alphabet.length()));
        }
        System.out.println(s);
        valueStore.put("randomCharacters", s);
    }


    /*Random click to enable action buttons when any input is given to any field*/
    @Then("I click outside the field")
    public void iClickOutsideTheField() {
        driver.findElement(By.xpath("//label[contains(text(),'WO #:')]")).click();
    }

    @And("I check the {string} fields input is not saved")
    public void iCheckTheFieldsInputIsNotSaved(String fieldName) {
        WebElement field = driver.findElement(By.xpath("//label[normalize-space(text())='" + fieldName + "']/following-sibling::input"));
        Assert.assertFalse(field.getText().equals(valueStore.get(fieldName)), "");
    }

    @And("I click {string} checkbox")
    public void iClickCheckbox(String checkBox) {
        commonPage.pageLoaded();
        System.out.println("Clicking " + checkBox + " check box");
        Assert.assertTrue(commonPage.commonCheckBox(checkBox), checkBox + " checkbox could not be found!");
    }

    @Then("I click on {string} checkbox")
    public void iClickCheckboxOnPage(String fieldName) {
        commonPage.pageLoaded();
        System.out.println("Clicking " + fieldName + " check box");
        Assert.assertTrue(lossrunspage.tableCheckbox(currentTab, fieldName), fieldName + " checkbox could not be found!");

    }


    @And("I verify the following checkboxes in the table")
    public void iVerifyTheFollowingCheckboxesInTheTable(Map<String, String> table) {
        commonPage.pageLoaded();
        table.forEach((ckbx, state) -> {
            switch (state) {
                case "checked":
                    Assert.assertTrue(lossrunspage.verifyTableCheckbox(currentTab, ckbx), "should be checked but is not");
                    break;
                case "unchecked":
                    Assert.assertFalse(lossrunspage.verifyTableCheckbox(currentTab, ckbx), "should not be checked but is");
                    break;
                default:
                    System.out.println("status was not in the switch statement. Must be 'checked' or 'unchecked'");
                    Assert.fail("invalid switch value provided");
            }
        });
    }
}

