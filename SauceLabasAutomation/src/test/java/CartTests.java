import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class CartTests extends BaseTest{

    @Test
    public void verifyCartProductNameWhenAProductIsAdded() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.clickOnCartIconButton();

        CartPage cartPage = new CartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(cartPage.isProductDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(cartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
    }

    @Test
    public void verifyProductIsRemovedFromCartPage() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.clickOnCartIconButton();

        CartPage cartPage = new CartPage(DriverManager.getDriver().driver);
        cartPage.removeProduct("Sauce Labs Fleece Jacket");
        Assertions.assertFalse(cartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
    }
}
