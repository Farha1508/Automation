package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Runner.class)
@CucumberOptions(features= {"src/test/java/Feature/Certuploader.feature"},
                 plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/UI2report.html",
                          "json:target/cucumber.json", "html:target/site/cucumber-pretty"},
                 glue = "Steps", dryRun = false, strict = true,
                // tags = {"@uploadcert, @Certificates, @now_GeneratePaperReg_letter, @ManageUsers, @Companies, @ExceptionHandling, @clients, @Batches, @Insuredrole, @PatraDocumentrole, @Support, @FunctionalTest"},
                 monochrome = true)

public class Testrunner extends AbstractTestNGCucumberTests {

}

/*
@AfterClass
    public static void setup() {
        Reporter.loadXMLConfig(new File("D:\\Github-Repos\\QA-CertVault\\src\\test\\extent-config.xml"));
        Reporter.setSystemInfo("Test Pattern", "Automation Testing");
        Reporter.setSystemInfo("Environment", "Dev");
        Reporter.setSystemInfo("User Name", "Anusha E");
        Reporter.setSystemInfo("Java Version", "1.8.0_151");
        Reporter.setSystemInfo("OS", "Windows 10 Pro" + "64 bit");
        Reporter.setSystemInfo("Maven", "3.5.2");
        Reporter.setTestRunnerOutput("Cucumber JUnit Test Runner");
    }
 */







