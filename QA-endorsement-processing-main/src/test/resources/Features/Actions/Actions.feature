@Actions
Feature: Actions tests

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

  @9926
  Scenario: Save & Next button
    And I sort the "Work Order #" grid column by "Descending"
    And I enter the "Work Order #" value of "row 2" into value store as "WO #"
    And I open the Work Order
    Then I check if the "Save & Next" button is "disabled"
    And I create a random string
    And I enter a random string in the "Requestor" field
    Then I check if the "Save & Next" button is "enabled"
    And I click the "Save & Next" button
    Then I verify I am at the expected work order after hitting Save & Next
    # Save and Next button works as expected

  @10337
  Scenario: Save & Next on last WO
    And I sort the "Work Order #" grid column by "Descending"
    And I enter the "Work Order #" value of "row 1" into value store as "WO #"
    When I click the "Add Work Order" button
    When I click the "Submit and Open" button
    Then I check if the "Save & Next" button is "disabled"
    And I create a random string
    And I enter a random string in the "Requestor" field
    Then I check if the "Save & Next" button is "disabled"
    # Save and Next button not active for last WO in db

  @9928
  Scenario: Cancel Button
    And I sort the "Work Order #" grid column by "Descending"
    And I enter the "Work Order #" value of "row 1" into value store as "WO #"
    And I open the Work Order
    And I create a random string
    And I enter a random string in the "Requestor" field
    And I click the "Save" button
    Then I check if the "Cancel Changes" button is "disabled"
    And I enter "new value" in the "Requestor" field
    Then I check if the "Cancel Changes" button is "enabled"
    And I click the "Cancel Changes" button
    # the cancel button does not become active if you enter a value that is the same as the existing value
    And I enter a random string in the "Requestor" field
    Then I check if the "Cancel Changes" button is "disabled"
    # cancel button works as expected


