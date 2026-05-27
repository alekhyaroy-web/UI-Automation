package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverFactory implements DriverFactory{
    @Override
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");

        // Disable images for faster execution
        options.addArguments("--blink-settings=imagesEnabled=false");

        // Disable automation controlled flag
        options.addArguments("--disable-blink-features=AutomationControlled");

//        // Optional: block ads using extension
//        options.addExtensions(
//                new File("C:\\Users\\alekh\\IdeaProjects\\selenium-java-ui-automation-framework\\src\\main\\resources\\extensions\\ublock.crx")
//        );
//
//        // Additional Chrome preferences
//        Map<String, Object> prefs = new HashMap<>();
//
//        prefs.put(
//                "profile.default_content_setting_values.notifications",
//                2
//        );
//
//        prefs.put(
//                "profile.managed_default_content_settings.images",
//                2
//        );
//
//        options.setExperimentalOption("prefs", prefs);
        if(Boolean.parseBoolean(System.getProperty("headless","false"))){
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }
}

