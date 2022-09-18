package hooks;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utilities.DocToHTMLConvertUtility;

public class CucumberApplicationHook {

	private DriverFactory driverFactory;
	private WebDriver driver;

	@Before
	public void InitializeDriver() {
		driverFactory = new DriverFactory();
		driver = driverFactory.initialize_driver();
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@Before
	public void TakeScreenshotBeforeScenario(Scenario scenario) {
		TakeScreenShot(scenario);
	}

	@After
	public void TakeScreenshotAfterScenario(Scenario scenario) {
		TakeScreenShot(scenario);
	}
	
	private void TakeScreenShot(Scenario scenario) {
		String screenshotName = "SuccessScreenshot_for_"+scenario.getName();
		if (scenario.isFailed()) {
			screenshotName = "FailedScreenshot_for_"+scenario.getName();
		}
		scenario.getName().replaceAll(" ", "_");
		
		byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(sourcePath, "image/png", screenshotName);
		Allure.addAttachment(screenshotName, new ByteArrayInputStream(sourcePath));
	}
	
	//@After
	public void AttachResultToReport(Scenario scenario)
	{
		 String filePath = System.getProperty("user.dir")+ "/target/Screenshots/";
		 String fileName="ScreenShot_2.docx";
		 String name  = fileName.substring(0, fileName.lastIndexOf("."));
		 try {
			DocToHTMLConvertUtility.docx(filePath ,fileName ,name +".html");
			scenario.attach(filePath+name +".html", "text/html;charset=utf-8", name +".html");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
