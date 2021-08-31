package Steps;

import Base.BaseUtil;
import Pages.CommonForm;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

public class FormSteps extends BaseUtil {
    private CommonForm commonForm;

    public FormSteps() {
        this.commonForm = new CommonForm(driver, js);
    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.commonButton(button), button + " button could not be found on page");
        BaseUtil.pageLoaded();
        System.out.println("Clicking " + button + " button.");
    }

    @When("I click the Start button in the Time Tracking modal")
    public void iClickTheStartButton () {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.timeModalStart(), "button could not be found on page");
        BaseUtil.pageLoaded();
        System.out.println("Clicking the Time Tracking start button.");
    }

    @When("I click the WO Close button")
    public void iClickWOCloseButton() {
        Assert.assertTrue(commonForm.clickCloseWOButton(), "WO Close button could not be found on page");
        System.out.println("Clicked the WO Close button.");
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

    @Then("I Click on {string} tab")
    public void iClickOnTab(String tabname) throws InterruptedException {
        currentTab = tabname.replaceAll("\\s", "");
        commonPage.pageLoaded();
        commonForm.addWOTabs(tabname);
        Thread.sleep(3000);
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

    @Then("I verify that the Next Reminder Needed is set to the expected day based the {string} date")
    public void iVerifyTheReminderDueDateDetails(String rushSetting) {
        BaseUtil.pageLoaded();
        String reminderNeededDate = driver.findElement(By.xpath("//label[contains(text(), 'Next Reminder Needed') ]/following-sibling::input"))
                .getAttribute("value");
        String setDate = commonPage.commonFieldRead(rushSetting);
        Assert.assertTrue(commonForm.reminderDateChecker(setDate, reminderNeededDate, rushSetting));

    }

    @Then("I verify the {string} date is the expected value")
    public void iVerifyEmailDate(String emailDate) {
        BaseUtil.pageLoaded();
        String date = driver.findElement(By.xpath("//label[contains(text(), '" + emailDate + "') ]/following-sibling::input"))
                .getAttribute("value");
        String trimmedEmailDate = commonForm.dateTrimmer(date);
        Assert.assertEquals(commonForm.emailSentDateChecker(), trimmedEmailDate, "wrong date found for " + emailDate);
    }

    @Then("I verify the history date is the expected value")
    public void iVerifyHistoryDate() {
        BaseUtil.pageLoaded();
        String expectedDate = commonForm.emailSentDateChecker();
        String trimmedHistoryDate = commonForm.dateTrimmer(headerInfo);
        System.out.println("History Date is: " + trimmedHistoryDate);
        Assert.assertEquals(expectedDate, trimmedHistoryDate, "wrong date found for history. Expected: " + expectedDate + " but found " + trimmedHistoryDate);
    }

    @Then("I verify that the Next Reminder Needed is no longer displayed")
    public void iVerifyTheReminderIsGone() {
        BaseUtil.pageLoaded();
        String reminderNeededDate = driver.findElement(By.xpath("//label[contains(text(), 'Next Reminder Needed') ]/parent::div"))
                .getAttribute("style");

        Assert.assertEquals("display: none;", reminderNeededDate);
    }

    @Then("I verify that the Next Reminder Needed is the placeholder")
    public void iVerifyTheReminderIsPlaceholder() {
        BaseUtil.pageLoaded();
        String reminderNeededDate = driver.findElement(By.xpath("//label[contains(text(), 'Next Reminder Needed') ]/following-sibling::input"))
                .getAttribute("placeholder");

        Assert.assertEquals("Next Reminder Needed", reminderNeededDate);
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

    @And("the {string} field is mandatory")
    public void iVerifyFieldIsMandatory(String field) {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.mandatoryField(field));
    }

    @Then("I verify that there is a limit of 100 characters")
    public void iVerifyFieldHasCharacterLimit() {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.maxLengthVisible() != null, "Could not find error");
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

    @Then("I verify the Task Completed checkbox is not visible")
    public void iVerifyTheCheckboxIsNotVisible() {
        BaseUtil.pageLoaded();
        Assert.assertTrue(!commonForm.taskCompleteChk().isDisplayed());
    }

    @Then("I verify that the confirmation warning appears")
    public void iVerifyTheConfirmationWarning() {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.elementByText("Confirm!").isDisplayed());
        Assert.assertTrue(commonForm.elementByText("Please be sure you want to discard this work order.").isDisplayed());
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

    @Then("I verify the submit error appears")
    public void iVerifyErrorAppears() {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.errorIsVisible().isDisplayed());

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

    @And("I add the Work Order number to the Time Tracking modal")
    public void iAddWOToTimeTracking() {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.commonFieldEnter("Work Order #:", valueStore.get("WO #")));
        System.out.println("Adding Work Order " + valueStore.get("WO #") + " to Time Tracking modal");
    }

    @When("I clear the {string} field")
    public void iClearTheField(String fieldName) {
        System.out.println("Clearing the " + fieldName + " field.");
        commonForm.commonFieldClear(fieldName);
    }

    @When("I enter {string} in the {string} date field with the format {string}")
    public void iEnterInTheDateFieldWithTheFormat(String inputText, String fieldName, String dateFormat) {
        long milliSecondsDate = System.currentTimeMillis();
        long oneDayMilliseconds = 86400000;
        SimpleDateFormat desiredDate = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        String dateTimeString = "not yet set";
        switch (inputText) {
            case "<yesterday>" -> dateTimeString = desiredDate.format(new Date(milliSecondsDate - oneDayMilliseconds));
            case "<today>" -> dateTimeString = desiredDate.format(new Date());
            case "<tomorrow>" -> dateTimeString = desiredDate.format(new Date(milliSecondsDate + oneDayMilliseconds));
            default -> Assert.fail("Date was not in method input.");
        }

        System.out.println("Entering " + dateTimeString + " into " + fieldName + " field.");
        Assert.assertTrue(commonForm.commonFieldEnter(fieldName, dateTimeString),
                "Field labeled " + fieldName + " is not present on page!");
        valueStore.put(fieldName, dateTimeString);
    }


}
