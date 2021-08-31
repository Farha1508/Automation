Feature: Demonstrating the date picker for forms

  Background: Login
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

  Scenario: Pick date from PO item
    And I click on the "Print Shop" tile
    And I click the "Add Record" button
    And I pick today's date from the "Date of Customer Request" date picker
    And I set the date in the "Date of Customer Request" date picker to
      | Month  | Day | Year |
      | August | 11  | 2020 |
    And I wait for "5" seconds

  Scenario: Pick date from add implementation
    And I click on the "Implementations" tile
    And I click the "Add Implementation" button
    And I pick today's date from the "Implementation Start Date" date picker
    And I set that date in the "Customer Launch Expectation" date picker to
      | Month | Day | Year |
      | April | 1   | 2021 |
    And I wait for "5" seconds


  Scenario: Pick date from time rec - Policy Checking
    And I click on the "Work Order Tracking" tile
    And I open "Policy Checking" for company "Company 002"
    And I click on the "Time Rec Admin" link
    And I set that date in the "From" date picker to
      | Month | Day | Year | Time | AM / PM |
      | June  | 19  | 2020 | 6:27 | AM      |
    And I pick today's date from the "To" date picker
    And I click on the "Run" link
#
  Scenario: Pick date from Add Policy - Policy Checking
    And I click on the "Work Order Tracking" tile
    And I open "Policy Checking" for company "Company 002"
    And I click the "Add Policy" button
    And I set that date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I set that date in the "Due Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | July  | 5   | 2020 | 9:15 | PM      |
    And I wait for "5" seconds

  Scenario: Pick date from time rec - Certificate Issuance
    And I click on the "Work Order Tracking" tile
    And I open "Certificate Issuance" for company "Company 019"
    And I click on the "Time Rec Admin" link
    And I set that date in the "From" date picker to
      | Month  | Day | Year | Time | AM / PM |
      | August | 1   | 2020 | 6:27 | AM      |
    And I set that date in the "To" date picker to
      | Month  | Day | Year | Time | AM / PM |
      | August | 8   | 2020 | 9:15 | PM      |
    And I click on the "Run" link
    And I wait for "10" seconds