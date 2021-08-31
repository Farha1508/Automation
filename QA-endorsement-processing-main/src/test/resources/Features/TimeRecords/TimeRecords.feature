Feature: Policy Checking -Home Page Different Grids with Invalid search Data

  Background: Verifying the Grid Column Headers Presence and Validating error messeges for grid search
    Given I am on the login page

  @13928
  Scenario: Verify Time Records in Time Records Page
    When I enter the email and password for the "base user"
    And I click the Sign In button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Endorsement Processing" for company "Company 002"
    #And If the "Timer Alert" modal is displayed, dismiss it

    When I click on the "Time Records" link
    And I store the current username
    Then All search results will contain the following values
      | Employee | <current user> |

    # Not technically part of this test case. Was part of original automation. Will move when I find out where it should go.
    When set the tab to "timerecordspage"
    Then Verify the following headers are present
      | Company          |
      | Service          |
      | Task Description |
      | WO #             |
      | Start Time       |
      | End Time         |
      | Duration         |
      | Billable         |