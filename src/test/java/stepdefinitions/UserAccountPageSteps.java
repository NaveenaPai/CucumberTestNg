package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.LoginPage;
import pages.UserAccountPage;
import utilities.ConfigReader;

public class UserAccountPageSteps extends DriverFactory {
	private WebDriver driver;

	UserAccountPage accountPage;
	LoginPage loginPage;
	HomePage homePage;

	String userAccountPageTitle;

	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable userCredentialTable) {
		logger.info("user_has_already_logged_in_to_application ----- Start");
		driver = getDriver();
		logger.info("Driver sucessfully initialised");
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToSiteURL();
		logger.info("Sucessfully navigated to "+ConfigReader.getPropertyValue("url"));
		List<Map<String, String>> credentials = userCredentialTable.asMaps();
		String userName = credentials.get(0).get("username");
		String password = credentials.get(0).get("password");
		accountPage = loginPage.login(userName, password);
		logger.info("Sucessfully logged in using creadentiasl : "+userName+", "+password);
		logger.info("user_has_already_logged_in_to_application ----- End");
	}

	@Then("user gets the title of account page")
	public void user_gets_the_title_of_the_next_landing_page_i_e_useraccount_page() {
		logger.info("user_gets_the_title_of_the_next_landing_page_i_e_useraccount_page ----- Start");
		userAccountPageTitle = accountPage.getUserAccountPageTitle();
		logger.info("Fetched the title --> "+userAccountPageTitle);
		logger.info("user_gets_the_title_of_the_next_landing_page_i_e_useraccount_page ----- End");
	}

	@Then("account page title should be {string}")
	public void useraccount_page_title_should_be(String expectedTitle) {
		logger.info("useraccount_page_title_should_be ----- Start");
		Assert.assertEquals(userAccountPageTitle, expectedTitle);
		logger.info("Sucessfully validated page title to be "+ expectedTitle);
		logger.info("useraccount_page_title_should_be ----- End");
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
		logger.info("user_is_on_accounts_page ----- Start");
		Assert.assertNotNull(accountPage);
		logger.info("user_is_on_accounts_page ----- End");
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable accountOptionsTable) {
		logger.info("user_gets_accounts_section ----- Start");
		List<String> expectedoptions = accountOptionsTable.asList();
		List<String> optionsFromScreen = accountPage.getAccountsSectionsList();
		Assert.assertTrue(optionsFromScreen.containsAll(expectedoptions));
		logger.info("Sucessfully validated the options in account section "+expectedoptions);
		logger.info("user_gets_accounts_section ----- End");
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedOptionsCount) {
		logger.info("accounts_section_count_should_be ----- Start");
		Integer optionsCnt = accountPage.getAccountsOptionsCount();
		Assert.assertTrue(optionsCnt.equals(expectedOptionsCount));
		logger.info("Sucessfully validated the no. of options in account section to be "+optionsCnt);
		logger.info("accounts_section_count_should_be ----- End");
	}

	@Then("log out of the application")
	public void log_out_of_the_application() {
		logger.info("log_out_of_the_application() ----- Start");
		loginPage = accountPage.logout();
		logger.info("Successfully logged out");
		logger.info("log_out_of_the_application() ----- End");
	}
}
