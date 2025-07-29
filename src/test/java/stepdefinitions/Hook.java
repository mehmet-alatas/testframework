package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;


public class Hook {

 @After
    public void tearDown(Scenario scenario) throws Exception {
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "scenario_" + scenario.getName());
    }

     @After
    public void tearDown2(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {//eğer scenario fail olursa calisacak
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "scenario_" + scenario.getName());
            Driver.closeDriver();
        }
    }

}
