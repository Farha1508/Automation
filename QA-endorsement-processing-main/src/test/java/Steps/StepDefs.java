package Steps;

import Base.BaseUtil;
import Base.KpiClass;
import Pages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class StepDefs extends BaseUtil {


    public StepDefs() {
        login = new Login(driver);
        wait = new WebDriverWait(driver, 10);

    }

    @Given("^I am on the login page$")
    public void iAmOnTheLoginPage() {
        System.out.println("Navigating to DEV login page\n");
//        driver.get("https://dev.patracorp.net");
        driver.get("http://login.dev.patracloud.net/auth");
        driver.manage().window().maximize();
    }


    @And("I open the URL {string}")
    public void iOpenTheURL(String url) {

        driver.get(url);
        commonPage.pageLoaded();
    }

    @When("I enter the email and password for the \"([^\"]*)\"$")
    public void iEnterTheEmailAndPasswordForThe(String user) throws Exception {
        currentLogin = user;

        login.EnterCredentials(user);

    }

    @When("I enter a(n) {string} email address and {string} password")
    public void iEnterEmail(String userType, String passType) throws Exception {
        // should this switch statement instead be in the enterEmail() function?
        switch (userType) {
            case "empty" -> login.enterEmail("");
            case "invalid" -> login.enterEmail(login.getLogins().getProperty("email.invaliduser")+"<current date>");
            case "valid" -> login.enterEmail(login.getLogins().getProperty("email.validuser"));
            case "incorrect" -> login.enterEmail(login.getLogins().getProperty("email.incorrectuser")); //email missing the '@' symbol.
            default -> throw new Exception("No login information available for " + userType + " please check Login list.");
        }

        // should this switch statement instead be in the enterPass() function?
        switch (passType) {
            case "empty" -> login.enterPass("");
            case "invalid" -> login.enterPass(login.getLogins().getProperty("pass.invaliduser"));
            case "valid" -> login.enterPass(login.getLogins().getProperty("pass.validuser"));
            default -> throw new Exception("No login information available for " + userType + " please check Login list.");
        }
    }

    @Then("I log out")
    public void iLogOut() {
        System.out.println("Logging out " + currentLogin.toUpperCase());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Log Out')]"))).click();
        commonPage.pageLoaded();
    }

    @And("I click the Sign In button")
    public void iClickTheSignInButton() {
        System.out.println("Clicking Sign In button\n");
        login.ClickSignIn();
    }

    @Then("I will be taken to the apps page")
    public void iWillBeTakenToTheAppsPage() throws Exception {
        login.onCorrectPage();
    }

    @When("I click on the {string} tile")
    public void iClickOnTheTile(String tileName) throws Exception {
        currentApp = tileName;
        System.out.println("Clicking on " + tileName + " tile");
        login.ClickOnTile(tileName);
        pageMap(currentApp);
        commonPage.pageLoaded();
    }

    @Then("I will be taken to the homepage for that app")
    public void iWillBeTakenToTheHomepageForThatApp() {
        commonPage.pageLoaded();
        Assert.assertTrue(commonPage.onCorrectPage(), "Homepage for " + currentApp + " not displayed");
        System.out.println("On homepage for " + currentApp);


    }

    @And("I wait for {string} seconds")
    public void iWaitForSeconds(String seconds) throws InterruptedException {
        int timeToWait = Integer.parseInt(seconds) * 1000;

        Thread.sleep(timeToWait);
    }



    @And("Return to the apps page")
    public void returnToTheAppsPage() {
        driver.findElement(By.id("index")).click();
    }



    @And("I take a screenshot")
    public void iTakeAScreenshot() {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format((new Date()));
        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + BaseUtil.scenarioName + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + BaseUtil.scenarioName + dateString + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @And("I get the {string} info from valueStore")
    public void iGetTheInfoFromValueStore(String storeItem) throws InterruptedException {
        js.executeScript("alert('" + storeItem + " in valueStore: " + valueStore.get(storeItem) + "')");
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        System.out.println(storeItem + " in valueStore: " + valueStore.get(storeItem));
    }

    @And("I clear the valueStore")
    public void iClearTheValueStore() {
        valueStore.clear();
        editedValues.clear();
    }


    @And("I find the newly created business")
    public void iFindTheNewlyCreatedBusiness() {
        String check = valueStore.get("Business/Policy Holder Name");
        WebElement result = commonPage.gridEntry("row 1", "Business Name");
        commonPage.gridHeaderEnter("Business Name", check);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(result)));
        driver.findElement(By.linkText(check)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"opportunities_datatable_ajax\"]/tbody/tr/td[3]")));

    }

    @And("I click on the {string} link")
    public void iClickOnTheLink(String linkText) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText(linkText)));
        }
        commonPage.pageLoaded();

    }

    @And("I add {string} key and {string} value to valueMap")
    public void iAddKeyAndValueToValueMap(String key, String value) {
        valueStore.put(key, value);
    }

    @And("I print out the valueStore")
    public void iPrintOutTheValueStore() {
        valueStore.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    @Then("The new activity will be displayed in the Activities tab")
    public void theNewActivityWillBeDisplayedInTheActivitiesTab() {
        commonPage.pageLoaded();
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addActivity")));
        try {
            driver.findElement(By.linkText("Activities")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Activities")));
        }

        commonPage.pageLoaded();
        Assert.assertTrue(driver.findElement(By.linkText(valueStore.get("Activity Type"))).isDisplayed(),
                "New activity " + valueStore.get("Activity Type") + " not found in Activities grid");

        System.out.println("New activity " + valueStore.get("Activity Type") + " is displayed in the Activities grid");

//        if(driver.findElement(By.linkText(valueStore.get("Activity Type"))).isDisplayed()) {
//            System.out.println("New activity " + valueStore.get("Activity Type") + " is displayed in the Activities grid");
//        } else {
//            System.err.println("New activity " + valueStore.get("Activity Type") + " not found in Activities grid");
//        }
    }

    @When("I open the newly created activity")
    public void iOpenTheNewlyCreatedActivity() {
        driver.findElement(By.linkText("Activities")).click();
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(valueStore.get("Activity Type")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_follow")));
    }

    @Then("The new follow up will be displayed in the grid")
    public void theNewFollowUpWillBeDisplayedInTheGrid() {
        driver.findElement(By.id("followUp_submit")).click();
        WebElement activity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\"" + valueStore.get("Follow Up Type") + "\"]")));
        Assert.assertEquals(activity.getText(), valueStore.get("Follow Up Type"),
                "Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");
        /*
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[1]")));
        driver.findElement(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\""+valueStore.get("Follow Up Type")+"\"]"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\""+valueStore.get("Follow Up Type")+"\"]")).isDisplayed(),
                "Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");

         */

//        if(commonPage.gridEntry("row 1", "Follow Up Type").getText().equals(valueStore.get("Follow Up Type"))) {
//            System.out.println(valueStore.get("Follow Up Type") + " follow up is displayed in Follow Ups grid");
//        } else {
//            System.err.println("Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");
//        }

    }

    @When("I upload an attachment")
    public void iUploadAnAttachment() {
        if (!commonPage.commonButton("Add Attachments") && !commonPage.commonButton("Add Attachment")) {
            driver.findElement(By.linkText("Add Attachments")).click();
        }
        commonPage.pageLoaded();
        Set<String> handles = driver.getWindowHandles();
        if (driver.findElements(By.id("addAttachment")).size() >= 1) {
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
        if (driver.findElements(By.id("addAttachment")).size() >= 1) {
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
        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + "opened attachment" + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + "opened attachment" + dateString + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.switchTo().window(parentWindow);

    }

    @Then("The file will not be displayed in the Attachments grid")
    public void theFileWillNotBeDisplayedInTheAttachmentsGrid() {
        try {
            driver.findElement(By.linkText("Attachments")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Attachments")));
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='No data available in table']")));
    }

    @And("I open the opportunity")
    public void iOpenTheOpportunity() {
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(valueStore.get("Coverage Type")))).click();

    }


    @Then("The update success message will be displayed")
    public void theUpdateSuccessMessageWillBeDisplayed() {
        wait.until(ExpectedConditions.attributeContains(By.id("oppor_succ_msg"), "style", "block"));
    }

    @And("I click {string} checkbox")
    public void iClickCheckbox(String checkBox) {
        commonPage.pageLoaded();
        System.out.println("Clicking " + checkBox + " check box");
        Assert.assertTrue(commonPage.commonCheckBox(checkBox), checkBox + " checkbox could not be found!");
    }

    @And("I get all the KPI elements")
    public void iGetAllTheKPIElements() {
        ArrayList<KpiClass> testKpi = new ArrayList<>();
        List<WebElement> kpi = driver.findElements(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]"));
        KpiClass listEntry;
        String numExtraction;
        StringBuilder stringBuilder;
        String title;
        int intValue;
        float floatValue;
        for (WebElement element : kpi) {
            title = element.findElement(By.className("desc")).getText();
            List<WebElement> values = element.findElements(By.xpath(".//descendant::div[@class=\"number\"]/span"));
            for (WebElement ele2 : values) {
                String cTitle = title.replaceAll("\\n", " ");
                StringBuilder vName;
                numExtraction = ele2.getText().replaceAll("\\s+", "");
                if (numExtraction.matches(".*\\d\\.\\d.*")) {
                    stringBuilder = new StringBuilder();
                    vName = new StringBuilder();
                    for (int i = 0; i < numExtraction.length(); i++) {
                        if (Character.isDigit(numExtraction.charAt(i)) || numExtraction.charAt(i) == '.') {
                            stringBuilder.append(numExtraction.charAt(i));
                        } else if (Character.isLetter(numExtraction.charAt(i))) {
                            vName.append(numExtraction.charAt(i));
                        }
                    }
                    if (vName.length() > 0) {
                        cTitle += " " + vName.toString();
                    }
                    floatValue = Float.parseFloat(stringBuilder.toString());
                    listEntry = new KpiClass(cTitle, floatValue);
                } else {
                    stringBuilder = new StringBuilder();
                    vName = new StringBuilder();
                    for (int i = 0; i < numExtraction.length(); i++) {
                        if (Character.isDigit(numExtraction.charAt(i))) {
                            stringBuilder.append(numExtraction.charAt(i));
                        } else if (Character.isLetter(numExtraction.charAt(i))) {
                            vName.append(numExtraction.charAt(i));
                        }
                    }
                    if (vName.length() > 0) {
                        cTitle += " " + vName.toString();
                    }
                    intValue = Integer.parseInt(stringBuilder.toString());
                    listEntry = new KpiClass(cTitle, intValue);
                }
                testKpi.add(listEntry);
            }

        }
        System.out.println("Printing testKpi array!");
        for (KpiClass kpiClass : testKpi) {
            System.out.println("KPI Title: " + kpiClass.getKpiTitle());
            if (kpiClass.getKpiValueInt() < 0) {
                System.out.println("KPI Value: " + kpiClass.getKpiValueFloat());
            } else {
                System.out.println("KPI Value: " + kpiClass.getKpiValueInt());
            }

        }
    }

    @And("I choose {string} from the list")
    public void iChooseFromTheList(String choice) {
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu inner selectpicker\"]/descendant::span[text()=\"" + choice + "\"]")).click();
    }

    @And("I get the text from the {string} field")
    public void iGetTheTextFromTheField(String field) {
        String fieldText = commonPage.commonFieldRead(field);
        System.out.println("Text currently visible in " + field + " field: " + fieldText);
        valueStore.put(field, fieldText);
    }

    @And("I return to the Apps homepage")
    public void iReturnToTheAppsHomepage() {
        driver.get("https://dev.patracorp.net/home");
        commonPage.pageLoaded();
    }

    @And("I open DBR for Company 002")
    public void iOpenDBRForCompany() {
        commonPage.pageLoaded();
        WebElement selectedList = driver.findElement(By.id("companyStartId"));
        Select list = new Select(selectedList);
        list.selectByVisibleText("Company 002");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(" Company 002 - DBR"))).click();
        commonPage.pageLoaded();
    }

    @And("I open {string} for Company 002")
    public void iOpenForCompany(String service) {
        commonPage.pageLoaded();
        WebElement selectedList = driver.findElement(By.id("companyStartId"));
        Select list = new Select(selectedList);
        list.selectByVisibleText("Company 002");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Company 002 - " + service + ""))).click();
        commonPage.pageLoaded();
    }

    @Then("I verify the number of records in the {string} grid match the {string} KPI")
    public void iVerifyTheNumberOfRecordsInTheGridMatchTheKPI(String tabName, String kpi) {
        commonPage.clickKpi(kpi);
        commonPage.pageLoaded();
        String rowNum;
        rowNum = Integer.toString(commonPage.gridRecordNumber(tabName));

        Assert.assertEquals(rowNum, valueStore.get(kpi), "Rows in grid do not match number of " + kpi + " KPI");
        System.out.println("Number of records in grid (" + rowNum + ") match the number shown in the " + kpi + " KPI (" + valueStore.get(kpi) + ")");
    }

    @Then("The log out version of the sign in page will be displayed")
    public void theLogOutVersionOfTheSignInPageWillBeDisplayed() {
        commonPage.pageLoaded();
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/logout"), "Not redirected to log out page!");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]")).getText().equals("You are now logged out"),
                "You are now logged out message not displayed!");
        System.out.println("Log out screen displayed");
    }

    @And("I fail the test")
    public void iFailTheTest() {
        Assert.fail("Failing test for testing purpose");
    }



    @And("I add the following items to the valueMap")
    public void iAddTheFollowingItemsToTheValueMap(Map<String, String> table) {
        table.forEach((key, value) -> valueStore.put(key, value));
    }

    @When("I edit the following items")
    public void iEditTheFollowingItems(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        table.forEach((key, value) -> {
            boolean foundItem = false;
            if (value.contains("<current date>")) {
                foundItem = true;
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (commonPage.commonFieldEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
            } else if (commonPage.commonDropDownSelect(key, value)) {
                foundItem = true;
                System.out.println("Selecting \"" + value + "\" from \"" + key + "\" drop down");
            } else if (commonPage.commonTextAreaEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" text area");
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
            editedValues.put(key, value);
        });
    }





    @And("Verify the error {string} displays")
    public void verifyTheErrorDisplays(String error) {
        commonPage.pageLoaded();
        boolean errorDisplays = driver.findElement(By.xpath("//*[normalize-space()=\"" + error + "\"]")).isDisplayed();

        Assert.assertTrue(errorDisplays, "Error not displayed");

        System.out.println("Correct error displays");
    }

    @And("I press the tab button")
    public void pressTabButton() {
        commonPage.pageLoaded();

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

    @And("this {string} a data driven test")
    public void theNextTestIsDataDriven(String isDataDriven) {
        if (isDataDriven.toLowerCase(Locale.ROOT).equals("is")){
            paramTestNext = true;
            System.out.println(paramTestNext);
        } else if (isDataDriven.toLowerCase(Locale.ROOT).equals("is not")){
            paramTestNext = false;
            System.out.println(paramTestNext);
        } else {
            System.out.println("value must be 'is' or 'is not'");
            Assert.fail();
        }
    }

    //Login Steps

    @Then("I see the Login Failed message")
    public void iSeeTheLoginFailedMessage() {
        System.out.println("Checking the error message.");
        boolean check = login.confirmErrorMsg();
        Assert.assertTrue(login.confirmErrorMsg(), "The error message was incorrect or not found.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I see the email error message")
    public void iSeeTheEmailErrorMessage() {
        // full message looks something like "Please include an '@' in the email address. 'carlwu_patracorp.com' is missing an '@'."
        //Note: these warnings are browser-specific. This code was written for Chrome.
        Assert.assertTrue(login.warningMsgDisplayed("email").contains("Please include an '@' in the email address."), "The warning message was not found, or had incorrect text.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I see the empty {string} field warning")
    public void thereIsNoErrorDisplayed(String field) {
        System.out.println("There should be a warning on the empty field.");
        String warningMsg = "Please fill out this field.";
        //Note: these warnings are browser-specific. This code was written for Chrome.
        Assert.assertEquals(login.warningMsgDisplayed(field), warningMsg);
        Assert.assertFalse(login.errorMsgDisplayed(), "An error message was displayed, but was not expected to.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @When("I Click on user icon in WOT")
    public void iClickOnUserIcon() {
        System.out.println("Clicking User Icon");
        login.WOTClickUserIcon();
    }

    @And("I refresh the page")
    public void refreshThePage() {
        driver.navigate().refresh();
        commonPage.pageLoaded();
        System.out.println("Refreshing the page");
    }

    @And("I store the current username")
    public void storeCurrentUser() {
        login.WOTClickUserIcon();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("userName")));
        valueStore.put("currentUser", driver.findElement(By.className("userName")).getText());
    }
}
