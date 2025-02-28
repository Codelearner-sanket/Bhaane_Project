package com.bhaane.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(id = "mdiv")
	WebElement mdivTag;

	@FindBy(xpath = "//li[@class='col hidden-xs']")
	WebElement account;

	@FindBy(name = "email")
	WebElement emailField;

	@FindBy(name = "password")
	WebElement passwordField;

	@FindBy(xpath = "//button[@class='btn btn-primary'][normalize-space()='submit']")
	WebElement submitButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	public void clickMdivTag() {
		
			// Wait until the element is visible
			wait.until(ExpectedConditions.visibilityOf(mdivTag));

			// Scroll into view in case it's hidden
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mdivTag);

			// Ensure element is clickable
			wait.until(ExpectedConditions.elementToBeClickable(mdivTag)).click();
			System.out.println("Popup closed successfully!");
	
		
	}

	public void account() {
		account.click();
	}

	public void login(String email, String password) {

		emailField.sendKeys("sanket123@gmail.com");
		passwordField.sendKeys("Pass@123");
		submitButton.click();
	}

}
