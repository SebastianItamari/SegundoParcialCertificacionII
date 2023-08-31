package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {
    WebDriver driver;

    @FindBy(id = "first-name")
    WebElement firstNameTextBox;

    @FindBy(name = "lastName")
    WebElement lastNameTextBox;

    @FindBy(css = "input[data-test='postalCode']")
    WebElement zipCodeTextBox;

    @FindBy(xpath = "//input[@id='continue']")
    WebElement continueButton;

    public CheckoutInformationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstNameTextBox(String firstName){
        firstNameTextBox.sendKeys(firstName);
    }

    public void setLastNameTextBox(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }

    public void setZipCodeTextBox(String zipCode){
        zipCodeTextBox.sendKeys(zipCode);
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }

    public boolean existErrorMessageLabel(){
        return !driver.findElements(By.xpath("//h3[@data-test='error']")).isEmpty();
    }
    public boolean isErrorTextDisplayed(String error){
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String actualErrorMessage = errorMessage.getText();
        return error.equalsIgnoreCase(actualErrorMessage);
    }
}
