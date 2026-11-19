package com.oipolloi.automation.tests;

import com.oipolloi.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    @Test(groups = {"smoke"})
    public void homepageLoadsWithExpectedBrandTitle() {
        HomePage home = new HomePage(driver).open();
        Assert.assertTrue(home.title().toLowerCase().contains("oi polloi"),
                "Homepage title should identify Oi Polloi.");
        Assert.assertTrue(home.url().startsWith("https://oipolloi.com"),
                "Browser should be on the Oi Polloi storefront.");
    }

    @Test(groups = {"smoke"})
    public void brandLogoIsVisibleAndNavigatesToHomepage() {
        HomePage home = new HomePage(driver).open();
        Assert.assertTrue(home.logoIsVisible(), "Store logo/home link should be visible.");
        home.clickLogo();
        Assert.assertTrue(home.url().equals("https://oipolloi.com/")
                        || home.url().equals("https://oipolloi.com"),
                "Clicking the logo should navigate to the homepage.");
    }

    @Test(groups = {"smoke"})
    public void globalShippingAnnouncementIsVisible() {
        HomePage home = new HomePage(driver).open();
        Assert.assertTrue(home.shippingAnnouncementIsVisible(),
                "Free standard global shipping announcement should be visible.");
    }

    @Test(groups = {"smoke"})
    public void shopNavigationIsAvailable() {
        HomePage home = new HomePage(driver).open();
        Assert.assertTrue(home.shopMenuIsVisible(),
                "Shop menu or its category links should be available.");
        Assert.assertTrue(home.clothingLinkIsVisible(),
                "Clothing should be available from the Shop navigation.");
        Assert.assertTrue(home.footwearLinkIsVisible(),
                "Footwear should be available from the Shop navigation.");
        Assert.assertTrue(home.accessoriesLinkIsVisible(),
                "Accessories should be available from the Shop navigation.");
    }
}
