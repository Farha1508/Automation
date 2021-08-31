@WorkOrderGrids
Feature: Work Order Grids

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
    #

  @9996
  Scenario: Paging Functionality
    When I navigate to the "Open" tab
    And I get the current grid page number
    # Right Arrow navigation
    And I move to the next page in the grid
    # Left Arrow navigation
    And I move to the previous page in the grid
    # Input value to change page
    And I change pages by entering a number

  @9997
  Scenario: View Dropdown Functionality
    # Verify correct number of records are listed
    When I navigate to the "Open" tab
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected

  @9999
  Scenario: Sorting
    # Verify Open sorted by Due Date
    When I navigate to the "Open" tab
    When I get the "Due Date" for "row 1" row of the grid
    And I sort the "Due Date" grid column by "Ascending"
    Then I verify that value of "Due Date" for "row 1" matches
    # Verify Completed sorted by Due Date
    When I navigate to the "Completed" tab
    When I get the "Due Date" for "row 1" row of the grid
    And I sort the "Due Date" grid column by "Ascending"
    Then I verify that value of "Due Date" for "row 1" matches
    # Verify Discarded sorted by Due Date
    When I navigate to the "Discarded" tab
    When I get the "Due Date" for "row 1" row of the grid
    And I sort the "Due Date" grid column by "Ascending"
    Then I verify that value of "Due Date" for "row 1" matches
    # Verify Important Instructions sorted by Due Date
    When I navigate to the "Important Instructions" tab
    When I get the "Due Date" for "row 1" row of the grid
    And I sort the "Due Date" grid column by "Ascending"
    Then I verify that value of "Due Date" for "row 1" matches
    # Verify all grids have their own sorting

  @9998
  Scenario: Reset Functionality
    When I navigate to the "Open" tab
    And I get the current record count in the "Open" tab
    # Reset button appears on all grids
    # Reset removes filtering
    And I enter a filter that will return no results
    Then I verify the number of records in "Open" is 0
    When I click the "Open" tab Reset button
    Then I verify the number of "Open" records in KPI has "Not Changed"
    When I navigate to the "Completed" tab
    And I get the current record count in the "Completed" tab
    And I enter a filter that will return no results
    Then I verify the number of records in "Completed" is 0
    When I click the "Completed" tab Reset button
    Then I verify the number of "Completed" records in KPI has "Not Changed"
    When I navigate to the "Discarded" tab
    And I get the current record count in the "Discarded" tab
    And I enter a filter that will return no results
    Then I verify the number of records in "Discarded" is 0
    When I click the "Discarded" tab Reset button
    Then I verify the number of "Discarded" records in KPI has "Not Changed"
    When I navigate to the "Important Instructions" tab
    And I get the current record count in the "Important Instructions" tab
    And I enter a filter that will return no results
    Then I verify the number of records in "Important Instructions" is 0
    When I click the "Important Instructions" tab Reset button
    Then I verify the number of "Important Instructions" records in KPI has "Not Changed"
    # Verify grid not reset when closing WO

  @9954
  Scenario: Verify Grid -Select Multiple Values selection from grid dropdown
    When I get the number of records in the "Open" tab
    And I select "Available" from the "Status" header in the grid
    And I select "Completed" from the "Status" header in the grid
    And I select "Discarded" from the "Status" header in the grid
    And I select "In Progress" from the "Status" header in the grid
    And I select "On Hold" from the "Status" header in the grid
    Then I check if the following items are selected in the "Status" header
      | Available | Completed | Discarded | In Progress | On Hold |
    And The number of records in the "Open" tab is "decreased"

  @9952
  Scenario: Verify Grid -Select All Values selection from grid dropdown
    When I get the number of records in the "Completed" tab
    And I select "Available" from the "Status" header in the grid
    And I select "Completed" from the "Status" header in the grid
    And I select "Discarded" from the "Status" header in the grid
    And I select "In Progress" from the "Status" header in the grid
    And I select "On Hold" from the "Status" header in the grid
    And I select "Incoming Request" from the "Status" header in the grid
    And I select "Invalid Status" from the "Status" header in the grid
    And I select "Reports Reviewed" from the "Status" header in the grid
    And I select "Statement to Delivery" from the "Status" header in the grid
    And I select "Summary Pending" from the "Status" header in the grid
    And I select "Summary QA Pending" from the "Status" header in the grid
    And I select "Unassigned" from the "Status" header in the grid
    And I select "Dev Status" from the "Status" header in the grid
    Then I check if the following items are selected in the "Status" header
      | Available | Completed | Discarded | In Progress | On Hold | Incoming Request | Invalid Status | Reports Reviewed | Statement to Delivery | Summary Pending | Summary QA Pending | Unassigned | Dev Status |

    And The number of records in the "Completed" tab is "the same"