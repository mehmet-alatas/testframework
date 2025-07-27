package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing the main bug management screen using PageFactory.
 */
public class BugPage {

    public BugPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "input[placeholder='New bug name...']")
    private WebElement newBugInput;

    @FindBy(css = ".add-bug .primary-btn")
    private WebElement addBugButton;

    @FindBy(css = ".bug-card")
    private List<WebElement> bugCards;

    @FindBy(css = ".bug-actions .secondary-btn")
    public List<WebElement> editButtons;

    @FindBy(css = ".bug-actions .danger-btn")
    private List<WebElement> deleteButtons;

    @FindBy(css = ".edit-container .primary-btn")
    public WebElement saveButton;


    @FindBy(css = ".edit-container .secondary-btn")
    private WebElement cancelButton;

    @FindBy(css = ".edit-container input[type='text']")
    private WebElement editInput;


    @FindBy(xpath = "//*[.='Bug Tracking System']")
    private WebElement bugTrackingSystemTitle;

    public void addBug(String bugName) {
        newBugInput.clear();
        newBugInput.sendKeys(bugName);
        addBugButton.click();
    }


    public void verifyOnBugPage() {
        WaitUtils.waitForVisibility(bugTrackingSystemTitle, 10);
        String actualTitle = bugTrackingSystemTitle.getText();
        Assert.assertEquals("Bug Tracking System", actualTitle);

    }


    public List<String> getBugNames() {
        List<String> names = new ArrayList<>();
        for (WebElement card : bugCards) {
            names.add(card.findElement(By.cssSelector(".bug-name")).getText());
        }
        return names;
    }

    public int getBugCount() {
        return bugCards.size();
    }

    public int getLastEditButtonindeks() {
        return editButtons.size() - 1;
    }


    private WebElement getBugCardByName(String name) {
        for (WebElement card : bugCards) {
            String currentName = card.findElement(By.cssSelector(".bug-name")).getText();
            if (currentName.equals(name)) {
                return card;
            }
        }
        return null;
    }

    public void deleteAllBug() {
      for (WebElement deleteButton : deleteButtons){
           deleteButton.click();
      }
    }


    public void deleteBug(String name) {
        WebElement card = getBugCardByName(name);
        if (card != null) {
            card.findElement(By.cssSelector(".bug-actions .danger-btn")).click();
        }
    }

    public void editBug(String oldName, String newName) {
        WebElement card = getBugCardByName(oldName);
        if (card != null) {
            card.findElement(By.cssSelector(".bug-actions .secondary-btn")).click();
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
            WebElement editField = wait.until(ExpectedConditions.visibilityOf(editInput));
            editField.clear();
            editField.sendKeys(newName);
            card.findElement(By.cssSelector(".edit-container .primary-btn")).click();
        }
    }

    public boolean isBugPresent(String name) {
        return getBugCardByName(name) != null;
    }
}
