@Actions
Feature: Actions Button Tests for Edit WO Page

  Background: Login component
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"
    ###---------- Login component ----------###
    When I enter the email and password for the "super user"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- Application Select ----------###
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    #

  @9720
  Scenario: Save & Next button
    And I sort the "Work Order #" grid column by "Descending"
    And I enter the "Work Order #" value of "row 2" into value store as "WO #"
    And I open the Work Order
    Then I check if the "Save & Next" button is "disabled"
    And I create a random string
    And I enter a random string in the "Requestor" field
    Then I click outside the field
    Then I check if the "Save & Next" button is "enabled"
    And Click on "Save & Next" button
    Then I verify I am at the expected work order after hitting Save & Next
    # Save and Next button works as expected

  @10338
  Scenario: Save & Next on last WO
    And I sort the "Work Order #" grid column by "Descending"
    And I enter the "Work Order #" value of "row 1" into value store as "WO #"
    When I Click on Add Work Order button
    And Click on "Submit and Open" button
    Then I check if the "Save & Next" button is "disabled"
    And I create a random string
    And I enter a random string in the "Requestor" field
    Then I click outside the field
    Then I check if the "Save & Next" button is "disabled"
    # Save and Next button not active for last WO in db

  @9718
  Scenario: Cancel Button
    And I sort the "Work Order #" grid column by "Descending"
    And I enter the "Work Order #" value of "row 1" into value store as "WO #"
    And I open the Work Order
    And I create a random string
    And I enter a random string in the "Requestor" field
    Then I click outside the field
    And Click on "Save" button
    Then I check if the "Cancel Changes" button is "disabled"
    And I enter "new value" in the "Requestor" field
    Then I click outside the field
    Then I check if the "Cancel Changes" button is "enabled"
    And Click on "Cancel Changes" button
    # the cancel button does not become active if you enter a value that is the same as the existing value
    And I enter a random string in the "Requestor" field
    Then I click outside the field
    Then I check if the "Cancel Changes" button is "disabled"
    # cancel button works as expected

  @9717
  Scenario: Close Button
    And I navigate to the "Open" tab
    When I Click on Add Work Order button
    And Click on "Submit" button
    And I get the new work order number from the confirmation modal
    When I search the stored work order number
    And I click on the top work order link
    And I create a random string
    And I enter a random string in the "Requestor" field
    Then I click outside the field
    And Click on "Save" button
    Then I check if the "Close" button is "enabled"
    And I enter "new value" in the "Requestor" field
    Then I click outside the field
    Then I check if the "Close" button is "enabled"
    And Click on "Close" button
    When I search the stored work order number
    And I click on the top work order link
    And I check the "Requestor" fields input is not saved


