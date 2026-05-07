package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.GiftCardPage;
import pages.HomePage;
import pages.SearchResultPage;
import utils.screenshotfile;

import java.util.List;

public class SearchAndFilter {
    WebDriver driver;
    HomePage hp;
    SearchResultPage sp;
    GiftCardPage gp;


    @Given("the user opens the Pepperfry website")
    public void the_user_opens_the_pepperfry_website() {
        driver = BaseClass.getDriver();
        driver.get(BaseClass.getProperty("appUrl"));
    }

    @Given("the user closes the popup if present")
    public void the_user_closes_the_popup_if_present() {
        hp = new HomePage(driver);
        hp.closePopUpIfPresent();
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String string) {
        hp.searchItem(string);
    }

    @When("the user applies a maximum price filter of {int} and select the brand {string}")
    public void the_user_applies_a_maximum_price_filter_of_and_select_the_brand(Integer int1, String string2) {
        sp = new SearchResultPage(driver);
        sp.openMoreFilters();
        sp.expandFilter(BaseClass.getProperty("filter1"));
        sp.setMaxPrice(int1);
        sp.expandFilter(BaseClass.getProperty("filter2"));
        sp.selectBrand(string2);
        sp.clickApply();

    }

    @And("the top {int} products with their prices should be displayed")
    public void the_top_products_with_their_prices_in_should_be_displayed(Integer int1) {
        String mxprice = BaseClass.getProperty("maxprice");
        int mx = Integer.parseInt(mxprice);
        sp.displayTopProducts(int1, mx);
    }

    @Then("the user validate that prices are less than {int}")
    public void the_user_validate_that_prices_are_less_than(Integer maxPrice) {
        List<Integer> prices = sp.getAllProductPrices();
        for (int price : prices) {
            Assert.assertTrue(
                    price <= maxPrice,
                    "Product price ₹" + price + " exceeds max price ₹" + maxPrice
            );
        }
        System.out.println("All prices are within ₹" + maxPrice);
    }

}
