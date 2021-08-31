Feature: Automate based on Support Role

  @Supportrole
  Scenario: Support login, upload, KPI - Certs tab,search cert,view,buttons - User Profile - Logout.
  Automated C1627, C1628, C1629, C605, C588 (c1586)

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxx@info.com  | xxxx    |
    And I click on Sign In button

  # -----------Support module - Performing search filter, button handling--------------newui
    And I click on Admin menu
    And I click on Support tab
    #--C1627
    When I search with columns in users list
      | name | account  | accesscode | type | company | companyid | addedby                | addedondatefrom |addedondateto| expirationdatefrom |expirationdateto | registered |
      |      | 6U93b4oB | 6U93b4oB   |      |         |           |                        |                 |             |                    |                 |            |
    #C1628, C1629, C6702
    And I perform click events in Action column
    #--C605 btn changed to icon in ui2
    And I click on View Certs btn
    #C588
    When I search with columns in support view cert grid
      | certid | insured | holder                   | issuer | expirationdatefrom | expirationdateto | issuedfrom | issuedto   | uploaddatefrom | uploaddateto |
      | 23095  |         | Hillsborough County BOCC |        | 01-01-2022         | 01-01-2022       | 11-20-2020 | 11-20-2020 | 11-24-2020     | 11-24-2020   |
    And I perform support buttons events available for that cert

    # -----------User Profile module ---------- new ui
    When I click on Profile icon and My Profile
    And I edit New Certificate Upload Notification status

    And I click on Reset Password button
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      |             | Test123     | Test123         |
    And I click on Reset Password button
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      | 123         | Test123     | Test123         |
    And I click on Reset Password button
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      | 888         | Test123     | Test123         |
    Then I click on Profile icon and Logout


