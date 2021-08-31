
Feature: single file upload
  @Uploadcert
  Scenario: Automated C97, C613, C1611, C612, C607, C610, C608

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button
    #C97, C613, C1611
    And I click on Upload Cert button
    And I choose file to upload
    #C5140
    And I verify company groups dropdown while uploading cert
      | companygroups               |
      | Heffernan Insurance Brokers |
    And I choose file to upload
    And I verify company group value in view cert
    And I click on OKAY button
    #---Upload cert --- verify C612
    And I click on Upload Cert button
    And I browse a pdf and refresh the page to verify clear filepath
    # ---Invalid pdf upload-- C607
    And I click on Upload Cert button
    And I choose invalid pdf file to upload
   #--verify upload file is disabled -- C610
    And I click on Upload Cert button
   # And I verify Upload File button is disabled by default - removed in UI
    #C608 - Revised functionality no longer existing
    And I click on Upload Cert button
    And I click on Revision button
    And I search with columns in certificate list
      | uploadedby             | insured | holder              | uploaddatefrom | uploaddateto |
      | e.anusha@patracorp.net |         | 400 California, LLC |                |              |
  #  And I click on View button in certificate list
  #  And I select radio button under Revise column
  #  And I choose file to upload
  #  And I verify for Revised watermark









