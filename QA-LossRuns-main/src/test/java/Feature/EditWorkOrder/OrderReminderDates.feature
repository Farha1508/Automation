Feature: Test Order/Reminder Dates page

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

  @9896
  Scenario: Validate Not updating the mandatory fields
    When I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    Then I Click on "Order/Reminder Dates" tab
    Then Click on Add Order button
    And I click the "Save" button
    Then I verify the submit error "Your changes cannot be saved at this time. Some Required Fields are empty" appears

  @9889
  Scenario:Verify Delete Order functionality for Order/Reminder Dates tab
    And I sort the "Work Order #" grid column by "Descending"
    And I enter the "Work Order #" value of "row 1" into value store as "WO #"
    And I open the Work Order
    When I navigate to the "Order/Reminder Dates" tab
    Then Click on Add Order button
    Then Select "Associated Indemnity Corp." as Carrier Name
    And I set the date in the "Reminder" date picker and set Next Reminder date to "Today" Date
    And I click the "Save" button
    Then Reload the page
    Then Click on delete icon
    And I click the "Save" button

  @9890
  Scenario:Verify Next Reminder
    When I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click the Reset button in the grid header
    When I search the stored work order number
    And I click on the top work order link
    When I navigate to the "Order/Reminder Dates" tab
    Then Click on Add Order button
    Then Select "Associated Indemnity Corp." as Carrier Name
    And I set the date in the "Order Date" date picker and set Next Reminder date to "Past" Date
    And I set the date in the "Reminder" date picker and set Next Reminder date to "Today" Date
    And I click the "Save" button
    Then Reload the page
    Then I verify that the "Next Reminder Needed" is set to the expected day based on the "Reminder" date
    Then I click on "Done" checkbox
    And I click the "Save" button
    Then Reload the page
    Then I verify that the Next Reminder Needed is empty

