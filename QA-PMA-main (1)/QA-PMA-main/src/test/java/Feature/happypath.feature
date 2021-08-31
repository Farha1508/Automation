# new feature
# Tags: optional

Feature: PMA - Lead Assignment - AMA Application-Microsites

  Scenario: Microsites to Lead assignment for non oli

    Given I navigate to login page
    And I enter login details
      | email                    | password |
      | m.prathima@patracorp.net | Oct@1234 |
    And I click on Sign In button
    When I select WO Tracking application box
    And I clicks Home button

    #----Add business----#
    And user clicks add business button
    And user enters the following business details
      | brand       | subbrand     | businessname | businessclass | clientid | broker     | referrername | referringcompany | prospectorigin | needbydate | yearsinbusiness | experience | website       | typeoforganization | ifotherpleasespecify | descriptionofoperations | estannualgrosssales | estannualpayroll | noofactiveowners | fte | pte | fein      | coveragetype |
      | Heff Direct | Bold Penguin | 999sep       | Homecare      |          | Prathima M |              | ABCD company     | Call In        | 05/22/2018 | 22              | 11         | www.gmail.com | Corporation        |                      | desc of ops             | 2563                | 256000           | 250              | 20  | 15  | FEIN112j3 | DIC          |
    And user clicks submit button
    And user clicks on opportunity grid
    #----Grid Search----#
    And user searches with the following
      | businessname |
      | 999sep       |
    And user selects opportunity
    And clicks on Business Details link to edit
    And I edit add contact under business details fields
    And I click on Edit symbol for Add Contact
    And I click on Add Addresses button
      | addresstype      | city  | street1     | street2        | zip    | country |
      | Physical Address | Vizag | Dec25 nagar | Birla junction | 123456 | India   |
    And I click on Edit symbol for add address
    And user clicks business add activity button
    And user adds the following activity details
      | activitytype | activitystatus | departmentassigned | assignedto | needbydate | activityperformed | activitycompleted | followuprequired | reviewdesired | description        |
      | Proposal     | Open           | Broker             | Prathima M | 02/08/2018 |                   |                   |                  |               | Automation Testing |
    And user clicks business add activity submit button
    Then user should see the record under business activities tab
    And user clicks business add note button
    And user enter the following note details
      | title      | description |
      | automation | testing     |
    And user clicks business add note submit button
    And user should see the record under business notes tab
    And user clicks business add opportunity button
    And user enters the following opportunity details
      | status      | coveragetype    | needbydate | opportunityreceiveddate | departmentassigned | assignedto | opportunityassigneddate | currentinsurancecarrier | renewaldate | currentpremium | priorclaims | origin  | describepriorclaims |
      | Info Needed | Commercial Auto | 02/09/2018 | 12/09/2018              | Broker             | Prathima M | 01/09/2018              | test                    | 09/02/2018  | 25             |             | Call In | Testing             |
    And user clicks business add opportunity submit button
    And click on opportunities tab
    And user selects coverage under opportunities tab
    And user edits opportunity details
      | oppstatus  | wheniscoverageneeded |
      | Needs Call | Not sure             |
    And user clicks opportunity detail submit button

    And I clicks Home button
    And I click Reset button
    And I see business page of that record
    And I click on Coverage name
    And I click on Add Activity section
      | activitytype  | activitystatus | departmentassigned | assignedto | needbydate | reviewdesired | followupreq | description |
      | Proposal Call | Open           | Broker             | Prathima M | 12/02/2018 |               |             | Testing     |
    And I click on Add Note section
      | title     | description |
      | testtitle | testdesc    |
    And I click on Add Claim section
      | claimdate  | claimstatus | claimamount | descriptionofclaim |
      | 12/08/2018 | Open        | $2500       | test               |
    And I click on Add Quote section
      | quotedate  | carrier | quotedpremium | carrierdeclined | declinedreason |
      | 12/08/2018 | AIG     | $2800         |                 | Pricing        |
    And I click on History tab
    Then click on Print PDF button
    And finally clicking on Reports button
    And I click record under Activity Type column
    #And I select activity type record

    #Sprint Changes:Activity Timer
    #----Start Timer in activity page------#
    And I select the task
    And I enter values in Activity detail section under Activity page
      | activitytype  | activitystatus | departmentassigned | assignedto | needbydate | activityperformed | activitycompleted | reviewdesired | followuprequired | numberoffollowups | followupfrequency | finalfollowup | description          |
      | Proposal Call | In Progress    | Broker             | Prathima M | 12/07/2018 |                   |                   |               |                  | 2                 | 15 days           | 45th day      | contd... PMA testing |
    And I click on Add Followup button under Followups section
    And I enter follow up details
      | followuptype      | followupdate | deptassigned | assignedto |
      | AMS Policy Detail | 12/05/2018   | PMA Manager  | Prathima M |
    And I click on Edit Date symbol
    And I click on Add Note under To Do List page
      | title      | description |
      | todo title | todo desc   |
    And I click on Add Claim under To Do List page
      | claimdate  | claimstatus | claimamount | descriptionofclaim |
      | 12/03/2018 | Open        | $808        | test claims        |
    And I click on Add Quote under To Do List page
      | quotedate  | carrier   | quotedpremium | carrierdeclined | declinedreason |
      | 12/07/2018 | All Risks | $2500         |                 | Pricing        |
    And I clicks Home button
     #Stop timer
    And I click on stop timer
    And I Click on opportunity tab
    And I click on View Pdf

    #-------Global Search-----#
    And user searches with the following global value
      | globalsearch    |
      | Business: 70715 |
    And user clicks the first link

    And I click on Lead Assignment on top grid
    And I select coverage under opportunity tab in Lead Assignment
    And I enter values under Oportunity Assignment
      | brand | subbrand | classofbusiness | clientid | broker     | accesslevel | servicelevel       | descriptionofoperations |
      | Foley |          | Animal services | 11315    | Prathima M | Direct      | Affiliate Serviced | desc                    |
    And I click Lead Submit button
    When I click on Assignment Rules button
    And I click AMS Entry Information tab
    And I click Notes tab
    And I clicks Home button
    Then I Click on Logout