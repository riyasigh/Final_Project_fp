package tests;

import factory.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class Tc001_OpenWebsite extends BaseTest {

    @Test
    public void verifyWebsiteOpens(){
        driver.get(factory.BaseClass.prop.getProperty("appUrl"));
        homePage.closePopUpIfPresent();

        Assert.assertTrue(
                driver.getTitle().toLowerCase().contains("pepperfry"),"Pepperfry website did not open");
    }

}
