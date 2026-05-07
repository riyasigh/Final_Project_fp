package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.screenshotfile;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath="//a[@class='close-modal']")
    private WebElement POP_UP;

    @FindBy(id=("search"))
    private WebElement SEARCH_BOX;

    @FindBy(xpath = "//a[normalize-space()='GIFT CARDS']")
    private WebElement giftCardsLink;

    @FindBy(xpath = "//a[normalize-space()='Home Decor']")
    private WebElement HOME_DECOR_MENU;

//    @FindBy(xpath = "//a[contains(@class,'hd-menu-category-link ng-star-inserted')]")
//    private List<WebElement> HOME_DECOR_ITEMS;


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
        scrollBy(0, -300);
        giftCardsLink.click();
        System.out.println("Navigated to Gift Cards.");
    }

    public void hoverOverHomeDecor() {
        wait.until(ExpectedConditions.visibilityOf(HOME_DECOR_MENU));
        hover(HOME_DECOR_MENU);
        pause(1000);
        System.out.println("Hovered over Home Decor menu.");
        screenshotfile.takeScreenshot(driver,"HomeDecorPage");
    }

    public List<String> getHomeDecorItems() {
        List<String> itemNames = new ArrayList<>();
        System.out.println("\n===============================================");
        System.out.println("Home Decor Menu Items:");
        System.out.println("===============================================");

        // Re-fetch fresh elements every time — never use @FindBy for dynamic Angular lists
        List<WebElement> freshItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//a[contains(@class,'hd-menu-category-link ng-star-inserted')]")
        ));

        for (WebElement item : freshItems) {
            String name = item.getText().trim();
            if (!name.isEmpty()) {
                itemNames.add(name);
                System.out.println("• " + name);
            }
        }

        System.out.println("Total items found: " + itemNames.size());
        System.out.println("===============================================\n");
        return itemNames;
    }

//    public List<String> getHomeDecorItems() {
//        List<String> itemNames = new ArrayList<>();
//        System.out.println("\n===============================================");
//        System.out.println("Home Decor Menu Items:");
//        System.out.println("===============================================");
//
//        for (WebElement item : HOME_DECOR_ITEMS) {
//            String name = item.getText().trim();
//            if (!name.isEmpty()) {
//                itemNames.add(name);
//                System.out.println("• " + name);
//            }
//        }
//        System.out.println("Total items found: " + itemNames.size());
//        System.out.println("===============================================\n");
//        return itemNames;
//    }
}
