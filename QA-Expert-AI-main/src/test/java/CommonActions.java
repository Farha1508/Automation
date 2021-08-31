import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonActions {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    public CommonActions(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
    }

    public boolean pageLoaded() {
//        System.out.println("jQuery readystate: " + js.executeScript("return jQuery.active == 0"));
//        System.out.println("document.readyState: " + js.executeScript("return document.readyState").toString());
        // wait for jQuery to load
//        ExpectedCondition<Boolean> jQueryLoad = driver -> (Boolean) js.executeScript("return jQuery.active == 0");
        // Catch jQuery not existing on page
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return (Boolean) js.executeScript("return jQuery.active == 0");
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> js.executeScript("return document.readyState").toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);

    }

    public void openCompany(String service, String company) {
        WebElement selectCompany = driver.findElement(By.id("companyStartId"));
        selectCompany.click();
        Select list = new Select(selectCompany);
        list.selectByVisibleText(company);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(company + " - " + service))).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), driver.findElement(By.linkText(company + " - " + service)));
        }
        pageLoaded();
    }

    public void clickErrorHandle(String error, WebElement target) {
        String xPath = "";
        String selector = "";
        Pattern element = Pattern.compile("(?<=click: \\<)(.*?)(?=\\s*\\>)");
        Pattern type = Pattern.compile("^\\w+");
        Pattern tag = Pattern.compile("\\w+=+'(.*?)'");
        Matcher eleM = element.matcher(error);
        while (eleM.find()) {
            selector = eleM.group();
        }

        Matcher typeM = type.matcher(selector);
        while (typeM.find()) {
            xPath += "//" + typeM.group();
        }

        Matcher tagM = tag.matcher(selector);
        while (tagM.find()) {
            xPath += "[@" + tagM.group() + "]";
        }
        List<WebElement> blockers = driver.findElements(By.xpath(xPath));
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(blockers));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
        } catch (TimeoutException ignored) {

        }

        target.click();
    }

    public void hold(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }

    public WebElement commonDropDown(String dropDown) {
        WebElement selectedList = null;
        // This list is to compensate for the Time Tracking pop-up in Work Order Tracking
        List<WebElement> lists = driver.findElements(By.xpath(
                "//label[normalize-space()='" + dropDown + "']/following-sibling::div/select"));
        // This list will find the drop downs in the rest of the apps
        List<WebElement> lists2 = driver.findElements(By.xpath(
                "//label[text()[normalize-space()='" + dropDown + "']]/following-sibling::select"));

        if (lists2.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedList == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement lst : lists2) {
                    if (lst.isDisplayed()) {
                        selectedList = lst;
                    }
                }
            }
        } else if (lists.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedList == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement lst : lists) {
                    if (lst.isDisplayed()) {
                        selectedList = lst;
                    }
                }
            }
        }

        return selectedList;
    }

    public boolean commonDropDownSelect(String dropDown, String selection) {
        WebElement selectedList = commonDropDown(dropDown);

        if (selectedList == null) {
            return false;
        }

        try {
            Select list = new Select(selectedList);
            list.selectByVisibleText(selection);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

        return true;
    }

    public boolean commonButton(String button) {
        List<WebElement> buttons = driver.findElements(By.xpath(
                "//button[normalize-space()='" + button + "']"));

        if (buttons.size() > 0) {
            for (WebElement btn : buttons) {
                if (btn.isDisplayed() && btn.isEnabled()) {
                    try {
                        js.executeScript("arguments[0].click()", btn);
                    } catch (ElementClickInterceptedException e) {
                        clickErrorHandle(e.toString(), btn);
                    }

                    return true;
                }
            }
        }
        return false;
    }

    public void userLogin(String user) {
        Properties login = new Properties();
        try{
            login.load(getClass().getClassLoader().getResourceAsStream("logins.properties"));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        String email = null;
        String pass = null;
        System.out.println("Entering login for " + user.toUpperCase() + " role\n");
        switch (user.toLowerCase()) {
            case "po initiator" -> {
                email = login.getProperty("poInitEmail");
                pass = login.getProperty("poInitPass");
            }
            case "po approver" -> {
                email = login.getProperty("poApproveEmail");
                pass = login.getProperty("poApprovePass");
            }
            case "po purchase officer" -> {
                email = login.getProperty("poPurchOffEmail");
                pass = login.getProperty("poPurchOffPass");
            }
            case "po procurement manager" -> {
                email = login.getProperty("poProcManEmail");
                pass = login.getProperty("poProcManPass");
            }
            case "super user" -> {
                email = login.getProperty("superUserEmail");
                pass = login.getProperty("superUserPass");
            }
            case "expense user" -> {
                email = login.getProperty("expenseUserEmail");
                pass = login.getProperty("expenseUserPass");
            }
            case "expense approver" -> {
                email = login.getProperty("expenseApproveEmail");
                pass = login.getProperty("expenseApprovePass");
            }
            case "expense manager" -> {
                email = login.getProperty("expenseManEmail");
                pass = login.getProperty("expenseManPass");
            }
            default -> {
                System.err.println("No login information available for " + user + " please check Login list.");
                System.exit(1);
            }
        }
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("submit")).click();

    }
}
