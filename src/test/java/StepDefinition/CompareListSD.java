package StepDefinition;

import ElementLocator.CompareListLocator;
import ElementLocator.ShoppingLocator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static StepDefinition.Hooks.driver;

public class CompareListSD {

    CompareListLocator locatorCL = new CompareListLocator();
    ShoppingLocator locatorS = new ShoppingLocator();

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
}
