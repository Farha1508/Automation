
Feature: Demonstrating different uses for JavaScript Executor

  Background: Log into PMA
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

  Scenario: Click Add Request
    And I click on the Add Request button with JSE
    And I wait for "5" seconds

  Scenario: Scroll window
    And I navigate to the "Purchase Orders" tab
    And I select the "100" option from the the Viewing drop down
    And I wait for "5" seconds
    And I scroll down by "200" pixels
    And I wait for "5" seconds