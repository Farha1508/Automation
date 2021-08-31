package Pages;

import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TimeRecordsFunctions extends BaseUtil {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;
    private final HashMap<String, String> gridMap = new HashMap<>();

    public TimeRecordsFunctions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
        this.js = (JavascriptExecutor)driver;
    }

    public void timeRecordsPages(String page) {
        WebElement timeRecordPage = driver.findElement(By.xpath("//div[@class='topMenu hidden-sm hidden-xs']/descendant::a[contains(text(),'"+page+"')]"));
        timeRecordPage.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void verifyPage(String currentpage) throws Exception {
        String URL = driver.getCurrentUrl();
        Properties config = new Properties();
        config.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        String env = config.getProperty("environment");
        if (env.equals("dev")) {
            switch (currentpage.toLowerCase()) {
                case "homepage" :
                    Assert.assertTrue(URL.contains("http://lossruns.dev.patracloud.net/lossruns") ,  URL + " did not match the expected");
                    break;
                case "time record":
                    Assert.assertTrue(URL.contains("http://lossruns.dev.patracloud.net/lossruns/time-record") ,  URL + "did not match the expected");
                    break;
                case "time record open":
                    Assert.assertTrue(URL.contains("http://lossruns.dev.patracloud.net/lossruns/time-record-open") , URL + "did not match the expected");
                    break;
                case "time record admin":
                    Assert.assertTrue(URL.contains("http://lossruns.dev.patracloud.net/lossruns/time-record-admin") , URL + "did not match the expected");
                    break;
                default:
                    throw new Exception("No Page information available for " + currentpage + " please check Page name.");

            }
        }else if (env.equals("release")) {
            switch (currentpage.toLowerCase()) {
                case "homepage" :
                    Assert.assertEquals(URL, "http://lossruns.release.patracloud.net/lossruns");
                case "time record":
                    Assert.assertEquals(URL, "http://lossruns.release.patracloud.net/lossruns/time-record");
                    break;
                case "time record open":
                    Assert.assertEquals(URL, "http://lossruns.release.patracloud.net/lossruns/time-record-open");
                    break;
                case "time record admin":
                    Assert.assertEquals(URL, "http://lossruns.release.patracloud.net/lossruns/time-record-admin");
                    break;
                default:
                    throw new Exception("No Page information available for " + currentpage + " please check Page name.");

            }

        }

    }

    public void runBttn(){
        WebElement bttn = driver.findElement(By.id("btnRun"));
        bttn.click();
    }

    public void columnClick(String tableColumn ) {
        WebElement colmn = driver.findElement(By.xpath("//th/div[text()=\"" + tableColumn + "\"]"));
        colmn.click();
        colmn.click();
    }

    public boolean HeaderFind(String page, String header) {
        String tabName = page.replaceAll("\\s", "");
        try {
            return driver.findElement(By.xpath("//table[@id='dt"+tabName+"']/descendant::th[contains(@aria-label,'"+header+"')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public boolean linkDisplayed(String link) {
        try {
            if (driver.findElement(By.partialLinkText(link)).isDisplayed()) {
                System.out.println("Link: " + link + " was displayed.");
                return true;
            } else {
                System.out.println("Link: " + link + " was not displayed.");
                return false;
            }
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Link: " + link + " does not exist.");
            return false;
        }
    }


}
