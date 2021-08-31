package Pages;

import Base.BaseUtil;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonPage extends BaseUtil {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public CommonPage(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public boolean commonButton(String button) {
        List<WebElement> buttons = driver.findElements(By.xpath(
                "//button[normalize-space()=\"" + button + "\"]"));
        if (buttons.size() > 0) {
            for (WebElement btn : buttons) {
                if (btn.isDisplayed()) {
                    wait.until(ExpectedConditions.elementToBeClickable(btn));
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


    public boolean commonCheckBox(String checkBox) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[normalize-space(text())=\"" + checkBox + "\"]/ancestor::div[@class='checkbox']"))).click();
            return true;
        } catch (TimeoutException e) {
            return false;
        }

//        List<WebElement> checkBoxes = driver.findElements(By.xpath(
//                "//label[normalize-space(text())=\"" + checkBox + "\"]/ancestor::div[@class='checkbox']"));
//
//        if (checkBoxes.size() > 0) {
//            for (WebElement chkbx : checkBoxes) {
//                if (chkbx.isEnabled()) {
//                    js.executeScript("arguments[0].click()", chkbx);
//                    return true;
//                }
//            }
//        }
//        return false;
    }


    public WebElement commonField(String field) {
        WebElement selectedField = null;
        List<WebElement> fields = driver.findElements(By.xpath(
                "//label[normalize-space(text())=\"" + field + "\"]/following-sibling::input"));

        if (fields.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedField == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                for (WebElement fld : fields) {
                    if (fld.isDisplayed()) {
                        selectedField = fld;
                        break;
                    }
                }
            }
        }
        return selectedField;
    }


    public boolean commonFieldEnter(String field, String text) {
        WebElement selectedField = commonField(field);

        if (selectedField == null) {
            return false;
        }
        commonFieldEmpty(selectedField);
        selectedField.sendKeys(text);
        return true;
    }

    public void commonFieldEmpty(WebElement selectedField) {
        selectedField.click();
        String os = System.getProperty("os.name");
        if (os.startsWith("Mac")) {
            selectedField.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        } else {
            selectedField.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        }
        selectedField.sendKeys(Keys.DELETE);
    }

    public String commonFieldRead(String field) {
        WebElement selectedField = commonField(field);
        return commonFieldRead(selectedField);
    }

    public String commonFieldRead(WebElement field) {
        if (field == null) {
            return null;
        }

        try {
            if (field.getAttribute("value") == null) {
                return field.getText();
            }
            return field.getAttribute("value");
        } catch (NullPointerException e) {
            return null;
        }
    }


    public boolean commonSelectorPick(String selector, String entry) {
        WebElement selectedSelector = commonField(selector);

        if (selectedSelector == null) {
            return false;
        }

        selectedSelector.click();
        selectedSelector.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedSelector.sendKeys(entry);
        selectedSelector.sendKeys(Keys.ENTER);
//        WebElement selection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@role=\"menuitem\"]/descendant::div/div[normalize-space()=\""+entry+"\"]")));
//        js.executeScript("arguments[0].click()", selection);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@role=\"menuitem\"]/descendant::div/div[normalize-space()=\""+entry+"\"]"))).click();
        return true;
    }

    public WebElement commonTextArea(String textArea) {
        WebElement selectedTextArea = null;
        List<WebElement> textAreas = driver.findElements(By.xpath(
                "//label[normalize-space(text())=\"" + textArea + "\"]/following-sibling::textarea"));

        if (textAreas.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedTextArea == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement txtA : textAreas) {
                    if (txtA.isDisplayed()) {
                        selectedTextArea = txtA;
                    }
                }
            }
        }
        return selectedTextArea;
    }


    public boolean commonTextAreaEnter(String textArea, String text) {
        WebElement selectedTextArea = commonTextArea(textArea);

        if (selectedTextArea == null) {
            return false;
        }

        selectedTextArea.click();
        selectedTextArea.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedTextArea.sendKeys(text);
        return true;
    }


    public String commonTextAreaRead(String textArea) {
        WebElement selectedTextArea = commonTextArea(textArea);
        try {
            if (selectedTextArea.getAttribute("value") == null) {
                return selectedTextArea.getText();
            }
            return selectedTextArea.getAttribute("value");
        } catch (NullPointerException e) {
            return e.toString();
        }

    }


    public WebElement commonDropDown(String dropDown) {
        WebElement selectedList = null;

        List<WebElement> lists = driver.findElements(By.xpath(
                "//label[normalize-space()=\"" + dropDown + "\"]/following::button[1]"));

        if (lists.size() > 0) {
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
        selectDropDown(dropDown);
        try {

            WebElement choice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@role=\"menuitem\"]/descendant::div[normalize-space()=\"" + selection + "\"]")));

            js.executeScript("arguments[0].click()", choice);

        } catch (ElementClickInterceptedException e) {

            clickErrorHandle(e.toString(), driver.findElement(By.xpath("//span[@role=\"menuitem\"]/descendant::div[normalize-space()=\"" + selection + "\"]")));
        }

        return true;
    }

    public void selectDropDown(String dropDown) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()=\"" + dropDown + "\"]/following::button[1]"))).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), driver.findElement(By.xpath("//label[normalize-space()=\"" + dropDown + "\"]/following::button[1]")));
        }
    }

    public boolean commonDropDownSelectByIndex(String dropDown, int index) {
        selectDropDown(dropDown);
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role=\"menu\"]/descendant::span[position()=" + index + "]"))).click();
        } catch (ElementClickInterceptedException e) {

            clickErrorHandle(e.toString(), driver.findElement(By.xpath("//div[@role=\"menu\"]/descendant::span[position()=" + index + "]")));
        }

        return true;
    }


    public String commonDropDownRead(String dropDown) {
        WebElement selectedList = commonDropDown(dropDown);

        if (selectedList == null) {
            return null;
        }

        try {
            Select list = new Select(selectedList);
            return list.getFirstSelectedOption().getText();
        } catch (NullPointerException e) {
            return null;
        }

    }

    public boolean commonMultiSelect(String multiSelect, List<String> picks) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()=\"" + multiSelect + "\"]/following::button[1]"))).click();

        picks.forEach((entry) -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@role=\"menuitem\"]/descendant::div[normalize-space()=\"" + entry + "\"]"))).click();
        });

        driver.findElement(By.xpath("//body")).click();
        return true;
    }

    public WebElement commonDate(String datePicker) {
        WebElement selectedPicker = null;
        try {
            selectedPicker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space(text())=\"" + datePicker + "\"]/following-sibling::input")));
        } catch (TimeoutException ignored) {

        }

        return selectedPicker;
    }

    private int monthReturn(String monthCheck) {
        switch (monthCheck.toLowerCase()) {
            case "january":
                return 0;
            case "february":
                return 1;
            case "march":
                return 2;
            case "april":
                return 3;
            case "may":
                return 4;
            case "june":
                return 5;
            case "july":
                return 6;
            case "august":
                return 7;
            case "september":
                return 8;
            case "october":
                return 9;
            case "november":
                return 10;
            case "december":
                return 11;
            default:
                System.out.println("could not find " + monthCheck + " month");
                return -1;
        }
    }

    public boolean commonDatePick(String datePicker, String day, String month, String year) {
        SimpleDateFormat curDate = new SimpleDateFormat("MMMM yyyy");
        WebElement selectedPicker = commonDate(datePicker);
        int monthInt = monthReturn(month);


        if (selectedPicker == null || monthInt == -1) {
            return false;
        }
        String pickerXPath;
        String dfVal = selectedPicker.getAttribute("value");
        if (!dfVal.isEmpty()) {
            String[] dateArr = dfVal.split("-");
            pickerXPath = new DateFormatSymbols().getMonths()[Integer.parseInt(dateArr[1]) - 1] + " " + Integer.parseInt(dateArr[0]);

        } else {
            pickerXPath = curDate.format(new Date());
        }
        selectedPicker.click();
        String pickerPath = generateXPATH(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"" + pickerXPath + "\"]"))), "");
        System.out.println("pickerPath: " + pickerPath);
        WebElement pickerMonthYear = driver.findElement(By.xpath(pickerPath));

        String curMY = pickerMonthYear.getText().replaceAll("\\s+", "");
        String curMonth = curMY.replaceAll("\\d", "");
        String curYear = curMY.replaceAll("\\D", "");

        driver.findElement(By.xpath("//div[text()=\"" + curYear + "\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"" + year + "\"]/ancestor::button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"" + curMonth + " " + year + "\"]")));
        int monthDist = monthReturn(curMonth) - monthInt;
        if (Integer.signum(monthDist) > 0) {
            for (int i = 0; i < monthDist; i++) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pickerPath + "/preceding::button[1]"))).click();
