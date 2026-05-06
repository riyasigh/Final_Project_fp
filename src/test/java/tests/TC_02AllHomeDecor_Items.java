package tests;

import org.testng.annotations.Test;

public class TC_02AllHomeDecor_Items extends BaseTest{
    @Test
    public void verifyWebsiteOpens() {
        driver.get(factory.BaseClass.prop.getProperty("appUrl"));
        homePage.closePopUpIfPresent();

        homePage.hoverOverHomeDecor();
        homePage.getHomeDecorItems();
    }
}
