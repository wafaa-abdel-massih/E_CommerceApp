package StepDefinition;

import ElementLocator.ShoppingLocator;
import ElementLocator.WishListLocator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import static StepDefinition.Hooks.driver;

public class WishlistSD {

    WishListLocator locatorWL = new WishListLocator();
    private final ShoppingLocator locatorS = new ShoppingLocator();

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
}
