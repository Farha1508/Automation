Feature: Automate based on Patra Documents Role
  @PatraDocumentrole
  Scenario: Patra Documents role Login, Paper Registration page, Generate Paper Reg Letter, User Profile, Logout
            TC Id's: C5123,C5124,C5121,C864,C103,C5122.

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email                        | password |
      | patradocuments@patracorp.com | xxxx    |
    And I click on Sign In button

  #  And I click on Admin menu
  #  And I click on Paper Registration menu

    #C5123
    When I click on Flag for paper Registration to verify validation message
    #C5124 Flag process
    When I search with columns in paper Registration
      | account | company      | companytype | brokercompany | paperadddatefrom | paperadddateto |
      |         | Mar Cor Inc. |             |               |                  |                |
    #C5122
    And I get the status of Letters Sent column
    #C5122
    And I get the status of Total Letters Sent column
    When I select checkbox under select user/row column and flag
    #C5121
    And I click Paper Registration Letters button to generate letters
    #C864
    And I verify cancel button in generate paper letters form
    #C103
    And I select Broker Company in Generate Paper letters
    #C5122 #re-test status
    When I search with columns in paper Registration
      | account | company      | companytype | brokercompany | paperadddatefrom | paperadddateto |
      |         | Mar Cor Inc. |             |               |                  |                |
    And I get the status of Letters Sent column
    And I get the status of Total Letters Sent column

    #Paper Letter History C11741
    And I click on Paper Letter History button
    When I search with columns in Paper Letter History grid
      | rootcomp                    | companygrp                  | letters | dategenfrom | dategento | lettertype | comptype | genby                  |
      | Heffernan Insurance Brokers | Heffernan Insurance Brokers | 2       |             |           | Manual     | Holder   | e.anusha@patracorp.net |
    #C11742
    And I click on Clear All Filters button for paper letter history
    # -----------User Profile module ---------- new ui
     #--C10103
    And Header title accessibility
    When I click on Profile icon and My Profile
    When I click on profile icon and user guide
    Then I click on Profile icon and Logout

