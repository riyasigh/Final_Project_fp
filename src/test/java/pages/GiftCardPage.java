package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.screenshotfile;

import java.security.PrivateKey;
import java.util.List;

public class GiftCardPage extends BasePage{

    @FindBy (xpath= "//img[@alt='Pepperfry Birthday Gift Card']")
    private WebElement BIRTHDAY_GIFTCARD;

    @FindBy (xpath= "//input[@formcontrolname='sname']")
    private WebElement SENDER_NAME;

    @FindBy (xpath= "//input[@formcontrolname='rname']")
    private WebElement RECEIVER_NAME;

    @FindBy (xpath= "//input[@formcontrolname='smob']")
    private WebElement SENDER_MOBILE;

    @FindBy (xpath= "//input[@formcontrolname='rmob']")
    private WebElement RECEIVER_MOBILE;

    @FindBy (xpath= "//input[@formcontrolname='rmail']")
    private WebElement RECEIVER_EMAIL;

    @FindBy (xpath= "//input[@formcontrolname='smail']")
    private WebElement SENDER_EMAIL;

    @FindBy (xpath= "//textarea[@placeholder='Enter your message here']")
    private WebElement MESSAGE_INPUT;

    @FindBy (xpath= "//span[contains(@class,'gc-den-card-value') and text()='1000']/ancestor::div[contains(@class,'gc-den-card')]//a[text()='ADD']")
    private WebElement ADD_1000Btn;

    @FindBy (xpath= "//button[@id='gc-proceed-checkout-btn']//div")
    private WebElement PROCEED_BTN;

    @FindBy(xpath = "//div[contains(@class,'form-error') and contains(text(),'Email')]")
    private WebElement ERROR;


    public GiftCardPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);;
    }

    public void selectBirthdayCard(){
        wait.until(ExpectedConditions.visibilityOf(BIRTHDAY_GIFTCARD));
        jsScrollIntoView(BIRTHDAY_GIFTCARD);
//        pause(2000);
        jsClick(BIRTHDAY_GIFTCARD);
        System.out.println("Birthday card selected.");
    }

    public void fillRecepientName(String rname){
        RECEIVER_NAME.sendKeys(rname);
    }

    public void fillSenderName(String sname){
        SENDER_NAME.sendKeys(sname);
    }

    public void fillReceiverMobileNumber(String RMobNum){
        RECEIVER_MOBILE.sendKeys(RMobNum);
    }

    public void fillSenderMobileNumber(String SMobNum){
        SENDER_MOBILE.sendKeys(SMobNum);
    }

    public void fillSenderEmail(String SEmail){
        SENDER_EMAIL.sendKeys(SEmail);
    }

    public void fillReceiverEmail(String REmail){
        RECEIVER_EMAIL.sendKeys(REmail);
    }

    public void fillMessage(String Message){
        MESSAGE_INPUT.sendKeys(Message);
    }

    public void selectAmount1000(){
        shortWait.until(ExpectedConditions.visibilityOf(ADD_1000Btn));
        jsScrollIntoView(ADD_1000Btn);
        jsClick(ADD_1000Btn);
        System.out.println("1000 denomination selected.");
    }

    public void clickProceedToCheckout() {
        jsClick(PROCEED_BTN);
        stopNavigation();
        System.out.println("Proceed to Checkout clicked.");

        try {
            shortWait.until(ExpectedConditions.visibilityOf(ERROR));
            jsScrollIntoView(ERROR);
        } catch (Exception e) {
            System.out.println("No error popup appeared after Proceed click.");
        }
    }

    public String getSenderEmailErrorMessage(){
        try{
            shortWait.until(ExpectedConditions.visibilityOf(ERROR));
            screenshotfile.takeScreenshot(driver,"EmailErrorPage");
            return ERROR.getText().trim();
        }catch(Exception e){
            return "";
        }
    }

    public boolean isErrorDisplayed() {
        try {
            return ERROR.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillGiftCardDetails(String recipientName, String senderName,
                                    String recipientMobile, String senderMobile,
                                    String senderEmail,String receiverEmail ,String message){
        fillRecepientName(recipientName);
        fillSenderName(senderName);
        fillReceiverMobileNumber(recipientMobile);
        fillSenderMobileNumber(senderMobile);
        fillSenderEmail(senderEmail);
        fillReceiverEmail(receiverEmail);
        fillMessage(message);
        System.out.println("Gift card form filled.");
    }
}



