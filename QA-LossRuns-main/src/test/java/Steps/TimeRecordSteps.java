package Steps;

import Base.BaseUtil;
import Pages.GridFunctions;
import Pages.LossRunsHappyPathPage;
import Pages.TimeRecordsFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import java.util.List;

public class TimeRecordSteps extends BaseUtil {
    private final BaseUtil base;

    public TimeRecordSteps(BaseUtil base) {
        this.base = base;
    }

    private final TimeRecordsFunctions timeRecordsFunctions = new TimeRecordsFunctions(driver);
    private final GridFunctions gridFunctions = new GridFunctions(driver);

    @When("I click on {string} Tab")
    public void iClickOnTimeRecordTab(String tabname) throws Throwable {
        timeRecordsFunctions.timeRecordsPages(tabname);
        BaseUtil.pageLoaded();
    }

    @Then("I should be redirected to {string} page")
    public void iShouldBeRedirectedToTimeRecordPage(String pagename) throws Throwable {
        currentTab = pagename;
        currentPage = pagename;
        timeRecordsFunctions.verifyPage(currentPage);
        Thread.sleep(3000);
    }

    @And("Verify the following headers are present on page")
    public void verifyTheFollowingHeadersArePresentOnPage(List<String> table) {
        for (String header : table) {
            Assert.assertTrue(timeRecordsFunctions.HeaderFind(currentPage, header),
                    header + " header not visible in " + currentPage + " grid!");
            System.out.println(header + " header verified on grid");
        }
    }


    @Then("I click on Run button")
    public void iClickOnRunButton() {
        timeRecordsFunctions.runBttn();
    }

    @Then("I double click on {string} column")
    public void iDoubleClickOnColumn(String column) {
        timeRecordsFunctions.columnClick(column);
    }

    @And("If the {string} modal is displayed, dismiss it")
    public void ifTheModalIsDisplayedDismissIt(String modalTitle) {
        System.out.println("Checking if the " + modalTitle + " modal is displayed.");
        try {
            driver.findElement(By.xpath("//span[@class=\"jconfirm-title\" and contains(text(), \"" + modalTitle + "\")]"));
            // Todo: check contents here to cover C11683 in TimeRecordsAdmin
            // Todo: buttons exist "No" and "Yes"
            // Todo: text is correct "Time exceeded 2 hours. Are you still working on this request"
            System.out.println("Dismissing the " + modalTitle + " modal.");
            driver.findElement(By.xpath("//button[contains(text(), \"NO\")]")).click();
            // Todo: verify modal dismissed
        } catch (NoSuchElementException e) {
            System.out.println("No " + modalTitle + " alert was displayed.");
        }

    }

    @And("The {string} link {string} displayed")
    public void theLinkDisplayed(String linkText, String expectation) {
        System.out.println("Checking if " + linkText + " link exists.");
        if (expectation.equalsIgnoreCase("is not")) {
            Assert.assertFalse(timeRecordsFunctions.linkDisplayed(linkText));
        } else {
            Assert.assertTrue(timeRecordsFunctions.linkDisplayed(linkText));
        }
    }
}

