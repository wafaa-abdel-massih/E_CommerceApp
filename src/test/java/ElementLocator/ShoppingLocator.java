package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingLocator {
    public WebElement product(WebDriver driver, int n) {
        return driver.findElement(By.xpath("//div[@class=\"item-grid\"]/div[" + n + "]/div[@class=\"product-item\"]/div[@class=\"picture\"]/a/img"));
    }
    public WebElement addToCartButton(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"add-to-cart-panel\"]/button"));
    }

    public WebElement successMessage(WebDriver driver){
        return driver.findElement(By.xpath("//p[@class=\"content\"]"));
    }

    public WebElement linkToBeAdded(WebDriver driver){
        return driver.findElement(By.xpath("//p[@class=\"content\"]/a"));
    }

}
