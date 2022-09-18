package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ConfigReader;

public class HomePage {

	private WebDriver driver;

	@FindBy(className = "login")
	WebElement signInLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage navigateToSiteURL() {

		String siteURL = ConfigReader.getPropertyValue("url");
		driver.get(siteURL);
		signInLink.click();
		return new LoginPage(driver);
	}
}
