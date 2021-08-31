Feature: Upload & print, PrintShop Integration with CertVault
  Scenario: handling Upload & print, PS login, record, attachment view.

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button

    And I click on Upload and Print tab
    And I click on Upload and send mail button
    #C9895
    And i verify validation for checking flags
    #C9822
    And I check Insured mail or Holder mail checkbox
    And I choose renewal file to upload
    And I click on OKAY button
   #C9904
    Given I navigate to login page of print shop app
    And I enter login credentials
      | email                       | password |
      | CertVault.Printshop@patracorp.com | xxxx      |
    And I click on Sign In button
    When I select Printshop application box

    And I click on Assigned To Printshop KPI
    When I search with columns in Processing tab
      | service              | mailingtype |
      | Certificate Issuance | CertVault   |
    And I click on record id
    And I click on Attachments tab
    #C9908, C10566
    And I click on link under Attachments tab







