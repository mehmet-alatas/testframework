package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * JUnit 4 test runner for the bug tracker Cucumber tests. This class configures
 * the feature and glue locations and enables pretty printing of results.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",//konsol da scenariolar ile ilgili ayrintili bilgi verir
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml"
        },
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        tags = ""
)
public class TestRunner {
}