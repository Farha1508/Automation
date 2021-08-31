Feature: Agency Layer automation

  Background: Navigate to Home page of Agency layer
    Given I am on the login page
    When I enter the email and password for the "Agency"
    And I click the Sign In button
    And I click the hamburger menu


  @10398
  Scenario: Creating an Agent through hamburger menu
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter the following information into the form
      | Email        | <gmail>+<random> |
      | First Name   | Agent            |
      | Last Name    | Test             |
      | Mobile Phone | 5552223333       |
    And I click the "Submit Form" button
    And I verify that "Request is successfully done" pop message is displayed
    And I wait for 2 seconds
    And I log out
    And I verify the email for "Agency Staff" was received
    And I open the link from the email
    And I set password of the new user
    And I wait for 2 seconds
    And I login as "agency staff"
    Then I log out


  @10400
  Scenario: Reset Password
    And I select the "Staff" option
    And I pick email of an existing staff
    And I click the "Edit" button
    And I click the "Reset Password" button
    And I verify that "Request is successfully done" pop message is displayed
    And I verify the email for "Forgot Password" was received
    And I open the link from the email
    And I set password of the new user
    And I wait for 2 seconds
    And I login as "agency staff"
    Then I log out


  @10401
  Scenario: Setting an agent as admin
    And I select the "Staff" option
    And I select staff having following values
      | admin     | false |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Admin" question
    And I click the "Submit Form" button
    And I verify that "Request is successfully done" pop message is displayed


  @10402
  Scenario: Setting an agent as Read-Only
    And I select the "Staff" option
    And I select staff having following values
      | admin     | false |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Read Only" question
    And I click the "Submit Form" button
    And I verify that "Request is successfully done" pop message is displayed

  @10403
  Scenario: Setting an agent as Suspended
    And I select the "Staff" option
    And I select staff having following values
      | admin     | false |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Suspended" question
    And I click the "Submit Form" button
    And I select alternate staff
    And I click the "Confirm" button
    And I verify that "Request is successfully done" pop message is displayed


  @10404
  Scenario: Admin agent cannot be set as Read-Only
    And I select the "Staff" option
    And I select staff having following values
      | admin     | true  |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Read Only" question
    And I click the "Submit Form" button
    Then I verify that "Admin can't be read only" validation error message appears


  @10405
  Scenario: Read-Only staff cannot be set as Admin
    And I select the "Staff" option
    And I select staff having following values
      | admin     | false |
      | suspended | false |
      | readOnly  | true  |
    And I wait for 2 seconds
    And I choose the "true" option from the "Admin" question
    And I click the "Submit Form" button
    Then I verify that "Admin can't be read only" validation error message appears


  @10406
  Scenario: Admin True/False button validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I choose the "true" option from the "Admin" question
    And I verify that "Admin" value is not "false"
    And I choose the "false" option from the "Admin" question
    Then I verify that "Admin" value is not "true"

  @10407
  Scenario: Read Only True/False button validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I choose the "true" option from the "Read Only" question
    And I verify that "Read Only" value is not "false"
    And I choose the "false" option from the "Read Only" question
    Then I verify that "Read Only" value is not "true"

  @10408
  Scenario: Suspended True/False button validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I choose the "true" option from the "Suspended" question
    And I verify that "Suspended" value is not "false"
    And I choose the "false" option from the "Suspended" question
    Then I verify that "Suspended" value is not "true"

  @10409
  Scenario: Email field validations
    And I select the "Staff" option
    And I pick email of an existing staff
    And I click the "Add staff" button
    And I enter "abc@gmail" in the "Email" field
    Then I verify that "Email is invalid" validation error message appears
    And I enter "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890abcd1234567890@gmail.com" in the "Email" field
    Then I verify that "Email is invalid" validation error message appears
    And I enter "" in the "Email" field
    And I click the "Submit Form" button
    Then I verify that "Email is invalid" validation error message appears
    And I enter duplicate email in the "Email" field
    And I enter "Jimmy" in the "First Name" field
    And I enter "Anderson" in the "Last Name" field
    And I enter "9876543210" in the "Mobile Phone" field
    And I click the "Submit Form" button
    And I verify that "Failed to create" pop message is displayed


  @10410
  Scenario: First Name field validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890abcd1234567890" in the "First Name" field
    Then I verify that "This field value is too long" validation error message appears
    And I empty the "First Name" field
    Then I verify that "This field is required" validation error message appears

  @10411
  Scenario: Last Name field validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890abcd1234567890" in the "Last Name" field
    Then I verify that "This field value is too long" validation error message appears
    And I empty the "Last Name" field
    Then I verify that "This field is required" validation error message appears

  @10412
  Scenario: Mobile Phone field validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter "ABC" in the "Mobile Phone" field
    And I verify that "Phone number is invalid" validation error message appears
    And I enter "!@#" in the "Mobile Phone" field
    And I verify that "Phone number is invalid" validation error message appears
    And I enter "" in the "Mobile Phone" field
    And I verify that "Phone number is invalid" validation error message appears
    And I enter "98765432109" in the "Mobile Phone" field
    Then I verify that "Phone number is invalid" validation error message appears

