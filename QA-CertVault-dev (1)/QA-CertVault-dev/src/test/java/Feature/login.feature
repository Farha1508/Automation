Feature: Automate login test run functionality
   Scenario: Positive and Negative scenarios for Login, Register and Forgot Password modules.

    Given I navigate to login page of cert vault
    And I click SignIn/Register button

    #C614 valid login  --- new ui2
    And I enter login credentials
      | email       | password |
      | xxx@xxx.xxx | xxx      |
    And I click on Sign In button
    #C615 invalid user
    And I enter login credentials
      | email                    | password |
      | rajendraya@patracorp.com | 111      |
    And I click on Sign In button
    #C616 invalid pwd
    And I enter login credentials
      | email                        | password   |
      | rajendra.yarra@patracorp.com | invalidpwd |
    And I click on Sign In button
    #C617 user empty
    And I enter login credentials
      | email | password |
      |       | nov      |
    And I click on Sign In button
    #C618 pwd empty
    And I enter login credentials
      | email               | password |
      | nov25@patracorp.com |          |
    And I click on Sign In button
    #C1634 Too many Attempts
    And I enter login credentials
      | email            | password |
      | anusha@patra.com | nov27    |
    And I click on Sign In button
    #C1732 disabled account login
    And I enter login credentials
      | email               | password |
      | mount@patracorp.com | mount    |
    And I click on Sign In button
   #C1635 locked out accounts and expired accounts from C869
    And I enter login credentials
      | email                     | password |
      | hema.sundar@patracorp.com | name    |
    And I click on Sign In button

    When I click on Register button
   #--------Registered access code (check validation)------C1620
    And I enter access code
      | accesscode |
      | Csj2Vqn8   |
    And I click on View my certs! button

   #--C1619 Invalid access code
    And I enter access code
      | accesscode        |
      | rx4yqDug_fF2\g4nV |
    And I click on View my certs! button
    #--- Register new user using access code----C1610, C1613, C1620, C2002
    And I enter access code
      | accesscode       |
      | 6U93b4oB	 |
    And I click on View my certs! button
     #C5184 T&C validation
    And I fill details in registerInfo page
      | name          | password | confirmpassword | address                 | email                    |
      | test register | test     | test            | test address automation | testemail+01@automation.com |
    #C5183 registration process
    And I click on Terms and Conditions hyperlink
    And I accept the checkbox terms and conditions
    Then I click on Profile icon and Logout
    #-------Forgot Password validations----- C1624
     And I click SignIn/Register button
     When I click on Forgot Password link
     And I click on submit button
    #And I click on cancel button - Removed in UI2-C620
    #-------Forgot Password with unknown account----- C1625
     And I enter email in Forgot password pop-up
       | forgotpwdemail  |
       | bhanu@gmail.com |
     And I click on submit button
    #-------Forgot Password with valid account-----C1623
     And I enter email in Forgot password pop-up
       | forgotpwdemail         |
       | anushae403+sasi@gmail.com |
     And I click on submit button









