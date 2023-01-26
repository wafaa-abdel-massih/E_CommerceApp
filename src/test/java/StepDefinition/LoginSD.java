package StepDefinition;

import ElementLocator.RegisterLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import static StepDefinition.Hooks.driver;

public class LoginSD {

    RegisterLocator locator = new RegisterLocator();

    @Given("user navigates to login page")
    public void loginPage() {
        driver.navigate().to("https://demo.nopcommerce.com/login?returnUrl=%2F");
    }

    @When("user enter valid email")
    public void validEmail() { locator.emailField(driver).sendKeys("testing@gmail.com"); }

    @And("user enter valid password")
    public void validPassword(){
        locator.passwordField(driver).sendKeys("test123");
    }

    @And("user click on login button")
    public void loginButton(){
        locator.passwordField(driver).sendKeys(Keys.ENTER);
    }

    @Then("user could login successfully")
    public void loginSuccessfully() {
        Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/");
    }

}
