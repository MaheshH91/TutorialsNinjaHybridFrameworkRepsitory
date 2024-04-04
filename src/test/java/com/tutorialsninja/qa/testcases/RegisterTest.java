package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.test.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegistrationPage();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifyRegisteringAnAccountWithMandatoryfields() {
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataprop.getProperty("firstName"),
				dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataprop.getProperty("telephone"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessHeading(),
				dataprop.getProperty("accountSuccessfullyCreatedMsg"));
	}

	@Test(priority = 2)
	public void VerifyRegisteringAnAccountByProvidingAllFields() {
		accountSuccessPage = registerPage.registerWithAllFields(dataprop.getProperty("firstName"),
				dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataprop.getProperty("telephone"), prop.getProperty("validPassword"));

		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessHeading(),
				dataprop.getProperty("accountSuccessfullyCreatedMsg"));
	}

	@Test(priority = 3)
	public void VerifyRegisteringAccountWithExistingEmailId() {
		accountSuccessPage = registerPage.registerWithAllFields(dataprop.getProperty("firstName"),
				dataprop.getProperty("lastName"), prop.getProperty("validEmail"), dataprop.getProperty("telephone"),
				prop.getProperty("validPassword"));

		Assert.assertTrue(
				registerPage.duplicateEmailAddressWarning().contains(dataprop.getProperty("duplicateEmailWarningMsg")),
				"Warning Message regarding duplicatge email is not displayed.");
	}

	@Test(priority = 4)
	public void VerifyRegisteringAccountWithoutFillingAnyDetails() {
		registerPage.clickOnContinueBtn();
		Assert.assertTrue(registerPage.displayStatusOfWarningMessage(dataprop.getProperty("privacyPolicyWarningMsg"),
				dataprop.getProperty("firstNameWarningMsg"), dataprop.getProperty("lastNameWarningMsg"),
				dataprop.getProperty("emailAddressWarningMsg"), dataprop.getProperty("telephoneWarningMsg"),
				dataprop.getProperty("passwordWarningMsg")), "Warning Message(s) is/are not displayed.");
	}
}
