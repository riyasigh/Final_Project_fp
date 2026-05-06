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



//    @When("the user enters gift card details from excel row {string}")
//    public void enter_gift_card_details_from_excel_row(String rowIndex) throws IOException {
//        int index=Integer.parseInt(rowIndex);
//        // Read Excel
//        // Fill all fields
//        // Focus validation on senderEmail only
//        List<HashMap<String,String>> excelData= giftdata.data(filepath,"sheet1");
//        HashMap<String,String >row = excelData.get(index);
//        String receiverName=row.get("receiverName");
//        String senderName=row.get("senderName");
//        String receiverMobile=row.get("receiverMobile");
//        String senderMobile=row.get("senderMobile");
//        String receiverEmail=row.get("receiverEmail");
//        String senderMail=row.get("senderEmail");
//        String card_Message=row.get("message");
//        String expectedMessage=row.get("expectedMessage");
//        gp.fillGiftCardDetails(receiverName,senderName,receiverMobile,senderMobile,senderMail,receiverEmail,card_Message);
//        String actualError=gp.getSenderEmailErrorMessage();
//        String result;
//        if(expectedMessage.equalsIgnoreCase("success")){
//            if(actualError.isEmpty()){
//                result="PASS";
//            }else{
//                result="FAIL";
//            }
//        }else{
//            if(actualError.equals(expectedMessage)){
//                result="PASS";
//            }else{
//                result="FAIL";
//            }
//        }
//        giftdata.storeValues.put("expectedMessage", expectedMessage);
//        giftdata.storeValues.put("actualError", actualError);
//        giftdata.storeValues.put("result", result);
//        giftdata.storeValues.put("rowIndex", String.valueOf(index));
//        giftdata.writeResult(filepath, "sheet1", index, actualError, result);
//
//    }
//
//    @When("the user selects the 1000 denomination")
//    public void the_user_selects_the_1000_denomination() {
//        gp.selectAmount1000();
//    }
//
//    @When("the user clicks proceed to checkout")
//    public void the_user_clicks_proceed_to_checkout() {
//        gp.clickProceedToCheckout();
//    }
//
//    @Then("the sender email validation message should be displayed correctly")
//    public void validate_sender_email_message() {
//
//        String expectedMessage = giftdata.storeValues.get("expectedMessage");
//        String actualError = giftdata.storeValues.get("actualError");
//        String result = giftdata.storeValues.get("result");
//
//        if (expectedMessage.equalsIgnoreCase("success")) {
//
//            Assert.assertTrue(
//                    actualError.isEmpty(),
//                    "Expected no error message, but found: " + actualError
//            );
//
//        } else {
//
//            Assert.assertEquals(
//                    actualError,
//                    expectedMessage,
//                    "Sender email error message mismatch"
//            );
//        }
//
//        // Safety check
//        Assert.assertEquals(result, "PASS", "Final result is FAIL");
//    }
