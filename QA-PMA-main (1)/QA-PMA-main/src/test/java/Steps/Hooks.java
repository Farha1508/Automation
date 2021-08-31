package Steps;

import Base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

public class Hooks extends BaseUtil{

    private BaseUtil base;

    public Hooks(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest() {
        // System.setProperty("webdriver.gecko.driver","G:\\Selenium\\Geckodriver\\geckodriver.exe");
        //  System.out.println("Opening Firefox browser");
        System.setProperty("webdriver.chrome.driver","D:\\Backup\\D Drive\\chromedriver_win32\\chromedriver.exe");
        System.out.println("Opening Chrome browser");
        base.driver = new ChromeDriver();
    }

    @After
    public void TearDownTest(Scenario scenario) {
        scenario.write("Scenario Finished");
        if (scenario.isFailed()) {
           // TakesScreenshot
            System.out.println(scenario.getName());
            scenario.embed(((TakesScreenshot)base.driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
    }
    static WebDriver driver;
    public static void takeScreenshot(String filename) throws IOException {
        //takes screenshot and store it in file format
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //now copy screenshot to desired loaction using copyfile method
        FileUtils.copyFile(file, new File("C:\\Users\\E.Anusha\\QA-PMA\\src\\test\\java" +filename+ "image/jpg"));
    }
}



/*
    And user clicks business grid
    And user clicks Reset button for business
    And user searches with the following
      | businessname |
      | Anusha feb22     |
    And user selects business

    And clicks on Business Details link to edit
    And I edit add contact under business details fields
    And I click on Add Addresses button
      | addresstype      | city  | street1     | region | street2        | zip   | country |
      | Shipping Address | Vizag | A15 nagar | Andhra | Birla junction | 123456| India   |

    And user clicks business add activity button
    And user adds the following activity details
      | activitytype     | activitystatus | departmentassigned | assignedto | needbydate | activityperformed | activitycompleted | followuprequired | reviewdesired | description           |
      | Proposal | Open           | Broker          | Anusha E.           | 02/28/2018 |                   |                   |                  |               | Automation Testing March7 |
    And user clicks business add activity submit button
    Then user should see the record under business activities tab

    And user clicks business add note button
    And user enter the following note details
      | title      | description |
      | automat March7 | testing March7   |
    And user clicks business add note submit button
    And user should see the record under business notes tab
    And user clicks business add opportunity button
    And user enters the following opportunity details
      | status     | coveragetype    | needbydate | opportunityreceiveddate | departmentassigned | assignedto | opportunityassigneddate | currentinsurancecarrier | renewaldate | currentpremium | priorclaims | origin  | describepriorclaims |
      | Info Needed | Commercial Auto | 02/09/2018 | 01/09/2018              | Broker        | Anusha E.           | 01/09/2018              | Bound  anusha  March7                 | 09/02/2018  | 25           |             | Call In | Testing anusha March7           |
    And user clicks business add opportunity submit button
    And click on opportunities tab
    And user selects coverage under opportunities tab
    And user edits the following opportunity details
      | status      | coveragetype | wheniscoverageneeded | needbydate | opportunityreceiveddate | departmentassigned | assignedtoopportunity | assigneddate | currentinsurancecarrier | renewaldate | currentpremium | accesslevel | quotedate  | quotedcarrier | termlife | accountpaid |
      | Info Needed |              | Not sure             | 02/31/2018 | 02/01/2018              |                    |                       | 01/02/2018   | testing March7                 | 09/01/18    | 12             | Direct      | 01/23/2018 | Testing March7      |          |             |
    And user clicks opportunity detail submit button

    And I clicks Home button
    And I click Reset button
    And I Click a record under Business Name column
    And I see business page of that record
    And I click on Coverage name

    And I click on Add Activity section
      | activitytype  | activitystatus | departmentassigned | assignedto | needbydate | reviewdesired | followupreq | description                  |
      | Proposal Call | Open           | Broker             | Anusha E.  | 02/22/2018 |               |             | Tested by Anusha Oct22 2018 |
    And I click on Add Note section
      | title          | description    |
      | testtitle Oct22 | testdesc Oct22 |
    And I click on Add Claim section
      | claimdate  | claimstatus | claimamount | descriptionofclaim   |
      | 02/28/2018 | Open        | $2500       | test for claims desc Oct22 2018 |
    And I click on Add Quote section
      | quotedate  | carrier | quotedpremium | carrierdeclined | declinedreason |
      | 10/28/2018 | ACE     | $2350         |                 | Pricing        |

    And finally clicking on Reports button

    And I click on To Do List button
    And I click record under Activity Type column
    And I select activity type record
    And I enter values in Activity detail section under Activity page
      | activitytype  | activitystatus | departmentassigned | assignedto | needbydate | activityperformed | activitycompleted | reviewdesired | followuprequired | numberoffollowups | followupfrequency | finalfollowup | description                 |
      | Proposal Call | In progress    | Broker             | Anusha E.  | 01/12/2018 |                   |                   |               |                  | 2                 | 15 days           | 45th day      | contd... PMA testing March7 |
    And I click on Add Note under To Do List page
      | title           | description    |
      | todo title Bound March7 | todo desc Bound March7  |
    And I click on Add Claim under To Do List page
      | claimdate  | claimstatus | claimamount | descriptionofclaim        |
      | 01/19/2018 | Open        | $800        | Bound for claims desc todo March7  |
    And I click on Add Quote under To Do List page
      | quotedate  | carrier   | quotedpremium | carrierdeclined | declinedreason |
      | 02/11/2018 | All Risks | $6500         |                 | Timing         |


    And I clicks Home button
    And I select one of the KPI My New Opportunities
    And I click Reset button
    And user clicks business grid
    And I Click on opportunity tab
    Then I sort the fileds under Opportunity grid
    Then I Click on Business and opportunity tabs and sort columns there
 */

/*
    And clicks on Business Details link to edit
    And I edit add contact under business details fields


    And user clicks business add activity button
    And user adds the following activity details
      | activitytype     | activitystatus | departmentassigned | assignedto | needbydate | activityperformed | activitycompleted | followuprequired | reviewdesired | description           |
      | Proposal | Open           | Broker          | Anusha E.           | 02/28/2018 |                   |                   |                  |               | Automation Testing March14 |
    And user clicks business add activity submit button
    Then user should see the record under business activities tab

    And user clicks business add note button
    And user enter the following note details
      | title      | description |
      | automation March14 | testing March14   |
    And user clicks business add note submit button
    And user should see the record under business notes tab
    And user clicks business add opportunity button
    And user enters the following opportunity details
      | status     | coveragetype    | needbydate | opportunityreceiveddate | departmentassigned | assignedto | opportunityassigneddate | currentinsurancecarrier | renewaldate | currentpremium | priorclaims | origin  | describepriorclaims |
      | Needs Call | Commercial Auto | 04/09/2018 | 01/28/2018              | Broker        | Anusha E.           | 01/01/2018              | Bound  anusha  March14                 | 09/05/2018  | 250          |             | Call In | Testing anusha March14           |
    And user clicks business add opportunity submit button
    And click on opportunities tab
    And user selects coverage under opportunities tab
    And user edits the following opportunity details
      | status      | coveragetype | wheniscoverageneeded | needbydate | opportunityreceiveddate | departmentassigned | assignedtoopportunity | assigneddate | currentinsurancecarrier | renewaldate | currentpremium | accesslevel | quotedate  | quotedcarrier | termlife | accountpaid |
      | Needs Call |              | Not sure             | 02/31/2018 | 02/05/2018              |                    |                       | 01/02/2018   | testing March14                 | 09/01/18    | 12             | Direct      | 01/23/2018 | Testing March14      |          |             |
    And user clicks opportunity detail submit button

    And I clicks Home button
    And I click Reset button
    And I Click a record under Business Name column
    And I see business page of that record
    And I click on Coverage name

    And I click on Add Activity section
      | activitytype  | activitystatus | departmentassigned | assignedto | needbydate | reviewdesired | followupreq | description      |
      | Proposal Call | Open           | Broker             | Anusha E.   | 02/22/2018 |               |             | Tested by Anusha March15 |
    And I click on Add Note section
      | title          | description    |
      | testtitle March9 | testdesc March9 |
    And I click on Add Claim section
      | claimdate  | claimstatus | claimamount | descriptionofclaim   |
      | 01/28/2018 | Closed        | $500       | Bound for claims desc March14 |
    And I click on Add Quote section
      | quotedate  | carrier | quotedpremium | carrierdeclined | declinedreason |
      | 01/28/2018 | AIG     | $2800         |                 | Pricing         |
    Then click on Print PDF button

    And I click on History tab


    And finally clicking on Reports button

    And I enter values in Activity detail section under Activity page
      | activitytype  | activitystatus | departmentassigned | assignedto | needbydate | activityperformed | activitycompleted | reviewdesired | followuprequired | numberoffollowups | followupfrequency | finalfollowup | description                 |
      | Proposal Call | In progress    | Broker             | Anusha E.  | 04/22/2018 |                   |                   |               |                  | 2                 | 15 days           | 45th day      | contd... PMA testing March14 |
    And I click on Add Followup button under Followups section
    And I enter follow up details
      | followuptype | followupdate | deptassigned | assignedto |
      | First Call   | 03/30/2018  | Broker       | Anusha E.   |
    And I click on Add Note under To Do List page
      | title           | description    |
      | todo title Bound March14 | todo desc Bound March14  |
 */
