package com.bhaane.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import com.bhaane.base.BaseTest;
import com.bhaane.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	@Test	
    public void testValidLogin() throws Exception {
		
        LoginPage loginPage = new LoginPage(driver);
       // loginPage.clickMdivTag();
        loginPage.account();
        Thread.sleep(3000);
        loginPage.login("sanket123@gmail.com", "Pass@123");
        // Add assertions to verify successful login
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.account();
        loginPage.login("invalid@email.com", "invalidPassword");
        // Add assertions to verify error message
    }

}
