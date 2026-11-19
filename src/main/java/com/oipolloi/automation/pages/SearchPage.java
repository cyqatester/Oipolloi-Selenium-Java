package com.oipolloi.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
    private final By searchInput = By.cssSelector(
            "input[type='search'], input[name='q'], input[placeholder*='Search']");
    private final By searchResults = By.cssSelector(
            "main, #MainContent, [id*='SearchResults']");
    private final By productLinks = By.cssSelector("a[href*='/products/']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public int resultProductCount() {
        return all(productLinks).size();
    }

    public boolean resultsRegionIsVisible() {
        return isVisible(searchResults);
    }

    public String url() {
        return currentUrl();
    }

    public boolean searchInputIsVisible() {
    return driver.findElements(searchInput)
            .stream()
            .anyMatch(element -> {
                try {
                    return element.isDisplayed();
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    return false;
                }
            });
    }
}
