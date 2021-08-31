Feature: Purchase Order End to End Flow

Background: Purchase order application complete functionality
  ##------------Initiator - Login Functionality-------------###
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

###------------Approver - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
  And I navigate to the "Pending Approval" tab
###---------------Approves the work order by Approver---------------###
  And I open the created WO
  And I approve the request
###-----------Verify the status after approving WO---------###
  Then I Verify that request got approved successfully BY APPROVER
  And I Click on user icon
  And I click Logout button

###------------Purchase Officer - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Purchaseofficer"
    ###----------Navigates to Request Tab------------------###
  And I open the created WO
  Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    ###-----------------Add Quote Details for the created Work Order---------------###
  And Click on Add Quote by Purchase Officer Button

  @1872
  Scenario: Verify PO is raised for >5k Quotes
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | keyboard   | Apple       | 6000     |
  And I click the "Total Price" box
    ###----------------Submit the quote 1--------------------####
  And I click the "Submit" button
  And I click the "confirm" button
  Then I verify that "Quote Successfully Added" validation  message appears
  And Click on Add Quote by Purchase Officer Button
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | Mouse   | Test1       | 7000     |
  And I click the "Total Price" box
     ###----------------Submit the quote 2--------------------####
  And I click the "Submit" and "confirm" button
  Then I verify that "Quote Successfully Added" validation  message appears
    ###----------------Verify the quote status--------------------####
  Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
  And Verify the approval status on Quotes Tab for 1st Quote
  And Verify the approval status on Quotes Tab for 2nd Quote
  And I Click on user icon
  And I click Logout button

###------------Procurement Manager - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Procurementmanager"

###----------------------Get the Work Order ID-----------------------###
  And I open the created WO
  Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Pending for Approval"
  And I click the eye icon

###------------Approve any one of the quotes by Proc Mgr-------------###
  And I click the "Approve" button
  And I click the "confirm" button
    ###----------------Verify the quote status--------------------####
  Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
  And Verify the approval status on Quotes Tab for 1st Quote
  And Verify the approval status on Quotes Tab for 2nd Quote
  And I Click on user icon
  And I click Logout button

###------------Approver - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
    ###---------------Get the Work order number and search in grid--------------###
  And I open the created WO
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Partially Approved"
  And I click the eye icon
###---------------Approves the same quote by Approver---------------###
  And I click the "Approve" button
  And I click the "confirm" button
  Then I verify that "Quote Approved" validation  message appears
  Then I verify the selected status is "Pending Purchase Order" for the dropdown "Status"
  Then I check if "Approval Status" is changed to "Approved"
  And I Click on user icon
  And I click Logout button

###------------Purchase Officer - To raise purchase order Request-------------###
  Given I log into the Purchase Orders app as an "Purchaseofficer"

###----------Navigates to Request Tab and search the raised Work order------------------###
  And I open the created WO

###---------------------------To Add PO, enter all the required details-------------------------------------###
  And I click the "Add Purchase Order" button
  Then Enter Purchase Order confirmation Details
    | VendorReferenceNumber | VendorAddress              | PartNo |
    | PATRA-1631            | Visakapatnam,AndhraPradesh | 1A     |

 ###---------------------------Raise Purchase Order Request---------------------###
  And I raise the purchase order
  And I verify the selected status is "Purchase Order Raised" for the dropdown "Status"
  And I get the created Purchase Order Number

###---------------------------Closing the raised Purchase Order---------------------###
  And I close the purchase order
Then I verify that "Purchase Order Closed Successfully" validation  message appears
    And I verify the selected status is "Purchase Order Closed" for the dropdown "Status"
  And I Click on user icon
  And I click Logout button

##------------Initiator - To Close Work Order------------###
  Given I log into the Purchase Orders app as an "Initiator"

