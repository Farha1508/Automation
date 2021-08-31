package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;



    // @RunWith(Project1.class)
    @CucumberOptions(features = {"src/test/java/Feature"}, format = {"json:target/cucumber.json", "html:target/site/cucumber-pretty", "junit: target/cucumber.xml"}, glue = "Steps")

    public class Runner extends AbstractTestNGCucumberTests
    {

    }





/*
  Feature: PMA

  Scenario: Opportunity Bound

    Given I navigate to login page
    And I enter login details
      | email                  | password |
      | e.anusha@patracorp.com | Nov@2018  |
    And I click on Sign In button
    When I select WO Tracking application box

    And user clicks business grid

    And user searches with the following
      | businessname     |
      | 999 Anusha Oct26 |
    And user selects business
    And click on opportunities tab
    And user selects coverage under opportunities tab
    And user selects opportunity detail status as bound or proposed
      | status   |
      | bound |
    And user adds quote with the following values if there is no quote
      | quotedate  | carrier | quotedpremium |
      | 11/11/2018 | AIG 3   | 200           |
    And user clicks add quote submit button
    And user selects a quote by clicking mark selected button
    And user clicks confirm button
    And user enters the mandatory fields in opportunity detail
      |effectivedate|quotepremium|commission|brokerfee|
      |09/09/2019   |200         |10        |100      |
    And user clicks opportunity detail submit button



<<<<<<< HEAD:src/test/java/Runner/Runner.java
    And I clicks Home button
    And I select one of the KPI My New Opportunities
    And I click Reset button
    And user clicks business grid
    And I Click on opportunity tab
    Then I sort the fileds under Opportunity grid
    And I click on View Pdf
    Then I Click on Business and opportunity tabs and sort columns there

 */






