package StepDefinition;

import ElementLocator.SuccessOrderLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import static StepDefinition.Hooks.driver;

public class SuccessfulOrderSD {

    SuccessOrderLocator locatorSO = new SuccessOrderLocator();
    @And("user go to cart")
    public void userGoToCart() {
        locatorSO.shoppingCartButton(driver).click();

    }

    @And("user agree terms")
    public void userAgreeTerms() {
        locatorSO.agreeTerms(driver).click();
    }

    @And("click on checkout")
    public void clickOnCheckout() {
        locatorSO.checkoutButton(driver).click();
    }

    @And("user fill billing address required data")
    public void userFillBillingAddressRequiredData() throws InterruptedException {
        locatorSO.billingFirstName(driver).clear();
        locatorSO.billingFirstName(driver).sendKeys("wafaa");
        locatorSO.billingLastName(driver).clear();
        locatorSO.billingLastName(driver).sendKeys("habib");
        locatorSO.billingEmail(driver).clear();
        locatorSO.billingEmail(driver).sendKeys("test@gmail.com");
        locatorSO.billingCountry(driver).click();
        locatorSO.billingCountryOption(driver).click();
        Thread.sleep(1000);
        locatorSO.billingState(driver).click();
        locatorSO.billingStateOption(driver).click();
        locatorSO.billingCity(driver).sendKeys("city");
        locatorSO.billingAddress(driver).sendKeys("address1");
        locatorSO.billingPostalCode(driver).sendKeys("1222");
        locatorSO.billingPhoneNumber(driver).sendKeys("123456789");
        locatorSO.continueButton(driver).click();
    }

    @And("user choose shipping method")
    public void userChooseShippingMethod() throws InterruptedException {
        Thread.sleep(1000);
        locatorSO.shippingMethod(driver).click();
        locatorSO.continueButton2(driver).click();
    }

    @And("user choose payment method")
    public void userChoosePaymentMethod() throws InterruptedException {
        Thread.sleep(1000);
        locatorSO.paymentMethod(driver).click();
        locatorSO.continueButton3(driver).click();
    }

    @And("user continue button after check payment info")
    public void userContinueButtonAfterCheckPaymentInfo() throws InterruptedException {
        Thread.sleep(1000);
        locatorSO.paymentInfoContinue(driver).click();
    }

    @And("user confirm the order")
    public void userConfirmTheOrder() throws InterruptedException {
        Thread.sleep(1000);
        locatorSO.confirmButton(driver).click();
    }

    @Then("a confirm message should be displayed")
    public void aConfirmMessageShouldBeDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(locatorSO.confirmMessage(driver).getText().contains("Your order has been successfully processed!"));
    }
}
