Feature: Quote Approval Process for all Roles

Background:  ##-------------Common steps in each scenarios --------------##
##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
        ###-----------Initiator Adding Requests and Items---------###
    When I click the "Add Request" button
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
    ###-----------Verify the status once Work Order created---------###
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

@1775
Scenario: Verify the functionality of APPROVE process through grid.
 ###------------Approver - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to approve the work order---------------###
    And I navigate to the "Pending Approval" tab
    And I "Approve" the request from the grid
    And I click the "confirm" button

###-----------Verify the status from request grid---------###
    And I verify the status is "Pending Quotes" from Requests tab
    And I Click on user icon
    And I click Logout button

##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
    ###-----------Verify the status from Requests tab---------###
    And I verify the status is "Pending Quotes" from Requests tab


  @1778
  Scenario: Verify the functionality of SEND BACK process through grid.
 ###------------Approver - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to approve the work order---------------###
    And I navigate to the "Pending Approval" tab
    And I "Send back" the request from the grid
    And I click the "confirm" button

    ###-----------Verify the status after sending back WO---------###
    And I verify the status is "Work Order Raised" from Requests tab
    And I Click on user icon
    And I click Logout button

      ##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
    ###-----------Verify the status from  request page---------###
    And I verify the status is "Work Order Raised" from Requests tab

  @1779
  Scenario: Verify the functionality of REJECT process through grid
 ###------------Approver - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to approve the work order---------------###
    And I navigate to the "Pending Approval" tab
    And I "Reject" the request from the grid
    And I click the "confirm" button

 ###-----------Verify the status after Rejecting WO---------###
    And I verify the status is "Rejected" from Requests tab
    And I Click on user icon
    And I click Logout button

##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
###-----------Verify the status from request page---------###
    And I verify the status is "Rejected" from Requests tab

@1780
Scenario: Verify the functionality of APPROVE process through detailed request page.
 ###------------Approver - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to approve the work order---------------###
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    Then I Verify that request got approved successfully BY APPROVER
    ###-----------Verify the status from detailed request page---------###
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

##-------------Initiator - Login Functionality --------------##
   Given I log into the Purchase Orders app as an "Initiator"
    ###-----------Verify the status from detailed Requests tab---------###
    And I open the created WO
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"

@1782
  Scenario: Verify the functionality of REJECT process through detailed request page
 ###------------Approver - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to approve the work order---------------###
  And I navigate to the "Pending Approval" tab
  And I open the created WO
  And I click the "Reject" button
  And I click the "confirm" button
  Then I verify that "Requests Rejected Successfully" validation  message appears

 ###-----------Verify the status after Rejecting---------###
  Then I verify the selected status is "Rejected" for the dropdown "Status"
  And I Click on user icon
  And I click Logout button

##-------------Initiator - Login Functionality --------------##
  Given I log into the Purchase Orders app as an "Initiator"
###-----------Verify the status from detailed request page---------###
  And I open the created WO
  Then I verify the selected status is "Rejected" for the dropdown "Status"

@1883
Scenario: Verify the Quotes added with Total Price <5k and APPROVE it
 ##---------Create a Work order# request from initiator role and approve the same from approver role-------------###
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
###------------Click on Add Quote by Initiator-------------###
  And Click on Add Quote by Initiator Button and click yes
  Then I verify that "Add Quote Permission given to Initiator" validation  message appears
  And I Click on user icon
  And I click Logout button

###------------Initiator - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Initiator"
  And I open the created WO
###------------Add a quote from Initiator role which is <5K-------------###
  And Click on Add Quote by Initiator Button
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | keyboard   | Apple       | 4000     |
  And I click the "Total Price" box
  And I click the "Submit" button
  And I click the "confirm" button
  And I Click on user icon
  And I click Logout button

###------------Approver role - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
  And I open the created WO
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Pending for Approval"
  And I click the eye icon
  And I click the "Approve" button
  And I click the "confirm" button
  Then I verify that "Quote Approved" validation  message appears
  Then I check if "Approval Status" is "Approved"

@1884
 Scenario: Verify the Quotes added with Total Price <5k and SEND BACK by Approver.
   ##---------Create a Work order# request from initiator role and send back the same from approver role-------------###
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
###------------Click on Add Quote by Initiator-------------###
  And Click on Add Quote by Initiator Button and click yes
  Then I verify that "Add Quote Permission given to Initiator" validation  message appears
  And I Click on user icon
  And I click Logout button

###------------Initiator - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Initiator"
  And I open the created WO
###------------Add a quote from Initiator role which is <5K-------------###
  And Click on Add Quote by Initiator Button
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | keyboard   | Apple       | 4000     |
  And I click the "Total Price" box
  And I click the "Submit" button
  And I click the "confirm" button
  And I Click on user icon
  And I click Logout button

###------------Approver role - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
  And I open the created WO
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Pending for Approval"
  And I click the eye icon
  And I click the "Send back" button
  And I click the "confirm" button
  Then I verify that "Quote Sent Back to the Purchase officer" validation  message appears
  Then I verify that buttons are disabled
  Then I check if "Approval Status" is "Quote Sent Back by Approver"
  And I verify the selected status is "Pending Quotes" for the dropdown "Status"

@1885
Scenario: Verify the Quotes added with Total Price <5k and REJECT it by Approver.
   ##---------Create a Work order# request from initiator role and Reject the same from approver role-------------###
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
###------------Click on Add Quote by Initiator-------------###
And Click on Add Quote by Initiator Button and click yes
  Then I verify that "Add Quote Permission given to Initiator" validation  message appears
  And I Click on user icon
  And I click Logout button

