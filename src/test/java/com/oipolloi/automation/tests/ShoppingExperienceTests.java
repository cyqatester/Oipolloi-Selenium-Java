package com.oipolloi.automation.tests;

import com.oipolloi.automation.pages.CollectionPage;
import com.oipolloi.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingExperienceTests extends BaseTest {

    @Test(groups = {"catalog"})
    public void homepageDisplaysAtLeastOneProductLink() {
        HomePage home = new HomePage(driver).open();
        Assert.assertTrue(home.productLinkCount() > 0,
                "Homepage should contain at least one product link.");
        Assert.assertTrue(home.anyProductCardIsVisible(),
                "At least one product link should be visible.");
    }

    @Test(groups = {"catalog"})
    public void latestCollectionDisplaysProductCards() {
        HomePage home = new HomePage(driver).open();
        home.navigateToLatest();
        CollectionPage collection = new CollectionPage(driver);
        Assert.assertTrue(collection.collectionHeadingIsVisible(),
                "Latest collection should display a heading.");
        Assert.assertTrue(collection.productCount() > 0,
                "Latest collection should contain product links.");
        Assert.assertTrue(collection.firstProductIsVisible(),
                "First product link should be visible.");
    }

    @Test(groups = {"catalog"})
    public void latestCollectionDisplaysProductPricing() {
        HomePage home = new HomePage(driver).open();
        home.navigateToLatest();
        CollectionPage collection = new CollectionPage(driver);
        Assert.assertTrue(collection.anyPriceIsVisible(),
                "At least one product price should be displayed in the latest collection.");
    }

    @Test(groups = {"cart"})
    public void cartControlOpensCartView() {
        HomePage home = new HomePage(driver).open();
        Assert.assertTrue(home.cartTriggerIsVisible(), "Cart control should be visible.");
        home.openCart();
        Assert.assertTrue(home.cartViewIsVisible(),
                "Cart drawer or cart page should be displayed after clicking Cart.");
    }
}
