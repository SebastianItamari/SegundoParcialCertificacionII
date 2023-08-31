package pages;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HomePage {
    WebDriver driver;

    @FindBy(className ="app_logo")
    WebElement pageTitle;

    @FindBy(className="product_sort_container")
    WebElement sortComboBox;

    @FindBy(className="shopping_cart_container")
    WebElement cartIconContainer;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    WebElement menuButton;

    @FindBy(linkText="Twitter")
    WebElement twitterButton;

    @FindBy(linkText="Facebook")
    WebElement facebookButton;

    @FindBy(linkText="LinkedIn")
    WebElement linkedInButton;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageTitleIsDisplayed(){
        return pageTitle.isDisplayed();
    }

    public boolean someAddButtonExist(){
        return !driver.findElements(By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory']")).isEmpty();
    }
    public void selectSortComboBox(String option){
        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText(option);
    }

    public boolean areProductsInDescendantOrderByName(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();

        for(WebElement element: products){
            actualProductNames.add(element.getText());
        }

        return Ordering.natural().reverse().isOrdered(actualProductNames);
    }

    public boolean areProductsInAscendantOrderByName(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();

        for(WebElement element: products){
            actualProductNames.add(element.getText());
        }

        return Ordering.natural().isOrdered(actualProductNames);
    }

    public boolean areProductsInAscendantOrderByPrice(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualProductNames = new ArrayList<>();

        for(WebElement element: products){
            actualProductNames.add(Double.parseDouble(element.getText().substring(1)));
        }

        return Ordering.natural().isOrdered(actualProductNames);
    }

    public boolean areProductsInDescendantOrderByPrice(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualProductNames = new ArrayList<>();

        for(WebElement element: products){
            actualProductNames.add(Double.parseDouble(element.getText().substring(1)));
        }

        return Ordering.natural().reverse().isOrdered(actualProductNames);
    }

    public void clickOnMenuButton(){
        menuButton.click();
    }

    public void clickOnResetAppStateButton(){
        WebElement resetAppStateButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[id='reset_sidebar_link']")));
        resetAppStateButton.click();
    }

    public void clickOnCartIconButton(){
        cartIconContainer.click();
    }

    public void clickOnTwitterButton(){
        twitterButton.click();
    }

    public void clickOnFacebookButton(){
        facebookButton.click();
    }

    public void clickOnLinkedInButton(){
        linkedInButton.click();
    }
    public boolean isTextEqualToInCartIcon(String text){
        return cartIconContainer.getText().equalsIgnoreCase(text);
    }

    public boolean verifyNewOpenWindowURL(String url, String mainWindow){
        Set<String> openedWindows = driver.getWindowHandles();
        for(String window: openedWindows) {
            if(!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        String newWindowURL = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(mainWindow);
        return newWindowURL.equals(url);
    }

    public void addProductToCart(String productName){
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartId = "add-to-cart-";
        addToCartId = addToCartId + productNameLowerCase;

        WebElement addToCartButton = driver.findElement(By.id(addToCartId));
        addToCartButton.click();
    }

    public void removeProduct(String product){
        String productLowerCase = product.toLowerCase();
        productLowerCase = productLowerCase.replace(" ", "-");
        String removeProductId = "remove-"+productLowerCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));
        removeButton.click();
    }

    public void clickOnLogOutButton(){
        WebElement logOutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        logOutButton.click();
    }
}
