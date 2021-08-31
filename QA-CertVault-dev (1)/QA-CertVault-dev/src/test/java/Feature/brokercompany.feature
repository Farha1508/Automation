Feature: Automate based on Broker company type
  Scenario: Broker user login, upload, KPI - Certs tab,search cert,view,buttons - User Profile - Logout

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xxxx       |
    And I click on Sign In button

   # ------------Upload Cert module ----------
   And I click on Upload Cert button
   And I choose file to upload
   And I click on OKAY button

   # ----------Certificates -----------------
   And I click on Certificates tab
   And Get the KPI Count
   And I click on Clear All Filters button for Certificates
   And I click on Recent KPI
   When I search with columns in header grid
     | certid  | insured              | holder     | issuer                 | expirationfrom | expirationto | issuedfrom | issuedto | uploaddatefrom | uploaddateto | insuredstatus | holderstatus | revised| renewal | renewalbatch| companygroup|
     | 23576   |                      |            |                        |                |              |            |          |                |              |               |              |        |         |             |             |
   And Verify the Company Group for current broker
   And I click on View Cert button
   And I click on View Supporting Documents button
   And Delete Supporting document button not accessible for users and admin
   And I handle paper registration button functionality
   And I handle email registration button functionality
     | enteremail            |
     | anushae403@gmail.com |
   And I click on Email Cert
   And I verify Delete button is not visible for insured/holder/broker/cert uploader users

   # -----------User Profile module ----------
    And Header title accessibility
    And I click on User Profile tab
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      |             | Test123     | Test123         |
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      | 123         | Test123     | Test123         |
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      | Test123     | Test123     | Test123         |
    Then I click on Profile icon and Logout





