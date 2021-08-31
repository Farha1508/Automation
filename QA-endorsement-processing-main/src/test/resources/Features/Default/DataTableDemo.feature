Feature: Demonstration of DataTable utilization
  Except for the login component, the scenarios here only execute code from the Step definition file

  Background: Login component
    Given I am on the login page
    ###---------- Login component ----------###
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###

    ###---------- App selection ----------###
    When I click on the "PMA" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###


  Scenario: Filling in form as single data table
    And I click the "Add Business" button
    And I enter the following information into the form
      | Brand                       | Patra Select                            |
      | Business Class              | Pest Control                            |
      | Broker                      | Ian Maclachlan                          |
      | Coverage Type               | Cyber                                   |
      | Business/Policy Holder Name | DataTable Demo Field <current date>     |
      | Referrer Name               | Testy McTest                            |
      | Referring Company           | test                                    |
      | Years In Business           | 11                                      |
      | Website                     | www.patra.com                           |
      | Description of Operations   | DataTable Demo Text Area <current date> |

    And I wait for "10" seconds

  Scenario: Filling in form using separate tables for each item type
    And I click the "Add Business" button
    And I edit the following drop downs
      | Brand        | Business Class | Broker         | Coverage Type |
      | Patra Select | Pest Control   | Ian Maclachlan | Cyber         |
    And I edit the following fields
      | Business/Policy Holder Name         | Referrer Name | Referring Company | Years In Business | Website       |
      | DataTable Demo Field <current date> | Testy McTest  | test              | 11                | www.patra.com |
    And I edit the following text areas
      | Description of Operations               |
      | DataTable Demo Text Area <current date> |

    And I wait for "10" seconds

