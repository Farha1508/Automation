@Test
Feature: Automating Grid

  Background: Login to Node application
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    When I click on the "Work Order Tracking" tile
    When I open "Document Retrieval" for company "Company 019"
    When I navigate to the "Incoming" tab

  @7470
  Scenario: Verify Reset Functionality
    When I enter "12345" into the "Work Order #" grid header
    Then The "Work Order #" grid header is "12345"
    And I click the Doc retrieval Reset button
    Then The "Work Order #" grid header is ""

  @7471
  Scenario: Verify Work Order # column in "Doc-Retrieval" home page grid
    When I sort the "Work Order #" grid column by "descending"
    When I get the "Work Order #" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    And I click the Doc retrieval Reset button
    Then I enter "3" into the "Work Order #" grid header
    Then The information in the first row of the grid will match what was entered
    And I click the Doc retrieval Reset button
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I sort the "Work Order #" grid column by "descending"
    When I get the "Work Order #" for "row 3" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered


  @7472
  Scenario: Grid - WO Type column
    When I select "Importing into DocStar" from the "Work Order Type" header in the grid
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I select "Carrier Alerts" from the "Work Order Type" header in the grid
    Then The information in the first row of the grid will match what was entered


  #@7473
  #Scenario: Grid - WO Type column search field
    #When I click on "Work Order Type" column dropdown
    #Then I get the "" field to enter dropdown values
    #Then I enter "Accounting" into the "Search" grid header
    #And I wait for "2" seconds
    #Then I click on "ColumnSearchClose" button

  @7474
  Scenario: Grid - Status column
    When I select "Available" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I select "Summary QA Pending" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered



  #@7475
  #Scenario: Grid - "Status" column search field.
    #When I click on "Status" column dropdown
    #Then I enter "Available" into the "Search" grid header
    #And I wait for "2" seconds
    #Then I click on "ColumnSearchClose" button



  @7477
  Scenario: Grid - "Assigned To" column
    When I select "ankur khandelwal" from the "Assigned To" header in the grid
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I select "ankur khandelwal" from the "Assigned To" header in the grid
    Then The information in the first row of the grid will match what was entered


  #@7478
  #Scenario: Grid - "Assigned To" column search field
    #When I click on "Assigned To" column dropdown
    #Then I enter "ankur khandelwal" into the "Search" grid header
    #And I wait for "2" seconds
    #Then I click on "ColumnSearchClose" button


  @7479
  Scenario: Grid - "Summary" column
    When I sort the "Summary" grid column by "descending"
    When I get the "Summary" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I sort the "Summary" grid column by "descending"
    When I get the "Summary" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered


  @7481
  Scenario: Grid - Requestor column
    When I sort the "Requestor" grid column by "descending"
    When I get the "Requestor" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I sort the "Requestor" grid column by "descending"
    When I get the "Requestor" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered



  @7483
  Scenario: Grid - "Date Received" column
    When I sort the "Date Received" grid column by "descending"
    When I get the "Date Received" for "row 5" row of the grid
    When I set that date in the header
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I sort the "Date Received" grid column by "descending"
    When I get the "Date Received" for "row 4" row of the grid
    When I set that date in the header
    Then The information in the first row of the grid will match what was entered



  @7484
  Scenario: Grid - "Date Completed" column
    When I sort the "Date Completed" grid column by "descending"
    When I get the "Date Completed" for "row 2" row of the grid
    When I set that date in the header
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I sort the "Date Completed" grid column by "ascending"
    When I get the "Date Completed" for "row 4" row of the grid
    When I set that date in the header
    Then The information in the first row of the grid will match what was entered



  @7485
  Scenario: Grid - "# of Docs Downloaded" column
    When I sort the "# of Docs Downloaded" grid column by "descending"
    When I get the "# of Docs Downloaded" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I sort the "# of Docs Downloaded" grid column by "descending"
    When I get the "# of Docs Downloaded" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered



  @7489
  Scenario: Grid - "Number of" column
    When I sort the "Number Of" grid column by "descending"
    When I get the " Number Of" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    #Navigate to Discarded tab
    When I navigate to the "Discarded" tab
    When I sort the "Number Of" grid column by "descending"
    When I get the " Number Of" for "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered



  @7933
  Scenario: Grid - Important Instructions
    When I navigate to the "Important Instructions" tab
    Then Verify the following headers are present
      |Work Order #|
      |Work Order Type|
      |Status         |
      |Assigned To    |
      |Summary        |
      |Requestor      |
      |Date Completed |
      |# of Docs Downloaded|
      |Number Of           |


  @7948
  Scenario: Grid - Discarded
    When I navigate to the "Discarded" tab
    Then Verify the following headers are present
      |Work Order #|
      |Work Order Type|
      |Status|
      |Assigned To|
      |Summary|
      |Requestor|
      |Date Received|
      |Date Completed|
      |# of Docs Downloaded|
      |Number Of|
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected


