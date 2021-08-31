package Pages;

import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KPITrackingPage extends BaseUtil {
    private final WebDriver driver;

    public KPITrackingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*-----------KPI's--------------------*/
    public void KPI(String name) {
        WebElement kpiname = driver.findElement(By.xpath("//a[contains(@id,'kpi')][.//text()[normalize-space()=\"" + name + "\"]]"));
        kpiname.click();
    }


    // Get WO number
    public static String wonumber;
    @FindBy(how = How.XPATH, using = "//div[@class='jconfirm-content']/div/font/b")
    public static WebElement WONumber;

    @FindBy(how = How.CLASS_NAME, using = "jconfirm-buttons")
    public static WebElement ConfirmWorkorder;

    public String getWonumber() {
        wait.until(ExpectedConditions.visibilityOf(WONumber));
        wonumber = WONumber.getText();
        System.out.println(wonumber);
        ConfirmWorkorder.click();
        return wonumber;
    }

    // Input Work Order Id
    @FindBy(how = How.ID, using = "WorkOrderLossRunNumberappOpen")
    public static WebElement inputWO;
    @FindBy(how = How.XPATH, using = "//table[@id=\"dtprocessing\"]/tbody/tr/td/a")
    public static WebElement WOrecord;

    public void OpenWO() throws InterruptedException {
        WOrecord.click();
        Thread.sleep(3000);
    }

    public void searchWorkOrder(String number) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('WorkOrderLossRunNumberappOpen').value= " + number + "");
        inputWO.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    /*------------Different conditions for different KPI's----------------------*/
    @FindBy(how = How.ID, using = "StatusID")
    public static WebElement status;
    @FindBy(how = How.ID, using = "emailOverride")
    public static WebElement emailoverride;
    @FindBy(how = How.XPATH, using = "//label[contains(text(),'All Orders Complete')]/span")
    public static WebElement allOrdersComplete;
    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Reports Reviewed')]/span")
    public static WebElement reportsReviewed;


    public void setKPIConditions(String name) throws Exception {
        switch (name.toLowerCase()) {
            case "loss run requests":
                if (status.getText() == "Discarded" || status.getText() == "On Hold") {
                    status.sendKeys("In Progress");
                }
                System.out.println("Status NOT EQUALS  Discarded OR On Hold");

                if (emailoverride.getText() == "Important Instructions") {
                    emailoverride.sendKeys("Report");
                }
                System.out.println("Email Override NOT EQUALS Important Instructions");

                if (!allOrdersComplete.isSelected() && !reportsReviewed.isSelected()) {
                    System.out.println("All Orders Complete and Reports Reviewed checkboxes are unchecked ");
                }
                break;
            case "pending carrier response":
                if (status.getText() != "In Progress") {
                    status.sendKeys("In Progress");
                }
                System.out.println("Status EQUALS In Progress");

                if (emailoverride.getText() == "Important Instructions") {
                    emailoverride.sendKeys("Report");
                }
                System.out.println("Email Override NOT EQUALS Important Instructions");

                if (!allOrdersComplete.isSelected() && !reportsReviewed.isSelected()) {
                    System.out.println("All Orders Complete and Reports Reviewed checkboxes are unchecked ");
                }
                break;

            default:
                throw new Exception("No KPI available for " + name.toUpperCase() + "please use valid KPI name");
        }
    }

    public void reviewedCompletedCheckbox(String checkboxName) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

           switch (checkboxName.toLowerCase()) {
                case "all orders complete":
                    js.executeScript("arguments[0].click();", allOrdersComplete);
                    break;
                case "reports reviewed":
                    js.executeScript("arguments[0].click();", reportsReviewed);
                    break;
                default:
                    throw new Exception("No checkbox found please select valid checkbox");
            }
        }
        catch (Exception e){
            System.out.println("No checkbox found");
    }

}


    /*-------------------Get latest dates-------------------*/
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");

        //get current date time with Date()
        Date date = new Date();

        // Now format the date
        String date1 = dateFormat.format(date);

        return date1;
    }

    public String getPastdate(int numberOfDays) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = now.minusDays(numberOfDays);
        String date2 = then.format(format);

        return date2;
    }


    /*---------------Date Picker---------------------------*/
    public void datePicker(String date) throws Exception {

        //This is from date picker table
        WebElement dateWidgetFrom = driver.findElement(By.xpath("//div[@class='datepicker-days']/table/tbody"));

        //button to move next in calendar

        WebElement nextLink = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='next']"));

        //button to click in center of calendar header

        WebElement midLink = driver.findElement(By.xpath("//div[@class='datepicker-days']/descendant::th[@class='datepicker-switch']"));

        //button to move previous month in calendar

        WebElement previousLink = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='prev']"));

        //Split the date time to get only the date part

        String[] date_dd_MM_yyyy = (date.split(" ")[0]).split("/");



        //get the year difference between current year and year to set in calander
        int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);

        midLink.click();

        if(yearDiff!=0){

            //if you have to move next year

            if(yearDiff>0){

                for(int i=0;i< yearDiff;i++){

                    System.out.println("Year Diff->"+i);

                    nextLink.click();

                }

            }

            //if you have to move previous year

            else if(yearDiff<0){

                for(int i=0;i< (yearDiff*(-1));i++){

                    System.out.println("Year Diff->"+i);

                    previousLink.click();

                }

            }

        }

        Thread.sleep(1000);

        //Get all months from calendar to select correct one

        List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath("//div[@class='datepicker-months']/table/tbody/tr/td[not(contains(@class,'k-other-month'))]/span"));
        list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1).click();
        String select_date = date_dd_MM_yyyy[0];
        System.out.println(select_date);

        Thread.sleep(1000);


        //This are the columns of the from date picker table
        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));

        for (WebElement cell : columns) {
            //Select Today's Date
            if (cell.getText().equals(select_date)) {
                cell.click();
                break;
            }
        }

        //Wait for 4 Seconds to see Today's date selected.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*----------------Insured Reminder Date---------------------*/
    @FindBy(how = How.ID, using = "InsuredReminder")
    WebElement insured_Date;

    public void insuredDate() throws Exception {
        insured_Date.click();
        datePicker(getDate());
    }

    /*---------------Order/Reminder Dates------------------*/

    public boolean orderedDate(String fieldname , String text) throws Exception {
        String date1 = getDate();
        System.out.println("Current Date is " + date1);
        List<WebElement> order_Date = driver.findElements(By.xpath("//input[@placeholder= '"+fieldname+"']"));
        order_Date.get((order_Date.size()-1)).click();
        switch (text.toLowerCase()) {
            case "any":
                datePicker(date1);
                break;
            case "today":
                String date2 = getPastdate(7); /* numberofDays is set 7  days past to automatically set next reminder date field to Today's date */
                System.out.println(date2);
                    datePicker(date2);
                break;
            case "past":
                String date3 = getPastdate(10);
                    datePicker(date3);
                break;
            default:
                throw new Exception("No Date found please select valid date");
        }
        return true;
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

}
