import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class MenuTest extends BaseTest{
    @Test
    public void resetAppStateButtonInHomePageTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.clickOnMenuButton();
        homePage.clickOnResetAppStateButton();
        Assertions.assertTrue(homePage.isTextEqualToInCartIcon(""));
        //homePage.removeProduct("Sauce Labs Backpack");
        Assertions.assertFalse(homePage.someAddButtonExist());
    }
}
