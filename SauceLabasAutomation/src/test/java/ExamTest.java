import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
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
        Assertions.assertTrue(cartPage.isCorrectThePriceOfEveryProduct());
        //Se verifica que el ícono del carrito tiene el número de productos agregados
        Assertions.assertTrue(cartPage.isTextEqualToInCartIcon("3"));

        cartPage.removeProduct("Sauce Labs Bike Light");
        cartPage.removeProduct("Sauce Labs Fleece Jacket");
        cartPage.removeProduct("Sauce Labs Onesie");

        //Se verifica que los ítems removidos no aparezcan en la lista de productos
        //de la página del carrito
        Assertions.assertFalse(cartPage.isProductDisplayed("Sauce Labs Bike Light"));
        Assertions.assertFalse(cartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertFalse(cartPage.isProductDisplayed("Sauce Labs Onesie"));
        //Se verifica que el ícono del carrito no contenga ningún número debido a que se
        //eliminaron todos los productos
        Assertions.assertTrue(cartPage.isTextEqualToInCartIcon(""));
    }
}
