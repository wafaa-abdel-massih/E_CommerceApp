package StepDefinition;

import ElementLocator.SelectCategoryLocator;
import ElementLocator.TagsLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import static StepDefinition.Hooks.driver;

public class SelectTagsSD {

    TagsLocator locatorT = new TagsLocator();
    String tagName;
    SelectCategoryLocator locatorSRC = new SelectCategoryLocator();

    @And("user select any category")
    public void selectAnyCategory() {
        locatorSRC.mainCategories(driver).get(4).click();
    }

    @When("user select different tag")
    public void selectTag() {
        locatorT.listOfTags(driver, 2).click();
        tagName = locatorT.listOfTags(driver, 2).getText();
    }

    @Then("a text contains this specific tag should be displayed")
    public void textContainsSpecificTag() {
        Assert.assertTrue(locatorT.tagTitle(driver).getText().contains(tagName));
    }

    @And("user select different tag again")
    public void selectTagAgain() {
        locatorT.listOfTags(driver, 6).click();
        tagName = locatorT.listOfTags(driver, 6).getText();
    }

}
