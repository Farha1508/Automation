Feature:Different Grids in Purchase Order

  Background: Sign In
    Given I log into the Purchase Orders app as an "Initiator"
    When I navigate to the "Requests" tab

  @1268
  Scenario: Grid - Paging Functionality
###------------- Testing pagination on Purchase Orders tab --------------###
    And I go to the "middle" page of the grid by entering the number in the pagination field
    And I get the current grid page number
    And I move to the next page in the grid
    And I move to the previous page in the grid
    And I change pages by entering a number


@1269
Scenario:Verify View dropdown Functionality in PO Grids
  When I select the "20" option from the the Viewing drop down
  Then The number of rows displayed will be less than or equal to the number selected
  When I select the "50" option from the the Viewing drop down
 Then The number of rows displayed will be less than or equal to the number selected
  When I select the "100" option from the the Viewing drop down
Then The number of rows displayed will be less than or equal to the number selected
  When I select the "10" option from the the Viewing drop down
  Then The number of rows displayed will be less than or equal to the number selected

  @1270
  Scenario: Verify Reset Functionality
    When I get the "WO #" for "row 4" of the grid
    And I enter that information into the grid header
    And I click the Reset button in the grid header
    Then The "WO #" grid header is ""

  @1272
  Scenario: Grid - WO Title column in PO Home Page Grid
##--------- C1299-Grid- Verify Enter text that will give unique result------##
    When I get the "WO Title" for "row 4" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

##---------C1300-Grid- Verify Enter text that will give multiple results-------##
    When I enter "18" into the "WO Title" grid header
    Then I get the number of records in the "Requests" tab

##---------C1301-Grid- Verify Enter text that will give zero(0) results------##
    When I enter "00000000dss" into the "WO Title" grid header
    Then There is no data in the grid

@1274
Scenario: Grid - Initiator column in PO Home Page Grid

  ##-----------C1303-Verify Grid- Drop Select unique Value-------##
  When I deselect the "POInitiator Dev" checkbox
  When I select "Jagan V" from the "Initiator" header in the grid
  And I check if "Jagan V" is selected in the "Initiator" header

##-----------C1302-Verify Grid -Select All Values selection from grid dropdown-------##
  And I select "multiselect-all" from the "Initiator" header in the grid
  Then I check if the following items are selected in the "Initiator" header
    |po initiator|POInitiator Dev|PO-Prod Initiator|Patra Vizag IT Team|Patra Raipur IT Team|
  And The number of records in the "Requests" tab is "the same"

  ##-----------C1304-Verify Grid -Multiple Values selection from grid dropdown-------##
  And I select "multiselect-all" from the "Initiator" header in the grid
    When I get the number of records in the "Requests" tab
    And I select "Patra Vizag IT Team" from the "Initiator" header in the grid
    Then I check if the following items are selected in the "Initiator" header
      | Patra Vizag IT Team |
    And The number of records in the "Requests" tab is "decreased"
  When I get the number of records in the "Requests" tab
    And I select "po initiator" from the "Initiator" header in the grid
    And I select "Patra Raipur IT Team" from the "Initiator" header in the grid
    Then I check if the following items are selected in the "Initiator" header
      | Patra Vizag IT Team | po initiator| Patra Raipur IT Team |
    And The number of records in the "Requests" tab is "increased"


@1275
Scenario: Grid - Initiator column Search field
  And I click the "1 selected" button
And I enter "Jagan V" into the search field
And I click the cross button
Then Verify that text is removed from the field
And Verify that items in the dropdown list are displayed
 And I enter "PO" into the search field
  Then Verify that items in the dropdown list are displayed

@1276
Scenario: Grid - Status column in PO Home Page Grid

 ##-----------C1303-Verify Grid- Drop Select unique Value-------##
  When I select "Pending Quotes" from the "Status" header in the grid
  And I check if "Pending Quotes" is selected in the "Status" header

##-----------C1302-Verify Grid -Select All Values selection from grid dropdown-------##
  And I select "multiselect-all" from the "Status" header in the grid
  Then I check if the following items are selected in the "Status" header
|Pending Quotes|Pending Purchase Order|Pending Request Approval|Purchase Order Closed|Purchase Order Raised|Quote Pending Approver|Quote Pending Approver|Quote Pending Approver|Work Order Closed|Work Order Raised|
  And The number of records in the "Requests" tab is "the same"

  ##-----------C1304-Verify Grid -Multiple Values selection from grid dropdown-------##
  And I select "multiselect-all" from the "Status" header in the grid
  When I get the number of records in the "Requests" tab
  And I select "Purchase Order Raised" from the "Status" header in the grid
  Then I check if the following items are selected in the "Status" header
    | Purchase Order Raised |
  And The number of records in the "Requests" tab is "decreased"
  When I get the number of records in the "Requests" tab
  And I select "Purchase Order Closed" from the "Status" header in the grid
  And I select "Work Order Closed" from the "Status" header in the grid
  Then I check if the following items are selected in the "Status" header
    | Purchase Order Raised | Purchase Order Closed| Work Order Closed|
  And The number of records in the "Requests" tab is "increased"
 And I get the number of records in the "Requests" tab

