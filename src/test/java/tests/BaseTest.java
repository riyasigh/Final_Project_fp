package tests;

import factory.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    @BeforeMethod
    public void setUp(){
        BaseClass.setUp();
        driver=BaseClass.getDriver();
        homePage=new HomePage(driver);
    }

    @AfterMethod
    public void tearDown(){
        BaseClass.tearDown();
    }
}
