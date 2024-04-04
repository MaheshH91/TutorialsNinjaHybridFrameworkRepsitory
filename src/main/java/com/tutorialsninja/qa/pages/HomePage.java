package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(xpath = "//span[.='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchBoxField;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButtonElement;

	@FindBy(linkText = "HP LP3065")
	private WebElement productLinkElement;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	public void clickOnMyAccount() {
//		myAccountDropMenu.click();
//	}

	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
//	public LoginPage clickOnloginOption() {
//		loginOption.click();
//		return new LoginPage(driver);
//	}

	public RegisterPage clickOnRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	public RegisterPage navigateToRegistrationPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
		
		
	}

	public void enterProductNameIntoSearchBoxField(String productName) {
		searchBoxField.sendKeys(productName);
	}

	public SearchPage clickOnSearchButton() {
		searchButtonElement.click();
		return new SearchPage(driver);
	}
	
	public SearchPage searchForAproduct(String productText) {
		searchBoxField.sendKeys(productText);
		searchButtonElement.click();
		return new SearchPage(driver);
	}
}
