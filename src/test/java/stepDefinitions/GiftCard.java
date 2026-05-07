package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.GiftCardPage;
import pages.HomePage;
import pages.SearchResultPage;
import utils.giftdata;
import utils.screenshotfile;
//screenshotfile.capture(driver, "AfterHover");
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GiftCard {
    WebDriver driver;
    HomePage hp;
    GiftCardPage gp;
    SearchResultPage sp;

    String filepath = System.getProperty("user.dir")
            + "/src/test/resources/GiftExcel/giftdata.xlsx";

    @Given("the user navigates to the Pepperfry home page")
    public void the_user_navigates_to_the_pepperfry_website() {
        driver = BaseClass.getDriver();
        driver.get(BaseClass.getProperty("appUrl"));
    }

    @Given("any visible popup on the home page is closed")
    public void any_visible_popup_on_the_home_page_is_closed() {
        hp = new HomePage(driver);
        hp.closePopUpIfPresent();
    }

    @When("the user opens gift card option")
    public void the_user_opens_gift_card_option() {
        hp.goToGiftCards();
        gp = new GiftCardPage(driver);
    }

    @When("the user selects the birthday gift card")
    public void the_user_selects_the_birthday_gift_card() {
        gp.selectBirthdayCard();
    }

    @When("the user enters gift card details from excel row {string}")
    public void enter_gift_card_details_from_excel_row(String rowIndex) throws IOException {
        int index=Integer.parseInt(rowIndex);
        List<HashMap<String,String>> excelData= giftdata.data(filepath,"sheet1");
        HashMap<String,String >row = excelData.get(index);
        String receiverName=row.get("receiverName");
        String senderName=row.get("senderName");
        String receiverMobile=row.get("receiverMobile");
        String senderMobile=row.get("senderMobile");
        String receiverEmail=row.get("receiverEmail");
        String senderMail=row.get("senderEmail");
        String card_Message=row.get("message");
        String expectedMessage =row.get("expectedMessage");

        gp.fillGiftCardDetails(receiverName,senderName,receiverMobile,senderMobile,senderMail,receiverEmail,card_Message);

        giftdata.storeValues.put("expectedMessage", expectedMessage);
        giftdata.storeValues.put("rowIndex", String.valueOf(index));
    }

    @When("the user selects the 1000 denomination")
    public void the_user_selects_the_1000_denomination() {
        gp.selectAmount1000();
    }

    @When("the user clicks proceed to checkout")
    public void the_user_clicks_proceed_to_checkout() {
        gp.clickProceedToCheckout();
    }

    @Then("the sender email validation message should be displayed correctly")
    public void validate_sender_email_message() throws IOException {

        String expectedMessage = giftdata.storeValues.get("expectedMessage");
        int index = Integer.parseInt(giftdata.storeValues.get("rowIndex"));
        String actualError = gp.getSenderEmailErrorMessage();

        String result;
        if (expectedMessage.equalsIgnoreCase("success")) {
            result = actualError.isEmpty() ? "PASS" : "FAIL";
            Assert.assertTrue(actualError.isEmpty(),
                    "Expected no error but found: " + actualError);
        } else {
            result = actualError.equals(expectedMessage) ? "PASS" : "FAIL";
            Assert.assertEquals(actualError, expectedMessage,
                    "Email error message mismatch");
        }

        Assert.assertEquals(result, "PASS", "Final result is FAIL");

        giftdata.writeResult(filepath, "sheet1", index, actualError, result);
    }
}
