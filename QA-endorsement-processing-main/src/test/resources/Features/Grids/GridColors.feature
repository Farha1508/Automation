@GridColors
Feature: Row Colorization Testing

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

  @9957
  Scenario: Row Color White for Normal Work Orders
    And set the tab to "Open"
    When I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I find the Endorsement Processing work order in the grid
    And The top row background colour is "White"

    # verify normal work orders have a white background

  @9958
  Scenario: Row Color Yellow for Rush Work Orders
    And set the tab to "Open"
    When I click the "Add Work Order" button
    And I click "Rush" checkbox
    And I select "Incoming Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I find the Endorsement Processing work order in the grid
    And The top row background colour is "Rush - Yellow"
    # verify rush work orders have a yellow background

  @10219
  Scenario: Row Color Purple for Late Work Orders
    And set the tab to "Open"
    When I click the "Add Work Order" button
    And I set the "Due Date Override" date picker to today's date
    And I select "Incoming Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I find the Endorsement Processing work order in the grid
    And The top row background colour is "DueDateToday -Purple"
    # verify late work orders have a red background

  @10218
  Scenario: Row Color Red for Due Work Orders
    And set the tab to "Open"
    When I click the "Add Work Order" button
    And I set the date in the "Due Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I select "Incoming Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I find the Endorsement Processing work order in the grid
    And The top row background colour is "SLA -Red"
    # verify work orders are purple on their due date