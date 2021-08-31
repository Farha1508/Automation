Feature: Roles

  Background: Login to Node and Navigating to PC home page
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"

  @11333
  Scenario: Verify Time Records With User Base role
    When I enter the email and password for the "user role"
    And I click the Sign In button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    And If the "Timer Alert" modal is displayed, dismiss it

    When I click on "Time Records" Tab
    Then The "Time Record" link "is" displayed
    And The "Time Rec Admin" link "is not" displayed
    And The "Time Rec Open" link "is not" displayed
    And The "Reports" link "is not" displayed

  @11334
  Scenario: Verify Time Records, Time Rec Admin, Time Record Open & Reports With Team Lead/Sr Team Lead/ Manager Role
    When I enter the email and password for the "super user"
    And I click the Sign In button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    And If the "Timer Alert" modal is displayed, dismiss it

    When I click on "Time Records" Tab
    Then The "Time Records" link "is" displayed
    And The "Time Record Admin" link "is" displayed
    And The "Time Record Open" link "is" displayed
    And The "Reports" link "is" displayed