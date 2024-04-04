package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailIdField;

	@FindBy(id = "input-telephone")
	private WebElement telePhoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continuebutton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarnningEle;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarnning;

	@FindBy(xpath = "//*[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//*[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//*[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//*[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//*[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public AccountSuccessPage clickOnContinueBtn() {
		continuebutton.click();
		return new AccountSuccessPage(driver);
	}

	public String retrivePrivacyPolicyWarning() {
		return privacyPolicyWarnningEle.getText();
	}

	public String duplicateEmailAddressWarning() {
		return duplicateEmailAddressWarnning.getText();
	}

	public String retriveFirstnameWarning() {

		return firstNameWarning.getText();
	}

	public String retriveLastnameWarning() {

		return lastNameWarning.getText();
	}

	public String retriveEmailWarning() {

		return emailWarning.getText();
	}

	public String retriveTelephoneWarning() {
		return telephoneWarning.getText();
	}

	public String retrivePasswordWarning() {
		return passwordWarning.getText();
	}

	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastnameText, String emailIDText,
			String telephoneNum, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastnameText);
		emailIdField.sendKeys(emailIDText);
		telePhoneField.sendKeys(telephoneNum);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		privacyPolicyField.click();
		continuebutton.click();
		return new AccountSuccessPage(driver);
	}

	public AccountSuccessPage registerWithAllFields(String firstNameText, String lastnameText, String emailIDText,
			String telephoneNum, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastnameText);
		emailIdField.sendKeys(emailIDText);
		telePhoneField.sendKeys(telephoneNum);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		yesNewsLetterOption.click();
		privacyPolicyField.click();
		continuebutton.click();
		return new AccountSuccessPage(driver);
	}

	public AccountSuccessPage registerWithDuplicateEmail(String firstNameText, String lastnameText, String emailIDText,
			String telephoneNum, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastnameText);
		emailIdField.sendKeys(emailIDText);
		telePhoneField.sendKeys(telephoneNum);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		privacyPolicyField.click();
		continuebutton.click();
		return new AccountSuccessPage(driver);
	}

	public boolean displayStatusOfWarningMessage(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning,
			String expectedLastNameWarning, String expectedEmailWarning, String expectedTelephoneWarning,
			String expectedPasswordWarning) {
		boolean privacyPolicyWarningStatus = privacyPolicyWarnningEle.getText().contains(expectedPrivacyPolicyWarning);

		boolean firstnameWarningStatus = firstNameWarning.getText().equals(expectedFirstNameWarning);

		boolean lastnameWarningStatus = lastNameWarning.getText().equals(expectedLastNameWarning);

		boolean emailWarningStatus = emailWarning.getText().equals(expectedEmailWarning);

		boolean telephoneWarningStatus = telephoneWarning.getText().equals(expectedTelephoneWarning);

		boolean passwordWarningStatus = passwordWarning.getText().equals(expectedPasswordWarning);

		return privacyPolicyWarningStatus && firstnameWarningStatus && lastnameWarningStatus && emailWarningStatus
				&& telephoneWarningStatus && passwordWarningStatus;
	}
}
