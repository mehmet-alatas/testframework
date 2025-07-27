package stepdefinitions.uı_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.BugPage;
import pages.LoginPage;
import utilities.Driver;

public class LoginStepDefs {
    @Given("the bug tracker application is open at {string}")
    public void theBugTrackerApplicationIsOpenAt(String url) {
        Driver.getDriver().get(url);
    }

    @When("I enter {string} as the username and {string} as the password")
    public void iEnterAsTheUsernameAndAsThePassword(String userName, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.setUsername(userName);
        loginPage.setPassword(password);
    }

    @And("I click the Login button")
    public void iClickTheButton() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLogin();
    }


    @And("I should see a Bug Tracking System")
    public void iShouldSeeABugTrackingSystem() {
        BugPage bugPage = new BugPage();
        bugPage.verifyOnBugPage();
    }

    @Then("I should see an authentication error message")
    public void iShouldSeeAnAuthenticationErrorMessage() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyErrorMessage("Geçersiz kullanıcı adı veya şifre");

    }

    @And("I should stay on the login page")
    public void iShouldStayOnTheLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyOnTheLoginPage();
    }

    @When("I leave the username field empty and enter {string} as the password")
    public void iLeaveTheUsernameFieldEmptyAndEnterAsThePassword(String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.setUsername("");
        loginPage.setPassword(password);
    }

    @Then("I should see a validation message saying {string}")
    public void iShouldSeeAValidationMessageSaying(String errorMessageText) {
        LoginPage loginPage = new LoginPage();
        loginPage.verifyErrorMessage(errorMessageText);
    }

    @When("I enter {string} as the username and leave the password field empty")
    public void iEnterAsTheUsernameAndLeaveThePasswordFieldEmpty(String arg0) {
        LoginPage loginPage = new LoginPage();
        loginPage.setUsername(arg0);
        loginPage.setPassword("");
    }

    @When("I leave both the username and password fields empty")
    public void iLeaveBothTheUsernameAndPasswordFieldsEmpty() {
        LoginPage loginPage = new LoginPage();
        loginPage.setUsername("");
        loginPage.setPassword("");
    }

    @When("I enter a password in the password field")
    public void iEnterAPasswordInThePasswordField() {
        LoginPage loginPage = new LoginPage();
        loginPage.setPassword("examplePassword123");
    }

    @Then("the characters in the password field should be masked \\(shown as dots or asterisks)")
    public void theCharactersInThePasswordFieldShouldBeMaskedShownAsDotsOrAsterisks() {
        LoginPage loginPage = new LoginPage();
        String passwordFieldType = loginPage.getPasswordFieldType();
        Assert.assertEquals("password", passwordFieldType);

    }

    @And("close the browser")
    public void closeTheBrowser() {
        Driver.closeDriver();
    }
}
