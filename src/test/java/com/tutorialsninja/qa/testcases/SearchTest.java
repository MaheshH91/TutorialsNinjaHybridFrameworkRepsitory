package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import com.tutorialsninja.qa.test.base.Base;

public class SearchTest extends Base {
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifySearchWithValidProduct() {
		searchPage = homePage.searchForAproduct(dataprop.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHpProduct());
	}

	@Test(priority = 2)
	public void VerifySearchWithInValidProduct() {
		searchPage = homePage.searchForAproduct(dataprop.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retriveNoProductMessage(), dataprop.getProperty("noProductMessage"),
				"No product message in search results is not displayed");
		/*
		 * Assert.assertEquals(searchPage.retriveNoProductMessage(),
		 * dataprop.getProperty("noProductMessage"),
		 * "No product message in search results is not displayed");
		 */
	}

	@Test(priority = 3, dependsOnMethods = "VerifySearchWithInValidProduct")
	public void VerifySearchWithoutAnyProduct() {

		searchPage = homePage.clickOnSearchButton();

		Assert.assertEquals(searchPage.retriveNoProductMessage(), dataprop.getProperty("noProductMessage"),
				"No product message in search results is not displayed");
	}
}
