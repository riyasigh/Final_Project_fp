package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features ={".//Features/1_SearchAndFilter.feature"},
        glue = "stepDefinitions"
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
