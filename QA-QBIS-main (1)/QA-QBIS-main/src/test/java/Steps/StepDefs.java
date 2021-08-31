package Steps;

import Base.BaseUtil;
import Base.Emails;
import Pages.CommonPage;
import Pages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepDefs extends BaseUtil {


    public StepDefs() {
        login = new Login(driver);
        commonPage = new CommonPage(driver, js);
        emails = new Emails(login.getLogins().getProperty("gmail.email"), login.getLogins().getProperty("gmail.auth"));
        wait = new WebDriverWait(driver, 10);
    }

    @Given("^I am on the login page$")
    public void iAmOnTheLoginPage() {
        System.out.println("Navigating to DEV login page\n");
        driver.get("https://dev.qbis.co/#/signin");
        driver.manage().window().maximize();
    }


    @And("I open the URL {string}")
    public void iOpenTheURL(String url) {

        driver.get(url);
    }

    @When("I enter the email and password for the \"([^\"]*)\"$")
    public void iEnterTheEmailAndPasswordForThe(String user) throws Exception {
        currentLogin = user;
        login.EnterCredentials(user);

    }

    @Then("I log out")
    public void iLogOut() {
        System.out.println("Opening profile menu...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]"))).click();
        System.out.println("Signing out " + currentLogin.toUpperCase());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Sign Out')]"))).click();
        commonPage.pageLoaded();
    }

    @And("I click the Sign In button")
    public void iClickTheSignInButton() {
        System.out.println("Clicking Sign In button\n");
        login.ClickSignIn();
        commonPage.pageLoaded();
        commonPage.setCurrentLayerAndUser();
    }

    @Then("I will be taken to the apps page")
    public void iWillBeTakenToTheAppsPage() throws Exception {
        login.onCorrectPage();
    }

    @And("I wait for {int} seconds")
    public void iWaitForSeconds(int seconds) {
        // A common method is created for explicit time bound wait
        commonPage.waitFor(seconds * 1000);
    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        commonPage.pageLoaded();
        Assert.assertTrue(commonPage.commonButton(button), button + " button could not be found on page");
        System.out.println("Clicking " + button + " button.");
    }

    @And("I enter {string} in the {string} field")
    public void iEnterInTheField(String text, String fieldName) {
        String trackEntry = text;
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        String random = new SimpleDateFormat("DDDyyHHmmss").format(new Date());

        if (trackEntry.contains("<current date>")) {
            trackEntry = trackEntry.replaceAll("<current date>", dateString + " " + timeString);
        }
        if (trackEntry.contains("<random>")) {
            trackEntry = trackEntry.replaceAll("<random>", random);
        }
        if (trackEntry.contains("<gmail>")) {
            String email = login.getLogins().getProperty("gmail.email");
            if (trackEntry.contains("+")) {
                String modifier = trackEntry.substring(trackEntry.indexOf('+'));
                String[] eSplit = email.split("@");
                email = eSplit[0] + modifier + "@" + eSplit[1];
            }
            trackEntry = email;
        }

        System.out.println("Entering " + trackEntry + " into " + fieldName + " field.");

        Assert.assertTrue(commonPage.commonFieldEnter(fieldName, trackEntry),
                "Field labeled " + fieldName + " is not present on page!");

        valueStore.put(fieldName, trackEntry);
    }

    @And("I select {string} from the {string} drop down")
    public void iSelectFromTheDropDown(String selection, String dropDown) {
        commonPage.pageLoaded();
        System.out.println("Selecting " + selection + " from " + dropDown + " drop down.");
        Assert.assertTrue(commonPage.commonDropDownSelect(dropDown, selection),
                "Could not select " + selection + " from the " + dropDown + " dropdown");

        valueStore.put(dropDown, selection);
    }

    @And("I get the value for the {string} drop down")
    public void iGetTheValueForTheDropDown(String dropDown) {

        System.out.println("Entry in " + dropDown + " drop down: " + commonPage.commonDropDownSelect(null, dropDown));
    }

    @And("I take a screenshot")
    public void iTakeAScreenshot() {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format((new Date()));
        System.out.println("attempting to add to file: target/screenshots/" + BaseUtil.scenarioName + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("target/screenshots/" + BaseUtil.scenarioName + dateString + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @And("I get the {string} info from valueStore")
    public void iGetTheInfoFromValueStore(String storeItem) {
        System.out.println(storeItem + " in valueStore = " + valueStore.get(storeItem));
    }

    @And("I clear the valueStore")
    public void iClearTheValueStore() {
        valueStore.clear();
        editedValues.clear();
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

    @When("I open the newly created activity")
    public void iOpenTheNewlyCreatedActivity() {
        driver.findElement(By.linkText("Activities")).click();
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(valueStore.get("Activity Type")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_follow")));
    }

    @And("I pick today's date from the {string} date picker")
    public void iPickTodaySDateFromTheDatePicker(String datePicker) {
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Assert.assertTrue(commonPage.commonDatePick(datePicker, day.format(new Date()), month.format(new Date()), year.format(new Date())), "Could not enter date into " + datePicker + " date picker");
        System.out.println("Entering today's date (" + month.format(new Date()) + " " + day.format(new Date()) + ", " + year.format(new Date()) + ") into " + datePicker + " date picker");
    }

    @And("I enter {string} in the {string} text box")
    public void iEnterInTheTextBox(String text, String textArea) {
        String trackEntry;
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());

        if (text.contains("<current date>")) {
            trackEntry = text.replaceAll("<current date>", dateString + " " + timeString);
        } else {
            trackEntry = text;
        }

        System.out.println("Entering " + trackEntry + " into " + textArea + " text box.");

        Assert.assertTrue(commonPage.commonTextAreaEnter(textArea, trackEntry),
                "Could not find " + textArea + " text area on page");

        valueStore.put(textArea, trackEntry);
    }

    @When("I upload an attachment")
    public void iUploadAnAttachment() {
        if (!commonPage.commonButton("Add Attachments")) {
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
        commonPage.pageLoaded();

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

        driver.close();
        driver.switchTo().window(parentWindow);

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


    @And("I pick {string} - {string} - {string} from the {string} date picker")
    public void iPickFromTheDatePicker(String day, String month, String year, String datePicker) {
        Assert.assertTrue(commonPage.commonDatePick(datePicker, day, month, year),
                "Could not find the " + datePicker + " date picker");
    }

    @And("I click {string} checkbox")
    public void iClickCheckbox(String checkBox) {
        System.out.println("Clicking " + checkBox + " check box");
        Assert.assertTrue(commonPage.commonCheckBox(checkBox), checkBox + " checkbox could not be found!");
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

    @And("I edit the following drop downs")
    public void iEditTheFollowingDropDowns(List<Map<String, String>> table) {
        Map<String, String> dropDowns = table.get(0);
        dropDowns.forEach((key, value) -> {
            System.out.println("Selecting " + value + " from " + key + " drop down");
            Assert.assertTrue(commonPage.commonDropDownSelect(key, value), "Could not find " + key + " drop down");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });

    }

    @And("I edit the following fields")
    public void iEditTheFollowingFields(List<Map<String, String>> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        String random = new SimpleDateFormat("DDDyyHHmmss").format(new Date());
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (value.contains("<random>")) {
                value = value.replaceAll("<random>", random);
            }
            if (value.contains("<gmail>")) {
                String email = login.getLogins().getProperty("gmail.email");
                if (value.contains("+")) {
                    String modifier = value.substring(value.indexOf('+'));
                    String[] eSplit = email.split("@");
                    email = eSplit[0] + modifier + "@" + eSplit[1];
                }
                value = email;
            }
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(commonPage.commonFieldEnter(key, value), "Could not find " + key + " field!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @And("I edit the following text areas")
    public void iEditTheFollowingTextAreas(List<Map<String, String>> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(commonPage.commonTextAreaEnter(key, value), "Could not find " + key + " text area!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @Then("The log out version of the sign in page will be displayed")
    public void theLogOutVersionOfTheSignInPageWillBeDisplayed() {
        commonPage.pageLoaded();
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/logout"), "Not redirected to log out page!");
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[1]")).getText(), "You are now logged out",
                "You are now logged out message not displayed!");
        System.out.println("Log out screen displayed");
    }

    @And("I fail the test")
    public void iFailTheTest() {
        Assert.fail("Failing test for testing purpose");
    }

    @And("I enter the following information into the form")
    public void iEnterTheFollowingInformationIntoTheForm(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        String random = new SimpleDateFormat("DDDyyHHmmss").format(new Date());
        table.forEach((key, value) -> {
            boolean foundItem = false;

            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (value.contains("<random>")) {
                value = value.replaceAll("<random>", random);
            }
            if (value.contains("<gmail>")) {
                String email = login.getLogins().getProperty("gmail.email");
                if (value.contains("+")) {
                    String modifier = value.substring(value.indexOf('+'));
                    String[] eSplit = email.split("@");
                    email = eSplit[0] + modifier + "@" + eSplit[1];
                    valueStore.put("randomEmail", email);
                }
                value = email;
            } else if (value.contains("Test Agency")) {
                valueStore.put("agencyName", value);
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
            valueStore.put(key, value);
        });
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

    @And("I set the date in the {string} date picker to")
    public void iSetTheDateInTheDatePickerTo(String datePicker, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if (selections.containsKey("Time")) {
            Assert.assertTrue(commonPage.commonDatePick(datePicker, selections), "Could not enter date into " + datePicker + " date picker");
            System.out.println("Entering " + selections.get("Month") + " " + selections.get("Day") + ", " + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + datePicker + " date picker.");
        } else {
            Assert.assertTrue(commonPage.commonDatePick(datePicker, selections.get("Day"), selections.get("Month"), selections.get("Year")), "Could not enter date into " + datePicker + " date picker");

            System.out.println("Entering " + selections.get("Month") + " " + selections.get("Day") + ", " + selections.get("Year") + " into " + datePicker + " date picker.");
        }
    }


    @And("I enter {string} in the {string} selector")
    public void iEnterInTheSelector(String entry, String selector) {

        System.out.println("Entering " + entry + " into " + selector + " field.");

        Assert.assertTrue(commonPage.commonSelectorPick(selector, entry),
                "Field labeled " + selector + " is not present on page!");

        valueStore.put(selector, entry);
    }

    @When("I enter the {string} email and {string} password")
    public void iEnterTheEmailAndPassword(String email, String password) {
        driver.findElement(By.id("signIn-Email")).sendKeys(email);
        driver.findElement(By.id("signIn-Password")).sendKeys(password);
    }

    @Given("I am using the {string} browser")
    public void iAmUsingTheBrowser(String browser) {
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                Assert.fail(browser.toUpperCase() + " not supported");
        }
    }

    @And("I select the {string} option from the hamburger menu")
    public void iSelectTheOptionFromTheHamburgerMenu(String option) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[1]/div[4]/div/header/div/button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@role=\"menuitem\"]/descendant::div[normalize-space()=\"" + option + "\"]"))).click();
    }

    @And("I verify the Owners page is displayed")
    public void iVerifyTheOwnersPageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/descendant::span[normalize-space()=\"Add owner\"]")));
    }

    @And("I open the Add Owner form")
    public void iOpenTheAddOwnerForm() {
        commonPage.commonButton("Add owner");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signature__authorizedSignature\"]/div[1]/div/div[2]/canvas")));
    }

    @And("I add a signature")
    public void iAddASignature() {

        WebElement canvas = driver.findElement(By.xpath("//*[@id=\"signature__authorizedSignature\"]/div[1]/div/div[2]/canvas"));
        Actions builder = new Actions(driver);
        Action drawAction = builder.moveToElement(driver.findElement(By.xpath("//*[@id=\"signature__authorizedSignature\"]/div[1]/div/div[2]/canvas"))) //start points x axis and y axis.
                .clickAndHold()
                .moveByOffset(-50, 60)
                .moveByOffset(-60, -70)
                .moveByOffset(150, 60)
                .moveByOffset(-60, -70)
                .release()
                .click()
                .build();
        drawAction.perform();
    }


    @And("I enter given text into the following fields")
    public void iEnterGivenTextIntoTheFollowingFields(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        String random = new SimpleDateFormat("DDDyyHHmmss").format(new Date());
        table.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (value.contains("<random>")) {
                value = value.replaceAll("<random>", random);
            }
            if (value.contains("<gmail>")) {
                String email = login.getLogins().getProperty("gmail.email");
                if (value.contains("+")) {
                    String modifier = value.substring(value.indexOf('+'));
                    String[] eSplit = email.split("@");
                    email = eSplit[0] + modifier + "@" + eSplit[1];
                }
                value = email;
            }
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(commonPage.commonFieldEnter(key, value), "Could not find " + key + " field!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @And("I verify the new owner is added")
    public void iVerifyTheNewOwnerIsAdded() {
        String businessName = valueStore.get("Business Name");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class=\"ownerObject.info.businessName\" and normalize-space(text())=\"" + businessName + "\"]")));
        System.out.println("Verified creation of \"" + valueStore.get("Business Name") + "\" owner");
    }

    @And("I verify the email for {string} was received")
    public void iVerifyTheEmailForWasReceived(String emailType) {
        String subject = "";
        Pattern linkPattern = Pattern.compile("(?<=Open )(.*?)(?= from your browser)");
        switch (emailType) {
            case "Owner Creation":
                subject = "Welcome to Your " + valueStore.get("Business Name") + " Account";
                // linkPattern = Pattern.compile("(?<=Open )(.*?)(?= from your browser)");
                break;
            case "Carrier Staff":
                subject = "Welcome to Your Markel Account";
                break;
            case "Owner Staff":
                subject = "Welcome to Your " + valueStore.get("currentOwner") + " Account";
                break;
            case "Staff Creation":
                subject = "Welcome to Your Socius Insurance Account";
                break;
            case "Forgot Password":
                subject = "Reset your PatraCloud password";
                linkPattern = Pattern.compile("(?<=follow this link: )(.*)");
                break;
            case "Agency Staff":
                subject = "Welcome to Your Socius Agency Account";
                break;
            case "Agency Creation":
                subject = "Welcome to Your " + valueStore.get("agencyName") + " Account";
                break;
            default:
                Assert.fail("No subject found for " + emailType + " email type");
        }
        int msgCount = emails.inboxMessageCount();
        Assert.assertNotEquals(msgCount, -1, "Could not retrieve number of messages from inbox");
        String emailMessage = emails.getEmailBySubject(subject);
        if (emailMessage == null) {
            System.out.println("Message not found, waiting for up to one minute for message");
            long startTime = System.currentTimeMillis();
            while (emails.inboxMessageCount() == msgCount && (System.currentTimeMillis() - startTime) < 60000) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {

                }
            }
            emailMessage = emails.getEmailBySubject(subject);
        }
        Assert.assertNotNull(emailMessage,
                "Email with subject \"" + subject + "\" could not be found after waiting for one minute");
        assert linkPattern != null;
        Matcher msgBody = linkPattern.matcher(emailMessage);
        if (msgBody.find()) {
            String msgLink = msgBody.group();
            System.out.println("found following link: " + msgLink);
            valueStore.put("Email Link", msgLink);
        }

    }

    @And("I open the link from the email")
    public void iOpenTheLinkFromTheEmail() {
        driver.manage().deleteAllCookies();
        driver.get(valueStore.get("Email Link"));
    }
}
