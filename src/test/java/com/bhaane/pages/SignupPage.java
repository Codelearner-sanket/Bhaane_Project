package com.bhaane.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

	WebDriver driver;


	@FindBy(xpath = "//li[@class='col hidden-xs']")
	WebElement account;

	@FindBy(xpath = "//div[@data-modal='#modal-register']")
	WebElement signupButton;

	@FindBy(xpath = "//input[@name='first_name']")
	WebElement inputFirstName;

	@FindBy(xpath = "//input[@name='last_name']")
	WebElement inputLastName;

	@FindBy(xpath = "//div[@class='col-12']//input[@name='email']")
	WebElement inputEmail;

	@FindBy(xpath = "//input[@name='mobile']")
	WebElement inputMobile;

	@FindBy(xpath = "//div[@class='form-group']//input[@name='password']")
	WebElement inputPassword;
	
	@FindBy(xpath="//button[@class='btn btn-primary c-center']")
	WebElement submitButton;

// Constructor
	public SignupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void signup(String firstName, String lastName, String email, String mobile, String password) {
		account.click();	
		signupButton.click();
		inputFirstName.sendKeys(firstName);
		inputLastName.sendKeys(lastName);
		inputEmail.sendKeys(email);
		inputMobile.sendKeys(mobile);
		inputPassword.sendKeys(password);
		submitButton.click();
	}

}
