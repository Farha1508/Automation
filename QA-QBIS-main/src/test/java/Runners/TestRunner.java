package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/resources/Features"},
        plugin = {"Base.FailedStepReport:target/thestep.txt","json:target/cucumber.json","html:target/site/cucumber-pretty"},
        glue = "Steps", dryRun = false, strict = true,
        monochrome = true,
        tags = {"@dev"})
public class TestRunner extends AbstractTestNGCucumberTests {


}
