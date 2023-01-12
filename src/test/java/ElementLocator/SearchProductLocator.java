package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchProductLocator {

    public WebElement searchBox(WebDriver driver){

        return driver.findElement(By.id("small-searchterms"));
    }

    public WebElement productTitle(WebDriver driver){

        return driver.findElement(By.className("product-title"));
    }
}
