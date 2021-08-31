Feature: Mapping TC for Manage Users module

  Scenario: Automating C103, C864, (c104), C1582, C1584, C127, C865, C866, C867, C868, C1585, C1626, C2003, C870, C105

    Given I navigate to login page of cert vault

    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button
    #----Manage Users module------ C104
    And I click on Manage Users tab
    And I click Add User button
      | name       | role               | companytype | companyname | emailaddress                   | pwd      |
      |            | Exception Handling |             |             |                                |          |
      | automation | Super Admin        |             |             | anushae403+suppadmin@gmail.com |          |
      |            | Patra Documents    |             |             |                                |          |
      |            | Cert Uploader      |             |             |                                |          |
      |            | Support            |             |             |                                | Test@123 |
    And I click on Save button in pop-up
    #------C1582, C1584------
    And I click Add User button
      | name | role    | companytype | companyname | emailaddress                   | pwd |
      |      | Support |             |             | anushae403+suppadmin@gmail.com |     |
    And I click on Save button in pop-up
    And I verify validation for Add user pop-up
    And I click on close button in add user form
    #--C103, C864
    #And I click Paper Registration Letters button to generate letters - removed in UI2
    # C868, C866, C865
    When I search with columns in manage users header grid
      | name | account                   | companyname | companyid  |role | lastloginfrom | lastloginto |
      | fire | anushae403+fire@gmail.com |             |  1265      |     |               |             |
    And I mark the user as enable or disable
    #C127
    And I click edit symbol and edit user details
      | name | role | pwd |
      |      |      |     |
    And I click on Save button in edit user pop-up
    #C868, C867
    And I click on Manage Users tab
    And I click edit symbol and edit user details
      | name | role    | pwd |
      |      | Support |     |
    And I verify validation for Edit user pop-up
    #C105,C1626,C869
    When I search with columns in manage users header grid
      | name | account                   | companyname | companyid  |role | lastloginfrom | lastloginto |
      |      | anushae403+fire@gmail.com |             |            |     |               |             |
    When I click on Reset Password button in users page
      | newpwd   | confirmpwd |
      | Test@111 | Test@111   |
    And I enter password confirmation
      | confirmuserpwd |
      | October@123    |
    #And I verify validation for reset failed - removed recently/broken c2003
    #C1585
    And I click on Clear All Filters button manage users
    #C870
    When I search with columns in manage users header grid
      | name | account                   | companyname | companyid  |role | lastloginfrom | lastloginto |
      |      | anushae403+fire@gmail.com |             |            |     |               |             |
    And I click edit symbol and edit user details
      | name       | role            |
      | Patra docs | Patra Documents |
    When I click on Reset Password button in users page
      | newpwd   | confirmpwd |
      | Test@111 | Test@111   |
    And I verify validation for password confirmation
      | confirmuserpwd |
      | invalid        |
    And I click x button to close
   #C1626
    And I click edit symbol and edit user details
      | name   | role  | pwd     |
      | anusha | Admin | Test321 |
   #C1635 locked out accounts and expired accounts from C869
    And I enter login credentials
      | email         | password |
      | test@user.com | Test@123 |
    And I click on Sign In button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button
    And I click on Admin menu
    And I click on Manage Users tab
    When I search with columns in manage users header grid
      | name | account       | companyname | companyid  |role | lastloginfrom | lastloginto |
      |      | test@user.com |             |            |     |               |             |
    And I click edit symbol and edit user details
      | name     | role  | companytype | companyname | emailaddress |
      | test anu | Admin | Admin       | Patra Admin |              |
    When I click on Reset Password button in users page
      | newpwd | confirmpwd |
      | ****** | *****      |
    And I enter password confirmation
      | confirmuserpwd |
      | ******         |
    Then I click on Profile icon and Logout
    And I enter login credentials
      | email         | password |
      | test@user.com | ****     |
    And I click on Sign In button


