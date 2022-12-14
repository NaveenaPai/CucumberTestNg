package testrunners;


import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

	
@CucumberOptions(
		features = {"src/test/resources/features/"},
		glue = {"stepdefinitions", "hooks"},
		plugin = { "pretty",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"  }, monochrome = true
		//plugin = {"pretty",
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				//"timeline:test-output-thread/","rerun:target/rerun.txt"
				//}
		
		)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}

