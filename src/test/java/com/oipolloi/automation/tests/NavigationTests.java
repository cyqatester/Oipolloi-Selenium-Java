package com.oipolloi.automation.tests;

import com.oipolloi.automation.pages.CollectionPage;
import com.oipolloi.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {

        @Test(groups = { "navigation" })
        public void clothingNavigationFromShopDropdown() {
                HomePage home = new HomePage(driver).open();
                Assert.assertTrue(home.clothingLinkIsVisible(), "Clothing link should be visible.");
                home.navigateToClothing();
                Assert.assertTrue(home.url().contains("/collections/clothing"),
                                "Clothing link should navigate to the clothing collection.");
                Assert.assertTrue(new CollectionPage(driver).mainContentIsVisible(),
                                "Clothing collection main content should be visible.");
        }

        @Test(groups = { "navigation" })
        public void footwearNavigationFromShopDropdown() {
                HomePage home = new HomePage(driver).open();
                Assert.assertTrue(home.footwearLinkIsVisible(), "Footwear link should be visible.");
                home.navigateToFootwear();
                Assert.assertTrue(home.url().contains("/collections/footwear"),
                                "Footwear link should navigate to the footwear collection.");
                Assert.assertTrue(new CollectionPage(driver).mainContentIsVisible(),
                                "Footwear collection main content should be visible.");
        }

        @Test(groups = { "navigation" })
        public void accessoriesNavigationFromShopDropdown() {
                HomePage home = new HomePage(driver).open();
                Assert.assertTrue(home.accessoriesLinkIsVisible(), "Accessories link should be visible.");
                home.navigateToAccessories();
                Assert.assertTrue(home.url().contains("/collections/accessories"),
                                "Accessories link should navigate to the accessories collection.");
                Assert.assertTrue(new CollectionPage(driver).mainContentIsVisible(),
                                "Accessories collection main content should be visible.");
        }

        @Test(groups = { "navigation" })
        public void latestCollectionNavigationWorks() {
                HomePage home = new HomePage(driver).open();
                home.navigateToLatest();
                CollectionPage collection = new CollectionPage(driver);
                Assert.assertTrue(collection.url().contains("/collections/latest"),
                                "Latest link should open the latest collection.");
                Assert.assertTrue(collection.mainContentIsVisible(),
                                "Latest collection main content should be visible.");
        }

        @Test(groups = { "navigation" })
        public void brandsDropdownExposesAllBrandsLink() {
                HomePage home = new HomePage(driver).open();

                Assert.assertTrue(home.brandsTriggerIsVisible(),
                                "Brands navigation should be available.");
                Assert.assertTrue(home.allBrandsLinkIsVisible(),
                                "All Brands link should be visible after opening the Brands menu.");
        }

        @Test(groups = { "navigation" })
        public void deckOutStoryNavigationIsAvailable() {
                HomePage home = new HomePage(driver).open();

                Assert.assertTrue(home.deckOutLinkIsVisible(),
                                "Deck~Out story link should be visible on the storefront.");

                home.openDeckOut();

                Assert.assertTrue(home.url().contains("/blogs/deck-out"),
                                "Deck~Out link should navigate to the Deck~Out blog.");
        }
}
