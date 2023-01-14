package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishListLocator {
    public WebElement addToWishlistButton(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"add-to-wishlist\"]/button"));
    }
}
