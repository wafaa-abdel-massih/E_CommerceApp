package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    static WebDriver driver;
    String projectDir = System.getProperty("user.dir");
    String chromeDriverPath = projectDir + "\\src\\main\\resources\\chromedriver.exe";

    //////////////////////////////////////////////////////////////////////////
    // Before Each Scenario

    @Before
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver= new ChromeDriver();
        driver.manage().window().maximize();
    }

    //////////////////////////////////////////////////////////////////////////
    // After Each Scenario

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    //////////////////////////////////////////////////////////////////////////
    // Get Driver

    public WebDriver getDriver(){
        return driver;
    }
}
