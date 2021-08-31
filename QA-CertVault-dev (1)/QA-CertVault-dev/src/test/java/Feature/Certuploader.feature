Feature: Add new user as cert uploader

  Scenario: Login, Add/edit user, login as cert uploader user

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xxxx     |
    And I click on Sign In button


    #--add cert uploader user--
    And I click on Manage Users tab
    #C11751
    And I click Add User button
      | name       | role          | companytype | companyname | emailaddress                  | pwd      |
      | testcertup | Cert Uploader |             |             | anushae403+uploader@gmail.com | Test@123 |
    And I click on Save button in pop-up
    #C11752
    Then I verify validation for Assign companies dropdown
    #C11761
    And I click on Assign companies dropdown
      | assigncompany           |
      | Grand Canyon Insurance - 50  |
    And I click on Save button in pop-up

    #---- Edit section---
    When I search with columns in manage users header grid
      | name             | account    | companyname | companyid  |role           | lastloginfrom | lastloginto |
      | certupautomation |            |             |            | cert uploader |               |             |
    And I click on edit user button
      | name             |
      | certupautomation |
    #C11761
    And I click on Assign companies dropdown
      | assigncompany               |
      | Grand Canyon Insurance      |
      | Heffernan Insurance Brokers |
      | USI Insurance Services      |
    And I click on Save button in pop-up
    And I click on Clear All Filters button manage users
    Then I click on Profile icon and Logout

    #-- Login as cert uploader user---
    And I enter login credentials
      | email                      | password |
      | certuploader@patracorp.com | xxxx       |
    And I click on Sign In button
    #C11753
    And Header title accessibility
    And I click on Active KPI
    And I click on View Cert button
    #C12140
    And I verify Email certificate button is not visible for cert uploader user
    And I click on View Supporting Documents button
    And Delete Supporting document button not accessible for users and admin
    And I verify Delete button is not visible for insured/holder/broker/cert uploader users
    And I click on Certificates tab
    #C11754
    And I click on switch company for cert uploader user
      | switch_company           |
      | USI Insurance Services - 17  |
    Then I click on Profile icon and Logout
