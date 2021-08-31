
Feature: Header Actions

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

  @13888
  Scenario: Click on Patra Logo
    And I click on the Patra Logo
    Then The page for the selected company and service will be displayed
    #

  @13936
  Scenario: Verify Company Selection Functionality
    When I switch to the "Company 042" company in App Header which is "valid"
    And I click the "confirm" button
    Then The page for the selected company and service will be displayed

  @13937
  Scenario: Verify Company Selection Functionality Validation
    When I switch to the "Company 019" company in App Header which is "invalid"
    And I click the "confirm" button
    And I click the "OK" button
    Then The page for the selected company and service will be displayed

  @13908
  Scenario: Verify Service Selection Functionality
    When I switch to the "Policy Checking" service in App Header which is "valid"
    And I click the "confirm" button
    Then The page for the selected company and service will be displayed

  @13934
  Scenario: Verify functionality of Global Search
    When I click the "Add Work Order" button
    And I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I enter the Work Order Number in the Global Search
    Then I verify I have opened the expected Work Order

  @13935
  Scenario: Verify validation of Global Search
    Then I enter an invalid Work Order Number in the Global Search




