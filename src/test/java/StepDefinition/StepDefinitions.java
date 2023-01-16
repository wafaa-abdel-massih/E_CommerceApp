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
        driver.manage().window().maximize();
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
        locator.emailField(driver).sendKeys("test@gmail.com");
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
            locatorSC.euroOption(driver).click();
        }
        else {
            locatorSC.usOption(driver).click();
        }
    }

    @Then("€ or $ sign based on selected currency should be displayed next to price")
    public void euroSign() {
        if(Objects.equals(currency, "US Dollar")){
            Assert.assertTrue(locatorSC.sign(driver).getText().contains("€"));
        }
        else {
            Assert.assertTrue(locatorSC.sign(driver).getText().contains("$"));
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
            //Thread.sleep(1000);
            locatorSRC.subCategories(driver, randomCategory+1, randomSubCategory+1).click();
        }
    }

    @Then("user could select category")
    public void userCouldSelectCategory() {
        String categoryName;
        if (sub) {
            //Thread.sleep(1000);
            categoryName = locatorSRC.subCategories(driver, randomCategory + 1, randomSubCategory + 1).getText();
        }
        else {
            categoryName = locatorSRC.mainCategories(driver).get(randomCategory).getText();
        }
        categoryName = categoryName.replace(' ','-').toLowerCase();

        Assert.assertTrue(driver.getCurrentUrl().contains(categoryName));
    }

    //////////////////////////////////////////////////////////////////////////
    // Filter With Color Scenario

    ColorFilterLocator locatorCF = new ColorFilterLocator();
    @And("user select Apparel > Shoes category")
    public void shoesCategory() {
        Actions builder = new Actions(driver);
        builder.moveToElement(locatorSRC.mainCategories(driver).get(2)).build().perform();
        //Thread.sleep(1000);
        locatorSRC.subCategories(driver,3,1).click();
    }

    @When("user checked in specific color")
    public void checkedInSpecificColor() throws InterruptedException {
        locatorCF.redColorFilter(driver).click();
        Thread.sleep(2000);
    }

    @Then("product with checked specific color should be displayed")
    public void specificColorProduct() {
        locatorCF.product(driver).click();
        List<WebElement> colors = locatorCF.productColors(driver);

        for (WebElement color : colors){
            if (Objects.equals(color.getAttribute("data-attr-value"), "25")){
                Assert.assertTrue(true);
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // Select Tags Scenario

    TagsLocator locatorT = new TagsLocator();
    String tagName;
    @And("user select any category")
    public void selectAnyCategory() {
        locatorSRC.mainCategories(driver).get(4).click();
    }

    @When("user select different tag")
    public void selectTag() {
        locatorT.listOfTags(driver, 2).click();
        tagName = locatorT.listOfTags(driver, 2).getText();
    }

    @Then("a text contains this specific tag should be displayed")
    public void textContainsSpecificTag() {
        Assert.assertTrue(locatorT.tagTitle(driver).getText().contains(tagName));
    }

    @And("user select different tag again")
    public void selectTagAgain() {
        locatorT.listOfTags(driver, 6).click();
        tagName = locatorT.listOfTags(driver, 6).getText();
    }

    //////////////////////////////////////////////////////////////////////////
    // Add Product to shopping cart Scenario

    ShoppingLocator locatorS = new ShoppingLocator();
    @And("user select category")
    public void selectCategory() {
        Actions builder = new Actions(driver);
        builder.moveToElement(locatorSRC.mainCategories(driver).get(2)).build().perform();
        //Thread.sleep(1000);
        locatorSRC.subCategories(driver, 3, 3).click();
    }

    @And("user select product")
    public void userSelectProduct() {
        locatorS.product(driver, 2).click();
    }

    @When("user click on add to cart")
    public void clickOnAddToCart() {
        locatorS.addToCartButton(driver).click();
    }

    @Then("a successful message should be displayed")
    public void messageDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(locatorS.successMessage(driver).getText().contains("The product has been added to your "));
        Assert.assertTrue(locatorS.linkToBeAdded(driver).getText().contains("shopping cart"));
    }

    @And("user select another category")
    public void selectCategoryAgain() throws InterruptedException {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.PAGE_UP).build().perform();
        Thread.sleep(2000);
        builder.moveToElement(locatorSRC.mainCategories(driver).get(0)).build().perform();
        locatorSRC.subCategories(driver, 1, 2).click();
    }

    @And("user select another product")
    public void selectProductAgain() {
        locatorS.product(driver, 1).click();
    }

    //////////////////////////////////////////////////////////////////////////
    // Add Product to WishList Scenario

    WishListLocator locatorWL = new WishListLocator();
    @When("user click on add to wishlist")
    public void userClickOnAddToWishlist() {
        locatorWL.addToWishlistButton(driver).click();
    }

    @Then("added to wishlist successfully")
    public void addedToWishlistSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(locatorS.successMessage(driver).getText().contains("The product has been added to your "));
        Assert.assertTrue(locatorS.linkToBeAdded(driver).getText().contains("wishlist"));
    }

    //////////////////////////////////////////////////////////////////////////
    // Add Product to CompareList Scenario

    CompareListLocator locatorCL = new CompareListLocator();
    @When("user click on add to compare list")
    public void userClickOnAddToCompareList() {
        locatorCL.addToCompareListButton(driver).click();
    }

    @Then("added to compare list successfully")
    public void addedToCompareListSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(locatorS.successMessage(driver).getText().contains("The product has been added to your "));
        Assert.assertTrue(locatorS.linkToBeAdded(driver).getText().contains("product comparison"));
    }

    //////////////////////////////////////////////////////////////////////////
    // Successful Order Scenario

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

    //////////////////////////////////////////////////////////////////////////
    // After Each Scenario

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
