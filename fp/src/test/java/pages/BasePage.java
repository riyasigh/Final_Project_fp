package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        this.action=new Actions(driver);
        this.js=(JavascriptExecutor) driver;
    }

    public WebElement waitForVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForPresence(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void jsClick(WebElement element){
        js.executeScript("arguments[0].click()",element);
    }

    public void jsScrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView({block:'center'});",element);
    }

    public void scrollBy(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    public void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
