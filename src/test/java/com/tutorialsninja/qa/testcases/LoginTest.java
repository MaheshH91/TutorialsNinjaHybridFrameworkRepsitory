package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.test.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
//	WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSuppler")
	public void verifyLoginWithValidCredentials(String email, String password) {
		accountPage = loginPage.login(email, password);

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit your account information is not displayed.");
	}

	@DataProvider(name = "validCredentialsSuppler")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestData("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("invalidPassword"));

		Assert.assertTrue(
				loginPage.getemailPasswordNotMatchingWarningMessage()
						.contains(dataprop.getProperty("emailPasswordNoMatchWarning")),
				"Expected Warning Message is not displayed.");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmainAndValidPassword() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));

		Assert.assertTrue(
				loginPage.getemailPasswordNotMatchingWarningMessage()
						.contains(dataprop.getProperty("emailPasswordNoMatchWarning")),
				"Expected Warning Message is not displayed.");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmainAndInvalidPassword() {
		loginPage.login(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));

		Assert.assertTrue(
				loginPage.getemailPasswordNotMatchingWarningMessage()
						.contains(dataprop.getProperty("emailPasswordNoMatchWarning")),
				"Expected Warning Message is not displayed.");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {
		loginPage.clickOnLoginButton();
		Assert.assertTrue(
				loginPage.getemailPasswordNotMatchingWarningMessage()
						.contains(dataprop.getProperty("emailPasswordNoMatchWarning")),
				"Expected Warning Message is not displayed.");
	}

}
