Feature: Tests performing the actions on a WO
  @5151
  Scenario: Verifying the presence of Reject Button

##---------Create a Work order# request from initiator role and approve the same from approver role-------------###
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
    Then I Verify that request got approved successfully BY APPROVER
    And I Click on user icon
    And I click Logout button

    ###------------Purchase Officer - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    Then I verify that Reject button "is" displayed

    ###------------Add a quote from Purchase officer role which is >5K or < 5K-------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 900      |
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    Then I verify that "Quote Successfully Added" validation  message appears
    Then I verify that Reject button "is" displayed
    And I Click on user icon
    And I click Logout button

    ###------------Logged into Procurement manager role/approver's role and approve the raised quote-------------###
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    Then I verify that "Quote approved by Procurement Manager, need Manager approval" validation  message appears
    And I Click on user icon
    And I click Logout button

    ###------------logged into the Purchase officer role-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    ###------------Reject button should not present when quote got approved partially-------------###
    Then I verify that Reject button "is not" displayed
    And I Click on user icon
    And I click Logout button

   ###------------logged into the Approver role-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    Then I verify that "Quote Approved" validation  message appears
    And I Click on user icon
    And I click Logout button

    ###------------logged into the Purchase officer role-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    ###------------Reject button should not present when quote got approved fully-------------###
    Then I verify that Reject button "is not" displayed
    And I Click on user icon
    And I click Logout button

  @5179
  Scenario: Delete Attachments
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
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    Then I verify that delete button is enabled
    And I delete the attachments
    And I click the "confirm" button
    Then I verify that "Deleted Successfully" validation  message appears