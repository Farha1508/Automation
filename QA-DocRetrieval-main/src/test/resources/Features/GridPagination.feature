
Feature: Testing pagination for grid


  Scenario: Grid pagination for Purchase Order

    ##------------- Initiator - Login Functionality --------------##
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
    ###------------- Testing pagination on Purchase Orders tab --------------###
    When I navigate to the "Purchase Orders" tab
    And I get the current grid page number
    And I move to the next page in the grid
    And I move to the previous page in the grid
    And I change pages by entering a number
    ###------------- Testing pagination on Purchase Orders tab --------------###
    ###------------- Testing View dropdown on Purchase Orders tab --------------###
    When I navigate to the "Purchase Orders" tab
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    ###------------- Testing View dropdown on Purchase Orders tab --------------###

  Scenario: Grid pagination for Work Order Tracking
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

    ###---------- App selection ----------###
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    ###---------- Application Select ----------###
    When I open "Policy Checking" for company "Company 002"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    When I navigate to the "Open Policies" tab
    ###------------- Testing pagination on Open Policies tab --------------###
    And I move to the next page in the grid
    And I move to the previous page in the grid
    ###------------- Testing pagination on Open Policies tab --------------###
    ###------------- Testing View dropdown on Open Policies tab --------------###
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    ###------------- Testing View dropdown on Open Policies tab --------------###