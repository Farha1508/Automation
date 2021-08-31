# new feature
# Tags: optional

Feature: More test cases to automate

  Scenario: Few more scenarios

    Given I navigate to Oli website
    And I enter the below values in oli website
      | coverageneeded  | classofbusiness | olizipcode |
      | Commercial Auto | Animal Services | 1234       |
    And I enter the following details in oli website
      | olifname | olastname | obusinessname | olocationadd | ocity | ostate | oemai         | ophone     | oaccess          | oservice           | oservicer  | onotes |
      | test     | test      | 999aug18      | US           | US    | US     | test@test.com | 1234567890 | Fully Outsourced | Affiliate Serviced | Greg North | 999    |
    Given I navigate to login page
    And I enter login details
      | email | password |
      | xxxxx | xxxx     |
    And I click on Sign In button
    When I select WO Tracking application box
   #lead assignment
    And I click on Lead Assignment on top grid
    And I enter business name in business column
      | leadbusinessname |
      | 999aug18         |
    And I select broker

    #PMA verification
    And I clicks Home button
   # And user clicks business grid
    And user searches with the following
      | businessname |
      | 999aug18     |

    #Tech Debt 2019
    #C1927,C1933
    And user selects coverage under opportunities tab
    And user selects the status to Bound
    And I click on Add Quote under To Do List page
      | quotedate  | carrier   | quotedpremium | carrierdeclined | declinedreason |
      | 12/07/2018 | All Risks | $2500         |                 | Pricing        |
    And user edits the following opportunity details
      | status | coveragetype | wheniscoverageneeded | needbydate | opportunityreceiveddate | broker     | assigneddate | currentinsurancecarrier | renewaldate | currentpremium | accesslevel | servicelevel       | prospectorigin | businesstype    | accountmanager | effectivedate | commission | brokerfee |
      | Bound  | AD&D         | Not sure             | 08/06/2019 | 08/06/2019              | Prathima M | 08/06/2019   | 999                     |             | 12             | Direct      | Affiliate Serviced | SEM            | Renewal Rewrite | Prathima M     | 08/16/2019    | 1          | 1         |
    And I verify the My Bound opportunities KPI for This month and This year

    #AMA verification
    And I click on Account Management on top grid
    And I verify the businessname in AMA
      | amabusinessname |
      | 999aug18        |
    And I clicks Home button
    Then I Click on Logout

 # Microsites to Lead assignment for non oli
    Given I navigate to foley website
    And I enter the below values in non oli website
      | zipcode | classofbusiness    | coverageneeded | referrernamesentry |
      | 1234    | Contractor - Other | Urgently today | 123                |
    And I enter the following details in non oli website
      | rank | firstname | lastname | email         | businessname | phonenumber | locationaddress | city | state | zip  | website | descp | currentinsurance | renewaldate | grosssale | payroll | fulltimeemployee | parttimeemployee | fein | typeoforg | yearsinbusines | yearsofexp | activeowners | claims | notes |
      | 999  | test      | test     | test@test.com | 999aug181    | 1234567890  | US              | US   | US    | 1234 | test    | 123   | 999              | 01/08/2019  | 999       | 999     | 1                | 2                | 1    | other     | 1              | 1          | 1            | No     | 999   |
    And I enter details in foley oppurtunity page
    #Login into pma
    Given I navigate to login page
    And I enter login details
      | email | password |
      | xxxxx | xxxx     |
    And I click on Sign In button
    When I select WO Tracking application box
    #lead assignment
    And I click on Lead Assignment on top grid
    And I enter business name in business column
      | leadbusinessname |
      | 999aug181        |
    And I select broker
    #PMA verification
    And I clicks Home button
    And user searches with the following
      | businessname |
      | 999aug181    |
    And I clicks Home button

    #PMA timer
    #C1989
    And I click on start timer and verify for clear and close functionalities
    And I select company name
      | timercompany | timerbrandpma | timersubbrandpma | timerservice | timertaskpma |
      | Company: 2   | Heff Direct   | Bold Penguin     | Fulfillment  | Follow Up    |
    And I click on start in time tracking window
    And user clicks add business button
    And user enters the following business details
      | brand       | subbrand     | businessname | businessclass | clientid | broker     | referrername | referringcompany | prospectorigin | needbydate | yearsinbusiness | experience | website       | typeoforganization | ifotherpleasespecify | descriptionofoperations | estannualgrosssales | estannualpayroll | noofactiveowners | fte | pte | fein      | coveragetype |
      | Heff Direct | Bold Penguin | 999aug22     | Homecare      |          | Prathima M |              | ABCD company     | Call In        |            | 22              | 11         | www.gmail.com | Corporation        |                      | desc of ops             | 2563                |                  | 250              | 20  | 15  | FEIN112j3 | DIC          |
    And user clicks submit button

    #Tech Debt 2019
    #C1922,C1924,C1688
    And I verify My open opportunities and my new opportunities KPI

   #Bound BOR checkbox functionality
    And I clicks Home button
    And user selects coverage under opportunities tab

    #Tech Debt 2019
    #C1988
    And I select Bound BOR checkbox to verify that add quote window is disabled
    And I enter the below mandatory fields for bound BOR
      | status | accesslevel | servicelevel       | businesstype    | accountmanager |
      | Bound  | Direct      | Affiliate Serviced | Renewal Rewrite | Prathima M     |
    And user clicks opportunity detail submit button

   #Delete Quote for PMA manager role
    And I clicks Home button
    And user selects coverage under opportunities tab
    And I click on Add Quote section
      | quotedate  | carrier | quotedpremium | carrierdeclined | declinedreason |
      | 12/28/2018 | AIG     | $2800         |                 | Pricing        |

     #Sprint Changes
    #Tech Debt 2019
    #C1812
    And I Verify the delete quote functionality for PMA manager role
    Then I Click on Logout

  #Delete Quote for support role
    Given I navigate to login page
    And I enter login details
      | email | password |
      | xxxxx | xxxx     |
    And I click on Sign In button
    When I select WO Tracking application box
    And I verify for Lead assignment link for support role
    And user selects coverage under opportunities tab
    And I click on Add Quote section
      | quotedate  | carrier | quotedpremium | carrierdeclined | declinedreason |
      | 12/28/2018 | AIG     | $2800         |                 | Pricing        |

     #Sprint Changes
    #Tech Debt 2019
    #C2001 #C1953
    And I Verify the delete quote functionality for Support role

    #Imported Leads tab
    And I clicks Home button
    #Tech Debt 2019
    #C1836
    And user clicks add business button
    And user enters the following business details
      | brand       | subbrand     | businessname | businessclass | clientid | broker     | referrername | referringcompany | prospectorigin | needbydate | yearsinbusiness | experience | website       | typeoforganization | ifotherpleasespecify | descriptionofoperations | estannualgrosssales | estannualpayroll | noofactiveowners | fte | pte | fein      | coveragetype |
      | Heff Direct | Bold Penguin | 999Aug18     | 999 Apr19 BC  |          | Prathima M |              | ABCD company     | Call In        | 05/02/2018 | 22              | 11         | www.gmail.com | Corporation        |                      | desc of ops             | 2563                | 256000           | 250              | 20  | 15  | FEIN112j3 | DIC          |
    And user clicks submit button
    And user searches with the following
      | businessname |
      | 999Aug18     |
    And user selects coverage under opportunities tab
    And I change the status to Imported and verify in imported leads tab

    #C1846
    And I clicks Home button
    And I click Reset button
    And user clicks add business button
    And user enters the following business details
      | brand       | subbrand     | businessname | businessclass | clientid | broker     | referrername | referringcompany | prospectorigin | needbydate | yearsinbusiness | experience | website       | typeoforganization | ifotherpleasespecify | descriptionofoperations | estannualgrosssales | estannualpayroll | noofactiveowners | fte | pte | fein      | coveragetype |
      | Heff Direct | Bold Penguin | 999Aug181    | 999 Apr19 BC  |          | Prathima M |              | ABCD company     | Call In        | 05/02/2018 | 22              | 11         | www.gmail.com | Corporation        |                      | desc of ops             | 2563                | 256000           | 250              | 20  | 15  | FEIN112j3 | DIC          |
    And user clicks submit button
    And user searches with the following
      | businessname |
      | 999Aug181    |
    And user selects coverage under opportunities tab
    And I change the status to Untouched and verify that lead is in opportunity tab
    Then I Click on Logout

    #-----Heffernan  Role-----#
    # Tech Debt 2019
    # C1957
    #non visibility of Lead assignment link for heffernan role
    Given I navigate to login page
    And I enter login details
      | email | password |
      | xxxxx | xxxx     |
    And I click on Sign In button
    When I select WO Tracking application box

    And user clicks add business button
    And user enters the following business details
      | brand                    | subbrand     | businessname | businessclass   | clientid | broker     | referrername | referringcompany | prospectorigin | needbydate | yearsinbusiness | experience | website       | typeoforganization | ifotherpleasespecify | descriptionofoperations | estannualgrosssales | estannualpayroll | noofactiveowners | fte | pte | fein      | coveragetype |
      | Heffernan Transportation | BP Transport | 999Aug02619  | Animal Services |          | Prathima M |              | ABCD company     | Call In        | 05/02/2018 | 22              | 11         | www.gmail.com | Corporation        |                      | desc of ops             | 2563                | 256000           | 250              | 20  | 15  | FEIN112j3 | DIC          |
    And user clicks submit button

    And I capture screen shot
    Then I Click on Logout

 #-----Oli manager Role-----#

    #Tech Debt 2019
    # C1949 #C1950
    #other brands global search for oli manager role
    Given I navigate to login page
    And I enter login details
      | email | password |
      | xxxxx | xxxx     |
    And I click on Sign In button
    When I select WO Tracking application box

    And I verify the Oli brand records in opportunity tab
    And user searches with the following global value
      | globalsearch |
      | heffernan    |
    And I verify the search result
    Then I Click on Logout

    #-------To Do list KPI-------#
    #C1696,C1699,C1701,C1703
    Given I navigate to login page
    And I enter login details
      | email | password |
      | xxxxx | xxxx     |
    And I click on Sign In button
    When I select WO Tracking application box
    And I click on To Do List link
    And I verify the activities kpi
    And I clicks Home button
    And user clicks business grid
    And I select business in business grid
    And user clicks business add activity button
    And user adds the following activity details
      | activitytype | activitystatus | departmentassigned | assignedto | needbydate | activityperformed | activitycompleted | followuprequired | reviewdesired | description              |
      | Proposal     | Open           | Broker             | Prathima M | 02/08/2018 |                   |                   |                  |               | Automation Testing Dec25 |
    And user clicks business add activity submit button
    Then user should see the record under business activities tab
    And I click on To Do List link
    And I verify the activities kpi

    #C1699
    And I click on To Do List link
    And I verify follow ups KPI
    And I select activity type record
    And I click on Add Followup button under Followups section
    And I enter follow up details
      | followuptype      | followupdate | deptassigned | assignedto |
      | AMS Policy Detail | 09/18/2018   | Broker       | Prathima M |
    And I click on To Do List link
    And I verify follow ups KPI

    #C1703
    And I click on To Do List link
    And I verify recently completed KPI
    And I select activity type record
    And I click on activity completed checkbox
    And I verify recently completed KPI

    #C1701
    And I click on To Do List link
    And I verify my reviews KPI
    And I select activity type record
    And I click on review desired checkbox
    And I verify my reviews KPI
    Then I Click on Logout

    #C2011
    #Special project team role verification
    Given I navigate to login page
    And I enter login details
      | email | password |
      | xxxxx | xxxx     |
    And I click on Sign In button
    When I select WO Tracking application box
    And user clicks business grid
    And user selects business in business grid
    And user clicks business add activity button
    And user adds the following activity details
      | activitytype | activitystatus | departmentassigned   | assignedto | needbydate | activityperformed | activitycompleted | followuprequired | reviewdesired | description              |
      | Proposal     | Open           | Special Project Team | Test4 Test | 02/08/2018 |                   |                   |                  |               | Automation Testing Dec25 |
    And user clicks business add activity submit button
    Then user should see the record under business activities tab
    Then I Click on Logout

    #C1978
    #Microsites duplicate business verification
    Given I navigate to Oli website
    And I enter the below values in oli website
      | coverageneeded  | classofbusiness | olizipcode |
      | Commercial Auto | Animal Services | 1234       |
    And I enter the following details in oli website
      | olifname | olastname | obusinessname | olocationadd | ocity | ostate | oemai         | ophone     | oaccess          | oservice           | oservicer  | onotes |
      | test     | test      | 999aug1920192 | US           | US    | US     | test@test.com | 1234567890 | Fully Outsourced | Affiliate Serviced | Greg North | 999    |
    And I click on yes in coverage alert window for duplicate businessname

    #C1982
    Given I navigate to Oli website
    And I enter the below values in oli website
      | coverageneeded  | classofbusiness | olizipcode |
      | Commercial Auto | Animal Services | 1234       |
    And I enter the following details in oli website
      | olifname | olastname | obusinessname | olocationadd | ocity | ostate | oemai         | ophone     | oaccess          | oservice           | oservicer  | onotes |
      | test     | test      | 999aug1920192 | US           | US    | US     | test@test.com | 1234567890 | Fully Outsourced | Affiliate Serviced | Greg North | 999    |
    And I click on no in coverage alert window for duplicate businessname

    #C1966
    Given I navigate to foley website
    And I enter the below values in non oli website
      | zipcode | classofbusiness    | coverageneeded | referrernamesentry |
      | 1234    | Contractor - Other | Urgently today | 123                |
    And I enter the following details in non oli website
      | rank | firstname | lastname | email      | businessname  | phonenumber | locationaddress | city | state | zip | website | descp | currentinsurance | renewaldate | grosssale | payroll | fulltimeemployee | parttimeemployee | fein | typeoforg | yearsinbusines | yearsofexp | activeowners | claims | notes |
      | 999  | 999       | 999      | 999@99.com | 999apr19 2019 | 2113232323  | US              | US   | US    |     | test    |       |                  |             |           |         |                  |                  |      |           |                |            |              |        | 999   |
    #And I enter details in foley oppurtunity page
    And I click on yes in coverage alert for foley website

    #C1968
    Given I navigate to foley website
    And I enter the below values in non oli website
      | zipcode | classofbusiness    | coverageneeded | referrernamesentry |
      | 1234    | Contractor - Other | Urgently today | 123                |
    And I enter the following details in non oli website
      | rank | firstname | lastname | email      | businessname  | phonenumber | locationaddress | city | state | zip | website | descp | currentinsurance | renewaldate | grosssale | payroll | fulltimeemployee | parttimeemployee | fein | typeoforg | yearsinbusines | yearsofexp | activeowners | claims | notes |
      | 999  | 999       | 999      | 999@99.com | 999apr19 2019 | 2113232323  | US              | US   | US    |     | test    |       |                  |             |           |         |                  |                  |      |           |                |            |              |        | 999   |
    #And I enter details in foley oppurtunity page
    And I click on no in coverage alert for foley website

    #C1951,#C1952
    #Heffernan global search for heff direct role
    Given I navigate to login page
    And I enter login details
      | email | password |
      | xxxxx | xxxx     |
    And I click on Sign In button
    When I select WO Tracking application box
    And user searches with the following global value
      | globalsearch |
      | Heffernan    |
    And I capture screen shot for heff direct role
    Then I Click on Logout







