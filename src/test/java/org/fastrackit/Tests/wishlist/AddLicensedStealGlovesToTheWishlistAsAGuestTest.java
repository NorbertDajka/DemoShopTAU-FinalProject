package org.fastrackit.Tests.wishlist;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.Body.Header;
import org.fastrackit.Pages.DemoShopPage;
import org.fastrackit.Product.Product;
import org.fastrackit.config.BaseTestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


@Feature("Adding to wishlist as guest")
@Test(suiteName = "Wishlist")
public class AddLicensedStealGlovesToTheWishlistAsAGuestTest extends BaseTestConfig {
    DemoShopPage page;
    Header header;
    Product licensedSteelGloves;
    @BeforeMethod
    public void setup(){
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        this.header = new Header();
        licensedSteelGloves.addToWishlist();
    }

    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Adding a product to the wishlist and verified at the badge")
    public void add_product_to_wishlist_as_guest(){
        assertFalse(header.wishlistBadge.exists(),"Only logged in users should be able to create a wishlist!");
    }
}
