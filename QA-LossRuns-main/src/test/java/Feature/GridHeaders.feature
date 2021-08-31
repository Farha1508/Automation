Feature: Manipulation of different grid headers

  Background: Login
    ###---------- Login component ----------###
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"
    When I enter the email and password for the "Super User"
    And Now clicked on Sign In button
    Then I click on the "Work Order Tracking" tile
    And I wait for "10" seconds
    When I open "Loss Run" for company "Company 002"
    And I wait for "5" seconds
    Then The page for the selected company and service will be displayed


  @11547
  Scenario: Date Headers Automatic Selection Loss Runs
    ###---------- Application Select ----------###
    And I navigate to the "Open" tab
    When I sort the "Summary" grid column by "descending"
    When I get the "Due Date" for "row 1" row of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Completed" tab
    When I get the "Date Completed" for "row 3" row of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Discarded" tab
    When I get the "Due Date" for "row 5" row of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Important Instructions" tab
    When I get the "Add Date" for "row 1" row of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

  @11530
  Scenario: Field Headers Automatic Selection
    ###---------- Application Select ----------###
    And I navigate to the "Open" tab
    And I get the "Work Order #" for "row 2" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Completed" tab
    When I sort the "Client Code" grid column by "descending"
    And I get the "Client Code" for "row 7" row of the grid
    And I enter that information into the grid header
    And I wait for "5" seconds
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Discarded" tab
    When I sort the "Summary" grid column by "descending"
    And I get the "Summary" for "row 6" row of the grid
    And I enter that information into the grid header
    And I wait for "5" seconds
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Important Instructions" tab
    And I get the "Requestor" for "row 1" row of the grid
    And I enter that information into the grid header
    And I wait for "5" seconds
    Then The information in the first row of the grid will match what was entered


    @11550
  Scenario: Selector Headers Manual Selection
    ###---------- Application Select ----------###
    And I navigate to the "Open" tab
    When I select "Available" from the "Status" header in the grid
    And I wait for "5" seconds
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Completed" tab
    When I select "None" from the "Email Override" header in the grid
     And I wait for "5" seconds
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Discarded" tab
    When I select "Satish Prasad" from the "Assigned To" header in the grid
     And I wait for "5" seconds
    Then The information in the first row of the grid will match what was entered
    And I navigate to the "Important Instructions" tab
    When I select "Important Instructions" from the "Email Override" header in the grid
    Then The information in the first row of the grid will match what was entered

  @11546
  Scenario: Verify Grid Headers Loss Runs
    ###---------- Application Select ----------###
    And I navigate to the "Open" tab
    And Verify the following headers are present
      | # | Work Order # | Status | Client Code | Summary | Add Date | Requestor | Assigned To | Email Override | Insured Reminder | Policy Number | Next Reminder Needed | Is Rush | Next Check Date | Branch Office | Due Date |
    And I wait for "5" seconds
    And I navigate to the "Completed" tab
    And I wait for "5" seconds
    And Verify the following headers are present
      | Work Order # | Status | Client Code | Summary | Add Date | Requestor | Assigned To | Email Override | Insured Reminder | Policy Number | Next Reminder Needed | Is Rush | Next Check Date | Branch Office | Date Completed | Due Date | SLA Miss Time (Mins) |
    And I wait for "5" seconds
    And I navigate to the "Discarded" tab
    And Verify the following headers are present
      | Work Order # | Status | Client Code | Summary | Add Date | Requestor | Assigned To | Email Override | Insured Reminder | Policy Number | Next Reminder Needed | Is Rush | Next Check Date | Branch Office | Due Date |
    And I wait for "5" seconds
    #see
    And I navigate to the "Important Instructions" tab
    And Verify the following headers are present
      | Work Order # | Status | Client Code | Summary | Add Date | Requestor | Assigned To | Email Override | Insured Reminder | Policy Number | Next Reminder Needed | Is Rush | Next Check Date | Branch Office | Due Date |

