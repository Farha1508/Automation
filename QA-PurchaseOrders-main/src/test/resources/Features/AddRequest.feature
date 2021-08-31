Feature: Tests involving the creation of a request

  @1148
  Scenario: Add Request Happy Path
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bangalore            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    Then The new request will be displayed in the grid

  @1496
    Scenario: Add Request Cancel
      Given I log into the Purchase Orders app as an "Initiator"
      And I click the "Add Request" button
      And I enter the following information into the form
        | WO Title   | Test <current date> |
        | Location   | Vizag TH            |
        | Department | IT                  |
      And I click the "Cancel Changes" button
    And I click the "confirm" button
    Then Verify the changes has been erased

  @4879
  Scenario: Add Request with Location Vizag
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
     | keyboard   | Apple       | 900      |
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And I click the "Add Purchase Order" button
    And I enter "GIP" in the "Vendor Address" text box
    And I click the edit action
    And I enter "10" in the Part No field
    And I click the save action
   And I raise the purchase order
 And I close the purchase order
    And I click the "Print PDF" button
   And Verify the GST "37AAECP2604C1ZW" under the Address Section for "Vizag location"

  @4880
  Scenario: Add Request with Location Raipur
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Raipur            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 900      |
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And I click the "Add Purchase Order" button
    And I enter "GIP" in the "Vendor Address" text box
    And I click the edit action
    And I enter "10" in the Part No field
    And I click the save action
   And  I raise the purchase order
  And I close the purchase order
    And I click the "Print PDF" button
    And Verify the GST "22AAECP2604C1Z7" under the Address Section for "Raipur location"

  @4881
  Scenario: Add Request with Location Bangalore
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bangalore            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 900      |
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And I click the "Add Purchase Order" button
    And I enter "GIP" in the "Vendor Address" text box
    And I click the edit action
    And I enter "10" in the Part No field
    And I click the save action
    And I raise the purchase order
    And I close the purchase order
    And I click the "Print PDF" button
    And Verify the GST "29AAECP2604C6Z0" under the Address Section for "Bangalore location"