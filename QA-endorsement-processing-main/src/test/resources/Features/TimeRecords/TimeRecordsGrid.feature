@TimeRecords
Feature: Time Records Grid

  Background: Login component
    Given I am on the login page
    ###---------- Login component ----------###
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Endorsement Processing" for company "Company 002"
    Then The page for the selected company and service will be displayed
    When I click on the "Time Record Admin" link
    And set the tab to "timerecordadmin"
    #

  @13890
  Scenario: Time Record Grid Reset Functionality
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Employee" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    When I click the Reset button in the grid header
    Then I expect "" in the "Employee" grid header

  @13891
  Scenario: Verify Paging Functionality
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    And I move to the next page in the grid
    And I move to the previous page in the grid

  @13892
  Scenario: Verify View dropdown Functionality
    And I set the "From" date picker to 2 months ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected

  @13894
  Scenario: Verify Grid -Verify WO # Column Search Field
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "WO#" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @13895
  Scenario: Verify Employee column in Time Record Open Page Grid
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Employee" for "row 5" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @13896
  Scenario: Grid -Verify End Time Date column functionality
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "End Time" for "row 7" row of the grid
    And I set that date in the header
    Then The date in the first row of the grid will match what was entered

  @13897
  Scenario: Grid -Verify Company Dropdown functionality
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Company" for "row 7" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @13898
  Scenario: Verify Service dropdown in timer grids
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Service" for "row 6" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @13899
  Scenario: Verify  Task Description column in timer grids
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Task Description" for "row 9" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @13901
  Scenario: Grid - Verify Hours/Duration column functionality
    # fails due to wot-pc#349
    # may fail due to wot-pc#336 (Time-out errors when sorting grid).
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    And I sort the "Duration (Hours)" grid column by "descending"
    When I get the "Duration (Hours)" for "row 1" row of the grid
        # will sometimes fail because of wot-certs/291
    And I enter that information into both grid headers
    Then The information in the first row of the grid will match what was entered
    # The Duration column in the Time Rec Open does not have a header filter.
    # The only thing to check for Duration is sorting, which is handled in test case 2442.

  @13893
  Scenario: Grid - Sorting functionality
    # may fail due to wot-pc#336 (Time-out errors when sorting grid).
    # fails due to wot-pc#348
    When I set the "From" date picker to 1 months ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    Then I test the sorting of the following columns
      | Company          |
      | Service          |
      | Task Description |
      | WO#              |
      | Employee         |
      | Start Time       |
      | End Time         |
      | Duration (Hours) |
      | Billable         |
      | WO Required      |
      | Team Lead        |
      | Sr. Team Lead    |
      | Manager          |
      | Location         |


#    TODO: need a better way of testing Open Records since the data is unstable during the test
#    When I click on the "Time Record Open" link
#    And set the tab to "timerecopen"
#    Then I test the sorting of the following columns
#      | Company          |
#      | Service          |
#      | Task Description |
#      | WO #             |
#      | Employee         |
#      | Start Time       |
#      | End Time         |
#      | Duration (Hours) |
#      | Billable         |
#      | Team Lead        |
#      | Sr Team Lead     |
#      | Manager          |

  @13902
  Scenario: Verify Team Lead column in Time Record Open Page Grid
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Team Lead" for "row 3" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @13903
  Scenario: Verify Sr Team Lead column in Time Record Open Page Grid
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    And I sort the "Sr. Team Lead" grid column by "descending"
    When I get the "Sr. Team Lead" for "row 4" row of the grid
    # This Sr. Team Lead column header has an extra space at the end. Will not be selected if the space is removed.
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @13904
  Scenario: Grid -Verify Start Time Date column functionality
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Start Time" for "row 5" row of the grid
    And I set that date in the header
    Then The date in the first row of the grid will match what was entered

  @13905
  Scenario: Grid - Verify Billable column functionality
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Billable" for "row 4" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @13906
  Scenario: Grid - Verify WO Required column functionality
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "WO Required" for "row 8" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @13900
  Scenario: Verify Manager column in Timer grid
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Manager" for "row 5" row of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @13907
  Scenario: Verify Grid -Verify Location Column Search Field
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    When I get the "Location" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

