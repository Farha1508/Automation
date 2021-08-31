Feature: Rush Order Functionality

  Background: Login component
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"
    ###---------- Login component ----------###
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- Application Select ----------###
    When I click on the "Setup" tile
    Then I will be taken to the homepage for that app
    When I click on the "Company" link
    And I click on the services button for "Company 002"
    And I navigate to the "Due Date Override" Admin tab
    #

  #There is some issue with Rush DDO setup in admin, its not updating ,refer to bug #858 , so do not change Rush DDO until unless its fixed
  @9872
  Scenario: Verify Mark Rush functionality - Business days checked
    And I select "Loss Run" from the Transaction Type filter
    When I edit the "Loss Run" row
      | DDO           | 5    |
      | Rush DDO      | 2    |
      | Business Days | True |
    And I return to the Apps homepage
    When I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app

    #TEST

    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    When I Click on Add Work Order button
    And Click on "Submit and Open" button
    Then I verify that the Due Date is set correctly
      | Due Date      | 5     |
      | Business Days | True  |
      | Rush          | False |
    And I click the "Mark Rush" button
    Then I verify that the Due Date is set correctly
      | Due Date      | 2    |
      | Business Days | True |
      | Rush          | True |
    # Add Rush Order

  @9873
  Scenario: Verify Remove Rush functionality - Business days unchecked
    And I select "Loss Run" from the Transaction Type filter
    When I edit the "Loss Run" row
      | DDO           | 5     |
      | Rush DDO      | 2     |
      | Business Days | False |
    And I return to the Apps homepage
    When I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app

    #TEST

    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    When I Click on Add Work Order button
    And Click on "Submit and Open" button
    Then I verify that the Due Date is set correctly
      | Due Date      | 5     |
      | Business Days | False |
      | Rush          | False |
    And I click the "Mark Rush" button
    Then I verify that the Due Date is set correctly
      | Due Date      | 2     |
      | Business Days | False |
      | Rush          | True  |
    And I click the "Remove Rush" button
    Then I verify that the Due Date is set correctly
      | Due Date      | 5     |
      | Business Days | False |
      | Rush          | False |
    # Remove Rush Order
