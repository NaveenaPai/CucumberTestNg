package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import factory.DriverFactory;

public class LoginPage extends DriverFactory {

	private WebDriver driver;

	 @FindBy(id = "email")
	WebElement emailId;
	@FindBy(id = "passwd")
	WebElement password;
	public static @FindBy(id = "SubmitLogin")
	WebElement signInButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

		public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public void enterUserName(String username) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailId);
		emailId.sendKeys(username);
	}

	public void enterPassword(String pwd) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", password);
		password.sendKeys(pwd);
	}

	public UserAccountPage clickOnLogin() {
		signInButton.click();
		return new UserAccountPage(driver);
	}

	public UserAccountPage login(String username, String passwrd) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailId);
		emailId.sendKeys(username);
		password.sendKeys(passwrd);
		signInButton.click();
		return new UserAccountPage(driver);
	}
}
