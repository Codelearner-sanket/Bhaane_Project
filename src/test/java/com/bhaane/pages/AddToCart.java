package com.bhaane.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCart {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    @FindBy(id = "mdiv")  // Modify based on actual popup locator
    private WebElement popupClose;

    @FindBy(css = ".selection")  // Dropdown element before login
    private WebElement dropdownWrapper;

    @FindBy(xpath = "//li[@class='col hidden-xs']")
    private WebElement accountIcon;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@class='btn btn-primary'][normalize-space()='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class=\"hidden-xs col-md-5\"]/ul/li[5]/a")
    private WebElement famsLink;

    private final String PRODUCT_XPATH = "(//div[@class='img-box img-switch'])[2]";

    @FindBy(xpath = "//span[@class='bold add-to-bag'and text()='add to bag']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='img-box']")
    private WebElement cartConfirmation;

    // Constructor
    public AddToCart(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Increased timeout
        PageFactory.initElements(driver, this);
    }

    // Close popup if present
    public void closePopup() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(popupClose)).click();
            System.out.println("Popup closed successfully.");
        } catch (Exception e) {
            System.out.println("No popup displayed.");
        }
    }

    // Click on the dropdown wrapper before login
 // Click on the dropdown and select the first option
    public void selectFirstDropdownOption() {
        try {
            // Click on the dropdown wrapper
            wait.until(ExpectedConditions.elementToBeClickable(dropdownWrapper)).click();
            System.out.println("✅ Clicked on the dropdown wrapper.");

            // Wait for the dropdown search box to appear
            By dropdownSearch = By.xpath("//span[@class='select2-search select2-search--dropdown']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownSearch));
            System.out.println("✅ Dropdown appeared.");

            // Wait for the first item in the dropdown and click it
            By firstDropdownOption = By.xpath("//span[@class='select2-results']/ul/li[1]");
            WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(firstDropdownOption));
            firstOption.click();
            System.out.println("✅ Selected the first option from the dropdown.");
        } catch (Exception e) {
            System.out.println("❌ Failed to select the first dropdown option: " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        }
    }


    // Perform login
    public void login(String email, String password) {
        closePopup();
        wait.until(ExpectedConditions.elementToBeClickable(accountIcon)).click();
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        System.out.println("Login successful.");
    }

    // Click on fams
    public void clickfamsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(famsLink)).click();
        System.out.println("Clicked on 'fams'.");
    }

    // Click on the specific product
    public void clickProduct() {
        int maxRetries = 3;
        boolean clicked = false;

        for (int attempt = 0; attempt < maxRetries && !clicked; attempt++) {
            try {
                // Re-locate the product element dynamically each time
                WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(PRODUCT_XPATH)));

                // Scroll the element into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);

                // Add a small pause
                Thread.sleep(500);

                // Wait for the element to be clickable and click it
                wait.until(ExpectedConditions.elementToBeClickable(product)).click();
                System.out.println("✅ Clicked on the selected product.");

                // Wait for page to load after clicking
                waitForPageLoad();

                clicked = true;
            } catch (Exception e) {
                System.out.println("Attempt " + (attempt + 1) + " failed: " + e.getMessage());
                if (attempt == maxRetries - 1) {
                    System.out.println("❌ Failed to click on the selected product after " + maxRetries + " attempts");
                    throw new RuntimeException("Failed to click product after multiple attempts", e);
                }
                try {
                    Thread.sleep(1000); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Helper method to wait for page load
    private void waitForPageLoad() {
        try {
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            Thread.sleep(1000); // Small additional wait for any JS to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Click "Add to Cart"
    public void addToCart() {
        int maxRetries = 3;
        boolean clicked = false;

        for (int attempt = 0; attempt < maxRetries && !clicked; attempt++) {
            try {
                // Re-locate the button using By instead of the previously found element
                WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='bold add-to-bag'and text()='add to bag']")));

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
                Thread.sleep(500);

                addToCartBtn.click();
                System.out.println("✅ Clicked 'Add to Cart'.");
                clicked = true;
            } catch (Exception e) {
                System.out.println("Attempt " + (attempt + 1) + " to click 'Add to Cart' failed: " + e.getMessage());
                if (attempt == maxRetries - 1) {
                    System.out.println("❌ Failed to click 'Add to Cart' after " + maxRetries + " attempts");
                    throw new RuntimeException("Failed to add to cart after multiple attempts", e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Verify item is added to cart
    public boolean isItemInCart() {
        int maxRetries = 3;

        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                // Re-locate using By instead of the previously found element
                WebElement cartConfirm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='img-box']")));

                System.out.println("✅ Item is in the cart.");
                return true;
            } catch (Exception e) {
                System.out.println("Attempt " + (attempt + 1) + " to verify cart failed: " + e.getMessage());
                if (attempt == maxRetries - 1) {
                    System.out.println("❌ Item NOT found in the cart after " + maxRetries + " attempts!");
                    return false;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return false;
    }
}