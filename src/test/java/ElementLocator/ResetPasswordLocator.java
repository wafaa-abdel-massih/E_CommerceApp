package ElementLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResetPasswordLocator {

    public WebElement forgetPasswordLink(WebDriver driver){
        return driver.findElement(By.cssSelector("div.inputs.reversed>span>a"));
    }

    public WebElement resetMessage(WebDriver driver){
        return driver.findElement(By.className("content"));
    }

    public WebElement recoverButton(WebDriver driver){
        return driver.findElement(By.name("send-email"));
    }
}
