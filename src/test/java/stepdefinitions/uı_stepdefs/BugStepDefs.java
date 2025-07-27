package stepdefinitions.uı_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BugPage;
import pages.LoginPage;
import utilities.WaitUtils;

public class BugStepDefs {
    @Given("I am logged in as admin and on the bug list page")
    public void iAmLoggedInAsAdminAndOnTheBugListPage() {

        LoginPage loginPage = new LoginPage();
        loginPage.setUsername("admin");
        loginPage.setPassword("123456");
        loginPage.clickLogin();
    }

    @When("I enter {string} as the name")
    public void iEnterAsTheName(String bugName) {
        BugPage bugPage = new BugPage();
        bugPage.addBug(bugName);
    }



    @Then("I should see {string} in the bug list")
    public void iShouldSeeInTheBugList(String arg0) {
        BugPage bugPage = new BugPage();
        boolean isBugPresent = bugPage.isBugPresent(arg0);
        Assert.assertTrue("Expected bug '" + arg0 + "' should be in the list.", isBugPresent);
    }

    @And("I enter the name field space")
    public void iLeaveTheNameFieldEmpty() {
        BugPage bugPage = new BugPage();
        bugPage.addBug(" "); // Attempt to add an empty bug name
    }

    @Then("I should not see {string} in the bug list")
    public void iShouldNotSeeInTheBugList(String arg0) {
        BugPage bugPage = new BugPage();
        boolean isBugPresent = bugPage.isBugPresent(arg0);
        Assert.assertFalse(isBugPresent);
//        if (isBugPresent) {
//            throw new AssertionError("Expected bug '" + arg0 + "' should not be in the list.");
//        }
    }


    @And("I click the Edit button")
    public void iClickTheEditButton() {
        BugPage bugPage = new BugPage();
        bugPage.editButtons.get(bugPage.getLastEditButtonindeks()).click();
    }

    @And("I change the name to {string}")
    public void iChangeTheNameTo(String newBugName) {
        BugPage bugPage = new BugPage();
        bugPage.editBug("TestBug",newBugName);
    }

    @And("I click the Save button")
    public void iClickTheButton() {
        BugPage bugPage = new BugPage();
        WaitUtils.waitForVisibility(bugPage.saveButton,10);
        bugPage.saveButton.click();
    }

    @And("{string} should no longer be in the list")
    public void shouldNoLongerBeInTheList(String arg0) {
        BugPage bugPage = new BugPage();
        boolean isBugPresent = bugPage.isBugPresent(arg0);
        Assert.assertFalse("Expected bug '" + arg0 + "' should not be in the list.", isBugPresent);

    }

    @Given("I am not logged in")
    public void iAmNotLoggedIn() {

    }

    @When("I try to navigate to the {string} page for a bug")
    public void iTryToNavigateToThePageForABug(String arg0) {
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
    }

    @And("no bug should be edited")
    public void noBugShouldBeEdited() {
    }

    @When("I delete all the bugs")
    public void iDeleteAllTheBugs() {
        BugPage bugPage = new BugPage();
        bugPage.deleteAllBug();
    }

    @Then("I should see empty the bug list")
    public void iShouldSeeEmptyTheBugList() {
        BugPage bugPage = new BugPage();
        WaitUtils.waitFor(2);
        int bugCount = bugPage.getBugCount();
        Assert.assertEquals("The bug list should be empty after deletion.", 0, bugCount);
    }
}
