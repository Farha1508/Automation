@Test
Feature: Add Work and folder Movements of the record

  Background: Login to Node application
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    When I click on the "Work Order Tracking" tile
    When I open "Document Retrieval" for company "Company 019"

    @7457
    Scenario: Add Work Order Cancel Button functionality
      And I click the "Add Work Order" button
      And I click the "Cancel" button

    @7455
    Scenario: Verify Add work order functionality with submit button
        And I click the "Add Work Order" button
        And I select "All Matches" from the "Policy Status" drop down
        And I select "Attached Policies" from the "Work Order Type" drop down
        And I enter "TestingQA" in the "Carrier" field
        And I enter "TestingQA" in the "CSR Name" field
        And I enter "PatraT123@#" in the "Client Code" field
        And I enter "PatraTest" in the "Client Name" field
     #   And I select "301 - Personal Lines" from the "Department" drop down
        And I select "102" from the "Division" drop down
     #   And I select "Portland" from the "Branch Office" drop down
        And I click on the "Work Order Details" link
        And I enter "321314214" in the "Number of Docs Downloaded" field
        And I enter "321314214" in the "Traveler's # of Doc Downloaded" field
        And I enter "321314214" in the "PRAC # of Doc Downloaded" field
        And I enter "321314214" in the "Hartford # of Doc Downloaded" field
        And I enter "321314214" in the "Andover # of Doc Downloaded" field
        And I enter "321314214" in the "Chubb # of Doc Downloaded" field
        And I enter "3213" in the "Ohio # of Doc Downloaded" field
        And I enter "3213" in the "Mutual # of Doc Downloaded" field
        And I enter "3213" in the "AIG # of Doc Downloaded" field
        And I enter "3213" in the "UPC # of Doc Downloaded" field
        And I enter "3213" in the "Number of" field
        And I enter "3213" in the "Policy Number" field
        And I enter "3213" in the "Bunker Hill # of Doc Downloaded" field
        And I enter "3213" in the "Central # of Doc Downloaded" field
        And I enter "3213" in the "BITCO # of Doc Downloaded" field
        And I click on the "Incoming Email Details" link
        And I enter "mekala.ramireddy@patracorp.net" in the "Requestor" field
        And I enter "mekala.ramireddy@patracorp.net" in the "Sent to" field
        And I enter "Rami Automation Scripts Testing" in the "Summary" field
        And I click the "Submit" button

  @7456
  Scenario: Verify Add work order functionality with Submit And open Button
    And I click the "Add Work Order" button
    And I select "All Matches" from the "Policy Status" drop down
    And I select "Attached Policies" from the "Work Order Type" drop down
    And I enter "TestingQATeam" in the "Carrier" field
    And I enter "TestingQATeam" in the "CSR Name" field
    And I enter "PatraT123@#" in the "Client Code" field
    And I enter "PatraTest" in the "Client Name" field
  #  And I select "301 - Personal Lines" from the "Department" drop down
    And I select "102" from the "Division" drop down
  #  And I select "Portland" from the "Branch Office" drop down
    And I click on the "Work Order Details" link
    And I enter "321314214" in the "PRAC # of Doc Downloaded" field
    And I click the "Submit and Open" button

















