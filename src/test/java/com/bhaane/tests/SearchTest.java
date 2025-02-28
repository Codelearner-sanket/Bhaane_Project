package com.bhaane.tests;

import com.bhaane.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.bhaane.pages.SearchPage;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchFunctionality() {
        // Initialize SearchPage
        SearchPage searchPage = new SearchPage(driver);

        // Perform a search
        String searchKeyword = "Top";
        searchPage.performSearch(searchKeyword);

        // Verify search results
        String searchResultsText = searchPage.getSearchResultsText();
        Assert.assertTrue(searchResultsText.contains(searchKeyword), "Search results do not match the keyword.");
    }

    @Test
    public void testInvalidSearchFunctionality() {
        // Initialize SearchPage
        SearchPage searchPage = new SearchPage(driver);

        // Perform a search with an invalid keyword
        String invalidSearchKeyword = "hhhhh";
        searchPage.performSearch(invalidSearchKeyword);

        // Verify search results for invalid keyword
        String searchResultsText = searchPage.getSearchResultsText();
   Assert.assertTrue(searchResultsText.contains("Showing search results for "), "Invalid search results not displayed.");
    }
}
