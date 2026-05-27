package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.LoggerUtil;
import utils.WaitUtil;

import java.util.Random;

public class SignUpDetailsPage {
    WebDriver driver;
    WaitUtil waitUtil;

    public SignUpDetailsPage(WebDriver driver){
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        PageFactory.initElements(driver, this);
    }

    Random random = new Random();

    By accountInformationText = By.xpath("//b[text()='Enter Account Information']");

    @FindBy(id="id_gender1")
    private WebElement Mr;

    @FindBy(id="id_gender2")
    private WebElement Mrs;

    @FindBy(id="name")
    private WebElement name;

    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="days")
    private WebElement days;

    @FindBy(id="months")
    private WebElement months;

    @FindBy(id = "years")
    private WebElement year;

    @FindBy(id="first_name")
    private WebElement firstName;

    @FindBy(id="last_name")
    private WebElement lastName;

    @FindBy(id="address1")
    private WebElement address;

    @FindBy(id="state")
    private WebElement state;

    @FindBy(id="city")
    private WebElement city;

    @FindBy(id="zipcode")
    private WebElement zipCode;

    @FindBy(id="mobile_number")
    private WebElement mobileNumber;

    @FindBy(xpath = "//button[text()='Create Account']")
    private WebElement createAccount;

    @FindBy(xpath = "//b[text()='Account Created!']")
    private WebElement successMessage;

    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(),' Logged in as ')]")
    private WebElement signinUser;

    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    private WebElement deleteAccount;

    @FindBy(xpath = "//b[contains(text(),'Account Deleted!')]")
    private WebElement accountDeleted;

    @FindBy(css = "div[aria-label='Close ad']")
    private WebElement closeGoogleAd;


    public void checkAccountInformationText(){
        LoggerUtil.info("Validating Account Information Text appearing");
        try{
            waitUtil.waitForVisibility(accountInformationText,10);
            String text = driver.findElement(accountInformationText).getText();
            Assert.assertEquals(text,"ENTER ACCOUNT INFORMATION");
        } catch (Exception e) {
            System.out.println("Enter Account Information text missing");;
        }
    }
    public void selectGenderLabel(String genderLabel){
        LoggerUtil.info("Checking and selecting label as per first name");
        if(genderLabel.equals("Mr")){
            Mr.click();
        }
        else {
            Mrs.click();
        }
    }

    public void fillSignUpForm(String userPassword,String FirstName,String LastName){
        LoggerUtil.debug("Starting filling form");
        password.sendKeys(userPassword);
        Integer randomDate = random.nextInt(20)+1;
        Integer randomMonth = random.nextInt(12)+1;
        Select date = new Select(days);
        date.selectByValue(String.valueOf(randomDate));

        Select month = new Select(months);
        month.selectByValue(String.valueOf(randomMonth));

        Select selectYear = new Select(year);

        selectYear.selectByValue("1991");

        firstName.sendKeys(FirstName);

        lastName.sendKeys(LastName);

        address.sendKeys("Test");

        state.sendKeys("Test");

        city.sendKeys("Test");

        zipCode.sendKeys("123456");

        mobileNumber.sendKeys("1234567890");

        createAccount.click();
    }
    public void closeGoogleAdIfPresent(){

        try{

            waitUtil.waitForElementClickable(
                    By.cssSelector("div[aria-label='Close ad']"),
                    5
            );

            closeGoogleAd.click();

            LoggerUtil.info("Google Ad closed successfully");

        } catch (Exception e){

            LoggerUtil.info("Google Ad not present");

        }
    }

    public void verifySuccess(){
        LoggerUtil.info("Verifying Account Created Text");
        try{
            String text = successMessage.getText();

            Assert.assertEquals(text,"ACCOUNT CREATED!");
        } catch (Exception e) {
            System.out.println("ACCOUNT CREATED! text missing");
        }

        continueButton.click();
    }
    public void verifyUserName(String FirstName){
        closeGoogleAdIfPresent();
        LoggerUtil.debug("Verifying username");
        String usrName = signinUser.getText();
        String expectedText = "Logged in as "+ FirstName;
        Assert.assertEquals(usrName,expectedText);
    }

    public void clickDeleteAccount(){
        deleteAccount.click();
    }

    public void verifyAccountDeleted(){
        String delAccountText = accountDeleted.getText();

        try{
            Assert.assertEquals(delAccountText,"ACCOUNT DELETED!");
        } catch (Exception e) {
            System.out.println("ACCOUNT DELETED! text not appeared");
        }
        continueButton.click();
    }





}
