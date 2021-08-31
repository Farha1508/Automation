package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/Feature"},
        plugin = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
        glue = "Steps", dryRun = false,
        monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {


}
