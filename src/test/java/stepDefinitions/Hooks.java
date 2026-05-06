package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class Hooks {
    WebDriver driver;
    HomePage hp;
    @Before
    public void globalSetup(){
        BaseClass.setUp();
        driver=BaseClass.getDriver();
//        driver.get(BaseClass.getProperty("appUrl"));
//        driver.manage().window().maximize();
//
    }

    @After
    public void tearDown(Scenario scenario){
        BaseClass.tearDown();
    }



}
