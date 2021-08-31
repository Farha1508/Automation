Feature: Mapping TestRail TC for Companies module
  Scenario: Automated C583, C854, C856, C1614, C861, C862, C863, C584, C859, C855, C5629, C6698, C6699, C5629, C5630, C5637, C5219, C5218, C5210,
            C10546, C10797, C10548.

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email       | password |
      | xxxxx@xx.com | xxxx       |
    And I click on Sign In button
    #----new ui Companies module --------
    And I click on Admin menu
    And I click on Companies tab
    #C583
    And I click Add Company button
    #C856, C855
    And I fill details for add company
      | companyname | companytype | patraonecomp | address         |
      | name        | Insured     |              | testing address |
    #C854
    And I verify close button in Add Company form
    #C584
    When I search with columns in add company header grid
      | companyid | companyname              | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      | 10        | USI Insurance Services	 |             |            |         |               |                 |                |
    #C861, C9817, C859
    And I click edit symbol and edit company values
      | companyname | companytype | patraonecomp | address |
      |             | Broker      | Company 002  |         |
    #C863, C862
    And I verify validation for edit company form
    #C6698, C6699
    And I verify logo upload for company and its validation

    #C10546
    And I verify validation for logo image size
    #C1614
    And I click on Clear All Filters button
    #C5629
    When I search with columns in add company header grid
      | companyid | companyname                | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      |           | 101 Montgomery Street Co., |             |            |         |               |                              |                |
    And I select checkboxes for companies to merge
    And I click on Clear All Filters button
    When I search with columns in add company header grid
      | companyid | companyname             | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      |           | Peking Handicraft, Inc. |             |            |         |               |                 |                |
    And I select checkboxes for companies to merge
    And I verify merge companies functionality

    #C5630, C5637
    When I search with columns in add company header grid
      | companyid | companyname                | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      |         | 101 Montgomery Street Co., |       |            |         |  |                              |                |
    And I select checkboxes for companies to merge
    And I verify validation for company merge
    #C5219
    When I click on Add Company Group Icon
    And I verify close button in Add Company Group
    #C5218
    When I click on Add Company Group Icon
    And I verify validation for Add Company Group
    #C5210
    And I verify functionality of add company group
    And I click on Clear All Filters button
    #C10547
    When I search with columns in add company header grid
      | companyid | companyname                 | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      | 8        | Heffernan Insurance Brokers | Broker      |            |         |               |                 |                |
    And I Verify disabling of Insured Notifications in edit company
    #C11424 Notification Failure text field
    And I enter the Notification Failure email address
    #C12143 OCR type selection
    And I select OCR process type
    | ocr_type |
    | Accord         |
    And I click on Certificates tab
    And I click on View Cert button
    #C11673 failure email notification
    And I verify email notification failure address for companies
      | enteremail            |
      | otp3@patracorp.net |
    And I handle paper registration button functionality
    And I click on Admin menu
    And I click on Paper Registration menu
    And I click Paper Registration Letters button to generate letters
    And I select Broker Company in Generate Paper letters
    #C10797 child companies action btn
    When I search with columns in add company header grid
      | companyid | companyname                | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      |           | Peking Handicraft, Inc. |             |               |         |               |                 |                |
    And I click on Child Companies list under action column
    And I click x button to close child companies list pop-up

    #C12588 Patra one company list
    When I search with columns in add company header grid
      | companyid | companyname           | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      |           | Test Disable Broker 2 | Broker      |            |         |               |                 |                |
    When I click on Patra One Company list icon under Action column
    And I enter details in Patra One Company list pop-up
      | companyname      | metername | service | division | branch | department | companyforfilename |
      | Company 040 - 40 | FRENKEL   | DBR     |          |        |            |  FILE              |

    #C10548 Opt-out of using CV
    And I click on opt-out icon under Action column
    And I select broker and submit optout
    #C10566
    And I click on Upload Cert button
    And I choose file to upload
    And I click on Certificates tab
    And I click on View Cert button
    And I handle paper registration button functionality
    And I handle email registration button functionality
      | enteremail            |
      | anushae403@gmail.com |
    And I click on Admin menu
    And I click on Paper Registration menu
    And I click Paper Registration Letters button to generate letters
    And I select Broker Company in Generate Paper letters
    Given I navigate to login page of print shop app
    And I enter login credentials
      | email                             | password |
      | CertVault.Printshop@patracorp.com | xxxx     |
    And I click on Sign In button
    When I select Printshop application box
    And I click on Assigned To Printshop KPI
    When I search with columns in Processing tab
      | service              | mailingtype |
      | Certificate Issuance |             |
    And I click on record id
    And I click on Attachments tab
    And I click on link under Attachments tab



