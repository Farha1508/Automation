package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimeTracking {

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;


    public TimeTracking(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.ID, using = "startTimerID")   //Timer modal
    public WebElement timerModal;

    public boolean elementFound(WebElement targetElement, String elementName) {
        try {
            if (targetElement.isDisplayed()) {
                return true;
            } else {
                System.out.println("Element " + elementName + " was not displayed.");
                return false;
            }
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Element " + elementName + " does not exist, or cannot be interacted with.");
            return false;
        }
    }

    public boolean waitForElementGone(WebElement gallowsElement) {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < 5000) { // lifted from NodeApp
            try {
                if (gallowsElement.isDisplayed()) { // element is displayed: wait longer
                    waitForMiliseconds(500);
                } else { // element is hidden: we're done
                    return true;
                }
            } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException e) { // element doesn't exist: we're done
                return true;
            }
        }
        return false;
    }

    public void waitForMiliseconds(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean timeTrackingModalDisplayed(boolean expectation) {
        if (expectation) {
            wait.until(ExpectedConditions.visibilityOf(timerModal));
        } else {
            waitForElementGone(timerModal);
        }
        return elementFound(timerModal, "Time Tracking Modal");
    }
}
