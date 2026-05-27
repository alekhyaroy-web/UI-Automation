package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.LoggerUtil;
import utils.WaitUtil;

public class LoginPage {
    WebDriver driver;
    WaitUtil wait ;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtil(driver);
        PageFactory.initElements(driver, this);
    }


    By signupForm = By.className("signup-form");

    @FindBy(name = "name")
    WebElement userName;

    @FindBy(css = "input[data-qa='signup-email']")
    WebElement userEmail;

    @FindBy(xpath = "//button[text() = 'Signup']")
    WebElement signupButton;

    public void isSignUpFormVisible(){
        LoggerUtil.debug("Checking visibility of SignUP Form");
        try{
            wait.waitForVisibility(signupForm,10);
            String text = driver.findElement(signupForm).getText();
            Assert.assertEquals(text,"New User Signup!");
        }
        catch (TimeoutException e){
            System.out.println("Form not appeared !!!");
        }
    }
    public void fillSignUpForm(String name , String email){
        LoggerUtil.info("Sending name and email address");
        userName.sendKeys(name);
        LoggerUtil.info(email);
        userEmail.sendKeys(email);
    }
    public void clickSignUpButton(){

        LoggerUtil.info("Clicking on SignUp button.");
        signupButton.click();
    }

}
