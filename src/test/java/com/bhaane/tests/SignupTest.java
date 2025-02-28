
package com.bhaane.tests;

import com.bhaane.pages.LoginPage;
import com.bhaane.pages.SignupPage;
import com.bhaane.base.*;
import org.testng.annotations.Test;
import com.bhaane.pages.*;

public class SignupTest extends BaseTest {

    @Test
    public void testSignup() {
    	LoginPage loginPage = new LoginPage(driver); 
        SignupPage signupPage = new SignupPage(driver);
      
        signupPage.signup("John", "Doe", "johndoe123@gmail.com", "9876543210", "Test@123");
    }
}