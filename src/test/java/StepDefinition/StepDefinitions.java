package StepDefinition;

import ElementLocator.CurrenciesLocator;
import ElementLocator.RegisterLocator;
import ElementLocator.ResetPasswordLocator;
import ElementLocator.SearchProductLocator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefinitions {

    WebDriver driver;
    RegisterLocator locator = new RegisterLocator();;
    String projectDir = System.getProperty("user.dir");
    String chromeDriverPath = projectDir + "\\src\\main\\resources\\chromedriver.exe";

    @Before
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver= new ChromeDriver();
    }

    //////////////////////////////////////////////////////////////////////////
    // Register Scenario

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


    //////////////////////////////////////////////////////////////////////////
    // Login Scenario

    @Given("user navigates to login page")
    public void loginPage() {
        driver.navigate().to("https://demo.nopcommerce.com/login?returnUrl=%2F");
    }

    @When("user enter valid email")
    public void validEmail() { locator.emailField(driver).sendKeys("test@gmail.com"); }

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

    //////////////////////////////////////////////////////////////////////////
    // Reset Password Scenario

    ResetPasswordLocator locatorRP = new ResetPasswordLocator();

    @When("user click on forget password link")
    public void forgetPasswordLink(){
        locatorRP.forgetPasswordLink(driver).click();
    }

    @And("user click on recover button")
    public void recoverButton(){
        locatorRP.recoverButton(driver).click();
    }

    @Then("user receive an email to reset password successfully")
    public void resetSuccessfully(){
        String expectedMessage = "Email with instructions has been sent to you.";
        Assert.assertTrue(locatorRP.resetMessage(driver).getText().contains(expectedMessage));
    }


    //////////////////////////////////////////////////////////////////////////
    // Search Product Scenario

    SearchProductLocator locatorSP = new SearchProductLocator();
    @And("user enter product name in search box")
    public void searchProduct() { locatorSP.searchBox(driver).sendKeys("Bracelet"); }

    @And("user click on search button")
    public void searchButton() throws InterruptedException {
        locatorSP.searchBox(driver).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    @Then("searched product should be displayed")
    public void searchedProduct() {
        Assert.assertTrue(locatorSP.productTitle(driver).getText().contains("Bracelet"));
    }


    //////////////////////////////////////////////////////////////////////////
    // Switch Currencies Scenario

    CurrenciesLocator locatorSC = new CurrenciesLocator();
    @And("user selected US-EURO")
    public void euroSelected() {
        locatorSC.currenciesOptions(driver).click();
        locatorSC.euroOption(driver).click();
    }

    @Then("€ sign should be displayed next to price")
    public void euroSign() {
        Assert.assertTrue(locatorSC.euroSign(driver).getText().contains("€"));
    }

    //////////////////////////////////////////////////////////////////////////

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
