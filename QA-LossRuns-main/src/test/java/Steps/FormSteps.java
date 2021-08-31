package Steps;

import Base.BaseUtil;
import Pages.CommonForm;
import Pages.LossRunsHappyPathPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FormSteps extends BaseUtil {
    private CommonForm commonForm;
    private HashMap<String, String> exceptionMap = new HashMap<>();

    public FormSteps() {
        this.commonForm = new CommonForm(driver, js);
        lossrunspage = new LossRunsHappyPathPage(driver);
    }


    @Then("I check if the {string} button is {string}")
    public void iCheckTheStatusOfButton(String button, String status) {
        BaseUtil.pageLoaded();
        boolean buttonStatus = commonForm.commonButtonStatus(button);
        switch (status) {
            case "disabled":
                Assert.assertTrue(buttonStatus, "button is not disabled");
                System.out.println(button + " button is disabled");
                break;
            case "enabled":
                Assert.assertFalse(buttonStatus, "button is not disabled");
                System.out.println(button + " button is enabled");
                break;
            default:
                System.out.println("status was not in the switch statement.");
                Assert.fail("invalid switch value provided");
        }
        BaseUtil.pageLoaded();
    }

    @And("I select {string} from the {string} drop down")
    public void iSelectFromTheDropDown(String selection, String dropDown) {
        BaseUtil.pageLoaded();
        System.out.println("Selecting " + selection + " from " + dropDown + " drop down.");
        Assert.assertTrue(commonForm.commonDropDownSelect(dropDown, selection),
                "Could not select " + selection + " from the " + dropDown + " dropdown");

        valueStore.put(dropDown, selection);
    }

    @And("I edit the following drop downs")
    public void iEditTheFollowingDropDowns(List<Map<String, String>> table) {
        Map<String, String> dropDowns = table.get(0);
        dropDowns.forEach((key, value) -> {
            System.out.println("Selecting " + value + " from " + key + " drop down");
            Assert.assertTrue(commonForm.commonDropDownSelect(key, value),
                    "Could not select " + value + " from " + key + " drop down");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });

    }

    @And("I get the value for the {string} drop down")
    public void iGetTheValueForTheDropDown(String dropDown) {
        String value = commonForm.commonDropDownRead(dropDown);
        Assert.assertNotNull(value, "Could not read from " + dropDown + " drop down");

        System.out.println("Entry in " + dropDown + " drop down: " + value);
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

        Assert.assertTrue(commonForm.commonFieldEnter(fieldName, trackEntry),
                "Field labeled " + fieldName + " is not present on page!");

        valueStore.put(fieldName, trackEntry);
    }

    @And("I enter a random string in the {string} field")
    public void iEnterRandomInTheField(String fieldName) {
        String trackEntry = valueStore.get("randomCharacters");
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());


        System.out.println("Entering " + trackEntry + " into " + fieldName + " field.");

        Assert.assertTrue(commonForm.commonFieldEnter(fieldName, trackEntry),
                "Field labeled " + fieldName + " is not present on page!");

        valueStore.put(fieldName, trackEntry);
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
            Assert.assertTrue(commonForm.commonFieldEnter(key, value), "Could not find " + key + " field!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
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

        Assert.assertTrue(commonForm.commonTextAreaEnter(textArea, trackEntry),
                "Could not find " + textArea + " text area on page");

        valueStore.put(textArea, trackEntry);
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
            Assert.assertTrue(commonForm.commonTextAreaEnter(key, value), "Could not find " + key + " text area!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
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
            if (commonForm.commonFieldEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
            } else if (commonForm.commonDropDownSelect(key, value)) {
                foundItem = true;
                System.out.println("Selecting \"" + value + "\" from \"" + key + "\" drop down");
            } else if (commonForm.commonTextAreaEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" text area");
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
            valueStore.put(key, value);
        });
    }

    @When("I enter the following information into the table")
    public void iEnterTheFollowingInformationIntoTheTable(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        table.forEach((key, value) -> {
            boolean foundItem = false;
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (lossrunspage.carrierName(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
            } else if (lossrunspage.yearDetailInput(key, value)) {
                foundItem = true;
                System.out.println("Selecting \"" + value + "\" from \"" + key + "\" drop down");
            } else if(commonForm.commonFieldEnter(key , value)){
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
            } else if (commonForm.commonDropDownSelect(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" text area");
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
            valueStore.put(key, value);
        });
    }

    @And("I set the {string} date picker to today's date")
    public void iSetTheDatePickerToTodaySDate(String datePicker) {
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Map<String, String> dateTime = new HashMap<>();
        dateTime.put("Day", day.format(new Date()));
        dateTime.put("Month", month.format(new Date()));
        dateTime.put("Year", year.format(new Date()));
        dateTime.put("Time", "12:00");
        dateTime.put("AM / PM", "AM");
        if (!commonForm.commonDatePick(datePicker, dateTime.get("Day"), dateTime.get("Month"), dateTime.get("Year"))) {
            commonForm.commonDatePick(datePicker, dateTime);
        }
    }

    @And("I set the date in the {string} date picker to")
    public void iSetTheDateInTheDatePickerTo(String datePicker, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if (selections.containsKey("Time")) {
            Assert.assertTrue(commonForm.commonDatePick(datePicker, selections), "Could not enter date into " + datePicker + " date picker");
            System.out.println("Entering " + selections.get("Month") + " " + selections.get("Day") + ", " + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + datePicker + " date picker.");
        } else {
            Assert.assertTrue(commonForm.commonDatePick(datePicker, selections.get("Day"), selections.get("Month"), selections.get("Year")), "Could not enter date into " + datePicker + " date picker");

            System.out.println("Entering " + selections.get("Month") + " " + selections.get("Day") + ", " + selections.get("Year") + " into " + datePicker + " date picker.");
        }
    }

    @And("I verify the textarea {string} matches the created {string}")
    public void iVerifyTheField(String textArea, String value) {
        commonPage.pageLoaded();

        String id = null;
        switch (textArea) {
            case "Original Email":
                id = "Description";
                break;
            case "Comments":
                id = "Comments";
            default:
                System.out.println();
                Assert.fail("You must use a valid switch option");
                break;
        }

        String originalEmail = driver.findElement(By.xpath("//textarea[@id='" + id + "']")).getText().substring(1);

        System.out.println("Verifying '" + originalEmail + "' matches '" + valueStore.get(value) + "'");
        Assert.assertEquals(originalEmail, valueStore.get(value), "Expected " +
                valueStore.get(value) + " but found " + originalEmail);
    }

    @And("I verify the Original Email textarea is readonly")
    public void iVerifyTextAreaIsEditable() {
        commonPage.pageLoaded();

        WebElement element = driver.findElement(By.xpath("//textarea[@id='Description']"));

        Assert.assertTrue(commonForm.isAttributePresent(element, "readonly"), "web element is not readonly");
    }


    @Then("I verify that the due date is set to the expected day based on Rush being {string} in the Form")
    public void iVerifyTheRushDueDateCreation(String rushSetting) {
        BaseUtil.pageLoaded();
        //String dueDate = driver.findElement(By.xpath("//label[contains(text(), 'Due Date') ]/following-sibling::div")).getText();
        String dueDate = commonPage.commonFieldRead("Due Date");
        String addDate = commonPage.commonFieldRead("Start Date Override");
        Assert.assertTrue(commonForm.rushDueDateChecker(addDate, dueDate, rushSetting));

    }

    @Then("I verify that the due date is set to the expected day based on Rush being {string} in the Details")
    public void iVerifyTheRushDueDateDetails(String rushSetting) {
        BaseUtil.pageLoaded();
        String dueDate = driver.findElement(By.xpath("//label[contains(text(), 'Due Date') ]/following-sibling::div")).getText();
        String addDate = commonPage.commonFieldRead("Start Date Override");
        Assert.assertTrue(commonForm.rushDueDateChecker(addDate, dueDate, rushSetting));

    }


    @Then("I verify that the Next Reminder Needed is no longer displayed")
    public void iVerifyTheReminderIsGone() {
        BaseUtil.pageLoaded();
        String reminderNeededDate = driver.findElement(By.xpath("//label[contains(text(), 'Next Reminder Needed') ]/parent::div"))
                .getAttribute("style");

        Assert.assertEquals("display: none;", reminderNeededDate);
    }

    @Then("I verify that the Next Reminder Needed is empty")
    public void iVerifyTheReminderIsEmpty() {
        BaseUtil.pageLoaded();
        String reminderNeededDate = driver.findElement(By.xpath("//label[contains(text(), 'Next Reminder Needed') ]/following-sibling::input"))
                .getAttribute("value");

        Assert.assertEquals("", reminderNeededDate);
    }

    @Then("I verify that the {string} dropdown menu appears")
    public void iVerifyDropdownDisplayed(String dropDown) {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonPage.commonDropDown(dropDown).isDisplayed());

    }

    @Then("I verify that the {string} field appears")
    public void iVerifyFieldDisplayed(String field) {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.commonField(field).isDisplayed());
    }

    @Then("I verify the value for the {string} drop down is {string}")
    public void iVerifyTheValueOfTheDropDown(String dropDown, String dropDownValue) {
        String value = commonForm.commonDropDownRead(dropDown);
        Assert.assertTrue(value.equals(dropDownValue), "Could not read from " + dropDown + " drop down");
    }

    @Then("I verify these drop down menus have these values")
    public void iVerifyTheseDropMenus(Map<String, String> table) {
        table.forEach((dropDown, dropDownValue) -> {
            System.out.println(dropDown);
            String value = commonForm.commonDropDownRead(dropDown);
            Assert.assertEquals(dropDownValue, value, "Could not read from " + dropDown + " drop down");
        });
    }

    @Then("I verify the following information in the form")
    public void iVerifyTheFollowingInformationIntoTheForm(Map<String, String> table) {
        table.forEach((key, value) -> {
            boolean foundItem = false;
            if (value.equals(valueStore.get(key))) {
                foundItem = true;
                System.out.println("Reading \"" + value + "\" in \"" + key + "\" field");
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
        });
    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        Assert.assertTrue(commonForm.commonButton(button), button + " button could not be found on page");
        BaseUtil.pageLoaded();
        System.out.println("Clicking " + button + " button.");
    }

    @Then("I verify I am at the expected work order after hitting Save & Next")
    public void iVerifyWONumber() {
        commonPage.pageLoaded();
        String currentWO = driver.findElement(By.xpath("//label[normalize-space(text())='WO #:']/span")).getText();
        String expectedWO = String.valueOf(Integer.parseInt(valueStore.get("WO #")) + 1);
        System.out.println("Current Work Order is " + currentWO + " and expected Work Order is " + expectedWO);
        Assert.assertEquals(expectedWO, currentWO, "Not on the expected Work Order");
    }

    @And("I verify the following checkboxes in the form")
    public void iVerifyCheckboxChecked(Map<String, String> table) {
        commonPage.pageLoaded();

        table.forEach((ckbx, state) -> {
            switch (state) {
                case "checked":
                    Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space(text())='" + ckbx + "']/input[@type='checkbox']")).isSelected(), "should be checked but is not");
                    break;
                case "unchecked":
                    Assert.assertFalse(driver.findElement(By.xpath("//label[normalize-space(text())='" + ckbx + "']/input[@type='checkbox']")).isSelected(), "should not be checked but is9");
                    break;
                default:
                    System.out.println("status was not in the switch statement. Must be 'checked' or 'unchecked'");
                    Assert.fail("invalid switch value provided");
            }
        });
    }


    @And("I click on the {string} link")
    public void iClickOnTheLink(String linkText) {
        System.out.println("Clicking on the " + linkText + " link.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText(linkText)));
        }
        commonPage.pageLoaded();
    }

    @And("I return to the Apps homepage")
    public void iReturnToTheAppsHomepage() {
        driver.get("http://login.dev.patracloud.net/home");
        commonPage.pageLoaded();
    }

    @Then("I verify that the Due Date is set correctly")
    public void iVerifyThatTheDueDateIsSetCorrectly(Map<String, String> table) {
        System.out.println("Checking that the due date was set correctly.");
        if (!table.containsKey("Due Date")) {
            Assert.fail("No \"Due Date\" provided for step. Exiting.");
        }

        boolean rush = table.containsKey("Rush") ? Boolean.parseBoolean(table.get("Rush")) : false;
        long expectedDuration;

        if (valueStore.get("TAT").equals("TAT is not set")) {
            if (rush == true) {
                expectedDuration = Long.parseLong(valueStore.get("DefaultRushDDO"));
                BaseUtil.pageLoaded();
                Assert.assertEquals(commonForm.dueDateChecker(false), expectedDuration, "The number of days did not match.");
            } else {
                expectedDuration = Long.parseLong(valueStore.get("Dafaultddo"));
                BaseUtil.pageLoaded();
                Assert.assertEquals(commonForm.dueDateChecker(false), expectedDuration, "The number of days did not match.");
            }

        } else {
            boolean businessDays = table.containsKey("Business Days") ? Boolean.parseBoolean(table.get("Business Days")) : false;
            expectedDuration = Long.parseLong(table.get("Due Date"));
            if (expectedDuration == 0) {
                // If the DDO or Rush DDO is set to 0, then it defaults to either 14 (DDO) or 2 (Rush)
                expectedDuration = rush ? 4 : 5;
            }
            BaseUtil.pageLoaded();
            Assert.assertEquals(commonForm.dueDateChecker(businessDays), expectedDuration, "The number of days did not match.");
        }
    }

    @And("I get the work order number from the confirmation pop-up")
    public void iGetTheWorkOrderNumberFromTheConfirmationPopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"jconfirm\")]")));
        String woNum = driver.findElement(By.xpath("//div[@class=\"jconfirm-content-pane no-scroll\"]/descendant::font")).getText().replaceAll("\\D", "");
        valueStore.put("workOrder", woNum);
        exceptionMap.put("Related Work Order #", woNum);
        System.out.println("created work order number is: " + woNum);
        commonPage.commonButton("OK");

    }

    @Then("I verify the {string} tab is {string}")
    public void iVerifyTheTabExists(String tab, String status) {
        commonPage.pageLoaded();
        switch (status.toLowerCase(Locale.ROOT)) {
            case "present":
                Assert.assertTrue(commonForm.woTabsVisbility(tab), "Cannot find tab when tab was expected");
                break;
            case "not present":
                Assert.assertFalse(commonForm.woTabsVisbility(tab), "Found tab when tab was not expected");
                break;
            default:
                System.out.println("Next Reminder setting must either be \"Request Date\" or \"1st Follow Up\" or \"2nd Follow Up\" or \"3rd Follow Up\"");
                Assert.fail("incorrect switch values");
        }

    }

    @When("I select the {string} option from the the History tab Viewing drop down")
    public void iSelectTheOptionFromTheTheViewingDropDown(String rowOption) {
        BaseUtil.pageLoaded();
        rowCount = Integer.parseInt(rowOption);
        commonForm.formHistoryViewSelection(rowOption);

    }

    @Then("I verify the submit error {string} appears")

    public void iVerifyErrorAppears(String errorMsg) {
        BaseUtil.pageLoaded();
        Assert.assertEquals(commonForm.errorIsVisible().substring(2) , errorMsg);

    }

    @Then("I verify that the {string} is set to the expected day based on the {string} date")
    public void iVerifyThatTheIsSetToTheExpectedDayBasedTheDate(String fieldName, String rushSetting) {
            BaseUtil.pageLoaded();
            String reminderNeededDate = driver.findElement(By.xpath("//label[contains(text(), '"+fieldName+"') ]/following-sibling::input"))
                    .getAttribute("value");
            String setDate = commonPage.commonFieldRead(rushSetting);
        System.out.println(setDate);
            Assert.assertTrue(commonForm.reminderDateChecker(setDate, reminderNeededDate, rushSetting));

        }

    @Then("The following elements exist")
    public void fieldsExist(List<String> table) {
        pageLoaded();
        //Need atomic boolean to work with Java lambda expressions.
        AtomicBoolean elementExists = new AtomicBoolean(false);
        table.forEach(value -> {
            System.out.println("Checking the existance of: " + value);
            if (commonForm.commonField(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonDropDown(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonTextArea(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonButton2(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonCheckBox(value) != null) {
                elementExists.set(true);
            } else {
                elementExists.set(!driver.findElements(By.linkText(value)).isEmpty());
            }

            Assert.assertTrue(elementExists.get(), "Could not find " + value + " field!");
        });
    }

    @Then("The record is added")
    public void confirmAddRecord(List<String> fields) {
        // confirm record details match what were entered
        fields.forEach(key -> {
            boolean foundItem = false;
            if (commonForm.commonField(key) != null) {
                foundItem = true;
                String found = commonForm.commonFieldRead(key);
                if (key.contains("Date")) { // The way dates are stored and displayed are different. Need to do some parsing.
                    // Convert the expected format into the actual format, and then compare with the actual date.
                    // expected: "19-02-2021 103851", actual: "02/19/2021 10:38 AM"
                    SimpleDateFormat formatActual = new SimpleDateFormat("MM/dd/yyyy h:mm aa", Locale.ENGLISH); // need locale to catch AM/PM component
                    SimpleDateFormat formatExpected = new SimpleDateFormat("dd-MM-yyyy HHmmss");
                    try {
                        Date expectedDate = formatExpected.parse(valueStore.get(key));
                        String reformattedExpectedDate = formatActual.format(expectedDate);
                        // "Mail Date" in Print Shop is special. It is always set to 15:15 PT.
                        // Git issue: https://github.com/patracorp/print-shop/issues/34
                        // Test Case: https://patra.testrail.io/index.php?/cases/view/10466
                        if (key.equals("Mail Date")) {
                            reformattedExpectedDate = reformattedExpectedDate.substring(0, 11) + "3:15 PM";
                        }
                        System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + reformattedExpectedDate + "\") in \"" + key + "\" field");
                        Assert.assertEquals(found, reformattedExpectedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" field");
                    Assert.assertEquals(found, valueStore.get(key));
                }
            } else if (commonForm.commonDropDown(key) != null) {
                foundItem = true;
                String found = commonForm.commonDropDownRead(key);
                System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" drop down");
                Assert.assertEquals(found, valueStore.get(key));
            } else if (commonForm.commonTextArea(key) != null) {
                foundItem = true;
                String found = commonForm.commonTextAreaRead(key);
                System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" test area");
                Assert.assertEquals(found, valueStore.get(key));
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
        });
        System.out.println("Record has been successfully read");
    }

    @Then("I verify the {string} date is the expected value")
    public void iVerifyEmailDate(String emailDate) {
        BaseUtil.pageLoaded();
        String date = driver.findElement(By.xpath("//label[contains(text(), '" + emailDate + "') ]/following-sibling::div/input"))
                .getAttribute("value");
        String trimmedEmailDate = commonForm.dateTrimmer(date);
        Assert.assertEquals(commonForm.emailSentDateChecker(), trimmedEmailDate, "wrong date found for " + emailDate);
    }

    @And("I Verify the row is deleted")
    public void iVerifyTheRowIsDeleted() {
        Assert.assertTrue(lossrunspage.verifyRow().equals("No data available in table") , "The row was not deleted");
    }

}
