package tests;

import factory.BaseClass;
import org.testng.annotations.Test;
import pages.SearchResultPage;

public class TC_003CheckFiltersAreApplied extends BaseTest {
    @Test
    public void checkFiltersApplied(){
        driver.get(BaseClass.getProperty("appUrl"));
        homePage.closePopUpIfPresent();
        homePage.searchItem(BaseClass.getProperty("item"));

        SearchResultPage searchResultPage=new SearchResultPage(driver);
        searchResultPage.SearchResultPageOpens();

        searchResultPage.openMoreFilters();
        searchResultPage.expandFilter(BaseClass.getProperty("filter1"));

        String maxPrice=BaseClass.getProperty("maxprice");
        int maxp=Integer.parseInt(maxPrice);

        searchResultPage.setMaxPrice(maxp);
        searchResultPage.expandFilter(BaseClass.getProperty("filter2"));

        searchResultPage.selectBrand(BaseClass.getProperty("brandName"));

        searchResultPage.clickApply();
    }
}
