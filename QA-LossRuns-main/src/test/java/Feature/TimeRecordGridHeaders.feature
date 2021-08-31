Feature: Manipulation of different grid headers on Time Record pages

  Background: Login
    ###---------- Login component ----------###
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"
    When I enter the email and password for the "Super User"
    And Now clicked on Sign In button
    Then I click on the "Work Order Tracking" tile
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed

  @11334
  Scenario: Verify Time Records, Time Rec Admin, Time Record Open & Reports  With Team Lead and above roles
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    And Verify the following headers are present on page
      | Company | Service | Task Description | WO # | Employee | Start Time | End Time | Duration (Hours) | Billable |
    When I click on "Time Record Open" Tab
    Then I should be redirected to "Time Record Open" page
    And Verify the following headers are present on page
      | Edit | Company | Service | Task Description | WO # | Employee | Start Time | End Time | Duration (Hours) | Billable | Team Lead | Sr Team Lead | Manager |
    When I click on "Time Record Admin" Tab
    Then I should be redirected to "Time Record Admin" page
    Then I click on Run button
    And I wait for "10" seconds
    And Verify the following headers are present on page
      | Company | Service | Task Description | WO# | Employee | Start Time | End Time | Duration (Hours) | Billable | WO Required | Team Lead | Sr. Team Lead | Manager | Location |
    When I click on "Reports" Tab
    Then Now switch to new tab opened
    Then I should be redirected to Reports page

  @11320
  Scenario: Verify Company Dropdown functionality
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    When I select "Company 002" from the "Company" header in the grid
    And I wait for "5" seconds
    Then The information in the first row of the grid will match what was entered

  @11321
  Scenario: Verify Service Dropdown functionality
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    When I sort the "Service" grid column by "descending"
    And I get the "Service" for "row 2" row of the grid
    Then The information in the first row of the grid will match what was entered

  @11322
  Scenario: Verify Task Description dropdown functionality
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    When I sort the "Task Description" grid column by "descending"
    And I get the "Task Description" for "row 2" row of the grid
    Then The information in the first row of the grid will match what was entered

  @11317
  Scenario: Verify WO # Column Search Field
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    Then I double click on "WO #" column
    And I wait for "5" seconds
    And I get the "WO #" for "row 2" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @11318
  Scenario: Verify Employee Column Search Field
    When I click on "Time Record Open" Tab
    Then I should be redirected to "Time Record Open" page
    And I get the "Employee" for "row 2" row of the grid
    And I wait for "3" seconds
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @11328
  Scenario: Verify Billable Column Search Field
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    When I select "Yes" from the "Billable" header in the grid
    Then The information in the first row of the grid will match what was entered

  @11329
  Scenario: Verify WO Required Column Search Field
    When I click on "Time Record Admin" Tab
    Then I should be redirected to "Time Record Admin" page
    Then I click on Run button
    And I wait for "10" seconds
    When I select "No" from the "WO Required" header in the grid
    Then The information in the first row of the grid will match what was entered

  @11323
  Scenario: Verify Manager Column Search Field
    When I click on "Time Record Open" Tab
    Then I should be redirected to "Time Record Open" page
    When I select "Shashi T" from the "Manager" header in the grid
    Then The information in the first row of the grid will match what was entered

  @11326
  Scenario: Verify Sr Team Lead Column Search Field
    When I click on "Time Record Open" Tab
    Then I should be redirected to "Time Record Open" page
    When I select "Lavanya V" from the "Sr Team Lead " header in the grid
    Then The information in the first row of the grid will match what was entered

  @11325
  Scenario: Verify Team Lead Column Search Field
    When I click on "Time Record Open" Tab
    Then I should be redirected to "Time Record Open" page
    When I select "Abhishek Sharma" from the "Team Lead" header in the grid
    Then The information in the first row of the grid will match what was entered

  @11327
  Scenario: Verify Start Time Column Date Search Field
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    When I get the "Start Time" for "row 2" row of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

  @11319
  Scenario: Verify End Time Column Date Search Field
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    When I get the "End Time" for "row 2" row of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

  @11324
  Scenario: Verify Duration filter
    When I click on "Time Records" Tab
    Then I should be redirected to "Time Record" page
    When I get the "Duration (Hours)" for "row 2" row of the grid
    Then I set that duration range in the header
    Then The information in the first row of the grid will match what was entered

