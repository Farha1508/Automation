@AddAttachments
Feature: Add Attachment tests

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
    #

  @9942
  Scenario: Add an Attachment on the Details Page
    When I click the "Add Work Order" button
    When I click the "Submit and Open" button
    When I upload an attachment
    Then The file will be displayed in the Attachments grid

  @9944
  Scenario: Cancel an Attachment on the Details Page
    When I click the "Add Work Order" button
    When I click the "Submit and Open" button
    When I cancel an attachment
    Then The file will not be displayed in the Attachments grid