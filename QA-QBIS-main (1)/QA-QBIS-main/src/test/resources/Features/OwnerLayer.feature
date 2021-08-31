Feature: Owner Layer automation

  Background: Navigate to Home page of Owner layer
    Given I am on the login page
    When I enter the email and password for the "Owner"
    And I click the Sign In button
    And I click the hamburger menu


  @10760
  Scenario: Creating an Agency
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter the following information into the form
      | Default Commission % | 23                     |
      | Producer Code        | 123456                 |
      | Agency Name          | Test Agency - <random> |
      | Agent First Name     | Testy                  |
      | Agent Last Name      | McTest                 |
      | Agent Email          | <gmail>+<random>       |
      | Mobile Phone         | 5552223333             |
      | Address              | Test street            |
      | Zip Code             | 85001                  |
    And I click the "Submit Form" button
    And I verify that "Request is successfully done" pop message is displayed
    And I wait for 2 seconds
    And I log out
    And I verify the email for "Agency Creation" was received
    And I open the link from the email
    And I set password of the new user
    And I wait for 2 seconds
    And I login as "agency staff"
    Then I log out


  @10765
  Scenario: Default Commission field validations - Agency
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "A" in the "Default Commission %" field
    And I verify that "Commission should be a number between 0 and 100" validation error message appears
    And I enter "% @" in the "Default Commission %" field
    And I verify that "Commission should be a number between 0 and 100" validation error message appears
    And I enter "1010" in the "Default Commission %" field
    And I verify that "Commission should be a number between 0 and 100" validation error message appears
    And I empty the "Default Commission %" field
    Then I verify that "This field is required" validation error message appears


  @10766
  Scenario: Producer Code field validations - Agency
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Producer Code" field
    And I verify that "This field value is too long" validation error message appears
    And I empty the "Producer Code" field
    Then I verify that "This field is required" validation error message appears

  @10767
  Scenario: Agency Name field validations.
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "Test Agency 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Agency Name" field
    And I verify that "This field value is too long" validation error message appears
    And I enter "a#b%" in the "Agency Name" field
    And I verify that "Characters of # and % cannot be used in this field" validation error message appears
    And I empty the "Agency Name" field
    Then I verify that "This field is required" validation error message appears

  @10768
  Scenario: Agent First Name field validations via hamburger menu
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "John 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Agent First Name" field
    And I verify that "This field value is too long" validation error message appears
    And I empty the "Agent First Name" field
    Then I verify that "This field is required" validation error message appears

  @10769
  Scenario: Agent Last Name field validations via hamburger menu
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "John 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Agent Last Name" field
    And I verify that "This field value is too long" validation error message appears
    And I empty the "Agent Last Name" field
    Then I verify that "This field is required" validation error message appears

  @10770
  Scenario: Agent Email field validations via hamburger menu
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "testemail@patracorp" in the "Agent Email" field
    And I verify that "Email is invalid" validation error message appears
    And I enter "testemail+123456789012345678901234567890123456789012345678901234567890123456789012345678901@patracorp.net" in the "Agent Email" field
    And I verify that "Email is invalid" validation error message appears
    And I empty the "Agent Email" field
    Then I verify that "Email is invalid" validation error message appears


  @10771
  Scenario: Agent Phone Number field validations via hamburger menu
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "ABC" in the "Mobile Phone" field
    And I verify that "Phone number is invalid" validation error message appears
    And I enter "!@#" in the "Mobile Phone" field
    And I verify that "Phone number is invalid" validation error message appears
    And I empty the "Mobile Phone" field
    And I verify that "Phone number is invalid" validation error message appears
    And I enter "98765432109" in the "Mobile Phone" field
    Then I verify that "Phone number is invalid" validation error message appears

  @10772
  Scenario: Agent address field validations via hamburger menu
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "XYZ 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Address" field
    And I verify that "This field value is too long" validation error message appears
    And I empty the "Address" field
    Then I verify that "This field is required" validation error message appears

  @10773
  Scenario: Agent Zip Code field validations via hamburger menu
    And I select the "Agencies" option
    And I click the "Add agency" button
    And I enter "ABC" in the "Zip Code" field
    And I verify that "Zip code is invalid" validation error message appears
    And I enter "850011" in the "Zip Code" field
    And I verify that "Zip code is invalid" validation error message appears
    And I enter "11111" in the "Zip Code" field
    And I verify that "Zip code is invalid for selected state" validation error message appears
    And I empty the "Zip Code" field
    Then I verify that "Zip code is invalid" validation error message appears


  @10379
  Scenario: Creating a staff
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter the following information into the form
      | Email        | <gmail>+<random> |
      | First Name   | AgencyStaff      |
      | Last Name    | Test             |
      | Mobile Phone | 5552223333       |
    And I click the "Submit Form" button
    And I verify that "Request is successfully done" pop message is displayed
    And I wait for 2 seconds
    And I log out
    And I verify the email for "Staff Creation" was received
    And I open the link from the email
    And I set password of the new user
    And I wait for 2 seconds
    And I login as "owner"
    Then I log out


  @10381
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
    And I login as "owner"
    Then I log out


  @10382
  Scenario: Setting the owner staff as admin
    And I select the "Staff" option
    And I select staff having following values
      | admin     | false |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Admin" question
    And I click the "Submit Form" button
    And I verify that "Request is successfully done" pop message is displayed


  @10383
  Scenario: Setting the owner staff as Read-Only
    And I select the "Staff" option
    And I select staff having following values
      | admin     | false |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Read Only" question
    And I click the "Submit Form" button
    And I verify that "Request is successfully done" pop message is displayed

  @10384
  Scenario: Setting the owner staff as Suspended
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


  @10385
  Scenario: Admin staff cannot be set as Read-Only
    And I select the "Staff" option
    And I select staff having following values
      | admin     | true  |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Read Only" question
    And I click the "Submit Form" button
    Then I verify that "Admin can't be read only" validation error message appears


  @10386
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

  @10390
  Scenario: Email field validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter "abc@gmail" in the "Email" field
    Then I verify that "Email is invalid" validation error message appears
    And I enter "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890abcd1234567890@gmail.com" in the "Email" field
    Then I verify that "Email is invalid" validation error message appears
    And I enter "" in the "Email" field
    And I click the "Submit Form" button
    Then I verify that "Email is invalid" validation error message appears
    And I enter "qa.jellyfish+1234@gmail.com" in the "Email" field
    And I enter "Jimmy" in the "First Name" field
    And I enter "Anderson" in the "Last Name" field
    And I enter "9876543210" in the "Mobile Phone" field
    And I click the "Submit Form" button
    And I verify that "Failed to create" pop message is displayed


  @10391
  Scenario: First Name field validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890abcd1234567890" in the "First Name" field
    Then I verify that "This field value is too long" validation error message appears
    And I empty the "First Name" field
    Then I verify that "This field is required" validation error message appears

  @10392
  Scenario: Last Name field validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890abcd1234567890" in the "Last Name" field
    Then I verify that "This field value is too long" validation error message appears
    And I empty the "Last Name" field
    Then I verify that "This field is required" validation error message appears

  @10393
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


  @10387
  Scenario: Admin True/False button validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I choose the "true" option from the "Admin" question
    And I verify that "Admin" value is not "false"
    And I choose the "false" option from the "Admin" question
    Then I verify that "Admin" value is not "true"

  @10388
  Scenario: Read Only True/False button validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I choose the "true" option from the "Read Only" question
    And I verify that "Read Only" value is not "false"
    And I choose the "false" option from the "Read Only" question
    Then I verify that "Read Only" value is not "true"

  @10389
  Scenario: Suspended True/False button validations
    And I select the "Staff" option
    And I click the "Add staff" button
    And I choose the "true" option from the "Suspended" question
    And I verify that "Suspended" value is not "false"
    And I choose the "false" option from the "Suspended" question
    Then I verify that "Suspended" value is not "true"
