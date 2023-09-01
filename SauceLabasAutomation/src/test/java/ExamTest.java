import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import utilities.DriverManager;

public class ExamTest extends BaseTest{
    @Test
    public void examTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Onesie");
        homePage.clickOnCartIconButton();

        CartPage cartPage = new CartPage(DriverManager.getDriver().driver);

        //Verificando que los ítems agregados estén en el carrito
        Assertions.assertTrue(cartPage.isProductDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(cartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(cartPage.isProductDisplayed("Sauce Labs Onesie"));

        //Se verifica que el precio de cada producto en la página del carrito
        //sea igual a su precio en su página de producto correspondiente
        Assertions.assertTrue(cartPage.isCorrectTheInfoOfEveryProduct());
        //Se verifica que el ícono del carrito tiene el número de productos agregados
        Assertions.assertTrue(cartPage.isTextEqualToInCartIcon("3"));

        cartPage.removeProduct("Sauce Labs Bike Light");
        cartPage.removeProduct("Sauce Labs Fleece Jacket");
        cartPage.removeProduct("Sauce Labs Onesie");

        //Se verifica que la información de cada producto en la página del carrito
        //sea igual a su información en su página de producto correspondiente
        Assertions.assertFalse(cartPage.isProductDisplayed("Sauce Labs Bike Light"));
        Assertions.assertFalse(cartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertFalse(cartPage.isProductDisplayed("Sauce Labs Onesie"));
        //Se verifica que el ícono del carrito no contenga ningún número debido a que se
        //eliminaron todos los productos
        Assertions.assertTrue(cartPage.isTextEqualToInCartIcon(""));
    }

    @Test
    public void resetAppStateButtonInHomePageTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Onesie");
        homePage.clickOnMenuButton();
        homePage.clickOnResetAppStateButton();
        Assertions.assertTrue(homePage.isTextEqualToInCartIcon(""));
        //homePage.removeProduct("Sauce Labs Backpack");
        //homePage.removeProduct("Sauce Labs Onesie");
        Assertions.assertFalse(homePage.someAddButtonExist());
    }

    @Test
    public void errorMessageIsDisplayedWithEmptyFields(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.clickOnCartIconButton();

        CartPage cartPage = new CartPage(DriverManager.getDriver().driver);
        cartPage.clickOnCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(DriverManager.getDriver().driver);
        checkoutInformationPage.setFirstNameTextBox("Sebastián");
        checkoutInformationPage.setLastNameTextBox("Itamari");
        checkoutInformationPage.setZipCodeTextBox("");
        checkoutInformationPage.clickOnContinueButton();
        Assertions.assertTrue(checkoutInformationPage.existErrorMessageLabel());
        Assertions.assertTrue(checkoutInformationPage.isErrorTextDisplayed("Error: First Name is required") || checkoutInformationPage.isErrorTextDisplayed("Error: Last Name is required") || checkoutInformationPage.isErrorTextDisplayed("Error: Postal Code is required"));
    }

    @Test
    public void correctTotalPriceTest(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Onesie");
        homePage.clickOnCartIconButton();

        CartPage cartPage = new CartPage(DriverManager.getDriver().driver);
        cartPage.clickOnCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(DriverManager.getDriver().driver);
        checkoutInformationPage.setFirstNameTextBox("Sebastián");
        checkoutInformationPage.setLastNameTextBox("Itamari");
        checkoutInformationPage.setZipCodeTextBox("1234");
        checkoutInformationPage.clickOnContinueButton();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(checkoutOverviewPage.isTotalPriceCorrect());
    }

    @Test
    public void verifyInfoOfAddedProductsTest() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Onesie");
        homePage.clickOnCartIconButton();

        CartPage cartPage = new CartPage(DriverManager.getDriver().driver);

        //Se verifica que la información de cada producto en la página del carrito
        //sea igual a su información en su página de producto correspondiente
        Assertions.assertTrue(cartPage.isCorrectTheInfoOfEveryProduct());
    }

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
}
