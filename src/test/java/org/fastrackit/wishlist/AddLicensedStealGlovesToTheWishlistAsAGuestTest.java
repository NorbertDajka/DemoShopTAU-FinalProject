package org.fastrackit.wishlist;

import io.qameta.allure.Feature;
import org.fastrackit.*;
import org.fastrackit.config.BaseTestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


@Feature("Wishlist functionality as guest")
public class AddLicensedStealGlovesToTheWishlistAsAGuestTest extends BaseTestConfig {

    @AfterMethod
     public void cleanup(){
        Footer footer = new Footer();
        footer.resetPage();
    }

    @Test
    public void add_product_to_wishlist_as_guest(){
        DemoShopPage page = new DemoShopPage();
        page.openDemoShopApp();

        Product licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        licensedSteelGloves.addToWishlist();

        Header header = new Header();

        assertFalse(header.wishlistBadge.exists(),"Only logged in users should be able to create a wishlist!");
    }
}
