@WorkOrderGridsDataDriven
Feature: Work Order Grids Data Driven

  @setup
  Scenario: Login component
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
    And this "is" a data driven test
    #

  @setup
  Scenario Outline: Create Test Data for Drop Down Test
    When I click the "Add Work Order" button
    And I click "Task Back To AM" checkbox
    And I click "Rush" checkbox
    And I click "Hold" checkbox
    And I click "Email" checkbox
    And I click "Issue" checkbox
    And I click "Instructions" checkbox
    And I click "Pending Carrier" checkbox
    And I click "Assign" checkbox
    And I set the date in the "Due Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2020 | 6:45 | AM      |
    And I set the date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2020 | 6:45 | AM      |
    And I set the date in the "Effective Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2020 | 6:45 | AM      |
    And I set the date in the "Next Check Date" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2020 | 6:45 | AM      |
    And I enter the following information into the form
      | Folder           | <Folder>           |
      | Status           | On Hold            |
      | Endorsement Type | AGL                |
      | Request Type     | End from AM        |
      | Delivery Type    | Email Out          |
      | Producer Code    | AYAADE             |
      | Hold Reason      | Reason to Hold     |
      | Client Code      | Test Client Code   |
      | Assign To        | Jeff Turner        |
    ###---------- Verify Work Order Entry Tab ----------###
    Then I Click on "Work Order Details" tab
    And I set the date in the "Close WO If No Response From AM" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2020 | 6:45 | AM      |
    And I enter the following information into the form
      | Division      | Experience Modification Projection |
      | Department    | CL - Commercial Lines              |
      | Policy Status | Select Policy Status               |
      | Branch Office | 112 Arizona                        |
      | Endorsement Checked By               | Test User          |
      | Number Of                            | 123                |


    ###---------- Verify Endorsement Details Tab ----------###
    Then I Click on "Endorsement Details" tab
    And I enter the following information into the form
      | Billing Type    | DB  |
      | Is Invoiced     | Yes |
      | Premium Bearing | Yes |
      | Policy Number                        | asdf1234           |
      | Client Name                          | Test Client        |
    When I click the "Submit and Open" button
    And I enter the following information into the form
      | Requestor                            | Test Requestor     |
      | Summary                              | Test Summary       |
    When I click the "Save & Close" button

    Examples:
      | Folder                 |
      | Endorsements to Check  |
      | Completed Endorsements |
      | Discarded Endorsements |
      | Important Instructions |

  @10000
  Scenario Outline: Open WO Text Field columns
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I filter by "<text>" in the "<column>" text field
    Then I verify that value of "<column>" for "row 1" is "<text>"
    And I click the "<tab>" tab Reset button
    And I filter by "xxxx" in the "<column>" text field
    Then I verify the number of records in "<tab>" is 0
    Examples:
      | text             | column                 | tab  |
      | Test User        | Endorsement Checked By | Open |
      | Test Client Code | Client Code            | Open |
      | Jeff Turner      | Assigned To            | Open |
      | 123              | Number Of              | Open |
      | asdf1234         | Policy #               | Open |
      | Test Client      | Client Name            | Open |
      | Test Requestor   | Requestor              | Open |
      | Test Summary     | Summary                | Open |

  @10004
  Scenario Outline: Completed WO Text Field columns
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I filter by "<text>" in the "<column>" text field
    Then I verify that value of "<column>" for "row 1" is "<text>"
    And I click the "<tab>" tab Reset button
    And I filter by "xxxx" in the "<column>" text field
    Then I verify the number of records in "<tab>" is 0
    Examples:
      | text             | column                 | tab       |
      | Test User        | Endorsement Checked By | Completed |
      | Test Client Code | Client Code            | Completed |
      | Jeff Turner      | Assigned To            | Completed |
      | 123              | Number Of              | Completed |
      | asdf1234         | Policy #               | Completed |
      | Test Client      | Client Name            | Completed |
      | Test Requestor   | Requestor              | Completed |
      | Test Summary     | Summary                | Completed |

  @10007
  Scenario Outline: Discarded WO Text Field columns
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I filter by "<text>" in the "<column>" text field
    Then I verify that value of "<column>" for "row 1" is "<text>"
    And I click the "<tab>" tab Reset button
    And I filter by "xxxx" in the "<column>" text field
    Then I verify the number of records in "<tab>" is 0
    Examples:
      | text             | column                 | tab       |
      | Test User        | Endorsement Checked By | Discarded |
      | Test Client Code | Client Code            | Discarded |
      | Jeff Turner      | Assigned To            | Discarded |
      | 123              | Number Of              | Discarded |
      | asdf1234         | Policy #               | Discarded |
      | Test Client      | Client Name            | Discarded |
      | Test Requestor   | Requestor              | Discarded |
      | Test Summary     | Summary                | Discarded |

  @10010
  Scenario Outline: Important Instructions WO Text Field columns
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I filter by "<text>" in the "<column>" text field
    Then I verify that value of "<column>" for "row 1" is "<text>"
    And I click the "<tab>" tab Reset button
    And I filter by "xxxx" in the "<column>" text field
    Then I verify the number of records in "<tab>" is 0
    Examples:
      | text             | column                 | tab                    |
      | Test User        | Endorsement Checked By | Important Instructions |
      | Test Client Code | Client Code            | Important Instructions |
      | Jeff Turner      | Assigned To            | Important Instructions |
      | 123              | Number Of              | Important Instructions |
      | asdf1234         | Policy #               | Important Instructions |
      | Test Client      | Client Name            | Important Instructions |
      | Test Requestor   | Requestor              | Important Instructions |
      | Test Summary     | Summary                | Important Instructions |

  @10001
  Scenario Outline: Data Driven Open Checkbox test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I select "True" from the "<Column>" header in the grid
    And I verify the "<Column>" checkbox status for "row 1" row of the grid is "True"
    And I click the "<tab>" tab Reset button
    And I select "False" from the "<Column>" header in the grid
    And I verify the "<Column>" checkbox status for "row 1" row of the grid is "False"
    Examples:
      | Column          | tab  |
      | Rush            | Open |
      | Invoiced        | Open |
      | Premium Bearing | Open |
      | Task Back To AM | Open |
      | Hold            | Open |
      | Email           | Open |
      | Issue           | Open |
      | Instructions    | Open |
      | Pending Carrier | Open |

  @10008
  Scenario Outline: Data Driven Discarded Checkbox test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I select "True" from the "<Column>" header in the grid
    And I verify the "<Column>" checkbox status for "row 1" row of the grid is "True"
    And I click the "<tab>" tab Reset button
    And I select "False" from the "<Column>" header in the grid
    And I verify the "<Column>" checkbox status for "row 1" row of the grid is "False"
    Examples:
      | Column          | tab       |
      | Rush            | Discarded |
      | Invoiced        | Discarded |
      | Premium Bearing | Discarded |
      | Task Back To AM | Discarded |
      | Hold            | Discarded |
      | Email           | Discarded |
      | Issue           | Discarded |
      | Instructions    | Discarded |
      | Pending Carrier | Discarded |


  @10005
  Scenario Outline: Data Driven Completed Checkbox test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I select "True" from the "<Column>" header in the grid
    And I verify the "<Column>" checkbox status for "row 1" row of the grid is "True"
    And I click the "<tab>" tab Reset button
    And I select "False" from the "<Column>" header in the grid
    And I verify the "<Column>" checkbox status for "row 1" row of the grid is "False"

    Examples:
      | Column          | tab       |
      | Rush            | Completed |
      | Invoiced        | Completed |
      | Premium Bearing | Completed |
      | Task Back To AM | Completed |
      | Hold            | Completed |
      | Email           | Completed |
      | Issue           | Completed |
      | Instructions    | Completed |
      | Pending Carrier | Completed |

  @10011
  Scenario Outline: Data Driven Important Instructions Checkbox test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I select "True" from the "<Column>" header in the grid
    And I verify the "<Column>" checkbox status for "row 1" row of the grid is "True"
    And I click the "<tab>" tab Reset button
    And I select "False" from the "<Column>" header in the grid
    And I verify the "<Column>" checkbox status for "row 1" row of the grid is "False"

    Examples:
      | Column          | tab                    |
      | Rush            | Important Instructions |
      | Invoiced        | Important Instructions |
      | Premium Bearing | Important Instructions |
      | Task Back To AM | Important Instructions |
      | Hold            | Important Instructions |
      | Email           | Important Instructions |
      | Issue           | Important Instructions |
      | Instructions    | Important Instructions |
      | Pending Carrier | Important Instructions |

  @10001
  Scenario Outline: Data Driven Open Drop Down Test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I select "<Value>" from the "<Column>" header in the grid
    Then I verify that value of "<Column>" for "row 1" is "<Value>"

    Examples:
      | Column           | Value                              | tab  |
      | Status           | On Hold                            | Open |
      | Endorsement Type | AGL                                | Open |
      | Request Type     | End from AM                        | Open |
      | Delivery Type    | Email Out                          | Open |
      | Producer Code    | AYAADE                             | Open |
      | Division         | Experience Modification Projection | Open |
      | Department       | CL - Commercial Lines              | Open |
      | Branch Office    | 112 Arizona                        | Open |
      | Billing Type     | DB                                 | Open |

  @10005
  Scenario Outline: Data Driven Completed Drop Down Test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I select "<Value>" from the "<Column>" header in the grid
    Then I verify that value of "<Column>" for "row 1" is "<Value>"

    Examples:
      | Column           | Value                              | tab       |
      | Status           | On Hold                            | Completed |
      | Endorsement Type | AGL                                | Completed |
      | Request Type     | End from AM                        | Completed |
      | Delivery Type    | Email Out                          | Completed |
      | Producer Code    | AYAADE                             | Completed |
      | Division         | Experience Modification Projection | Completed |
      | Department       | CL - Commercial Lines              | Completed |
      | Branch Office    | 112 Arizona                        | Completed |
      | Billing Type     | DB                                 | Completed |

  @10008
  Scenario Outline: Data Driven Discarded Drop Down Test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I select "<Value>" from the "<Column>" header in the grid
    Then I verify that value of "<Column>" for "row 1" is "<Value>"

    Examples:
      | Column           | Value                              | tab       |
      | Status           | On Hold                            | Discarded |
      | Endorsement Type | AGL                                | Discarded |
      | Request Type     | End from AM                        | Discarded |
      | Delivery Type    | Email Out                          | Discarded |
      | Producer Code    | AYAADE                             | Discarded |
      | Division         | Experience Modification Projection | Discarded |
      | Department       | CL - Commercial Lines              | Discarded |
      | Branch Office    | 112 Arizona                        | Discarded |
      | Billing Type     | DB                                 | Discarded |

  @10011
  Scenario Outline: Data Driven Important Instructions Drop Down Test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I select "<Value>" from the "<Column>" header in the grid
    Then I verify that value of "<Column>" for "row 1" is "<Value>"

    Examples:
      | Column           | Value                              | tab                    |
      | Status           | On Hold                            | Important Instructions |
      | Endorsement Type | AGL                                | Important Instructions |
      | Request Type     | End from AM                        | Important Instructions |
      | Delivery Type    | Email Out                          | Important Instructions |
      | Producer Code    | AYAADE                             | Important Instructions |
      | Division         | Experience Modification Projection | Important Instructions |
      | Department       | CL - Commercial Lines              | Important Instructions |
      | Branch Office    | 112 Arizona                        | Important Instructions |
      | Billing Type     | DB                                 | Important Instructions |

  @10002
  Scenario Outline: Data Driven Open Date Test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I set the "From" field in the "<column>" header to "<day>"-"<month>"-"<year>"
    And I set the "To" field in the "<column>" header to "<day>"-"<month>"-"<year>"
    Then I verify that value of "<column>" for "row 1" is "<date>"

    Examples:
      | tab  | column                          | day | month | year | date       |
      | Open | Due Date                        | 11  | May   | 2020 | 05/11/2020 |
      | Open | Next Check Date                 | 11  | May   | 2020 | 05/11/2020 |
      | Open | Effective Date                  | 11  | May   | 2020 | 05/11/2020 |
      | Open | Next Check Date                 | 11  | May   | 2020 | 05/11/2020 |
      | Open | Close WO If No Response From AM | 11  | May   | 2020 | 05/11/2020 |

  @10006
  Scenario Outline: Data Driven Completed Date Test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I set the "From" field in the "<column>" header to "<day>"-"<month>"-"<year>"
    And I set the "To" field in the "<column>" header to "<day>"-"<month>"-"<year>"
    Then I verify that value of "<column>" for "row 1" is "<date>"

    Examples:
      | tab       | column                          | day | month | year | date       |
      | Completed | Due Date                        | 11  | May   | 2020 | 05/11/2020 |
      | Completed | Next Check Date                 | 11  | May   | 2020 | 05/11/2020 |
      | Completed | Effective Date                  | 11  | May   | 2020 | 05/11/2020 |
      | Completed | Next Check Date                 | 11  | May   | 2020 | 05/11/2020 |
      | Completed | Close WO If No Response From AM | 11  | May   | 2020 | 05/11/2020 |

  @10009
  Scenario Outline: Data Driven Discarded Date Test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I set the "From" field in the "<column>" header to "<day>"-"<month>"-"<year>"
    And I set the "To" field in the "<column>" header to "<day>"-"<month>"-"<year>"
    Then I verify that value of "<column>" for "row 1" is "<date>"

    Examples:
      | tab       | column                          | day | month | year | date       |
      | Discarded | Due Date                        | 11  | May   | 2020 | 05/11/2020 |
      | Discarded | Next Check Date                 | 11  | May   | 2020 | 05/11/2020 |
      | Discarded | Effective Date                  | 11  | May   | 2020 | 05/11/2020 |
      | Discarded | Next Check Date                 | 11  | May   | 2020 | 05/11/2020 |
      | Discarded | Close WO If No Response From AM | 11  | May   | 2020 | 05/11/2020 |

  @10012
  Scenario Outline: Data Driven Important Instructions Date Test
    And I Click on "<tab>" tab
    And I click the "<tab>" tab Reset button
    And I set the "From" field in the "<column>" header to "<day>"-"<month>"-"<year>"
    And I set the "To" field in the "<column>" header to "<day>"-"<month>"-"<year>"
    Then I verify that value of "<column>" for "row 1" is "<date>"

    Examples:
      | tab                    | column                          | day | month | year | date       |
      | Important Instructions | Due Date                        | 11  | May   | 2020 | 05/11/2020 |
      | Important Instructions | Next Check Date                 | 11  | May   | 2020 | 05/11/2020 |
      | Important Instructions | Effective Date                  | 11  | May   | 2020 | 05/11/2020 |
      | Important Instructions | Next Check Date                 | 11  | May   | 2020 | 05/11/2020 |
      | Important Instructions | Close WO If No Response From AM | 11  | May   | 2020 | 05/11/2020 |

  @setup
  Scenario: End of Parameterized Test
    And this "is not" a data driven test