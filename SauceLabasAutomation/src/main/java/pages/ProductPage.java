package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    WebDriver driver;
    @FindBy(className = "inventory_details_price")
    WebElement productPrice;

    @FindBy(xpath = "//div[@class='inventory_details_name large_size']")
    WebElement productName;

    @FindBy(xpath = "//div[@class='inventory_details_desc large_size']")
    WebElement productDescription;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPrice()
    {
        return  productPrice.getText();
    }

    public String getName()
    {
        return  productName.getText();
    }

    public String getDescription()
    {
        return  productDescription.getText();
    }
}
