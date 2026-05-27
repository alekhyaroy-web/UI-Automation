package SignUP;

import Base.BaseTest;
import Driver.DriverManager;
import Page.HomePage;
import Page.LoginPage;
import Page.SignUpDetailsPage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utils.TestDataUtil;

public class SingUpTest extends BaseTest {
    @Test
    @Description("Verify that user can Signup properly")
    public void testUserSingUp(){
        TestDataUtil.generateCredentials();
        WebDriver driver = DriverManager.getDriver();
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignUpDetailsPage signUpDetailsPage = new SignUpDetailsPage(driver);

        homePage.isHomePageVisible();
        homePage.clickSignupOrLogin();
        loginPage.fillSignUpForm(TestDataUtil.getFirstName(),TestDataUtil.getRandomMail());
        loginPage.clickSignUpButton();

        signUpDetailsPage.checkAccountInformationText();
        signUpDetailsPage.selectGenderLabel(TestDataUtil.getGenderLabel());
        signUpDetailsPage.fillSignUpForm(TestDataUtil.getStoredPassword(),TestDataUtil.getFirstName(),TestDataUtil.getLastName());
        signUpDetailsPage.verifySuccess();
        signUpDetailsPage.verifyUserName(TestDataUtil.getFirstName());
        signUpDetailsPage.clickDeleteAccount();
        signUpDetailsPage.verifyAccountDeleted();
    }
}
