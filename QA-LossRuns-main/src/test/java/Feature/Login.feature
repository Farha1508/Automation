Feature: Login Functionality

  Background: Landing Page Login
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"

  @10664
  Scenario: Login to node application With InvalidUserName and ValidPassword.
       ##------------Invalid User and Valid password[C2735] - Login Functionality-------------###
    When I enter a "invalid" email address and "valid" password
    And Now clicked on Sign In button
    Then I see the Login Failed message

  @10665
  Scenario: Login to node application With Valid User and inValid password
    When I enter a "valid" email address and "invalid" password
    And Now clicked on Sign In button
    Then I see the Login Failed message

  @10670
  Scenario: Login to node application With inValid User and inValid password
    When I enter a "invalid" email address and "invalid" password
    And Now clicked on Sign In button
    Then I see the Login Failed message

    # We need to find a better code to control for the test browser's language/locale (or force it into a specific locale).
    # so commenting them and will fix in next PR

#  @10666
#  Scenario: Login to node application With Empty UserName And Password
#    When I enter a "empty" email address and "valid" password
#    And Now clicked on Sign In button
#    Then I see the empty "email" field warning
#
#  @10667
#  Scenario: Login to node application With valid userName And Empty Password.
#    When I enter a "valid" email address and "empty" password
#    And Now clicked on Sign In button
#    Then I see the empty "password" field warning

  @9241
  Scenario: Login to node application With valid UserName And valid Password.
    When I enter the email and password for the "Super User"
    And Now clicked on Sign In button
    Then I will be taken to the apps page

  @9745
  Scenario: Verify Logout functionality
    When I enter the email and password for the "Super User"
    And Now clicked on Sign In button
    Then I will be taken to the apps page
    When I Click on user icon
    And I click Logout button
    Then I am redirected to login page



