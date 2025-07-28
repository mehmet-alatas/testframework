package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.WaitUtils;

/**
 * Page Object representing the login screen of the bug tracker application using PageFactory.
 * It encapsulates the elements needed for user authentication and actions such as
 * entering a username, entering a password, and submitting the login form.
 */
public class LoginPage {

    public LoginPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(css = "input[placeholder='Username']")
    private WebElement usernameField;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement passwordField;

    @FindBy(css = ".login-card .primary-btn")
    private WebElement loginButton;


    @FindBy(xpath = "//h2[.='Login']")
    private WebElement loginHeaderText;


    @FindBy(xpath = "//p[ contains(text(),'password')]")
    private WebElement errorMessage;



    public String getPasswordFieldType() {

        return passwordField.getAttribute("type");
    }

    public void verifyErrorMessage(String errorMessageText) {
        WaitUtils.waitForVisibility(errorMessage, 10);
        String actualTitle = errorMessage.getText();
        Assert.assertEquals(errorMessageText, actualTitle);

    }

    public void verifyOnTheLoginPage() {
        WaitUtils.waitForVisibility(loginHeaderText, 10);
        String actualTitle = loginHeaderText.getText();
        Assert.assertEquals("Login", actualTitle);

    }

    /**
     * Clears and enters text into the username field.
     *
     * @param username the username to type
     */
    public void setUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    /**
     * Clears and enters text into the password field.
     *
     * @param password the password to type
     */
    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLogin() {
        loginButton.click();
    }

    /**
     * Performs a full login action by filling out the username and password
     * fields and clicking the login button.
     *
     * @param username the username to use
     * @param password the password to use
     */
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
    }

    /**
     * Retrieves any error message displayed on the login page. If no error message
     * is present, this method returns null.
     *
     * @return the error message text or null if not present
     */
    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return null;
        }
    }


}
