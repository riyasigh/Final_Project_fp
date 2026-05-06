package tests;

import factory.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchResultPage;

import java.util.List;

public class TC_004VerifyFirstThreeProductsDisplayed extends BaseTest {
    @Test
    public void checkFiltersApplied() {
        driver.get(BaseClass.getProperty("appUrl"));
        homePage.closePopUpIfPresent();
        homePage.searchItem(BaseClass.getProperty("item"));

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.SearchResultPageOpens();

        searchResultPage.openMoreFilters();
        searchResultPage.expandFilter(BaseClass.getProperty("filter1"));

        String maxPrice = BaseClass.getProperty("maxprice");
        String count=BaseClass.getProperty("count");
        int maxp = Integer.parseInt(maxPrice);
        int c=Integer.parseInt(count);

        searchResultPage.setMaxPrice(maxp);
        searchResultPage.expandFilter(BaseClass.getProperty("filter2"));

        searchResultPage.selectBrand(BaseClass.getProperty("brandName"));

        searchResultPage.clickApply();

        searchResultPage.displayTopProducts(c,maxp);
        List<Integer> prices=searchResultPage.getAllProductPrices();
        Assert.assertTrue(prices.size()>0,"No products found");

        for(int price : prices){
            Assert.assertTrue(price<=15000, "Price found above 15000: ₹"+price);
        }
        System.out.println("All product prices are below ₹15000");


    }
}
