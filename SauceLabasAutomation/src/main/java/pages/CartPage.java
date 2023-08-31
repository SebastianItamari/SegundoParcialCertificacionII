package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    WebDriver driver;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(xpath = "//button[@name='checkout']")
    WebElement checkoutButton;

    @FindBy(className="shopping_cart_container")
    WebElement cartIconContainer;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }

    public boolean isProductDisplayed(String product) {
        for (WebElement element: productNames){
            if(element.getText().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }

    public void removeProduct(String product){
        String productLowerCase = product.toLowerCase();
        productLowerCase = productLowerCase.replace(" ", "-");
        String removeProductId = "remove-"+productLowerCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));
        removeButton.click();
    }

    public boolean isTextEqualToInCartIcon(String text){
        return cartIconContainer.getText().equalsIgnoreCase(text);
    }

    public boolean isCorrectTheInfoOfEveryProduct() {
        List<String> prices = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();

        for (int i = 0; i < driver.findElements(By.className("inventory_item_name")).size(); i++) {
            //Se utiliza el driver.findElements en cada iteración para evitar el error:
            //  stale element reference: element is not attached to the page document
            //aunque en este caso, los títulos de los productos no cambian y son estáticos.
            WebElement element = driver.findElements(By.className("inventory_item_name")).get(i);
            element.click();
            ProductPage productPage = new ProductPage(DriverManager.getDriver().driver);
            prices.add(productPage.getPrice());
            names.add(productPage.getName());
            descriptions.add(productPage.getDescription());
            cartIconContainer.click();
        }

        List<WebElement> elementsNames = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> elementsPrices = driver.findElements(By.className("inventory_item_price"));
        List<WebElement> elementsDescriptions = driver.findElements(By.xpath("//div[@class='inventory_item_desc']"));

        for (int i = 0; i < prices.size(); i++)
        {
            if(!elementsPrices.get(i).getText().equals(prices.get(i))|| !elementsNames.get(i).getText().equals(names.get(i)) || !elementsDescriptions.get(i).getText().equals(descriptions.get(i)))
            {
                return false;
            }
        }
        return true;
    }
}
