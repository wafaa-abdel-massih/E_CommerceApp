package StepDefinition;

import ElementLocator.CurrenciesLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import static StepDefinition.Hooks.driver;
import org.openqa.selenium.support.ui.Select;
import java.util.Objects;

public class SwitchCurrenciesSD {

    CurrenciesLocator locatorSC = new CurrenciesLocator();
    String currency;
    @And("user select US-EURO")
    public void currencySelected() {
        locatorSC.currenciesOptions(driver).click();
        Select selectedCurrency = new Select(locatorSC.currenciesOptions(driver));
        currency = selectedCurrency.getFirstSelectedOption().getText();

        if(Objects.equals(currency, "US Dollar")){
            locatorSC.euroOption(driver).click();
        }
        else {
            locatorSC.usOption(driver).click();
        }
    }

    @Then("€ or $ sign based on selected currency should be displayed next to price")
    public void euroSign() {
        if(Objects.equals(currency, "US Dollar")){
            Assert.assertTrue(locatorSC.sign(driver).getText().contains("€"));
        }
        else {
            Assert.assertTrue(locatorSC.sign(driver).getText().contains("$"));
        }
    }

}
