package Base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class NodeApp implements ICommonElements {

    private final WebDriver driver;
    private static  JavascriptExecutor js;
    private static WebDriverWait wait;

    public NodeApp(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;

        wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public JavascriptExecutor getJs() {
        return js;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    @Override
    public void login(String user) {

    }

    @Override
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

    @Override
    public boolean commonCheckBox(String checkBox) {
        List<WebElement> checkBoxes1 = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + checkBox + "']/input[@type='checkbox' and @class='badgebox']"));
        List<WebElement> checkBoxes2 = driver.findElements(By.xpath(
                "//label[normalize-space(text())='" + checkBox + "']/following-sibling::*//input[@type='checkbox']"));
        List<WebElement> checkBoxes3 = driver.findElements(By.xpath(
                "//label[normalize-space()='"+checkBox+"']/input[@type='checkbox']"));

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

    @Override
    public WebElement commonField(String field) {
        WebElement selectedField = null;
        List<WebElement> fields3 = driver.findElements(By.xpath(
                "//label[normalize-space(text())=\"" + field + "\"]/following-sibling::input"));
        List<WebElement> fields2 = driver.findElements(By.xpath("//label[translate(.,'\u00A0','') =\"" + field + "\"]/following-sibling::input"));
        // List to handle fields in the Time Tracking pop-up
        List<WebElement> fields = driver.findElements(By.xpath(
                "//label[normalize-space()=\"" + field + "\"]/following-sibling::div/input"));

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
        }
        return selectedField;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
                    if (pkr.isEnabled()) {
                        selectedPicker = pkr;
                    }
                }
            }
        }
        return selectedPicker;
    }

    @Override
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

        pageLoaded();

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

    @Override
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

    @Override
    public String commonDateRead(String datePicker) {
        WebElement selectedPicker = commonDate(datePicker);

        return selectedPicker.getText();
    }

    // KPI Methods
    public abstract KpiClass addKpi(int number);

    public void clickKpi(String kpi) {
        WebElement kpiLink = driver.findElement(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]/descendant::text()[normalize-space()='" + kpi + "']/ancestor::a"));
        js.executeScript("arguments[0].click()", kpiLink);
        pageLoaded();
    }

    public ArrayList<KpiClass> getKpi() {
        ArrayList<KpiClass> kpiList = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]")));
        pageLoaded();
        List<WebElement> kpi = driver.findElements(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]"));
        KpiClass listEntry;
        String numExtraction = "";
        StringBuilder kpiValue;
        String title;
        int intValue;
        float floatValue;

        for (WebElement element : kpi) {
            title = element.findElement(By.className("desc")).getText().replaceAll("\\n", " ").trim();
            int incrementer = 0;
            List<WebElement> values;
            List<WebElement> v1 = element.findElements(By.xpath(".//descendant::div[@class='number']/div[@class='desc']"));
            List<WebElement> v2 = element.findElements(By.xpath(".//descendant::div[@class='number']/span[text()]"));
            List<WebElement> v3 = element.findElements(By.xpath(".//descendant::div[@class='number']/div/span[text()]"));
            if (v1.size() > 0) {
                values = v1;
                title = "";
            } else if (v2.size() > 0) {
                values = v2;
            } else {
                values = v3;
            }

            for (WebElement ele2 : values) {
                StringBuilder vName;
                String cTitle = "";
                List<WebElement> small = ele2.findElements(By.xpath(".//preceding-sibling::small"));
                if (!title.equals("")) {
                    cTitle = title;
                }

                if (small.size() > 1) {
                    numExtraction = ele2.getText();
                    if (cTitle.equals("")) {
                        cTitle = small.get(incrementer).getText().trim();
                    } else {
                        cTitle += " " + small.get(incrementer).getText().trim();
                    }
                    incrementer++;
                } else if (small.size() == 1) {
                    numExtraction = ele2.getText();
                    if (cTitle.equals("")) {
                        cTitle = small.get(incrementer).getText().trim();
                    } else {
                        cTitle += " " + small.get(0).getText().replaceAll("[^\\w\\s\\>\\/]", "").trim();
                    }
                    incrementer++;
                } else {
                    numExtraction = ele2.getText().replaceAll("\\n", "");

                }

                if (numExtraction.matches(".*\\d\\.\\d.*")) {
                    kpiValue = new StringBuilder();
                    vName = new StringBuilder();
                    for (int i = 0; i < numExtraction.length(); i++) {
                        if (Character.isDigit(numExtraction.charAt(i)) || numExtraction.charAt(i) == '.') {
                            kpiValue.append(numExtraction.charAt(i));
                        } else if (Character.isLetter(numExtraction.charAt(i)) || numExtraction.charAt(i) == ' ') {
                            vName.append(numExtraction.charAt(i));
                        }
                    }
                    if (vName.length() > 0) {
                        if (title.equals("")) {
                            cTitle = vName.toString().trim();
                        } else {
                            cTitle += " " + vName.toString().trim();
                        }

                    }
                    floatValue = Float.parseFloat(kpiValue.toString());
                    listEntry = new KpiClass(cTitle, floatValue);
                } else {
                    kpiValue = new StringBuilder();
                    vName = new StringBuilder();
                    for (int i = 0; i < numExtraction.length(); i++) {
                        if (Character.isDigit(numExtraction.charAt(i))) {
                            kpiValue.append(numExtraction.charAt(i));
                        } else if (Character.isLetter(numExtraction.charAt(i)) || numExtraction.charAt(i) == ' ') {
                            vName.append(numExtraction.charAt(i));
                        }
                    }
                    if (vName.length() > 0) {
                        if (title.equals("")) {
                            cTitle = vName.toString().trim();
                        } else {
                            cTitle += " " + vName.toString().trim();
                        }
                    }
                    intValue = Integer.parseInt(kpiValue.toString());
                    listEntry = new KpiClass(cTitle, intValue);
                }
                kpiList.add(listEntry);
            }

        }
        return kpiList;
    }

    // Grid methods
    public abstract boolean gridTab(String tabName);

    public abstract int gridPageNumber(String tabName, String result);

    public abstract int gridRandomPage(String tabName);

    public abstract boolean gridNextPage(String tabName);

    public abstract boolean gridPrevPage(String tabName);

    public abstract void gridViewSelection(String tabName, String option);

    public abstract int gridRecordNumber(String tabName);

    public abstract int gridRowCount(String tabName);

    public String gridHeaderEnter(String headerName, String input) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath("" +
                "//div[normalize-space(text())='" + headerName + "']/following-sibling::input"));


        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        }

        if (input == null) {
            assert selectedHeader != null;
            if (selectedHeader.getAttribute("value") == null) {
                return selectedHeader.getText();
            }
            return selectedHeader.getAttribute("value");

        }

        assert selectedHeader != null;
        try {
            selectedHeader.click();

        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        selectedHeader.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedHeader.sendKeys(input);
        selectedHeader.click();
        return null;
    }

    public WebElement gridHeaderSelector(String headerName) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath("//div[normalize-space(text())='" + headerName + "']/following-sibling::span/div/button"));

        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        } else {
            return null;
        }
        return selectedHeader;
    }

    public boolean gridHeaderSelectorSelect(String headerName, String selection) {
        WebElement selectedHeader = gridHeaderSelector(headerName);

        if (selectedHeader == null) {
            return false;
        }

        try {
            selectedHeader.click();

        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='" + selection + "']")))).click();
            selectedHeader.click();
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

        return true;

    }

    public ArrayList<String> gridHeaderSelectorRead(String headerName) {
        ArrayList<String> selections = new ArrayList<>();
        WebElement selectedHeader = gridHeaderSelector(headerName);
        if (selectedHeader == null) {
            return null;
        }
        selectedHeader.click();
        wait.until(ExpectedConditions.visibilityOf(selectedHeader.findElement(By.xpath("following-sibling::ul"))));
        List<WebElement> active = selectedHeader.findElements(By.xpath("following::li[@class='active']"));
        if (active.size() > 0) {
            for (WebElement element : active) {
                selections.add(element.getText().trim());
            }
        } else {
            selectedHeader.click();
            return null;
        }

        selectedHeader.click();
        return selections;
    }
    //public abstract boolean gridHeaderFind(String tab, String header);

    /**
     * Finds and returns the text in the grid cell that matches the requested column and row.
     *
     * @param tableRow    This is the row of the grid being requested. Set this to either the text in the first cell of the row
     *                    being requested (such as the work order number in purchase orders)
     *                    or the number of the row (such as "row 3")
     * @param tableColumn This is the column of the grid being requested. Set this to the header name for the column
     * @return Returns the text in the matching column and row cell of the grid.
     */
    public WebElement gridEntry(String tableRow, String tableColumn) {
        WebElement column = null;
        WebElement row = null;
        String rowPath;
        String colPath;
        String title = null;

        List<WebElement> titles = driver.findElements(By.xpath("//table[@id]"));
        for (WebElement ttlEle : titles) {
            if (ttlEle.isDisplayed() && ttlEle.getAttribute("id").length() > 0) {
                title = ttlEle.getAttribute("id");
                break;
            }
        }

        List<WebElement> setCol = driver.findElements(By.xpath(
                "//th/div[text()='" + tableColumn + "']"));
        for (WebElement colElement : setCol) {
            if (colElement.isDisplayed()) {
                column = colElement;
                break;
            }
        }

        assert column != null;
        colPath = generateXPATH(column, "");
        Pattern colP = Pattern.compile("(?<=th\\[)(\\d+)(?=\\])");
        assert colPath != null;
        Matcher colM = colP.matcher(colPath);
        while (colM.find()) {
            colPath = colM.group();
        }

        if (tableRow.toLowerCase().contains("row")) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tableRow.length(); i++) {
                if (Character.isDigit(tableRow.charAt(i))) {
                    stringBuilder.append(tableRow.charAt(i));
                }

            }
            rowPath = stringBuilder.toString();
        } else {
            List<WebElement> setRow = driver.findElements(By.xpath("" +
                    "//td[normalize-space(text())='" + tableRow + "']"));
            for (WebElement rowElement : setRow) {
                if (rowElement.isDisplayed()) {
                    row = rowElement;
                    break;
                }
            }

            assert row != null;
            rowPath = generateXPATH(row, "");
            Pattern rowP = Pattern.compile("(?<=tr\\[)(\\d+)(?=\\])");
            assert rowPath != null;
            Matcher rowM = rowP.matcher(rowPath);
            while (rowM.find()) {
                rowPath = rowM.group();
            }
        }

        List<WebElement> results = driver.findElements(By.xpath("" +
                "//*[@id='" + title + "']/tbody/tr[" + rowPath + "]/td[" + colPath + "]"));
        for (WebElement resElement : results) {
            if (resElement.isDisplayed()) {
                return resElement;
            }
        }

        return null;
    }

    public String gridOpenItem(String tabName, String column) {
        int rows = gridRowCount(tabName);
        return "rows in the grid called from gridOpenItem: " + rows;
    }

    @Override
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

    @Override
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

   /* public boolean pageLoaded() {
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> (Boolean) js.executeScript("return jQuery.active == 20");

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> js.executeScript("return document.readyState").toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);

    }*/
   public static boolean pageLoaded() {
       // wait for jQuery to load
       ExpectedCondition<Boolean> jQueryLoad = driver -> {
           try {
               return (Boolean) js.executeScript("return jQuery.active == 20");
           }
           catch (Exception e) {
               // no jQuery present
               return true;
           }
       };

       // wait for Javascript to load
       ExpectedCondition<Boolean> jsLoad = driver -> js.executeScript("return document.readyState").toString().equals("complete");

       try {
           return wait.until(jQueryLoad) && wait.until(jsLoad);
       } catch (TimeoutException e) {
           return true;
       }

   }

}
