package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmailAdd(String email) {
		emailField.sendKeys(email);
	}

	public void enterPass(String password) {
		passwordField.sendKeys(password);
	}

	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}

	public AccountPage login(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}

	public String getemailPasswordNotMatchingWarningMessage() {
		return emailPasswordNotMatchingWarning.getText();
	}

}
