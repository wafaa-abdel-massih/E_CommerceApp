package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterLocator {
    public WebElement firstNameField(WebDriver driver){ return driver.findElement(By.id("FirstName")); }
    public WebElement lastNameField(WebDriver driver){ return driver.findElement(By.id("LastName")); }
    public WebElement emailField(WebDriver driver){ return driver.findElement(By.id("Email")); }
    public WebElement passwordField(WebDriver driver){ return driver.findElement(By.id("Password")); }
    public WebElement confirmPasswordField(WebDriver driver){ return driver.findElement(By.id("ConfirmPassword")); }
    public WebElement registerMessage(WebDriver driver){ return driver.findElement(By.className("result")); }
}
