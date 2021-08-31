@EditWorkOrder
Feature: Edit Work Order

  Background: Login component
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"
    ###---------- Login component ----------###
    When I enter the email and password for the "super user"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- Application Select ----------###
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    When I click the "Add Work Order" button
    And I navigate to the "Open" tab

  @9746
  Scenario: Original Email is non-editable
    And I Click on "Original Email" tab
    And I enter "Original Email <current date>" in the "Original Email" field
    And I click the "Submit and Open" button
    And I click the "Original Email" button
    And I verify the textarea "Original Email" matches the created "Original Email"
    And I verify the Original Email textarea is readonly
    # Verify Original Email field displays as expected and is not editable

  @9909
  Scenario:  Validate QA Detail Tab on WO Details page for Team-Lead and above
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    Then I verify the "QA" tab is "Present"
    Then I Click on "QA" tab
    Then I enter values in fields
      | # of Errors |
      | 12          |
    Then I select values from the drop down
      | Audit/QA | Error Made By | Error Type                   | Error Filed By |
      | Audit    | Satish Prasad | Basic Cert Processing errors | Satish Prasad  |
    Then I enter values in text area
      | Error Description                               |
      | Testing QA tab functionality through Automation |
    Then Click on "Save" button

  @9749
  Scenario: Verify QA Tab as Base User is not present
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    Then I verify the "QA" tab is "Present"
    And I log out
    When I enter the email and password for the "User Role"
    And I click the Sign In button
    Then I will be taken to the apps page
    When I click on the "Work Order Tracking" tile
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    When I search the stored work order number
    And I click on the top work order link
    Then I verify the "QA" tab is "Not Present"

  @9968
  Scenario: Verify QA tab for Base User with QA active
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    And I Click on "QA" tab
    And I enter the following information into the form
      | Audit/QA          | QA            |
      | # of Errors       | 2             |
      | Error Made By     | JFT QA user   |
      | Error Type        | Typo          |
      | Error Filed By    | Satish Prasad |
      | Error Description | error test    |
    When I click the "Save & Close" button
    When I search the stored work order number
    And I log out
    When I enter the email and password for the "Base QA User"
    And I click the Sign In button
    Then I will be taken to the apps page
    When I click on the "Work Order Tracking" tile
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    When I search the stored work order number
    And I click on the top work order link
    And I Click on "QA" tab
    Then I verify the following information in the form
      | Audit/QA          | QA            |
      | # of Errors       | 2             |
      | Error Made By     | JFT QA user   |
      | Error Type        | Typo          |
      | Error Filed By    | Satish Prasad |
      | Error Description | error test    |

  @9757
  Scenario: Verify History Grid
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    And I Click on "History" tab
    When I select the "20" option from the the History tab Viewing drop down
    Then I check if the "Save" button is "disabled"
    Then I check if the "Save & Next" button is "disabled"
    Then I check if the "Save & Close" button is "disabled"


  @9754,@9756,@9755
  Scenario: Sorting
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    When I search the stored work order number
    And I log out
    When I enter the email and password for the "Base QA User"
    And I click the Sign In button
    Then I will be taken to the apps page
    When I click on the "Work Order Tracking" tile
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    And I click the "Mark Rush" button
    And I click the "Save" button
    When I navigate to the "History" tab
    Then I test the sorting of the following columns
      | Date      |
      | User Name |