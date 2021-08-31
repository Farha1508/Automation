package Steps;

import Base.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PMASteps extends BaseUtil {

//    private final WebDriverWait wait = new WebDriverWait(driver, 10);

    public PMASteps() {

    }

    @And("I find the edited business")
    public void iFindTheEditedBusiness() {
        String check = valueStore.get("Business Name");
        System.out.println("Business Name in valueStore is " + check);
        WebElement result = commonPage.gridEntry("row 1", "Business Name");
        commonPage.gridHeaderEnter("Business Name", check);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(check))).click();
        commonPage.pageLoaded();
    }

    @Then("I will see that the edits were saved")
    public void iWillSeeThatTheEditsWereSaved() {
        editedValues.forEach((key, value) -> {
            String result = "";
            System.out.println("Checking value for " + key);
            try {
                if (commonPage.commonFieldRead(key).equals(value)) {
                    result = key + " field edit verified as " + value;
//                    System.out.println(key + " field edit verified as " + value);
                }
            } catch (NullPointerException ignored){}
            try {
                if (commonPage.commonDropDownRead(key).equals(value)) {
                    result = key + " Drop down edit verified as " + value;
//                    System.out.println(key + " Drop down edit verified as " + value);
                }
            } catch (NullPointerException ignored){}
            Assert.assertNotEquals(result, "", "Entry does not match edit of " + value);
            System.out.println(result);

        });

    }

    @Then("The new business will be displayed in the grid")
    public void theNewBusinessWillBeDisplayedInTheGrid() throws Exception {
        String check = valueStore.get("Business/Policy Holder Name");
        int gridSize = driver.findElements(By.xpath("//*[@id=\"dtopportunity\"]/tbody/tr")).size();
        if (gridSize <= 9) {
            Assert.assertTrue(driver.findElement(By.linkText(check)).isDisplayed());
        } else {
            commonPage.gridHeaderEnter("Business Name", check);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//*[@id=\"dtopportunity\"]/tbody/tr"), gridSize));
            Assert.assertEquals(commonPage.gridEntry("row 1", "Business Name").getText(), check, check + " not found in grid");
        }

        System.out.println("Found " + check + " in grid");

    }

    @And("Ensure I am on the detail page for that business")
    public void iWillBeOnTheDetailPageForThatBusiness() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("collapseExample")));
        System.out.println("On detail page for " + valueStore.get("Business/Policy Holder Name"));
    }

    @And("I verify the submit button is disabled")
    public void iVerifyTheSubmitButtonIsDisabled() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("oppor_submit")));
        WebElement opporSubmit = driver.findElement(By.id("oppor_submit"));
        System.out.println("submit button disabled status: " + opporSubmit.getAttribute("disabled"));
        if (driver.findElement(By.id("oppor_submit")).getAttribute("disabled").equals("true")) {
            System.out.println("Verified submit button is disabled on opportunity page load");
        } else {
            System.err.println("Opportunity is not disabled on page load");
        }
    }

    @Then("The edits to the opportunity can be saved")
    public void theEditsToTheOpportunityCanBeSaved() throws InterruptedException {
        commonPage.commonButton("Submit");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"jconfirm-buttons\"]")));

        WebElement cButton = driver.findElement(By.xpath("//button[@class=\"btn btn-default\" and text()=\"confirm\"]"));
        js.executeScript("arguments[0].click()", cButton);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("oppor_succ_msg")));
        Assert.assertTrue(driver.findElement(By.id("oppor_succ_msg")).isDisplayed());

        System.out.println("Edits to opportunity successfully saved");
    }

    @Then("The activity will display in the Activities tab of this opportunity")
    public void theActivityWillDisplayInTheActivitiesTabOfThisOpportunity() {
        js.executeScript("arguments[0].click()", driver.findElement(By.id("Actv_submit")));
        js.executeScript("arguments[0].click()", driver.findElement(By.linkText("Activities")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"activity_section_datatables\"]/tbody/tr/td[1]")));
        Assert.assertTrue(driver.findElement(By.linkText(valueStore.get("Activity Type"))).isDisplayed(),
                "Could not find " + valueStore.get("Activity Type") + " activity in Activities grid");
        System.out.println(valueStore.get("Activity Type") + " found in Activities grid");

    }

    @Then("the new note will be displayed in the Notes tab")
    public void theNewNoteWillBeDisplayedInTheNotesTab() {

        try {
            driver.findElement(By.linkText("Notes")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Notes")));
        }

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"notes_datatable_ajax\"]/tbody/tr[1]/td[4]")));
        boolean noteFound = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"" + valueStore.get("Description") + "\"]")));
            noteFound = true;
        } catch (TimeoutException ignored) {

        }


//        String result = commonPage.gridEntry("row 1", "Title").getText();

//        Assert.assertEquals(result, valueStore.get("Title"), valueStore.get("Title") + " note has not been added to Notes grid");
        Assert.assertTrue(noteFound, valueStore.get("Title") + " note has not been added to Notes grid!");
        System.out.println(valueStore.get("Title") + " note is present in Notes grid");
