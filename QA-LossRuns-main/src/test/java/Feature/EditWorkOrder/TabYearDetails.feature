Feature: Test Year Details page.

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
    And I navigate to the "Open" tab

  @9897
  Scenario: Verify Year Details tab
    When I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    When I navigate to the "Year Details" tab
    And Verify the following columns are present
      | Carrier              |
      | Policy Number        |
      | Report Link          |
      | Broker               |
      | AM Response Pending  |
      | Report Received Date |
      | Policy Year          |
      | Action               |

  @9902
  Scenario: Validate Not updating the mandatory fields
    When I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    When I navigate to the "Year Details" tab
    And I click the "Add Year Detail" button
    And I click the "Save" button
    Then I verify the submit error "Your changes cannot be saved at this time. Some Required Fields are empty" appears
    When I enter the following information into the table
      | Carrier | Associated Indemnity Corp. |
    And I click the "Save" button
    Then I verify the submit error "Your changes cannot be saved at this time. Some Required Fields are empty" appears

  @9900
  Scenario:Verify Delete Order functionality for Year Details tab
    When I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    When I navigate to the "Year Details" tab
    And I click the "Add Year Detail" button
    When I enter the following information into the table
      | Carrier       | Associated Indemnity Corp. |
      | Policy Number | CA101                      |
      | Report Link   | abc.com                    |
    And I click the "Save" button
    Then Reload the page
    Then Click on delete icon
    And I click the "Save" button
    Then Reload the page
    And I Verify the row is deleted

  @9898 @9899
  Scenario:Verify Add Year Order functionality for Year Details tab
    When I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    When I navigate to the "Year Details" tab
    # 1st - adding a new record and verify
    And I click the "Add Year Detail" button
    When I enter the following information into the table
      | Carrier              | Associated Indemnity Corp. |
      | Policy Number        | CA101                      |
      | Report Link          | abc.com                    |
      | Report Recieved Date | <current date>             |
      | Policy Year          | 2001-2002                  |
    And I click on "Broken" checkbox
    And I click on "AM Response Pending" checkbox
    And I click the "Save" button
    Then Reload the page
    Then The record is added
      | Carrier       |
      | Policy Number |
      | Report Link   |
      | Policy Year   |
    And I verify the following checkboxes in the table
      | Broken              | checked |
      | AM Response Pending | checked |

    #2nd - Editing the same record and verify
    When I enter the following information into the table
      | Carrier              | CareSouth Indemnity Services |
      | Policy Number        | PC201                        |
      | Report Link          | xyz.com                      |
      | Report Recieved Date | <current date>               |
      | Policy Year          | 2002-2003                    |
    And I click on "Broken" checkbox
    And I click on "AM Response Pending" checkbox
    And I click the "Save" button
    Then Reload the page
    Then The record is added
      | Carrier       |
      | Policy Number |
      | Report Link   |
      | Policy Year   |
    And I verify the following checkboxes in the table
      | Broken              | unchecked |
      | AM Response Pending | unchecked |