package com.oipolloi.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CollectionPage extends BasePage {
    private final By mainContent = By.cssSelector("main, #MainContent");
    private final By collectionHeading = By.cssSelector("main h1, #MainContent h1, h1");
    private final By productLinks = By.cssSelector("a[href*='/products/']");
    private final By productPrices = By.cssSelector(
            ".price, .price-item, [class*='price'], [data-price]");

    public CollectionPage(WebDriver driver) {
        super(driver);
    }

    public boolean mainContentIsVisible() {
        return isVisible(mainContent);
    }

    public boolean collectionHeadingIsVisible() {
        return isVisible(collectionHeading);
    }

    public String collectionHeadingText() {
        return text(collectionHeading);
    }

    public int productCount() {
        return all(productLinks).size();
    }

    public boolean firstProductIsVisible() {
        return !all(productLinks).isEmpty() && all(productLinks).get(0).isDisplayed();
    }

    public boolean anyPriceIsVisible() {
        return all(productPrices).stream().anyMatch(e -> {
            try { return e.isDisplayed() && !e.getText().isBlank(); }
            catch (org.openqa.selenium.StaleElementReferenceException ignored) { return false; }
        });
    }

    public String url() {
        return currentUrl();
    }
}
