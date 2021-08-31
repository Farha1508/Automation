package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Contains steps for using browser functions directly
 * (eg. using the back button, refreshing a page, navigating to a specific URL).
 */
public class BackgroundSteps extends BaseUtil {

    @And("I fail the test")
    public void iFailTheTest() {
        Assert.fail("Failing test for testing purpose");
    }

    @And("I add the following items to the valueMap")
    public void iAddTheFollowingItemsToTheValueMap(Map<String, String> table) {
        table.forEach((key, value) -> valueStore.put(key, value));
    }

    @And("I add {string} key and {string} value to valueMap")
    public void iAddKeyAndValueToValueMap(String key, String value) {
        valueStore.put(key, value);
    }

    @And("I store the current username")
    public void storeCurrentUser() {
        login.ClickUserIcon();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("userName")));
        valueStore.put("currentUser", driver.findElement(By.className("userName")).getText());
    }

    @And("I print out the valueStore")
    public void iPrintOutTheValueStore() {
        valueStore.forEach((key, value) -> System.out.println(key + " - " + value));
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

    @And("I take a screenshot")
    public void iTakeAScreenshot() {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format((new Date()));
        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + BaseUtil.scenarioName + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + BaseUtil.scenarioName + dateString + " " + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("I wait for {string} seconds")
    public void iWaitForSeconds(String seconds) throws InterruptedException {
        int timeToWait = Integer.parseInt(seconds) * 1000;
        Thread.sleep(timeToWait);
    }

    @And("I click on the services button for {string}")
    public void iClickOnTheServicesButtonFor(String companyName) {
        System.out.println("Opening the services for company " + companyName);
        driver.findElement(By.xpath("//td[normalize-space()=\"" + companyName + "\"]/following-sibling::td/a[@title=\"Services\"]")).click();
        pageLoaded();
    }

    @And("I navigate to the {string} Admin tab")
    public void iNavigateToTheAdminTab(String tabName) {
        System.out.println("Clicking the " + tabName + " tab in the Admin app.");
        //Unable to use the regular navigateToTab() method when on the Admin page
        driver.findElement(By.xpath("//li[normalize-space()=\"" + tabName + "\"]")).click();
        pageLoaded();
    }

    @And("I edit the {string} row")
    public void iEditTheRow(String serviceName, Map<String, String> table) {
        System.out.println("Editing the " + serviceName + " row.");
        List<WebElement> editButtons = driver.findElements(By.xpath("//td[normalize-space()=\"" + serviceName + "\"]/../td/input[@title =\"Edit\"]"));
        int rowIndex = 0; // This is used to select the edit row, which will only exist after the edit button has been clicked, and doesn't have any good selectors of its own.
        for (WebElement button : editButtons) {
            if (button.isDisplayed()) {
                rowIndex = button.findElements(By.xpath(".//../../preceding-sibling::tr")).size() + 1;
                //button.click(); /*This Was giving element not clickable message , so used javascript executor*/
                js.executeScript("arguments[0].click();",button);
                break;
            }
        }
        pageLoaded();

        /** If the service name is not mapped on Due Date Override page than it will take the default TAT days i.e
        * DDO = 3 days
        * Rush DDO = 1 day
        * Business Days = False
        * For reference please look into https://patracorp.atlassian.net/wiki/spaces/~18866344/pages/689176585/Current+TAT+Functionality page **/

        if (rowIndex == 0) {
            valueStore.put("TAT", "TAT is not set");
            valueStore.put("DefaultDDO", "3");
            valueStore.put("DefualtRushDDO", "1");
        } else {
            valueStore.put("TAT", "Editing the TAT");

            // When the edit button is clicked, the original row is removed, and a new "edit" style row is displayed.
            // Need all-new xpath selectors.

            // The new edit row gets added above the original row, so use the same index.
            WebElement targetRow = driver.findElement(By.xpath("//*[@id=\"dueDateGrid\"]/div[2]/table/tbody/tr[" + rowIndex + "]"));
            table.forEach((key, value) -> {
                // find out index of the column in the table header, because there's nothing else to grab
                int columnIndex = driver.findElements(By.xpath("//th[normalize-space()=\"" + key + "\"]/preceding-sibling::th")).size() + 1;
                WebElement cellToEdit = targetRow.findElement(By.xpath(".//td[" + columnIndex + "]/input"));
                if (key.equalsIgnoreCase("Business Days")) {
                    if (cellToEdit.isSelected() ^ Boolean.parseBoolean(value)) {
                        cellToEdit.click();
                    }
                } else {
                    cellToEdit.clear();
                    cellToEdit.sendKeys(value);
                }
            });

            // Save the changes
            targetRow.findElement((By.xpath(".//input[@title=\"Update\"]"))).click();
            pageLoaded();

            // Confirm that the row has been updated correctly

        }
    }

    @And("I select {string} from the Transaction Type filter")
    public void iSelectFromTheTransactionTypeFilter(String serviceName) {
        Select se = new Select(driver.findElement(By.xpath("//*[@id=\"dueDateGrid\"]/div[1]/table/tbody/tr[2]/td[1]/select")));
        se.selectByVisibleText(serviceName);
        pageLoaded();
    }
}
