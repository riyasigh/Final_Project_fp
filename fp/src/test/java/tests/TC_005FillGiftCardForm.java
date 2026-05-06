package tests;

import factory.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GiftCardPage;
import pages.SearchResultPage;

public class TC_005FillGiftCardForm extends BaseTest{

    @Test
    public void FillGiftCardForm() {
        driver.get(BaseClass.getProperty("appUrl"));
        homePage.closePopUpIfPresent();
        homePage.searchItem(BaseClass.getProperty("item"));

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.SearchResultPageOpens();

        searchResultPage.openMoreFilters();
        searchResultPage.expandFilter(BaseClass.getProperty("filter1"));

        String maxPrice = BaseClass.getProperty("maxprice");
        String count = BaseClass.getProperty("count");
        int maxp = Integer.parseInt(maxPrice);
        int c = Integer.parseInt(count);

        searchResultPage.setMaxPrice(maxp);
        searchResultPage.expandFilter(BaseClass.getProperty("filter2"));

        searchResultPage.selectBrand(BaseClass.getProperty("brandName"));

        searchResultPage.clickApply();

        searchResultPage.displayTopProducts(c, maxp);

        searchResultPage.openGiftCard();
        GiftCardPage gift = new GiftCardPage(driver);

        gift.selectBirthdayCard();

        String recipientname=BaseClass.getProperty("recipientname");
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
    }
}
