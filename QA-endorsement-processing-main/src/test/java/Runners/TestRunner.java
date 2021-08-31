package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/resources/Features"},
        plugin = {"Base.FailedStepReport:target/failedstep.txt","json:target/cucumber.json","html:target/cucumber-html/index.html"},
        glue = "Steps", dryRun = false,
        monochrome = true,
        tags =
                //"@AddWorkOrder or @KPIFolderTabMove"
                //"@WorkOrderGridsDataDriven or @WorkOrderGrids"
                //"@WorkOrderGrids"
                //"@GridColors or @Actions or @WorkOrderGridsDataDriven or @WorkOrderGrids or @AddWorkOrder or @KPIFolderTabMove"
        "@EditWorkOrder"
                )
public class TestRunner extends AbstractTestNGCucumberTests {


}
