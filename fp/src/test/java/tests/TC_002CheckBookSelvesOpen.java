package tests;

import factory.BaseClass;
import org.testng.annotations.Test;
import pages.SearchResultPage;

public class TC_002CheckBookSelvesOpen extends BaseTest{
    @Test
    public void CheckBookselves(){

        driver.get(BaseClass.getProperty("appUrl"));
        homePage.closePopUpIfPresent();
        homePage.searchItem(BaseClass.getProperty("item"));

        SearchResultPage searchResultPage=new SearchResultPage(driver);
        searchResultPage.SearchResultPageOpens();

    }
}
