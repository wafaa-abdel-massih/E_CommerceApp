package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TagsLocator {
    public WebElement listOfTags(WebDriver driver, int n){
        return driver.findElement(By.xpath("//div[@class=\"listbox\"]/div/ul/li["+n+"]/a"));
    }

    public WebElement tagTitle(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class=\"page-title\"]/h1"));
    }
}
