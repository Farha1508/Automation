Feature: Carrier Layer automation

  Background: Navigate to Owners page of Carrier layer
    Given I am on the login page
    When I enter the email and password for the "Carrier"
    And I click the Sign In button
    And I click the hamburger menu


  @2467
  Scenario: Creating an Owner
    And I select the "Owners" option
    And I open the Add Owner form
    And I enter the following information into the form
      | Default Commission %                                                                    | 23                       |
      | Producer Code                                                                           | 123456                   |
      | Unique Short Name                                                                       | Test Business - <random> |
      | Email address for receipt of notices from Platform                                      | <gmail>+<random>         |
      | Email address for receipt of New Policy notices from Platform                           | <gmail>+<random>         |
      | Business Name                                                                           | Test Business - <random> |
      | Federal Employment Identification Number (FEIN) or Taxpayer Identification Number (TIN) | 123456789                |
      | Street Address                                                                          | Test street              |
      | City                                                                                    | Phoenix                  |
      | State                                                                                   | Arizona                  |
      | Zip Code                                                                                | 85001                    |
      | Owner Admin First Name                                                                  | Testy                    |
      | Owner Admin Last Name                                                                   | McTest                   |
      | Owner Admin Phone number                                                                | 5552223333               |
      | Owner Admin Email                                                                       | <gmail>+<random>         |
    And I add a signature
    And I click the "Submit Form" button
    And I verify the new owner is added
    And I verify the email for "Owner Creation" was received
    And I open the link from the email
    And I set password of the new user
    And I wait for 2 seconds
    And I login as "Owner"
    Then I log out


  @2598
  Scenario: Editing details of an owner
    And I select the "Owners" option
    And I click the "Edit" button
    And I enter "Updated Business Name" in the "Business Name" field
    And I enter "New Test street" in the "Street Address" field
    And I enter "33101" in the "Zip Code" field
    And I enter "Miami" in the "City" field
    And I select "Florida" from the "State" drop down
    And I click the "Submit Form" button
    Then I Ensure that request is successful

  @10339
  Scenario: No field should be empty
    And I select the "Owners" option
    And I click the "Add owner" button
    And I click the "Submit Form" button
    Then I verify that "This field is required" validation error message appears


  @10340
  Scenario: Default Commission field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "A" in the "Default Commission %" field
    And I verify that "Commission should be a number between 0 and 100" validation error message appears
    And I enter "% @" in the "Default Commission %" field
    And I verify that "Commission should be a number between 0 and 100" validation error message appears
    And I enter "1010" in the "Default Commission %" field
    Then I verify that "Commission should be a number between 0 and 100" validation error message appears


  @10341
  Scenario: Producer Code field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Producer Code" field
    Then I verify that "This field value is too long" validation error message appears

  @10343
  Scenario: Validations for the field - 'Email address for receipt of notices from Platform'
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "testemail@patracorp" in the "Email address for receipt of notices from Platform" field
    And I verify that "Email is invalid" validation error message appears
    And I enter "testemail+1234567890123456789012345678901234567890123456789012345678901234567890123456789@patracorp.com" in the "Email address for receipt of notices from Platform" field
    Then I verify that "Email is invalid" validation error message appears

  @10344
  Scenario: Validation for the field - 'Email address for receipt of New Policy notices from Platform'
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "testemail@patracorp" in the "Email address for receipt of New Policy notices from Platform" field
    And I verify that "Email is invalid" validation error message appears
    And I enter "testemail+1234567890123456789012345678901234567890123456789012345678901234567890123456789@patracorp.com" in the "Email address for receipt of New Policy notices from Platform" field
    Then I verify that "Email is invalid" validation error message appears

  @10346
  Scenario: Business Name field validations.
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "Test Owner 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Business Name" field
    Then I verify that "This field value is too long" validation error message appears


  @10347
  Scenario: FIEN field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "A" in the "Federal Employment Identification Number (FEIN) or Taxpayer Identification Number (TIN)" field
    And I verify that "Fein is invalid" validation error message appears
    And I enter "12345678901" in the "Federal Employment Identification Number (FEIN) or Taxpayer Identification Number (TIN)" field
    And I verify that "Fein is invalid" validation error message appears
    And I enter "!@" in the "Federal Employment Identification Number (FEIN) or Taxpayer Identification Number (TIN)" field
    Then I verify that "Fein is invalid" validation error message appears


  @10348
  Scenario: Street Address field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "XYZ 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Street Address" field
    Then I verify that "This field value is too long" validation error message appears

  @10349
  Scenario: City field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "85001" in the "Zip Code" field
    And I enter "Houston" in the "City" field
    And I verify that "City is invalid for current Zip code" validation error message appears
    And I enter "Apple" in the "City" field
    Then I verify that "City is invalid for current Zip code" validation error message appears


  @10350
  Scenario: State field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "85001" in the "Zip Code" field
    And I select "Florida" from the "State" drop down
    And I wait for 2 seconds
    Then I verify that "State is invalid for current Zip code" validation error message appears


  @10351
  Scenario: Zip Code field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "ABC" in the "Zip Code" field
    And I verify that "Zip code is invalid" validation error message appears
    And I enter "850011" in the "Zip Code" field
    And I verify that "Zip code is invalid" validation error message appears
    And I enter "85001" in the "Zip Code" field
    And I select "Arizona" from the "State" drop down
    And I enter "85001" in the "Zip Code" field
    And I select "Arizona" from the "State" drop down
    And I enter "75001" in the "Zip Code" field
    And I wait for 5 seconds
    Then I verify that "Zip code is invalid for selected state" validation error message appears

  @10352
  Scenario: Owner Admin First Name field validations via hamburger menu
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "John 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Owner Admin First Name" field
    Then I verify that "This field value is too long" validation error message appears


  @10353
  Scenario: Owner Admin Last Name field validations via hamburger menu
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "Mendis 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" in the "Owner Admin Last Name" field
    Then I verify that "This field value is too long" validation error message appears


  @10354
  Scenario: Owner Admin Phone Number field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "ABC" in the "Owner Admin Phone number" field
    And I verify that "Phone number is invalid" validation error message appears
    And I enter "!@#" in the "Owner Admin Phone number" field
    And I verify that "Phone number is invalid" validation error message appears
    And I enter "" in the "Owner Admin Phone number" field
    And I verify that "Phone number is invalid" validation error message appears
    And I enter "98765432109" in the "Owner Admin Phone number" field
    Then I verify that "Phone number is invalid" validation error message appears

  @10355
  Scenario: Owner Admin Email field validations
    And I select the "Owners" option
    And I click the "Add owner" button
    And I enter "testemail@patracorp" in the "Owner Admin Email" field
    And I verify that "Email is invalid" validation error message appears
    And I enter "testemail@patracorp.net" in the "Owner Admin Email" field
    And I enter "testemail+123456789012345678901234567890123456789012345678901234567890123456789012345678901@patracorp.net" in the "Owner Admin Email" field
    Then I verify that "Email is invalid" validation error message appears

  @2686
  Scenario: Adding a new staff in carrier
    And I select the "Staff" option
    And I click the "Add staff" button
    And I enter randomEmail in the "Email" field
    And I enter "Kuch" in the "First Name" field
    And I enter "Bhi" in the "Last Name" field
    And I enter "9876543210" in the "Mobile Phone" field
    And I click the "Submit Form" button
    And I Ensure that request is successful
    And I log out
    And I wait for 2 seconds
    And I verify the email for "Carrier Staff" was received
    And I open the link from the email
    And I set password of the new user
    And I wait for 2 seconds
    And I login as "Carrier staff"
    Then I log out


  @10367
  Scenario: Setting the carrier staff as admin
    And I select the "Staff" option
    And I select staff having following values
      | admin     | false |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Admin" question
    And I click the "Submit Form" button
    Then I Ensure that request is successful

  Scenario Outline: Creating new staffs to test 10368, 10370, 10371, and 13394 scenarios
    And I select the "Staff" option
    And I click the "Add staff" button
    And I choose the "<admin>" option from the "Admin" question
    And I choose the "<readOnly>" option from the "Read Only" question
    And I choose the "<suspended>" option from the "Suspended" question
    And I enter randomEmail in the "Email" field
    And I enter "<fname>" in the "First Name" field
    And I enter "<lname>" in the "Last Name" field
    And I enter "1234567890" in the "Mobile Phone" field
    And I click the "Submit Form" button
    And I Ensure that request is successful
    And I log out
    And I wait for 5 seconds
    And I verify the email for "Carrier Staff" was received
    And I open the link from the email
    And I set password of the new user
    And I wait for 2 seconds

    Examples:
      | admin | suspended | readOnly | fname   | lname   |
      | true  | false     | false    | Ram     | Chandra |
      | false | false     | false    | Shayam  | Bihari  |
      | true  | false     | false    | Hari    | Murari  |
      | false | false     | true     | Narayan | Vasudev |


  @10368
  Scenario: Setting the carrier staff as Read-Only
    And I select the "Staff" option
    And I select staff having following values
      | admin     | false |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Read Only" question
    And I click the "Submit Form" button
    And I Ensure that request is successful
    And I log out
    And I wait for 2 seconds
    And I login as "Carrier staff"
    And I wait for 2 seconds
    And I verify that staff is read only
    Then I log out


  @10370
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

  @10371
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

  @10369
  Scenario: Setting the carrier staff as Suspended
    And I select the "Staff" option
    And I select staff having following values
      | admin     | true  |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "true" option from the "Suspended" question
    And I click the "Submit Form" button
    And I select alternate staff
    And I click the "Confirm" button
    Then I Ensure that request is successful

  @13394
  Scenario: Setting an admin staff as non-admin
    And I select the "Staff" option
    And I select staff having following values
      | admin     | true  |
      | suspended | false |
      | readOnly  | false |
    And I wait for 2 seconds
    And I choose the "false" option from the "Admin" question
    And I click the "Submit Form" button
    Then I Ensure that request is successful

