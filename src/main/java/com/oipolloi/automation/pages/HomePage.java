
package com.oipolloi.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;

public class HomePage extends BasePage {

    // All storefront selectors are intentionally maintained in page objects.
    private final By logoLink = By.cssSelector("a[href='/']");

    private final By shippingAnnouncement = By.xpath(
            "//*[self::a or self::p or self::div or self::span][contains(translate(normalize-space(.),"
                    + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'free standard global shipping')]");

    private final By shopMenuTrigger = By.xpath(
            "//*[self::summary or self::button][normalize-space()='Shop']");

    private final By clothingLink =
            By.cssSelector("a[href='/collections/clothing']");

    private final By footwearLink =
            By.cssSelector("a[href='/collections/footwear']");

    private final By accessoriesLink =
            By.cssSelector("a[href='/collections/accessories']");

    private final By latestLink =
            By.cssSelector("a[href='/collections/latest']");

    // private final By brandsTrigger =
    //         By.xpath("//*[normalize-space()='Brands']");

    private final By allBrandsLink =
            By.cssSelector("a[href='/pages/list-all-vendors']");

    private final By searchTrigger = By.xpath(
            "//a[contains(translate(@aria-label,'SEARCH','search'),'search') or contains(@href,'search')]"
                    + " | //button[contains(translate(@aria-label,'SEARCH','search'),'search')]");

    private final By searchInput = By.cssSelector(
            "input[type='search'], input[name='q'], input[placeholder*='Search']");

    private final By cartTrigger = By.cssSelector(
            "a[href='/cart'], a[href*='/cart'], button[aria-label*='Cart'], a[aria-label*='Cart']");

    private final By cartHeading = By.xpath(
            "//*[self::h1 or self::h2 or self::h3][contains(translate(normalize-space(.),"
                    + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'cart')]");

    private final By deckOutLink =
            By.cssSelector("a[href='/blogs/deck-out']");

    private final By productLinks =
            By.cssSelector("a[href*='/products/']");

    // private final By productCards = By.cssSelector(
    //         "li:has(a[href*='/products/']), .grid__item:has(a[href*='/products/']), "
    //                 + "[class*='product']:has(a[href*='/products/'])");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(com.oipolloi.automation.config.Config.baseUrl());
        return this;
    }

    public String title() {
        return driver.getTitle();
    }

    // Returns true if at least one matching element is displayed.
    private boolean isAnyElementVisible(By locator) {
        return driver.findElements(locator)
                .stream()
                .anyMatch(element -> {
                    try {
                        return element.isDisplayed();
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }
                });
    }

    // Returns the first visible and enabled matching element.
    private WebElement firstVisibleElement(By locator) {
        return wait.until(d -> {
            for (WebElement element : d.findElements(locator)) {
                try {
                    if (element.isDisplayed() && element.isEnabled()) {
                        return element;
                    }
                } catch (StaleElementReferenceException ignored) {
                    // Continue checking other matching elements.
                }
            }
            return null;
        });
    }

    public boolean logoIsVisible() {
        return isVisible(logoLink);
    }

    public void clickLogo() {
        clickWithFallback(logoLink);
    }

    public boolean shippingAnnouncementIsVisible() {
        return isVisible(shippingAnnouncement);
    }

    public boolean shopMenuIsVisible() {
        return isVisible(shopMenuTrigger) || isVisible(clothingLink);
    }

    public void openShopMenu() {
        if (isVisible(shopMenuTrigger)) {
            WebElement trigger = visible(shopMenuTrigger);
            try {
                trigger.click();
            } catch (org.openqa.selenium.WebDriverException e) {
                hover(shopMenuTrigger);
            }
        } else if (isVisible(clothingLink)) {
            return;
        }
    }

    public boolean clothingLinkIsVisible() {
        openShopMenu();
        return isVisible(clothingLink);
    }

    public boolean footwearLinkIsVisible() {
        openShopMenu();
        return isVisible(footwearLink);
    }

    public boolean accessoriesLinkIsVisible() {
        openShopMenu();
        return isVisible(accessoriesLink);
    }

    public void navigateToClothing() {
        openShopMenu();
        clickWithFallback(clothingLink);
        waitForUrlContains("/collections/clothing");
    }

    public void navigateToFootwear() {
        openShopMenu();
        clickWithFallback(footwearLink);
        waitForUrlContains("/collections/footwear");
    }

    public void navigateToAccessories() {
        openShopMenu();
        clickWithFallback(accessoriesLink);
        waitForUrlContains("/collections/accessories");
    }

    public void navigateToLatest() {
        openShopMenu();
        clickWithFallback(latestLink);
        waitForUrlContains("/collections/latest");
    }

    // Brands navigation

    public boolean brandsTriggerIsVisible() {
        return isAnyElementVisible(allBrandsLink);
    }

    public void openBrandsMenu() {
        // The All Brands link is already exposed in the storefront navigation.
    }

    public boolean allBrandsLinkIsVisible() {
        return isAnyElementVisible(allBrandsLink);
    }

    public void navigateToAllBrands() {
        firstVisibleElement(allBrandsLink).click();
        waitForUrlContains("/pages/list-all-vendors");
    }

    // Cart navigation

    public boolean cartTriggerIsVisible() {
        return isVisible(cartTrigger);
    }

    public void openCart() {
        clickWithFallback(cartTrigger);
    }

    public boolean cartViewIsVisible() {
        return isVisible(cartHeading)
                || isVisible(By.cssSelector(
                        "cart-drawer, #CartDrawer, [id*='CartDrawer']"));
    }

    // Deck~Out navigation

    public boolean deckOutLinkIsVisible() {
        return isAnyElementVisible(deckOutLink);
    }

    public void openDeckOut() {
        firstVisibleElement(deckOutLink).click();
        waitForUrlContains("/blogs/deck-out");
    }

    // Product information

    public int productLinkCount() {
        return all(productLinks).size();
    }

    public boolean anyProductCardIsVisible() {
        List<WebElement> links = all(productLinks);

        return links.stream().anyMatch(element -> {
            try {
                return element.isDisplayed();
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    public String url() {
        return currentUrl();
    }

    public boolean searchResultsAreVisible() {
        return isVisible(By.cssSelector("main, #MainContent"));
    }

    //NEW
    public boolean searchTriggerIsVisible() {
        return isAnyElementVisible(searchTrigger)
                || isAnyElementVisible(searchInput);
    }

    public void openSearch() {
        // Search may already be open.
        if (isAnyElementVisible(searchInput)) {
            return;
        }

        // Find and click the first visible search control.
        WebElement trigger = firstVisibleElement(searchTrigger);

        try {
            trigger.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", trigger);
        }

        // Wait for a visible search input.
        firstVisibleElement(searchInput);
    }

    public void searchFor(String query) {
        openSearch();

        WebElement input = firstVisibleElement(searchInput);
        input.clear();
        input.sendKeys(query, Keys.ENTER);

        wait.until(d -> d.getCurrentUrl().contains("q=")
                || d.getCurrentUrl().contains("/search"));
    }
}