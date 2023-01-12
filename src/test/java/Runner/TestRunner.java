package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src\\main\\resources\\features\\Register.feature",
        "src\\main\\resources\\features\\Login.feature"},
        glue = "StepDefinition"
)

public class TestRunner {
}
