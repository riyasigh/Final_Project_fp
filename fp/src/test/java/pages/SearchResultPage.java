package pages;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {
    @FindBy(xpath="//span[contains(text(),' More Filters ')]")
    private WebElement MORE_FILTERS;

    @FindBy(xpath = "//h1[normalize-space()='Bookshelves']")
    private WebElement BOOKSHELVES_OPEN;

    @FindBy (xpath="//accordion-heading[contains(text(),' Price ')]")
    private WebElement PRICE_FILTER;

    @FindBy (xpath="//input[@type='number' and @formcontrolname='inputMax']")
    private WebElement MAX_PRICE;

    @FindBy (xpath="//accordion-heading[contains(text(),' Brand ')]")
    private WebElement BRAND_FILTER;

    @FindBy (xpath="//label[@for='WoodenMood']")
    private WebElement BRAND_LABEL;

    @FindBy (xpath="//span[contains(text(),'APPLY')]")
    private WebElement APPLY_BUTTON;

    @FindBy (xpath="//div[@class='product-details marginBottom-4']/h2")
    List<WebElement> PRODUCT_LIST;

    @FindBy (xpath="//span[@class='product-offer-price font-bold text-xl ng-star-inserted']")
    List<WebElement>  PRODUCT_PRICE;

    @FindBy (xpath= "//a[contains(text(),'GIFT CARDS')]")
    private WebElement GIFT_CARD;

    public SearchResultPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void SearchResultPageOpens(){
        Assert.assertTrue(BOOKSHELVES_OPEN.isDisplayed(),"Bookshelves is not found");
    }

    public void openMoreFilters(){
        wait.until(ExpectedConditions.elementToBeClickable(MORE_FILTERS));
        MORE_FILTERS.click();
        pause(2000);
        System.out.println("More Filters panel opened.");
    }

    public void expandFilter(String filterName){
        By locator = By.xpath(
                String.format("//accordion-heading[normalize-space()='%s']", filterName));
        WebElement filter = waitForVisibility(locator);
        jsScrollIntoView(filter);
        pause(1000);
        filter.click();
        pause(1500);
        System.out.println("Expanded filter: "+ filterName);
    }

    public void setMaxPrice(int maxPrice){
        wait.until(ExpectedConditions.visibilityOf(MAX_PRICE));
        jsScrollIntoView(MAX_PRICE);
        pause(1000);
        MAX_PRICE.clear();
        MAX_PRICE.sendKeys(String.valueOf(maxPrice));
        pause(1500);
        System.out.println("Max price set to: ₹"+ maxPrice);
    }

    public void selectBrand(String brandName){
        wait.until(ExpectedConditions.visibilityOf(BRAND_LABEL));
        jsScrollIntoView(BRAND_LABEL);
        pause(1000);
        BRAND_LABEL.click();
        pause(1000);
        System.out.println("Selected brand: "+brandName);
    }

    public void clickApply(){
        wait.until(ExpectedConditions.elementToBeClickable(APPLY_BUTTON));
        jsScrollIntoView(APPLY_BUTTON);
        pause(500);
        jsClick(APPLY_BUTTON);
        System.out.println("Clicked: Apply");
    }

    public void displayTopProducts(int count,int maxPrice){
        pause(2000);
        scrollBy(0,300);
        pause(2000);

        List<String[]> products=new ArrayList<>();
        System.out.println("\n===============================================");

        System.out.println("Total products below ₹" + maxPrice + ": " +PRODUCT_LIST.size());
        System.out.println("Displaying Top " + count + " products:");
        System.out.println("===============================================\n");

        int displayed=Math.min(count,PRODUCT_LIST.size());
        for(int i=0;i< displayed;i++){
            String name=PRODUCT_LIST.get(i).getText();
            String price=(i<PRODUCT_PRICE.size()) ? PRODUCT_PRICE.get(i).getText() : "0";
            if(!name.isEmpty() && !price.isEmpty()){
                products.add(new String[]{name,price});
                System.out.println("Product: " + name + " | Price: " + price);
            }
        }
    }

    // Get all product prices as integers
    public List<Integer> getAllProductPrices() {
        List<Integer> prices = new ArrayList<>();

        for (WebElement priceElement : PRODUCT_PRICE) {
            String priceText = priceElement.getText()
                    .replaceAll("[^0-9]", "")
                    .trim();
            if (!priceText.isEmpty()) {
                prices.add(Integer.parseInt(priceText));
            }
        }
        return prices;
    }

    public void openGiftCard(){
        pause(1000);
        scrollBy(0,-300);
        pause(1000);
        GIFT_CARD.click();
        pause(1000);
        System.out.println("Navigated to Gift Cards.");
    }
}
