package tests;

import factory.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GiftCardPage;

public class TC_03ErrorMessageDisplay extends BaseTest{
    @Test
    public void FillGiftCardDetails(){
        driver.get(factory.BaseClass.prop.getProperty("appUrl"));
        homePage.closePopUpIfPresent();
        homePage.goToGiftCards();
        GiftCardPage gift = new GiftCardPage(driver);

        gift.selectBirthdayCard();

        String recipientname= BaseClass.getProperty("recipientname");
        String sendername=BaseClass.getProperty("sendername");
        String recipientMobile=BaseClass.getProperty("recipientmobile");
        String senderMobile=BaseClass.getProperty("sendermobile");

        String senderEmail=BaseClass.getProperty("senderemail");
        String recipientEmail=BaseClass.getProperty("recipientemail");
        String message=BaseClass.getProperty("message");

        String amount=BaseClass.getProperty("amount");
        int amt=Integer.parseInt(amount);
        gift.fillGiftCardDetails(recipientname,sendername,recipientMobile,senderMobile,senderEmail,recipientEmail,message);
        gift.selectAmount1000();
        gift.clickProceedToCheckout();


        Assert.assertTrue(gift.isErrorDisplayed(),"Checkout allowed even though recipient email is missing");
        System.out.println(gift.getSenderEmailErrorMessage());
    }
}
