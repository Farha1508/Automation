Feature: Agency API Configuration module
  Scenario: C14315, C14316, C14317.

    Given I navigate to login page of cert vault
    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xx       |
    And I click on Sign In button

    And I click on Admin menu
    And I click on Agency API Configuration tab

    #C14315
    When I click on Add API Configuration button
      | companyname | apiname   | endpointurl                                                | key     | value   |
      | jj          | Vertafore | https://ws12.sagitta-online.com/SagittaWS/Transporter.asmx | testkey | testval |
    #C14359
    When I perform grid search for agency api
      | compid | compname | enabledisable | apiname |
      | 20155  | jj       |               |         |
    #C14316
    And I click on edit api icon under Action column
    #C14317
    And I verify validation for add/edit api configuration


