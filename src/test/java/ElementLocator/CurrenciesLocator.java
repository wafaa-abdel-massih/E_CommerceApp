package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CurrenciesLocator {
    public WebElement currenciesOptions(WebDriver driver){ return driver.findElement(By.id("customerCurrency")); }
    public WebElement euroOption(WebDriver driver){ return driver.findElement(By.xpath("//select[@id=\"customerCurrency\"]/option[2]")); }
    public WebElement euroSign(WebDriver driver){ return driver.findElement(By.className("price")); }
    public WebElement usOption(WebDriver driver){ return driver.findElement(By.xpath("//select[@id=\"customerCurrency\"]/option[1]")); }
    public WebElement usSign(WebDriver driver){ return driver.findElement(By.className("price")); }
}
