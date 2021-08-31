
Feature: Automate Multiple Upload File functionality
  @MultipleFileUpload
  Scenario: Login, Multiple File Upload
    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button
    #--C606
    And I click on Upload Cert button
    And i get Files count and upload pdfs in folder






















