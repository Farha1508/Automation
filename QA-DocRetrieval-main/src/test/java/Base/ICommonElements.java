package Base;


import org.openqa.selenium.*;

import java.util.Map;

public interface ICommonElements {

    void login(String user);
    boolean onCorrectPage();

    /**
     * Finds and clicks a button on the page.
     * @param button <code>String</code> of the button's text label
     * @return <code>true</code> if the button was able to be found and clicked <code>false</code> if any errors occur
     */
    boolean commonButton(String button);
    boolean commonCheckBox(String checkBox);

    /**
     * Returns the <code>WebElement</code> for the selected field.
     * <p>
     * Called by the <code>commonFieldEnter</code> and <code>commonFieldRead</code>
     * functions to locate the field on the page.
     * @param field <code>String</code> label of the field
     * @return <code>WebElement</code> generated for the field
     */
    WebElement commonField(String field);
    /**
     * Enters text to any field.
     * @param field <code>String</code> label of the field
     * @param text <code>String</code> of the text you want to enter into the field
     * @return <code>true</code> if the entry happened with no errors; <code>false</code> otherwise.
     */
    boolean commonFieldEnter(String field, String text);
    /**
     * Returns what is currently entered the selected field.
     * @param field <code>String</code> label of the field
     * @return <code>String</code> of the current text in the field.
     */
    String commonFieldRead(String field);

    /**
     * Returns the <code>WebElement</code> for the selected text area.
     * <p></p>
     * Called by <code>commonTextAreaEnter</code> and <code>commonTextAreaRead</code> functions to locate the
     * text area on the page.
     * @param textArea <code>String</code> label of the text area
     * @return <code>WebElement</code> generated for the text area
     */
    WebElement commonTextArea(String textArea);
    /**
     * Enters text into the larger text boxes that can support multiple lines.
     * @param textArea <code>String</code> label of the text area
     * @param text <code>String</code> of the text to enter into the text box
     * @return <code>true</code> if the entry happened with no errors; <code>false</code> otherwise
     */
    boolean commonTextAreaEnter(String textArea, String text);

    /**
     * Returns what is currently entered in the selected text area.
     * @param textArea <code>String</code> label of the text area
     * @return <code>String</code> of the current text in the text area
     */
    String commonTextAreaRead(String textArea);

    /**
     * Returns the <code>WebElement</code> for the selected drop down.
     * <p>
     * Used by <code>commonDropDownSelect</code> and <code>commonDropDownRead</code> to locate the selected dropdown.
     * @param dropDown <code>String</code> label of the selected drop down
     * @return <code>WebElement</code> generated for the drop down
     */
    WebElement commonDropDown(String dropDown);
    /**
     * Selects an entry from the selected drop down.
     * @param dropDown <code>String</code> label of the selected drop down
     * @param selection <code>String</code> text of the selection to be made from the drop down
     * @return <code>true</code> if the selection happened with no errors; <code>false</code> otherwise
     */
    boolean commonDropDownSelect(String dropDown, String selection);

    /**
     * Returns the currently selected option in the drop down.
     * @param dropDown <code>String</code> label of the drop down
     * @return <code>String</code> of the current selection
     */
    String commonDropDownRead(String dropDown);

    /**
     * Returns the <code>WebElement</code> for the selected date picker
     * <p>
     *     Used by <code>commonDatePick</code> and <code>commonDateRead</code> to locate the selected date picker
     * </p>
     *
     * @param datePicker <code>String</code> label of the selected date picker
     * @return <code>WebElement</code> generated for the date picker
     */
    WebElement commonDate(String datePicker);

    /**
     * Selects the given date from the chosen date picker
     * @param datePicker <code>String</code> label of the chosen date picker
     * @param day <code>String</code> digit for the day with no preceding '0' (i.e., "5", not "05")
     * @param month <code>String</code> Full name of the month (i.e., "January", not "Jan")
     * @param year <code>String</code> four digit year
     * @return <code>true</code> if completed with no errors, otherwise <code>false</code>
     */
    boolean commonDatePick(String datePicker, String day, String month, String year);
    boolean commonDatePick(String header, Map<String, String> selections);

    /**
     * Returns what is currently displayed in the date picker
     * @param datePicker <code>String</code> label of the chosen date picker
     * @return <code>String</code> of what is currently displayed in the date picker
     */
    String commonDateRead(String datePicker);


    /**
     * This will generate the full XPath of the given WebElement. Use this if you gained the XPath for an element
     * dynamically, but still need the full XPath for some reason.
     * @param childElement This is the element you want to get the full XPath of.
     * @param current This will append the given String to the end of the generated XPath
     * @return Returns a string of the full XPath of the given WebElement
     */
    String generateXPATH(WebElement childElement, String current);

    /**
     * The clickErrorHandle function is used to handle the ElementClickInterceptedException that can occur when an
     * element is blocking the click of another element. It will dynamically create a new Xpath locator from the
     * error message and force the system to wait until that element is no longer visible or 10 seconds have passed and
     * then attempt the click again.
     * @param error The text of the ElementClickInterceptedException error needed to create the dynamic Xpath.
     * @param target The WebElement of the item being clicked, so that another click attempt can be made when the
     *               blocking element has been removed.
     */
    void clickErrorHandle(String error, WebElement target);

}
