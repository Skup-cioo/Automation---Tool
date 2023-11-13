package common;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Tests", glue = {"stepsDefiniton"}
        , monochrome = true
        , plugin = {"pretty", "json:target/JSONReports/report.json"},
        tags = "@Mouse"
)

public class TestRunner {
}
