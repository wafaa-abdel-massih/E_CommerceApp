package StepDefinition;

import ElementLocator.SelectCategoryLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import static StepDefinition.Hooks.driver;
import java.util.Random;

public class SelectCategoriesSD {

    Random random = new Random();
    LoginSD login = new LoginSD();

    SelectCategoryLocator locatorSRC = new SelectCategoryLocator();
    int randomCategory, randomSubCategory;
    boolean sub;

    @Given("user logged successfully")
    public void userLogged() {
        login.loginPage();         //driver.navigate().to("https://demo.nopcommerce.com/login?returnUrl=%2F");
        login.validEmail();        //locator.emailField(driver).sendKeys("testing@gmail.com");
        login.validPassword();     //locator.passwordField(driver).sendKeys("test123");
        login.loginButton();       //locator.passwordField(driver).sendKeys(Keys.ENTER);
        login.loginSuccessfully(); //Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/");
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
}
