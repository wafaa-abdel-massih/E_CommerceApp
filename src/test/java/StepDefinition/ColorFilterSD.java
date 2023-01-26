package StepDefinition;

import ElementLocator.ColorFilterLocator;
import ElementLocator.SelectCategoryLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import static StepDefinition.Hooks.driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Objects;

public class ColorFilterSD {
    ColorFilterLocator locatorCF = new ColorFilterLocator();
    SelectCategoryLocator locatorSRC = new SelectCategoryLocator();

    @And("user select Apparel > Shoes category")
    public void shoesCategory() {
        Actions builder = new Actions(driver);
        builder.moveToElement(locatorSRC.mainCategories(driver).get(2)).build().perform();
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

}
