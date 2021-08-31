Feature: Manipulation of different grid headers

  Background: Login
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

  Scenario: Date Headers Automatic Selection Policy Checking
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Policy Checking" for company "Company 002"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    And I navigate to the "Open Policies" tab
    When I get the "Effective Date" for "row 4" row of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

  Scenario: Date Headers Manual Selection for Policy Checking
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Policy Checking" for company "Company 002"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    And I navigate to the "Open Policies" tab
    And I set the date in the "Add Date" header to the following
      | From / To | Month    | Day | Year | Time | AM / PM |
      | From      | February | 2   | 2019 | 6:27 | AM      |
    And I set the date in the "Add Date" header to the following
      | From / To | Month | Day | Year | Time | AM / PM |
      | To        | March | 7   | 2020 | 6:27 | AM      |


  Scenario: Field Headers Automatic Selection
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Certificate Issuance" for company "Company 748"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    And I navigate to the "In Process" tab
    When I get the "WO #" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Discarded" tab
    And I get the "Requestor" for "row 6" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Completed" tab
    And I get the "Summary" for "row 2" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  Scenario: Field Headers Manual Selection
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Certificate Issuance" for company "Company 748"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    And I navigate to the "In Process" tab
    When I enter "303511" into the "WO #" grid header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Completed" tab
    When I enter "URGENT RUSH REQUEST NEED COI" into the "Summary" grid header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Discarded" tab
    When I enter "Out of Office" into the "Summary" grid header
    Then The information in the first row of the grid will match what was entered


  Scenario: Selector Headers Automatic Selection
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Certificate Issuance" for company "Company 748"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    And I navigate to the "In Process" tab
    When I get the "Folder" for "row 4" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Discarded" tab
    When I get the "Rush / Bulk" for "row 5" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Completed" tab
    When I get the "WO Type" for "row 5" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  Scenario: Selector Headers Manual Selection
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Certificate Issuance" for company "Company 748"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    And I navigate to the "In Process" tab
    When I select "Special Project" from the "WO Type" header in the grid
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Completed" tab
    When I select "On Hold" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Discarded" tab
    When I select "Rush" from the "Rush / Bulk" header in the grid
    Then The information in the first row of the grid will match what was entered

  Scenario: Verify Grid Headers Certificate Issuance
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Certificate Issuance" for company "Company 748"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    And I navigate to the "In Process" tab
    And Verify the following headers are present
      | WO # | Add Date | Folder | Client Code | Summary | Status | Assigned To | Due Date | Requestor | Branch Office | Profit Center | # of Certs | Rush / Bulk | Pending | WO Type | Update | SLA Miss Time | Holder Delivery | Insured Delivery |
    And I navigate to the "Completed" tab
    And Verify the following headers are present
      | WO # | Add Date | Client Code | Summary | Status | Due Date | Requestor | Branch Office | Profit Center | # of Certs | Rush / Bulk | Pending | WO Type | Update | SLA MET | Date Completed | Holder Delivery | Insured Delivery |
    And I navigate to the "Discarded" tab
    And Verify the following headers are present
      | WO # | Add Date | Client Code | Summary | Status | Assigned To | DueDate | Requestor | Branch Office | Profit Center | # of Certs | Rush / Bulk | Pending | WO Type | Update | SLA MET | Date Completed |
    And I navigate to the "Incoming Responses" tab
    And Verify the following headers are present
      | WO # | Add Date | Client Code | Summary | Folder | Status | Assigned To | DueDate | Requestor | Branch Office | Profit Center | # of Certs | Rush / Bulk | Pending | WO Type | Update | SLA MET | Date Completed |
    And I navigate to the "Important Instruction" tab
    And Verify the following headers are present
      | WO # | Add Date | Folder | Client Code | Summary | Status | Assigned To | Due Date | Requestor | Branch Office | Profit Center | # of Certs | Rush / Bulk | Pending | WO Type | Update | Holder Delivery | Insured Delivery |
    And I navigate to the "To be Discarded" tab
    And Verify the following headers are present
      | WO # | Add Date | Client Code | Summary | Status | Assigned To | Due Date | Requestor | Branch Office | Profit Center | # of Certs | Rush / Bulk | Pending | WO Type | Update |

    Scenario: Sorting columns
          ###---------- Application Select ----------###
      When I click on the "Work Order Tracking" tile
      When I open "Policy Checking" for company "Company 002"
      Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
      And I navigate to the "In Process" tab
      And I sort the "Add Date" grid column by "Descending"