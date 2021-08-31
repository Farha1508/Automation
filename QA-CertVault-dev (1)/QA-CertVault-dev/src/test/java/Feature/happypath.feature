Feature: Automate cv happypath
  Scenario: Login, upload cert, certificates, generate PRL, users, companies, exception handling, clients, batches, support -inprogress

    Given I navigate to login page of cert vault

    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xxxx       |
    And I click on Sign In button
   #C97, C613, C1611
    And I click on Upload Cert button
    And I choose file to upload
    And I click on OKAY button
    And I click on Certificates tab
    And Get the KPI Count
    #C101
    And I click on Clear All Filters button for Certificates
    #C99
    And I click on Recent KPI
    #C705
    And I click on Active KPI
    #C706
    And I click on Expired KPI
    #C1615
    And I sort the columns in Certificates
    #C100
    When I search with columns in header grid
      | certid | insured | holder         | issuer | expirationfrom | expirationto | issuedfrom | issuedto | uploaddatefrom | uploaddateto | insuredstatus | holderstatus | revised | renewal | renewalbatch | companygroup |
      | 31095  |         | Robert Company |        |                |              |            |          |                |              |               |              |         |         |              |              |
    #C5220--- Company group
    And Verify the Company Group for current broker
    And I click on View Cert button
    #C7451
    And I verify fields for single blockchain in view cert page
    #C1758
    And I verify validation for Paper Registration form in view cert
    #C102
    And I handle paper registration button functionality
    #873
    And I verify validation for blank text field in Email registration form
    #C12671
    And I verify already existing email in Email registration form
      | enteremail                                  |
      | sonali.gupta+tu7@jellyfishtechnologies.com  |
    #C12000, C11564
    And I handle blocked email registration functionality
      | enter_blocked_email                          |
      | sonali.gupta+98_h@jellyfishtechnologies.com	 |
    #C871
    And I handle email registration button functionality
      | enteremail                                  |
      | sonali.gupta+tee2@jellyfishtechnologies.com |
    #C12669
    And I verify the functionality of add extra holder or insured in Email registration form
      | enteremail                                    |
      | sonali.gupta+tu110@jellyfishtechnologies.com  |
    #C11994
    And I verify the remove Email functionality
    #C10671, C1638, C12672
    And I verify validation for Email registration form in view cert
    #C11983
    And I handle blocked fax registration functionality
      | enter_blocked_fax |
      | (999) 999-9999    |
    #C875, C11981, C11982, C1638
    And I verify validation for Fax Registration form in view cert
    #C11980
    And I handle fax registration button functionality
      | enterfax   |
      | 9999999990 |
    #C11985
    And I verify the remove Fax functionality
    #C872
    And I click on Email Cert
    # Verifying email notifications
    And I login into gmail account for email notification
    And I verify the email blocked notification
    And I verify the Email Registration letter for Insured and Holder user
    And I verify the email cert notification
    #C874,C875,C11673,C10671
    And I verify validation for Email Certificate form in view cert
    #C5141
    And I edit company group in view cert
    #C1759
    And I click on Add Supporting Documents button
    #C10927
    And I verify validation for blank supporting docs
    And I choose and upload file for Add Supporting docs
    And I click on View Supporting Documents button
    #C1760, C10932
    And I delete view support document
    #now_GeneratePaperReg_letter
    And I click on Admin menu
    And I click on Paper Registration menu
    #C5121, C864
    And I click Paper Registration Letters button to generate letters
    #C103
    And I select Broker Company in Generate Paper letters
    #C1627
    And I click on Admin menu
    And I click on Support tab
    When I search with columns in users list
      | name | account                                    | type | company |
      |      | sonali.gupta+213@jellyfishtechnologies.com |      |         |
    #C1581 delete cert
    And I click on Certificates tab
    And I click on Delete button in Certificates tab

    #----Certificates C1626-----
    And I click on Recent KPI
    And I update email in Email Registration Letter
    And I click on Manage Users tab
    When I search with columns in manage users header grid
      | name | account              | companyname | role |
      |      | updateemail@test.com |             |      |

    #ManageUsers
    #C104
    And I click on Manage Users tab
    And I click Add User button
      | name  | role        | companytype | companyname       | emailaddress        | pwd       |
      | nov25 | Super Admin | Broker      | Grand Canyon Risk | nov25@patracorp.com | Nov25@123 |
    And I click on Save button in pop-up
    #C1582
    And I click on close button in add user form
    #C868, C866, C865
    When I search with columns in manage users header grid
      | name | account                   | companyname       | companyid  |role |
      |      | nov25@patracorp.com       | Grand Canyon Risk |            |     |
    And I mark the user as enable or disable
    #C2003, C105, C1626
    When I search with columns in manage users header grid
      | name | account              | companyname  | companyid  |role |
      |      | venkat2901@gmail.com |              |            |     |
    And I click edit symbol and edit user details
      | name   | role  | pwd  |
      | anusha | Admin | yrdy |
    And I enter password confirmation
      | confirmuserpwd |
      |  888           |
    And I verify validation for reset failed
    #C869
    When I search with columns in manage users header grid
      | name | account                        | companyname   | companyid  |role |
      |      | venkat.srinvasan@patracorp.com |               |            |     |
    And I click edit symbol and edit user details
      | name   | role  | pwd     |
      | anusha | Admin | venkat7 |
    And I enter password confirmation
      | confirmuserpwd |
      | 77            |

    #Companies
    And I click on Admin menu
    And I click on Companies tab
    #C583
    And I click Add Company button
    #C856, C855, C854
    And I fill details for add company
      | companyname | companytype | patraonecomp | address         |
      | name        | Insured     |              | testing address |
    #C584
    When I search with columns in add company header grid
      | companyid | companyname     | companytype |
      |           | Big Mack Towing | Insured     |
    #C861, C859, C863, C862
    And I click edit symbol and edit company values
      | companyname | companytype | patraonecomp | address              |
      |             | Insured     | Company 019  | testing address edit |
    #C1614
    And I click on Clear All Filters button

    #ExceptionHandling
    And I click on Admin menu
    And I click on Exception Handling tab
    #C587
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer  |
      | 1215    |           |         |        |        |
    #C1616
    And I sort the columns in Exception handling
    #C1587
    And I click on Exceptions Clear All Filters button
     #--C585
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer  |
      | 1215    |           |         |        |       |
    And I click on View Cert button in Exception tab
    #C853
    And I verify that elements in page are disabled by default
    #C845
    And I click on Add Policy Information button
    And I click on Edit and fill details
      | policy1number | policycarrier | policy1effdate | policyexpdate |
      | test123       |  test123      | 02/05/2019     | 02/05/2019    |
    #C848
    And I click on Cancel button in exception
    #C845
    And I click on Add Policy Information button
    #C1618
    And click on Save btn
    And click on Save btn
    #C846
    And I verify validation message for exception cert fields

    #clients
    When I click on Clients link
    #C4870 gridsearch
    When I search with columns in clients header grid
      | cl_companyname         | cl_companytype | cl_favorite | cl_compregistered |
      | Just Get It Done, Inc. | Insured        | No          |                   |
    #C5127
    And I click favorite icon
    #C4871 drilldown
    And I click on clients company name view
    When I search with columns in header grid
      | insured                 | holder          | issuer                        | expirationfrom | expirationto | issuedfrom | issuedto | uploaddatefrom | uploaddateto |
      | Just Get It Done, Inc.  | Douglas Elliman | grouptestuser+4@patracorp.com |                |              |            |          |                |              |
    And I click on cert Id client list view
    #C4873
    When I click on backward button and verify Clear all functionality

    #Batches
    When I click on Batches link
    #C5064
    When I search with columns in batches header grid
      | batchid | batchname                              | primaryinsured     | primaryholder        |
      | 5275    | Mount Rushmore Insurance_Administrator | Arlen D. Stone, MD | Robert D. Winter, MD |
    And I click on Batch Id cert
    #C5067
    When I search with columns in batchDetail header grid
      | bd_companyname                           | bd_companytype | bd_noofcerts | bd_status  |
      | Monticelli Painting and Decorating, Inc. | Insured        | 1            | Registered |
    And I perform click events in Batch Detail page Action column
    And I click on View icon in Batch Detail page Action column
    And I click on cert id to view certificate
      | batchcert_certid |
      | 15693            |
    #C5066
    When I click on Batches link
    When I search with columns in batches header grid
      | batchid | batchname                   | primaryinsured                           | primaryholder      |
      | 5251    | Test Broker: Company_anusha | Monticelli Painting and Decorating, Inc. | Factory Lessor LLC |
    And I click on Clear All Filters button for Batches

    #Support
    And I click on Admin menu
    And I click on Support tab
    #C1627
    When I search with columns in users list
      | name        | account                   | type   | company                     |
      | venkatesanu | venkat290update@gmail.com | Broker | Heffernan Insurance Brokers |
    #C1628, C1629
    And I perform click events in Action column
    #C605 btn changed to icon in ui2
    And I click on View Certs btn
    #C588
    When I search with columns in support view cert grid
      | insured                          | holder                         | issuer                 |
      | American Coring and Supply, Inc. | Charles Hall Construction, LLC | e.anusha@patracorp.net |
    And I perform support buttons events available for that cert
    #C836
    When I click on Profile icon and My Profile
    #C2116
    And I edit New Certificate Upload Notification status
    #C582
    Then I click on Profile icon and Logout
  #-------Forgot Password validations----- C1624
    When I click on Forgot Password link
    And I click on submit button
  #-------Forgot Password with unknown account----- C1625
    When I click on Forgot Password link
    And I enter email in Forgot password pop-up
      | forgotpwdemail    |
      | xxx@patracorp.com |
    And I click on submit button
  #-------Forgot Password with valid account-----C1623
    When I click on Forgot Password link
    And I enter email in Forgot password pop-up
      | forgotpwdemail         |
      | venkat290113@gmail.com |
    And I click on submit button

  #--------Register Validation for Access code------C1613
    When I click on Register button
    And I click on View my certs! button
  #--------Register invalid access code (check validation)------C1619
    And I enter access code
      | accesscode |
      | invalid |
    And I click on View my certs! button
  #--------Registered access code (check validation)------C1620
    And I enter access code
      | accesscode |
      | 1GfMJsk   |
    And I click on View my certs! button
   #--- Register new user using access code----C1610
    And I enter access code
      | accesscode |
      | mdPQdY2g   |
    And I click on View my certs! button
    And I fill details in registerInfo page
      | name   | password | confirmpassword |
      | holder |          | test            |

    #--- Email notification-----
    And I login into gmail account for email notification
    #C1745,C11678
    And I verify the Delivery failure email notification
    #C11674,C10871
    And I verify the Email Registration letter for Insured and Holder user
    #C1636
    And I verify the single cert upload notification
    #C7421
    And I verify the renewal cert notification
    #C1623
    And I verify the Forgot Password notification
    #C10943
    And I verify the email cert notification
    #C11469, C11795
    And I verify the email blocked notification
    #C12355, C12436
    And I verify the Claim Access Code Request



























