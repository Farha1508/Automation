@TimeRecords
Feature: WO Time Records

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
    When I click the "Add Work Order" button
    #

  @9940
  Scenario: Verify Time Records tab grid UI
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    When I click the "Open" tab Reset button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Time Records" tab
    And Verify the following headers are present
      | Start Time          |
      | End Time            |
      | Service Description |
      | Task Description    |
      | Duration (Hours)    |

  @10244
  Scenario: Verify Time Records tab grid functionality
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    When I click the "Open" tab Reset button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Time Records" tab
    And I get the current record count in the "Time Records" tab
    And I enter the following information into the form
      | Summary                      | asdf1234           |
    And I click the "Save & Close" button
    And I click the "Start" button
    Then The Time Tracking modal "is" displayed
    And I select "Audit" from the "Task* :" drop down
    And I add the Work Order number to the Time Tracking modal
    And I click the Start button in the Time Tracking modal
    And I wait for "5" seconds
    #And I refresh the page
    And I click the "Stop" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Time Records" tab
    Then I verify the number of "Time Records" records in KPI has "Increased"
    
