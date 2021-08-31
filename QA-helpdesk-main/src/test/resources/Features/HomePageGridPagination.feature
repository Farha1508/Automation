Feature: Testing pagination for grid

  Scenario: Grid pagination for Help Desk

    ##------------- Initiator - Login Functionality --------------##
   ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

    ###---------- App selection ----------###
    When I click on the "Help Desk" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###
    ###------------- Testing pagination on Incoming WO tab --------------###
    When I navigate to the "Incoming WO" tab
    And I get the current grid page number
    And I move to the next page in the grid
    And I move to the previous page in the grid
    And I change pages by entering a number
    ###------------- Testing pagination on Incoming WO tab --------------###
    ###------------- Testing View dropdown on Incoming WO tab --------------###
    When I navigate to the "Incoming WO" tab
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected

###------------- Testing pagination on Pending WO tab --------------###
    When I navigate to the "Pending WO" tab
    And I get the current grid page number
    And I move to the next page in the grid
    And I move to the previous page in the grid
    And I change pages by entering a number
    ###------------- Testing pagination on Pending WO tab --------------###
    ###------------- Testing View dropdown on Pending WO tab --------------###
    When I navigate to the "Pending WO" tab
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected


    ###------------- Testing pagination on Completed WO tab --------------###
        When I navigate to the "Completed WO" tab
        And I get the current grid page number
        And I move to the next page in the grid
        And I move to the previous page in the grid
        And I change pages by entering a number
        ###------------- Testing pagination on Completed WO tab --------------###
        ###------------- Testing View dropdown on Completed WO tab --------------###
        When I navigate to the "Completed WO" tab
        When I select the "20" option from the the Viewing drop down
        Then The number of rows displayed will be less than or equal to the number selected
        When I select the "50" option from the the Viewing drop down
        Then The number of rows displayed will be less than or equal to the number selected
        When I select the "100" option from the the Viewing drop down
        Then The number of rows displayed will be less than or equal to the number selected
        When I select the "10" option from the the Viewing drop down
        Then The number of rows displayed will be less than or equal to the number selected


      ###------------- Testing pagination on Discarded WO tab --------------###
              When I navigate to the "Discarded WO" tab
              And I get the current grid page number
              And I move to the next page in the grid
              And I move to the previous page in the grid
              And I change pages by entering a number
              ###------------- Testing pagination on Discarded WO tab --------------###
              ###------------- Testing View dropdown on Discarded WO tab --------------###
              When I navigate to the "Discarded WO" tab
              When I select the "20" option from the the Viewing drop down
              Then The number of rows displayed will be less than or equal to the number selected
              When I select the "50" option from the the Viewing drop down
              Then The number of rows displayed will be less than or equal to the number selected
              When I select the "100" option from the the Viewing drop down
              Then The number of rows displayed will be less than or equal to the number selected
              When I select the "10" option from the the Viewing drop down
              Then The number of rows displayed will be less than or equal to the number selected