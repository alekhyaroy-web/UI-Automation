package Driver;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private DriverManager(){
        super();
    }
    public static void initDriver(@NotNull DriverFactory driverFactory){
        driver.set(driverFactory.createDriver());
    }
    public static WebDriver getDriver(){
        return driver.get();
    }
    public static void quit(){
        if(getDriver()!= null){
            getDriver().quit();
            driver.remove();
        }
    }
}
