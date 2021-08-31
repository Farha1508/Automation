package Runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/test/resources/DRFeatures"},
        plugin = {"Base.FailedStepReport:target/failedstep.txt","json:target/cucumber.json","html:target/site/cucumber-pretty"},
        glue = "Steps", dryRun = false, strict = true,
        monochrome = true,
        tags = {"@Test"})
public class TestRunner extends AbstractTestNGCucumberTests {


}
