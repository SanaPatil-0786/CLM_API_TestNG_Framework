package cucumberOptionTestng;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
        features = "src/test/java/features", // Path to your .feature files
		glue = {"stepDefinationCLMAPI"}, // Package containing step definitions

		plugin = { 
				"pretty", 
				"html:target/cucumber-reports.html",
				"json:target/cucumber.json"
				},
        tags ="@run",
        dryRun= false
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

}




 

 










