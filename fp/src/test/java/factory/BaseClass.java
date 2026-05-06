package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    protected static WebDriver driver;
    public static Properties prop;

    private static void loadConfig(){
        try{
            prop=new Properties();
            FileInputStream fis=new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties",e);
        }
    }

    public static void setUp(){
        loadConfig();
        String browser= prop.getProperty("browser");
        switch (browser){
            case "chrome":
                driver=new ChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser name in config file");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    //provide driver to other classes
    public static WebDriver getDriver(){
        return driver;
    }

    public static void tearDown(){
        driver.quit();
        System.out.println("Browser closed successfully");
    }


    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
