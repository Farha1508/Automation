@AddAttachments
Feature: Add Attachment tests

  Background: Login component
    Given Navigate to login page of Loss Runs app in "Patraone Cloud"
    ###---------- Login component ----------###
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- Application Select ----------###
    When I click on the "Work Order Tracking" tile
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed

  @9751
  Scenario: Add an Attachment on the Details Page
    When I Click on Add Work Order button
    And Click on "Submit and Open" button
    When I upload an attachment
    Then The file will be displayed in the Attachments grid

  @9753
  Scenario: Cancel an Attachment on the Details Page
    When I Click on Add Work Order button
    And Click on "Submit and Open" button
    When I cancel an attachment
    Then The file will not be displayed in the Attachments grid

  @9744
  Scenario: Delete an Attachment on the Details Page
    When I Click on Add Work Order button
    And Click on "Submit and Open" button
    When I upload an attachment
    Then The file will be displayed in the Attachments grid
    When I delete the attachment
    Then The file will not be displayed in the Attachments grid

  @9742
  Scenario: Verify Attachments Delete functionality with User
    Given I log out
    When I enter the email and password for the "user role"
    And I click the Sign In button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    When I open "Loss Run" for company "Company 002"
    Then The page for the selected company and service will be displayed
    And If the "Timer Alert" modal is displayed, dismiss it
    And I navigate to the "Open" tab

    When I click on the top work order link
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    And I cannot delete the attachment

  @10183
  Scenario: Sorting for the attachments tab
    And I navigate to the "Open" tab
    When I click on the top work order link
    And I upload an attachment
    And I upload an attachment
    And I upload an attachment
    When I navigate to the "Attachments" tab
    Then I test the sorting of the following columns
      | Date Created |
