package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinishPage {
    WebDriver driver;

    @FindBy(xpath = "//h2[@class='complete-header']")
    WebElement checkoutMessage;

    @FindBy(xpath = "//span[@class='title']")
    WebElement checkoutTitle;

    public FinishPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isCheckoutTextDisplayed(String message){
        String actualErrorMessage = checkoutMessage.getText();
        return message.equalsIgnoreCase(actualErrorMessage);
    }

    public boolean isCheckoutTitleDisplayed(String message){
        String actualErrorMessage = checkoutTitle.getText();
        return message.equalsIgnoreCase(actualErrorMessage);
    }
}
