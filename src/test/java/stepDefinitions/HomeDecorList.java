package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import utils.screenshotfile;

import java.util.List;

public class HomeDecorList {
    WebDriver driver;
    HomePage hp;

    @Given("the user launches the Pepperfry website")
    public void the_user_launches_the_pepperfry_website() {
        driver = BaseClass.getDriver();
        driver.get(BaseClass.getProperty("appUrl"));
    }

    @Given("any popup on the page is closed")
    public void any_popup_on_the_page_is_closed(){
        hp = new HomePage(driver);
        hp.closePopUpIfPresent();
    }

    @When("the user hovers over the Home Decor menu")
    public void the_user_hovers_over_the_home_decor_menu() {
        hp.hoverOverHomeDecor();
        screenshotfile.takeScreenshot(driver,"HomeDecorPage");
    }

    @Then("the Home Decor menu items should be captured and printed")
    public void the_home_decor_menu_items_should_be_captured_and_printed() {

        List<String> menuItems = hp.getHomeDecorItems();
        Assert.assertTrue(menuItems.size() > 0, "No Home Decor items found");
        System.out.println("Successfully extracted " + menuItems.size() + " items");
        System.out.println("Home Decor Menu Items");
        for (String item : menuItems) {
            System.out.println();
        }
    }
}