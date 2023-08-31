import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import utilities.DriverManager;

public class CheckoutTests extends BaseTest{

    @Test
    public void doNotContinueWithEmptyFields(){
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
        checkoutInformationPage.setLastNameTextBox("");
        checkoutInformationPage.setZipCodeTextBox("1234");
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
    public void doNotFinishCheckoutWithZeroProducts() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnCartIconButton();

        CartPage cartPage = new CartPage(DriverManager.getDriver().driver);
        cartPage.clickOnCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(DriverManager.getDriver().driver);
        checkoutInformationPage.setFirstNameTextBox("Sebastián");
        checkoutInformationPage.setLastNameTextBox("Itamari");
        checkoutInformationPage.setZipCodeTextBox("1234");
        checkoutInformationPage.clickOnContinueButton();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverManager.getDriver().driver);
        checkoutOverviewPage.clickOnFinishButton();

        FinishPage finishPage = new FinishPage(DriverManager.getDriver().driver);

        Assertions.assertFalse(finishPage.isCheckoutTitleDisplayed("Checkout: Complete!"));
        Assertions.assertFalse(finishPage.isCheckoutTextDisplayed("Thank you for your order!"));
    }

    @Test
    public void onlyNumbersInPostalCodeField(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        //homePage.clickOnAddBackpackButton();
        homePage.clickOnCartIconButton();

        CartPage cartPage = new CartPage(DriverManager.getDriver().driver);
        cartPage.clickOnCheckoutButton();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(DriverManager.getDriver().driver);
        checkoutInformationPage.setFirstNameTextBox("Sebastián");
        checkoutInformationPage.setLastNameTextBox("Itamari");
        checkoutInformationPage.setZipCodeTextBox("asdf123");
        checkoutInformationPage.clickOnContinueButton();
        Assertions.assertTrue(checkoutInformationPage.existErrorMessageLabel());
        Assertions.assertTrue(checkoutInformationPage.isErrorTextDisplayed("Error: Postal Code must be only numeric"));
    }
}
