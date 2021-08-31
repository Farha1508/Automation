@AddWorkOrder
Feature: Add Work Order

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
    And I click on the "Incoming Responses" KPI



  @9991
  Scenario: Submit a new Work Order
    When I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    Then I Click on "Endorsement Details" tab
    And I enter "Test Client<current date>" in the "Client Name" field
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    And I find the Endorsement Processing work order in the grid

  @9992
  Scenario: Submit and Open a new Work Order
    And I clear the valueStore
    When I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    And I enter "Test Client<current date>" in the "Client Code" field
    When I click the "Submit and Open" button
    Then I verify the content in "Client Code" matches what was entered in the "Client Code" field

  @9993  @SmokeTest
  Scenario: Cancel creating a Work Order
    And I clear the valueStore
    When I get the number of records in the "Open" tab
    When I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    And I enter "Test Client<current date>" in the "Client Code" field
    When I click the "Cancel" button
    Then I verify that the number of rows in the "Open" tab has not changed

  @10013
  Scenario: Work Order creation Check Box Functionality
    When I click the "Add Work Order" button
    ###---------- Rush Checkbox Test ----------###
    And I set the date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    When I click "Rush" checkbox
    Then I verify that the due date is set to the expected day based on Rush being "enabled" in the Form
    ###---------- Assign Checkbox Test ----------###
    When I click "Assign" checkbox
    Then I verify that the "Assign To" dropdown menu appears
    ###---------- Hold Checkbox Test ----------###
    When I click "Hold" checkbox
    Then I verify that the "Hold Reason" field appears
    And the "Hold Reason" field is mandatory
    And I enter "this is a long string for me to use as a way to test the character limit of this field because that is a fun thing to do " in the "Hold Reason" field
    Then I verify that there is a limit of 100 characters
    ###---------- Task Complete Checkbox Test ----------###
    When I click "Task Completed" checkbox
    Then I verify the value for the "Status" drop down is "Available"
    Then I verify the value for the "Folder" drop down is "Endorsement Processing"
    And I select "Unassigned" from the "Status" drop down
    ###---------- Email Checkbox Test ----------###
    When I click "Email" checkbox
    Then I verify the Task Completed checkbox is not visible
    Then I verify the value for the "Status" drop down is "Available"
    Then I verify the value for the "Folder" drop down is "Email Needed"
    ###---------- Uncheck Email to reset form ----------###
    When I click "Email" checkbox
    When I click the "ok" button
    ###---------- Discard Checkbox Test ----------###
    When I click "Discard" checkbox
    Then I verify that the confirmation warning appears
    When I click the "confirm" button
    Then I verify the Task Completed checkbox is not visible
    Then I verify that the "Discarded Reason" dropdown menu appears
    Then I verify the value for the "Folder" drop down is "Discarded Endorsements"
    Then I verify the value for the "Status" drop down is "Discarded"
    ###---------- Uncheck Discard to reset form ----------###
    When I click "Discard" checkbox
    When I click the "ok" button
    And I wait for "1" seconds
    When I click the "ok" button
    ###---------- Issue Checkbox Test ----------###
    When I click "Issue" checkbox
    Then I verify the value for the "Folder" drop down is "Issues to be Reviewed"
    ###---------- Pending Carrier Checkbox Test ----------###
    When I click "Pending Carrier" checkbox
    Then I verify the value for the "Folder" drop down is "Pending Carrier"
    ###---------- Instructions Checkbox Test ----------###
    When I click "Instructions" checkbox
    Then I verify the value for the "Folder" drop down is "Important Instructions"
    Then I verify the value for the "Status" drop down is "Completed"
    ###---------- Setup Test Data for Task Back to AM Test ----------###
    And I set the date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Start Date Override" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Due Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Due Date Override" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Next Check Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Next Check Date" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Effective Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Effective Date" key and "05/11/2018 6:45 AM" value to valueMap
    And I enter the following information into the form
      | Client Code                                 | Test Client Code        |
      | Status                                      | Unassigned              |
      | Folder                                      | Not Assigned            |
      | Endorsement Type                            | ACC                     |
      | Process                                     | Endorsements Checking   |
      | Request Type                                | Audit                   |
      | Producer Code                               | MCDCH1                  |
      | Delivery Type                               | Mailed                  |
      | Automatic Download                          | Yes                     |
      | Endorsement From Daily Report               | Yes                     |
      | Endorsement Downloaded From Carrier Website | Yes                     |
    ###---------- Task Back to AM Checkbox Test ----------###
    When I click "Task Back To AM" checkbox
    Then I verify the following information in the form
      | Client Code                                 | Test Client Code        |
      | Status                                      | Unassigned              |
      | Folder                                      | Not Assigned            |
      | Endorsement Type                            | ACC                     |
      | Process                                     | Endorsements Checking   |
      | Request Type                                | Audit                   |
      | Producer Code                               | MCDCH1                  |
      | Delivery Type                               | Mailed                  |
      | Automatic Download                          | Yes                     |
      | Endorsement From Daily Report               | Yes                     |
      | Endorsement Downloaded From Carrier Website | Yes                     |
      | Start Date Override                         | 05/11/2018 6:45 AM      |
      | Due Date Override                           | 05/11/2018 6:45 AM      |
      | Next Check Date                             | 05/11/2018 6:45 AM      |
      | Effective Date                              | 05/11/2018 6:45 AM      |

  @9994
  Scenario: Cannot Submit Work Order without filling Mandatory Fields
    #Select the Hold checkbox to reveal the Hold Reason field which is mandatory
    #try to create the WO and verify you can't and the expected error appears
    When I click the "Add Work Order" button
    When I click "Hold" checkbox
    When I click the "Submit" button
    Then I verify the submit error appears

  @9995
  Scenario: Cannot Submit and Open Work Order without filling Mandatory Fields
    #Select the Hold checkbox to reveal the Hold Reason field which is mandatory
    #try to create the WO and verify you can't and the expected error appears
    When I click the "Add Work Order" button
    When I click "Hold" checkbox
    When I click the "Submit and Open" button
    Then I verify the submit error appears

  @10801
  Scenario: Create a new Work Order will all fields filled with Submit

    And I click on the "Incoming Responses" KPI
    When I click the "Add Work Order" button

    ###---------- Fill Work Order Entry Tab ----------###
    And I set the date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Start Date Override" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Due Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Due Date Override" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Next Check Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Next Check Date" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Effective Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Effective Date" key and "05/11/2018 6:45 AM" value to valueMap
    And I enter the following information into the form
      | Client Code                                 | Test Client Code        |
      | Endorsement Type                            | ACC                     |
      | Process                                     | Endorsements Checking   |
      | Request Type                                | Audit                   |
      | Producer Code                               | MCDCH1                  |
      | Delivery Type                               | Mailed                  |
      | Automatic Download                          | Yes                     |
      | Endorsement From Daily Report               | Yes                     |
      | Endorsement Downloaded From Carrier Website | Yes                     |
      | Folder                                      | Not Assigned            |
      | Status                                      | Unassigned              |

    ###---------- Fill Work Order Details Tab ----------###
    Then I Click on "Work Order Details" tab
    And I enter the following information into the form
      | Endorsement Checked By                      | Test User                            |
      | Branch Office                               | 101 Walnut Creek                     |
      | Division                                    | Experience Modification Projection   |
      | QA Done by                                  | Jeff Turner                          |
      | Department                                  | CL - Commercial Lines                |
      | Profit Center                               | Select Profit Center                 |
      | Policy Status                               | Select Policy Status                 |
      | Number Of                                   | 123                                  |
      | Complexity                                  | Difficult                            |
    And I set the date in the "Close WO If No Response From AM" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Close WO If No Response From AM" key and "05/11/2018 6:45 AM" value to valueMap

    ###---------- Fill Endorsement Details Tab ----------###
    Then I Click on "Endorsement Details" tab
    And I enter the following information into the form
      | Policy Number                      | asdf1234           |
      | Client Name                        | Test Client        |
      | Carrier                            | Ace                |
      | Billing Type                       | AB                 |
      | Premium Bearing                    | Yes                |
      | Is Invoiced                        | Yes                |

    ###---------- I submit the work order and open it -----------###
    When I click the "Submit" button
    And I get the work order number from the confirmation pop-up
    When I click the "Open" tab Reset button
    And I find the Endorsement Processing work order in the grid
    And I open the Work Order
    And I wait for "2" seconds

    ###---------- Verify Work Order Entry Tab ----------###
    Then I verify the following information in the form
      | Client Code                                 | Test Client Code        |
      | Status                                      | Unassigned              |
      | Folder                                      | Not Assigned            |
      | Endorsement Type                            | ACC                     |
      | Process                                     | Endorsements Checking   |
      | Request Type                                | Audit                   |
      | Producer Code                               | MCDCH1                  |
      | Delivery Type                               | Mailed                  |
      | Automatic Download                          | Yes                     |
      | Endorsement From Daily Report               | Yes                     |
      | Endorsement Downloaded From Carrier Website | Yes                     |
      | Start Date Override                         | 05/11/2018 6:45 AM      |
      | Due Date Override                           | 05/11/2018 6:45 AM      |
      | Next Check Date                             | 05/11/2018 6:45 AM      |
      | Effective Date                              | 05/11/2018 6:45 AM      |

    ###---------- Verify Work Order Entry Tab ----------###
    Then I verify the following information in the form
      | Endorsement Checked By                      | Test User                            |
      | Branch Office                               | 101 Walnut Creek                     |
      | Division                                    | Experience Modification Projection   |
      | QA Done by                                  | Jeff Turner                          |
      | Department                                  | CL - Commercial Lines                |
      | Profit Center                               | Select Profit Center                 |
      | Policy Status                               | Select Policy Status                 |
      | Number Of                                   | 123                                  |
      | Complexity                                  | Difficult                            |
      | Close WO If No Response From AM             | 05/11/2018 6:45 AM                   |

    ###---------- Verify Endorsement Details Tab ----------###
    Then I Click on "Endorsement" tab
    Then I verify the following information in the form
      | Policy Number                      | asdf1234           |
      | Client Name                        | Test Client        |
      | Carrier                            | Ace                |
      | Billing Type                       | AB                 |
      | Premium Bearing                    | Yes                |
      | Is Invoiced                        | Yes                |

  @10802
  Scenario: Create a new Work Order with all fields filled with Submit and Open

    And I click on the "Incoming Responses" KPI
    When I click the "Add Work Order" button

    ###---------- Fill Work Order Entry Tab ----------###
    And I set the date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Start Date Override" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Due Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Due Date Override" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Next Check Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Next Check Date" key and "05/11/2018 6:45 AM" value to valueMap
    And I set the date in the "Effective Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Effective Date" key and "05/11/2018 6:45 AM" value to valueMap
    And I enter the following information into the form
      | Client Code                                 | Test Client Code        |
      | Endorsement Type                            | ACC                     |
      | Process                                     | Endorsements Checking   |
      | Request Type                                | Audit                   |
      | Producer Code                               | MCDCH1                  |
      | Delivery Type                               | Mailed                  |
      | Automatic Download                          | Yes                     |
      | Endorsement From Daily Report               | Yes                     |
      | Endorsement Downloaded From Carrier Website | Yes                     |
      | Folder                                      | Not Assigned            |
      | Status                                      | Unassigned              |

    ###---------- Fill Work Order Details Tab ----------###
    Then I Click on "Work Order Details" tab
    And I enter the following information into the form
      | Endorsement Checked By                      | Test User                            |
      | Branch Office                               | 101 Walnut Creek                     |
      | Division                                    | Experience Modification Projection   |
      | QA Done by                                  | Jeff Turner                          |
      | Department                                  | CL - Commercial Lines                |
      | Profit Center                               | Select Profit Center                 |
      | Policy Status                               | Select Policy Status                 |
      | Number Of                                   | 123                                  |
      | Complexity                                  | Difficult                            |
    And I set the date in the "Close WO If No Response From AM" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I add "Close WO If No Response From AM" key and "05/11/2018 6:45 AM" value to valueMap

    ###---------- Fill Endorsement Details Tab ----------###
    Then I Click on "Endorsement Details" tab
    And I enter the following information into the form
      | Policy Number                      | asdf1234           |
      | Client Name                        | Test Client        |
      | Carrier                            | Ace                |
      | Billing Type                       | AB                 |
      | Premium Bearing                    | Yes                |
      | Is Invoiced                        | Yes                |

    ###---------- I submit and open the work order and open it -----------###
    When I click the "Submit and Open" button


    ###---------- Verify Work Order Entry Tab ----------###
    Then I verify the following information in the form
      | Client Code                                 | Test Client Code        |
      | Status                                      | Unassigned              |
      | Folder                                      | Not Assigned            |
      | Endorsement Type                            | ACC                     |
      | Process                                     | Endorsements Checking   |
      | Request Type                                | Audit                   |
      | Producer Code                               | MCDCH1                  |
      | Delivery Type                               | Mailed                  |
      | Automatic Download                          | Yes                     |
      | Endorsement From Daily Report               | Yes                     |
      | Endorsement Downloaded From Carrier Website | Yes                     |
      | Start Date Override                         | 05/11/2018 6:45 AM      |
      | Due Date Override                           | 05/11/2018 6:45 AM      |
      | Next Check Date                             | 05/11/2018 6:45 AM      |
      | Effective Date                              | 05/11/2018 6:45 AM      |

    ###---------- Verify Work Order Entry Tab ----------###
    Then I verify the following information in the form
      | Endorsement Checked By                      | Test User                            |
      | Branch Office                               | 101 Walnut Creek                     |
      | Division                                    | Experience Modification Projection   |
      | QA Done by                                  | Jeff Turner                          |
      | Department                                  | CL - Commercial Lines                |
      | Profit Center                               | Select Profit Center                 |
      | Policy Status                               | Select Policy Status                 |
      | Number Of                                   | 123                                  |
      | Complexity                                  | Difficult                            |
      | Close WO If No Response From AM             | 05/11/2018 6:45 AM                   |

    ###---------- Verify Endorsement Details Tab ----------###
    Then I Click on "Endorsement" tab
    Then I verify the following information in the form
      | Policy Number                      | asdf1234           |
      | Client Name                        | Test Client        |
      | Carrier                            | Ace                |
      | Billing Type                       | AB                 |
      | Premium Bearing                    | Yes                |
      | Is Invoiced                        | Yes                |


