# All node features have a common sign-in feature. You can drop this feature file into
# any other automation project (and update the the tags for each scenario as necessary)
  # You will need to create some users and a file with some credentials called loginsDev.properties
  # It should contain:
  # # Super Users
  # superUserEmail=*your company email*
  # superUserPass= *your development password*
  #
  ## Other login credentials
  # invalidUser={fake email}<current date>@patracorp.com
  # invalidPassword={fake password}
  # validUser={email}<current date>@patracorp.com
  # validPassword={valid password}
  ## "invalid format" means an email without an @ symbol
  # incorrectUser={email without @ symbol}

Feature: Login Functionality
  Background: Landing Page Login
    Given I am on the login page
    And I wait for 1 seconds

  @XXXX @SmokeTest
  Scenario: Login to node application With InvalidUserName and ValidPassword.
       ##------------Invalid User and Valid password[C2735] - Login Functionality-------------###
    When I enter an "invalid" email address and "valid" password
    And I click the "Sign in" button
    Then I see the Login Failed message

  @XXXX @SmokeTest
  Scenario: Login to node application With Valid User and inValid password
    When I enter a "valid" email address and "invalid" password
    And I click the "Sign in" button
    Then I see the Login Failed message

  @XXXX @SmokeTest
  Scenario: Login to node application With inValid User and inValid password
    When I enter a "invalid" email address and "invalid" password
    And I click the "Sign in" button
    Then I see the Login Failed message

  @XXXX @SmokeTest
  Scenario: Login to node application With Empty UserName And Password
   When I enter a "empty" email address and "valid" password
   And I click the "Sign in" button
   Then I see the empty "email" field warning

  @XXXX @SmokeTest
  Scenario: Login to node application With valid userName And Empty Password.
  When I enter a "valid" email address and "empty" password
  And I click the "Sign in" button
  Then I see the empty "password" field warning

  @XXXX @SmokeTest
  Scenario: Login to node application With empty username and empty password.
  When I enter a "empty" email address and "empty" password
  And I click the "Sign in" button
  Then I see the empty "email" field warning

  @XXXX @SmokeTest
  Scenario: Login to node application With incorrect format of username & valid/invalid password
    When I enter a "incorrect" email address and "valid" password
    And I click the "Sign in" button
    Then I see the email error message

  @XXXX @SmokeTest2
  Scenario: Login to node application With valid UserName And validPassword.
    When I enter the email and password for the "Super User"
    And I click the "Sign in" button
    Then I will be taken to the apps page

  @XXXX @SmokeTest2
  Scenario: VerifyLogout functionality
     When I enter the email and password for the "Super User"
     And I click the "Sign in" button
     Then I will be taken to the apps page
     When I Click on user icon
     And I click Logout button
     Then I am redirected to login page



