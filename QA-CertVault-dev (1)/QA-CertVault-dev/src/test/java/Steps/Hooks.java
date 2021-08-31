package Steps;

import Base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.util.Date;

public class Hooks extends BaseUtil {

    private BaseUtil base;

    public Hooks(BaseUtil base) throws FileNotFoundException {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) throws IOException {
        System.setProperty("webdriver.chrome.driver", chromedriverlocation);
//        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/test/resources/Extension/oemmndcbldboiebfnladdacbdfmadadm-2.3.164-Crx4Chrome.com.crx"));
        driver = new ChromeDriver(options);
    }

    @After
    public void TearDownTest(Scenario scenario) {
        scenario.write("Scenario finished");
        if (scenario.isFailed())
        {  // TakesScreenshot
            scenario.embed(((TakesScreenshot)base.driver).getScreenshotAs(OutputType.BYTES), "image/png");
            System.out.println(scenario.getName());

            File srcFile = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.FILE);
            String currentdate = new SimpleDateFormat("MM-dd-yyyy-HH-mm").format(new Date());
             try {
                FileUtils.copyFile(srcFile, new File(filepath + currentdate +".png"));
            //    FileUtils.copyFile(srcFile, new File(String.valueOf(MediaEntityBuilder.createScreenCaptureFromPath("C:\\DESKTOP BACKUP FILES\\QA-CertVault\\screenshots\\"+System.currentTimeMillis()+".png").build())));
         } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
     }
}



/*
    And I click How It Works link  //need inspect
    And I click FAQ link
    And I click Home link   //need inspect
    And I click Contact Us link  //need inspect
    And I enter name, company and email to contact them

*/



