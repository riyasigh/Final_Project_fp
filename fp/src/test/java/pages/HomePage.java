package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(xpath="//a[@class='close-modal']")
    private WebElement POP_UP;
    @FindBy(id=("search"))
    private WebElement SEARCH_BOX;
    @FindBy(xpath = "//a[normalize-space()='GIFT CARDS']")
    private WebElement giftCardsLink;
    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void closePopUpIfPresent(){
        try{
            jsClick(POP_UP);
            System.out.println("PopUp closed");
        } catch (Exception e) {
            System.out.println("Popup not present - continuing");
        }
    }

    public void searchItem(String item){
        wait.until(ExpectedConditions.visibilityOf(SEARCH_BOX));
        SEARCH_BOX.sendKeys(item);
        action.sendKeys(Keys.ENTER).perform();
        pause(3000);
        System.out.println("Searched for: "+item);
    }

    public void goToGiftCards() {
        pause(2000);
        scrollBy(0, -300);
        pause(2000);
        giftCardsLink.click();
        pause(1000);
        System.out.println("Navigated to Gift Cards.");
    }
}
