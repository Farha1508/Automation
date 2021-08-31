@Login
Feature: Login


  Background: Login component
    Given I am on the login page

  @10806
  Scenario: Login with Invalid User and Valid password
    When I enter an "invalid" email address and "valid" password
    And I click the Sign In button
    Then I see the Login Failed message

  @10807
  Scenario: Login to node application With Valid User and inValid password
    When I enter a "valid" email address and "invalid" password
    And I click the "Sign in" button
    Then I see the Login Failed message

  @10812
  Scenario: Login to node application With inValid User and inValid password
    When I enter a "invalid" email address and "invalid" password
    And I click the "Sign in" button
    Then I see the Login Failed message

  @10808
  Scenario: Login to node application With Empty UserName And Password
    When I enter a "empty" email address and "valid" password
    And I click the "Sign in" button
    Then I see the empty "email" field warning

  @10809
  Scenario: Login to node application With valid userName And Empty Password.
    When I enter a "valid" email address and "empty" password
    And I click the "Sign in" button
    Then I see the empty "password" field warning
