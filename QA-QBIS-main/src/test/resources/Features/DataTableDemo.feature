Feature: Demonstration of DataTable utilization
  Except for the login component, the scenarios here only execute code from the Step definition file

  @2450
  Scenario: Creating an application for new Business
    Given I am on the login page
    When I enter the email and password for the "Owner"
    And I click the Sign In button
    And I open the New Application form
    And I enter "Test Business Name" in the "Business Name" field
    And I enter "Testing Services" in the "Describe Your Business Services" selector
    And I enter "98666" in the "Zip Code" field
    And I enter "TestFirst" in the "First Name" field
    And I enter "TestLast" in the "Last Name" field
    And I enter "testemail@patracorp.net" in the "Email" field
    And I enter "5555555555" in the "Phone" field
    And I click the "Continue" button

  Scenario: Using DataTable with titles on right and entry on left
    Given I am on the login page
    When I enter the email and password for the "Carrier"
    And I click the Sign In button
    And I open the New Application form
    And I enter the following information into the form
      | Business Name    | Test Business Name              |
      | Testing Services | Describe Your Business Services |
      | Zip Code         | 98666                           |
      | First Name       | TestFirst                       |
      | Last Name        | TestLast                        |
      | Email            | testemail@patracorp.net         |
      | Phone            | 5555555555                      |
    And I click the "Continue" button

  Scenario: Using DataTable with titles on top and entry underneath
    Given I am on the login page
    When I enter the email and password for the "Agency"
    And I click the Sign In button
    And I open the New Application form
    And I edit the following fields
      | Business Name      | Zip Code | Describe Your Business Services | First Name | Last Name | Email                   | Phone      |
      | Test Business Name | 98666    | Testing Services                | TestFirst  | TestLast  | testemail@patracorp.net | 5555555555 |

    And I click the "Continue" button

  Scenario Outline: Outline test
    Given I am on the login page
    When I enter the "<email>" email and "<password>" password
    And I click the Sign In button

    Examples:
      | email            | password  |
      | email1@email.com | password1 |
      | email2@email.com | password2 |

  Scenario Outline: Browser testing
    Given I am using the "<browser>" browser
    And I am on the login page
    When I enter the email and password for the "Carrier"
    And I click the Sign In button

    Examples:
      | browser |
      | chrome  |
      | firefox |
      | edge    |

