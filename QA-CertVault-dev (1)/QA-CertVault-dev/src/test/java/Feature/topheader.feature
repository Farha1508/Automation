Feature: Automate S58 Verify Top Header buttons
  Scenario: Automating C835, C836, C2116, C842, C841, C838, C840, C582.

    Given I navigate to login page of cert vault

    And I click SignIn/Register button
    And I enter login credentials
      | email       | password |
      | xxx@info.com  | xxxx      |
    And I click on Sign In button

    #C835
    When I click on logo in top header
  #  And Header title accessibility
    When I click on Profile icon and My Profile
    #C2116
    And I edit New Certificate Upload Notification status
    #C10883
    When I click on profile icon and user guide
    #C11343
    When I click on profile icon and switch company
    | switch_company                   |
    | Heffernan Insurance Brokers - 6  |
    #C10883
    When I click on profile icon and user guide

    # ---User Profile module --C836
    When I click on Profile icon and My Profile
    #--C2116
    And I edit New Certificate Upload Notification status

    #--C842, C841, C838, C840
   And I click on Reset Password button
    #842 blank fields
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      |             |             |                 |

    And I click on Reset Password button
    #841--when password not match
    And I work on reset password
      | oldpassword    | newpassword | confirmpassword |
      | jftdefault@1  | default@1   | default@12       |

    And I click on Reset Password button
    #9912--incorrect password format
    And I work on reset password
      | oldpassword    | newpassword | confirmpassword |
      | jftdefault  | July123   | July123        |

    And I click on Reset Password button
    #12228---Password Previously Used, Please choose another new password
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      | jftdefault@1   | default@123   | default@123       |

    And I click on Reset Password button
    #838---Incorrect password entered
    And I work on reset password
      | oldpassword | newpassword | confirmpassword |
      | jelly@123    | jftdefault@1   | jftdefault@1      |

    And I click on Reset Password button
    #837---Enter valid password to reset
    And I work on reset password
      | oldpassword       | newpassword | confirmpassword |
      | jftdefault@1    | jftdefault@21   | jftdefault@21       |
    #C582
    Then I click on Profile icon and Logout






