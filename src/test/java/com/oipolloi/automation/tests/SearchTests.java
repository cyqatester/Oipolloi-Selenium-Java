package com.oipolloi.automation.tests;

import com.oipolloi.automation.pages.HomePage;
import com.oipolloi.automation.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {

    @Test(groups = {"search"})
    public void searchControlIsVisibleAndCanBeOpened() {
        HomePage home = new HomePage(driver).open();
        Assert.assertTrue(home.searchTriggerIsVisible(),
                "Search control or search field should be visible.");
        home.openSearch();
        Assert.assertTrue(new SearchPage(driver).searchInputIsVisible(),
                "Search input should be visible after opening search.");
    }

    @Test(groups = {"search"})
    public void searchingForProductDisplaysSearchPage() {
        HomePage home = new HomePage(driver).open();
        home.searchFor("jacket");
        SearchPage search = new SearchPage(driver);
        Assert.assertTrue(search.url().contains("search") || search.url().contains("q="),
                "Submitting a keyword should navigate to search results.");
        Assert.assertTrue(search.resultsRegionIsVisible(),
                "Search results region should be visible.");
    }
}
