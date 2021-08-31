Feature: Handling blocked domain page
  Scenario: Login, header grid, clear, add/edit, delete, emailcert functionality,

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xxxx       |
    And I click on Sign In button
    And I click on Admin menu
    And I click on Blocked Domains tab
    #C11372
    When I search with columns in blocked domain header grid
      | id   | domainname                 | company   | status | adddatefrom| adddateto|
      | 1078 | anushae403+block@gmail.com | 3 Eros HO |        | 10-30-2020 |          |
    #C12015
    And I click on Clear All Filters button for Blocked Domains
    #C11373
    And I click on Add Blocked Domain button
    And I enter details in Add Blocked Domain pop-up
      | blockedtype | domainemail | status      | company         |
      | Domain      | gmail.com   | Blocklisted | 3 Eros HO - 153 |
    And I click on Add Blocked Domain Save button
    When I search with columns in blocked domain header grid
      | id | domainname | company | status | adddatefrom | adddateto |
      |    | gmail.com  |         |        |             |           |
    And I click on edit blocked domain
    And I click on Add Blocked Domain Save button

    #C11563 -validations
    And I click on Add Blocked Domain button
    And I click on Add Blocked Domain Save button
    And I enter details in Add Blocked Domain pop-up
      | blockedtype | domainemail  | status            | company         |
      | Domain      | email@tt.com | Allowed - Company | 3 Eros HO - 153 |
    And I click on Add Blocked Domain Save button
    And I enter details in Add Blocked Domain pop-up
      | blockedtype | domainemail | status      | company         |
      | Email       | gmail.com   | Blocklisted | 3 Eros HO - 153 |
    And I click on Add Blocked Domain Save button
    And I enter details in Add Blocked Domain pop-up
      | blockedtype | domainemail                | status | company |
      | Email       | anushae403+block@gmail.com |        |         |
    And I click on Add Blocked Domain Save button
    And I click on x icon for Add blocked Domain form
    #C11564
    When I search with columns in blocked domain header grid
      | id | domainname                | company | status      | adddatefrom| adddateto|
      |    | anushae403+pace@gmail.com |         | Blocklisted |            |          |
    And I click on Certificates tab
    When I search with columns in header grid
      | insured               | holder     | issuer                 | expirationfrom | expirationto | issuedfrom | issuedto | uploaddatefrom | uploaddateto |
      | Madden & Nelson, Inc. | David Hall | e.anusha@patracorp.net |                |              |            |          |                |              |
    And I click on View Cert button
    And I scroll down to the page
    And I handle email registration button functionality
      | enteremail                |
      | anushae403+pace@gmail.com |

    #C11562
    When I search with columns in blocked domain header grid
      | id   | domainname | company | status | adddatefrom| adddateto|
      | 1079 |            |         |        |            |          |
    And I click on blocked Domain Delete icon

    #C12466 duplicate email/domain validation
    When I search with columns in blocked domain header grid
      | id | domainname         | company | status | adddatefrom | adddateto |
      |    | sasswa50@gmail.com |         |        |             |           |
    And I click on edit blocked domain
    And I enter details in Add Blocked Domain pop-up
      | blockedtype | domainemail                | status | company |
      | Email       | anushae403+block@gmail.com |        |         |
    And I click on Add Blocked Domain Save button


    ###### Fax Blocklist ########
    #C12014
    When I click on Fax blocklist button
    When I search with columns in fax blocklist header grid
      | id | faxnumber        | adddatefrom | adddateto  |
      | 13 | 2 (342) 342-3523 | 12-11-2020  | 12-15-2020 |
    #C11956
    And I click on edit icon for fax blocklist
      | faxnumber   |
      | 23423423521 |
    And I click on Add Blocked Domain Save button
    #C12015
    And I click on Clear All Filters button for Blocked Domains
    #C12014
    When I search with columns in fax blocklist header grid
      | id | faxnumber | adddatefrom | adddateto |
      | 10 |           |             |           |
    #C11957
    And I click on Delete icon for Fax Blocklist
    #C11955
    And I click on Add Blocked Domain button
    And I enter fax details in Add Blocked Domain pop-up
      | blockedtype | fax       |
      | Fax         | 23423     |
    And I verify validation for fax
    And I click on x icon for Add blocked Domain form
     #C11954
    And I click on Add Blocked Domain button
    And I enter fax details in Add Blocked Domain pop-up
      | blockedtype | fax         |
      | Fax         | 23423423521 |
    And I click on Add Blocked Domain Save button
    #C12459 Fax already existing record
    And I verify validation for already existing records

