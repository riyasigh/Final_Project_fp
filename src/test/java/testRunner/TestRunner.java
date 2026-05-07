package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ={
                ".//Features/1_SearchAndFilter.feature",
                ".//Features/2_HomeDecorList.feature",
                ".//Features/3_GiftCard.feature"
        },
        glue = "stepDefinitions",
        plugin={
                "pretty","html:reports/myreport.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        dryRun=false,
        monochrome = true
)

public class TestRunner {
}

//extends AbstractTestNGCucumberTests {

