package cucumberOptionTestng;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/features/AgreementDocumentAPI.feature",      // Path to your .feature files
    glue = {"stepDefinationCLMAPI"},                   // Package containing step definitions
    plugin = {
        "pretty",                                 // Console output
        "html:target/cucumber-reports.html",      // HTML report
        "json:target/cucumber.json"               // JSON report
    },
    		monochrome = true,
    		dryRun = true
    		//tags ="@run"
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

}
