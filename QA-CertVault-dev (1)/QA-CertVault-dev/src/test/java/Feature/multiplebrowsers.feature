Feature: Handling cross browser testing
  Scenario Outline: Accessing multiple browsers

    Given I open browser "<browsers>"
    Given I navigate to login page of cert vault

    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xxxx     |
    And I click on Sign In button

    #C99
    And I click on Recent KPI
    #C705
    And I click on Active KPI
    #C706
    And I click on Expired KPI

    Examples:
      | browsers |
      | chrome   |
      | edge     |
      | firefox  |