###-------------------Search the Raised Work order request in grid--------------------###
And I open the created WO
And I verify the selected status is "Purchase Order Closed" for the dropdown "Status"

 ###---------------------------Closing the raised Work Order--------------------------###
  And I close the created work order
  And I verify the selected status is "Work Order Closed" for the dropdown "Status"
  And I Click on user icon
  And I click Logout button

@1873
Scenario: Verify PO is raised for <5k Quotes
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | keyboard   | Apple       | 600     |
  And I click the "Total Price" box
    ###----------------Submit the quote 1--------------------####
  And I click the "Submit" button
  And I click the "confirm" button
  Then I verify that "Quote Successfully Added" validation  message appears
  And Click on Add Quote by Purchase Officer Button
  And I set the quote date in the datepicker
  Then Input values in the Quotes pop-up
    | QuoteTitle | QuoteVendor | QuotedPrice |
    | Mouse   | Test1       | 700     |
  And I click the "Total Price" box
     ###----------------Submit the quote 2--------------------####
  And I click the "Submit" and "confirm" button
  Then I verify that "Quote Successfully Added" validation  message appears
    ###----------------Verify the quote status--------------------####
  Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
  And Verify the approval status on Quotes Tab for 1st Quote
  And Verify the approval status on Quotes Tab for 2nd Quote
  And I Click on user icon
  And I click Logout button

###------------Procurement Manager - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Procurementmanager"

###----------------------Get the Work Order ID-----------------------###
  And I open the created WO
  Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Pending for Approval"
  And I click the eye icon

###------------Approve any one of the quotes by Proc Mgr-------------###
  And I click the "Approve" button
  And I click the "confirm" button
    ###----------------Verify the quote status--------------------####
  Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
  And Verify the approval status on Quotes Tab for 1st Quote
  And Verify the approval status on Quotes Tab for 2nd Quote
  And I Click on user icon
  And I click Logout button

###------------Approver - Login Functionality-------------###
  Given I log into the Purchase Orders app as an "Approver"
    ###---------------Get the Work order number and search in grid--------------###
  And I open the created WO
  And I click on the "Quotes" link
  Then I check if "Approval Status" is "Partially Approved"
  And I click the eye icon
###---------------Approves the same quote by Approver---------------###
  And I click the "Approve" button
  And I click the "confirm" button
  Then I verify that "Quote Approved" validation  message appears
  Then I verify the selected status is "Pending Purchase Order" for the dropdown "Status"
  Then I check if "Approval Status" is changed to "Approved"
  And I Click on user icon
  And I click Logout button

###------------Purchase Officer - To raise purchase order Request-------------###
  Given I log into the Purchase Orders app as an "Purchaseofficer"

###----------Navigates to Request Tab and search the raised Work order------------------###
  And I open the created WO

###---------------------------To Add PO, enter all the required details-------------------------------------###
  And I click the "Add Purchase Order" button
  Then Enter Purchase Order confirmation Details
    | VendorReferenceNumber | VendorAddress              | PartNo |
    | PATRA-1631            | Visakapatnam,AndhraPradesh | 1A     |

 ###---------------------------Raise Purchase Order Request---------------------###
  And I raise the purchase order
  And I verify the selected status is "Purchase Order Raised" for the dropdown "Status"
  And I get the created Purchase Order Number

###---------------------------Closing the raised Purchase Order---------------------###
  And I close the purchase order
 Then I verify that "Purchase Order Closed Successfully" validation  message appears
  And I verify the selected status is "Purchase Order Closed" for the dropdown "Status"
  And I Click on user icon
  And I click Logout button

##------------Initiator - To Close Work Order------------###
  Given I log into the Purchase Orders app as an "Initiator"

###-------------------Search the Raised Work order request in grid--------------------###
  And I open the created WO
  And I verify the selected status is "Purchase Order Closed" for the dropdown "Status"

 ###---------------------------Closing the raised Work Order--------------------------###
  And I close the created work order
  And I verify the selected status is "Work Order Closed" for the dropdown "Status"
  And I Click on user icon
  And I click Logout button

