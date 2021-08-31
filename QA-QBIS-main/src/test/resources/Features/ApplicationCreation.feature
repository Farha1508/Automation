Feature: Application Creation

  @2450
  Scenario: Creating an application for new Business
    Given I am on the login page
    When I enter the email and password for the "Owner"
    And I click the Sign In button
    And I open the New Application form
    And I enter "Test Business Name" in the "Business Name" field
    And I enter "Testing Services" in the "Describe Your Business Services" selector
    And I enter "98666" in the "Zip Code" field
    And I enter "TestFirst" in the "First Name" field
    And I enter "TestLast" in the "Last Name" field
    And I enter "testemail@patracorp.net" in the "Email" field
    And I enter "5555555555" in the "Phone" field
    And I click the "Continue" button
    And Ensure I am taken to the Pick Your Product page
    And I click "Employment Practices Liability Coverage" checkbox
    And I click the "Continue" button
    Then The General Questions page will be displayed
    When I set the date in the "Desired start date of insurance coverage?" date picker to
      | Year | Month    | Day |
      | 2021 | February | 11  |
    And I select "March" from the "Month" drop down
    And I select "2007" from the "Year" drop down
    And I select "Not For Profit" from the "What is the organization type of the company?" drop down
    And I enter "99" in the "Enter the total number of employees, including yourself, full-time, part-time, and seasonal employees, volunteers, and independent contractors:" field
    And I enter "99999999" in the "What are the company's current assets? (must be greater than $0 and rounded to nearest $1)" field
    And I enter "999999" in the "What are the company's current liabilities? (must be greater than $0 and rounded to nearest $1)" field
    And I enter "9999999" in the "What is the company's total annual revenue? (rounded to nearest $1)" field
    And I click the "Continue" button
    Then The Employment Practices Liability Questions form will be displayed
    When I choose the "Yes" option from the "Does the company have a dedicated Human Resource Administrator?" question
    And I choose the "Yes" option from the "Is the company's HR Administrator a different person than the Chief Executive Officer?" question
    And I pick the following from the "Please select each of the following HR policies that the company has in place (more than one may be selected):" multi-select
      | Equal Employment Opportunity (EEO) / Discrimination | Anti-Harassment | Family Medical Leave Act (FMLA) |
    And I choose the "No" option from the "Has the company had or does the company anticipate any layoffs or a reduction in force in the past or upcoming 18 months?" question
    And I select "Decreasing employee turnover rate" from the "Which best describes the company's turnover rate from the past three years?" drop down
    And I enter "0" in the "How many of the company's claims in the past five years were specifically related to the company's employment practices liability insurance coverage?" field
    And I enter "60" in the "How many of the company's employees are full time, volunteers, and/or independent consultants?" field
    And I enter "0" in the "How many of the company's employees are part time (≤ 25 hours/week)?" field
    And I enter "30" in the "How many of the company's employees work abroad?" field
    And I enter "9" in the "How many of the company's employees are seasonal (≤ 6 months/year) or temporary?" field
    And I select "Company-dedicated department with local resources" from the "Which best describes the company's Human Resources Department?" drop down
    And I select "Written, updated and frequently distributed" from the "Which best describes the company's Human Resources policies and procedures?" drop down
    And I select "0% to 9%" from the "What percentage of employees make more than $100,000 per year?" drop down
    
    


    And I wait for 5 seconds