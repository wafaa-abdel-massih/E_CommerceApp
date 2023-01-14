package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ColorFilterLocator {
    public WebElement redColorFilter(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"filter-content\"]/ul/li[3]/input"));
    }

    public WebElement filteredProduct(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"picture\"]/a"));
    }
    public List<WebElement> productColors(WebDriver driver){
        return driver.findElements(By.xpath("//dd[@id=\"product_attribute_input_10\"]/ul/li"));
    }
}
