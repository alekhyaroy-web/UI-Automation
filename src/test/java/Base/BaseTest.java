package Base;

import Driver.ChromeDriverFactory;
import Driver.DriverFactory;
import Driver.DriverManager;
import Driver.FirefoxDriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.LoggerUtil;

public class BaseTest {

    @BeforeMethod
    public void setup(){

        LoggerUtil.info("Setting up driver");
        String browser = System.getProperty("browser","chrome");
        DriverFactory factory;
        switch (browser.toLowerCase()){
            case "firefox" : factory = new FirefoxDriverFactory(); break;
            case "chrome" : default: factory = new ChromeDriverFactory();break;
        }
        DriverManager.initDriver(factory);

        LoggerUtil.info("Opening Url");
        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
    }
    @AfterMethod
    public void tearDown(){
        LoggerUtil.info("Closing the browser");
        DriverManager.quit();
    }
}
