package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import pages.UserAccountPage;
import utilities.ConfigReader;

public class LoginPageSteps extends DriverFactory {

	private WebDriver driver;
	private LoginPage loginPage;
	private UserAccountPage userAcccountPage;

	private String loginTitle;
	private String userAccountPageTitle;

	@Given("user is on the login page")
	public void user_is_on_the_login_page() {
		logger.info("user_is_on_the_login_page() ----- Start");
		driver = getDriver();
		logger.info("Driver sucessfully initialised");
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToSiteURL();
		logger.info("Sucessfully navigated to "+ConfigReader.getPropertyValue("url"));
		logger.info("user_is_on_the_login_page() ----- End");
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		loginTitle = loginPage.getLoginPageTitle();
		logger.info("Fetched the login page tile --> "+loginTitle);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String titleExpected) {
		
		Assert.assertTrue(loginTitle.equals(titleExpected));
		logger.info("Validated the title to be "+ titleExpected);
	}

	@When("user enters username {string}")
	public void user_enters_username(String username) {
		
		loginPage.enterUserName(username);
		logger.info("Entered user id :  "+username);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) {
		loginPage.enterPassword(password);
		logger.info("Entered password :  "+password);
	}

	@When("user clicks on Login button")
	public void user_clicks_on_login_button() {
		
		userAcccountPage = loginPage.clickOnLogin();
		logger.info("Successfully Logged in ");
	}

	@Then("user gets the title of useraccount page")
	public void user_gets_the_title_of_the_next_landing_page_i_e_useraccount_page() {
		userAccountPageTitle = userAcccountPage.getUserAccountPageTitle();
		logger.info("Fetched the account page tile --> "+userAccountPageTitle);
	}

	@Then("useraccount page title should be {string}")
	public void useraccount_page_title_should_be(String expectedTitle) {
		Assert.assertTrue(userAccountPageTitle.equals(expectedTitle));
		logger.info("Verified the account page tile to be --> "+expectedTitle);
	}

		
}
