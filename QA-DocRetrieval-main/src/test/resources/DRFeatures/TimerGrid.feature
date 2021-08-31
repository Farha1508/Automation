Feature: Manipulation of different grid headers

  Background: Login to Node application
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    When I click on the "Work Order Tracking" tile
    When I open "Document Retrieval" for company "Company 019"
 #   Then The page for the selected company and service will be displayed
    And I wait for "3" seconds
    And I click on the "Time Rec Open" link

  #Scenario: Verify Pagination functionality
    #And set the tab to "timerecordopen"
    #And I wait for "5" seconds
    #And I move to the next page in the grid
    #And I wait for "10" seconds
    #And I move to the previous page in the grid