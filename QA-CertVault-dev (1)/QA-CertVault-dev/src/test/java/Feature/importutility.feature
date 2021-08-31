Feature: Automating Import Utility module
  Scenario: C12447, C12448, C12450, C12451, C12455, C12456, C12475, C12476, C12534.

    Given I navigate to login page of cert vault

    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button

    And I click on Admin menu
    And I click on Import Utility tab

    When I click on Import Holders button
    #C12448
    And I upload invalid csv file to verify validation
    #C12447
    And I choose csv file to upload
    #C12450
    When I click on Import History link
    #C12451
    When I search with columns in import history page
      | importid | filename     | fromdate | todate | brokercompany                    | uploadedby             | status |
      | 3        | national.csv |          |        | Heffernan Insurance Brokers (SF) | e.anusha@patracorp.net |        |
    And I click on Clear All Filters button for import History

    #C12455 Verify users creation from data to be imported
    And I click on Manage Users tab
    When I search with columns in manage users header grid
      | name | account         | companyname | companyid | role | lastloginfrom | lastloginto |
      |      | email1@test.com |   A         |           |      |               |             |
      |      | email2@test.com |   B         |           |      |               |             |
      |      | email3@test.com |   C         |           |      |               |             |

    #C12456 Verify companies creation from data to be imported
    #C12475 duplicate user
    #C12476 duplicate company
    #C12534 create new record if company+address is unique
    And I click on Admin menu
    And I click on Companies tab
    When I search with columns in add company header grid
      | companyid | companyname | companytype | registered | address | companyrootid | companyrootname | companyhaslogo |
      |           | A           |             |            | streetA |               |                 |                |
      |           | B           |             |            | streetB |               |                 |                |
      |           | C           |             |            | streetC |               |                 |                |

    And I click on Admin menu
    And I click on Import Utility tab
    When I click on Import Users button
    And I choose csv file to upload import users