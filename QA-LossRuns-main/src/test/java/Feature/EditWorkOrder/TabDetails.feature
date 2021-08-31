Feature: Detail Tab

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

    @9750
  Scenario: Validate Details tab fields
    When I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    Then I Click on "Detail" tab
    # First Row
    Then The following elements exist
      | Branch Office |
      | Division      |
      | Department    |
      | Profit Center |
     # Second Row
    Then The following elements exist
      | Number Of                             |
      | # Downloaded                          |
      | # of Loss Runs                        |
      | # Ordered                             |
      | Total # of Requests                   |
      | # Contractor's Liability Phone Report |
      | # of Contractor's GL Phone Report     |

    # Third Row
    Then The following elements exist
      | Comments          |
      | Date Completed    |
      | Completed WO Flag |

    When I enter the following information into the form
      | Status                                | Available                    |
      | Branch Office                         | 101 Walnut Creek, CA         |
      | Division                              | Loss Runs - QA               |
      | Department                            | PL - Personal Lines          |
      | Profit Center                         | CMC                          |
      | Number Of                             | 301                          |
      | # Downloaded                          | 401                          |
      | # of Loss Runs                        | 501                          |
      | # Ordered                             | 601                          |
      | Total # of Requests                   | 701                          |
      | # Contractor's Liability Phone Report | 801                          |
      | # of Contractor's GL Phone Report     | 901                          |
      | Comments                              | Please add your comment here |

    And I click "Completed WO Flag" checkbox
    Then I verify the "Date Completed" date is the expected value
    And I click the "Save" button
    And Reload the page
    Then The record is added
      | Branch Office                         |
      | Division                              |
      | Department                            |
      | Profit Center                         |
      | Number Of                             |
      | # Downloaded                          |
      | # of Loss Runs                        |
      | # Ordered                             |
      | Total # of Requests                   |
      | # Contractor's Liability Phone Report |
      | # of Contractor's GL Phone Report     |
      | Comments                              |
    And I verify the following checkboxes in the form
      | Completed WO Flag | checked |
    Then I verify the "Date Completed" date is the expected value
    And I verify the "Status" is changed to "Completed"

