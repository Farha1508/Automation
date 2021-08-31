package Pages;

import Base.BaseUtil;
import cucumber.api.java.gl.E;
import gherkin.lexer.Th;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.security.sasl.SaslServer;
import javax.sql.rowset.serial.SerialStruct;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Loginpage extends BaseUtil {

    private BaseUtil base;

    public Loginpage() {

        this.base = base;
    }

   //Intitializing elements in Home Page

    @FindBy(how = How.ID, using = "email")
    public WebElement txtemail;
    @FindBy(how = How.ID, using = "password")
    public WebElement txtpassword;
    @FindBy(how = How.ID, using = "submit")
    public WebElement btnSignIn;

    @FindBy(how = How.ID, using = "index1")         //Home button
    public WebElement btnhome;
    @FindBy(how = How.XPATH, using = "//*[@id='dtopportunity_wrapper']/div[1]/div[2]/div/div[3]")
    //Reset button opportunity grid
    public WebElement btnReset;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtbusiness_wrapper\"]/div[1]/div[2]/div/div[3]")
    //Reset button business grid
    public WebElement resetbtn;

    @FindBy(how = How.ID, using = "index3")  //Reports button
    public WebElement btnReports;
    @FindBy(how = How.ID, using = "index7")  //Lead Assignment button
    public WebElement btnleadassignment;

    @FindBy(how = How.ID, using = "index8")  //Account Management button
    public WebElement btnaccountmanagement;
    @FindBy(how = How.ID, using = "importing-Policies")  //Import Policies button
    public WebElement btnimportpolicies;
    @FindBy(how = How.ID, using = "viewfile") //Import Policies Submit button
    public WebElement btnsubmitpolicies;
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[4]/button")
    //Alert pop up ok btn /html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[4]/button
    public WebElement alertokbtn;
    @FindBy(how = How.XPATH, using = "//*[@id='Import_Policy_Cancel']")  //close import policies
    public WebElement btncloseimportpolicies;
    @FindBy(how = How.ID, using = "view-Yearly-Summary")  //View Yearly Summary button
    public WebElement btnviewyearlysummary;
    @FindBy(how = How.XPATH, using = "//*[@id='viewSummary']/div/div/div/div[1]/div/div[2]/button")
    //close view yearly summary
    public WebElement btncloseyearlysummary;
    //Intitializing elements in Business Page
    @FindBy(how = How.ID, using = "Opp-addBusiness")  //Add Business button
    public WebElement btnAddBusiness;
    @FindBy(how = How.ID, using = "brand-list-drop-down")  //Brand
    public WebElement cbBrand;
    @FindBy(how = How.ID, using = "sub-brand-list-drop-down") //sub Brand
    public WebElement cbSubBrand;
    @FindBy(how = How.ID, using = "business-name-form")  //Business/Policy Holder Name
    public WebElement txtBusinessNameForm;
    @FindBy(how = How.ID, using = "business-class-form")  //Business Class
    public WebElement cbBusinessClassForm;
    @FindBy(how = How.ID, using = "client-id-form")  //Client ID
    public WebElement txtClientIDForm;
    @FindBy(how = How.ID, using = "broker-form")   //Broker
    public WebElement cbBrokerForm;
    @FindBy(how = How.ID, using = "referrer-name-form")  //Referrer Name
    public WebElement txtReferrerNameForm;
    @FindBy(how = How.ID, using = "refering-company-form")  //Referring Company
    public WebElement txtReferrerCompanyForm;
    @FindBy(how = How.ID, using = "prospect-origin-form")   //Prospect Origin
    public WebElement cbProspectOriginForm;
    @FindBy(how = How.ID, using = "urgency-need-form")   //urgency need by date
    public WebElement txtUrgencyNeedByDateForm;
    @FindBy(how = How.ID, using = "year-in-business-form")  //year-in-business
    public WebElement txtYearsInBusinessForm;
    @FindBy(how = How.ID, using = "experience-form")      //Experience
    public WebElement txtExperienceForm;
    @FindBy(how = How.ID, using = "website-form")         //Website
    public WebElement txtWebsiteForm;
    @FindBy(how = How.ID, using = "type-of-organization-form")
    public WebElement cbTypeOfOrganizationForm;
    @FindBy(how = How.ID, using = "other-specify-form")
    public WebElement txtIfOtherPleaseSpecifyForm;
    @FindBy(how = How.ID, using = "description-of-operation-form")
    public WebElement txtDescriptionOfOperationsForm;
    @FindBy(how = How.ID, using = "est-annual-gross-form")
    public WebElement txtESTAnnualGrossSalesForm;
    @FindBy(how = How.ID, using = "est-annual-form")
    public WebElement txtESTAnnualPayrollForm;
    @FindBy(how = How.ID, using = "active-owners-form")
    public WebElement txtNoOfActiveOwnersForm;
    @FindBy(how = How.ID, using = "fte-form")
    public WebElement txtFTEForm;
    @FindBy(how = How.ID, using = "pte-form")
    public WebElement txtPTEForm;
    @FindBy(how = How.ID, using = "fein-form")
    public WebElement txtFEINForm;
    @FindBy(how = How.ID, using = "O-coverage-type")
    public WebElement cbCoverageTypeForm;
    @FindBy(how = How.ID, using = "Business-Details-Submit")    //Submit
    public WebElement btnAddBusinessSubmitForm;
    @FindBy(how = How.ID, using = "Business-Details-Cancel")    //Submit
    public WebElement btnAddBusinessCancelForm;
    @FindBy(how = How.XPATH, using = "//*[@id=\"tabbtnbusiness\"]")   //Business tab grid
    public WebElement btnBusinessGrid;
    //  @FindBy(how = How.ID, using = "Business Name")  // Business Name search box
    //  @FindBy(how = How.CSS, using ="#th-BusinessNameBus > ul:nth-child(1) > li:nth-child(2) > input:nth-child(1)")
    //  @FindBy(how = How.ID, using = "Business Name")
    // @FindBy(how = How.XPATH, using = "//*th[@id=’th-BusinessNameBus’]//following::input")
    @FindBy(how = How.XPATH, using = "//*[@id=\"Business Name\"]")
    public WebElement txtBusinessName;
    @FindBy(how = How.CSS, using = "#opportunities_datatable_ajax > tbody > tr:nth-child(1) > td:nth-child(1)")
    //Selecting opportunity in business detail page
    public WebElement linkBusinessName;
    /*
     * created by Anusha on 01/17/2018
     * */
    @FindBy(how = How.ID, using = "business-details-click")  //Business Details link
    public WebElement linkBussDetails;//*[@id="dtbusiness"]/tbody/tr/td[1]/a
    @FindBy(how = How.ID, using = "addContactsButton")  //Add Contact
    public WebElement BussAddContact;
    @FindBy(how = How.ID, using = "contact-name-cform")  //Contact name
    public WebElement txtcontactname;
    @FindBy(how = How.ID, using = "contact-phone-work-cform")  //Contact phone(Work)
    public WebElement txtcontactwork;
    @FindBy(how = How.ID, using = "contact-email-cform")  //Contact email
    public WebElement txtcontactemail;
    @FindBy(how = How.ID, using = "contact-phone-work-cform")  //Contact phone(Mobile)
    public WebElement txtcontactmobile;
    @FindBy(how = How.ID, using = "contact-title-cform")  //Contact Title
    public WebElement txtcontacttitle;
    //   @FindBy(how = How.ID, using = "contact-type-cform")  //Contact Type dropdown
    @FindBy(how = How.CSS, using = "#contact-type-cform")
    public WebElement drpcontacttype;
    @FindBy(how = How.ID, using = "contact-member-number-cform")  //Contact Member Number
    public WebElement txtcontactmemnum;
    @FindBy(how = How.ID, using = "contact-dob-cform")  //Contact Date of Birth
    public WebElement calcontactDOB;
    @FindBy(how = How.ID, using = "make-primary-cform")  //Make Primary
    public WebElement chckmakeprimary;
    @FindBy(how = How.ID, using = "mark-inactive-aform")  //Mark Inactive
    public WebElement chckmarkinactive;
    @FindBy(how = How.ID, using = "contact-submit-form")  //Submit for Add Contact
    public WebElement btncontactsubmit;
    @FindBy(how = How.ID, using = "contact-cancel-form")  //Cancel for Add Contact
    public WebElement btncontactcancel;
    @FindBy(how = How.XPATH, using = "//*[@id='contact-table']/tr/td[8]/button/span")   //Edit symbol Add Contact
    public WebElement EditAddContact;
    /*
     * created by Anusha on 01/18/2018
     * */
    @FindBy(how = How.ID, using = "addAddressesButton")   //Add Addresses
    public WebElement BussAddAddress;
    @FindBy(how = How.CSS, using = "#address-type-aform")   //Address Type
    public WebElement drpaddresstype;
    @FindBy(how = How.ID, using = "city-aform")          //city
    public WebElement txtcity;
    @FindBy(how = How.ID, using = "street1-aform")
    public WebElement txtStreet1;
    @FindBy(how = How.ID, using = "street2-aform")
    public WebElement txtStreet2;
    @FindBy(how = How.ID, using = "region-aform")
    public WebElement txtregion;
    @FindBy(how = How.ID, using = "zip-aform")
    public WebElement txtzip;
    @FindBy(how = How.ID, using = "country-aform")
    public WebElement txtcountry;
    @FindBy(how = How.ID, using = "address-submit-form")   //Submit Add Address
    public WebElement btnaddresssubmit;
    @FindBy(how = How.ID, using = "address-cancel-form")   //Submit Add Address
    public WebElement btnaddresscancel;
    //  @FindBy(how = How.CSS, using = "#address-table > tr > td:nth-child(7) > button > span")  //Edit icon Add Address
    @FindBy(how = How.XPATH, using = "//*[@id='address-table']/tr/td[7]/button/span")
    public WebElement editaddress;

    @FindBy(how = How.ID, using = "addOpportunity-button")   //Add Opportunity under Business
    public WebElement btnBussAddOpportunity;
    @FindBy(how = How.ID, using = "ao-status")          //Status
    public WebElement cbStatus;
    @FindBy(how = How.ID, using = "ao-coverage-type")    // Coverage Type
    public WebElement cbCoverageType;
    @FindBy(how = How.ID, using = "ao-need-by-date")           //Need by Date
    public WebElement txtNeedbyDate;
    @FindBy(how = How.ID, using = "ao-reDate")             //Opportunity Received Date
    public WebElement txtOpportunityReceivedDate;
    @FindBy(how = How.ID, using = "ao-department-assigned")   //
    public WebElement txtDepartmentAssigned;
    @FindBy(how = How.ID, using = "ao-assigned-to")
    public WebElement txtAssignedTo;
    @FindBy(how = How.ID, using = "ao-assigned-date")        //Opportunity Assigned Date
    public WebElement txtOpportunityAssignedDate;
    @FindBy(how = How.ID, using = "ao-current-insurance")   //Current Insurance Carrier
    public WebElement txtCurrentInsurance;
    @FindBy(how = How.ID, using = "ao-renewal-date")       //Renewal Date
    public WebElement txtRenewalDate;
    @FindBy(how = How.ID, using = "ao-current-premium")    //Current Premium
    public WebElement txtCurrentPremium;
    @FindBy(how = How.ID, using = "ao-prior-claims")      //Prior Claims?
    public WebElement chkPriorClaims;
    @FindBy(how = How.ID, using = "ao-origin")           //Origin
    public WebElement cbOrigin;
    @FindBy(how = How.ID, using = "ao-describe-prior-claims")    //Describe Prior Claims
    public WebElement txtDescribePriorClaims;
    @FindBy(how = How.ID, using = "ao-submit-form")    // Business Add opportunity Submit button
    public WebElement btnAddOpportunitySubmit;
    @FindBy(how = How.ID, using = "ao-cancel-form") // Business Add opportunity Cancel button
    public WebElement btnAddOpportunityCancel;
    @FindBy(how = How.ID, using = "addNote-button")    //Business Add Note
    public WebElement btnAddNote;
    @FindBy(how = How.ID, using = "note_title")
    public WebElement txtNoteTitle;
    @FindBy(how = How.ID, using = "note_desc")
    public WebElement txtNoteDescription;
    @FindBy(how = How.ID, using = "notes_submit")
    public WebElement btnAddNoteSubmit;
    @FindBy(how = How.ID, using = "notes_close")
    public WebElement btnAddNoteClose;
    @FindBy(how = How.XPATH, using = "//a[@href='#notes']")   //Business page Notes Tab
    public WebElement btnNoteTab;
    @FindBy(how = How.ID, using = "addActivity-button")       //Business Add Activity button
    public WebElement btnAddActivity;
    @FindBy(how = How.ID, using = "Activ_Activity_type")
    public WebElement cbActivityType;
    @FindBy(how = How.ID, using = "Activ_Activity_status")
    public WebElement cbActivityStatus;
    @FindBy(how = How.ID, using = "Activ_Dept_assign")
    public WebElement cbDepartmentAssigned;
    @FindBy(how = How.ID, using = "Activ_assign_to")
    public WebElement cbAssignedTo;
    @FindBy(how = How.ID, using = "Actv_need_by_date")
    public WebElement txtActivityNeedByDate;
    @FindBy(how = How.ID, using = "actvity_perfm")
    public WebElement chkActivityPerformed;
    @FindBy(how = How.ID, using = "Actv_Completed")
    public WebElement chkActivityCompleted;
    @FindBy(how = How.ID, using = "follow_up_req")
    public WebElement chkFollowUpRequired;
    @FindBy(how = How.ID, using = "Review_desired")
    public WebElement chkReviewDesired;
    @FindBy(how = How.ID, using = "Actv_desc")
    public WebElement txtActivityDescription;
    @FindBy(how = How.ID, using = "Actv_submit")
    public WebElement btnActivitySubmit;
    @FindBy(how = How.ID, using = "Actv_submit_close")
    public WebElement btnActivityClose;
    @FindBy(how = How.ID, using = "activities-tab")    //Business page Activities Tab
    public WebElement btnActivitiesTab;
    @FindBy(how = How.ID, using = "opportunities-tab")  //Business page Opportunities Tab
    public WebElement opportunitiestab;
    @FindBy(how = How.CSS, using = "#opportunities_datatable_ajax > tbody > tr.odd > td:nth-child(1) > a")
    //  Selecting Coverage under business tab
    public WebElement linkcoverageopportunities;

    @FindBy(how = How.CSS, using = "#dtopportunity > tbody > tr:nth-child(1) > td.sorting_1 > div > a")
    //selecting coverage under Opportunities
    public WebElement linkCoverage;

    //Locating Elements in Business / opportunity -Detail tab
    @FindBy(how = How.LINK_TEXT, using = "Detail")
    public WebElement tabDetail;
    @FindBy(how = How.ID, using = "opport_status")
    public WebElement drpStatus;

    @FindBy(how = How.ID, using = "coverage_type")
    public WebElement txtcoveragetype;
    @FindBy(how = How.ID, using = "coverage_urgcy")
    public WebElement txtcoverageneeded;
    @FindBy(how = How.ID, using = "coverage_need")
    //   @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > tbody > tr:nth-child(5) > td.today.day")
    public WebElement calneedbydate;

    @FindBy(how = How.ID, using = "oppo_received_date")
    public WebElement txtopprecdate;
    @FindBy(how = How.ID, using = "assigned_date")
    public WebElement calassigneddate;
    @FindBy(how = How.ID, using = "current_insurance_carrier")
    public WebElement txtcurrentinsurancecarrier;
    @FindBy(how = How.ID, using = "renewal_date")
    public WebElement calrenewaldate;
    @FindBy(how = How.ID, using = "curr_premium")
    public WebElement txtcurrentpremium;
    @FindBy(how = How.ID, using = "access_level")
    public WebElement drpdownaccess;
    @FindBy(how = How.ID, using = "quote_date")
    public WebElement txtquotedate;
    @FindBy(how = How.ID, using = "quote_carrier")
    public WebElement txtquotedcarrier;
    @FindBy(how = How.ID, using = "effective_date")       //Effective Date
    public WebElement caleffectivedate;
    @FindBy(how = How.ID, using = "quote_premium")       //Quote Premium
    public WebElement txtquoteprem;
    @FindBy(how = How.ID, using = "commis_perc")       //Commission(%)
    public WebElement txtcommission;
    @FindBy(how = How.ID, using = "broker_fee")       //CBroker Fee
    public WebElement txtbrokerfee;
    @FindBy(how = How.ID, using = "is_term_life")
    public WebElement checktermlife;
    @FindBy(how = How.ID, using = "is_account_paid")
    public WebElement checkaccountpaid;
    @FindBy(how = How.ID, using = "oppor_submit")  //Submit button for Detail tab under Opportunity
    public WebElement btnSubmit;

    @FindBy(how = How.ID, using = "Activities_sects")  //Activities Tab under Opportunity//
    public WebElement Activities;
    @FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/ul/li[1]/button") //Add Activity button//
    public WebElement AddActivity;
    @FindBy(how = How.ID, using = "Actv_submit")        //Submit button under Add Activity//
    public WebElement ActSubmit;
    @FindBy(how = How.ID, using = "Actv_submit_close")  //Cancel button under Add Activity//
    public WebElement ActCancel;

    //Locating elements under Add Activity section //
    //  @FindBy(how = How.ID, using = "Activ_Activity_type")  //Activity Type
    @FindBy(how = How.NAME, using = "Activ_Activity_type")
    public WebElement drpActivitytype;
    @FindBy(how = How.ID, using = "Actv_need_by_date")  //Activity Need By DAte
    public WebElement actneedbydate;
    @FindBy(how = How.ID, using = "Actv_need_by_date")
    //Activity Need By DAte calendar picker
    public WebElement actneedbydatepicker;
    @FindBy(how = How.ID, using = "Review_desired")  //checkbox review desired //
    public WebElement actreviewdesired;
    @FindBy(how = How.ID, using = "Actv_desc")
    public WebElement actdescrption;

    @FindBy(how = How.ID, using = "Notes_sects")      //Notes Tab under Opportunity//
    public WebElement Notes;
    @FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/ul/li[2]/button") //Add Note button
    public WebElement AddNote;
    @FindBy(how = How.ID, using = "note_title")  //Title textbox
    public WebElement Notetitle;
    @FindBy(how = How.ID, using = "note_desc")   //Description box
    public WebElement Notedesc;
    @FindBy(how = How.ID, using = "notes_submit")
    public WebElement NoteSubmit;
    @FindBy(how = How.ID, using = "notes_close")
    public WebElement NoteClose;

    @FindBy(how = How.ID, using = "Attachments_sects")  //Attachments Tab under Opportunity
    public WebElement Attachments;
    @FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/ul/li[3]/button") //Add Attachments button //
    public WebElement AddAttach;
    @FindBy(how = How.XPATH, using = "//*[@id='addAttachments']/div/div/div/div[1]/span")
    //Close button in add attachments//
    public WebElement closeattach;

    @FindBy(how = How.ID, using = "Claims_sects")       //Claims Tab under Opportunity//
    public WebElement Claims;
    @FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/ul/li[4]/button") //Add Claim button//
    public WebElement AddClaim;
    @FindBy(how = How.ID, using = "Claim_date")  //Claim Date
    public WebElement calclaimdate;
    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/table/tbody/tr[5]/td[4]")  //Claim Date picker
    public WebElement calclaimdatepick;
    @FindBy(how = How.ID, using = "Claim_status") //Claim Status
    public WebElement drpclaimstatus;
    @FindBy(how = How.ID, using = "Claim_amount") //Claim Amount
    public WebElement txtclaimamt;
    @FindBy(how = How.ID, using = "Claim_Description") //Description of Claim
    public WebElement txtclaimdesc;
    @FindBy(how = How.ID, using = "claim_submit")   //Submit
    public WebElement claimSubmit;
    @FindBy(how = How.ID, using = "claim_close")    //Cancel
    public WebElement claimcancel;

    @FindBy(how = How.ID, using = "Quotes_sects")     //Quotes Tab under Opportunity//
    public WebElement Quotes;
    @FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/ul/li[5]/button")  //Add quote button
    public WebElement addquote;
    @FindBy(how = How.ID, using = "add_quote_date")  //Quote Date
    public WebElement calquotedate;
    @FindBy(how = How.ID, using = "Add_quote_carrier")  //Carrier
    public WebElement drpquotecarrier;
    @FindBy(how = How.ID, using = "Add_quote_premium")  //Premium
    public WebElement txtquotepremium;
    @FindBy(how = How.ID, using = "quote_carr_decl")  //Carrier declined
    public WebElement chckquotecarrdeclined;
    @FindBy(how = How.ID, using = "quote_decline_reason")  // declined reason
    public WebElement drpquotecarrdeclinedreason;
    @FindBy(how = How.ID, using = "quote_mark_selec")  //Mark as Selected Quote
    public WebElement chckquotemarkselected;
    @FindBy(how = How.ID, using = "quote_submit")   //Submit
    public WebElement quoteSubmit;
    //  @FindBy(how = How.ID, using = "quote_close")   //Cancel
    @FindBy(how = How.XPATH, using = "//*[@id='QuoteModel']/div/div/div/div[1]/div/div[3]/button")
    public WebElement quotecancel;

    @FindBy(how = How.XPATH, using = "//*[@id='PDFURL']/button")  //Print PDF button //
    public WebElement Printpdf;

    @FindBy(how = How.ID, using = "history_sects")       //History Tab under Opportunity//
    public WebElement History;

    //Locating Elements in Business / opportunity -Activities tab
    @FindBy(how = How.LINK_TEXT, using = "Bind Subjectivity Follow-Up")  //Selecting Record in Activity Type column
    public WebElement ActivityType;
    @FindBy(how = How.ID, using = "Activity Performed")  //checkbox activity performed //
    public WebElement ActivityPerformed;
    @FindBy(how = How.ID, using = "follow_up_req")  //checkbox Followup required //
    public WebElement ChckFollowup;
    @FindBy(how = How.ID, using = "Actv_no_follows")
    public WebElement Numberfollowup;
    @FindBy(how = How.ID, using = "Actv_follow_freq")
    public WebElement freqfollowup;
    @FindBy(how = How.ID, using = "Actv_final_follow")
    public WebElement finalfollowup;
    @FindBy(how = How.ID, using = "activity_submit")  //Submit button - Activity Detail tab
    public WebElement ActDetSubmit;

    /*Created By Anusha E on 5th Jan 2018 */
    //Initializing elements in ToDoList page

    @FindBy(how = How.ID, using = "index2")         //To Do List button
    public WebElement btntodolist;
    @FindBy(how = How.XPATH, using = "//*[@id='dtactivities_wrapper']/div[1]/div[2]/div/div[3]")  //Reset button
    public WebElement btntodoReset;
    @FindBy(how = How.XPATH, using = "//*[@id='th-AssignedToACT']/ul/li[2]/span/div/ul/li[27]/a/label/input")
    public WebElement tododrpassignedto;
    /* @FindBy(how = How.LINK_TEXT, using = "Activities")  //Activities tab
     public WebElement todoActivities; */
    @FindBy(how = How.LINK_TEXT, using = "Follow Ups")  //Follow Ups tab
    public WebElement todofollowups;
    @FindBy(how = How.ID, using = "Business Name")   //TO DO business name search box
    public WebElement todobusinesssearch;
    @FindBy(how = How.ID, using = "Description")      //TO DO Description search box
    public WebElement tododescriptionsearch;
    @FindBy(how = How.LINK_TEXT, using = "Anusha feb22")  // Business Name record
    public WebElement linktodobname;
    @FindBy(how = How.ID, using = "select-ActivityTypeACT")  //Activity Type dropdown
    public WebElement drpselActtype;
    @FindBy(how = How.LINK_TEXT, using = "First Call")  // Activity Type record
    public WebElement linktodoacttype;

    @FindBy(how = How.ID, using = "aa-activity_type")  //Activity Type
    public WebElement todoacttype;
    @FindBy(how = How.ID, using = "aa-activity_status")  //Activity Status
    public WebElement todoactstatus;
    @FindBy(how = How.ID, using = "aa-dept_assigned")  //Department Assigned
    public WebElement tododeptassign;
    @FindBy(how = How.ID, using = "aa-assigned_to")  // Assigned To
    public WebElement todoassignedto;
    @FindBy(how = How.ID, using = "aa-coverage_need")  // Need by Date
    public WebElement todoneedbydate;
    @FindBy(how = How.ID, using = "aa-actvity_perfm")  //Activity Performed checkbox under Activity Detail
    public WebElement todoActivityperformed;
    @FindBy(how = How.ID, using = "aa-Review_desired")  //checkbox review desired //
    public WebElement todoreviewdesired;
    @FindBy(how = How.ID, using = "aa-Actv_Completed")  //checkbox Activity Completed //
    public WebElement todoactcompleted;
    @FindBy(how = How.ID, using = "aa-follow_up_req")  //Follow Up Required checkbox under Activity Detail
    public WebElement todofollowupreq;
    @FindBy(how = How.ID, using = "aa-Actv_no_follows")  //Number of Follow Ups under Activity Detail
    public WebElement todonumoffollowup;
    @FindBy(how = How.ID, using = "aa-Actv_follow_freq")  //Follow Up Frequency under Activity Detail
    public WebElement todofollowupfreq;
    @FindBy(how = How.ID, using = "aa-Actv_final_follow")  //Final Follow Up under Activity Detail
    public WebElement todofinalfollowup;

    @FindBy(how = How.ID, using = "add_follow")  // Add follow Up button
    public WebElement todoaddfollowup;
    @FindBy(how = How.ID, using = "close_follow")  // Close follow Up button
    public WebElement todoclosefollowup;
    @FindBy(how = How.CSS, using = "#followup_type")  // Follow Up Type
    public WebElement drpfollowuptype;
    @FindBy(how = How.ID, using = "followUp_date")  //Follow Up Date
    public WebElement calfollowupdate;
    @FindBy(how = How.XPATH, using = "/html/body/div[5]/div[1]/table/tbody/tr[5]/td[4]")
    // Follow Up date
    public WebElement drpfollowupdate;
    @FindBy(how = How.ID, using = "followup_Completed")  // Follow Up completed
    public WebElement chckfollowupcmplt;
    @FindBy(how = How.CSS, using = "#follow_dept_assigned")  // Dept assigned
    public WebElement drpdeptassig;
    @FindBy(how = How.CSS, using = "#follow_assigned_to")   //Assigned To
    //  @FindBy(how = How.NAME, using = "follow_assigned_to")
    public WebElement drpassigned;
    @FindBy(how = How.ID, using = "followUp_submit")  // Follow Up Submit
    public WebElement followupsubmit;
    @FindBy(how = How.ID, using = "followUp_cancel")  // Follow Up Cancel
    public WebElement followupcancel;
    @FindBy(how = How.CSS, using = "#\\31 2\\2f 25\\2f 2018 > span")  //Edit date follow up
    public WebElement editfolowup;
    @FindBy(how = How.CSS, using = "#th-ActivityTypeOppsActvFollowUps > ul > li > span")
    public WebElement sorteditdate;
    @FindBy(how = How.ID, using = "follow_date")  //New follow up date
    public WebElement newfolowupdate;
    @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom > div.datepicker-days > table > tbody > tr:nth-child(5) > td:nth-child(4)")
    //New follow up date picker
    public WebElement newfolowupdatepick;
    @FindBy(how = How.CSS, using = "#follow_date_submit")  // Edit Follow Up Submit
    public WebElement editfollowupsubmit;
    @FindBy(how = How.ID, using = "follow_date_close")  // Edit Follow Up Close
    public WebElement editfollowupclose;
    @FindBy(how = How.LINK_TEXT, using = "Complete Follow Up")  //Complete Folow Up button
    public WebElement btncmpltfollowup;
    // @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[4]/button[2]")    //Alert cancel
    @FindBy(how = How.CSS, using = "body > div.jconfirm.jconfirm-light.jconfirm-open > div.jconfirm-scrollpane > div > div > div > div > div > div > div > div.jconfirm-buttons > button:nth-child(2)")
    public WebElement popupcancel;
    @FindBy(how = How.CSS, using = "body > div.jconfirm.jconfirm-light.jconfirm-open > div.jconfirm-scrollpane > div > div > div > div > div > div > div > div.jconfirm-buttons > button:nth-child(1)")
    //Alert confirm for complte follow ups
    public WebElement popupconfirm;

    @FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/ul/li[1]/button") //To DO Add Note button //
    public WebElement ToDoAddNote;
    @FindBy(how = How.ID, using = "note_title")  //To DO Title textbox //
    public WebElement todoNotetitle;
    @FindBy(how = How.ID, using = "note_desc")   //To DO Description box//
    public WebElement todoNotedesc;
    @FindBy(how = How.ID, using = "notes_submit")
    public WebElement todoNoteSubmit;
    @FindBy(how = How.ID, using = "notes_close")
    public WebElement todoNoteCancel;
    @FindBy(how = How.ID, using = "Notes_sects")      //To Do Notes Tab //
    public WebElement todoNotes;

    @FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/ul/li[3]/button") //To Do Add Claim button//
    public WebElement todoAddClaim;

    @FindBy(how = How.ID, using = "Claim_date")  //To Do Claim Date
    public WebElement todocalclaimdate;
    @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom > div.datepicker-days > table > tbody > tr:nth-child(5) > td:nth-child(6)")
    //To Do Claim Date
    public WebElement todoclaimdatepick;
    @FindBy(how = How.ID, using = "Claim_status") //To Do Claim Status
    public WebElement tododrpclaimstatus;
    @FindBy(how = How.ID, using = "Claim_amount") //To Do Claim Amount
    public WebElement todotxtclaimamt;
    @FindBy(how = How.ID, using = "Claim_Description") //To Do Description of Claim
    public WebElement todotxtclaimdesc;
    @FindBy(how = How.ID, using = "claim_submit")   //To Do Submit
    public WebElement todoclaimSubmit;
    @FindBy(how = How.ID, using = "claim_close")    //To Do Cancel
    public WebElement todoclaimcancel;
    @FindBy(how = How.ID, using = "Claims_sects")       //Claims Tab under Activity//
    public WebElement ToDoClaims;

    @FindBy(how = How.ID, using = "Quotes_sects")     //Quotes Tab under Activity//
    public WebElement ToDoQuotes;
    @FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/ul/li[4]/button")  //To Do Add quote button
    public WebElement todoaddquote;
    @FindBy(how = How.ID, using = "add_quote_date")  //To Do Quote Date
    public WebElement todocalquotedate;
    @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom > div.datepicker-days > table > tbody > tr:nth-child(3) > td:nth-child(6)")
    public WebElement todoquotedatepick;
    @FindBy(how = How.ID, using = "Add_quote_carrier")  //To Do Carrier
    public WebElement tododrpquotecarrier;
    @FindBy(how = How.ID, using = "Add_quote_premium")  //To Do Quoted Premium
    public WebElement todotxtquotepremium;
    @FindBy(how = How.ID, using = "quote_submitted")  //Submitted
    public WebElement todochcksubmitted;
    @FindBy(how = How.ID, using = "quote_carr_decl")  //To Do Carrier declined
    public WebElement todochckquotecarrdeclined;
    @FindBy(how = How.ID, using = "quote_decline_reason")  // To Do declined reason
    public WebElement tododrpquotecarrdeclinedreason;
    @FindBy(how = How.ID, using = "quote_mark_selec")  //To Do Mark as Selected Quote
    public WebElement todochckquotemarkselected;
    @FindBy(how = How.ID, using = "quote_submit")   //To Do Submit
    public WebElement todoquoteSubmit;
    @FindBy(how = How.ID, using = "quote_close")   //To Do Cancel
    public WebElement todoquotecancel;
    @FindBy(how = How.ID, using = "carrier_5949")     //Is Carrier Declined Checkbox in quote grid
    public WebElement chckiscarrierdeclined;
    @FindBy(how = How.LINK_TEXT, using = "confirm")   // Confirm pop-up
    public WebElement popbtnConfirm;
    @FindBy(how = How.LINK_TEXT, using = "cancel")    // Cancel pop-up
    public WebElement popbtncancel;
    @FindBy(how = How.XPATH, using = "//*[@id='quotes_section_datatables']/tbody/tr[3]/td[7]/center")
    public WebElement editquotesymbol;

    @FindBy(how = How.ID, using = "typeaheadValue")  // Global search text box
    public WebElement txtGlobalSearch;
    //  @FindBy(how = How.XPATH, using = "//*[@id='the-basics-new']/span/div/div/p/a/strong")
    @FindBy(how = How.CSS, using = "#the-basics-new > span > div > div > p > a") //Global Search link select
    public WebElement linkGlobalSearchSelection;

    // Initializing elements for KPI's //
    @FindBy(how = How.ID, using = "kpi1")
    public WebElement tpmmynewopportunities;

    @FindBy(how = How.ID, using = "kpi2")
    public WebElement tpmmycommission;

    @FindBy(how = How.ID, using = "kpi3")
    public WebElement tpmmyopportunities;

    @FindBy(how = How.ID, using = "kpi4")
    public WebElement tpmmyboundopportunities;

    @FindBy(how = How.ID, using = "tabbtnopportunity")
    public WebElement opportunitytab;
    @FindBy(how = How.CSS, using = "#dtopportunity > tbody > tr > td:nth-child(12) > center > a > span")
    //View PDF in opportunity tab grid
    public WebElement viewpdf;

    // Initializing elements for column sorting //
    @FindBy(how = How.CSS, using = "#th-CoverageOpp > ul > li:nth-child(1) > span")
    public WebElement colsortcoverage;
    @FindBy(how = How.CSS, using = "#th-BusinessNameOpp > ul > li:nth-child(1) > span")
    public WebElement colsortbusiness;
    @FindBy(how = How.CSS, using = "#th-BusinessClassOpp > ul > li:nth-child(1) > span")
    public WebElement colsortclass;
    @FindBy(how = How.CSS, using = "#th-BusinessNameBus > ul > li:nth-child(1) > span")
    public WebElement colsortbusinessname;
    @FindBy(how = How.CSS, using = "#th-BrandNameBus > ul > li:nth-child(1) > span")
    public WebElement colsortbrandname;
    @FindBy(how = How.CSS, using = "#th-BrokerBus > ul > li:nth-child(1) > span")
    public WebElement colsortbrokername;
    @FindBy(how = How.CSS, using = "body > header > nav > div > div > div:nth-child(3) > div.topbar-actions > div > button > i")
    public WebElement clickicon;
    @FindBy(how = How.CSS, using = "body > header > nav > div > div > div:nth-child(3) > div.topbar-actions > div > ul > li.btn.btn-block.userMenu > a > h4")
    public WebElement btnlogout;

    // Initializing elements for Opportunity Status to Bound or proposed
    //  @FindBy(how = How.CSS, using = "#add_quote_date")
    @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom > div.datepicker-days > table > tbody > tr:nth-child(3) > td.today.day")
    public WebElement calOpportunityDetailQuoteDate;
    @FindBy(how = How.CSS, using = "#Add_quote_carrier")   //Quote Carrier
    public WebElement cbOpportunityDetailQuoteCarrier;
    @FindBy(how = How.CSS, using = "#Add_quote_premium")   //quote Premium
    public WebElement txtOpportunityDetailQuotedPremium;
    @FindBy(how = How.ID, using = "quote_submit")
    public WebElement btnOpportunityDetailQuoteSubmit;    //quotesubmit
    @FindBy(how = How.ID, using = "quote_close")
    public WebElement btnOpportunityDetailQuoteCancel;   //quotecancel
    @FindBy(how = How.CSS, using = ".Bound_Status_quote_close")
    public WebElement btnAddQuoteXClick;
    @FindBy(how = How.CSS, using = "#bs_quotes_section_datatables > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6) > center:nth-child(1) > button:nth-child(1)")
    public WebElement btnOpportunityDetailQuoteMarkSelected;
    @FindBy(how = How.CSS, using = "body > div.jconfirm.jconfirm-light.jconfirm-open > div.jconfirm-scrollpane > div > div > div > div > div > div > div > div.jconfirm-buttons > button:nth-child(1)")
    public WebElement btnOpportunityDetailQuoteMarkSelectedConfirm;
    // Initializing elements for Lead Assignment
    @FindBy(how = How.LINK_TEXT, using = "Commercial Auto")
    //Selecting Coverage under Opportunities tab in Lead Assignment page
    public WebElement leadcoverage;
    @FindBy(how = How.ID, using = "assign_rules")   //Assignment Rules button
    public WebElement btnassignmentrules;
    @FindBy(how = How.ID, using = "amsEntry_sects")   //AMS Entry Info tab
    public WebElement AMSentryinfo;
    @FindBy(how = How.ID, using = "notes-tab")   //Notes tab
    public WebElement Leadnotes;
    @FindBy(how = How.ID, using = "lead_brand")   //Brand
    public WebElement Leadbrand;
    @FindBy(how = How.ID, using = "lead_subbrand")   //Sub Brand
    public WebElement Leadsubbrand;
    @FindBy(how = How.ID, using = "lead_businessclass")   //Class Of Business
    public WebElement Leadclassofbusiness;
    @FindBy(how = How.ID, using = "lead_clientID")   //Client ID
    public WebElement Leadclientid;
    @FindBy(how = How.ID, using = "lead_broker")   //broker
    public WebElement Leadbroker;
    @FindBy(how = How.ID, using = "lead_access")   //Access level
    public WebElement Leadaccesslevel;
    @FindBy(how = How.ID, using = "lead_service")   //Service level
    public WebElement Leadservicelevel;
    @FindBy(how = How.ID, using = "lead_description")   //Description of operations
    public WebElement Leaddescofops;
    @FindBy(how = How.ID, using = "lead_submit")   //Submit
    public WebElement LeadSubmitbtn;

    public Loginpage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        txtemail.sendKeys(email);
        txtpassword.sendKeys(password);
    }

    public void signInButton() {
        btnSignIn.click();
    }

    public void home() {
        btnhome.click();
    }

    public void bussreset() {
        resetbtn.click();
    }

    public void reset() {
        btnReset.click();
    }

    public void addBusinessClick() {
        btnAddBusiness.click();
    }

    public void addBusiness(String brand, String subbrand, String businessname, String businessclass, String clientid, String broker, String tpmreferrername,
                            String referringcompany, String prospectorigin, String leadassignedby, String needbydate, String yearsinbusiness, String experience,
                            String website, String typeoforganization, String ifotherpleasespecify, String descriptionofoperations, String estannualgrosssales,
                            String estannualpayroll, String noofactiveowners, String fte, String pte, String fein, String coveragetype) throws InterruptedException{
            cbBrand.click();
            Thread.sleep(3000);
            cbBrand.sendKeys(brand);
            Thread.sleep(3000);
            cbBrand.click();
            Thread.sleep(3000);
            cbSubBrand.sendKeys(subbrand);
            Thread.sleep(3000);
            txtBusinessNameForm.sendKeys(businessname);
            Thread.sleep(3000);
            cbBusinessClassForm.sendKeys(businessclass);
            Thread.sleep(3000);
            txtClientIDForm.sendKeys(clientid);
            Thread.sleep(3000);
            cbBrokerForm.sendKeys(broker);
            Thread.sleep(3000);
            txtReferrerNameForm.sendKeys(tpmreferrername);
            Thread.sleep(3000);
            txtReferrerCompanyForm.sendKeys(referringcompany);
            Thread.sleep(3000);
            cbProspectOriginForm.sendKeys(prospectorigin);
            Thread.sleep(3000);
            txtUrgencyNeedByDateForm.sendKeys(needbydate);
            Thread.sleep(3000);
            txtYearsInBusinessForm.sendKeys(yearsinbusiness);
            Thread.sleep(3000);
            txtExperienceForm.sendKeys(experience);
            Thread.sleep(3000);
            txtWebsiteForm.sendKeys(website);
            Thread.sleep(3000);
            cbTypeOfOrganizationForm.sendKeys(typeoforganization);
            Thread.sleep(3000);
            txtIfOtherPleaseSpecifyForm.sendKeys(ifotherpleasespecify);
            Thread.sleep(3000);
            txtDescriptionOfOperationsForm.sendKeys(descriptionofoperations);
            Thread.sleep(3000);
            txtESTAnnualGrossSalesForm.sendKeys(estannualgrosssales);
            Thread.sleep(3000);
            txtESTAnnualPayrollForm.sendKeys(estannualpayroll);
            Thread.sleep(3000);
            txtNoOfActiveOwnersForm.sendKeys(noofactiveowners);
            Thread.sleep(3000);
            txtFTEForm.sendKeys(fte);
            Thread.sleep(3000);
            txtPTEForm.sendKeys(pte);
            Thread.sleep(3000);
            txtFEINForm.sendKeys(fein);
            Thread.sleep(3000);
            cbCoverageTypeForm.sendKeys(coveragetype);
            Thread.sleep(3000);
            }

    public void addbusinesssubmit() {
        btnAddBusinessSubmitForm.click();
        btnAddBusinessCancelForm.click();
    }

    public void businessdetails()throws InterruptedException{
            oppbusinessname.click();
            Thread.sleep(2000);
            linkBussDetails.click();
            Thread.sleep(2000);
            }

    public void addcontact() {
        BussAddContact.click();
        txtcontactname.sendKeys("test..2354");
        txtcontactwork.sendKeys("(777) 77 - 777");
        txtcontactemail.sendKeys("dec23@gmail.com");
        txtcontactmobile.sendKeys("(959)595-9581");
        txtcontacttitle.sendKeys("titlecond");
        drpcontacttype.click();
        drpcontacttype.sendKeys("CEO");
        drpcontacttype.click();
        txtcontactmemnum.sendKeys("1458789");
        calcontactDOB.sendKeys("01/27/2018");
        chckmakeprimary.click();
        btncontactsubmit.click();
        btncontactcancel.click();
    }

    public void editcontact() {
        EditAddContact.click();
    }

    public void editcontactfields() {
        txtcontactemail.clear();
        txtcontactemail.sendKeys("dectes21@gmail.com");
        txtcontactmemnum.clear();
        txtcontactmemnum.sendKeys("11315");
        btncontactsubmit.click();
        btncontactcancel.click();
    }

    public void addaddressesclick() {
        BussAddAddress.click();
    }

    public void addaddresses(String addresstype, String city, String street1, String street2, String region, String zip, String country)throws InterruptedException {
            drpaddresstype.click();
            Thread.sleep(2000);
            drpaddresstype.sendKeys(addresstype);
            Thread.sleep(2000);
            drpaddresstype.click();
            Thread.sleep(2000);
            txtcity.sendKeys(city);
            Thread.sleep(2000);
            txtStreet1.sendKeys(street1);
            Thread.sleep(2000);
            txtStreet2.sendKeys(street2);
            Thread.sleep(2000);
            //   txtregion.sendKeys(region);
            //  txtzip.sendKeys(zip);
            txtcountry.sendKeys(country);
            Thread.sleep(2000);
            btnaddresssubmit.click();
            Thread.sleep(2000);
            btnaddresscancel.click();
            Thread.sleep(2000);
            }

    public void editaddress()throws InterruptedException{
            editaddress.click();
            Thread.sleep(2000);
            btnaddresssubmit.click();
            Thread.sleep(2000);
            }

    public void updateaddress() {
        txtcity.clear();
        txtcity.sendKeys("Hyd girls1");
        txtStreet1.clear();
        txtStreet1.sendKeys(" testing1");
        txtStreet2.clear();
        txtStreet2.sendKeys(" Bound");
        txtcountry.sendKeys("India..");
        btnaddresssubmit.click();
        btnaddresscancel.click();
    }

    public void businessgridclick() {
        btnBusinessGrid.click();
    }

    public void businessnamesearch(String businessname)throws InterruptedException {
          //btnBusinessGrid.click();
            Thread.sleep(2000);
            txtBusinessName.click();
            Thread.sleep(1000);
            txtBusinessName.sendKeys(businessname);
            Thread.sleep(3000);
    }

    @FindBy(how = How.CSS, using = "#dtopportunity > tbody > tr.odd > td.sorting_1 > div > a")
    public WebElement oppselectgrid;

    public void oppselect()throws InterruptedException {
          oppselectgrid.click();
          }

    public void SelectBusiness() {
        linkBusinessName.click();
    }

    @FindBy(how = How.CSS, using = "#dtbusiness > tbody > tr.odd > td:nth-child(1) > a")
    public WebElement bussinessselect; //select business in bussiness grid

    public void selectbusiness1() {
        bussinessselect.click();
    }

    public void businessaddactivityclick()throws InterruptedException{
            Thread.sleep(2000);
            btnAddActivity.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"addActivity-button\"]")
    public WebElement clickingonaddactivitybutton;

    public void businessaddactivity(String ActivityType, String ActivityStatus, String DepartmentAssigned, String AssignedTo,
                                    String NeedByDate, String ActivityPerformed, String ActivityCompleted, String FollowUpRequired,
                                    String ReviewDesired, String Description)throws InterruptedException {

            //clickingonaddactivitybutton.click();
            //Thread.sleep(4000);
            cbActivityType.sendKeys(ActivityType);
            Thread.sleep(1000);
            cbActivityStatus.sendKeys(ActivityStatus);
            Thread.sleep(1000);
            cbDepartmentAssigned.click();
            Thread.sleep(1000);
            cbDepartmentAssigned.sendKeys(DepartmentAssigned);
            Thread.sleep(1000);
            cbDepartmentAssigned.click();
            Thread.sleep(1000);
            cbAssignedTo.click();
            Thread.sleep(1000);
            cbAssignedTo.sendKeys(AssignedTo);
            Thread.sleep(1000);
            cbAssignedTo.click();
            Thread.sleep(1000);
            txtActivityNeedByDate.sendKeys(NeedByDate);
            Thread.sleep(1000);
            chkReviewDesired.sendKeys(ReviewDesired);
            txtActivityDescription.sendKeys(Description);
            }

    public void businessaddactivitysubmit() {
        btnActivitySubmit.click();
        btnActivityClose.click();
    }

    public void selectactivitiestab() {
        btnActivitiesTab.click();
    }

    public void businessaddnoteclick() {
        btnAddNote.click();
    }

    public void businessaddnote(String Title, String Description) {
        txtNoteTitle.sendKeys(Title);
        txtNoteDescription.sendKeys(Description);
    }

    public void businessaddnotesubmit() {
        btnAddNoteSubmit.click();
        btnAddNoteClose.click();
    }

    public void businessnotestab() {
        btnNoteTab.click();
    }

    public void businessaddopportunityclick() {
        btnBussAddOpportunity.click();
    }

    public void businessaddopportunity(String Status, String CoverageType, String NeedByDate, String OpportunityReceivedDate,
                                       String DepartmentAssigned, String AssignedTo, String OpportunityAssignedDate,
                                       String CurrentInsuranceCarrier, String RenewalDate, String CurrentPremium,
                                       String PriorClaims, String Origin, String DescribePriorClaims) {
        cbStatus.sendKeys(Status);
        cbCoverageType.sendKeys(CoverageType);
        txtNeedbyDate.click();
        txtNeedbyDate.sendKeys(NeedByDate);
        txtNeedbyDate.click();
        txtOpportunityReceivedDate.click();
        txtOpportunityReceivedDate.sendKeys(OpportunityReceivedDate);
        txtOpportunityReceivedDate.click();
        txtOpportunityAssignedDate.sendKeys(OpportunityAssignedDate);
        txtCurrentInsurance.sendKeys(CurrentInsuranceCarrier);
        txtRenewalDate.sendKeys(RenewalDate);
        txtCurrentPremium.sendKeys(CurrentPremium);
        chkPriorClaims.sendKeys(PriorClaims);
        cbOrigin.sendKeys(Origin);
        txtDescribePriorClaims.sendKeys(DescribePriorClaims);
    }

    public void businessaddopportunitysubmit() {
        btnAddOpportunitySubmit.click();
        btnAddOpportunityCancel.click();
    }

    public void clickopportunitiestab() {
        opportunitiestab.click();
    }

    /* opportunity page starts*/

    public void selectcoverageunderopportunitiestab() {
            linkBusinessName.click();
            //Thread.sleep(3000);
    }

    @FindBy(how = How.ID, using = "coverage_type_id")
    public WebElement oppcoveragetype;

    @FindBy(how = How.ID, using = "opp_broker_id")
    public WebElement oppbroker;

    @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom > div.datepicker-days > table > tbody > tr:nth-child(2) > td.active.day")
    public WebElement oppassigneddate;

    @FindBy(how = How.ID, using = "service_level")
    public WebElement oppservicelevel;

    @FindBy(how = How.ID, using = "prospect_origin")
    public WebElement oppprospectorigin;

    @FindBy(how = How.XPATH, using = "/html/body/div[5]/div[1]/table/tbody/tr[2]/td[5]")
    public WebElement opprecieveddate;

    @FindBy(how = How.XPATH, using = "/html/body/div[5]/div[1]/table/tbody/tr[3]/td[3]")
    public WebElement effectivedatepic; //opp effective date picker

    public void editopportunitydetail(String status, String coveragetype, String wheniscoverageneeded, String needbydate,
                                      String opportunityreceiveddate, String broker, String assigneddate,
                                      String currentinsurancecarrier, String renewaldate, String currentpremium, String accesslevel, String servicelevel, String prospectorigin, String businesstype, String accountmanager, String effectivedate, String commission, String brokerfee)throws InterruptedException {

            Thread.sleep(2000);
            drpStatus.sendKeys(status);
            Thread.sleep(2000);
            txtcoverageneeded.sendKeys(wheniscoverageneeded);
            Thread.sleep(2000);
            oppbroker.sendKeys(broker);
            Thread.sleep(2000);
            //oppassigneddate.sendKeys(AssignedDate);
            txtcurrentinsurancecarrier.sendKeys(currentinsurancecarrier);
            Thread.sleep(2000);
            txtcurrentpremium.sendKeys(currentpremium);
            drpdownaccess.sendKeys(accesslevel);
            oppservicelevel.sendKeys(servicelevel);
            oppprospectorigin.sendKeys(prospectorigin);
            caleffectivedate.click();
            Thread.sleep(1000);
            effectivedatepic.click();
            Thread.sleep(2000);
            txtcommission.sendKeys(commission);
            Thread.sleep(1000);
            txtbrokerfee.sendKeys(brokerfee);
            Thread.sleep(1000);
            oppbusinesstype.sendKeys(businesstype);
            Thread.sleep(1000);
            accntmanager.sendKeys(accountmanager);
            Thread.sleep(1000);
            btnSubmit.click();
            Thread.sleep(1000);
            btnhome.click();
            Thread.sleep(1000);
            }

    public void opportunitydetailsubmit()throws InterruptedException {
            btnSubmit.click();
            Thread.sleep(1000);
           }

    /* opportunity page starts*/
    public void clickopprecord() {
        linkCoverage.click();
    }

    public void oppdetail(String status, String coveragetype, String wheniscoverageneeded, String needbydate, String opportunityreceiveddate,
                          String departmentassigned, String assignedtoopportunity, String assigneddate, String currentinsurancecarrier,
                          String renewaldate, String currentpremium, String accesslevel, String quotedate, String quotedcarrier) {
        drpStatus.sendKeys(status);
        txtcoveragetype.sendKeys(coveragetype);
        txtcoverageneeded.sendKeys(wheniscoverageneeded);
        calneedbydate.sendKeys(needbydate);
        txtopprecdate.sendKeys(opportunityreceiveddate);
        txtcurrentinsurancecarrier.sendKeys(currentinsurancecarrier);
        txtcurrentpremium.sendKeys(currentpremium);
        drpdownaccess.sendKeys(accesslevel);
        calrenewaldate.sendKeys(renewaldate);
        txtquotedcarrier.sendKeys(quotedcarrier);
        checktermlife.click();
        btnSubmit.click();
    }

    public void ClickAddActivity() {
        AddActivity.click();  // Add Activity button //
       }

    @FindBy(how = How.ID, using = "Activ_assign_to")
    public WebElement activityassigned;

    @FindBy(how = How.XPATH, using = "/html/body/div[6]/div[1]/table/tbody/tr[1]/td[2]")
    public WebElement needdatecal;

    public void addactivity(String activitytype, String activitystatus, String departmentassigned, String assignedto,
                            String needbydate, String reviewdesired, String followupreq, String description)throws InterruptedException {

            drpActivitytype.click();
            Thread.sleep(3000);
            drpActivitytype.sendKeys(activitytype);
            Thread.sleep(3000);
            drpActivitytype.click();
            Thread.sleep(3000);
            cbDepartmentAssigned.click();
            Thread.sleep(3000);
            cbDepartmentAssigned.sendKeys(departmentassigned);
            Thread.sleep(3000);
            activityassigned.click();
            Thread.sleep(3000);
            activityassigned.sendKeys(assignedto);
            Thread.sleep(3000);
            txtActivityNeedByDate.click();
            needdatecal.click();
            //txtActivityNeedByDate.sendKeys(needbydate);
            Thread.sleep(5000);
            actreviewdesired.click();
            Thread.sleep(3000);
            ChckFollowup.click();
            Thread.sleep(3000);
            Numberfollowup.sendKeys("2");
            Thread.sleep(3000);
            freqfollowup.sendKeys("15days");
            Thread.sleep(3000);
            finalfollowup.sendKeys("30days");
            Thread.sleep(3000);
            actdescrption.sendKeys(description);
            Thread.sleep(3000);
            ActSubmit.click();
            ActCancel.click();
           }

    public void ClickAddNote() {
        Activities.click();
        AddNote.click();
    }

    public void addnote(String title, String description) {
        Notetitle.sendKeys(title);
        Notedesc.sendKeys(description);
        NoteSubmit.click();
        NoteClose.click();
        Notes.click();
    }

    public void AddAttachments() {
        AddAttach.click();
        closeattach.click();                     //closing attachments box //
        Attachments.click();
    }

    public void ClickAddClaim() {
        AddClaim.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[6]/div[1]/table/tbody/tr[1]/td[2]")
    public WebElement claimdatecal;

    public void addclaim(String claimdate, String claimstatus, String claimamount, String descriptionofclaim)throws InterruptedException {
            calclaimdate.click();
            Thread.sleep(2000);
            claimdatecal.click();
            Thread.sleep(2000);
            // calclaimdatepick.click();
            drpclaimstatus.sendKeys(claimstatus);
            Thread.sleep(2000);
            txtclaimamt.sendKeys(claimamount);
            Thread.sleep(2000);
            txtclaimdesc.sendKeys(descriptionofclaim);
            Thread.sleep(2000);
            claimSubmit.click();
            Thread.sleep(2000);
            claimcancel.click();
            }

    public void ClickAddQuote() {
        // Claims.click();
        addquote.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[6]/div[1]/table/tbody/tr[1]/td[2]")
    public WebElement quotedatecal;

    public void addquote(String quotedate, String carrier, String quotedpremium, String carrierdeclined, String declinedreason)throws InterruptedException {
            calquotedate.click();
            Thread.sleep(2000);
            quotedatecal.click();
            Thread.sleep(2000);
            drpquotecarrier.sendKeys(carrier);
            Thread.sleep(2000);
            txtquotepremium.sendKeys(quotedpremium);
            Thread.sleep(2000);
            todochcksubmitted.click();
            Thread.sleep(2000);
            chckquotecarrdeclined.click();
            Thread.sleep(2000);
            drpquotecarrdeclinedreason.sendKeys(declinedreason);
            Thread.sleep(2000);
            quoteSubmit.click();
            quotecancel.click();
            Quotes.click();
           }

    public void historytab()throws InterruptedException {
            Thread.sleep(1000);
            History.click();
            Thread.sleep(1000);
    }

    public void printpdf() {
        Printpdf.click();   //Print PDF
    }

    public void todolist() {
        btntodolist.click();
    }

    @FindBy(how = How.CSS, using = "#activity_section_datatables > tbody > tr > td:nth-child(1) > a")
    public WebElement oppactivitytype;  //activity in opportunity page

    public void activities()throws InterruptedException {
            Activities.click();
            Thread.sleep(1000);
            oppactivitytype.click();
            Thread.sleep(1000);
           }

    @FindBy(how = How.CSS, using = "#th-BusinessNameACT > div")
    public WebElement activitybusinessname; //business name column in activities tab

    public void activitytype()throws InterruptedException {
            activitybusinessname.click();
            Thread.sleep(1000);
            linktodoacttype.click();
            Thread.sleep(1000);
            }

    public void activitydetail(String activitytype, String activitystatus, String departmentassigned, String assignedto, String needbydate,
                               String activityperformed, String activitycompleted, String reviewdesired, String followuprequired,
                               String numberoffollowups, String followupfrequency, String finalfollowup, String description) {
        todoacttype.sendKeys(activitytype);
        tododeptassign.sendKeys(departmentassigned);
        todoassignedto.sendKeys(assignedto);
        todoneedbydate.sendKeys(needbydate);
        todoactcompleted.click();
        todoreviewdesired.click();
        todofollowupreq.click();
        todonumoffollowup.sendKeys(numberoffollowups);
        todofollowupfreq.sendKeys(followupfrequency);
        todofinalfollowup.sendKeys(finalfollowup);
        actdescrption.sendKeys(description);
        ActDetSubmit.click();
    }

    public void followups() {
        todoaddfollowup.click();
    }

    public void editfollowup(String followuptype, String followupdate, String deptassigned, String assignedto)throws InterruptedException {
            drpfollowuptype.sendKeys(followuptype);
            Thread.sleep(2000);
            calfollowupdate.click();
            Thread.sleep(2000);
            drpfollowupdate.click();
            Thread.sleep(3000);
            //chckfollowupcmplt.click();
            // Thread.sleep(2000);
            drpdeptassig.sendKeys(deptassigned);
            Thread.sleep(2000);
            drpassigned.click();
            Thread.sleep(2000);
            drpassigned.sendKeys(assignedto);
            Thread.sleep(2000);
            drpassigned.click();
            Thread.sleep(2000);
            followupsubmit.click();
            Thread.sleep(2000);
            }

    public void editdate() {
        //  sorteditdate.click();
        editfolowup.click();
    }

    public void editFUDate()throws InterruptedException {
             newfolowupdate.click();
            Thread.sleep(2000);
            newfolowupdatepick.click();
            Thread.sleep(2000);
            editfollowupsubmit.click();
            Thread.sleep(2000);
             }

    public void completeFU() {
        btncmpltfollowup.click();
        popupconfirm.click();
    }

    public void clicktodoaddnote() {
        ToDoAddNote.click();
    }

    public void todoaddnote(String title, String description) {
        todoNotetitle.sendKeys(title);
        todoNotedesc.sendKeys(description);
        todoNoteSubmit.click();
        todoNoteCancel.click();
        todoNotes.click();
    }

    public void clicktodoaddclaim() {
        todoAddClaim.click();
    }

    public void todoaddclaim(String claimdate, String claimstatus, String claimamount, String descriptionofclaim) {
        todocalclaimdate.click();
        todoclaimdatepick.click();
        tododrpclaimstatus.sendKeys(claimstatus);
        todotxtclaimamt.sendKeys(claimamount);
        todotxtclaimdesc.sendKeys(descriptionofclaim);
        todoclaimSubmit.click();
        todoclaimcancel.click();
        ToDoClaims.click();
    }

    public void ClickToDoAddQuote() {
        todoaddquote.click();
    }

    @FindBy(how = How.ID, using = "quote_title")
    public WebElement quotewindow;  //quote window

    @FindBy(how = How.CSS, using = "body > div.container-fluid > div.page-content.page-content-popup > div > div:nth-child(5) > div > ul > li:nth-child(1) > a")
    public WebElement detailtab;   //detail tab

    public void todoaddquote(String quotedate, String carrier, String quotedpremium, String carrierdeclined, String declinedreason)throws InterruptedException {
            quotewindow.click();
            Thread.sleep(2000);
            todocalquotedate.click();
            Thread.sleep(1000);
            todoquotedatepick.click();
            Thread.sleep(1000);
            tododrpquotecarrier.sendKeys(carrier);
            Thread.sleep(1000);
            todotxtquotepremium.sendKeys(quotedpremium);
            Thread.sleep(1000);
            todochcksubmitted.click();
            Thread.sleep(1000);
            todochckquotecarrdeclined.click();
            Thread.sleep(1000);
            tododrpquotecarrdeclinedreason.sendKeys(declinedreason);
            Thread.sleep(1000);
            todochckquotemarkselected.click();
            Thread.sleep(1000);
            todoquoteSubmit.click();
            Thread.sleep(2000);
            detailtab.click();
            Thread.sleep(1500);
    }

    public void globalsearch(String GlobalSearch) {
        txtGlobalSearch.sendKeys(GlobalSearch);
    }

    public void globalsearchlinkselect() {
        linkGlobalSearchSelection.click();
    }

    public void reportbutton() {
        btnReports.click();
    }

    public void accountmanagement() {
        btnaccountmanagement.click();
    }

    public void importpolicies() {
        btnimportpolicies.click();
    }

    public void importsubmit() {
        btnsubmitpolicies.click();
    }

    public void alertok() {
        alertokbtn.click();
    }

    public void closeimportpolicies() {
        btncloseimportpolicies.click();
    }

    public void viewyearlysummary() {
        btnviewyearlysummary.click();
    }

    public void closeyearlysummary() {
        btncloseyearlysummary.click();
    }

    public void sortcoverage() {
        colsortcoverage.click();
    }

    public void sortbusinessname() {
        colsortbusiness.click();
    }

    public void sortclass() {
        colsortclass.click();
    }

    public void clickbusinesspage() {
        btnBusinessGrid.click();
    }

    public void sortbbusinessname() {
        colsortbusinessname.click();
    }

    public void sortbbrandname() {
        colsortbrandname.click();
    }

    public void sortbroker() {
        colsortbrokername.click();
    }

    public void clicklogouticon() {
        clickicon.click();
    }

    public void logout() {
        btnlogout.click();
    }

    public void mynewoppportunities() {
        tpmmynewopportunities.click();
    }

    public void mycommissionthismonth() {
        tpmmycommission.click();
    }

    public void myopportunities() {
        tpmmyopportunities.click();
    }

    public void myboundopportunities() {
        tpmmyboundopportunities.click();
    }

    public void opportunitytab() {
        opportunitytab.click();
    }

    public void viewPDF() {
        viewpdf.click();
    }

    public void leadassignmentbutton() {
        btnleadassignment.click();
    }

    public void leadcoverage() {
        leadcoverage.click();
    }

    public void leadoppassign(String brand, String subbrand, String classofbusiness, String clientid, String broker, String accesslevel, String servicelevel, String descriptionofoperations) {
        Leadbrand.sendKeys(brand);
        Leadsubbrand.sendKeys(subbrand);
        Leadclassofbusiness.sendKeys(classofbusiness);
        Leadclientid.sendKeys(clientid);
        Leadbroker.sendKeys(broker);
        Leadaccesslevel.sendKeys(accesslevel);
        Leadservicelevel.sendKeys(servicelevel);
        Leaddescofops.sendKeys(descriptionofoperations);
    }

    public void leadsubmit() {
        LeadSubmitbtn.click();
    }

    public void assignmentrules() {
        btnassignmentrules.click();
    }

    public void amsentryinfotab() {
        AMSentryinfo.click();
    }

    public void leadnotes() {
        Leadnotes.click();
    }

    public void editopportunitydetailstatusasboundorproposed(String status)  //Edit Opportunity Status as Bound/Proposed
    {
        drpStatus.sendKeys(status);
    }

    public void opportunitydetailaddquotesubmit() {          //Submit Button in Add Quote
        btnOpportunityDetailQuoteSubmit.click();
    }

    public void editopportunitydetailaddquote(String QuoteDate, String Carrier, String QuotedPremium) {
        calOpportunityDetailQuoteDate.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
        calOpportunityDetailQuoteDate.sendKeys(QuoteDate);            //edit Opportunity Status as Bound/Proposed and Add Quote Popup
        cbOpportunityDetailQuoteCarrier.sendKeys(Carrier);
        txtOpportunityDetailQuotedPremium.sendKeys(QuotedPremium);
    }

    public void opportunityquotemarkselected() {
        btnOpportunityDetailQuoteMarkSelected.click();           //Select Quote Popup
    }

    public void opportunityquotemarkselectedconfirm() {
        btnOpportunityDetailQuoteMarkSelectedConfirm.click();   //Confirm Button in Select Quote
    }

    public void mandatoryfieldsinopportunitydetail(String EffectiveDate, String QuotePremium, String Commission, String BrokerFee) {
        caleffectivedate.sendKeys(EffectiveDate);
        caleffectivedate.click();
        txtquoteprem.sendKeys(QuotePremium);
        txtcommission.sendKeys(Commission);
        txtbrokerfee.sendKeys(BrokerFee);
    }

    @FindBy(how = How.ID, using = "login_coverage_type")
    public WebElement select_coverage_desired;

    @FindBy(how = How.ID, using = "login_zipcode")
    public WebElement zip_code;

    @FindBy(how = How.ID, using = "login_business_class")
    public WebElement business_class;

    @FindBy(how = How.ID, using = "login_opportunity")
    public WebElement coverage_needed;

    @FindBy(how = How.ID, using = "login_msr_name")
    public WebElement refferer_name;

    @FindBy(how = How.ID, using = "login_continue_button")
    public WebElement continue_button;

    @FindBy(how = How.ID, using = "feedBack_title")
    public WebElement foleyrank;

    @FindBy(how = How.ID, using = "feedBack_firstName")
    public WebElement foleyfirstname;

    @FindBy(how = How.ID, using = "feedBack_lastName")
    public WebElement foleylastname;

    @FindBy(how = How.ID, using = "feedBack_emailAddress")
    public WebElement foleyemail;

    @FindBy(how = How.ID, using = "feedBack_businessName")
    public WebElement foleybusinessname;

    @FindBy(how = How.ID, using = "feedBack_phoneNumber")
    public WebElement foleyphonenum;

    @FindBy(how = How.ID, using = "feedBack_title_location")
    public WebElement foleylocation;

    @FindBy(how = How.ID, using = "feedBack_city")
    public WebElement foleycity;

    @FindBy(how = How.ID, using = "feedBack_state")
    public WebElement foleystate;

    @FindBy(how = How.ID, using = "feedBack_zip")
    public WebElement foleyzip;

    @FindBy(how = How.ID, using = "feedBack_website")
    public WebElement foleywebsite;

    @FindBy(how = How.NAME, using = "feedBack_descOperation")
    public WebElement foleydesc;

    @FindBy(how = How.ID, using = "feedBack_currentInsurance")
    public WebElement foleyinsc;

    @FindBy(how = How.ID, using = "feedBack_renewalDate")
    public WebElement foleydate;

    @FindBy(how = How.ID, using = "feedBack_annualGross")
    public WebElement foleyannual;

    @FindBy(how = How.NAME, using = "feedBack_annualPayroll")
    public WebElement foleypayroll;

    @FindBy(how = How.ID, using = "feedBack_fullTimeEmployees")
    public WebElement foleyfulltimeempl;

    @FindBy(how = How.ID, using = "feedBack_partTimeEmployees")
    public WebElement foleypartimeempl;

    @FindBy(how = How.NAME, using = "feedBack_fein")
    public WebElement foleyfein;

    @FindBy(how = How.ID, using = "feedBack_typeOrgan")
    public WebElement foleyorg;

    @FindBy(how = How.ID, using = "feedBack_yearsBusiness")
    public WebElement foleyyears;

    @FindBy(how = How.ID, using = "feedBack_yearExperience")
    public WebElement foleyexp;

    @FindBy(how = How.ID, using = "feedBack_activeOwners")
    public WebElement foleyownr;


    @FindBy(how = How.ID, using = "feedBack_claims")
    public WebElement foleyclaims;

    @FindBy(how = How.NAME, using = "feedBack_comments")
    public WebElement foleynotes;

    @FindBy(how = How.ID, using = "Sec_submit")
    public WebElement foleycontinue;

    @FindBy(how = How.ID, using = "question_id_25")
    public WebElement foleyopp;

    @FindBy(how = How.XPATH, using = "//*[@id=\"questionsForm\"]/div/div[7]/div/input")
    public WebElement oppsubmit;

    @FindBy(how = How.NAME, using = "Business Name")
    public WebElement leadmicrobusinessname;

    @FindBy(how = How.CSS, using = "#dtopportunity > tbody > tr > td:nth-child(1) > div > a")
    public WebElement foleyleadcoverage;

    @FindBy(how = How.NAME, using = "lead_broker")
    public WebElement leadbrokerselect;

    public void entering_values_into_microsite(String zipcode, String classofbusiness, String coverageneeded, String referrernamesentry)throws InterruptedException {
            select_coverage_desired.click();
            Thread.sleep(1000);
            Select tpmselect_coverage_desired = new Select(select_coverage_desired);
            tpmselect_coverage_desired.selectByVisibleText("Aviation");
            Thread.sleep(1000);
            zip_code.sendKeys(zipcode);
            Thread.sleep(2000);
            business_class.sendKeys(classofbusiness);
            Thread.sleep(2000);
            coverage_needed.sendKeys(coverageneeded);
            Thread.sleep(2000);
            refferer_name.sendKeys(referrernamesentry);
            Thread.sleep(2000);
            continue_button.click();
            Thread.sleep(5000);
    }

    public void detailpage(String rank, String firstname, String lastname, String email, String businessname, String phonenumber,
                           String locationaddress, String city, String state, String zip, String website, String descp, String currentinsurance, String renewaldate, String grosssale, String payroll,
                           String fulltimeemployee, String parttimeemployee, String fein, String typeoforg, String yearsinbusines, String yearsofexp, String activeowners, String claims, String notes)throws InterruptedException {
            foleyrank.sendKeys(rank);
            Thread.sleep(2000);
            foleyfirstname.sendKeys(firstname);
            Thread.sleep(2000);
            foleylastname.sendKeys(lastname);
            Thread.sleep(2000);
            foleyemail.sendKeys(email);
            Thread.sleep(2000);
            foleybusinessname.sendKeys(businessname);
            Thread.sleep(2000);
            foleyphonenum.sendKeys(phonenumber);
            Thread.sleep(2000);
            foleylocation.sendKeys(locationaddress);
            Thread.sleep(2000);
            foleycity.sendKeys(city);
            Thread.sleep(2000);
            foleystate.sendKeys(state);
            Thread.sleep(2000);
            foleyzip.sendKeys(zip);
            Thread.sleep(2000);
            foleywebsite.sendKeys(website);
            Thread.sleep(2000);
            foleydesc.sendKeys(descp);
            Thread.sleep(2000);
            foleyinsc.sendKeys(currentinsurance);
            Thread.sleep(2000);
            foleydate.sendKeys(renewaldate);
            Thread.sleep(2000);
            foleyannual.sendKeys(grosssale);
            Thread.sleep(2000);
            foleypayroll.sendKeys(payroll);
            Thread.sleep(2000);
            foleyfulltimeempl.sendKeys(fulltimeemployee);
            Thread.sleep(2000);
            foleypartimeempl.sendKeys(parttimeemployee);
            Thread.sleep(2000);
            foleyfein.sendKeys(fein);
            Thread.sleep(2000);
            foleyorg.sendKeys(typeoforg);
            Thread.sleep(2000);
            foleyyears.sendKeys(yearsinbusines);
            Thread.sleep(2000);
            foleyexp.sendKeys(yearsofexp);
            Thread.sleep(2000);
            foleyownr.sendKeys(activeowners);
            Thread.sleep(2000);
            foleyclaims.sendKeys(claims);
            Thread.sleep(2000);
            foleynotes.sendKeys(notes);
            Thread.sleep(2000);
            foleycontinue.click();
            Thread.sleep(2000);
           }

    public void opportunitydetail() {
        foleyopp.sendKeys("999");
        oppsubmit.click();
    }

    public void micrositelead(String leadbusinessname)throws InterruptedException {
            leadmicrobusinessname.sendKeys(leadbusinessname);
            Thread.sleep(5000);
            foleyleadcoverage.click();
            Thread.sleep(5000);
           }

    public void leadbroker()throws InterruptedException{
            leadbrokerselect.click();
            Thread.sleep(2000);
            Select slt = new Select(leadbrokerselect);
            slt.selectByVisibleText("Prathima M");
            Thread.sleep(2000);
            LeadSubmitbtn.click();
            Thread.sleep(4000);
           }

    @FindBy(how = How.ID, using = "login_coverage_type")
    public WebElement olisltcoverage;

    @FindBy(how = How.ID, using = "login_business_class")
    public WebElement oliclassbusiness;

    @FindBy(how = How.ID, using = "login_zipcode")
    public WebElement olizip;

    @FindBy(how = How.ID, using = "login_continue_button")
    public WebElement olinorthsub;

    public void olidetails(String coverageneeded, String classofbusiness, String olizipcode) {
        olisltcoverage.sendKeys(coverageneeded);
        oliclassbusiness.sendKeys(classofbusiness);
        olizip.sendKeys(olizipcode);
        olinorthsub.click();
    }

    @FindBy(how = How.ID, using = "feedBack_firstName")
    public WebElement olifName;

    @FindBy(how = How.ID, using = "feedBack_lastName")
    public WebElement olilname;

    @FindBy(how = How.ID, using = "feedBack_businessName")
    public WebElement oliBussname;

    @FindBy(how = How.ID, using = "feedBack_title_location")
    public WebElement oliLocaadd;

    @FindBy(how = How.ID, using = "feedBack_city")
    public WebElement oliCity;

    @FindBy(how = How.ID, using = "feedBack_state")
    public WebElement oliState;

    @FindBy(how = How.ID, using = "feedBack_emailAddress")
    public WebElement oliEmailadd;

    @FindBy(how = How.ID, using = "feedBack_phoneNumber")
    public WebElement oliPhonenum;

    @FindBy(how = How.ID, using = "feedBack_access_levels")
    public WebElement oliaccesslvl;

    @FindBy(how = How.ID, using = "feedBack_service_levels")
    public WebElement olisrvclvl;

    @FindBy(how = How.ID, using = "servicer")
    public WebElement olisvcr;

    @FindBy(how = How.NAME, using = "feedBack_comments")
    public WebElement olinot;

    @FindBy(how = How.ID, using = "Sec_submit")
    public WebElement olisub;

    public void oppdetailoli(String olifname, String olastname, String obusinessname, String olocationadd, String ocity, String ostate, String oemai, String ophone, String oaccess, String oservice, String oservicer, String onotes)throws InterruptedException {
            olifName.sendKeys(olifname);
            Thread.sleep(2000);
            olilname.sendKeys(olastname);
            Thread.sleep(2000);
            oliBussname.sendKeys(obusinessname);
            Thread.sleep(2000);
            oliLocaadd.sendKeys(olocationadd);
            Thread.sleep(2000);
            oliCity.sendKeys(ocity);
            Thread.sleep(2000);
            oliState.sendKeys(ostate);
            Thread.sleep(2000);
            oliEmailadd.sendKeys(oemai);
            Thread.sleep(2000);
            oliPhonenum.sendKeys(ophone);
            Thread.sleep(2000);
            oliaccesslvl.sendKeys(oaccess);
            Thread.sleep(2000);
            olisrvclvl.sendKeys(oservice);
            Thread.sleep(2000);
            olinot.sendKeys(onotes);
            Thread.sleep(2000);
            olisub.click();
            Thread.sleep(2000);
    }

    @FindBy(how = How.ID, using = "business-details-click")
    public WebElement businessdetail;

    public void businesslink() {
        businessdetail.click();
        System.out.println("Business detail clicked");
    }

    @FindBy(how = How.CSS, using = "#quotes_section_datatables > tbody > tr.odd > td:nth-child(10) > center")
    public WebElement deletequote;

    @FindBy(how = How.CSS, using = "body > div.jconfirm.jconfirm-light.jconfirm-open > div.jconfirm-scrollpane > div > div > div > div > div > div > div > div.jconfirm-buttons > button:nth-child(1)")
    public WebElement quoteyes;

    @FindBy(how = How.CSS, using = "body > div.jconfirm.jconfirm-light.jconfirm-open > div.jconfirm-scrollpane > div > div > div > div > div > div > div > div.jconfirm-buttons > button")
    public WebElement deletequoteok;

    public void deletequote()throws InterruptedException {
            Quotes.click();
            Thread.sleep(2000);
            deletequote.click();
            Thread.sleep(2000);
            quoteyes.click();
            Thread.sleep(3000);
            deletequoteok.click();
            Thread.sleep(3000);
           }

    @FindBy(how = How.ID, using = "sw_start")
    public WebElement starttime;

    @FindBy(how = How.ID, using = "timer_Company_name")
    public WebElement selectcompany;

    @FindBy(how = How.ID, using = "timer-Details-Cancel")
    public WebElement cleartimer;

    @FindBy(how = How.ID, using = "timer-Details-close")
    public WebElement timerclose;

    @FindBy(how = How.ID, using = "timer_brand")
    public WebElement timerbrand;

    @FindBy(how = How.ID, using = "timer_subBrand")
    public WebElement timersubbrand;

    @FindBy(how = How.ID, using = "timer_service")
    public WebElement timerser;

    @FindBy(how = How.ID, using = "timer_task")
    public WebElement timertask;

    @FindBy(how = How.ID, using = "timer-Details-Submit")
    public WebElement timersubmit;

    @FindBy(how = How.ID, using = "sw_stop")
    public WebElement timerstop;

    public void starttimer()throws InterruptedException {
            starttime.click();
            Thread.sleep(3000);
            selectcompany.click();
            Thread.sleep(2000);
            selectcompany.sendKeys("Heffernan");
            Thread.sleep(2000);
            cleartimer.click();
            Thread.sleep(2000);
            timerclose.click();
            Thread.sleep(2000);
          }

    public void companytimer(String timercompany, String timerbrandpma, String timersubbrandpma, String timerservice, String timertaskpma)throws InterruptedException {
            starttime.click();
            Thread.sleep(2000);
            selectcompany.click();
            Thread.sleep(2000);
            selectcompany.sendKeys(timercompany);
            Thread.sleep(2000);
            timerbrand.click();
            Thread.sleep(2000);
            timerbrand.sendKeys(timerbrandpma);
            Thread.sleep(2000);
            timersubbrand.click();
            Thread.sleep(2000);
            timersubbrand.sendKeys(timersubbrandpma);
            timerser.click();
            timerser.sendKeys(timerservice);
            Thread.sleep(2000);
            timertask.click();
            Thread.sleep(2000);
            timertask.sendKeys(timertaskpma);
            Thread.sleep(2000);
            }

    public void timetrackstart() {
        timersubmit.click();
    }

    public void taskactivity()throws InterruptedException {
            starttime.click();
            Thread.sleep(2000);
            timertask.sendKeys("Acords");
            Thread.sleep(2000);
            timersubmit.click();
            Thread.sleep(3000);
            // timerstop.click();
            }

    @FindBy(how = How.NAME, using = "feedBack_comments")
    public WebElement oppnotes;
    @FindBy(how = How.XPATH, using = "//*[@id=\"questionsForm\"]/div/div[7]/div/input")
    public WebElement opposubmit;

    @FindBy(how = How.XPATH, using = "//*[@id=\"questionsForm\"]/div/div[3]/div/input")
    public WebElement opposubmit1; //foley final page submit

    public void oppfoley()throws InterruptedException {
            //oppnotes.sendKeys("test999");
            //Thread.sleep(2000);
            opposubmit1.click();
            Thread.sleep(1000);
            }

    public void stoptimer() {
        timerstop.click();
    }

    public void statuschange() {
        Select str = new Select(drpStatus);
        str.selectByVisibleText("Bound");
    }

    @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom > div.datepicker-days > table > tbody > tr:nth-child(1) > td.active.day")
    public WebElement needbydatepicker;  //opp need by date calender

    @FindBy(how = How.ID, using = "service_level")
    public WebElement oppservicelevel1;    //opp service level

    @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom > div.datepicker-days > table > tbody > tr:nth-child(1) > td:nth-child(5)")
    public WebElement oppeffectivedate;    //opp effective date

    @FindBy(how = How.ID, using = "business-type-form")
    public WebElement oppbusinesstype;    //opp business type

    @FindBy(how = How.ID, using = "policy-acc-manager")
    public WebElement accntmanager;    // account manager)

    @FindBy(how = How.XPATH, using = "//*[@id=\"opportunity_detail\"]/div[1]/div/div[1]/button[2]")
    public WebElement oppcancel;

    @FindBy(how = How.CSS, using = "body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > tbody > tr:nth-child(3) > td.today.day")
    public WebElement needbydateopp;   //opportunity need by date

    public void boundstatus(String status, String accesslevel, String servicelevel, String businesstype, String accountmanager)throws InterruptedException {
            drpStatus.sendKeys(status);
            Thread.sleep(1000);
            drpdownaccess.sendKeys("Direct");
            Thread.sleep(1000);
            oppservicelevel1.sendKeys("Oli Serviced");
            Thread.sleep(1000);
            oppbusinesstype.sendKeys(businesstype);
            Thread.sleep(1000);
            accntmanager.sendKeys(accountmanager);
            Thread.sleep(1000);
            btnSubmit.click();
            Thread.sleep(1000);
            oppcancel.click();
           }
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi5Count\"]")
    public WebElement boundthismonth;

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi6Count\"]")
    public WebElement boundthisyear;

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3Count\"]")
    public WebElement myopenopportunities;

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1Count\"]")
    public WebElement mynewopportunities;

    @FindBy(how = How.ID, using = "IsBoundBOR")
    public WebElement boundborcheckbox;

    public void boundkpi() {
        String boundthismonthKPI = boundthismonth.getText();
        System.out.println("My Bound opportunities this month KPI is :" + boundthismonthKPI);

        String boundthisyearKPI = boundthisyear.getText();
        System.out.println("My Bound opportunities this year KPI is :" + boundthisyearKPI);

        String myopenKPI = myopenopportunities.getText();
        System.out.println("My Open opportunities KPI count is :" + myopenKPI);

        String mynewKPI = mynewopportunities.getText();
        System.out.println("My Open opportunities KPI count is :" + mynewKPI);
    }

    public void boundbor()throws InterruptedException {
            Thread.sleep(1000);
            boundborcheckbox.click();
            Thread.sleep(2500);
            }
    @FindBy(how = How.ID, using = "Insured")
    public WebElement amainsuredcol; //insured column in ama

    public void businessama(String amabusinessname)throws InterruptedException {
            Thread.sleep(1000);
            amainsuredcol.sendKeys(amabusinessname);
            Thread.sleep(3000);
            }
    @FindBy(how = How.ID, using = "business-name")
    public WebElement oppbusinessname; //to capture business name in opportunity detail page

    @FindBy(how = How.ID, using = "tabbtnimportedleads")
    public WebElement importedleadstab; // imported leads tab

    @FindBy(how = How.CSS, using = "#th-BusinessNameLeads > div")
    public WebElement busimportedlead; //imported leads business sorting

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-BusinessNameLeads\"]/div/following-sibling::Business Name")
    public WebElement importedbusiness;

    @FindBy(how = How.CSS, using = "#th-BusinessClassLeads > span")
    public WebElement importedleadsclass; //class column in imported leads

    @FindBy(how = How.CSS, using = "#th-BusinessClassLeads > span > div > ul > li.multiselect-item.multiselect-filter > div > input")
    public WebElement classsearch; //search in class column

    @FindBy(how = How.CSS, using = "#th-BusinessClassLeads > span > div > ul > li.multiselect-item.multiselect-all > a > label > input[type=checkbox]")
    public WebElement selectallclass; //select all in class column

    public void imported()throws InterruptedException {
            Thread.sleep(1000);
            drpStatus.sendKeys("Imported");
            Thread.sleep(1000);
            btnSubmit.click();
            Thread.sleep(2000);
            btnhome.click();
            Thread.sleep(1000);
            importedleadstab.click();
            Thread.sleep(3000);
            importedleadsclass.click();
            Thread.sleep(1000);
            classsearch.sendKeys(" 999 Apr19 BC");
            Thread.sleep(1000);
            selectallclass.click();
            Thread.sleep(1000);
            importedleadstab.click();
            Thread.sleep(1000);
           }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtopportunity\"]/tbody/tr[1]/td[6]/div")
    public WebElement oliopptab; //oli records in opportunity tab for olimanager login

    public void olimanager() {
        String olimgr = new String(oliopptab.getText());
        System.out.println("Brand details for Oli manager is \n" + olimgr);
    }

    @FindBy(how = How.CSS, using = "#the-basics-new > span > div > div > div")
    public WebElement olisearch;

    public void olimngrglobalsearch() {
        String globalsrch = new String(olisearch.getText());
        System.out.println("Global search results is \n" + globalsrch);
    }

    @FindBy(how = How.ID, using = "kpi1Count")
    public WebElement todolistkpi1;  //activities KPI

    @FindBy(how = How.ID, using = "kpi2Count")
    public WebElement todolistkpi2; //follow up KPI

    @FindBy(how = How.ID, using = "kpi3Count")
    public WebElement todolistkpi3; //recently completed KPI

    @FindBy(how = How.ID, using = "kpi4Count")
    public WebElement todolistkpi4; //

    public void activitieskpi()throws InterruptedException {
            Thread.sleep(3000);
            String kpi1 = new String(todolistkpi1.getText());
            System.out.println(" Activities KPI count is " + kpi1);
            Thread.sleep(1000);
            }

    @FindBy(how = How.CSS, using = "#dtbusiness > tbody > tr:nth-child(1) > td:nth-child(1) > a")
    public WebElement businessselect; //select business from business tab

    public void selectbusiness()throws InterruptedException {
            Thread.sleep(2000);
            businessselect.click();
            Thread.sleep(1000);
            }

    public void followupkpi()throws InterruptedException {
            Thread.sleep(2000);
            String todolist2 = new String(todolistkpi2.getText());
            Thread.sleep(2000);
            System.out.println("KPI Count is " + todolist2);
            }

    public void recentlycompleted()throws InterruptedException{
            Thread.sleep(2000);
            String thirdkpi = new String(todolistkpi3.getText());
            Thread.sleep(1000);
            System.out.println("Recently Completed KPI count is " + thirdkpi);
             }

    public void activitycompleted()throws InterruptedException {
            todoactcompleted.click();
            Thread.sleep(1000);
            ActDetSubmit.click();
            Thread.sleep(1000);
            btntodolist.click();
            Thread.sleep(1000);
            }

    public void myreviews()throws InterruptedException {
            Thread.sleep(2000);
            String todo4 = new String(todolistkpi4.getText());
            Thread.sleep(2000);
            System.out.println("My Reviews KPI count is " + todo4);
            }

    public void reviewdesiredcheck()throws InterruptedException {
            todoactcompleted.click();
            Thread.sleep(2000);
            todoreviewdesired.click();
            Thread.sleep(2000);
            ActDetSubmit.click();
            Thread.sleep(2000);
            btntodolist.click();
            Thread.sleep(2000);
           }

    @FindBy(how = How.CSS, using = "#dtimportedleads_wrapper > div:nth-child(1) > div:nth-child(2) > div > div.btn.btn-sm.red.btn-outline.search-clear")
    public WebElement importedreset; //reset button in imported tab

    public void oppstatus()throws InterruptedException {
            Thread.sleep(1000);
            drpStatus.sendKeys("Untouched");
            Thread.sleep(1000);
            btnSubmit.click();
            Thread.sleep(1000);
            btnhome.click();
            Thread.sleep(1000);
            importedleadstab.click();
            Thread.sleep(3000);
            importedreset.click();
            Thread.sleep(1000);
            importedleadsclass.click();
            Thread.sleep(1000);
            classsearch.sendKeys(" 999 Apr19 BC");
            Thread.sleep(1000);
            selectallclass.click();
            Thread.sleep(1000);
            }

    @FindBy(how = How.ID, using = "yes_continue")
    public WebElement yescoverageoli; //yes button in coverage alert

    @FindBy(how = How.CSS, using = "#first_step > div:nth-child(1) > div > h2")
    public WebElement finalpage; //Thank you page

    @FindBy(how = How.ID, using = "no_continue")
    public WebElement nocoverageoli; //no button in coverage alert

    @FindBy(how = How.CSS, using = "#first_step > div:nth-child(2) > div > p:nth-child(1)")
    public WebElement finalpage1; //no in coverage alert

    @FindBy(how = How.CSS, using = "#questionsForm > div > div:nth-child(4) > div > input")
    public WebElement foleysubmit;

    public void yescoverage() {
        yescoverageoli.click();
        String str = new String(finalpage.getText());
        System.out.println("Navigates to final page " + str);
    }

    public void nocoverage() {
        nocoverageoli.click();
        String str = new String(finalpage1.getText());
        System.out.println("Navigates to final page " + str);
    }

    public void foleyyescoverage() {
        yescoverageoli.click();
        foleysubmit.click();
    }

    public void foleynocoverage() {
        nocoverageoli.click();
    }

    public void oppdetailpage(String oppstatus, String wheniscoverageneeded) {
        drpStatus.sendKeys(oppstatus);
        txtcoverageneeded.sendKeys(wheniscoverageneeded);
    }
}