/*
        if(result.equals(valueStore.get("Title"))) {
            System.out.println(valueStore.get("Title") + " note is present in Notes grid");
        } else {
            System.err.println(valueStore.get("Title") + " note has not been added to Notes grid");
        }

 */
    }

    @And("I open the Add Note form")
    public void iOpenTheAddNoteForm() {
        commonPage.commonButton("Add Note");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Add_Notes")));
    }

    @When("I open the Add Business form")
    public void iOpenTheAddBusinessForm() {
        commonPage.commonButton("Add Business");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addBusiness")));
    }

    @And("I open the Business Details form")
    public void iOpenTheBusinessDetailsForm() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Business Details"))).click();
        wait.until(ExpectedConditions.attributeContains(By.id("collapseExample"), "aria-expanded", "true"));
    }

    @And("I open the Add Activity form")
    public void iOpenTheAddActivityForm() {
        commonPage.commonButton("Add Activity");
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addActivity")));
    }

    @And("I open the Add Follow Up form")
    public void iOpenTheAddFollowUpForm() {
        commonPage.commonButton("Add FollowUp");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Add_follow_ups")));
    }

    @And("I open the Add Opportunity form")
    public void iOpenTheAddOpportunityForm() {
        commonPage.commonButton("Add Opportunity");
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addOpportunity")));
    }

    @And("I open the Add Claim form")
    public void iOpenTheAddClaimForm() {
        commonPage.commonButton("Add Claim");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claim_add")));
    }

    @Then("The claim will be displayed in the Claims tab of the opportunity")
    public void theClaimWillBeDisplayedInTheClaimsTabOfTheOpportunity() {
        js.executeScript("document.getElementById('claim_submit').click()");
        js.executeScript("document.getElementById('Claims_sects').click()");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"claims_section_datatables\"]/tbody/tr/td[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=\"" + valueStore.get("Description of Claim") + "\"]")).isDisplayed(),
                "Could not find " + valueStore.get("Description of Claim") + " claim in Claims grid");
        System.out.println(valueStore.get("Description of Claim") + " claim found in Claims grid");
    }

    @And("I open the Add Quote form")
    public void iOpenTheAddQuoteForm() {
        commonPage.commonButton("Add Quote");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteForm")));
    }

    @Then("The quote will be displayed in the Quotes tab")
    public void theQuoteWillBeDisplayedInTheQuotesTab() {
        js.executeScript("document.getElementById('quote_submit').click()");
        js.executeScript("document.getElementById('Quotes_sects').click()");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"quotes_section_datatables\"]/tbody/tr/td[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=\"" + valueStore.get("Carrier") + "\"]")).isDisplayed(),
                "Could not find " + valueStore.get("Carrier") + " quote in Quotes grid");
        System.out.println(valueStore.get("Carrier") + " quote found in Quotes grid");
    }

    @And("I return to the PMA home page")
    public void iReturnToThePMAHomePage() {
        try {
            driver.findElement(By.linkText("Home")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Home")));
        }
        commonPage.pageLoaded();
    }

    @And("I add a quote to this opportunity")
    public void iAddAQuoteToThisOpportunity() {
        commonPage.commonButton("Add Quote");
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteForm")));
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        commonPage.commonDatePick("Quote Date", day.format(new Date()), month.format(new Date()), year.format(new Date()));
        commonPage.commonDropDownSelect("Carrier", "AAA");
        commonPage.commonDropDownSelect("Carrier Type", "Standard (S)");
        commonPage.commonFieldEnter("Quoted Premium", "500");
        commonPage.commonCheckBox("Submitted");
        commonPage.commonCheckBox("Mark as Selected Quote");
        js.executeScript("document.getElementById('quote_submit').click()");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("quoteForm")));

//        commonPage.commonDatePick(day.format(new Date()), month.format(new Date()), year.format(new Date()), "Effective Date");
//        commonPage.commonFieldEnter("Commission", "50");
//        commonPage.commonFieldEnter("Broker Fee", "25");
//        js.executeScript("document.getElementById('oppor_submit').click()");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("oppor_succ_msg")));
        System.out.println("Added quote to " + valueStore.get("Coverage Type") + " opportunity");
    }


    @When("I go to the To Do List section")
    public void iGoToTheToDoListSection() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("To Do List"))).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("To Do List")));
        }

        if (commonPage.pageLoaded()) {
            System.out.println("On To Do List page");
        } else {
            System.out.println("Something didn't load");
        }

    }

    @And("I verify the number of records in the grid match the KPI")
    public void iVerifyTheNumberOfRecordsInTheGridMatchTheKPI(String kpi) {
        List<WebElement> descriptions = driver.findElements(By.className("desc"));
        String item = "";
        for(WebElement element : descriptions) {
            String description = element.getText().replaceAll("\\n", " ");
            if(valueStore.containsKey(description)) {
                item = description;
                element.click();
                commonPage.pageLoaded();
            }
            System.out.println(description);
        }
        String rowNum = Integer.toString(commonPage.gridRecordNumber("Activities"));
        Assert.assertEquals(rowNum, valueStore.get(item), "Rows in grid do not match number of " + item + " KPI");
        System.out.println("Number of records in grid (" +rowNum + ") match the number shown in the "+item+" KPI (" + valueStore.get(item) + ")");
    }
}
