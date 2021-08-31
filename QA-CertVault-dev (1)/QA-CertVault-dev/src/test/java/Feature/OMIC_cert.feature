Feature: Upload an OMIC certificates

  Scenario: Login, upload omic cert, switch broker as omic, OCR type omic, verify details

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password   |
      | xxxxx@xx.com | xxxx       |
    And I click on Sign In button
    When I click on profile icon and switch company
      | switch_company                               |
      | Ophthalmic Mutual Insurance Company - 20503  |
    And I click on Admin menu
    And I click on Companies tab
    When I search with columns in add company header grid
      | companyid | companyname                         | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      | 20503     | Ophthalmic Mutual Insurance Company | Broker      |            |         |               |                 |                |
    And I select OCR process type
      | ocr_type |
      | OMIC     |
    And I click on Upload Cert button
    #C12452
    And I choose file to upload
    And I verify text message in certificate uploaded successfully pop-up
    #C11984
    And I enter Fax in post cert upload popup
      | fax          |
      | 98765432456	 |
    #C11760
    And I enter Email in post cert upload popup
      | email                                        |
      | sonali.gupta+thye13@jellyfishtechnologies.com|
    #C12673
    And I click on OKAY button
    #12470,C12472,C12473,C12474
    And I verify extract details for ins/hol/broker/issued/exp date in view cert from OMIC
    And I click on Upload Cert button
    #C12453
    And I choose renewal file to upload
    And I click on OKAY button
