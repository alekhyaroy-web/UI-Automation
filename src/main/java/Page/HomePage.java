package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

public class HomePage {
    WebDriver driver;

    WaitUtil waitUtil;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.waitUtil = new WaitUtil(driver);
    }

    By carouselSlide = By.id("slider-carousel");
    @FindBy(xpath = "//a[text()=' Signup / Login']")
    WebElement loginSignUpButton;

    public Boolean isHomePageVisible(){
        try {
            waitUtil.waitForVisibility(carouselSlide, 10);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickSignupOrLogin(){
        loginSignUpButton.click();
    }
}
