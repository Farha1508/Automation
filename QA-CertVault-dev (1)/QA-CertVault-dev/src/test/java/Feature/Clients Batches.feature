Feature: Automate for Clients and Batches page
  @clientbatches
  Scenario: Clients & Batches page - Grid search, View cert, handling Action icons, Batch Detail page search, action column, view icon.

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxx@info.com  | xxxx      |
    And I click on Sign In button

    When I click on Clients link
    And I click favorite icon
 
   #--------Batches-------C5067
    When I click on Batches link
    When I search with columns in batches header grid
      | batchid | batchname | batch_adddate_from | batch_adddate_to | primaryinsured | primaryholder | batch_status | batch_company_group |
      | 181     |           |                    |                  |                |               |              |                     |
    And I click on Batch Id cert
    #C5064
    When I search with columns in batchDetail header grid
      | bd_companyname   | bd_companytype | bd_noofcerts | bd_status |
      | CMD Construction | Insured        | 1            |           |
    And I perform click events in Batch Detail page Action column
    And I click on View icon in Batch Detail page Action column
    #5067
    And I click on cert id to view certificate
      | batchcert_certid | insured          | holder            | issuer                   | expirationdatefrom | expirationdateto | issuedfrom | issuedto | uploaddatefrom | uploaddateto | insuredstatus | holderstatus |
      | 23660            | CMD Construction | Violet Botanicals | brokeruser@patracorp.com |                    |                  |            |          |                |              | Unregistered  | Unregistered |
    #C5066
    When I click on Batches link
    When I search with columns in batches header grid
      | batchid | batchname                          | primaryinsured | primaryholder |
      | 139     | Heffernan Insurance Brokers_anusha |                |               |
    And I click on Clear All Filters button for Batches

   #------Clients module-------
    When I click on Clients link
    #C4870 gridsearch
    When I search with columns in clients header grid
      | cl_companyname      | cl_companytype | cl_adddatefrom   | cl_adddateto  |cl_favorite | cl_compregistered | cl_company_group             |
      | 400 California, LLC | Holder         | 09-02-2020 	    | 09-12-2020   	|No          |                   | Heffernan Insurance Brokers  |
    #C5127
    And I click favorite icon
    #C4871
    And I click on clients company name view
    And I click on cert id to view certificate
      | batchcert_certid | insured          | holder            | issuer                   | expirationdatefrom | expirationdateto | issuedfrom | issuedto | uploaddatefrom | uploaddateto | insuredstatus | holderstatus |
      | 23660            | CMD Construction | Violet Botanicals | brokeruser@patracorp.com |                    |                  |            |          |                |              | Unregistered  | Unregistered |
    And I click on cert Id client list view
    #C4873
    When I click on backward button and verify Clear all functionality


