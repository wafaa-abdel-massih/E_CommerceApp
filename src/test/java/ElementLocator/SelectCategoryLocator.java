package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SelectCategoryLocator {
    public List<WebElement> mainCategories(WebDriver driver){
        return driver.findElements(By.xpath("//div[@class=\"header-menu\"]/ul[1]/li"));
    }

    public WebElement subCategories(WebDriver driver, int n, int m){
        return driver.findElement(By.xpath("//div[@class=\"header-menu\"]/ul[1]/li["+n+"]/ul/li["+m+"]/a"));
    }
}
