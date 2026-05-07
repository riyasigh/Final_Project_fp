package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class Hooks {
    WebDriver driver;
    HomePage hp;
    @Before
    public void globalSetup(){
        BaseClass.setUp();
        driver=BaseClass.getDriver();
    }

    @After
    public void tearDown(Scenario scenario){
        BaseClass.tearDown();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver d = BaseClass.getDriver();
            if (d != null) {
                TakesScreenshot ts = (TakesScreenshot) d;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        }
    }

}
