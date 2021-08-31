
Feature: Sanity tests for PMA
  All tests related to checking the happy path for PMA

  Background: Log into PMA
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "PMA Manager"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

    ###---------- App selection ----------###
    When I click on the "PMA" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###

  @6710
  Scenario: Add Business Happy Path

    ###---------- Add Business ----------###
    When I open the Add Business form
    And I clear the valueStore
    And I select "Patra Select" from the "Brand" drop down
    And I select "ABRC - SAMS" from the "Sub Brand" drop down
    And I enter "999 Test Business <current date>" in the "Business/Policy Holder Name" field
    And I select "Animal Services" from the "Business Class" drop down
    And I select "PMAManager Dev" from the "Broker" drop down
    And I select "CL - Occupational Health" from the "Coverage Type" drop down
    And I enter "999 TEST PLEASE IGNORE" in the "Description of Operations" text box
    And I click the "Submit" button
    And I navigate to the "Opportunity" tab
    Then The new business will be displayed in the grid
    ###---------- Add Business ----------###

  @6704
  Scenario: Edit Added Business

    ###---------- Edit Business ----------###
    When I navigate to the "Business" tab
    And I find the newly created business
    And I open the Business Details form
    And Ensure I am on the detail page for that business
    And I edit the following drop downs
      | Brand | Sub Brand          | Business Class  | Prospect Origin | Type of Organization |
      | Oli   | American Marketing | Animal Services | Email           | Other                |

    And I edit the following fields
      | Business Name                           | Client ID | Referrer Name | Referring Company | Years In Business | Experience | Website       | If Other - Please Specify | # FTE | # PTE | FEIN |
      | 999 Test Business <current date> EDITED | 123       | test          | test              | 11                | 12         | www.patra.com | test                      | 999   | 999   | 999  |

    And I click the "Submit" button
    And I return to the PMA home page
    And I navigate to the "Business" tab
    And I find the edited business
    And I click on the "Business Details" link
    And Ensure I am on the detail page for that business
    Then I will see that the edits were saved
    ###---------- Edit Business Details ----------###

  @6705
  Scenario: Add Activity for Mandatory Fields
    ###---------- Add Activity ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the Add Activity form
    And I select "AMS Policy Detail" from the "Activity Type" drop down
    And I select "Open" from the "Activity Status" drop down
    And I select "Broker" from the "Department Assigned" drop down
    And I enter "07/06/2019" in the "Need By Date" field
    And I click the "Submit" button
    Then The new activity will be displayed in the Activities tab
    ###---------- Add Activity ----------###

  @6706
  Scenario: Add Follow Up
    ###---------- Add Follow Up ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the newly created activity
    And I open the Add Follow Up form
    And I select "First Call" from the "Follow Up Type" drop down
    And I pick today's date from the "Follow Up Date" date picker
    And I select "Broker" from the "Department Assigned" drop down
    And I select "PMAManager Dev" from the "Assigned To" drop down
    Then The new follow up will be displayed in the grid
    ###---------- Add Follow Up ----------###

  @6707
  Scenario: Add Note to New Business
    ###---------- Add Note ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the Add Note form
    And I enter "999 Test Note Title <current date>" in the "Title" field
    And I enter "999 Test Note Description <current date>" in the "Description" text box
    And I click the "Submit" button
    Then the new note will be displayed in the Notes tab
    ###---------- Add Note ----------###

  @6707
  Scenario: Add Attachment to New Business
    ###---------- Add Attachment ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    ###---------- Add Attachment ----------###

  @6711
  Scenario: Add Opportunity in Business Tab
    ###---------- Add Opportunity ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the Add Opportunity form
    And I select "Untouched" from the "Status" drop down
    And I select "Bumbershoot" from the "Coverage Type" drop down
    And I enter "01012020" in the "Need by Date" field
    And I enter "01012020" in the "Opportunity Received Date" field
    And I enter "999" in the "Current Insurance Carrier" field
    And I enter "999" in the "Current Premium" field
    And I select "Email" from the "Origin" drop down
    And I select "Fully Outsourced" from the "Access Level" drop down
    And I select "Oli Serviced" from the "Service Level" drop down
    And I click the "Submit" button
    Then I open the opportunity
    ###---------- Add Opportunity ----------###

  @6711
  Scenario: PMA - Edit Opportunity in Detail Tab
    ###---------- Edit Opportunity ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the opportunity
    And I verify the submit button is disabled
    And I select "Info Needed" from the "Status" drop down
    And I select "CL - Aviation" from the "Coverage Type" drop down
    And I select "Not sure" from the "When is Coverage Needed?" drop down
    And I pick today's date from the "Need by Date" date picker
    And I enter "999" in the "Current Insurance Carrier" field
    And I pick "2" - "February" - "2020" from the "Renewal Date" date picker
    And I enter "999" in the "Current Premium" field
    And I select "Direct" from the "Access Level" drop down
    And I select "Affiliate Serviced" from the "Service Level" drop down
    And I select "SEM" from the "Prospect Origin" drop down
    And I select "New New" from the "Business Type" drop down
    Then The edits to the opportunity can be saved
    ###---------- Edit Opportunity ----------###

  @6713
  Scenario: PMA - Add Activity to an Opportunity
    ###---------- Add Activity in Opportunity ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the opportunity
    And I open the Add Activity form
    And I select "Call Customer" from the "Activity Type" drop down
    And I select "Open" from the "Activity Status" drop down
    And I select "Broker" from the "Department Assigned" drop down
    And I select "PMAManager Dev" from the "Assigned To" drop down
    And I pick "1" - "January" - "2020" from the "Need By Date" date picker
    Then The activity will display in the Activities tab of this opportunity
    ###---------- Add Activity in Opportunity ----------###

  @6714
  Scenario: PMA - Add Note to an Opportunity
    ###---------- Adding note to opportunity ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the opportunity
    And I open the Add Note form
    And I enter "Opportunity Note Title" in the "Title" field
    And I enter "Opportunity Note body" in the "Description" text box
    And I click the "Submit" button
    Then the new note will be displayed in the Notes tab
    ###---------- Adding note to opportunity ----------###

  @6715
  Scenario: PMA - Add Attachment to an Opportunity
    ###---------- Adding attachment to opportunity ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the opportunity
    And I click on the "Attachments" link
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    ###---------- Adding attachment to opportunity ----------###

  @6716
  Scenario: PMA - Add Claim to an Opportunity
    ###---------- Adding claim to opportunity ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the opportunity
    And I open the Add Claim form
    And I pick "1" - "January" - "2019" from the "Claim Date" date picker
    And I select "Open" from the "Claim Status" drop down
    And I enter "10" in the "Claim Amount" field
    And I enter "999" in the "Description of Claim" text box
    Then The claim will be displayed in the Claims tab of the opportunity
    ###---------- Adding claim to opportunity ----------###

  @6717
  Scenario: PMA - Add Quote to an Opportunity
    ###---------- Adding quote to opportunity ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the opportunity
    And I open the Add Quote form
    And I pick "1" - "January" - "2020" from the "Quote Date" date picker
    And I select "ACE" from the "Carrier" drop down
    And I select "Standard (S)" from the "Carrier Type" drop down
    And I enter "999" in the "Quoted Premium" field
    And I click "Submitted" checkbox
    And I click "Carrier Declined" checkbox
    And I select "Pricing" from the "Declined Reason" drop down
    And I click "Mark as Selected Quote" checkbox
    Then The quote will be displayed in the Quotes tab
    ###---------- Adding quote to opportunity ----------###

  @2958
  Scenario: PMA - KPIs
    ###---------- Increase My Open Opportunities ----------###
    When I get the count for "All" KPI
    And I navigate to the "Business" tab
    And I find the edited business
    And I open the Add Opportunity form
    And I select "Info Needed" from the "Status" drop down
    And I select "CL - Cyber/Internet" from the "Coverage Type" drop down
    And I click the "Submit" button
    And I return to the PMA home page
    Then Verify that "My Open Opportunities" KPI has "Increased"
    ###---------- Increase My Open Opportunities ----------###
    ###---------- Increase My New Opportunities ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the Add Opportunity form
    And I select "Untouched" from the "Status" drop down
    And I select "Life Insurance" from the "Coverage Type" drop down
    And I click the "Submit" button
    And I return to the PMA home page
    Then Verify that "My New Opportunities" KPI has "Increased"
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the Add Opportunity form
    And I select "Review Today" from the "Status" drop down
    And I select "CL - Crime" from the "Coverage Type" drop down
    And I click the "Submit" button
    And I return to the PMA home page
    Then Verify that "My New Opportunities" KPI has "Increased"
    ###---------- Increase My New Opportunities ----------###
    ###---------- Increase My Bound Opportunities ----------###
    When I navigate to the "Business" tab
    And I find the edited business
    And I open the opportunity
    And I add a quote to this opportunity
    And I select "Bound" from the "Status" drop down
    And I pick today's date from the "Effective Date" date picker
    And I enter "50" in the "Commission(%)" field
    And I enter "100" in the "Broker Fee" field
    And I click the "Submit" button
    And I return to the PMA home page
    And Verify that "My Bound Opportunities This Month Count" KPI has "Increased"
    And Verify that "My Bound Opportunities This Month Commission" KPI has "Increased"
    And Verify that "My Bound Opportunities This Year Count" KPI has "Increased"
    And Verify that "My Bound Opportunities This Year Commission" KPI has "Increased"
    ###---------- Increase My Bound Opportunities ----------###

  @2960
  Scenario: PMA - ToDo list KPI
    ###---------- Activities Assigned to Me Today KPI ----------###
    When I go to the To Do List section
    And I get the count for "Activities Assigned To Me Today" KPI
    And I verify the number of records in the grid match the "Activities Assigned To Me Today" KPI
    And I return to the PMA home page
    And I find the edited business
    And I open the Add Activity form
    And I select "HD Outreach Set Up" from the "Activity Type" drop down
    And I select "Broker" from the "Department Assigned" drop down
    And I select "PMAManager Dev" from the "Assigned To" drop down
    And I enter "01012020" in the "Need By Date" field
    And I click the "Submit" button
    And I go to the To Do List section
    Then Verify that "Activities Assigned To Me Today" KPI has "Increased"
    ###---------- Activities Assigned to Me Today KPI ----------###
    ###---------- Follow Ups Assigned to Me Today KPI ----------###
    When I get the count for "Follow Ups Assigned To Me Today" KPI
    And I verify the number of records in the grid match the "Follow Ups Assigned To Me Today" KPI
    And I return to the PMA home page
    And I find the edited business
    And I open the newly created activity
    And I open the Add Follow Up form
    And I select "First Call" from the "Follow Up Type" drop down
    And I pick today's date from the "Follow Up Date" date picker
    And I select "Broker" from the "Department Assigned" drop down
    And I select "PMAManager Dev" from the "Assigned To" drop down
    And The new follow up will be displayed in the grid
    And I go to the To Do List section
    Then Verify that "Follow Ups Assigned To Me Today" KPI has "Increased"
    ###---------- Follow Ups Assigned to Me Today KPI ----------###
    ###---------- Recently Completed Activities KPI ----------###
    When I get the count for "Recently Completed Activities" KPI
    And I verify the number of records in the grid match the "Recently Completed Activities" KPI
    And I return to the PMA home page
    And I find the edited business
    And I open the newly created activity
    And I click "Activity Completed" checkbox
    And I click the "Submit" button
    And I go to the To Do List section
    Then Verify that "Recently Completed Activities" KPI has "Increased"
    ###---------- Recently Completed Activities KPI ----------###
    ###---------- My Reviews KPI ----------###
    When I get the count for "My Reviews" KPI
    And I verify the number of records in the grid match the "My Reviews" KPI
    And I return to the PMA home page
    And I find the edited business
    And I open the newly created activity
    And I click "Review Desired" checkbox
    And I click the "Submit" button
    And I go to the To Do List section
    Then Verify that "My Reviews" KPI has "Increased"
    When I return to the PMA home page
    And I find the edited business
    And I open the newly created activity
    And I click "Review Desired" checkbox
    And I click the "Submit" button
    And I go to the To Do List section
    Then Verify that "My Reviews" KPI has "Decreased"
    ###---------- My Reviews KPI ----------###