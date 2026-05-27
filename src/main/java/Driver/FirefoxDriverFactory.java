package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory implements DriverFactory{
    @Override
    public WebDriver createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled",false);
        if(Boolean.parseBoolean(System.getProperty("headless","false"))){
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }
}
