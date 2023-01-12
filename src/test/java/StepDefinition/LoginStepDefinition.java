package StepDefinition;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepDefinition {

    WebDriver driver;

    @Given("^user open the browser \"(.*)\" and \"(.*)\"$")
    public void user_open_the_browser(String s, String d) {
        String projectDir = System.getProperty("user.dir"); //equal to C:\Users\wafaa\Desktop\SeleniumPractice
        String chromeDriverPath = projectDir + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

    }

    @And("user navigates to login page")
    public void user_navigates_to_login_page() {
        driver = new ChromeDriver();
        driver.navigate().to("https://the-internet.herokuapp.com/login");
    }

    @When("user enter valid username")
    public void user_enter_valid_username() {

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);

        String expectedResult = "You logged into a secure area!";
        String actualResult= driver.findElement(By.id("flash")).getText();

        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Then("user could login successfully")
    public void user_could_login_successfully() {
        Assert.assertEquals("https://the-internet.herokuapp.com/secure", driver.getCurrentUrl());
        driver.quit();
    }
}
