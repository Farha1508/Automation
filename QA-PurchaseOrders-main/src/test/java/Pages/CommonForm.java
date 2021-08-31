package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.BaseUtil;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonForm {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public CommonForm(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
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
    public WebElement commonButtonGet(String button) {
        // This method returns the button element without clicking it (which the commonButton() method does).
        List<WebElement> buttons = driver.findElements(By.xpath(
                "//button[normalize-space()='" + button + "']"));

        if (buttons.size() > 0) {
            for (WebElement btn : buttons) {
                if (btn.isDisplayed() && btn.isEnabled()) {
                    return btn;
                }
            }
        }
        return null;
    }

    public void commonLinkClick(String linkText) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), driver.findElement(By.linkText(linkText)));
        }

    }

    public WebElement commonField(String field) {
        WebElement selectedField = null;
        List<WebElement> fields = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + field + "']/following-sibling::input"));
        fields.addAll(driver.findElements(By.xpath(
                "//label[translate(.,'\u00A0','') ='" + field + "']/following-sibling::input")));

        // List to handle fields in the Time Tracking pop-up
        fields.addAll(driver.findElements(By.xpath(
                "//label[normalize-space()='" + field + "']/following-sibling::div/input")));

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

        selectedField.click();
        selectedField.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedField.sendKeys(text);

        return true;
    }


    public String commonFieldRead(String field) {
        WebElement selectedField = commonField(field);

        if (selectedField == null) {
            return null;
        }

        try {
            if (selectedField.getAttribute("value") == null) {
                return selectedField.getText();
            }
            return selectedField.getAttribute("value");
        } catch (NullPointerException e) {
            return null;
        }
    }


    public WebElement commonTextArea(String textArea) {
        WebElement selectedTextArea = null;
        List<WebElement> textAreas = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + textArea + "']/following-sibling::textarea"));

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
        // This list is to compensate for the Time Tracking pop-up in Work Order Tracking
        List<WebElement> lists = driver.findElements(By.xpath(
                "//label[normalize-space()='" + dropDown + "']/following-sibling::div/select"));
        // This list will find the drop downs in the rest of the apps

        lists.addAll(driver.findElements(By.xpath(
                "//label[text()[normalize-space()='" + dropDown + "']]/following-sibling::select")));

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

    public boolean commonDropDownSelect(WebElement dropDown, String selection) {

        if (dropDown == null) {
            return false;
        }

        try {
            Select list = new Select(dropDown);
            list.selectByVisibleText(selection);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

        return true;
    }

    public boolean commonDropDownSelect(String dropDown, String selection) {
        WebElement selectedList = commonDropDown(dropDown);
        return commonDropDownSelect(selectedList, selection);


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


    public WebElement commonDate(String datePicker) {
        WebElement selectedPicker = null;
        List<WebElement> pickers = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + datePicker + "']/following-sibling::div//span[@class='glyphicon glyphicon-calendar']"));

        if (pickers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedPicker == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement pkr : pickers) {
                    if (pkr.isDisplayed()) {
                        selectedPicker = pkr;
                    }
                }
            }
        }
        return selectedPicker;
    }

    /**
     * Sets the date for date pickers. Use this for date pickers that do not need time to be set.
     * For pickers that need time set use {@link #commonDatePick(String, Map) commonDatePick}
     *
     * @param datePicker Name of the date picker to manipulate.
     * @param day        Day of the month.
     * @param month      Full name of the month.
     * @param year       Four-digit year as a String.
     * @return Returns true if process completed with no errors, otherwise returns false.
     */
    public boolean commonDatePick(String datePicker, String day, String month, String year) {
        WebElement selectedPicker = commonDate(datePicker);
        return commonDatePick(selectedPicker, day, month, year);
    }

    /**
     * Sets the date for date pickers without a time component using a WebElement locator.
     * For finding the date picker with the name, use {@link #commonDatePick(String, String, String, String) commonDatePick}
     *
     * @param datePicker WebElement locator of the date picker.
     * @param day        Day of the month.
     * @param month      Full name of the month.
     * @param year       Four-digit year as a String.
     * @return Returns true if process completed with no errors, otherwise returns false.
     */
    public boolean commonDatePick(WebElement datePicker, String day, String month, String year) {
        month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        if (datePicker == null) {
            return false;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(datePicker)).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), datePicker);
        }

        BaseUtil.pageLoaded();

        WebElement pickerMonthYear;
        try {
            pickerMonthYear = driver.findElement(By.xpath("//div[@class='datepicker-days']/descendant::th[@class='datepicker-switch']"));
        } catch (NoSuchElementException e) {
            return false;
        }

        WebElement pickerYear = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='datepicker-switch']"));
        WebElement pickerNext = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='next']"));
        WebElement pickerPrev = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='prev']"));

        String curMY = pickerMonthYear.getText().replaceAll("\\s+", "");
        String curMonth = curMY.replaceAll("\\d", "");
        String curYear = curMY.replaceAll("\\D", "");

        if (!month.equalsIgnoreCase(curMonth) || !year.equalsIgnoreCase(curYear)) {
            pickerMonthYear.click();
            wait.until(ExpectedConditions.visibilityOf(pickerYear));
            if (!pickerYear.getText().equalsIgnoreCase(year)) {
                int myYr = Integer.parseInt(year);
                long startTime = System.currentTimeMillis();
                while (!pickerYear.getText().equalsIgnoreCase(year) && (System.currentTimeMillis() - startTime) < 30000) {
                    int pickYr = Integer.parseInt(pickerYear.getText());
                    if (pickYr < myYr) {
                        pickerNext.click();
                    } else {
                        pickerPrev.click();
                    }
                }

            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='datepicker-months']/descendant::span[contains(@class, 'month') " +
                            "and text()='" + month.substring(0, 3) + "']"))).click();


        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='datepicker-days']" +
                "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()='" + day + "']"))).click();

        return true;
    }

    /**
     * Sets the date in date pickers that require time be included.
     * For date pickers that don't need time use {@link #commonDatePick(String, String, String, String) commonDatePick}
     *
     * @param datePicker Name of the date picker to be manipulated.
     * @param date       A Map containing date to enter. Checks for the following keys:
     *                   "Year", "Month", "Day", "Time", "AM / PM"
     * @return Returns true if process completed with no errors, otherwise returns false.
     */
    public boolean commonDatePick(String datePicker, Map<String, String> date) {
        WebElement selectedPicker = commonDate(datePicker);
        return commonDatePick(selectedPicker, date);
    }

    /**
     * Sets the date for date pickers that have a time component using a WebElement locator.
     * For finding the date picker with the name use {@link #commonDatePick(String, Map) commonDatePick}
     *
     * @param datePicker WebElement locator of the date picker.
     * @param date       A Map containing the date to enter. Checks for the following keys:
     *                   "Year", "Month", "Day", "Time", "AM / PM"
     * @return Returns true if process completed with no errors, otherwise returns false.
     */
    public boolean commonDatePick(WebElement datePicker, Map<String, String> date) {
        Map<String, String> selections = new HashMap<>(date);
        selections.put("Month", selections.get("Month").substring(0, 1).toUpperCase() + selections.get("Month").substring(1).toLowerCase());
        if (datePicker == null) {
            return false;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(datePicker)).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), datePicker);
        }

        BaseUtil.pageLoaded();

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
                            "and text()='" + selections.get("Month").substring(0, 3) + "']"));
            for (WebElement ele : pM) {
                if (ele.isDisplayed()) {
                    ele.click();
                    break;
                }
            }

        }
        List<WebElement> pD = driver.findElements(By.xpath("//div[@class='datetimepicker-days']" +
                "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()='" + selections.get("Day") + "']"));
        for (WebElement ele : pD) {
            if (ele.isDisplayed()) {
                ele.click();
            }
        }
        List<WebElement> pH = driver.findElements(By.xpath(
                "//div[@class='datetimepicker-hours']/descendant::legend[normalize-space()='" + selections.get("AM / PM") + "']/following-sibling::span[normalize-space()='" + hour + "']"));
        for (WebElement ele : pH) {
            if (ele.isDisplayed()) {
                ele.click();
                break;
            }
        }
        List<WebElement> pMi = driver.findElements(By.xpath(
                "//div[@class='datetimepicker-minutes']/descendant::legend[normalize-space()='" + selections.get("AM / PM") + "']/following-sibling::span[normalize-space()='" + selections.get("Time") + "']"));
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

    /**
     * Sets date to one in the future or past of the current date.
     * Locates date picker by name. To locate date picker by WebElement
     * user {@link #commonDateShift(WebElement, int, String, String, boolean) commonDateShift}
     *
     * @param datePicker Name of the date picker to be manipulated.
     * @param count      Number of units you want to shift the date by.
     * @param units      Type of units used to move date. Accepts "days", "months", and "years".
     * @param direction  Which way to move the date. Accepts "past" and "future".
     * @param hasTime    If the date picker needs to have time set, use true. Otherwise use false.
     * @return Returns true if process completed with no errors, otherwise returns false.
     */
    public boolean commonDateShift(String datePicker, int count, String units, String direction, boolean hasTime) {
        WebElement selectedPicker = commonDate(datePicker);
        if (hasTime) return commonDateShift(selectedPicker, count, units, direction, true);

        return commonDateShift(selectedPicker, count, units, direction, false);

    }

    /**
     * Sets date to one in the future or past of the current date.
     * Uses WebElement locator to find date picker. To find picker by name
     * use {@link #commonDateShift(String, int, String, String, boolean) commonDateShift}
     *
     * @param datePicker WebElement locator of the date picker to be manipulated.
     * @param count      Number of units you want to shift the date by.
     * @param units      Type of units used to move date. Accepts "days", "months", and "years".
     * @param direction  Which way to move the date. Accepts "past" and "future".
     * @param hasTime    If the date picker needs to have time set, use true. Otherwise use false.
     * @return Returns true if process completed with no errors, otherwise returns false.
     */
    public boolean commonDateShift(WebElement datePicker, int count, String units, String direction, boolean hasTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MMMM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        HashMap<String, Integer> period = new HashMap<>();
        period.put("years", 0);
        period.put("months", 0);
        period.put("days", 0);
        period.put(units.toLowerCase(), count);
        LocalDateTime adjust;
        if (direction.equalsIgnoreCase("past")) {
            adjust = now.minus(Period.of(period.get("years"), period.get("months"), period.get("days")));
        } else {
            adjust = now.plus(Period.of(period.get("years"), period.get("months"), period.get("days")));
        }
        String[] dateArr = adjust.format(format).split("/");
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put("Day", dateArr[0]);
        dateMap.put("Month", dateArr[1]);
        dateMap.put("Year", dateArr[2]);
        dateMap.put("Time", "12:00");
        dateMap.put("AM / PM", "PM");
        if (hasTime) return commonDatePick(datePicker, dateMap);

        return commonDatePick(datePicker, dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));

    }


    public WebElement commonCheckBox(String checkBox) {
        List<WebElement> checkBoxes = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + checkBox + "']/input[@type='checkbox' and @class='badgebox']"));
        checkBoxes.addAll(driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + checkBox + "']/following-sibling::*//input[@type='checkbox']")));
        checkBoxes.addAll(driver.findElements(By.xpath(
                "//label[normalize-space()='" + checkBox + "']/input[@type='checkbox']")));

        if (checkBoxes.size() > 0) {
            for (WebElement chkbx : checkBoxes) {
                if (chkbx.isEnabled()) {
                    return chkbx;
                }
            }
        }
        return null;
    }


    public boolean commonCheckBoxClick(String checkBox) {
        WebElement chkbx = commonCheckBox(checkBox);
        if (chkbx != null) {
            js.executeScript("arguments[0].click()", chkbx);
            return true;
        }
        return false;
    }


    public boolean commonCheckBoxSelected(String checkBox) {
        WebElement chkbx = commonCheckBox(checkBox);
        if (chkbx != null) {
            return chkbx.isSelected();
        }
        System.out.println("Could not find checkbox: " + checkBox);
        return false;
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
        } catch (TimeoutException ignored) {

        }

        target.click();
    }


}