@1273
Scenario: Grid - Status column Search field
  And I click the "select. . ." button
 And I enter "Pending Request Approval" into the search field for "status" column field
  And I click the cross icon
  Then Verify that text is removed from the field
  And Verify that items in the Status dropdown list are displayed
  And I enter "Pending Request Approval" into the search field for "status" column field
  And Verify that items in the Status dropdown list are displayed
  And I select the "Pending Request Approval" checkbox
  And I clear the "Search" field for "Status" column field at index 1
  And I enter "Pending" into the search field for "status" column field
  And Verify that items in the Status dropdown list are displayed

@1278
Scenario: Grid - Request Date column in PO Home Page Grid

##--------- C1299-Grid- Verify Enter text that will give unique result------##
  And I get the "Date" for "row 1" of the grid
  When I set that date in the header
  Then I get the number of records in the "Requests" tab

##---------C1300-Grid- Verify Enter text that will give multiple results-------##
And I click the Reset button in the grid header
 And I get the "Date" for "row 4" of the grid
When I set that date in the header
Then I get the number of records in the "Requests" tab

##---------C1301-Grid- Verify Enter text that will give zero(0) results------##
  And I enter "abc" into the "Date" grid header
  Then There is no data in the grid

@1279
Scenario: Grid - Location column in PO Home Page Grid
##-----------C1303-Verify Grid- Drop Select unique Value-------##
  When I select "Bangalore" from the "Location" header in the grid
  And I check if "Bangalore" is selected in the "Location" header

##-----------C1302-Verify Grid -Select All Values selection from grid dropdown-------##
  And I select "multiselect-all" from the "Location" header in the grid
  Then I check if the following items are selected in the "Location" header
    |Bangalore|Bhilai|Coimbatore|Hyderabad|Raipur|Vizag TH|Vizag VP|
  And The number of records in the "Requests" tab is "the same"

  ##-----------C1304-Verify Grid -Multiple Values selection from grid dropdown-------##
  And I click the Reset button in the grid header
  When I get the number of records in the "Requests" tab
  And I select "Coimbatore" from the "Location" header in the grid
  Then I check if the following items are selected in the "Location" header
    | Coimbatore|
  And The number of records in the "Requests" tab is "decreased"
  When I get the number of records in the "Requests" tab
  And I select "Bhilai" from the "Location" header in the grid
  And I select "Hyderabad" from the "Location" header in the grid
  Then I check if the following items are selected in the "Location" header
    | Coimbatore | Bhilai| Hyderabad|
  And The number of records in the "Requests" tab is "increased"
  And I get the number of records in the "Requests" tab

  @1280
  Scenario: Grid - Location column Search field
    And I click the select. . . button for "Location" field
    And I enter "Raipur" in the "Search" field for Location column field at index 2
    And I click the cross icon button
    Then Verify that text is removed from the "Search" field for Location column field at index 2
    And Verify that items in the Location dropdown list are displayed
    And I enter "Vizag TH" in the "Search" field for Location column field at index 2
    And Verify that items in the Location dropdown list are displayed
    And I select the "Vizag TH" checkbox
    And I clear the "Search" field for "Location" column field at index 2
    And I enter "Vizag" in the "Search" field for Location column field at index 2
    And Verify that items in the Location dropdown list are displayed

@1281
Scenario: Grid - Department column in PO Home Page Grid

##-----------C1303-Verify Grid- Drop Select unique Value-------##
  When I select "Finance" from the "Department" header in the grid
  And I check if "Finance" is selected in the "Department" header

##-----------C1302-Verify Grid -Select All Values selection from grid dropdown-------##
  And I select "multiselect-all" from the "Department" header in the grid
  Then I check if the following items are selected in the "Department" header
    |IT|HR|Legal|Admin|Facility|Finance|
  And The number of records in the "Requests" tab is "the same"

  ##-----------C1304-Verify Grid -Multiple Values selection from grid dropdown-------##
  And I click the Reset button in the grid header
  When I get the number of records in the "Requests" tab
  And I select "Facility" from the "Department" header in the grid
  Then I check if the following items are selected in the "Department" header
    | Facility|
  And The number of records in the "Requests" tab is "decreased"
  When I get the number of records in the "Requests" tab
  And I select "Legal" from the "Department" header in the grid
  And I select "Admin" from the "Department" header in the grid
  Then I check if the following items are selected in the "Department" header
    |Facility| Legal| Admin|
  And The number of records in the "Requests" tab is "increased"
  And I get the number of records in the "Requests" tab


  @1282
  Scenario: Grid - Department column Search field
    And I click the select. . . button for "Department" field
    And I enter "HR" in the "Search" field for Location column field at index 3
    And I click the cross
    Then Verify that text is removed from the "Search" field for Location column field at index 3
    And Verify that items in the Department dropdown list are displayed
    And I enter "Admin" in the "Search" field for Location column field at index 3
    And Verify that items in the Department dropdown list are displayed
    And I select the "Admin" checkbox
    And I clear the "Search" field for "Department" column field at index 3
    And I enter "F" in the "Search" field for Location column field at index 3
    And Verify that items in the Department dropdown list are displayed


