Feature: Demonstration of steps and methods for filling out form items

  These scenarios will show the use of the methods to fill out text fields, drop down lists, text areas and date pickers

  Background: Print Shop Login
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "Print Shop" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###

  Scenario: Fill form with individual steps
    ###---------- Fill Form ----------###
    And I click the "Add Record" button
    And I select "Company 748" from the "Company" drop down
    And I select "Policy Checking" from the "Service" drop down
    And I enter "Text field test <current date>" in the "Client Code" field
    And I set the date in the "Date of Customer Request" date picker to
      | Month  | Day | Year |
      | August | 11  | 2020 |
    And I set the "Mail Date" date picker to today's date
    And I enter "Text area test <current date>" in the "Notes" text box
    And I wait for "5" seconds

  Scenario: Fill in form with data table
    And I click the "Add Record" button
    And I enter the following information into the form
      | Company     | Company 748                    |
      | Service     | Policy Checking                |
      | Client Code | Text field test <current date> |
      | Notes       | Text area test <current date>  |
    And I set the date in the "Date of Customer Request" date picker to
      | Month  | Day | Year |
      | August | 11  | 2020 |
    And I wait for "5" seconds