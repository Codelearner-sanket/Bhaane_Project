package com.bhaane.tests;

import com.bhaane.base.BaseTest;
import com.bhaane.pages.AddToCart;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void testWishlistFunctionality() {
        AddToCart wishlistPage = new AddToCart(driver);

        String email = "johndoe123@gmail.com";
        String password = "Test@123";

        // Close popup (if any)
        wishlistPage.closePopup();

        // Click on the dropdown wrapper before login
        wishlistPage.selectFirstDropdownOption();

        // Login
        wishlistPage.login(email, password);

        // Click "March Sale"
        wishlistPage.clickfamsLink();

        // Click the selected product
        wishlistPage.clickProduct();

        // Add product to cart
        wishlistPage.addToCart();

        // Verify item in cart
        boolean isItemAdded = wishlistPage.isItemInCart();
        Assert.assertTrue(isItemAdded, "❌ Item was not added to the cart!");
    }
}