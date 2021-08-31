Feature: CV negative test cases
  @NegativeTest
  Scenario: Login page validations
    Given I navigate to login page of cert vault

    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button
   #----Certs C1615----
    And I click on Certificates tab
    And I sort the columns in Certificates
   #----Certificates C1626-----
    And I click on Upload Cert button
    And I choose file to upload
    And I click on OKAY button
    And I click on Certificates tab
    And I click on Recent KPI
    And I update email in Email Registration Letter
    And I click on Manage Users tab
    When I search with columns in manage users header grid
      | name | account                   | companyname       | companyid  |role |
      |      | updateemail@test.com      |                   |            |     |
    #---Upload cert --- verify C612
    And I click on Upload Cert button
    And I browse a pdf and refresh the page to verify clear filepath
    # ---Invalid pdf upload-- C607
    And I click on Upload Cert button
    And I choose invalid pdf file to upload
    #verify upload file is disabled -- C610
    And I click on Upload Cert button
    And I verify Upload File button is disabled by default
    #------Manage Users C1582, C1584-------
    And I click on Manage Users tab
    And I click Add User button
      | name     | role        | companytype | companyname       | emailaddress           | pwd |
      |          | Super Admin | Broker      | Grand Canyon Risk | e.anusha@patracorp.com |     |
    And I click on Save button in pop-up
    And I click on close button in add user form
    #------ Manage Users C868-------
    And I click on Manage Users tab
    When I search with columns in manage users header grid
      | name | account                     | companyname | companyid  |role | lastloginfrom | lastloginto |
      |      | automatedscript@testing.com |             |            |     |               |             |
    And I click edit symbol and edit user details
      | name     | role | emailaddress |
      | automate | User | email@com    |
    And I click on close button in add user form
    #-------Manage Users C870--------
    And I click on Manage Users tab
    When I search with columns in manage users header grid
      | name | account                     | companyname  | companyid  |role | lastloginfrom | lastloginto |
      |      | automatedscript@testing.com |              |            |     |               |             |
    And I click edit symbol and edit user details
      | name | role  | emailaddress                | pwd      |
      | aaa  | Admin | automatedscript@testing.com | Test3211 |
    And I enter password confirmation
      | confirmuserpwd |
      | incorrect pwd  |
    #------Manage User - reset password functionality----C869,C1635
    And I click on Manage Users tab
    When I search with columns in manage users header grid
      | name | account               | companyname | companyid  |role | lastloginfrom | lastloginto |
      |      | venkat54321@gmail.com |             |            |     |               |             |
    And I click edit symbol and edit user details
      | name        | role | emailaddress          | pwd  |
      | anusha test | User | venkat54321@gmail.com | xxxx |
    And I enter password confirmation
      | confirmuserpwd |
      | xxx            |
    Then I click on Profile icon and Logout
    And I enter login credentials
      | email                 | password   |
      | venkat54321@gmail.com | xxxxx  |
    And I click on Sign In button
     #-----Manage users C2003----
    And I click on Manage Users tab
    When I search with columns in manage users header grid
      | name | account                     | companyname | companyid  |role | lastloginfrom | lastloginto |
      |      | automatedscript@testing.com |             |            |     |               |             |
    And I click edit symbol and edit user details
      | name | role  | pwd      |
      | aaa     | Admin | Test321  |
    And I enter password confirmation
      | confirmuserpwd |
      | aaa            |
    And I click x button to close
    #---- Verify login for disabled account-------C1732
    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxx@xxxx.com | xxxx     |
    And I click on Sign In button
    And I click on Manage Users tab
    And I click on Clear All Filters button
    When I search with columns in manage users header grid
      | name | account         | companyname              | companyid  |role  | lastloginfrom | lastloginto |
      |      | patra@patra.com | Mount Rushmore Insurance |            | User |               |             |
    And I mark the user as enable or disable
    Then I click on Profile icon and Logout
    And I enter login credentials
      | email           | password |
      | patra@patra.com | xxxxx    |
    And I click on Sign In button
    # -------- Exception Handling --------C1587
    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email         | password |
      | mount@xxx.com | xxxx     |
    And I click on Sign In button
    And I click on Exception Handling tab
    And I search with columns in exception grid
      | certid | errortype                                      | insured | holder                | issuer               |
      | 1597   | OCR Partial Failure/ Database Validation Error |         | Sunbelt Rentals, Inc. | system@patracorp.com |
    And I click on Clear All Filters button
    # -------- Exception Handling sort--------C1616
    And I click on Exception Handling tab
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer |
      | 100    |           |         |        |        |
    And I sort the columns in Exception handling
    #---- Exception C845 ------
    And I click on Exception Handling tab
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer |
      | 594    |           |         |        |        |
    And I click on View Cert button in Exception tab
    And I click on Add Policy Information button
   # ----------- Exception Handling module --------C585, C853, C1618
    And I click on Exception Handling tab
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer |
      | 5747   |           |         |        |        |
    And I click on View Cert button in Exception tab
    And I verify that elements in page are disabled by default
    And I click on Edit and fill details
      | policy1number | policycarrier | policy1effdate | policyexpdate |
      | test123       | test123       | 02/05/2019     | 02/05/2019    |
    And click on Save btn
     # -------- Exception Handling --------C845, C848, C846
    And I click on Exception Handling tab
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer |
      | 594    |           |         |        |        |
    And I click on View Cert button in Exception tab
    And I click on Edit and fill details
      | policy1number | policycarrier | policy1effdate | policyexpdate |
      | test123       | test123       | 02/05/2019     | 02/05/2019    |
    And I click on Cancel button in exception
    And click on Save btn
    And click on Save btn
    #-----Verify logo click ---C835
    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email               | password |
      | mount@xxx.com | xxxx     |
    And I click on Sign In button
    When I click on logo in top header
    #---- Verify User Locked by making login attempt upto 5 times-------C1634
    Given I navigate to login page of cert vault
    And I click SignIn/Register button
  # Attempt 1 :
    And I enter login credentials
      | email          | password |
      | heffxxx@info.com | 55       |
    And I click on Sign In button
  # Attempt 2 :
    And I enter login credentials
      | email          | password |
      | hefxxf@info.com | 555      |
    And I click on Sign In button
  # Attempt 3 :
    And I enter login credentials
      | email          | password |
      | heffxx@info.com | 5556     |
    And I click on Sign In button
  # Attempt 4 :
    And I enter login credentials
      | email           | password |
      | heffxx@infoa.com | 555      |
    And I click on Sign In button
  # Attempt 5 :
    And I enter login credentials
      | email          | password |
      | heffxx@info.com | 555      |
    And I click on Sign In button




