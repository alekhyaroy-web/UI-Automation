package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    private WebDriver driver;

    public WaitUtil(WebDriver driver){
        this.driver = driver;
    }

    private WebDriverWait getWait(int timeoutInSeconds){

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(timeoutInSeconds)
        );
    }

    public WebElement waitForVisibility(By locator,int timeoutInSeconds){

        return getWait(timeoutInSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator,int timeoutInSeconds){

        return getWait(timeoutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitforElementPresence(By locator,int timeoutInSeconds){

        return getWait(timeoutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Added for Google Ads popup handling
    public WebElement waitForElementClickable(By locator,int timeoutInSeconds){

        return getWait(timeoutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Optional reusable invisibility wait
    public boolean waitForInvisibility(By locator,int timeoutInSeconds){

        return getWait(timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}