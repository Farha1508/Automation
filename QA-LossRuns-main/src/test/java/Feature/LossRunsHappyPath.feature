Feature: Loss Runs application E2E testing

  Background: User is logged in and navigate to Homepage page
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"
    When I enter the email and password for the "Super User"
    And Now clicked on Sign In button
    Then I click on the "Work Order Tracking" tile
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed

  @10759
  Scenario: Validate Patra Logo
    Then validate patra logo on the homepage
    Then I click on "Time Record Admin" Tab
    When I Click on Patra logo
    Then I should be redirected to "Homepage" page


  @11307
  Scenario: Global Search functionality for valid WO
    And I navigate to the "Open" tab
    And I get the "Work Order #" for "row 2" row of the grid
    Then Enter Work order Id under Global search field and verify

  @11308
  Scenario: Global Search functionality for Invalid WO
    Then Enter "124455" Work order Id under Global search field and verify

  @9285
  Scenario: Add WO - Submit and Open button functionality
    When I Click on Add Work Order button
    And Click on "Submit and Open" button
    Then WO should be created and WO Details page should be displayed

  @9286
  Scenario: Add WO - Cancel button functionality
    When I Click on Add Work Order button
    And Click on "Cancel" button
    Then Add WO page should be closed and homepage should be displayed

  @9287
  Scenario: Add WO - Mandatory fields not filled and click Submit button
    When I Click on Add Work Order button
    Then Select Status as 'Select Status'
    And Click on "Submit" button
    Then Validation message is displayed

  @9911
  Scenario: Add WO - Mandatory fields not filled and click Submit and Open button
    When I Click on Add Work Order button
    Then Select Status as 'Select Status'
    And Click on "Submit and Open" button
    Then Validation message is displayed

  @9284
  Scenario: Add WO - Submit button functionality
    When I Click on Add Work Order button
    Then I Click on "Work Order Entry" tab
    Then I select values from the drop down
      | Work Order Type | Quarter     | Assigned To   |
      | Accident        | 1st Quarter | Satish Prasad |
    Then I enter values in fields
      | Client Code | Client Name       | Policy # | Send Reports To      | Requestor               |
      | CL123       | Automation Tester | PO12334  | sentto@patracorp.com | requestor@patracorp.com |
    Then I enter values in text area
      | Summary                                                                                                                                                                              |
      | Automation Summary -Possibly one of the biggest limitations of test automation is that it can't think like a human, meaning that user experience tests will need to be done manually |
    Then I Click on "Work Order Details" tab
    Then I select values from the drop down
      | Branch Office        | Division      | Department            | Profit center |
      | 101 Walnut Creek, CA | Status Report | CL - Commercial Lines | CMC           |
    Then I enter values in fields
      | Number Of | # Downloaded | # of Loss Runs | # Ordered | Total # of Requests | # Contractor's Liability Phone Report | # of Contractor's GL Phone Report |
      | 11        | 22           | 43             | 33        | 77                  | 88                                    | 23                                |
    Then I enter values in text area
      | Comments                                        |
      | Testing Add WO functionality through Automation |
    Then I Click on "Original Email" tab
    Then I enter values in text area
      | Original Email                                                                                                                                                                                                                                                                                                                         |
      | automation testing purpose - Please see below and attached for certificate request MARY SAFFIOTI Commercial Lines Assistant Account Manager USI Insurance Services LLC 12 Gill Street, Suite 5500, Woburn, MA 01801 781-939-2002  f: 781.376.5035 Mary.Saffioti@usi.com<mailto:Mary.Saffioti@usi.com> www.usi.com<http://www.usi.com/> |
    And Click on "Submit" button
    Then I get the new WO count


  @9713
  Scenario: Verify Order/Reminder Dates tab
    And Enter Work order Id in Global search field and open
    When I navigate to the "Order/Reminder Dates" tab
    Then Validate the below fields are displayed
      | Next Reminder Needed |
      | Reports Reviewed     |
      | All Orders Complete  |
    And Verify the following columns are present
      | Carrier Name  |
      | Order Date    |
      | Reminder      |
      | Done          |
      | Next Reminder |
      | Action        |

    Then I Click on "Reports Reviewed" Checkbox
    And Verify Status field is changed to "Reports Reviewed"
    Then I Click on "All Orders Complete" Checkbox
    And Verify Status field is changed to "Completed"
    Then Click on "Save" button
    Then Reload the page
    And Verify Status field is changed to "Completed"








