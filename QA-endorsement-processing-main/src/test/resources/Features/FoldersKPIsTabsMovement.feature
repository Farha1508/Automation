@KPIFolderTabMove
Feature: KPI, Folder and Tab Movement

  Background: Login component
    Given I am on the login page
    ###---------- Login component ----------###
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Endorsement Processing" for company "Company 002"
    Then The page for the selected company and service will be displayed

  @10047
  Scenario: Verify the functionality of Incoming Requests KPI
    And I click on the "Incoming Responses" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    When I click the "OK" button
    Then I verify the number of "Open" records in KPI has "Increased"
    #Verify the KPI lists all expected records and the count is accurate

  @10049
  Scenario: Verify the functionality of Pending Responses KPI
    And I click on the "Pending Responses" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Pending Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    When I click the "OK" button
    Then I verify the number of "Open" records in KPI has "Increased"
    #Verify the KPI lists all expected records and the count is accurate

  @10050
  Scenario: Verify the functionality of Email Needed KPI
    And I click on the "Email Needed" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Email Needed" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    When I click the "OK" button
    Then I verify the number of "Open" records in KPI has "Increased"
    #Verify the KPI lists all expected records and the count is accurate

  @10048
  Scenario: Verify the functionality of the Quality Assurance KPI
    And I click on the "Quality Assurance" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Quality Assurance" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    When I click the "OK" button
    Then I verify the number of "Open" records in KPI has "Increased"
    #Verify the KPI lists all expected records and the count is accurate

  @10052
  Scenario: Verify the functionality of the Endorsement Processing KPI
    And I click on the "Endorsement Processing" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Endorsement Processing" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    When I click the "OK" button
    Then I verify the number of "Open" records in KPI has "Increased"
    #Verify the KPI lists all expected records and the count is accurate

  @10051
  Scenario: Verify the functionality of the SLA Missed KPI
    And I click on the "SLA Missed" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I set the date in the "Due Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    When I click the "Submit and Open" button
    And I select "Completed Endorsements" from the "Folder" drop down
    When I click the "Save & Close" button
    And I click on the "SLA Missed" KPI
    Then I verify the number of "Open" records in KPI has "Increased"
    #Verify the KPI lists all expected records and the count is accurate

  @9987
  Scenario: Verify movement to Open Work Orders tab
    And I click on the "Incoming Responses" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I find the Endorsement Processing work order in the grid
    And I Click on "Completed" tab
    And I verify the Endorsement Processing work order is not in the "Completed" grid
    And I Click on "Discarded" tab
    And I verify the Endorsement Processing work order is not in the "Discarded" grid
    And I Click on "Important Instructions" tab
    And I verify the Endorsement Processing work order is not in the "Important Instructions" grid
    #Verify the work orders move to the expected tabs

  @9988
  Scenario: Verify movement to Completed Work Orders tab
    And I click on the "Incoming Responses" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I select "Completed Endorsements" from the "Folder" drop down
    And I click the "Save & Close" button
    And I Click on "Completed" tab
    And I find the Endorsement Processing work order in the grid
    And I Click on "Open" tab
    And I verify the Endorsement Processing work order is not in the "Open" grid
    And I Click on "Discarded" tab
    And I verify the Endorsement Processing work order is not in the "Discarded" grid
    And I Click on "Important Instructions" tab
    And I verify the Endorsement Processing work order is not in the "Important Instructions" grid
    #Verify the work orders move to the expected tab

  @9989
  Scenario: Verify movement to Discarded Work Orders tab
    And I click on the "Incoming Responses" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Discarded Endorsements" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I Click on "Discarded" tab
    And I find the Endorsement Processing work order in the grid
    And I Click on "Open" tab
    And I verify the Endorsement Processing work order is not in the "Open" grid
    And I Click on "Completed" tab
    And I verify the Endorsement Processing work order is not in the "Completed" grid
    And I Click on "Important Instructions" tab
    And I verify the Endorsement Processing work order is not in the "Important Instructions" grid
    #Verify the work orders move to the expected tab

  @9990
  Scenario: Verify movement to Instructions Work Orders tab
    And I click on the "Incoming Responses" KPI
    And I get the current record count in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Important Instructions" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I Click on "Important Instructions" tab
    And I find the Endorsement Processing work order in the grid
    And I Click on "Open" tab
    And I verify the Endorsement Processing work order is not in the "Open" grid
    And I Click on "Completed" tab
    And I verify the Endorsement Processing work order is not in the "Completed" grid
    And I Click on "Discarded" tab
    And I verify the Endorsement Processing work order is not in the "Discarded" grid
    #Verify the work orders move to the expected tab