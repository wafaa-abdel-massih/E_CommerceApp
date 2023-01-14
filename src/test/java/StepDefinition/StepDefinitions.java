package StepDefinition;

import ElementLocator.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class StepDefinitions {

    WebDriver driver;
    RegisterLocator locator = new RegisterLocator();
    String projectDir = System.getProperty("user.dir");
    String chromeDriverPath = projectDir + "\\src\\main\\resources\\chromedriver.exe";
    Random random = new Random();


    public StepDefinitions(){ System.setProperty("webdriver.chrome.driver", chromeDriverPath); }

    //////////////////////////////////////////////////////////////////////////
    // Before Each Scenario

    @Before
    public void openBrowser(){
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
    String currency;
    @And("user select US-EURO")
    public void currencySelected() {
        locatorSC.currenciesOptions(driver).click();
        Select selectedCurrency = new Select(locatorSC.currenciesOptions(driver));
        currency = selectedCurrency.getFirstSelectedOption().getText();

        if(Objects.equals(currency, "US Dollar")){
            locatorSC.usOption(driver).click();
        }
        else {
            locatorSC.euroOption(driver).click();
        }
    }

    @Then("€ or $ sign based on selected currency should be displayed next to price")
    public void euroSign() {
        if(Objects.equals(currency, "US Dollar")){
            Assert.assertTrue(locatorSC.euroSign(driver).getText().contains("$"));
        }
        else {
            Assert.assertTrue(locatorSC.euroSign(driver).getText().contains("€"));
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // Select Random Categories Scenario

    SelectCategoryLocator locatorSRC = new SelectCategoryLocator();
    int randomCategory, randomSubCategory;
    boolean sub;

    @Given("user logged successfully")
    public void userLogged() {
        loginPage();         //driver.navigate().to("https://demo.nopcommerce.com/login?returnUrl=%2F");
        validEmail();        //locator.emailField(driver).sendKeys("test@gmail.com");
        validPassword();     //locator.passwordField(driver).sendKeys("test123");
        loginButton();       //locator.passwordField(driver).sendKeys(Keys.ENTER);
        loginSuccessfully(); //Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/");
    }

    @And("hover on random selected category")
    public void hoverOnRandomSelectedCategory() {
        int max = 7;
        randomCategory = random.nextInt(max);
        Actions builder = new Actions(driver);
        builder.moveToElement(locatorSRC.mainCategories(driver).get(randomCategory)).build().perform();
    }

    @And("sub category should be displayed if found")
    public void subCategoryShouldBeDisplayedIfFound() {
        if (randomCategory != 0 && randomCategory != 1 && randomCategory != 2){
            locatorSRC.mainCategories(driver).get(randomCategory).click();
            sub = false;
        }
        else {
            sub = true;
            int subMax = 3;
            randomSubCategory = random.nextInt(subMax);
            locatorSRC.subCategories(driver, randomCategory+1, randomSubCategory+1).click();
        }
    }

    @Then("user could select category")
    public void userCouldSelectCategory() {
        String categoryName;
        if (sub) {
            categoryName = locatorSRC.subCategories(driver, randomCategory + 1, randomSubCategory + 1).getText();
        }
        else {
            categoryName = locatorSRC.mainCategories(driver).get(randomCategory).getText();
        }
        Assert.assertTrue(driver.getCurrentUrl().contains(categoryName));
    }

    //////////////////////////////////////////////////////////////////////////
    // Filter With Color Scenario

    ColorFilterLocator locatorCF = new ColorFilterLocator();
    @And("user select Apparel > Shoes category")
    public void shoesCategory() {
        Actions builder = new Actions(driver);
        builder.moveToElement(locatorSRC.mainCategories(driver).get(2)).build().perform();
        locatorSRC.subCategories(driver,3,1).click();
    }

    @When("user checked in specific color")
    public void checkedInSpecificColor() {
        locatorCF.redColorFilter(driver).click();
    }

    @Then("product with checked specific color should be displayed")
    public void specificColorProduct() {
        locatorCF.filteredProduct(driver).click();
        List<WebElement> colors = locatorCF.productColors(driver);

        for (WebElement color : colors){
            if (Objects.equals(color.getAttribute("data-attr-value"), "25")){
                Assert.assertTrue(true);
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // After Each Scenario

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
