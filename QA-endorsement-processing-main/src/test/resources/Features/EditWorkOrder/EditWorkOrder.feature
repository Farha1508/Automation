@EditWorkOrder
Feature: Edit Work Order

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
    When I click the "Add Work Order" button
    #

  @10234
  Scenario: Original Email is non-editable
    And I Click on "Original Email" tab
    And I enter "Original Email <current date>" in the "Original Email" field
    And I click the "Submit and Open" button
    And I click the "Original Email" button
    And I verify the textarea "Original Email" matches the created "Original Email"
    And I verify the Original Email textarea is readonly
    # Verify Original Email field displays as expected and is not editable

  @9977
  Scenario: Verify the Next Reminder Needed field functionality
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    When I click the "Open" tab Reset button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Order/Reminder Dates" tab
    And I set the date in the "Request Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    When I click the "Save & Close" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Order/Reminder Dates" tab
    Then I verify that the Next Reminder Needed is set to the expected day based the "Request Date" date
    And I set the date in the "1st Follow Up" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 17  | 2018 | 6:45 | AM      |
    When I click the "Save & Close" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Order/Reminder Dates" tab
    Then I verify that the Next Reminder Needed is set to the expected day based the "1st Follow Up" date
    And I set the date in the "2nd Follow Up" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 24 | 2018 | 6:45 | AM      |
    When I click the "Save & Close" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Order/Reminder Dates" tab
    Then I verify that the Next Reminder Needed is set to the expected day based the "2nd Follow Up" date
    And I set the date in the "3rd Follow Up" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 31 | 2018 | 6:45 | AM      |
    When I click the "Save & Close" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Order/Reminder Dates" tab
    Then I verify that the Next Reminder Needed is set to the expected day based the "3rd Follow Up" date
    # Verify functionality of Order/Reminder Dates

  @9975
  Scenario: Verify the Next Reminder Date is displayed and prioritized is as expected
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    When I click the "Open" tab Reset button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    # First we ensure the Next Reminder Needed date prioritizes the 3rd Follow Up date
    And I Click on "Order/Reminder Dates" tab
    And I set the date in the "3rd Follow Up" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 31 | 2018 | 6:45 | AM      |
    And I set the date in the "1st Follow Up" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 18 | 2018 | 6:45 | AM      |
    And I set the date in the "Request Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I set the date in the "2nd Follow Up" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 24 | 2018 | 6:45 | AM      |
    When I click the "Save & Close" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Order/Reminder Dates" tab
    Then I verify that the Next Reminder Needed is set to the expected day based the "3rd Follow Up" date
    # Now we check to see if the Endt Received checkbox removes the Next Reminder Needed date
    And I click "Endt Received" checkbox
    Then I verify that the Next Reminder Needed is no longer displayed
    And I click "Endt Received" checkbox
    And I click "Back to AM for Additional Info" checkbox
    When I click the "Save & Close" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Order/Reminder Dates" tab
    Then I verify that the Next Reminder Needed is the placeholder
    # Verify functionality of Order/Reminder Dates

  @10292
  Scenario: Email Reason Panel
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    When I click the "Open" tab Reset button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Email Reason/Delivery" tab
    And I click "Initial Email Sent" checkbox
    And I click "No Endorsement Transaction" checkbox
    And I click "No Diary" checkbox
    And I click "No Change Request" checkbox
    And I click "24 Hrs Email Sent" checkbox
    And I click "48 Hrs Email Sent" checkbox
    When I click the "Save & Close" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Email Reason/Delivery" tab
    And I verify the following checkboxes in the form
      | Initial Email Sent         | checked |
      | No Endorsement Transaction | checked |
      | No Diary                   | checked |
      | No Change Request          | checked |
      | 24 Hrs Email Sent          | checked |
      | 48 Hrs Email Sent          | checked |
    Then I verify the "24 Hrs Email Sent" date is the expected value
    Then I verify the "48 Hrs Email Sent" date is the expected value
    # Verify the functionality of the Email Reason panel

  @10294
  Scenario: Delivery Status Panel
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    When I click the "Open" tab Reset button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Email Reason/Delivery" tab
    And I click "Email Sent to A/C Manager" checkbox
    And I click "Email Attached in Tam" checkbox
    And I click "Epay Updated" checkbox
    And I click "EPIC Updated" checkbox
    And I click "Email Attached in EPIC" checkbox
    And I click "Checklist Uploaded to TAM Activity" checkbox
    And I click "Endorsement Upload to TAM Activity" checkbox
    When I click the "Save & Close" button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I Click on "Email Reason/Delivery" tab
    And I verify the following checkboxes in the form
      | Email Sent to A/C Manager          | checked |
      | Email Attached in Tam              | checked |
      | Epay Updated                       | checked |
      | EPIC Updated                       | checked |
      | Email Attached in EPIC             | checked |
      | Checklist Uploaded to TAM Activity | checked |
      | Endorsement Upload to TAM Activity | checked |
    # Verify the functionality of the Email Reason panel

    @10295
    Scenario: Verify Audit Questions Tab
      When I click the "Submit" button
      And I get the work order number from the confirmation pop-up
      When I click the "Open" tab Reset button
      And I find the Endorsement Processing work order in the grid
      And I open the Work Order
      And I Click on "Audit Questions" tab
      Then I verify these drop down menus have these values
        | ImageRight task is moved to Endorsement Issuance review step?                                                                                              | N/A |
        | Draft saved in S:Drive not in ImageRight?                                                                                                                  | N/A |
        | Pro-rata factor applied correctly?                                                                                                                         | N/A |
        | Endorsement request has all the information needed to process?                                                                                             | N/A |
        | Other?                                                                                                                                                     | N/A |
        | Transaction Type is entered in Premiere and QS?                                                                                                            | N/A |
        | Policy Number is correct per endorsement request or policy number on policy?                                                                               | N/A |
        | Named Insured matches name on policy and/or endorsement request?                                                                                           | N/A |
        | Policy Change Effective date on all forms matches endorsement effective date on the request?                                                               | N/A |
        | Coverage Parts Affected section on Endt is correct per endorsements request?                                                                               | N/A |
        | Changes match per the endorsement request?                                                                                                                 | N/A |
        | Endorsement premium matches per what is on the endorsement request and/or per QS or Premiere?                                                              | N/A |
        | Appropriate form attached per endorsement request or per standard on Endt instruction Guide?                                                               | N/A |
        | VIN or FEIN numbers are correct per endt requests if applicable?                                                                                           | N/A |
        | AP/RP, Taxes, Fess and Surcharges are correct per QS or Premiere, Note: If the endt is not committed, this will not appear?                                | N/A |
        | Fill in verbiage on form is correct per endorsement request or per standard on Endorsement Instruction Guide?                                              | N/A |
        | Spelling is correct on all forms?                                                                                                                          | N/A |
        | Endorsement Number is correct by checking in Premiere or QS for the last endt, number created?                                                             | N/A |
        | Authorized Representative field on the endt is deleted off endorsement forms and this would only need to be verified to see if they have not taken if off? | N/A |
      And I enter the following information into the form
        | ImageRight task is moved to Endorsement Issuance review step?                                                                                              | Yes |
        | Draft saved in S:Drive not in ImageRight?                                                                                                                  | Yes |
        | Pro-rata factor applied correctly?                                                                                                                         | Yes |
        | Endorsement request has all the information needed to process?                                                                                             | Yes |
        | Other?                                                                                                                                                     | Yes |
        | Transaction Type is entered in Premiere and QS?                                                                                                            | Yes |
        | Policy Number is correct per endorsement request or policy number on policy?                                                                               | Yes |
        | Named Insured matches name on policy and/or endorsement request?                                                                                           | Yes |
        | Policy Change Effective date on all forms matches endorsement effective date on the request?                                                               | Yes |
        | Coverage Parts Affected section on Endt is correct per endorsements request?                                                                               | Yes |
        | Changes match per the endorsement request?                                                                                                                 | Yes |
        | Endorsement premium matches per what is on the endorsement request and/or per QS or Premiere?                                                              | Yes |
        | Appropriate form attached per endorsement request or per standard on Endt instruction Guide?                                                               | Yes |
        | VIN or FEIN numbers are correct per endt requests if applicable?                                                                                           | Yes |
        | AP/RP, Taxes, Fess and Surcharges are correct per QS or Premiere, Note: If the endt is not committed, this will not appear?                                | Yes |
        | Fill in verbiage on form is correct per endorsement request or per standard on Endorsement Instruction Guide?                                              | Yes |
        | Spelling is correct on all forms?                                                                                                                          | Yes |
        | Endorsement Number is correct by checking in Premiere or QS for the last endt, number created?                                                             | Yes |
        | Authorized Representative field on the endt is deleted off endorsement forms and this would only need to be verified to see if they have not taken if off? | Yes |
      When I click the "Save & Close" button
      And I find the Endorsement Processing work order in the grid
      And I open the Work Order
      And I Click on "Audit Questions" tab
      Then I verify these drop down menus have these values
        | ImageRight task is moved to Endorsement Issuance review step?                                                                                              | Yes |
        | Draft saved in S:Drive not in ImageRight?                                                                                                                  | Yes |
        | Pro-rata factor applied correctly?                                                                                                                         | Yes |
        | Endorsement request has all the information needed to process?                                                                                             | Yes |
        | Other?                                                                                                                                                     | Yes |
        | Transaction Type is entered in Premiere and QS?                                                                                                            | Yes |
        | Policy Number is correct per endorsement request or policy number on policy?                                                                               | Yes |
        | Named Insured matches name on policy and/or endorsement request?                                                                                           | Yes |
        | Policy Change Effective date on all forms matches endorsement effective date on the request?                                                               | Yes |
        | Coverage Parts Affected section on Endt is correct per endorsements request?                                                                               | Yes |
        | Changes match per the endorsement request?                                                                                                                 | Yes |
        | Endorsement premium matches per what is on the endorsement request and/or per QS or Premiere?                                                              | Yes |
        | Appropriate form attached per endorsement request or per standard on Endt instruction Guide?                                                               | Yes |
        | VIN or FEIN numbers are correct per endt requests if applicable?                                                                                           | Yes |
        | AP/RP, Taxes, Fess and Surcharges are correct per QS or Premiere, Note: If the endt is not committed, this will not appear?                                | Yes |
        | Fill in verbiage on form is correct per endorsement request or per standard on Endorsement Instruction Guide?                                              | Yes |
        | Spelling is correct on all forms?                                                                                                                          | Yes |
        | Endorsement Number is correct by checking in Premiere or QS for the last endt, number created?                                                             | Yes |
        | Authorized Representative field on the endt is deleted off endorsement forms and this would only need to be verified to see if they have not taken if off? | Yes |
      And I enter the following information into the form
        | ImageRight task is moved to Endorsement Issuance review step?                                                                                              | No |
        | Draft saved in S:Drive not in ImageRight?                                                                                                                  | No |
        | Pro-rata factor applied correctly?                                                                                                                         | No |
        | Endorsement request has all the information needed to process?                                                                                             | No |
        | Other?                                                                                                                                                     | No |
        | Transaction Type is entered in Premiere and QS?                                                                                                            | No |
        | Policy Number is correct per endorsement request or policy number on policy?                                                                               | No |
        | Named Insured matches name on policy and/or endorsement request?                                                                                           | No |
        | Policy Change Effective date on all forms matches endorsement effective date on the request?                                                               | No |
        | Coverage Parts Affected section on Endt is correct per endorsements request?                                                                               | No |
        | Changes match per the endorsement request?                                                                                                                 | No |
        | Endorsement premium matches per what is on the endorsement request and/or per QS or Premiere?                                                              | No |
        | Appropriate form attached per endorsement request or per standard on Endt instruction Guide?                                                               | No |
        | VIN or FEIN numbers are correct per endt requests if applicable?                                                                                           | No |
        | AP/RP, Taxes, Fess and Surcharges are correct per QS or Premiere, Note: If the endt is not committed, this will not appear?                                | No |
        | Fill in verbiage on form is correct per endorsement request or per standard on Endorsement Instruction Guide?                                              | No |
        | Spelling is correct on all forms?                                                                                                                          | No |
        | Endorsement Number is correct by checking in Premiere or QS for the last endt, number created?                                                             | No |
        | Authorized Representative field on the endt is deleted off endorsement forms and this would only need to be verified to see if they have not taken if off? | No |
      When I click the "Save & Close" button
      And I find the Endorsement Processing work order in the grid
      And I open the Work Order
      And I Click on "Audit Questions" tab
      Then I verify these drop down menus have these values
        | ImageRight task is moved to Endorsement Issuance review step?                                                                                              | No |
        | Draft saved in S:Drive not in ImageRight?                                                                                                                  | No |
        | Pro-rata factor applied correctly?                                                                                                                         | No |
        | Endorsement request has all the information needed to process?                                                                                             | No |
        | Other?                                                                                                                                                     | No |
        | Transaction Type is entered in Premiere and QS?                                                                                                            | No |
        | Policy Number is correct per endorsement request or policy number on policy?                                                                               | No |
        | Named Insured matches name on policy and/or endorsement request?                                                                                           | No |
        | Policy Change Effective date on all forms matches endorsement effective date on the request?                                                               | No |
        | Coverage Parts Affected section on Endt is correct per endorsements request?                                                                               | No |
        | Changes match per the endorsement request?                                                                                                                 | No |
        | Endorsement premium matches per what is on the endorsement request and/or per QS or Premiere?                                                              | No |
        | Appropriate form attached per endorsement request or per standard on Endt instruction Guide?                                                               | No |
        | VIN or FEIN numbers are correct per endt requests if applicable?                                                                                           | No |
        | AP/RP, Taxes, Fess and Surcharges are correct per QS or Premiere, Note: If the endt is not committed, this will not appear?                                | No |
        | Fill in verbiage on form is correct per endorsement request or per standard on Endorsement Instruction Guide?                                              | No |
        | Spelling is correct on all forms?                                                                                                                          | No |
        | Endorsement Number is correct by checking in Premiere or QS for the last endt, number created?                                                             | No |
        | Authorized Representative field on the endt is deleted off endorsement forms and this would only need to be verified to see if they have not taken if off? | No |

      @9966
      Scenario: Verify QA Tab as Lead or Above
        When I click the "Submit" button
        And I get the work order number from the confirmation pop-up
        When I click the "Open" tab Reset button
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        And I Click on "QA" tab
        And I enter the following information into the form
          | Audit / QA        | QA              |
          | # of Errors       | 2               |
          | Error Made By     | test jt_user_qa |
          | Error Type        | Typo            |
          | Error Filed By    | Jeff Turner     |
          | Error Description | error test      |
        When I click the "Save & Close" button
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        And I Click on "QA" tab
        Then I verify the following information in the form
          | Audit / QA        | QA              |
          | # of Errors       | 2               |
          | Error Made By     | test jt_user_qa |
          | Error Type        | Typo            |
          | Error Filed By    | Jeff Turner     |
          | Error Description | error test      |

      @9967
      Scenario: Verify QA Tab as Base User is not present
        When I click the "Submit" button
        And I get the work order number from the confirmation pop-up
        When I click the "Open" tab Reset button
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        Then I verify the "QA" tab is "Present"
        When I Click on user icon in WOT
        And I log out
        Given I am on the login page
        When I enter the email and password for the "Base User"
        And I click the Sign In button
        Then I will be taken to the apps page
        When I click on the "Work Order Tracking" tile
        When I open "Endorsement Processing" for company "Company 002"
        Then The page for the selected company and service will be displayed
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        Then I verify the "QA" tab is "Not Present"

      @9968
      Scenario: Verify QA tab for Base User with QA active
        When I click the "Submit" button
        And I get the work order number from the confirmation pop-up
        When I click the "Open" tab Reset button
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        And I Click on "QA" tab
        And I enter the following information into the form
          | Audit / QA        | QA              |
          | # of Errors       | 2               |
          | Error Made By     | test jt_user_qa |
          | Error Type        | Typo            |
          | Error Filed By    | Jeff Turner     |
          | Error Description | error test      |
        When I click the "Save & Close" button
        And I find the Endorsement Processing work order in the grid
        When I Click on user icon in WOT
        And I log out
        Given I am on the login page
        When I enter the email and password for the "Base QA User"
        And I click the Sign In button
        Then I will be taken to the apps page
        When I click on the "Work Order Tracking" tile
        When I open "Endorsement Processing" for company "Company 002"
        Then The page for the selected company and service will be displayed
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        And I Click on "QA" tab
        Then I verify the following information in the form
          | Audit / QA        | QA              |
          | # of Errors       | 2               |
          | Error Made By     | test jt_user_qa |
          | Error Type        | Typo            |
          | Error Filed By    | Jeff Turner     |
          | Error Description | error test      |

      @9921
      Scenario: Verify History Grid
        When I click the "Submit" button
        And I get the work order number from the confirmation pop-up
        When I click the "Open" tab Reset button
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        And I Click on "History" tab
        When I select the "20" option from the the History tab Viewing drop down
        Then I check if the "Save" button is "disabled"
        Then I check if the "Save & Next" button is "disabled"
        Then I check if the "Save & Close" button is "disabled"

      @9922
      Scenario: New Record
        When I click the "Submit" button
        And I get the work order number from the confirmation pop-up
        When I click the "Open" tab Reset button
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        And I Click on "History" tab
        When I get the "Date" for "row 1" row of the grid
        Then I verify the history date is the expected value
        Then I verify that value of "Folder" for "row 1" is "Endorsements to Check"
        Then I verify that value of "IsRush" for "row 1" is "No"
        Then I verify that value of "User Name" for "row 1" is "Jeff Turner"
        And I click the "Mark Rush" button
        And I select "Pending Responses" from the "Folder" drop down
        When I click the "Save & Close" button
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        And I Click on "History" tab
        When I get the "Date" for "row 1" row of the grid
        Then I verify the history date is the expected value
        Then I verify that value of "Folder" for "row 1" is "Pending Responses"
        When I get the "IsRush" for "row 1" row of the grid
        Then I verify that value of "IsRush" for "row 1" is "Yes"
        When I get the "User Name" for "row 1" row of the grid
        Then I verify that value of "User Name" for "row 1" is "Jeff Turner"


      @9924
      Scenario: Sorting
        When I click the "Submit" button
        And I get the work order number from the confirmation pop-up
        When I Click on user icon in WOT
        And I log out
        Given I am on the login page
        When I enter the email and password for the "Base QA User"
        And I click the Sign In button
        Then I will be taken to the apps page
        When I click on the "Work Order Tracking" tile
        When I open "Endorsement Processing" for company "Company 002"
        Then The page for the selected company and service will be displayed
        And I find the Endorsement Processing work order in the grid
        And I open the Work Order
        And I select "Pending Responses" from the "Folder" drop down
        And I click the "Mark Rush" button
        And I click the "Save" button
        And I Click on "History" tab
        When I get the "Folder" for "row 2" row of the grid
        And I sort the "Folder" grid column by "Ascending"
        Then I verify that value of "Folder" for "row 1" matches
        When I get the "IsRush" for "row 2" row of the grid
        And I sort the "IsRush" grid column by "Descending"
        Then I verify that value of "IsRush" for "row 1" matches
        When I get the "User Name" for "row 2" row of the grid
        And I sort the "User Name" grid column by "Ascending"
        Then I verify that value of "User Name" for "row 1" matches
        When I get the "Date" for "row 2" row of the grid
        And I sort the "Date" grid column by "Descending"
        Then I verify that value of "Date" for "row 1" matches