###------------Initiator - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Initiator"
  And I open the created WO
###------------Add a quote from Initiator role which is <5K-------------###
  And Click on Add Quote by Initiator Button
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | keyboard   | Apple       | 4000     |
  And I click the "Total Price" box
  And I click the "Submit" button
  And I click the "confirm" button
  And I Click on user icon
  And I click Logout button

###------------Approver role - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
  And I open the created WO
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Pending for Approval"
  And I click the eye icon
  And I click the "Reject" button
  And I click the "confirm" button
  Then I verify that "Quote Rejected" validation  message appears
  Then I verify that buttons are disabled
  Then I check if "Approval Status" is "Rejected"
  And I verify the selected status is "Pending Quotes" for the dropdown "Status"

  @1888
  Scenario: Quotes By Purchase Officer
##---------Verify the Quotes added with Total Price >5k and APPROVE by Proc Mgr and SEND BACK by Approver and SEND BACK by Proc Mgr----###
##---------Create a Work order# request from initiator role and approve the same from approver role-------------###
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
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    ###------------Add a quote from Purchase officer role which is >5K-------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 6000     |
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    Then I verify that "Quote Successfully Added" validation  message appears
    And I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

###------------Procurement Manager role - Login Functionality-------------###
Given I log into the Purchase Orders app as an "Procurementmanager"
And I open the created WO
Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
And I click on the "Quotes" link
Then I check if "Approval Status" is "Pending for Approval"
And I click the eye icon
And I click the "Approve" button
And I click the "confirm" button
Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
Then I check if "Approval Status" is "Partially Approved"

###------------Approver role - Login Functionality-------------###
Given I log into the Purchase Orders app as an "Approver"
And I open the created WO
And I click on the "Quotes" link
Then I check if "Approval Status" is "Partially Approved"
And I click the eye icon
And I click the "Send back" button
And I click the "confirm" button
Then I verify that "Quote Sent Back By Manager to Procurement Manager" validation  message appears
And I verify that buttons are disabled
Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
Then I check if "Approval Status" is "Not Approved"

###------------Procurement Manager role - Login Functionality-------------###
Given I log into the Purchase Orders app as an "Procurementmanager"
And I open the created WO
And I click on the "Quotes" link
Then I check if "Approval Status" is "Quote Sent Back by Approver"
And I click the eye icon
And I click the "Send back" button
And I click the "confirm" button
Then I verify that "Quote Sent Back by Procurement Manager to Purchase Officer" validation  message appears
Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
Then I check if "Approval Status" is "Quote Sent Back by Procurement Manager"


@1889
Scenario: Verify the Quotes added with Total Price >5k and APPROVE by Proc Mgr and REJECT by Approver.
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
  Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    ###------------Add a quote from Purchase officer role which is >5K-------------###
  And Click on Add Quote by Purchase Officer Button
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | keyboard   | Apple       | 6000     |
  And I click the "Total Price" box
  And I click the "Submit" button
  And I click the "confirm" button
  Then I verify that "Quote Successfully Added" validation  message appears
  Then I verify that Reject button "is" displayed
  And I Click on user icon
  And I click Logout button

###------------Procurement Manager role - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Procurementmanager"
  And I open the created WO
  Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Pending for Approval"
  And I click the eye icon
  And I click the "Approve" button
  And I click the "confirm" button
  Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
  Then I check if "Approval Status" is "Partially Approved"

###------------Approver role - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
  And I open the created WO
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Partially Approved"
  And I click the eye icon
  And I click the "Reject" button
  And I click the "confirm" button
  Then I verify that "Quote Rejected" validation  message appears
  And I verify that buttons are disabled
  Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
  Then I check if "Approval Status" is "Rejected"

@1891
Scenario: Verify that multiple quotes are added and approved.
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
  Then I verify the selected status is "Pending Quotes" for the dropdown "Status"

 ###------------Add multiple quotes from Purchase officer role which is >5K-------------###
  And Click on Add Quote by Purchase Officer Button
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | keyboard   | Apple       | 6000     |
  And I click the "Total Price" box
  And I click the "Submit" button
  And I click the "confirm" button
  Then I verify that "Quote Successfully Added" validation  message appears
  And Click on Add Quote by Purchase Officer Button
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | Mouse   | Test1       | 7000     |
  And I click the "Total Price" box
 And I click the "Submit" button
  And I click the "confirm" button
  Then I verify that "Quote Successfully Added" validation  message appears
  And I Click on user icon
  And I click Logout button

  ###------------Procurement Manager role - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Procurementmanager"
  And I open the created WO
  Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Pending for Approval"
  And I click the eye icon
  And I click the "Approve" button
  And I click the "confirm" button
  Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
  Then I check if "Approval Status" is "Partially Approved"

  ##---------Verify that remaining Quotes "Approval Status" changed to "Not approved"--------##
  Then I check if "Approval Status" is "Not Approved"
  And I click the eye icon in the second row
  And I verify that buttons are disabled
  And I click the "Close" button
  And I click the "Add Comment" button
  And I enter "Title" in the "Title" field
  And I enter "Description" in the "Description" text box
  And I click "Submit" button
  Then I verify that "Comment Successfully Added" validation  message appears
  And I Click on user icon
  And I click Logout button

###------------Approver role - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
  And I open the created WO
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Partially Approved"
  And I click the eye icon
  And I click the "Approve" button
  And I click the "confirm" button
  Then I verify that "Quote Approved" validation  message appears
  Then I verify the selected status is "Pending Purchase Order" for the dropdown "Status"
  Then I check if "Approval Status" is changed to "Approved"







    
