Feature:  Loss Runs all buttons & Checkboxes functionality

  Background: Login to Node and Navigating to PC home page
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"
    When I enter the email and password for the "Super User"
    And Now clicked on Sign In button
    Then I click on the "Work Order Tracking" tile
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed


  @9848
  Scenario: Verify White Color Functionality
    When I Click on Add Work Order button
    And Click on "Submit" button
    And I get the new work order number from the confirmation modal
    And I navigate to the "Open" tab
    When I search the stored work order number
    Then I Verified the Work order back ground "White" color

  @9849
  Scenario: Verify Yellow Color Functionality
    And I navigate to the "Open" tab
    And I search the stored work order number
    And I click on the top work order link
    And I click on "Mark Rush" button
    Then Click on "Save & Close" button
    And I search the stored work order number
    Then I Verified the Work order back ground "Rush - Yellow" color


