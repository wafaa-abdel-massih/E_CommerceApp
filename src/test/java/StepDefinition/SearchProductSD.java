package StepDefinition;

import ElementLocator.SearchProductLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import static StepDefinition.Hooks.driver;

public class SearchProductSD {

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

}
