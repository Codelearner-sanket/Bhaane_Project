package com.bhaane.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//img[@class='ico trigger-search']")
	private WebElement searchIcon;

	@FindBy(name = "q") // Replace with actual locator for the search input field
	private WebElement searchInput;

	@FindBy(xpath = "//button[@type='submit']") // Replace with actual locator for the search button
	private WebElement searchButton;

	@FindBy(xpath = "//p[@class='mt-md']") // Updated XPath for search results
	private WebElement searchResults;

	// Constructor
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
		PageFactory.initElements(driver, this);
	}

	// Method to enter search keyword
	public void enterSearchKeyword(String keyword) {
		wait.until(ExpectedConditions.visibilityOf(searchInput)); // Wait until input is visible
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchInput);

		searchInput.clear();
		searchInput.sendKeys(keyword);
	}

	// Method to click the search button
	public void clickSearchButton() {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)); // Wait for the search button to be clickable
		searchButton.click();
	}

	public void performSearch(String keyword) {
		try {
			// Click on the search icon to reveal the search box
			wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();

			// Wait for the search input to appear
			wait.until(ExpectedConditions.visibilityOf(searchInput));

			// Enter search keyword
			searchInput.clear();
			searchInput.sendKeys(keyword);
			searchInput.sendKeys(Keys.ENTER); // Press Enter
			// Wait for search results to appear
			wait.until(ExpectedConditions.visibilityOf(searchResults));

		} catch (Exception e) {
			System.out.println("⚠️ Error performing search: " + e.getMessage());
		}
	}

	// Method to get search results text
	public String getSearchResultsText() {
		try {
			return wait.until(ExpectedConditions.visibilityOf(searchResults)).getText();
		} catch (Exception e) {
			System.out.println("⚠️ Error fetching search results: " + e.getMessage());
			return "";
		}
	}
}
