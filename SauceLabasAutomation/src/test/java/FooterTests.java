import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class FooterTests extends BaseTest{
    @Test
    public void facebookButtonTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnFacebookButton();
        Assertions.assertTrue(homePage.verifyNewOpenWindowURL("https://www.facebook.com/saucelabs", DriverManager.getDriver().driver.getWindowHandle()));
        //Assertions.assertTrue(homePage.verifyNewOpenWindowURL("https://www.google.com", DriverManager.getDriver().driver.getWindowHandle()));
    }

    @Test
    public void twitterButtonTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnTwitterButton();
        Assertions.assertTrue(homePage.verifyNewOpenWindowURL("https://twitter.com/saucelabs", DriverManager.getDriver().driver.getWindowHandle()));
    }

    @Test
    public void linkedInButtonTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnLinkedInButton();
        Assertions.assertTrue(homePage.verifyNewOpenWindowURL("https://www.linkedin.com/company/sauce-labs/", DriverManager.getDriver().driver.getWindowHandle()));
    }
}
