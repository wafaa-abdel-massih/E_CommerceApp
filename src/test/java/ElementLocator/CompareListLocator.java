package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CompareListLocator {
    public WebElement addToCompareListButton(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"compare-products\"]/button"));
    }
}