//
            }
        } else {
            for (int i = 0; i < Math.abs(monthDist); i++) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pickerPath + "/following::button[1]"))).click();
//
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = \"" + day + "\"]/ancestor::button[@type='button']"))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text() = \"" + day + "\"]/ancestor::button[@type='button']")));


        return true;
    }


    public boolean commonDatePick(String datePicker, Map<String, String> selections) {
        WebElement selectedPicker = commonDate(datePicker);

        if (selectedPicker == null) {
            return false;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(selectedPicker)).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedPicker);
        }

        pageLoaded();

        WebElement pickerMonthYear = null;
        List<WebElement> pMY = driver.findElements(By.xpath("//div[@class='datetimepicker-days']/descendant::th[@class='switch']"));
        for (WebElement ele : pMY) {
            if (ele.isDisplayed()) {
                pickerMonthYear = ele;
            }
        }
        String hour = selections.get("Time").replaceAll("(:\\d\\d)", "");
        WebElement pickerYear = null;
        WebElement pickerNext = null;
        WebElement pickerPrev = null;

        assert pickerMonthYear != null;
        String curMY = pickerMonthYear.getText().replaceAll("\\s+", "");
        String curMonth = curMY.replaceAll("\\d", "");
        String curYear = curMY.replaceAll("\\D", "");

        if (!selections.get("Month").equalsIgnoreCase(curMonth) || !selections.get("Year").equalsIgnoreCase(curYear)) {
            pickerMonthYear.click();
            List<WebElement> pY = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='switch']"));
            for (WebElement ele : pY) {
                if (ele.isDisplayed()) {
                    pickerYear = ele;
                }
            }
            List<WebElement> pN = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='next']"));
            for (WebElement ele : pN) {
                if (ele.isDisplayed()) {
                    pickerNext = ele;
                }
            }
            List<WebElement> pP = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='prev']"));
            for (WebElement ele : pP) {
                if (ele.isDisplayed()) {
                    pickerPrev = ele;
                }
            }
            assert pickerYear != null;

            if (!pickerYear.getText().equalsIgnoreCase(selections.get("Year"))) {
                assert pickerPrev != null;
                assert pickerNext != null;
                int myYr = Integer.parseInt(selections.get("Year"));
                long startTime = System.currentTimeMillis();
                while (!pickerYear.getText().equalsIgnoreCase(selections.get("Year")) && (System.currentTimeMillis() - startTime) < 30000) {
                    int pickYr = Integer.parseInt(pickerYear.getText());
                    if (pickYr < myYr) {
                        pickerNext.click();
                    } else {
                        pickerPrev.click();
                    }
                }

            }
            List<WebElement> pM = driver.findElements(By.xpath(
                    "//div[@class='datetimepicker-months']/descendant::span[contains(@class, 'month') " +
                            "and text()=\"" + selections.get("Month").substring(0, 3) + "\"]"));
            for (WebElement ele : pM) {
                if (ele.isDisplayed()) {
                    ele.click();
                    break;
                }
            }

        }
        List<WebElement> pD = driver.findElements(By.xpath("//div[@class='datetimepicker-days']" +
                "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()=\"" + selections.get("Day") + "\"]"));
        for (WebElement ele : pD) {
            if (ele.isDisplayed()) {
                ele.click();
            }
        }
        List<WebElement> pH = driver.findElements(By.xpath(
                "//div[@class='datetimepicker-hours']/descendant::legend[normalize-space()=\"" + selections.get("AM / PM") + "\"]/following-sibling::span[normalize-space()=\"" + hour + "\"]"));
        for (WebElement ele : pH) {
            if (ele.isDisplayed()) {
                ele.click();
                break;
            }
        }
        List<WebElement> pMi = driver.findElements(By.xpath(
                "//div[@class='datetimepicker-minutes']/descendant::legend[normalize-space()=\"" + selections.get("AM / PM") + "\"]/following-sibling::span[normalize-space()=\"" + selections.get("Time") + "\"]"));
        for (WebElement ele : pMi) {
            if (ele.isDisplayed()) {
                ele.click();
                break;
            }
        }

        return true;
    }

    public String commonDateRead(String datePicker) {
        WebElement selectedPicker = commonDate(datePicker);

        return selectedPicker.getText();
    }

    public String generateXPATH(WebElement childElement, String current) {
        String childTag = childElement.getTagName();
        if (childTag.equals("html")) {
            return "/html[1]" + current;
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for (int i = 0; i < childrenElements.size(); i++) {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if (childTag.equals(childrenElementTag)) {
                count++;
            }
            if (childElement.equals(childrenElement)) {
                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
            }
        }
        return null;
    }


    public void clickErrorHandle(String error, WebElement target) {
        System.err.println(error);
        String xPath = "";
        String selector = "";
        Pattern element = Pattern.compile("(?<=click: \\<)(.*?)(?=\\s*\\>)");
        Pattern type = Pattern.compile("^\\w+");
        Pattern tag = Pattern.compile("\\w+=+\"(.*?)\"");
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
        System.out.println("BLOCKING XPATH: " + xPath);
        List<WebElement> blockers = driver.findElements(By.xpath(xPath));
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(blockers));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
        } catch (TimeoutException ignored) {

        }

        target.click();
    }

    public boolean pageLoaded() {
        // wait for jQuery to load
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

        try {
            return wait.until(jQueryLoad) && wait.until(jsLoad);
        } catch (TimeoutException e) {
            System.err.println("Timeout exception occurred for pageLoaded");
        }
        return false;


    }

    public boolean scrollPageVertically(int pix) {
        js.executeScript("window.scrollBy(0," + pix + ")");
        return true;
    }

    public void sendKeysToElement(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            System.out.println("Successfully Sent the following keys: '" + text + "' to element: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following keys: " + text);
            Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
        }
    }

    public void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            System.out.println("WebElement: " + "<" + element.toString() + "> is successfully clicked.");
        } catch (Exception e) {
            System.out.println("Unable to click the WebElement, Exception: " + e.getMessage());
            Assert.fail("Unable to click the WebElement" + "<" + element.toString() + "> using locator: " + "<" + element.toString() + ">");
        }
    }

    public void switchTab(int index) {
        List<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        if (availableWindows.size() >= index + 1) {
            driver.switchTo().window(availableWindows.get(index)); // switching to new tab for password setting
            return;
        }
        Assert.fail("Unable to switch tab to indexed " + index + " out of " + availableWindows.size() + " tabs.");
    }

    // common method for time bound explicit wait
    public void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }


    public void setCurrentLayerAndUser() {
        String url = driver.getCurrentUrl();
        if (url.endsWith("qcarrier/#/home")) {
            valueStore.put("currentLayer", "Carrier");
        } else if (url.endsWith("qowners/#/home")) {
            valueStore.put("currentLayer", "Owner");
        } else if (url.endsWith("qagency/#/home")) {
            valueStore.put("currentLayer", "Agency");
        }
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'qbisAvatarUserName')]")));
        String user = element.getText();
        user = user.substring(user.indexOf("/") + 1);
        System.out.println("Logged In user is: " + user);
    }
}
