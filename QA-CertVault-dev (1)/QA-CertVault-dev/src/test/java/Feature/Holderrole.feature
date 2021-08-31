Feature: Automate based on Holder role
  Scenario: Holder login - KPI - Certs tab,search cert,view,buttons - User Profile - Logout
            Automated TC C99, C706, C705, C100,

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email       | password |
      | xxx@info.com  | xxxx      |
    And I click on Sign In button
    #-----User header titles verification -----
    #C10103
    And Header title accessibility
   #-----KPI & Header Grid------
    And Get the KPI Count
    #C706
    And I click on Expired KPI
    #C705
    And I click on Active KPI
    #C99
    And I click on Recent KPI
    #C1587
    And I click on Clear All Filters button for Certificates
    #C100
    When I search with columns in header grid for Holder login
      | certid | insured         | broker                      | expirationfrom | expirationto | issuedfrom | issuedto | uploaddatefrom | uploaddateto |
      | 23619   |                |                             |                |              |            |          |                |              |
    #C1763
    And I click on View Cert button
    #C1638
    And Paper registration Email registration and Fax registration buttons do not be accessible for users and admin
    #C1638
    And Add Supporting document button not accessible for users and admin
    #C872, C874, C876, C875, C1745, C10943
    And I click on Email Cert
    And I login into gmail account for email notification
    #C10943
    And I verify the email cert notification
    #C1745
    And I verify the Delivery failure email notification
   #----- View Supporting Documents ---
    #C1760
    And I click on View Supporting Documents button
    #C1638, C10932
    And Delete Supporting document button not accessible for users and admin
    #C1638
    And I verify Delete button is not visible for insured/holder/broker/cert uploader users
    # -----------User Profile module ---------- new ui
    #C836
    When I click on Profile icon and My Profile
    When I click on Profile icon and Claim access code
    #C9777 Success claimed access code
    And I enter access code to claim
      | accesscodeclaim |
      | jJMY6u7g	        |
    Then I click on Submit button to claim
    #C11681 invalid access code
    And I enter access code to claim
      | accesscodeclaim |
      | gdffdwer        |
    Then I click on Submit button to claim
    #C11680 pending access code
    And I enter access code to claim
      | accesscodeclaim |
      | gRR4cahv        |
    Then I click on Submit button to claim
    #C9781 already merged access code
    And I enter access code to claim
      | accesscodeclaim |
      | 15t1o2ic        |
    Then I click on Submit button to claim
    #C13390 validating proposed merges for same company id's
    And I enter access code to claim
      | accesscodeclaim |
      | fX7BSPEN        |
    Then I click on Submit button to claim
    Then I click on Profile icon and Logout

  #Super admin login
    And I enter login credentials
      | email       | password |
      | xxx@info.com  | xxxx      |
    And I click on Sign In button
    And I click on Admin menu
    #----new ui Proposed Company Merge module --------
    And I click on Proposed Company Merge Tab
    #C11612
    And I click on Proposed Company Merge Clear All Filters button
    #C11615
    When I search with columns in header grid for Proposed merge header grid
      | userrequestingmerge | userscompany | userscompanytype | userscompanyid | userscompanytypeaddress  |companytobemerged | companytobemergedtype | companytobemergedtypecompanyid | companytobemergedtypeaddress                  |adddatefrom | adddateto | status  | completedby | requestedby |
      |                     |              | Holder           |    86          |                          |                  | Holder                |   23031                        |  303-B Role Mountain Rd. Greenville SC 81280  |            |           | Pending |            |             |
    And I get the status of proposed company merge
    #C9780 deny scenario
    When I click on deny button from action column
    And I get the status of proposed company merge
    And I click on Proposed Company Merge Clear All Filters button
    When I search with columns in header grid for Proposed merge header grid
      | userrequestingmerge | userscompany | userscompanytype | userscompanyid | userscompanytypeaddress  |companytobemerged | companytobemergedtype | companytobemergedtypecompanyid | companytobemergedtypeaddress                  |adddatefrom | adddateto | status  | completedby | requestedby |
      |                     |              | Holder           |    86          |                          |                  | Holder                |   23031                        |  303-B Role Mountain Rd. Greenville SC 81280  |            |           | Pending |            |             |
    And I get the status of proposed company merge
    #C9780 approve scenario
    When I click on approve button from action column
    And I get the status of proposed company merge

    #C12859
    When I click on Archived Merges
    #C12859
    And I verify archived merges should show only merged or declined records
    And I verify Active proposed Merges should show only pending merges
    Then I click on Profile icon and Logout

   #Holder Admin login
    And I enter login credentials
      | email        | password |
      | xxx@info.com | xxxx     |
    And I click on Sign In button
   ####-----Admin header titles verification-----
   #C1692
    And Header title accessibility
    Then I click on Profile icon and Logout



