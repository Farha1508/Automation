package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.BaseUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
        List<WebElement> buttons1 = driver.findElements(By.xpath(
                "//button[normalize-space()='" + button + "']"));
        List<WebElement> buttons2 = driver.findElements(By.xpath(
                "//h4[normalize-space()='" + button + "']"));

        if (buttons1.size() > 0) {
            for (WebElement btn : buttons1) {
                if (btn.isDisplayed() && btn.isEnabled()) {
                    try {
                        js.executeScript("arguments[0].click()", btn);
                    } catch (ElementClickInterceptedException e) {
                        clickErrorHandle(e.toString(), btn);
                    }

                    return true;
                }
            }
        } else if (buttons2.size() > 0) {
            for (WebElement btn : buttons2) {
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

    public boolean clickCloseWOButton() {

        List<WebElement> buttons1 = driver.findElements(By.xpath(
                "//a[@id='work_entry_close']"));

        if (buttons1.size() > 0) {
            for (WebElement btn : buttons1) {
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

    public boolean commonButtonStatus(String button) {
        List<WebElement> buttons = driver.findElements(By.xpath(
                "//button[normalize-space()='" + button + "']"));

        if (buttons.size() > 0) {
            for (WebElement btn : buttons) {
                try {
                    if (btn.getAttribute("disabled").equals("true")) {

                        return true;
                    }
                } catch (NullPointerException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean commonCheckBox(String checkBox) {
        List<WebElement> checkBoxes1 = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + checkBox + "']/input[@type='checkbox' and @class='badgebox']"));
        List<WebElement> checkBoxes2 = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + checkBox + "']/following-sibling::*//input[@type='checkbox']"));
        List<WebElement> checkBoxes3 = driver.findElements(By.xpath(
                "//label[normalize-space()='" + checkBox + "']/input[@type='checkbox']"));

        if (checkBoxes1.size() > 0) {
            for (WebElement chkbx : checkBoxes1) {
                if (chkbx.isEnabled()) {
                    js.executeScript("arguments[0].click()", chkbx);
                    return true;
                }
            }
        } else if (checkBoxes2.size() > 0) {
            for (WebElement chkbx : checkBoxes2) {
                if (chkbx.isDisplayed() && chkbx.isEnabled()) {
                    js.executeScript("arguments[0].click()", chkbx);

                    return true;
                }
            }
        } else if (checkBoxes3.size() > 0) {
            for (WebElement chkbx : checkBoxes3) {
                if (chkbx.isDisplayed() && chkbx.isEnabled()) {
                    js.executeScript("arguments[0].click()", chkbx);

                    return true;
                }
            }
        }
        return false;
    }

    public boolean timeModalStart() {

        try {
            List<WebElement> checkBoxes1 = driver.findElements(By.xpath(
                    "//div[normalize-space()='Time Tracking']/following-sibling::*//button[normalize-space()='Start']"));
            for (WebElement chkbx : checkBoxes1) {
                if (chkbx.isEnabled()) {
                    js.executeScript("arguments[0].click()", chkbx);
                    return true;
                }
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("Cannot find the Start button of the Time Tracking Modal" + nsee);
        }
        return false;
    }


    public WebElement commonField(String field) {
        WebElement selectedField = null;
        List<WebElement> fields3 = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + field + "']/following-sibling::input"));
        List<WebElement> fields2 = driver.findElements(By.xpath("//label[translate(.,'\u00A0','') ='" + field + "']/following-sibling::input"));
        // List to handle fields in the Time Tracking pop-up
        List<WebElement> fields = driver.findElements(By.xpath(
                "//label[normalize-space()='" + field + "']/following-sibling::div/input"));
        List<WebElement> fields4 = driver.findElements(By.xpath(
                "//label[normalize-space()='" + field + "']/following-sibling::textarea"));
        List<WebElement> fields5 = driver.findElements(By.xpath(
                "//label[contains(text(),'" + field + "')]/following-sibling::div/input"));

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
        } else if (fields2.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedField == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                for (WebElement fld : fields2) {
                    if (fld.isDisplayed()) {
                        selectedField = fld;
                        break;
                    }
                }
            }
        } else if (fields3.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedField == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                for (WebElement fld : fields3) {
                    if (fld.isDisplayed()) {
                        selectedField = fld;
                        break;
                    }
                }
            }
        } else if (fields4.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedField == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                for (WebElement fld : fields4) {
                    if (fld.isDisplayed()) {
                        selectedField = fld;
                        break;
                    }
                }
            }
        }
        else if (fields5.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedField == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                for (WebElement fld : fields5) {
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
        selectedField.sendKeys(Keys.TAB);

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
        List<WebElement> lists2 = driver.findElements(By.xpath(
                "//label[text()[normalize-space()='" + dropDown + "']]/following-sibling::select"));
        // This list will find the drop downs in a table
        List<WebElement> lists3 = driver.findElements(By.xpath(
                "//td[contains(normalize-space(), '" + dropDown + "')]/following-sibling::td/select"));

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
        } else if (lists3.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedList == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement lst : lists3) {
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


    public boolean commonDatePick(String datePicker, String day, String month, String year) {
        WebElement selectedPicker = commonDate(datePicker);

        if (selectedPicker == null) {
            return false;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(selectedPicker)).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedPicker);
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


    public WebElement taskCompleteChk() {
        WebElement taskComplete = driver.findElement(By.xpath(
                "//label[normalize-space(text())='Task Completed']/input[@type='checkbox' and @class='badgebox']"));
        return taskComplete;
    }

    public WebElement elementByText(String text) {
        WebElement e = driver.findElement(By.xpath("//*[text()='"+text+"\']"));

        return e;
    }

    public WebElement errorIsVisible() {
        WebElement e = null;

        try {
            e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.//text()[normalize-space()='Fill the Required Fields.']]")));
            System.out.println("Found submit error");
            return e;
        } catch (TimeoutException toe) {
            System.out.println("Timed out after 10 seconds");
            return e;
        }

    }

    public WebElement maxLengthVisible() {
        WebElement e = null;
        try {
            e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("maxLengthError")));
            System.out.println("Found Max Length error");
            return e;
        } catch (TimeoutException toe) {
            System.out.println("Timed out after 10 seconds");
            return e;
        }

    }

    public boolean mandatoryField(String field) {
        try{
            return driver.findElement(By.xpath(
                    "//label[normalize-space(text())='" + field + "']/span")).isDisplayed();
        } catch (NoSuchElementException ignored) {

        }
        return false;
    }

    public boolean reminderDateChecker(String setDate, String reminderDate, String setting) {

        String[] reminderDateArray = reminderDate.split("\\s+", 2);
        String[] startDateArray = setDate.split("\\s+", 2);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        int dayAdd = 0;
        Calendar c = Calendar.getInstance();
        switch (setting.toLowerCase(Locale.ROOT)) {
            case "request date":
                System.out.println("Request Date needs a 60 day reminder");
                dayAdd = 60;
                break;
            case "1st follow up":
                System.out.println("1st Follow Up needs a 15 day reminder");
                dayAdd = 15;
                break;
            case "2nd follow up":
                System.out.println("2nd Follow Up needs a 15 day reminder");
                dayAdd = 15;
                break;
            case "3rd follow up":
                System.out.println("3rd Follow Up needs a 15 day reminder");
                dayAdd = 15;
                break;
            default:
                System.out.println("Next Reminder setting must either be \"Request Date\" or \"1st Follow Up\" or \"2nd Follow Up\" or \"3rd Follow Up\"");
                return false;
        }
        try {
            c.setTime(sdf.parse(startDateArray[0]));
        } catch(ParseException e){
            e.printStackTrace();
        }

        c.add(Calendar.DAY_OF_MONTH, dayAdd);
        String calculatedReminderDate = sdf.format(c.getTime());
        String reinderDateCompare = reminderDateArray[0];
        System.out.println("triggering date is: " + startDateArray[0] + " next reminder date should be: " + calculatedReminderDate + " Actual next reminder date is: " + reinderDateCompare+"!");
        System.out.println(calculatedReminderDate.equals(reinderDateCompare));
        return calculatedReminderDate.equals(reinderDateCompare);
    }

    public boolean rushDueDateChecker(String startDate, String dueDate, String setting) {

        // Ensure you set the start date to a monday to avoid weekends
        String[] dueDateArray = dueDate.split("\\s+", 2);
        String[] startDateArray = startDate.split("\\s+", 2);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        int dayAdd = 0;
        Calendar c = Calendar.getInstance();
        if(setting.toLowerCase(Locale.ROOT).equals("enabled")){
            System.out.println("Rush setting is enabled");
            dayAdd = 2;
        } else if (setting.toLowerCase(Locale.ROOT).equals("disabled")) {
            System.out.println("Rush setting is disabled");
            dayAdd = 3;
        } else {
            System.out.println("rush setting must either be \"enabled\" or \"disabled\"");
            return false;
        }
        try {
            c.setTime(sdf.parse(startDateArray[0]));
        } catch(ParseException e){
            e.printStackTrace();
        }

        c.add(Calendar.DAY_OF_MONTH, dayAdd);
        String startDateRush = sdf.format(c.getTime());
        String dueDateCompare = dueDateArray[0];
        System.out.println("Add date is: " + startDateArray[0] + " Expected Due Date should be: " + startDateRush + " Actual Due Date is: " + dueDateCompare+"!");
        System.out.println(startDateRush.equals(dueDateCompare));
        return startDateRush.equals(dueDateCompare);
    }

    public String emailSentDateChecker() {
        LocalDate now = LocalDate.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return formattedDate;
    }

    public String dateTrimmer(String date) {
        String[] historyDateArray = date.split("\\s+", 2);
        String historyDateTrimmed = historyDateArray[0];
        return historyDateTrimmed;
    }

    public void addWOTabs(String tabName) {
        WebElement tab = driver.findElement(By.xpath("//a[.//text()[normalize-space()='" + tabName + "']]"));
        tab.click();
    }

    public boolean woTabsVisbility(String tabName) {

        boolean tabVisibility;

        tabVisibility = driver.findElement(By.xpath("//a[.//text()[normalize-space()='" + tabName + "']]")).isDisplayed();
        System.out.println("Tab visibility is: " + tabVisibility);

        return tabVisibility;
    }

    public boolean isAttributePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {}

        return result;
    }

    public void formHistoryViewSelection(String option) {
        System.out.println("Selecting " + option + " from Viewing dropdown");
        WebElement dropDown = driver.findElement(By.xpath("//*[@id='dthistory_length']/label/select"));
        Select rows = new Select(dropDown);
        dropDown.click();
        rows.selectByVisibleText(option);
    }

    public boolean commonFieldClear(String field) {
        // Cannot use the commonFieldEnter() method with a blank string to clear a field.
        // commonField() method does not find these fields.
        WebElement selectedField = commonField(field);
        //WebElement selectedPicker = commonDate(datePicker);

        if (selectedField == null) {
            // commonField() cannot find Time Rec Admin fields, for some reason. Adding in special exceptions.
            if (field.equals("To")) {
                selectedField = driver.findElement(By.id("startTimeTo"));
            } else if (field.equals("From")) {
                selectedField = driver.findElement(By.id("startTimeFrom"));
            } else {
                return false;
            }
        }
        selectedField.click();
        selectedField.clear();
        return true;
    }


}
