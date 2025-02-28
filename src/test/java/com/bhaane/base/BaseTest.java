package com.bhaane.base; 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.bhaane.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bhaane.com");

        // Handle popup once before tests
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickMdivTag(); 
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
