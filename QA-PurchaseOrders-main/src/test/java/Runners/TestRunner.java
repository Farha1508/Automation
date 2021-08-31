package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/resources/Features"},
        plugin = {"Base.FailedStepReport:target/failedstep.txt","json:target/cucumber.json","html:target/cucumber-html/index.html"},
        glue = "Steps", dryRun = false,
        monochrome = true,
        tags = "")
public class TestRunner extends AbstractTestNGCucumberTests {


}
