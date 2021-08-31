package Steps;


import Base.BaseUtil;
import Pages.Loginpage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.De;
import gherkin.lexer.Th;
import javafx.scene.chart.PieChart;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

public class LoginStep extends BaseUtil {

    private BaseUtil base;

    public LoginStep(BaseUtil base) {
        this.base = base;
    }

    @Given("^I navigate to login page$")
    public void iNavigateToLoginPage() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Navigates to Login page \n");
        base.driver.get("https://dev.patracorp.net/auth");
        base.driver.manage().window().maximize();
        Thread.sleep(10000);
    }

    @And("^I enter login details$")
    public void iEnterLoginDetails(DataTable Table)throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering email address and password \n");
        //Create ArrayList
        List<SignIn> users = new ArrayList<SignIn>();
        //Stores all users
        users = Table.asList(SignIn.class);
        Loginpage page = new Loginpage(base.driver);
        for (SignIn user : users) {
            page.login(user.email, user.password);
        }
    }

    @And("^I click on Sign In button$")
    public void iClickOnSignInButton()throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Sign In button \n");
        Loginpage page = new Loginpage(base.driver);
        page.signInButton();
    }

    @And("^I clicks Home button$")
    public void iClicksHomeButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("\n Clicks Home button \n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.home();
    }
    /* Business Page code */
    @And("^user clicks add business button$")
    public void userClicksAddBusinessButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.addBusinessClick();
    }

    @And("^user enters the following business details$")
    public void userEntersTheFollowingBusinessDetails(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<AddBusiness> businesses = new ArrayList<AddBusiness>();
        //Store all the users
        businesses = Table.asList(AddBusiness.class);
        Loginpage page = new Loginpage(base.driver);
        for (AddBusiness business : businesses) {
            Thread.sleep(3000);
            page.addBusiness(business.brand, business.subbrand, business.businessname, business.businessclass, business.clientid, business.broker, business.referrername,
                    business.referringcompany, business.prospectorigin, business.leadassignedby, business.needbydate, business.yearsinbusiness, business.experience,
                    business.website, business.typeoforganization, business.ifotherpleasespecify, business.descriptionofoperations, business.estannualgrosssales,
                    business.estannualpayroll, business.noofactiveowners, business.fte, business.pte, business.fein, business.coveragetype);
        }
    }

    @And("^user clicks submit button$")
    public void userClicksSubmitButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        page.addbusinesssubmit();
        Thread.sleep(6000);
    }

    @And("^user clicks Reset button for business$")
    public void userClicksResetButtonForBusiness() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On Reset");
        Loginpage page = new Loginpage(base.driver);
        page.bussreset();
        Thread.sleep(4000);
    }

    @And("^user searches with the following$")
    public void userSearchesWithTheFollowing(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<BusinessNameSearch> businessnames = new ArrayList<BusinessNameSearch>();
        //Store all the users
        businessnames = Table.asList(BusinessNameSearch.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(15000);
        for (BusinessNameSearch businessnamesearch : businessnames) {
            page.businessnamesearch(businessnamesearch.businessname);
            //base.driver.findElement(By.xpath("//*th[@id=’th-BusinessNameBus’]//following::input"));
        }
    }

    @And("^user selects business in business grid$")
    public void userSelectsBusiness() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click selected business record");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(25000);
        page.selectbusiness1();
    }

    @And("^clicks on Business Details link to edit$")
    public void clicksOnBusinessDetailsLinkToEdit() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click Business Detail link to edit");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.businessdetails();
    }

    @And("^I edit add contact under business details fields$")
    public void iEditAddContactUnderBusinessDetailsFields() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("add contact under Business Detail fields");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.addcontact();
    }

    @And("^I click on Edit symbol for Add Contact$")
    public void iClickOnEditSymbolForAddContact() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Edit Contact symbol");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.editcontact();
        Thread.sleep(5000);
        page.editcontactfields();
        Thread.sleep(5000);
    }

    @And("^I click on Add Addresses button$")
    public void iClickOnAddAddressesButton(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<AddAddresses> addaddresses = new ArrayList<AddAddresses>();
        //Store all the users
        addaddresses = Table.asList(AddAddresses.class);
        System.out.println("add addresses under Business Detail fields");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.addaddressesclick();
        for (AddAddresses addAddress : addaddresses) {
            page.addaddresses(addAddress.addresstype, addAddress.city, addAddress.street1, addAddress.street2, addAddress.zip,
                    addAddress.region, addAddress.country);
        }
        Thread.sleep(5000);
    }

    @And("^I click on Edit symbol for add address$")
    public void iClickOnEditSymbolForAddAddress() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Edit Address symbol");
        Loginpage page = new Loginpage(base.driver);
        page.editaddress();
        Thread.sleep(5000);
    }

    @And("^I enter data in update address fields$")
    public void iEnterDataInUpdateAddressFields() throws InterruptedException {
        System.out.println("Enters Update Address");
        Loginpage page = new Loginpage(base.driver);
        page.updateaddress();
        Thread.sleep(5000);
        page.addbusinesssubmit();
        Thread.sleep(2000);
        page.businessdetails();
    }

    @And("^user clicks business add activity button$")
    public void userClicksBusinessAddActivityButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click business add activity button");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(8000);
        page.businessaddactivityclick();
    }

    @And("^user adds the following activity details$")
    public void userAddsTheFollowingActivityDetails(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<BusinessAddActivity> activities = new ArrayList<BusinessAddActivity>();
        //Store all the users
        activities = Table.asList(BusinessAddActivity.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        for (BusinessAddActivity activity : activities) {
            page.businessaddactivity(activity.activitytype, activity.activitystatus, activity.departmentassigned, activity.assignedto, activity.needbydate, activity.activityperformed, activity.activitycompleted, activity.followuprequired, activity.reviewdesired, activity.description);
        }
    }

    @And("^user clicks business add activity submit button$")
    public void userClicksBusinessAddActivitySubmitButton() {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        page.businessaddactivitysubmit();
    }

    @Then("^user should see the record under business activities tab$")
    public void userShouldSeeTheRecordUnderBusinessActivitiesTab() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(6000);
        page.selectactivitiestab();
    }

    @And("^user clicks business add note button$")
    public void userClicksBusinessAddNoteButton() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Add Note");
        Loginpage page = new Loginpage(base.driver);
        page.businessaddnoteclick();
    }
    @And("^user enter the following note details$")
    public void userEnterTheFollowingNoteDetails(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<BusinessAddNote> notes = new ArrayList<BusinessAddNote>();
        //Store all the users
        notes = Table.asList(BusinessAddNote.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        for (BusinessAddNote note : notes) {
            page.businessaddnote(note.title, note.description);
        }
        System.out.println("Enters Add Note details");
    }

    @And("^user clicks business add note submit button$")
    public void userClicksBusinessAddNoteSubmitButton() {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        page.businessaddnotesubmit();
    }

    @And("^user should see the record under business notes tab$")
    public void userShouldSeeTheRecordUnderBusinessNotesTab() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(6000);
        page.businessnotestab();
    }

    @And("^user clicks business add opportunity button$")
    public void userClicksBusinessAddOpportunityButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Add Opportunity");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(1000);
        page.businessaddopportunityclick();
    }

    @And("^user enters the following opportunity details$")
    public void userEntersTheFollowingOpportunityDetails(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<BusinessAddOpportunity> opportunities = new ArrayList<BusinessAddOpportunity>();
        //Store all the users
        opportunities = Table.asList(BusinessAddOpportunity.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (BusinessAddOpportunity opportunity : opportunities) {
            page.businessaddopportunity(opportunity.status, opportunity.coveragetype, opportunity.needbydate, opportunity.opportunityreceiveddate, opportunity.departmentassigned, opportunity.assignedto, opportunity.opportunityassigneddate, opportunity.currentinsurancecarrier, opportunity.renewaldate, opportunity.currentpremium, opportunity.priorclaims, opportunity.origin, opportunity.describepriorclaims);
        }
        System.out.println("Enters Add Opportunity details");
    }

    @And("^user clicks business add opportunity submit button$")
    public void userClicksBusinessAddOpportunitySubmitButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(1000);
        page.businessaddopportunitysubmit();
    }

    @And("^click on opportunities tab$")
    public void clickOnOpportunitiesTab() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.clickopportunitiestab();
        System.out.println("Clicks Opportunity tab");
    }
    @And("^user selects coverage under opportunities tab$")
    public void userSelectsCoverageUnderOpportunitiesTab() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(1000);
        page.selectcoverageunderopportunitiestab();
    }

    @And("^user edits the following opportunity details$")
    public void userEditsTheFollowingOpportunityDetails(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<BusinessOpportunityDetail> details = new ArrayList<BusinessOpportunityDetail>();
        //Store all the users
        details = Table.asList(BusinessOpportunityDetail.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (BusinessOpportunityDetail detail : details) {
            page.editopportunitydetail(detail.status, detail.coveragetype, detail.wheniscoverageneeded, detail.needbydate, detail.opportunityreceiveddate, detail.broker, detail.assigneddate,
                    detail.currentinsurancecarrier, detail.renewaldate, detail.currentpremium, detail.accesslevel, detail.servicelevel, detail.prospectorigin, detail.businesstype, detail.accountmanager, detail.effectivedate, detail.commission, detail.brokerfee);
        }
    }

    @And("^user clicks opportunity detail submit button$")
    public void userClicksOpportunityDetailSubmitButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(1000);
        page.opportunitydetailsubmit();
    }
    /* Opportunity page code starts */
    @And("^I click Reset button$")
    public void iClickResetButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On Reset");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.reset();
    }

    @Then("^I see business page of that record$")
    public void iSeeBusinessPageOfThatRecord() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Opens business page record ");
    }

    @And("^I click on Coverage name$")
    public void iClickOnCoverageName() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks on coverage type selected");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickopprecord();
        Thread.sleep(2000);
    }

    @And("^I enter values in Detail section under Opportunity page$")
    public void iEnterValuesInDetailSectionUnderOpportunityPage(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering details values");
        //Create ArrayList
        List<Detail> users = new ArrayList<Detail>();
        users = Table.asList(Detail.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(10000);
        for (Detail user : users) {
            page.oppdetail(user.status, user.coveragetype, user.wheniscoverageneeded, user.needbydate, user.opportunityreceiveddate,
                    user.departmentassigned, user.assignedtoopportunity, user.assigneddate, user.currentinsurancecarrier,
                    user.renewaldate, user.currentpremium, user.accesslevel, user.quotedate, user.quotedcarrier);
        }
    }

    @And("^I click on Add Activity section$")
    public void iClickOnAddActivitySection(DataTable Table) throws InterruptedException {
        List<AddActivity> activities = new ArrayList<AddActivity>();
        activities = Table.asList(AddActivity.class);
        Loginpage page = new Loginpage(base.driver);
        page.ClickAddActivity();
        Thread.sleep(2000);
        for (AddActivity activity : activities) {
            page.addactivity(activity.activitytype, activity.activitystatus, activity.departmentassigned, activity.assignedto, activity.needbydate ,activity.reviewdesired, activity.followupreq, activity.description);
        }
    }

    @And("^I click on Add Note section$")
    public void iClickOnAddNoteSection(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Add Note");
        List<AddNote> users = new ArrayList<AddNote>();
        users = Table.asList(AddNote.class);
        Loginpage page = new Loginpage(base.driver);
        page.ClickAddNote();
        Thread.sleep(2000);
        for (AddNote user : users) {
            page.addnote(user.title, user.description);
        }
    }

    @And("^I click on Add Claim section$")
    public void iClickOnAddClaimSection(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Add Claim");
        List<AddClaim> users = new ArrayList<AddClaim>();
        users = Table.asList(AddClaim.class);
        Loginpage page = new Loginpage(base.driver);
        page.ClickAddClaim();
        Thread.sleep(4000);
        for (AddClaim user : users) {
            page.addclaim(user.claimdate, user.claimstatus, user.claimamount, user.descriptionofclaim);
        }
    }

    @And("^I click on Add Quote section$")
    public void iClickOnAddQuoteSection(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Add Quote");
        List<AddQuote> users = new ArrayList<AddQuote>();
        users = Table.asList(AddQuote.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(4000);
        page.ClickAddQuote();
        Thread.sleep(6000);
        for (AddQuote user : users) {
            page.addquote(user.quotedate, user.carrier, user.quotedpremium, user.carrierdeclined, user.declinedreason);
        }
    }

    @Then("^click on Print PDF button$")
    public void clickOnPrintPDFButton() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Opens print pdf page");
        Loginpage page = new Loginpage(base.driver);
        page.printpdf();
        System.out.println("End of opportunity page");
        String currentWindow = base.driver.getWindowHandle();  //Switching frames
        base.driver.switchTo().window(currentWindow);
    }

    @And("^I click on History tab$")
    public void iClickOnHistoryTab() throws InterruptedException{
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks History tab grid");
        Loginpage page = new Loginpage(base.driver);
        page.historytab();
        Thread.sleep(4000);
    }

    @And("^I click on To Do List link$")
    public void iClickOnToDoListButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks To Do List button \n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.todolist();
    }

    @And("^I click record under Activity Type column$")
    public void iClickRecordUnderActivityTypeColumn() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(" select Activity type record");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(4000);
        page.activities();
        Thread.sleep(4000);
    }

    @And("^I select activity type record$")
    public void iSelectActivityTypeRecord() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(" Clicks selecting Activity type record \n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(12000);
        page.activitytype();
    }

    @And("^I enter values in Activity detail section under Activity page$")
    public void iEnterValuesInActivityDetailSectionUnderActivityPage(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering values in To Do Activity detail");
        List<ActivityDetail> users = new ArrayList<ActivityDetail>();
        users = Table.asList(ActivityDetail.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(8000);
        for (ActivityDetail user : users) {
            page.activitydetail(user.activitytype, user.activitystatus, user.departmentassigned, user.assignedto, user.needbydate, user.activityperformed, user.activitycompleted,
                    user.reviewdesired, user.followuprequired, user.numberoffollowups, user.followupfrequency, user.finalfollowup, user.description);
        }
        Thread.sleep(5000);
    }

    @And("^I click on Add Note under To Do List page$")
    public void iClickOnAddNoteUnderToDoListPage(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering values in To Do Add Note");
        List<ToDoAddNote> users = new ArrayList<ToDoAddNote>();
        users = Table.asList(ToDoAddNote.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(7000);
        page.clicktodoaddnote();
        Thread.sleep(2000);
        for (ToDoAddNote user : users) {
            page.todoaddnote(user.title, user.description);
        }
    }

    @And("^I click on Add Claim under To Do List page$")
    public void iClickOnAddClaimUnderToDoListPage(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering values in To Do Add Claim");
        List<ToDoAddClaim> users = new ArrayList<ToDoAddClaim>();
        users = Table.asList(ToDoAddClaim.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.clicktodoaddclaim();
        for (ToDoAddClaim user : users) {
            page.todoaddclaim(user.claimdate, user.claimstatus, user.claimamount, user.descriptionofclaim);
        }
    }

    @And("^I click on Add Quote under To Do List page$")
    public void iClickOnAddQuoteUnderToDoListPage(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering values in To Do Add Quote");
        List<ToDoAddQuote> users = new ArrayList<ToDoAddQuote>();
        users = Table.asList(ToDoAddQuote.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
      //  page.ClickToDoAddQuote();
        for (ToDoAddQuote user : users) {
            page.todoaddquote(user.quotedate, user.carrier, user.quotedpremium, user.carrierdeclined, user.declinedreason);
        }
        System.out.println("Prints PDF");
    }

    @And("^user clicks Home button$")
    public void userClicksHomeButton() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Global search value");
    }

    @And("^user searches with the following global value$")
    public void userSearchesWithTheFollowingGlobalValue(DataTable Table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Global Search functionality \n ");
        List<GlobalSearch> keywords = new ArrayList<GlobalSearch>();
        keywords = Table.asList(GlobalSearch.class);
        Loginpage page = new Loginpage(base.driver);
        for (GlobalSearch keyword : keywords) {
            page.globalsearch(keyword.globalsearch);
        }
        Thread.sleep(8000);
    }

    @And("^user clicks the first link$")
    public void userClicksTheFirstLink() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicking on Global Search value Anusha Testing");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(4000);
        page.globalsearchlinkselect();
    }

    @And("^finally clicking on Reports button$")
    public void finallyClickingOnReportsButton() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click on Reports button");
        Loginpage page = new Loginpage(base.driver);
        page.reportbutton();
        String currentWindow = base.driver.getWindowHandle();  //Switching frames
        base.driver.switchTo().window(currentWindow);
    }

    @Then("^I sort the fileds under Opportunity grid$")
    public void iSortTheFiledsUnderOpportunityGrid() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Column Sorting for fields under opportunity tab ");
        Loginpage page = new Loginpage(base.driver);
        page.sortcoverage();
        Thread.sleep(6000);
        page.sortcoverage();
        Thread.sleep(8000);
        page.sortbusinessname();
        Thread.sleep(8000);
        page.sortbusinessname();
        Thread.sleep(8000);
        page.sortclass();
        Thread.sleep(8000);
        page.sortclass();
        Thread.sleep(4000);
    }

    @Then("^I Click on Business and opportunity tabs and sort columns there$")
    public void iClickOnBusinessAndpprtunityTabsAndSortColumnsThere() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Column Sorting for fields under business tab ");
        Loginpage page = new Loginpage(base.driver);
        page.businessgridclick();
        Thread.sleep(8000);
        page.sortbbusinessname();
        Thread.sleep(8000);
        page.sortbbrandname();
        Thread.sleep(8000);
        page.sortbroker();
        Thread.sleep(8000);
    }

    @Then("^I Click on Logout$")
    public void iClickOnLogout() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Logout");
        Loginpage page = new Loginpage(base.driver);
        page.clicklogouticon();
        Thread.sleep(8000);
        page.logout();
        Thread.sleep(8000);
    }

    @And("^I select one of the KPI My New Opportunities$")
    public void iSelectOneOfTheKPIMyNewOpportunities() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("selecting one KPI");
        Loginpage page = new Loginpage(base.driver);
        page.mycommissionthismonth();
        Thread.sleep(5000);
        page.myopportunities();
        Thread.sleep(5000);
        page.myboundopportunities();
        Thread.sleep(5000);
        page.mynewoppportunities();
        Thread.sleep(5000);
    }

    @And("^I Click on it business tab$")
    public void iClickOnItBusinessTab() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicking business tab");
        Loginpage page = new Loginpage(base.driver);
        page.businessgridclick();
        Thread.sleep(5000);
    }

    @And("^I Click on opportunity tab$")
    public void iClickOnOpportunityTab() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicking opportunity tab");
        Loginpage page = new Loginpage(base.driver);
        page.opportunitytab();
        Thread.sleep(5000);
    }

    @Then("^I sort the fileds under Opportunity tab$")
    public void iSortTheFiledsUnderOpportunityTab() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I click on View Pdf$")
    public void iClickOnViewPdf() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicking View pdf");
        Loginpage page = new Loginpage(base.driver);
        page.viewPDF();
        String currentWindow = base.driver.getWindowHandle();  //Switching frames
        base.driver.switchTo().window(currentWindow);
    }

    @And("^user selects opportunity detail status as bound or proposed$")
    public void userSelectsOpportunityDetailStatusAsBoundOrProposed(DataTable table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<OpportunityDetailStatusAsBoundOrProposed> statuses = new ArrayList<OpportunityDetailStatusAsBoundOrProposed>();
        statuses = table.asList(OpportunityDetailStatusAsBoundOrProposed.class);
        Loginpage page = new Loginpage(base.driver);
        System.out.println("Selecting status as bound");
        for (OpportunityDetailStatusAsBoundOrProposed status : statuses) {
            page.editopportunitydetailstatusasboundorproposed(status.status);
        }
        Thread.sleep(5000);
    }

    @And("^user adds quote with the following values if there is no quote$")
    public void userAddsQuoteWithTheFollowingValuesIfThereIsNoQuote(DataTable table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Add quote pop-up");
        List<OpportunityDetailAddQuote> quotes = new ArrayList<OpportunityDetailAddQuote>();
        quotes = table.asList(OpportunityDetailAddQuote.class);
        Thread.sleep(4000);
        Loginpage page = new Loginpage(base.driver);
        for (OpportunityDetailAddQuote quote : quotes) {
            page.editopportunitydetailaddquote(quote.quotedate, quote.carrier, quote.quotedpremium);
        }
        Thread.sleep(4000);
    }

    @And("^user clicks add quote submit button$")
    public void userClicksAddQuoteSubmitButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Add quote submit");
        Loginpage page = new Loginpage(base.driver);
        page.opportunitydetailaddquotesubmit();
        Thread.sleep(6000);
    }

    @And("^user selects a quote by clicking mark selected button$")
    public void userSelectsAQuoteByClickingMarkSelectedButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Mark selected button");
        Loginpage page = new Loginpage(base.driver);
        page.opportunityquotemarkselected();
        Thread.sleep(6000);
    }

    @And("^user clicks confirm button$")
    public void userClicksConfirmButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Confirm button");
        Loginpage page = new Loginpage(base.driver);
        page.opportunityquotemarkselectedconfirm();
        Thread.sleep(6000);
    }

    @And("^user enters the mandatory fields in opportunity detail$")
    public void userEntersTheMandatoryFieldsInOpportunityDetail(DataTable table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("enters the mandatory fields in opportunity detail");
        Loginpage page = new Loginpage(base.driver);
        List<MandatoryinOpportunityDetail> mandatoryfields = new ArrayList<MandatoryinOpportunityDetail>();
        mandatoryfields = table.asList(MandatoryinOpportunityDetail.class);
        Thread.sleep(4000);
        for (MandatoryinOpportunityDetail mandatoryfield : mandatoryfields) {
            page.mandatoryfieldsinopportunitydetail(mandatoryfield.effectivedate, mandatoryfield.quotepremium, mandatoryfield.commission, mandatoryfield.brokerfee);
        }
        Thread.sleep(2000);
    }

    @And("^I click on Add Followup button under Followups section$")
    public void iClickOnAddFollowupButtonUnderFollowupsSection() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(" Clicks Add Follow up button \n ");
        Loginpage page = new Loginpage(base.driver);
        page.followups();
        Thread.sleep(6000);
    }

    @And("^I enter follow up details$")
    public void iEnterFollowUpDetails(DataTable table) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(" Entering follow up details \n");
        Loginpage page = new Loginpage(base.driver);
        List<Followups> followdetails = new ArrayList<Followups>();
        followdetails = table.asList(Followups.class);
        Thread.sleep(5000);
        for (Followups followdetail : followdetails) {
            page.editfollowup(followdetail.followuptype, followdetail.followupdate, followdetail.deptassigned, followdetail.assignedto);
        }
        Thread.sleep(5000);
    }

    @And("^I click on Edit Date symbol$")
    public void iClickOnEditDateSymbol() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Edit Date");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(4000);
        // page.editdate();
        Thread.sleep(6000);
        page.editFUDate();
    }

    @And("^I click on Complete Follow Up button$")
    public void iClickOnCompleteFollowUpButton() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Complete follow up");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(6000);
        page.completeFU();
        Thread.sleep(4000);
    }

    @And("^I click on Lead Assignment on top grid$")
    public void iClickOnLeadAssignmentOnTopGrid() throws InterruptedException {
        System.out.println("Click on Lead Assignment button");
        Loginpage page = new Loginpage(base.driver);
        page.leadassignmentbutton();
        Thread.sleep(4000);
    }

    @And("^I select coverage under opportunity tab in Lead Assignment$")
    public void iSelectCoverageUnderOpportunityTabInLeadAssignment() throws InterruptedException {
        System.out.println("Seelct Coverages under Opportunity Lead Assignment");
        Loginpage page = new Loginpage(base.driver);
        page.leadcoverage();
        Thread.sleep(4000);
    }

    @And("^I enter values under Oportunity Assignment$")
    public void iEnterValuesUnderOportunityAssignment(DataTable table) {
        List<OppAssign> LeadAssignment = new ArrayList<OppAssign>();
        LeadAssignment = table.asList(OppAssign.class);
        System.out.println("entering values in Opportunity Assignment");
        Loginpage page = new Loginpage(base.driver);
        for(OppAssign LeadAssignmentfields: LeadAssignment ) {
            page.leadoppassign(LeadAssignmentfields.brand, LeadAssignmentfields.subbrand, LeadAssignmentfields.classofbusiness, LeadAssignmentfields.clientid,
                    LeadAssignmentfields.broker, LeadAssignmentfields.accesslevel, LeadAssignmentfields.servicelevel, LeadAssignmentfields.descriptionofoperations);
        }
    }

    @And("^I click Lead Submit button$")
    public void iClickLeadSubmitButton() throws InterruptedException {
        System.out.println("Click Lead submit button");
        Loginpage page = new Loginpage(base.driver);
        page.leadsubmit();
        Thread.sleep(4000);
    }

    @When("^I click on Assignment Rules button$")
    public void iClickOnAssignmentRulesButton() {
        System.out.println("Click Assignment Rules button");
        Loginpage page = new Loginpage(base.driver);
        page.assignmentrules();
        String currentWindow = base.driver.getWindowHandle();  //Switching frames
        base.driver.switchTo().window(currentWindow);
    }

    @And("^I click AMS Entry Information tab$")
    public void iClickAMSEntryInformationTab() throws InterruptedException {
        System.out.println("Click AMS Entry Information tab");
        Loginpage page = new Loginpage(base.driver);
        page.amsentryinfotab();
        Thread.sleep(4000);
    }

    @And("^I click Notes tab$")
    public void iClickNotesTab() {
        System.out.println("Click Notes tab");
        Loginpage page = new Loginpage(base.driver);
        page.leadnotes();
    }

    @And("^I click on Account Management on top grid$")
    public void iClickOnAccountManagementOnTopGrid() throws InterruptedException {
        System.out.println("Click on Account Management page");
        Loginpage page = new Loginpage(base.driver);
        page.accountmanagement();
        Thread.sleep(4000);
    }

    @When("^I click on Import Policies button$")
    public void iClickOnImportPoliciesButton() throws InterruptedException {
        System.out.println("Click on import policies button");
        Loginpage page = new Loginpage(base.driver);
        page.importpolicies();
        Thread.sleep(10000);
    }

    @Then("^I click on Close button under Import Policies pop-up$")
    public void iClickOnCloseButtonUnderImportPoliciesPopUp() throws InterruptedException {
        System.out.println("Click close button under import policies");
        Loginpage page = new Loginpage(base.driver);
        page.closeimportpolicies();
        Thread.sleep(4000);
    }

    @When("^I click on View Yearly Summary button$")
    public void iClickOnViewYearlySummaryButton() throws InterruptedException {
        System.out.println("Click on View Yearly summary button");
        Loginpage page = new Loginpage(base.driver);
        page.viewyearlysummary();
        Thread.sleep(4000);
    }

    @Then("^I click on Close button under Yearly summary pop-up$")
    public void iClickOnCloseButtonUnderYearlySummaryPopUp() throws InterruptedException {
        System.out.println("Click close button on Yearly summary button");
        Loginpage page = new Loginpage(base.driver);
        page.closeyearlysummary();
        Thread.sleep(4000);
    }

    @And("^I click on Submit button$")
    public void iClickOnSubmitButton() throws InterruptedException {
        System.out.println("Click submit button");
        Loginpage page = new Loginpage(base.driver);
        page.importsubmit();
        Thread.sleep(4000);
    }

    @And("^I click alert OK button$")
    public void iClickAlertOKButton() throws InterruptedException {
        System.out.println("Click alert OK button");
        Loginpage page = new Loginpage(base.driver);
        page.alertok();
        Thread.sleep(4000);
    }

    @When("^I select WO Tracking application box$")
    public void iSelectWOTrackingApplicationBox() throws InterruptedException {
        System.out.println("Before switching tab url is \n :" + base.driver.getCurrentUrl());
        Loginpage page = new Loginpage(base.driver);
        //page.PMATracklink();
        base.driver.navigate().to("https://nodedevpma.patracorp.net/");
        System.out.println("Clicks PMA box \n");
        Thread.sleep(3000);
    }

    @And("^user  clicks on business details in pma$")
    public void userClicksOnBusinessDetailsInPma() throws InterruptedException {
        System.out.println("Click on business details");
        Loginpage page= new Loginpage(base.driver);
        page.businesslink();
    }

    @And("^I Verify the delete quote functionality for PMA manager role$")
    public void iVerifyTheDeleteQuoteFunctionality() throws InterruptedException {
        System.out.println("Delete Quote");
        Loginpage page = new Loginpage(base.driver);
        page.deletequote();
    }

    @And("^I click on start timer and verify for clear and close functionalities$")
    public void iClickOnStartTimer() throws InterruptedException {
        System.out.println("Click on Start timer");
        Loginpage page = new Loginpage(base.driver);
        page.starttimer();
    }

    @And("^I select company name$")
    public void iSelectCompanyName(DataTable table) throws InterruptedException {
        System.out.println("Select company name");
        List<Pmatimer> values = new ArrayList<Pmatimer>();
        values = table.asList(Pmatimer.class);
        Loginpage page = new Loginpage(base.driver);
        for (Pmatimer valuess : values) {
            page.companytimer(valuess.timercompany, valuess.timerbrandpma, valuess.timersubbrandpma, valuess.timerservice, valuess.timertaskpma);
        }
    }

    @And("^user selects the status to Bound$")
    public void userSelectsTheStatusToBound()throws InterruptedException {
        System.out.println("Change the status to Bound \n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.statuschange();
    }

    @And("^I verify the My Bound opportunities KPI for This month and This year$")
    public void iVerifyTheMyBoundOpportunitiesKPIForThisMonthAndThisYear() throws InterruptedException {
        System.out.println("Verify Bound opportunities KPI \n");
        Loginpage page = new Loginpage(base.driver);
        page.boundkpi();
        }

    @And("^I verify My open opportunities and my new opportunities KPI$")
    public void iVerifyMyOpenOpportunitiesAndMyNewOpportunitiesKPI()throws InterruptedException {
        System.out.println("Verify KPI My open and new opportunities KPI \n");
        Loginpage page = new Loginpage(base.driver);
        page.boundkpi();
    }

    @And("^I select Bound BOR checkbox to verify that add quote window is disabled$")
    public void iSelectBoundBORCheckboxToVerifyThatAddQuoteWindowIsDisabled() throws InterruptedException {
        System.out.println("Check Bound BOR check box \n");
        Loginpage page = new Loginpage(base.driver);
        page.boundbor();
    }

    @And("^I enter the below mandatory fields for bound BOR$")
    public void iEnterTheBelowMandatoryFieldsForBoundBOR(DataTable table)throws InterruptedException{
        System.out.println("Opportunity with Bound status \n");
       List<Bound> oppbound = new ArrayList<Bound>();
        oppbound = table.asList(Bound.class);
        Loginpage page = new Loginpage(base.driver);
        for(Bound opppbound : oppbound){
            page.boundstatus(opppbound.status, opppbound.accesslevel, opppbound.servicelevel,opppbound.businesstype, opppbound.accountmanager);
        }
    }

    @And("^I Verify the delete quote functionality for Support role$")
    public void iVerifyTheDeleteQuoteFunctionalityForSupportRole() throws InterruptedException{
        System.out.println("Delete quote for support role login \n");
        Thread.sleep(3000);
        File scrFile = ((TakesScreenshot)base.driver).getScreenshotAs(OutputType.FILE);
        try {
            Thread.sleep(1000);
            FileUtils.copyFile(scrFile, new File("D:\\Backup\\D Drive\\Prathima\\PMA\\screenshot.png"));
            Thread.sleep(1500);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("^I verify for Lead assignment link for support role$")
    public void iVerifyForLeadAssignmentLinkForSupportRole() throws InterruptedException {
        System.out.println("Lead assignment link access \n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        File scrFile = ((TakesScreenshot)base.driver).getScreenshotAs(OutputType.FILE);
        try {
            Thread.sleep(1000);
            FileUtils.copyFile(scrFile, new File("D:\\Backup\\D Drive\\Prathima\\PMA\\leadassign.png"));
            Thread.sleep(1500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("^I verify the businessname in AMA$")
    public void iVerifyTheBusinessnameInAMA(DataTable tableable)throws InterruptedException {
        System.out.println("Verify business in AMA \n");
        List<Amainsured> insured = new ArrayList<Amainsured>();
        insured = tableable.asList(Amainsured.class);
        Loginpage page = new Loginpage(base.driver);
        for(Amainsured insuredd : insured) {
            page.businessama(insuredd.amabusinessname);
        }
    }

    @And("^I change the status to Imported and verify in imported leads tab$")
    public void iChangeTheStatusToImported()throws InterruptedException {
        System.out.println("Change the opportunity status to Imported \n");
        Loginpage page = new Loginpage(base.driver);
        page.imported();
    }

    @And("^I capture screen shot$")
    public void iCaptureScreenShot()throws InterruptedException{
        System.out.println("Capture Screen shot");
        base.driver.manage().window().fullscreen();
        Thread.sleep(300);
        File scrFile = ((TakesScreenshot)base.driver).getScreenshotAs(OutputType.FILE);
        try {
            Thread.sleep(2000);
            FileUtils.copyFile(scrFile, new File("D:\\Backup\\D Drive\\Prathima\\PMA\\heffernan.png"));
            Thread.sleep(2500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("^I verify the Oli brand records in opportunity tab$")
    public void iVerifyTheOliBrandRecordsInOpportunityTab()throws InterruptedException {
        System.out.println("Verification of Oli brand records \n");
        Loginpage page = new Loginpage(base.driver);
        page.olimanager();
    }

    @And("^I verify the search result$")
    public void iVerifyTheSearchResult()throws InterruptedException {
        System.out.println("Verifying global search results for oli manager \n");
        Loginpage page = new Loginpage(base.driver);
        page.olimngrglobalsearch();
    }

    @And("^I verify the activities kpi$")
    public void iVerifyTheActivitiesKpi()throws InterruptedException {
        System.out.println("Verify activities KPI in todo list page \n");
        Loginpage page = new Loginpage(base.driver);
        page.activitieskpi();

    }
    @And("^I select business in business grid$")
    public void iSelectBusinessInBusinessGrid()throws InterruptedException {
        System.out.println("Select business from business tab \n");
        Loginpage page = new Loginpage(base.driver);
        page.selectbusiness();
    }

    @And("^I verify follow ups KPI$")
    public void iVerifyFollowUpsKPI()throws InterruptedException {
        System.out.println(" Verify follow up KPI count \n");
        Loginpage page = new Loginpage(base.driver);
        page.followupkpi();
    }
    @And("^I verify recently completed KPI$")
    public void iVerifyRecentlyCompletedKPI()throws InterruptedException {
        System.out.println("Verifying recently completed KPI \n");
        Loginpage page = new Loginpage(base.driver);
        page.recentlycompleted();
    }
    @And("^I click on activity completed checkbox$")
    public void iClickOnActivityCompletedCheckbox()throws InterruptedException {
        System.out.println("Select Activity completed checkbox \n");
        Loginpage page = new Loginpage(base.driver);
        page.activitycompleted();
    }

    @And("^I verify my reviews KPI$")
    public void iVerifyMyReviewsKPI()throws InterruptedException {
        System.out.println("Verify my reviews KPI count \n");
        Loginpage page = new Loginpage(base.driver);
        page.myreviews();
    }

    @And("^I click on review desired checkbox$")
    public void iClickOnReviewDesiredCheckbox()throws InterruptedException {
        System.out.println("Click on review desired checkbox");
        Loginpage page = new Loginpage(base.driver);
        page.reviewdesiredcheck();
    }

    @And("^I change the status to Untouched and verify that lead is in opportunity tab$")
    public void iChangeTheStatusToUntouchedAndVerifyThatLeadIsInOpportunityTab()throws InterruptedException {
        System.out.println("Opportunity with untouched status \n");
        Loginpage page = new Loginpage(base.driver);
        page.oppstatus();
    }

    @And("^I click on yes in coverage alert window for duplicate businessname$")
    public void iClickOnYesInCoverageAlertWindowForDuplicateBusinessname()throws InterruptedException {
        System.out.println("Coverage alert in oli microsites \n");
        Loginpage page = new Loginpage(base.driver);
        page.yescoverage();
    }

    @And("^I click on no in coverage alert window for duplicate businessname$")
    public void iClickOnNoInCoverageAlertWindowForDuplicateBusinessname()throws InterruptedException {
        System.out.println("Coverage alert NO in oli microsites \n");
        Loginpage page = new Loginpage(base.driver);
        page.nocoverage();
    }

    @And("^I click on yes in coverage alert for foley website$")
    public void iClickOnYesInCoverageAlertForFoleyWebsite()throws InterruptedException {
        System.out.println("Coverage alert yes in non oli microsites \n");
        Loginpage page = new Loginpage(base.driver);
        page.foleyyescoverage();
    }

    @And("^I click on no in coverage alert for foley website$")
    public void iClickOnNoInCoverageAlertForFoleyWebsite()throws InterruptedException {
        System.out.println("Coverage alert no in non oli microsites \n");
        Loginpage page = new Loginpage(base.driver);
        page.foleynocoverage();
    }

    @And("^I capture screen shot for heff direct role$")
    public void iCaptureScreenShotForHeffDirectRole()throws InterruptedException {
        System.out.println("Capture Screen shot for heff direct role \n");
        base.driver.manage().window().fullscreen();
        Thread.sleep(300);
        File scrFile = ((TakesScreenshot)base.driver).getScreenshotAs(OutputType.FILE);
        try {
            Thread.sleep(2000);
            FileUtils.copyFile(scrFile, new File("D:\\Backup\\D Drive\\Prathima\\PMA\\heffdirect.png"));
            Thread.sleep(2500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("^user clicks on opportunity grid$")
    public void userClicksOnOpportunityGrid()throws InterruptedException{
        System.out.println("User clicks on opportunity tab \n");
        Loginpage page = new Loginpage(base.driver);
        page.opportunitytab();
    }

    @And("^user clicks business grid$")
    public void userClicksBusinessGrid()throws InterruptedException {
        System.out.println("clicks on business grid \n");
        Loginpage page = new Loginpage(base.driver);
        page.businessgridclick();
    }

    @And("^user edits opportunity details$")
    public void userEditsOpportunityDetails(DataTable table)throws InterruptedException {
        System.out.println("Edit opportunity details \n");
        List<oppeditt> editopp = new ArrayList<oppeditt>();
        editopp = table.asList(oppeditt.class);
        Loginpage page = new Loginpage(base.driver);
        for(oppeditt editoppp : editopp){
            page.oppdetailpage(editoppp.oppstatus,editoppp.wheniscoverageneeded);
        }
    }

    @And("^user selects opportunity$")
    public void userSelectsOpportunity()throws InterruptedException {
        System.out.println("Select the opportunity from grid \n");
        Loginpage page = new Loginpage(base.driver);
        page.oppselect();
    }

    public class oppeditt{
        public String oppstatus,wheniscoverageneeded;
        public oppeditt(String Oppstatus,String WhenIsCoverageNeeded){
            oppstatus = Oppstatus;
            wheniscoverageneeded = WhenIsCoverageNeeded;
        }
    }

    public class Amainsured{
        public String amabusinessname;
        public Amainsured(String Amabusinessname){
            amabusinessname = Amabusinessname;
        }
    }

    public class Bound{
        public String status,accesslevel,servicelevel,businesstype,accountmanager;
        public Bound(String Status,String Accesslevel,String Servicelevel,String Businesstype,String Accountmanager){
            status = Status;
            accesslevel = Accesslevel;
            servicelevel = Servicelevel;
            businesstype = Businesstype;
            accountmanager = Accountmanager;
        }
    }

    public class Pmatimer{
        public String timercompany,timerbrandpma,timersubbrandpma,timerservice,timertaskpma;

        public Pmatimer(String timerCompany,String timerBrand,String timerSubbrand,String timerService,String timerTask){
            timercompany = timerCompany;
            timerbrandpma = timerBrand;
            timersubbrandpma = timerSubbrand;
            timerservice = timerService;
            timertaskpma = timerTask;
        }
    }

    @And("^I click on start in time tracking window$")
    public void iClickOnStartInTimeTrackingWindow() throws InterruptedException {
        System.out.println("Click on start in time tracking window");
        Loginpage page = new Loginpage(base.driver);
        page.timetrackstart();
        Thread.sleep(3000);
    }

    @And("^I select the task$")
    public void iSelectTheTask() throws InterruptedException {
        System.out.println("Select task in activity");
        Loginpage page = new Loginpage(base.driver);
        page.taskactivity();
    }

    @Given("^I navigate to Oli website$")
    public void iNavigateToOliWebsite() throws InterruptedException {
        System.out.println("Access oli website\n");
        base.driver.navigate().to("https://north.patracorp.net/login/");
        base.driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    @And("^I enter the below values in oli website$")
    public void iEnterTheBelowValuesInOliWebsite(DataTable table) throws InterruptedException {
        System.out.println("Enter data in oli website \n");
        List<Odetails> olidtl = new ArrayList<Odetails>();
        olidtl = table.asList(Odetails.class);
        Loginpage page = new Loginpage(base.driver);
        for (Odetails olidtls : olidtl){
            page.olidetails(olidtls.coverageneeded,olidtls.classofbusiness,olidtls.olizipcode);
        }
    }

    @And("^I enter the following details in oli website$")
    public void iEnterTheFollowingDetailsInOliWebsite(DataTable table) throws InterruptedException {
        System.out.println("Enter the details in oli website \n");
        List<Oliopp> oliopps = new ArrayList<Oliopp>();
        oliopps = table.asList(Oliopp.class);
        Loginpage page = new Loginpage(base.driver);
        for (Oliopp olioppps : oliopps ){
            page.oppdetailoli(olioppps.olifname,olioppps.olastname,olioppps.obusinessname,olioppps.olocationadd,olioppps.ocity,olioppps.ostate,olioppps.oemai,olioppps.ophone,olioppps.oaccess,olioppps.oservice,olioppps.oservicer,olioppps.onotes);
        }
    }

    @And("^I enter business name in business column$")
    public void iEnterBusinessNameInBusinessColumn(DataTable table) throws InterruptedException {
        System.out.println("Search the business in lead assignment");
        List<Fbusiness> fbusinesses = new ArrayList<Fbusiness>();
        fbusinesses = table.asList(Fbusiness.class);
        Loginpage page = new Loginpage(base.driver);
        for (Fbusiness fbusinessess : fbusinesses ){
            page.micrositelead(fbusinessess.leadbusinessname);
        }
    }

    @And("^I select broker$")
    public void iSelectBroker() throws InterruptedException {
        System.out.println("Assign broker");
        Loginpage page = new Loginpage(base.driver);
        page.leadbroker();
    }

    @Given("^I navigate to foley website$")
    public void iNavigateToFoleyWebsite() throws InterruptedException {
        System.out.println("Access non oli website");
        base.driver.navigate().to("https://foley.patracorp.net/login/");
        base.driver.manage().window().maximize();
        Thread.sleep(5000);
    }


    @And("^I enter the below values in non oli website$")
    public void iEnterTheBelowValuesInNonOliWebsite(DataTable table) throws InterruptedException {
        System.out.println("User entering details into microsite");
        List<FoleySite> selections = new ArrayList<FoleySite>();
        selections = table.asList(FoleySite.class);
        Loginpage page = new Loginpage(base.driver);
        for (FoleySite selectionss : selections) {
            page.entering_values_into_microsite(selectionss.zipcode,selectionss.classofbusiness,selectionss.coverageneeded,selectionss.referrernamesentry);
        }
    }

    @And("^I enter the following details in non oli website$")
    public void iEnterTheFollowingDetailsInNonOliWebsite(DataTable table) throws InterruptedException {
        System.out.println("Enter the values in detail page");
        List<Detail1> details = new ArrayList<Detail1>();
        details = table.asList(Detail1.class);
        Loginpage page = new Loginpage(base.driver);
        for (Detail1 dtl : details) {
            page.detailpage(dtl.rank, dtl.firstname, dtl.lastname, dtl.email, dtl.businessname, dtl.phonenumber, dtl.locationaddress, dtl.city, dtl.state, dtl.zip, dtl.website, dtl.descp, dtl.currentinsurance, dtl.renewaldate, dtl.grosssale, dtl.payroll, dtl.fulltimeemployee, dtl.parttimeemployee, dtl.fein, dtl.typeoforg, dtl.yearsinbusines, dtl.yearsofexp, dtl.activeowners, dtl.claims, dtl.notes);
        }
    }

    @And("^I enter details in foley oppurtunity page$")
    public void iEnterDetailsInFoleyOppurtunityPage() throws InterruptedException {
        System.out.println("Foley opportunity page");
        Loginpage page = new Loginpage(base.driver);
        page.oppfoley();
        Thread.sleep(2000);
    }

    @And("^I click on stop timer$")
    public void iClickOnStopTimer() {
        System.out.println("Verify Stop timer functionality");
        Loginpage page = new Loginpage(base.driver);
        page.stoptimer();
    }


    public class Detail1 {
        public String rank, firstname, lastname, email, businessname, phonenumber, locationaddress, city, state, zip, website, descp, currentinsurance, renewaldate, grosssale, payroll, fulltimeemployee, parttimeemployee, fein, typeoforg, yearsinbusines, yearsofexp, activeowners, claims, notes;
        public Detail1(String Rank, String Firstname, String Lastname, String Email, String Businessname, String Phone, String Location, String City, String State, String Zip, String Website, String Descp, String Currentinsurance, String Renewal, String Gross, String Payroll, String Full, String Part, String Fein, String Typeoforg, String Yearsbusiness, String Yearsexp, String Active, String Claims, String Notes) {
            rank = Rank;
            firstname = Firstname;
            lastname = Lastname;
            email = Email;
            businessname = Businessname;
            phonenumber = Phone;
            locationaddress = Location;
            city = City;
            state = State;
            zip = Zip;
            website = Website;
            descp = Descp;
            currentinsurance = Currentinsurance;
            renewaldate = Renewal;
            grosssale = Gross;
            payroll = Payroll;
            fulltimeemployee = Full;
            parttimeemployee = Part;
            fein = Fein;
            typeoforg = Typeoforg;
            yearsinbusines = Yearsbusiness;
            yearsofexp = Yearsexp;
            activeowners = Active;
            claims = Claims;
            notes = Notes;
        }
    }

    public class FoleySite{
        public String zipcode;
        public String classofbusiness;
        public String coverageneeded;
        public String referrernamesentry;

        public FoleySite(String Zipcode,String Classofbusiness, String Coverageneeded, String Referrernamesentry ){
            zipcode = Zipcode;
            classofbusiness = Classofbusiness;
            coverageneeded = Coverageneeded;
            referrernamesentry = Referrernamesentry;
        }
    }

    public class Fbusiness{
        public String leadbusinessname;
        public Fbusiness(String Leadbusinessname){
            leadbusinessname = Leadbusinessname;
        }
    }

    public class Oliopp{
        public String olifname;public String olastname;public String obusinessname;public String olocationadd;public String ocity;public String ostate;public String oemai;public String ophone;public String oaccess;public String oservice;public String oservicer;public String onotes;
        public Oliopp(String oliFname,String olastName,String oBusinessname,String oLocationadd,String oCity,String oState,String oEmai,String oPhone,String oAccess,String oService,String oServicer,String oNotes){
            olifname = oliFname;
            olastname = olastName;
            obusinessname = oBusinessname;
            olocationadd = oLocationadd;
            ocity = oCity;
            ostate = oState;
            oemai = oEmai;
            ophone = oPhone;
            oaccess = oAccess;
            oservice = oService;
            oservicer = oServicer;
            onotes = oNotes;
        }
    }

    public class Odetails{
        public String coverageneeded;public String classofbusiness;public String olizipcode;
        public Odetails(String OliCoverage,String Olibusiness,String Olizip){
            coverageneeded = OliCoverage;
            classofbusiness = Olibusiness;
            olizipcode = Olizip;
        }
    }

    public class Followups {
        public String followuptype, followupdate,deptassigned,assignedto ;
        public Followups(String FollowUpType, String FollowUpDate, String DeptAssigned, String AssignedTo) {
            followuptype = FollowUpType;
            followupdate = FollowUpDate;
            deptassigned = DeptAssigned;
            assignedto = AssignedTo;
        }
    }

    public class SignIn {
        public String email, password;
        public SignIn(String Email, String Password) {
            email = Email;
            password = Password;
        }
    }

    public class BusNa {
        public String businessname;
        public BusNa(String BusinessName) {
            businessname = BusinessName;
        }
    }
    /* Declaring Business page variables */
    public class AddBusiness {
        public String brand, subbrand, businessname, businessclass, clientid,broker,referrername, referringcompany,prospectorigin, leadassignedby,needbydate,yearsinbusiness, experience, website,
                typeoforganization, ifotherpleasespecify,descriptionofoperations, estannualgrosssales,estannualpayroll,noofactiveowners,fte,pte, fein,coveragetype;

        public AddBusiness(String Brand, String SubBrand, String BusinessName, String BusinessClass, String ClientId, String Broker, String ReferrerName,
                           String ReferringCompany, String ProspectOrigin, String LeadAssignedBy, String NeedByDate, String YearsInBusiness, String Experience,
                           String Website, String TypeOfOrganization, String IfOtherPleaseSpecify, String DescriptionOfOperations, String EstAnnualGrossSales,
                           String EstAnnualPayroll, String NoOfActiveOwners, String FTE, String PTE, String FEIN, String CoverageType) {
            brand = Brand;
            subbrand = SubBrand;
            businessname = BusinessName;
            businessclass = BusinessClass;
            clientid = ClientId;
            broker = Broker;
            referrername = ReferrerName;
            referringcompany = ReferringCompany;
            prospectorigin = ProspectOrigin;
            leadassignedby = LeadAssignedBy;
            needbydate = NeedByDate;
            yearsinbusiness = YearsInBusiness;
            experience = Experience;
            website = Website;
            typeoforganization = TypeOfOrganization;
            ifotherpleasespecify = IfOtherPleaseSpecify;
            descriptionofoperations = DescriptionOfOperations;
            estannualgrosssales = EstAnnualGrossSales;
            estannualpayroll = EstAnnualPayroll;
            noofactiveowners = NoOfActiveOwners;
            fte = FTE;
            pte = PTE;
            fein = FEIN;
            coveragetype = CoverageType;
        }
    }

    public class AddAddresses {
        public String addresstype, city, region,street1, street2,zip, country;
        public AddAddresses(String AddressType, String City, String Region, String Street1, String Street2, String Zip, String Country) {
            addresstype = AddressType;
            city = City;
            region = Region;
            street1 = Street1;
            street1 = Street2;
            zip = Zip;
            country = Country;
        }
    }

    public class BusinessNameSearch {
        public String businessname;
        public BusinessNameSearch(String BusinessName) {
            businessname = BusinessName;
        }
    }

    public class EditBusinessDetails {
        public String contactname, contactwork,contactemail ;

        public EditBusinessDetails(String ContactName, String ContactWork, String contactEmail) {
            contactname = ContactName;
            contactwork = ContactWork;
            contactemail = contactEmail;
        }
    }

    public class BusinessAddOpportunity {
        public String status, coveragetype, needbydate,opportunityreceiveddate, departmentassigned, assignedto, opportunityassigneddate, currentinsurancecarrier, renewaldate, currentpremium,
                priorclaims, origin,describepriorclaims;

        public BusinessAddOpportunity(String Status, String CoverageType, String NeedByDate, String OpportunityReceivedDate,
                                      String DepartmentAssigned, String AssignedTo, String OpportunityAssignedDate,
                                      String CurrentInsuranceCarrier, String RenewalDate, String CurrentPremium,
                                      String PriorClaims, String Origin, String DescribePriorClaims) {
            status = Status;
            coveragetype = CoverageType;
            needbydate = NeedByDate;
            opportunityreceiveddate = OpportunityReceivedDate;
            departmentassigned = DepartmentAssigned;
            assignedto = AssignedTo;
            opportunityassigneddate = OpportunityAssignedDate;
            currentinsurancecarrier = CurrentInsuranceCarrier;
            renewaldate = RenewalDate;
            currentpremium = CurrentPremium;
            priorclaims = PriorClaims;
            origin = Origin;
            describepriorclaims = DescribePriorClaims;
        }
    }

    public class BusinessAddNote {
        public String title, description;
        public BusinessAddNote(String Title, String Description) {
            title = Title;
            description = Description;
        }
    }

    public class BusinessAddActivity {
        public String activitytype, activitystatus,departmentassigned, assignedto, needbydate, activityperformed, activitycompleted,followuprequired,reviewdesired, description;

        public BusinessAddActivity(String ActivityType, String ActivityStatus, String DepartmentAssigned, String AssignedTo,
                                   String NeedByDate, String ActivityPerformed, String ActivityCompleted, String FollowUpRequired,
                                   String ReviewDesired, String Description) {
            activitytype = ActivityType;
            activitystatus = ActivityStatus;
            departmentassigned = DepartmentAssigned;
            assignedto = AssignedTo;
            needbydate = NeedByDate;
            activityperformed = ActivityPerformed;
            activitycompleted = ActivityCompleted;
            followuprequired = FollowUpRequired;
            reviewdesired = ReviewDesired;
            description = Description;
        }
    }

    public class BusinessOpportunityDetail {
        public String status, coveragetype, wheniscoverageneeded,needbydate, opportunityreceiveddate, broker, assigneddate, currentinsurancecarrier, renewaldate,currentpremium,
                accesslevel, servicelevel, prospectorigin, businesstype, accountmanager,effectivedate, commission,brokerfee;

        public BusinessOpportunityDetail(String Status, String CoverageType, String WhenIsCoverageNeeded, String NeedByDate,
                                         String OpportunityReceivedDate, String Broker, String AssignedDate ,
                                         String CurrentInsuranceCarrier, String RenewalDate, String CurrentPremium, String AccessLevel, String Servicelevel,String Prospectorigin, String Businesstype, String Accountmanager, String Effectivedate, String Commission, String Brokerfee) {
            status = Status;
            coveragetype = CoverageType;
            wheniscoverageneeded = WhenIsCoverageNeeded;
            needbydate = NeedByDate;
            opportunityreceiveddate = OpportunityReceivedDate;
            broker = Broker;
            assigneddate = AssignedDate;
            currentinsurancecarrier = CurrentInsuranceCarrier;
            renewaldate = RenewalDate;
            currentpremium = CurrentPremium;
            accesslevel = AccessLevel;
            servicelevel = Servicelevel;
            prospectorigin = Prospectorigin;
            businesstype = Businesstype;
            accountmanager = Accountmanager;
            effectivedate = Effectivedate;
            commission = Commission;
            brokerfee = Brokerfee;

        }
    }

    /* Declaring Opportunity page variables */
    public class Detail {
        public String status, coveragetype,wheniscoverageneeded,needbydate,opportunityreceiveddate,departmentassigned,assignedtoopportunity,assigneddate,currentinsurancecarrier,renewaldate,
                currentpremium, accesslevel, quotedate, quotedcarrier, effectivedate, quotepremium, commission, brokerfee;

        public Detail(String Status, String CoverageType, String WhenIsCoverageNeeded, String NeedByDate, String OpportunityReceivedDate,
                      String DeptAssigned, String AssignedToOpportunity, String AssignedDate, String CurrentInsCarrier,
                      String RenewalDate, String CurrentPremium, String AccessLevel, String QuoteDate, String QuotedCarrier,
                      String EffectiveDate, String QuotePremium, String Commission, String BrokerFee) {
            status = Status;
            coveragetype = CoverageType;
            wheniscoverageneeded = WhenIsCoverageNeeded;
            needbydate = NeedByDate;
            opportunityreceiveddate = OpportunityReceivedDate;
            departmentassigned = DeptAssigned;
            assignedtoopportunity = AssignedToOpportunity;
            assigneddate = AssignedDate;
            currentinsurancecarrier = CurrentInsCarrier;
            renewaldate = RenewalDate;
            currentpremium = CurrentPremium;
            accesslevel = AccessLevel;
            quotedate = QuoteDate;
            quotedcarrier = QuotedCarrier;
            effectivedate = EffectiveDate;
            quotepremium = QuotePremium;
            commission = Commission;
            brokerfee = BrokerFee;
        }
    }

    public class AddActivity {
        public String activitytype, activitystatus, departmentassigned, assignedto, needbydate, reviewdesired, followupreq, description;

        public AddActivity(String Activitytype, String ActivityStatus, String DepartmentAssigned, String AssignedTo, String NeedByDate,
                           String ReviewDesired, String FollowUpReq, String Description) {
            activitytype = Activitytype;
            activitystatus = ActivityStatus;
            departmentassigned = DepartmentAssigned;
            assignedto = AssignedTo;
            needbydate = NeedByDate;
            reviewdesired = ReviewDesired;
            followupreq = FollowUpReq;
            description = Description;
        }
    }

    public class AddNote {
        public String title, description;

        public AddNote(String Title, String Description) {
            title = Title;
            description = Description;
        }
    }

    public class AddClaim {
        public String claimdate, claimstatus,claimamount, descriptionofclaim ;

        public AddClaim(String ClaimDate, String ClaimStatus, String ClaimAmount, String DescriptionOfClaim) {
            claimdate = ClaimDate;
            claimstatus = ClaimStatus;
            claimamount = ClaimAmount;
            descriptionofclaim = DescriptionOfClaim;
        }
    }

    public class AddQuote {
        public String quotedate, carrier,quotedpremium,carrierdeclined, declinedreason  ;
        public AddQuote(String QuoteDate, String Carrier, String QuotedPremium, String CarrierDeclined, String DeclinedReason) {
            quotedate = QuoteDate;
            carrier = Carrier;
            quotedpremium = QuotedPremium;
            carrierdeclined = CarrierDeclined;
            declinedreason = DeclinedReason;
        }
    }

    public class ActivityDetail {
        public String activitytype, activitystatus, departmentassigned, assignedto, needbydate, activityperformed, activitycompleted, reviewdesired, followuprequired, numberoffollowups,
                followupfrequency, finalfollowup, description;

        public ActivityDetail(String Activitytype, String Activitystatus, String Departmentassigned, String Assignedto, String Needbydate, String Activityperformed, String Activitycompleted,
                              String Reviewdesired, String Followuprequired, String Numberoffollowups, String Followupfrequency, String Finalfollowup, String Description) {
            activitytype = Activitytype;
            activitystatus = Activitystatus;
            departmentassigned = Departmentassigned;
            assignedto = Assignedto;
            needbydate = Needbydate;
            activityperformed = Activityperformed;
            activitycompleted = Activitycompleted;
            reviewdesired = Reviewdesired;
            followuprequired = Followuprequired;
            numberoffollowups = Numberoffollowups;
            followupfrequency = Followupfrequency;
            finalfollowup = Finalfollowup;
            description = Description;
        }
    }

    public class ToDoAddNote {
        public String title, description;
        public ToDoAddNote(String Title, String Description) {
            title = Title;
            description = Description;
        }
    }

    public class ToDoAddClaim {
        public String claimdate, claimstatus, claimamount, descriptionofclaim;

        public ToDoAddClaim(String ClaimDate, String ClaimStatus, String ClaimAmount, String DescriptionOfClaim) {
            claimdate = ClaimDate;
            claimstatus = ClaimStatus;
            claimamount = ClaimAmount;
            descriptionofclaim = DescriptionOfClaim;
        }
    }

    public class ToDoAddQuote {
        public String quotedate, carrier, quotedpremium, carrierdeclined, declinedreason;

        public ToDoAddQuote(String QuoteDate, String Carrier, String QuotedPremium, String CarrierDeclined, String DeclinedReason) {
            quotedate = QuoteDate;
            carrier = Carrier;
            quotedpremium = QuotedPremium;
            carrierdeclined = CarrierDeclined;
            declinedreason = DeclinedReason;
        }
    }

    public class GlobalSearch {
        public String globalsearch;

        public GlobalSearch(String Globalsearch) {
            globalsearch = Globalsearch;
        }
    }

    public class OpportunityDetailStatusAsBoundOrProposed {
        public String status;

        public OpportunityDetailStatusAsBoundOrProposed(String Status) {
            status = Status;
        }
    }

    public class OpportunityDetailAddQuote {
        public String quotedate, carrier, quotedpremium;

        public OpportunityDetailAddQuote(String QuoteDate, String Carrier, String QuotedPremium) {
            quotedate = QuoteDate;
            carrier = Carrier;
            quotedpremium = QuotedPremium;
        }
    }

    public class MandatoryinOpportunityDetail {
        public String effectivedate;
        public String quotepremium;
        public String commission;
        public String brokerfee;

        public MandatoryinOpportunityDetail(String EffectiveDate, String QuotePremium, String Commission, String BrokerFee) {
            effectivedate = EffectiveDate;
            quotepremium = QuotePremium;
            commission = Commission;
            brokerfee = BrokerFee;
        }
    }

    public class OppAssign{
        public String brand, subbrand, classofbusiness,clientid, broker, accesslevel, servicelevel, descriptionofoperations;

        public OppAssign(String Brand, String Subbrand, String ClassofBusiness, String Clientid, String Broker, String AccessLevel, String ServiceLevel, String DescriptionOfOperations) {
            brand = Brand;
            subbrand = Subbrand;
            classofbusiness = ClassofBusiness;
            clientid = Clientid;
            broker = Broker;
            accesslevel = AccessLevel;
            servicelevel = ServiceLevel;
            descriptionofoperations = DescriptionOfOperations;
        }
    }
}


