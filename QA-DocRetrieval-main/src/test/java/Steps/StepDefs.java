package Steps;

import Base.BaseUtil;
import Base.KpiClass;
import Base.NodeApp;
import Pages.GridFunctions;
import Pages.Login;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StepDefs extends BaseUtil {

    private final HashMap<String, String> exceptionMap = new HashMap<>();

    public StepDefs() {
        login = new Login(driver);
        //gridFunctions = new GridFunctions(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Given("^I am on the login page$")
    public void iAmOnTheLoginPage() {
        System.out.println("Navigating to DEV login page\n");
        driver.get("http://login.dev.patracloud.net/auth");
        driver.manage().window().maximize();
    }


    @And("I open the URL {string}")
    public void iOpenTheURL(String url) {

        driver.get(url);
        BaseUtil.pageLoaded();
    }


    @Then("I log out")
    public void iLogOut() {
        System.out.println("Logging out " + currentLogin.toUpperCase());

        if (currentApp.equalsIgnoreCase("setup")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-toggle"))).click();
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".glyphicon"))).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Log Out')]"))).click();
        BaseUtil.pageLoaded();
    }

    @And("I click the Sign In button")
    public void iClickTheSignInButton() throws InterruptedException {
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
        //    commonPage.pageLoaded();
    }

    @Then("I will be taken to the homepage for that app")
    public void iWillBeTakenToTheHomepageForThatApp() {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonPage.onCorrectPage(), "Homepage for " + currentApp + " not displayed");
        System.out.println("On homepage for " + currentApp);


    }

    @And("I wait for {string} seconds")
    public void iWaitForSeconds(String seconds) throws InterruptedException {
        int timeToWait = Integer.parseInt(seconds) * 1000;

        Thread.sleep(timeToWait);
    }


    @And("I get the count for {string} KPI")
    public void getTheCountForKPI(String kpiName) {
        boolean kpiFound = false;
        int incrementor = 0;
        kpiCompare = new ArrayList<>();
        kpiCompare = commonPage.getKpi();

        // kpiCompare array is being filled with the KPI that are visible on the screen
        while (commonPage.addKpi(incrementor) != null) {
            kpiCompare.add(commonPage.addKpi(incrementor));
            incrementor++;
        }





        // If "All" was used as the KPI name then it will print out a list of all KPIs visible with their values
        if (kpiName.equalsIgnoreCase("all")) {
            kpiFound = true;
            for (KpiClass kpiClass : kpiCompare) {
                // Checking if the KpiClass object is using a float or int value
                // If the getKpiValueInt is less than 0, then the KPIs value is being stored as a float
                if (kpiClass.getKpiValueInt() < 0) {
                    System.out.println(kpiClass.getKpiTitle() + " current value is " + kpiClass.getKpiValueFloat());
                    valueStore.put(kpiClass.getKpiTitle(), Float.toString(kpiClass.getKpiValueFloat()));
                } else {
                    System.out.println(kpiClass.getKpiTitle() + " current value is " + kpiClass.getKpiValueInt());
                    valueStore.put(kpiClass.getKpiTitle(), Integer.toString(kpiClass.getKpiValueInt()));
                }
            }
        } else {
            // Otherwise it will only print out the value of the KPI specified, but all will be stored
            for (KpiClass kpiClass : kpiCompare) {
                if (kpiClass.getKpiTitle().equalsIgnoreCase(kpiName)) {
                    kpiFound = true;
                    if (kpiClass.getKpiValueInt() < 0) {
                        System.out.println(kpiClass.getKpiTitle() + " current value is " + kpiClass.getKpiValueFloat());
                        valueStore.put(kpiClass.getKpiTitle(), Float.toString(kpiClass.getKpiValueFloat()));
                    } else {
                        System.out.println(kpiClass.getKpiTitle() + " current value is " + kpiClass.getKpiValueInt());
                        valueStore.put(kpiClass.getKpiTitle(), Integer.toString(kpiClass.getKpiValueInt()));
                    }

                }
            }
        }

        Assert.assertTrue(kpiFound, kpiName + " was not found, please ensure you are on the correct page");


    }

    @And("Return to the apps page")
    public void returnToTheAppsPage() {
        driver.findElement(By.id("index")).click();
    }


    @Then("Verify that {string} KPI has {string}")
    public void verifyThatKPIHas(String kpiName, String kpiStatus) throws Exception {

        int comparison = Integer.MAX_VALUE;
        int status;
        String originalValue = "No value found";
        String updatedValue = "No value found";
        boolean kpiFound = false;
        ArrayList<KpiClass> kpiUpdate;
        kpiUpdate = commonPage.getKpi();

        int incrementor = 0;
        // kpiUpdate array is being filled with all KPIs currently on screen
        while (commonPage.addKpi(incrementor) != null) {
            kpiUpdate.add(commonPage.addKpi(incrementor));
            incrementor++;
        }




        switch (kpiStatus.toLowerCase()) {
            case "increased":
                status = 1;
                break;
            case "decreased":
                status = -1;
                break;
            case "not changed":
                status = 0;
                break;
            default:
                throw new Exception(kpiStatus + " status not recognized, please use 'Increased', 'Decreased', or 'Unchanged'.");
        }

        // Checking if requested KPI is in the kpiUpdate array
        for (KpiClass updatedKpi : kpiUpdate) {
            if (updatedKpi.getKpiTitle().equalsIgnoreCase(kpiName)) {
                // Requested KPI found in kpiUpdate array, now finding a match in kpiCompare array
                for (KpiClass originalKpi : kpiCompare) {
                    if (originalKpi.getKpiTitle().equalsIgnoreCase(kpiName)) {
                        kpiFound = true;
                        if (originalKpi.getKpiValueInt() < 0) {
                            comparison = Float.compare(updatedKpi.getKpiValueFloat(), originalKpi.getKpiValueFloat());
                            originalValue = Float.toString(originalKpi.getKpiValueFloat());
                            updatedValue = Float.toString(updatedKpi.getKpiValueFloat());
                            // Setting the value in the kpiCompare array to be equal to the value in kpiUpdate
                            originalKpi.setKpiValueFloat(updatedKpi.getKpiValueFloat());
                            valueStore.put(originalKpi.getKpiTitle(), Float.toString(originalKpi.getKpiValueFloat()));

                        } else {
                            comparison = Integer.compare(updatedKpi.getKpiValueInt(), originalKpi.getKpiValueInt());
                            originalValue = Integer.toString(originalKpi.getKpiValueInt());
                            updatedValue = Integer.toString(updatedKpi.getKpiValueInt());
                            // Setting the value in the kpiCompare array to be equal to the value in kpiUpdate
                            originalKpi.setKpiValueInt(updatedKpi.getKpiValueInt());
                            valueStore.put(originalKpi.getKpiTitle(), Integer.toString(originalKpi.getKpiValueInt()));
                        }
                        break;
                    }
                }
            }

        }
        if (!kpiFound) Assert.fail("Could not find " + kpiName + " please ensure you are on the right page");

        Assert.assertEquals(comparison, status, "Value for " + kpiName + "  has not been " + kpiStatus + ". Original value: " + originalValue + " updated value " + updatedValue);

        System.out.println("Verified that value for " + kpiName + " has been " + kpiStatus + ". Original value: " + originalValue + " updated value " + updatedValue);


    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonPage.commonButton(button), button + " button could not be found on page");
        BaseUtil.pageLoaded();
        System.out.println("Clicking " + button + " button.");
    }

    @And("I enter {string} in the {string} field")
    public void iEnterInTheField(String text, String fieldName) {
        String trackEntry;
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());

        if (text.contains("<current date>")) {
            trackEntry = text.replaceAll("<current date>", dateString + " " + timeString);
        } else {
            trackEntry = text;
        }

        System.out.println("Entering " + trackEntry + " into " + fieldName + " field.");

        Assert.assertTrue(commonPage.commonFieldEnter(fieldName, trackEntry),
                "Field labeled " + fieldName + " is not present on page!");

        valueStore.put(fieldName, trackEntry);
    }

    @And("I select {string} from the {string} drop down")
    public void iSelectFromTheDropDown(String selection, String dropDown) {
        //  commonPage.pageLoaded();
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
    public void iFindTheNewlyCreatedBusiness() throws InterruptedException {
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
        BaseUtil.pageLoaded();

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
        BaseUtil.pageLoaded();
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addActivity")));
        try {
            driver.findElement(By.linkText("Activities")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Activities")));
        }

        BaseUtil.pageLoaded();
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
        BaseUtil.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(valueStore.get("Activity Type")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_follow")));
    }

    @And("I pick today's date from the {string} date picker")
    public void iPickTodaySDateFromTheDatePicker(String datePicker) {
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Map<String, String> dateTime = new HashMap<>();
        dateTime.put("Day", day.format(new Date()));
        dateTime.put("Month", month.format(new Date()));
        dateTime.put("Year", year.format(new Date()));
        dateTime.put("Time", "12:00");
        dateTime.put("AM / PM", "AM");
        if (!commonPage.commonDatePick(datePicker, dateTime.get("Day"), dateTime.get("Month"), dateTime.get("Year"))) {
            commonPage.commonDatePick(datePicker, dateTime);
        }

    }

    @Then("The new follow up will be displayed in the grid")
    public void theNewFollowUpWillBeDisplayedInTheGrid() {
        driver.findElement(By.id("followUp_submit")).click();
        WebElement activity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\"" + valueStore.get("Follow Up Type") + "\"]")));
        Assert.assertEquals(activity.getText(), valueStore.get("Follow Up Type"),
                "Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[1]")));
        driver.findElement(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\""+valueStore.get("Follow Up Type")+"\"]"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\""+valueStore.get("Follow Up Type")+"\"]")).isDisplayed(),
                "Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");




