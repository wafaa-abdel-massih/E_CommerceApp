package StepDefinition;

import ElementLocator.RegisterLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import static StepDefinition.Hooks.driver;


public class RegisterSD {

    RegisterLocator locator = new RegisterLocator();

    @Given("user navigates to register page")
    public void registerPage(){
        driver.navigate().to("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @When("user fill the required fields with valid data")
    public void validData(){
        locator.firstNameField(driver).sendKeys("wafaa");
        locator.lastNameField(driver).sendKeys("habib");
        locator.emailField(driver).sendKeys("testing@gmail.com");
        locator.passwordField(driver).sendKeys("test123");
        locator.confirmPasswordField(driver).sendKeys("test123");
    }

    @And("user click on register button")
    public void registerButton(){
        locator.confirmPasswordField(driver).sendKeys(Keys.ENTER);
    }

    @Then("user could register successfully")
    public void registered(){
        Assert.assertTrue(locator.registerMessage(driver).getText().contains("Your registration completed"));
    }

}
