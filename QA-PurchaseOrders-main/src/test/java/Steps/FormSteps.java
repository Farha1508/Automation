package Steps;

import Base.BaseUtil;
import Pages.CommonForm;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class FormSteps extends BaseUtil {

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.commonButton(button), button + " button could not be found on page");
        BaseUtil.pageLoaded();
        System.out.println("Clicking " + button + " button.");
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

        Assert.assertTrue(commonForm.commonFieldEnter(fieldName, trackEntry),
                "Field labeled " + fieldName + " is not present on page!");

        valueStore.put(fieldName, trackEntry);
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
            Assert.assertTrue(commonForm.commonFieldEnter(key, value), "Could not find " + key + " field!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @And("I get the text from the {string} field")
    public void iGetTheTextFromTheField(String field) {
        String fieldText = commonForm.commonFieldRead(field);
        System.out.println("Text currently visible in " + field + " field: " + fieldText);
        valueStore.put(field, fieldText);
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
                }
                value = email;
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

    @And("I click {string} checkbox")
    public void iClickCheckbox(String checkBox) {
        System.out.println("Clicking " + checkBox + " check box");
        Assert.assertTrue(commonForm.commonCheckBoxClick(checkBox), checkBox + " checkbox could not be found!");
    }

    @And("I select the {string} checkbox")
    public void iSelectCheckbox(String checkBox) {
        BaseUtil.pageLoaded();
        System.out.println("Selecting the " + checkBox + " checkbox.");
        if (!commonForm.commonCheckBoxSelected(checkBox)) {
            commonForm.commonCheckBoxClick(checkBox);
            BaseUtil.pageLoaded();
        }
        Assert.assertTrue(commonForm.commonCheckBoxSelected(checkBox), checkBox + " checkbox was not selected OR could not be found!");
        //valueStore.put(checkBox, "true");
    }

    // could possibly combine this with the previous stepdef as "I {string} the {string} checkbox"
    @And("I deselect the {string} checkbox")
    public void iDeselectCheckbox(String checkBox) {
        BaseUtil.pageLoaded();
        System.out.println("Selecting the " + checkBox + " checkbox.");
        if (commonForm.commonCheckBoxSelected(checkBox)) {
            commonForm.commonCheckBoxClick(checkBox);
            BaseUtil.pageLoaded();
        }
        Assert.assertNotNull(commonForm.commonCheckBox(checkBox), checkBox + " checkbox could not be found!");
        Assert.assertFalse(commonForm.commonCheckBoxSelected(checkBox), checkBox + " checkbox was selected, but was expected to be deselected.");
        //valueStore.put(checkBox, "false");
    }

    @Then("The {string} checkbox is {string}")
    public void checkboxSelected(String checkBox, String expectation) {
        // expectation should be "selected" or "deselected" (or "checked" or "unchecked")
        boolean boolExpect = false;
        if (expectation.equalsIgnoreCase("selected") || expectation.equalsIgnoreCase("checked")) {
            boolExpect = true;
        }

        BaseUtil.pageLoaded();
        System.out.println("Checking that the " + checkBox + " checkbox is " + expectation + ".");
        if (commonForm.commonCheckBox(checkBox) != null) {
            Assert.assertEquals(commonForm.commonCheckBoxSelected(checkBox), boolExpect);
        } else {
            Assert.fail(checkBox + " checkbox could not be found!");
        }
    }

    @And("I set the {string} date picker to {int} {string} in the {string}")
    public void iSetTheDatePickerToInThe(String datePicker, int count, String units, String direction, List<Map<String, String>> table) {
        Map<String, String> tCheck = table.get(0);
        boolean hasTime = tCheck.get("Has Time").equalsIgnoreCase("yes");
        commonForm.commonDateShift(datePicker, count, units, direction, hasTime);
    }

    @And("I upload an attachment")
    public void iUploadAnAttachment() {
        pageLoaded();
        // Creating a variable to check for the number of open tabs at the start of the operation
        int initWindows = driver.getWindowHandles().size();
        // Checks for three different ways to find the button to add attachments
        if (!commonForm.commonButton("Add Attachments") && !commonForm.commonButton("Add Attachment")) {
            driver.findElement(By.linkText("Add Attachments")).click();
        }
        pageLoaded();
        Set<String> handles = driver.getWindowHandles();
        // Checking if a new tab was opened after the add attachment button was clicked
        // If true, then email2db attachment process is used
        if (handles.size() > initWindows) {
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
            wait.until(ExpectedConditions.numberOfWindowsToBe(initWindows));
            // If statement to check if an attachment modal pop-up is displayed
        } else if (driver.findElements(By.id("addAttachment")).size() >= 1) {
            driver.findElement(By.id("attach_file")).sendKeys(attachLocation);
            driver.findElement(By.id("attachment_submit")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addAttachment")));
            // Final statement assumes a file browser dialog is displayed
        } else {
            driver.findElement(By.id("filename")).sendKeys(attachLocation);
        }
        pageLoaded();
    }

    @Then("The file will be displayed in the Attachments grid")
    public void theFileWillBeDisplayedInTheAttachmentsGrid() {
        // Boolean for verification at end of process
        boolean result = false;
        // Initializing the download folder for when file downloads are used for verification
        File downloads = new File(dLFolder);
        // Getting the number of files currently in the download folder
        int fileNum = downloads.listFiles().length;
        // Creating a variable to check for the number of open tabs at the start of the operation
        int initWindows = driver.getWindowHandles().size();
        commonForm.commonLinkClick("Attachments");
        pageLoaded();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(attachName))).click();


        System.out.println("File " + attachName + " found in Attachment grid");

        Set<String> handles = driver.getWindowHandles();
        // Checking if the attachment link opened a new tab
        // If true, it will use the URL of the new tab to determine if the attachment was successfully uploaded
        if (handles.size() > initWindows) {
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
        } else {
            long startTime = System.currentTimeMillis();
            while (downloads.listFiles().length == fileNum && (System.currentTimeMillis() - startTime) < 30000) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

                }
            }
            if (downloads.listFiles().length > fileNum) {
                for (File file : downloads.listFiles()) {
                    System.out.println("Filename: " + file.getName());
                    if (file.getName().contains(attachName)) {
                        System.out.println(attachName + " downloaded");
                        file.delete();
                    }
                }
            }


        }
    }
}