//        if(commonPage.gridEntry("row 1", "Follow Up Type").getText().equals(valueStore.get("Follow Up Type"))) {
//            System.out.println(valueStore.get("Follow Up Type") + " follow up is displayed in Follow Ups grid");
//        } else {
//            System.err.println("Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");
//        }

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
        commonPage.commonButton("Add Attachments");
        driver.findElement(By.id("filename")).sendKeys(attachLocation);

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
        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + "PMA opened attachment" + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + "PMA opened attachment" + dateString + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.switchTo().window(parentWindow);

    }

    @And("I open the opportunity")
    public void iOpenTheOpportunity() {
        BaseUtil.pageLoaded();
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

    @And("I get all the KPI elements")
    public void iGetAllTheKPIElements() {
        ArrayList<KpiClass> testKpi = new ArrayList<>();
        List<WebElement> kpi = driver.findElements(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]"));
        KpiClass listEntry;
        String numExtraction;
        StringBuilder stringBuilder;
        String title;
        String value;
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
                        cTitle += " " + vName;
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
                        cTitle += " " + vName;
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
        BaseUtil.pageLoaded();
    }

    @And("I open DBR for Company 002")
    public void iOpenDBRForCompany() {
        BaseUtil.pageLoaded();
        WebElement selectedList = driver.findElement(By.id("companyStartId"));
        Select list = new Select(selectedList);
        list.selectByVisibleText("Company 002");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(" Company 002 - DBR"))).click();
        BaseUtil.pageLoaded();
    }

    @And("I open {string} for Company 002")
    public void iOpenForCompany(String service) {
        BaseUtil.pageLoaded();
        WebElement selectedList = driver.findElement(By.id("companyStartId"));
        Select list = new Select(selectedList);
        list.selectByVisibleText("Company 002");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Company 002 - " + service + ""))).click();
        BaseUtil.pageLoaded();
    }

    @And("I edit the following drop downs")
    public void iEditTheFollowingDropDowns(List<Map<String, String>> table) {
        Map<String, String> dropDowns = table.get(0);
        dropDowns.forEach((key, value) -> {
            System.out.println("Selecting " + value + " from " + key + " drop down");
            Assert.assertTrue(commonPage.commonDropDownSelect(key, value),
                    "Could not select " + value + " from " + key + " drop down");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });

    }

    @And("I edit the following fields")
    public void iEditTheFollowingFields(List<Map<String, String>> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
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

    @And("I click on the {string} KPI")
    public void iClickOnTheKPI(String kpi) {

        commonPage.clickKpi(kpi);
    }

    @Then("I verify the number of records in the {string} grid match the {string} KPI")
    public void iVerifyTheNumberOfRecordsInTheGridMatchTheKPI(String tabName, String kpi) {
//        List<WebElement> descriptions = driver.findElements(By.className("desc"));
//        String item = "";
//        for(WebElement element : descriptions) {
//            String description = element.getText().replaceAll("\\n", " ");
//            if(description.equals(kpi) && valueStore.containsKey(description)) {
//                item = description;
//                element.click();
//                break;
//            }
//        }
        commonPage.clickKpi(kpi);
        BaseUtil.pageLoaded();
        String rowNum;
        rowNum = Integer.toString(commonPage.gridRecordNumber(tabName));

        Assert.assertEquals(rowNum, valueStore.get(kpi), "Rows in grid do not match number of " + kpi + " KPI");
        System.out.println("Number of records in grid (" + rowNum + ") match the number shown in the " + kpi + " KPI (" + valueStore.get(kpi) + ")");
    }

    @Then("The log out version of the sign in page will be displayed")
    public void theLogOutVersionOfTheSignInPageWillBeDisplayed() {
        NodeApp.pageLoaded();
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/logout"), "Not redirected to log out page!");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]")).getText().equals("You are now logged out"),
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
        table.forEach((key, value) -> {
            boolean foundItem = false;
            if (value.contains("<current date>")) {
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
            valueStore.put(key, value);
        });
    }

    @And("I add the following items to the valueMap")
    public void iAddTheFollowingItemsToTheValueMap(Map<String, String> table) {
        table.forEach((key, value) -> {
            valueStore.put(key, value);
        });
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


    @And("I set that date in the {string} date picker to")
    public void iSetThatDateInTheDatePickerTo(String datePicker, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if (selections.containsKey("Time")) {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + datePicker + " date picker.");
            commonPage.commonDatePick(datePicker, selections);
        } else {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " into " + datePicker + " date picker.");
            commonPage.commonDatePick(datePicker, selections.get("Day"), selections.get("Month"), selections.get("Year"));
        }
    }


    @And("Verify the error {string} displays")
    public void verifyTheErrorDisplays(String error) {
        NodeApp.pageLoaded();
        boolean errorDisplays = driver.findElement(By.xpath("//*[normalize-space()=\"" + error + "\"]")).isDisplayed();

        Assert.assertTrue(errorDisplays, "Error not displayed");

        System.out.println("Correct error displays");
    }

}
