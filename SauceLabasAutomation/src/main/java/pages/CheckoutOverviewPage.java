package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage {
    WebDriver driver;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productsPrices;

    @FindBy(xpath = "//button[@name='finish']")
    WebElement finishButton;

    @FindBy(className = "summary_tax_label")
    WebElement taxPrice;

    @FindBy(xpath = "//div[@class='summary_info_label summary_total_label']")
    WebElement totalPrice;

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnFinishButton(){
        finishButton.click();
    }

    public boolean isTotalPriceCorrect(){
        List<Double> prices = new ArrayList<>();
        double sum = Double.parseDouble(taxPrice.getText().substring(6));

        for(WebElement element: productsPrices){
            double aux = Double.parseDouble(element.getText().substring(1));
            prices.add(aux);
            sum += aux;
        }

        return (sum == Double.parseDouble(totalPrice.getText().substring(8)));
    }
}
