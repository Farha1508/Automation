Feature: Rush Order Functionality

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

  @9959
  Scenario: Verify Mark Rush functionality
    When I click the "Add Work Order" button
    And I set the date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I click "Rush" checkbox
    Then I verify that the due date is set to the expected day based on Rush being "enabled" in the Form
    And I click "Rush" checkbox
    And I click the "Submit and Open" button
    And I click the "Mark Rush" button
    Then I verify that the due date is set to the expected day based on Rush being "enabled" in the Details
    # Add Rush Order

  @9960
  Scenario: Verify Remove Rush functionality
    When I click the "Add Work Order" button
    And I set the date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I click "Rush" checkbox
    Then I verify that the due date is set to the expected day based on Rush being "enabled" in the Form
    And I click "Rush" checkbox
    Then I verify that the due date is set to the expected day based on Rush being "disabled" in the Form
    And I click the "Submit and Open" button
    And I click the "Mark Rush" button
    Then I verify that the due date is set to the expected day based on Rush being "enabled" in the Details
    And I click the "Remove Rush" button
    Then I verify that the due date is set to the expected day based on Rush being "disabled" in the Details
    # Remove Rush Order
