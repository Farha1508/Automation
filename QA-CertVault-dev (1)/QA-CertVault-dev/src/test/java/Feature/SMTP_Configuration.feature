Feature: Automating SMTP Configuration module
  Scenario: grid search, clear, add SMTP Configuration and its validation, edit

    Given I navigate to login page of cert vault

    And I click SignIn/Register button
    And I enter login credentials
      | email        | password |
      | xxxxx@xx.com | xxxx     |
    And I click on Sign In button
    When I click on profile icon and switch company
      | switch_company               |
      | USI Insurance Services - 17  |
    And I click on Admin menu
    And I click on SMTP Configuration tab

    #C12725
    And I click on Add SMTP Configuration button
    And I enter details in Add SMTP Configuration pop-up
      | select_company                | smtp_host               | smtp_username          | password | outbound_port | encryption_method |
      | test broker 3	              |patramail.patracorp.net  | smtpuser@patracorp.net | pass123  | 888           |  STARTTLS         |
    And I verify disable/enable SMTP Configuration
    And I click on save button in SMTP Configuration popup
    #C12723
    When I search with columns in SMTP Configuration header grid
      | companyid | companyname   | enableddisabled |
      |           | test broker 3 |                 |
    #C12852
    And I click on edit SMTP Configuration
    And I enter details in Add SMTP Configuration pop-up
      | select_company                | smtp_host           | smtp_username          | password | outbound_port | encryption_method |
      | test broker 3	              | patramail.patra.net | smtpuser@patracorp.net |          |               |  STARTTLS         |
    And I click on save button in SMTP Configuration popup
    #C12724
    And I click on Clear All Filters button for SMTP Configuration

    #C12726- validation
    And I click on Add SMTP Configuration button
    And I enter details in Add SMTP Configuration pop-up
      | select_company                | smtp_host               | smtp_username          | password | outbound_port | encryption_method |
      | Heffertech Insurance Brokers  |                         | smtpuser@patracorp.net | pass123  | 888           |  STARTTLS         |
    And I click on save button in SMTP Configuration popup
    #C12853
    And I click on x icon for SMTP Configuration form
    And I click on edit SMTP Configuration
    And I click on save button in SMTP Configuration popup
    #C12854
    And I click on x icon for SMTP Configuration form

    #C12858 Duplicate- SMTP Confiuration
    When I search with columns in SMTP Configuration header grid
      | companyid | companyname   | enableddisabled |
      |           | testing       |                 |
    And I click on edit SMTP Configuration
    And I enter details in Add SMTP Configuration pop-up
      | select_company               | smtp_host               | smtp_username          | password | outbound_port | encryption_method |
      | Heffertech Insurance Brokers |patramail.patracorp.net  | smtpuser@patracorp.net |          |               | STARTTLS          |
    And I click on save button in SMTP Configuration popup

    # Verifying the email notification- SMTP Confiuration
    And I click on Upload Cert button
    And I choose file to upload
    And I click on OKAY button
    And I handle blocked email registration functionality
      | enter_blocked_email                          |
      | sonali.gupta+98_h@jellyfishtechnologies.com  |
    And I handle email registration button functionality
      | enteremail                                   |
      | sonali.gupta+wwf12@jellyfishtechnologies.com |
    And I click on Email Cert
    And I login into gmail account for email notification
    And I verify the email cert notification
    And I verify the Email Registration letter for Insured and Holder user
    And I verify the email blocked notification
    And I verify the single cert upload notification