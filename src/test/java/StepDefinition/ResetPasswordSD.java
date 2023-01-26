package StepDefinition;

import ElementLocator.ResetPasswordLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import static StepDefinition.Hooks.driver;


public class ResetPasswordSD {

    ResetPasswordLocator locatorRP = new ResetPasswordLocator();

    @When("user click on forget password link")
    public void forgetPasswordLink(){
        locatorRP.forgetPasswordLink(driver).click();
    }

    @And("user click on recover button")
    public void recoverButton(){
        locatorRP.recoverButton(driver).click();
    }

    @Then("user receive an email to reset password successfully")
    public void resetSuccessfully(){
        String expectedMessage = "Email with instructions has been sent to you.";
        Assert.assertTrue(locatorRP.resetMessage(driver).getText().contains(expectedMessage));
    }
}
