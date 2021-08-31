Feature: Demonstration of the valueStore utilization
  Except for the login component, the scenarios here only execute code from the Step definition file

  Background: Login component
    Given I am on the login page
    ###---------- Login component ----------###
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

    ###---------- App selection ----------###
    When I click on the "PMA" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###


  Scenario: Filling in form
    And I click the "Add Business" button
    And I select "Patra Select" from the "Brand" drop down
    And I enter "ValueStore Test <current date>" in the "Business/Policy Holder Name" field
    And I select "Pest Control" from the "Business Class" drop down
    And I enter "This is a test of the ValueStore array" in the "Description of Operations" text box


  Scenario: Display info in valueStore
    And I get the "Business/Policy Holder Name" info from valueStore
    And I get the "Brand" info from valueStore
    And I get the "Description of Operations" info from valueStore

  Scenario: Clear valueStore and verify
    And I clear the valueStore
    And I get the "WO Title" info from valueStore

