@Test
Feature: Login functionality

  Background: Login to Node application
    ###---------- Login component ----------###
    Given I am on the login page

  @7054
  Scenario: Login to node application With valid UserName And valid Password.
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page


  @7055
  Scenario: Login to node application With Invalid UserName and Valid Password.
       ##------------Invalid User and Valid password[C2735] - Login Functionality-------------###
    When I enter an "invalid" email address and "valid" password
    And I click the Sign In button
    Then I see the Login Failed message


  @7056
  Scenario: Login to node application With inValid User and inValid password
    When I enter a "invalid" email address and "invalid" password
    And I click the Sign In button
    Then I see the Login Failed message


  @7057
  Scenario: Login to node application With Valid User and inValid password
    When I enter a "valid" email address and "invalid" password
    And I click the Sign In button
    Then I see the Login Failed message








