package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessOrderLocator {
    public WebElement shoppingCartButton(WebDriver driver){
        return driver.findElement(By.xpath("//li[@id=\"topcartlink\"]/a"));
    }
    public WebElement checkoutButton(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"checkout-buttons\"]/button"));
    }
    public WebElement agreeTerms(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"terms-of-service\"]/input"));
    }
    public WebElement billingFirstName(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_FirstName"));
    }
    public WebElement billingLastName(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_LastName"));
    }
    public WebElement billingEmail(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_Email"));
    }
    public WebElement billingCountry(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_CountryId"));
    }
    public WebElement billingCountryOption(WebDriver driver){
        return driver.findElement(By.xpath("//select[@id=\"BillingNewAddress_CountryId\"]/option[2]"));
    }
    public WebElement billingState(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_StateProvinceId"));
    }
    public WebElement billingStateOption(WebDriver driver){
        return driver.findElement(By.xpath("//select[@id=\"BillingNewAddress_StateProvinceId\"]/option[2]"));
    }
    public WebElement billingCity(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_City"));
    }
    public WebElement billingAddress(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_Address1"));
    }
    public WebElement billingPostalCode(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_ZipPostalCode"));
    }
    public WebElement billingPhoneNumber(WebDriver driver){
        return driver.findElement(By.id("BillingNewAddress_PhoneNumber"));
    }
    public WebElement continueButton(WebDriver driver){
        return driver.findElement(By.xpath("//div[@id=\"billing-buttons-container\"]/button[@name=\"save\"]"));
    }
    public WebElement shippingMethod(WebDriver driver){
        return driver.findElement(By.id("shippingoption_0"));
    }
    public WebElement continueButton2(WebDriver driver){
        return driver.findElement(By.xpath("//div[@id=\"shipping-method-buttons-container\"]/button"));
    }
    public WebElement paymentMethod(WebDriver driver){
        return driver.findElement(By.id("paymentmethod_0"));
    }
    public WebElement continueButton3(WebDriver driver){
        return driver.findElement(By.xpath("//div[@id=\"payment-method-buttons-container\"]/button"));
    }
    public WebElement paymentInfoContinue(WebDriver driver){
        return driver.findElement(By.xpath("//div[@id=\"checkout-step-payment-info\"]/div/button"));
    }
    public WebElement confirmButton(WebDriver driver){
        return driver.findElement(By.xpath("//div[@id=\"confirm-order-buttons-container\"]/button"));
    }
    public WebElement confirmMessage(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"title\"]/strong"));
    }
}
