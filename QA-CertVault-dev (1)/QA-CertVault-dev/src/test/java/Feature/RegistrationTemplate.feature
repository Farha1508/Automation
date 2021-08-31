Feature: Automating Registration Template module
  Scenario: grid search, clear, add template and its validation, edit, template buttons,

    Given I navigate to login page of cert vault

    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button

    And I click on Admin menu
    And I click on Registration Templates tab

    #C12603
    When I search with columns in registration template header grid
      | companyid | companyname | recipienttype | enableddisabled |
      |           | test broker |               |                 |
    #C12604
    And I click on Clear All Filters button for Registration Templates

    #C12605 adding new template
    When I click on Add Template button
      | companyname       | recipienttype |
      | testing           | Holder       |
    And I click on save button in add template popup
    #C12606 adding existing template values
    When I click on Add Template button
      | companyname       | recipienttype |
      | Diane Test Broker | Holder        |
    And I click on save button in add template popup

    #C12603
    When I search with columns in registration template header grid
      | companyid | companyname                 | recipienttype | enableddisabled |
      |           | Heffernan Insurance Brokers | Holder        |                 |
    #C12607
    When I click on edit icon under Action column
    #C12661
    And I verify logo position validations
      | brokerlogo | cvlogo    |
      | Top Right  | Top Right |
    And I click on Save button in template editor
    And I verify logo position validations
      | brokerlogo | cvlogo    |
      | Middle     | Top Right |
    #C12608,C12609,C12661
    And I click on Save button in template editor
    #C12663
    And I click on Enable/Disable button in template editor
    #C12664
    And I click on Preview button in template editor
    When I click on edit icon under Action column
    #C12662
    And I click on Cancel button in template editor






