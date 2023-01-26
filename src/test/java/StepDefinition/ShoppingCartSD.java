package StepDefinition;

import ElementLocator.SelectCategoryLocator;
import ElementLocator.ShoppingLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import static StepDefinition.Hooks.driver;
import org.openqa.selenium.interactions.Actions;

public class ShoppingCartSD {

    ShoppingLocator locatorS = new ShoppingLocator();
    private SelectCategoryLocator locatorSRC;

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

}
