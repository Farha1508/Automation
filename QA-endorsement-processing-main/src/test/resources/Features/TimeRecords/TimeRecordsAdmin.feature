Feature: Policy Checking -Home Page Different Grids with Invalid search Data
  Background: Verifying the Grid Column Headers Presence and Validating error messages for grid search
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the Sign In button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Endorsement Processing" for company "Company 002"
    When I click on the "Time Record Admin" link
    And set the tab to "Time Record Admin"

  @13940
  Scenario: Edit the WO in Time Records Admin
    # Blocked/fails due to wot-pc#345
    # Setup: create a new work order
    Given I click on the "Work Orders" link

    When I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up

    When I click on the "Time Record Admin" link
    And set the tab to "Time Record Admin"
    And I click the "Start" button
    Then The Time Tracking modal "is" displayed
    And I select "Audit" from the "Task* :" drop down
    And I add the Work Order number to the Time Tracking modal
    And I click the Start button in the Time Tracking modal

    When I wait for "5" seconds
    And I click the "Stop" button
    And I click on the "Run" link
    And I find the Endorsement Processing work order in the Time Record Admin grid
    Then The work order will be displayed in the grid
    #TODO: add edit steps


  @14351
  Scenario: Verify Form & TO Fields error message validation
    # Fails due to wot-pc#344
    And I clear the "To" field
    And I clear the "From" field
    When I click on the "Run" link
    Then I see an error warning for the "To" field
    And I see an error warning for the "From" field

  @14352
  Scenario: Get the Last 3 months Time records from Time record admin page
    When I set the "From" date picker to 3 months ago
    And I set the "To" date picker to 0 month ago
    And I click on the "Run" link
    And I sort the "Start Time" grid column by "ascending"
    # Then the top entry has a date/time on/after the From value

  @14348
  Scenario:  Verify Default From & To Fields
    Then The the "From" field is set to "today"
    And The the "To" field is set to "tomorrow"
    And There are no results

    When I set the "From" date picker to 2 months ago
    And I set the "To" date picker to 1 month ago
    And I click on the "Run" link
    Then There are results


  @14350
  Scenario: Verify future date selection on edit page
    # Fails due to wot-pc#343
    When I set the "From" date picker to 2 months ago
    And I set the "To" date picker to 1 month ago
    And I click on the "Run" link
    And I edit the top Time Rec row
    Then I am unable to select a future "From"
    Then I am unable to select a future "To"