package pl.jakub.myWebsite.cfg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverConfig {


    private final static String BROWSER = "chrome";

    public static WebDriver webDriverInstance = null;

    public static void Initialize() {
        if (webDriverInstance == null) {
            switch (BROWSER) {
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                    webDriverInstance = new FirefoxDriver();
                    break;
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                    webDriverInstance = new ChromeDriver();
                    break;
            }
        }
        webDriverInstance.manage().window().maximize();
        webDriverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void closeDriver() {
        webDriverInstance.close();
        webDriverInstance = null;
    }

    public static void quitDriver() {
        webDriverInstance.quit();
        webDriverInstance = null;
    }

    public static WebDriver getWebDriverInstance() {
        return webDriverInstance;
    }
}