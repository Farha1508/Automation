
Feature: Testing code before adding it to main feature

  Background: Log into Billing
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

    ###---------- App selection ----------###
    When I click on the "Work Order Tracking" tile
    ###---------- App selection ----------###
    
    ###---------- Fill ValueStore ----------###
    And I add "Record ID" key and "1845" value to valueMap
    And I add "Status" key and "Untouched" value to valueMap
    And I add "Account Manager" key and "NodeSanity User" value to valueMap
    And I add "Company" key and "Company 646" value to valueMap
    And I add "Business Type" key and "new" value to valueMap
    And I add "Employer Group" key and "999" value to valueMap
    ###---------- Fill ValueStore ----------###

  Scenario: Add WO Happy Path

    ###---------- Application Select ----------###
    When I open "Certificate Issuance" for company "Company 002"
    Then The page for the selected company and service will be displayed
    ###---------- Application Select ----------###
    And I get the "WO #" for the "row 4" row of the grid
    And I enter that wo number into the grid
    And I click on the "Discarded" link
    And I wait for "5" seconds
    And I get the "WO #" for the "row 4" row of the grid
    And I enter that wo number into the grid
#    And I enter "246653" into the "WO #" grid header
#    And I click on the "24" link
#    Then The detail page for the new work order will be displayed
#    And I select "Discarded Work Orders" from the "Folder" drop down
#    And I click the "confirm" button
#    And I select "Testing" from the "Discarded Reason" drop down
#    And I click the "Save" button
    And I wait for "5" seconds
