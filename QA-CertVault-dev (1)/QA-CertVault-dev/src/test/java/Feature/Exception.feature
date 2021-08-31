Feature: Mapping TestRail TC for Exception Handling module

  Scenario: Automated C587, C1616, C1587, C585, C853, C845, C1618, C848, C846, C1617.

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xxxx       |
    And I click on Sign In button
    And I click on Admin menu
    And I click on Exception Handling tab
    #C587
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer  | issuefrom  | issueto  | uploaddatefrom | uploaddateto |
      | 33704   |           |         |        |         |            |          |                |              |
    #C1616
    And I sort the columns in Exception handling
    #-- C1587
    And I click on Exceptions Clear All Filters button
    #C585
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer  |
      | 33704    |           |         |        |       |
    And I click on View Cert button in Exception tab
    #C853
    And I verify that elements in page are disabled by default
    #C845
    And I click on Add Policy Information button
    And I click on Edit and fill details
      | policy1number | policycarrier | policy1effdate | policyexpdate |
      | test123       |  test123      | 02/07/2020     | 02/08/2020    |
    #C848
    And I click on Cancel button in exception
    #C845
    And I click on Add Policy Information button
    #C1618
    And click on Save btn
    And click on Save btn
    #C846
    And I verify validation message for exception cert fields
    #C1617
    And I click on Admin menu
    And I click on Exception Handling tab
    And I search with columns in exception grid
      | certid | errortype | insured | holder | issuer |
      | 33703  |           |         |        |        |
    And I click on View Cert button in Exception tab
    And I click on Delete button






