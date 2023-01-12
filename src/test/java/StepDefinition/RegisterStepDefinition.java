package StepDefinition;

import ElementLocator.RegisterLocator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterStepDefinition {

    WebDriver driver;
    RegisterLocator register;

    public RegisterStepDefinition(){
        driver= new ChromeDriver();
        String projectDir = System.getProperty("user.dir");
        String chromeDriverPath = projectDir + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @Before
    public void loadWebsite(){
        driver.navigate().to("https://demo.nopcommerce.com/");
    }

    @Given("user navigates to register page")
    public void registerPage(){
        driver.navigate().to("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @When("user enter valid data")
    public void validData(){
        register = new RegisterLocator();
        register.firstNameField(driver).sendKeys("wafaa");
        register.lastNameField(driver).sendKeys("habib");
        register.emailField(driver).sendKeys("testing@gmail.com");
        register.passwordField(driver).sendKeys("test123");
        register.confirmPasswordField(driver).sendKeys("test123");
    }

    @And("user click on register button")
    public void registerButton(){
        register.confirmPasswordField(driver).sendKeys(Keys.ENTER);
    }

    @Then("user could register successfully")
    public void registered(){
        Assert.assertTrue(register.registerMessage(driver).getText().contains("Your registration completed"));
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}
