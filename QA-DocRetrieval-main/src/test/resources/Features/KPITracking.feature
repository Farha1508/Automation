Feature: Demonstration of the KPI tracking methods

  Scenario: KPI AMA-EB
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

    ###---------- App selection ----------###
    When I click on the "AMA-EB" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###

    And I get the count for "All" KPI

  Scenario: KPI PMA
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
     ###---------- App selection ----------###
    When I click on the "PMA" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    And I get the count for "All" KPI


  Scenario: KPI AMA
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super user"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "AMA" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    And I get the count for "All" KPI


  Scenario: KPI Implementations
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "Implementations" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    And I get the count for "All" KPI


  Scenario: KPI Expenses
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "Expenses" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    And I get the count for "All" KPI


  Scenario: KPI Print Shop
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "Print Shop" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    And I get the count for "All" KPI


  Scenario: KPI Purchase Order
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "Purchase Order" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    And I get the count for "All" KPI


  Scenario: KPI Work Order Tracking
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    And I open "DBR" for Company 002
    And I get the count for "All" KPI
    And Verify that "Statements to Check" KPI has "Not Changed"

    And I return to the Apps homepage

        ###---------- App selection ----------###
    When I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    And I open "Certificate Issuance" for Company 002
    And I get the count for "All" KPI
    And Verify that "Incoming Responses 30 mins" KPI has "Not Changed"