package Steps;

import Base.BaseUtil;
import Pages.Login;
import Pages.TimeTracking;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeTrackingSteps extends BaseUtil {

    TimeTracking timeTracking = new TimeTracking(driver, js);

    public TimeTrackingSteps() {
        login = new Login(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Then("The Time Tracking modal {string} displayed")
    public void timeTrackingModalDisplayed(String displayed) {
        if (displayed.equalsIgnoreCase("is not")) {
            Assert.assertFalse(timeTracking.timeTrackingModalDisplayed(false));
        } else {
            Assert.assertTrue(timeTracking.timeTrackingModalDisplayed(true));
        }
    }

    @And("I open the {string} page")
    public void timeTrackingOpen(String timePage) {
        commonPage.pageLoaded();

    }

    @And("I set the {string} date picker to {int} month(s) ago")
    public void iSetTheDatePickerToMonthsAgo(String datePicker, int monthsAgo) {
        System.out.println("Setting the " + datePicker + " to " + monthsAgo + " months ago.");
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Map<String, String> dateTime = new HashMap<>();

        long milliSecondsDate = System.currentTimeMillis();
        long oneMonthMilliseconds = 2592000000L;

        dateTime.put("Day", day.format(new Date(milliSecondsDate - monthsAgo * oneMonthMilliseconds)));
        dateTime.put("Month", month.format(new Date(milliSecondsDate - monthsAgo * oneMonthMilliseconds)));
        dateTime.put("Year", year.format(new Date(milliSecondsDate - monthsAgo * oneMonthMilliseconds)));
        dateTime.put("Time", "12:00");
        dateTime.put("AM / PM", "AM");
        if (!commonForm.commonDatePick(datePicker, dateTime.get("Day"), dateTime.get("Month"), dateTime.get("Year"))) {
            commonForm.commonDatePick(datePicker, dateTime);
        }
    }

    @Then("I see an error warning for the {string} field")
    public void iSeeAnErrorWarningForTheField(String fieldName) {
        Assert.assertTrue(woT.errorWarningDisplayed(fieldName));
    }

    @Then("The the {string} field is set to {string}")
    public void theTheFieldIsSetTo(String fieldName, String day) throws ParseException {
        System.out.println("Setting the " + fieldName + " field to " + day);
        DateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");

        Date actualDate = dateOnly.parse(commonForm.commonFieldRead(fieldName));
        Date expectedDate;

        if (day.equals("tomorrow")) {
            long milliSecondsDate = System.currentTimeMillis();
            long oneDayMilliseconds = 86400000;
            expectedDate = new Date(milliSecondsDate + oneDayMilliseconds);
        } else {
            expectedDate = new Date();
        }
        expectedDate = dateOnly.parse(dateOnly.format(expectedDate));// Remove the time component, setting it to midnight.

        Assert.assertEquals(expectedDate, actualDate);
    }

    @And("I edit the top Time Rec row")
    public void iEditTheTopTimeRecRow() {
        System.out.println("Editing the top row in " + currentTab);
        woT.clickTopTimeRecEdit();
    }

    @Then("I am unable to select a future {string}")
    public void iAmUnableToSelectAFuture(String datePicker) {
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Map<String, String> dateTime = new HashMap<>();

        long milliSecondsDate = System.currentTimeMillis();
        long oneMonthMilliseconds = 2592000000L;

        dateTime.put("Day", day.format(new Date(milliSecondsDate + oneMonthMilliseconds)));
        dateTime.put("Month", month.format(new Date(milliSecondsDate + oneMonthMilliseconds)));
        dateTime.put("Year", year.format(new Date(milliSecondsDate + oneMonthMilliseconds)));
        dateTime.put("Time", "12:00");
        dateTime.put("AM / PM", "AM");

        Assert.assertTrue(woT.cannotSelectDate(datePicker, dateTime));
//        if (!pcPage.cannotSelectDate(datePicker, dateTime.get("Day"), dateTime.get("Month"), dateTime.get("Year"))) {
//
//        }
    }

    @Then("The the {string} modal is displayed")
    public void theTheModalIsDisplayed(String modal) {
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'modal-dialog')]/descendant::*[text()=\"" + modal + "\"]")).isDisplayed());
    }
 }
