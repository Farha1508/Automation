
Feature: Automate based on Insured role
  @Insuredrole
  Scenario: Insured login - KPI - Certs tab,search cert,view,buttons - User Profile - Logout

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email         | password |
      | xxx@info.com  | xxxx      |
    And I click on Sign In button
    #C10091
    And Header title accessibility
    And Get the KPI Count
    And I click on Expired KPI
    And I click on Active KPI
    And I click on Recent KPI
    And I click on Clear All Filters button for Certificates
    When I search with columns in header grid for Insured login
      |certid   | broker                      | holder       | expirationfrom | expirationto | issuedfrom | issuedto | uploaddatefrom | uploaddateto |
      |1250     | Heffernan Insurance Brokers | Maharam      |                |              |            |          |                |              |
    And I click on View Cert button
    And Paper registration Email registration and Fax registration buttons do not be accessible for users and admin
    And Add Supporting document button not accessible for users and admin
    And I click on Email Cert
    And I login into gmail account for email notification
    And I verify the email cert notification
    And I verify the Delivery failure email notification
    And I click on View Supporting Documents button
    And Delete Supporting document button not accessible for users and admin
    And I verify Delete button is not visible for insured/holder/broker/cert uploader users

  # -----------User Profile module ---------- new ui
    When I click on Profile icon and My Profile
    #C9781
    When I click on Profile icon and Claim access code
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
    #C9777 Success claimed access code
    And I enter access code to claim
      | accesscodeclaim |
      | yZ9Yn3wP        |
    Then I click on Submit button to claim
    Then I click on Profile icon and Logout

    #Super admin login
    And I enter login credentials
      | email       | password |
      | xxxxxxxxxxx | xxxxx@12 |
    And I click on Sign In button
    And I click on Admin menu
    #----new ui Proposed Company Merge module --------
    And I click on Proposed Company Merge Tab
    And I click on Proposed Company Merge Clear All Filters button
    When I search with columns in header grid for Proposed merge header grid
      | userrequestingmerge | userscompany | userscompanytype | userscompanyid | userscompanytypeaddress  |companytobemerged | companytobemergedtype | companytobemergedtypecompanyid | companytobemergedtypeaddress                  |adddatefrom | adddateto | status  | completedby | requestedby |
      |                     |              | Holder           |    86          |                          |                  | Holder                |   23031                        |  303-B Role Mountain Rd. Greenville SC 81280  |            |           | Pending |            |             |
    #C9780
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


    #Insured Admin login
    And I enter login credentials
      | email        | password |
      | xxx@info.com | xxxx     |
    And I click on Sign In button
   ####-----Admin header titles verification-----
    #C1694
    And Header title accessibility
    Then I click on Profile icon and Logout


