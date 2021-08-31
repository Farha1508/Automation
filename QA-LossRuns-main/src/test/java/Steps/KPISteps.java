package Steps;

import Base.BaseUtil;
import Pages.GridFunctions;
import Pages.KPITrackingPage;
import Pages.LossRunsHappyPathPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

public class KPISteps extends BaseUtil {
    private final GridFunctions gridFunctions = new GridFunctions(driver);

    public KPISteps() {

    }

    private final KPITrackingPage kpitrackinpage = new KPITrackingPage(driver);
    private final LossRunsHappyPathPage lossrunspage = new LossRunsHappyPathPage(driver);

    @Then("I get the new WO count")
    public void iGetTheNewWOCount() {
        WO_number = kpitrackinpage.getWonumber();
    }

    @Then("Enter Work Order Id under WO input box")
    public void enterWorkOrderIdUnderWOInputBox() throws InterruptedException {
        kpitrackinpage.searchWorkOrder(WO_number);
    }

    @Then("click on WO Id")
    public void clickOnWOId() throws InterruptedException {
        kpitrackinpage.OpenWO();
    }

    @Then("Set the other conditions as per {string} KPI")
    public void setTheConditionsAsPerKPI(String kpi) throws Exception {
        kpitrackinpage.setKPIConditions(kpi);

    }

    @Then("Click on {string} tab")
    public void clickOnTab(String tab) throws IOException {
        lossrunspage.details_Page_Tabs(tab);

    }

    @Then("Select {string} as Carrier Name")
    public void selectAsAndOrderedDate(String value) {
        System.out.println("Selecting " + value + " from " + "Carrier Name" + " drop down");
        Assert.assertTrue(lossrunspage.carrierName(value , currentTab), "Could not find " + value + " drop down");
    }

    @And("I set the date in the {string} date picker and set Next Reminder date to {string} Date")
    public void iSetTheDateInTheDatePickerAndSetNextReminderDateToDate(String fieldName, String day) throws Exception {
        System.out.println("Selecting date from " + fieldName + " calendar");
        kpitrackinpage.orderedDate(fieldName,day);
    }


    @Then("I Click on {string} kpi")
    public void clickOnKpi(String kpiname) throws InterruptedException {
        kpitrackinpage.KPI(kpiname);
        System.out.println("Records for " + kpiname + " are displayed under Open tab");
        Thread.sleep(5000);
    }

    @And("I get the number of records {string} kpi")
    public void iGetTheNumberOfRecordsKpi(String kpiname) throws Exception {
        gridFunctions.gridTab("Open");
        switch (kpiname.toLowerCase()) {
            case "loss run requests":
                LRR_Count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + LRR_Count);
                break;
            case "today's reminders":
                TR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + TR_count);
                break;
            case "past due reminders":
                PDR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + PDR_count);
                break;
            case "reports reviewed":
                RR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + RR_count);
                break;
            case "pending carrier response":
                PCR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + PCR_count);
                break;
            case "project reminder":
                PR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + PR_count);
                break;
            case "carrier reminders needed":
                CRN_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + CRN_count);
            case "on hold requests":
                OHR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + OHR_count);
                break;
            default:
                throw new Exception("No KPI available for " + kpiname.toUpperCase() + " please use valid KPI name");
        }
    }

    @Then("I click on Reset button")
    public void iClickOnResetButton() throws InterruptedException, IOException {
        lossrunspage.resetButton();
        Thread.sleep(3000);
    }

    @And("I validate {string} kpi count is incremented by {int}")
    public void iValidateNumberOfRecordsIsIncrementedBy(String kpiname, int num) throws Exception {
        switch (kpiname.toLowerCase()) {
            case "loss run requests":
                int LRR_Count_new = gridFunctions.gridRecordNumber("Open");
                Assert.assertEquals(LRR_Count_new - LRR_Count, num);
                System.out.println("Number of records in " + kpiname + " kpi is incremented by :" + num);
                break;
            case "today's reminders":
                TR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + TR_count);
                break;
            case "past due reminders":
                PDR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + PDR_count);
                break;
            case "reports reviewed":
                RR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + RR_count);
                break;
            case "pending carrier response":
                PCR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + PCR_count);
                break;
            case "project reminder":
                PR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + PR_count);
                break;
            case "carrier reminders needed":
                CRN_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + CRN_count);
            case "on hold requests":
                OHR_count = gridFunctions.gridRecordNumber("Open");
                System.out.println("Number of records in " + kpiname + " kpi: " + OHR_count);
                break;
            default:
                throw new Exception("No KPI available for " + kpiname.toUpperCase() + " please use valid KPI name");
        }
    }

    @And("Enter Work order Id in Global search field and open")
    public void enterInGlobalSearchFieldAndOpen() throws InterruptedException {
        BaseUtil.pageLoaded();
        //lossrunspage.GlobalSearch("39324");
        System.out.println("WO ID = " + WO_number);
        lossrunspage.GlobalSearch(WO_number);
        Thread.sleep(3000);
    }

    @Then("Set Insured Reminder date field")
    public void setInsuredReminderDateField() throws Exception {
        kpitrackinpage.insuredDate();
    }

    @Then("Select {string} checkbox")
    public void selectCheckbox(String checkbox) {
        kpitrackinpage.reviewedCompletedCheckbox(checkbox);
    }
}
