package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class StepDefs extends BaseUtil {


    @And("I open the URL {string}")
    public void iOpenTheURL(String url) {

        driver.get(url);
        pageLoaded();
    }

    @When("I Click on user icon")
    public void iClickOnUserIcon() {
        System.out.println("Clicking User Icon");
        login.ClickUserIcon();
    }

    @And("I click Logout button")
    public void iClickLogoutButton() {
        System.out.println("Clicking Logout button");
        login.ClickLogOut();
    }

    @And("I wait for {string} seconds")
    public void iWaitForSeconds(String seconds) throws InterruptedException {
        int timeToWait = Integer.parseInt(seconds) * 1000;
        Thread.sleep(timeToWait);
    }

    @And("I take a screenshot")
    public void iTakeAScreenshot() {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format((new Date()));
        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + BaseUtil.scenarioName + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + BaseUtil.scenarioName + dateString + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("I get the {string} info from valueStore")
    public void iGetTheInfoFromValueStore(String storeItem) throws InterruptedException {
        js.executeScript("alert('" + storeItem + " in valueStore: " + valueStore.get(storeItem) + "')");
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        System.out.println(storeItem + " in valueStore: " + valueStore.get(storeItem));
    }

    @And("I clear the valueStore")
    public void iClearTheValueStore() {
        valueStore.clear();
        editedValues.clear();
    }

    @And("I click on the {string} link")
    public void iClickOnTheLink(String linkText) {
        commonForm.commonLinkClick(linkText);
        pageLoaded();

    }

    @And("I add {string} key and {string} value to valueMap")
    public void iAddKeyAndValueToValueMap(String key, String value) {
        valueStore.put(key, value);
    }

    @And("I print out the valueStore")
    public void iPrintOutTheValueStore() {
        valueStore.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    @And("I fail the test")
    public void iFailTheTest() {
        Assert.fail("Failing test for testing purpose");
    }


    @And("I add the following items to the valueMap")
    public void iAddTheFollowingItemsToTheValueMap(Map<String, String> table) {
        table.forEach((key, value) -> valueStore.put(key, value));
    }

    @And("I wait for {int} seconds")
    public void iWaitForSeconds(int seconds) throws InterruptedException {
        int timeToWait = seconds * 1000;
        Thread.sleep(timeToWait);
    }


    private String currentCompany;
    private String currentService;

    @When("I open {string} for company {string}")
    public void iOpenForCompany(String service, String company) {
        WebElement selectCompany = driver.findElement(By.id("companyStartId"));
        selectCompany.click();
        Select list = new Select(selectCompany);
        list.selectByVisibleText(company);
        commonForm.commonLinkClick(company + " - " + service);
        pageLoaded();

        currentCompany = company;
        currentService = service;

    }

    @Then("The page for the selected company and service will be displayed")
    public void thePageForTheSelectedCompanyAndServiceWillBeDisplayed() {
        pageLoaded();
        WebElement companySelector = driver.findElement(By.id("changecompany"));
        Select company = new Select(companySelector);
        String c = company.getFirstSelectedOption().getText();
        System.out.println("Selected company is: " + company.getFirstSelectedOption().getText());
        WebElement serviceSelector = driver.findElement(By.id("changeservice"));
        Select service = new Select(serviceSelector);
        String s = service.getFirstSelectedOption().getText();
        System.out.println("Selected service is: " + service.getFirstSelectedOption().getText());
        Assert.assertEquals(c, currentCompany, "Company on page (" + c + ") does not match previously selected company(" + currentCompany + ")");
        Assert.assertEquals(s, currentService, "Service on page (" + s + ") does not match previously selected service (" + currentService + ")");
        System.out.println("Verified current page is the " + s + " page for " + c);
    }
}
