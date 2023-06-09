package org.fastrackit.Tests.wishlist;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.Body.Header;
import org.fastrackit.Body.LoginModal;
import org.fastrackit.Pages.DemoShopPage;
import org.fastrackit.Pages.WishlistPage;
import org.fastrackit.Product.Product;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.UserDataProvider;
import org.testng.annotations.*;
import org.fastrackit.dataprovider.*;

import static org.testng.Assert.*;

@Test(suiteName = "Wishlist")
@Feature("Removing from wishlist - logged in user")
public class RemoveProductFromWishlistTest extends BaseTestConfig {
    DemoShopPage page;
    LoginModal loginModal;
    Header header;
    Product licensedSteelGloves;
    WishlistPage wishlistPage;
    @BeforeMethod
    public void setup(){
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.loginModal = new LoginModal();
        this.licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        this.header = new Header();
        this.wishlistPage = new WishlistPage(licensedSteelGloves);
    }
    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Removing a whislisted product from home page and verified that dissapears from badge")
    @Severity(SeverityLevel.NORMAL)
    public void removing_a_product_from_wishlist_on_homepage_as_logged_user_verified_at_badge(User user) {
        loginModal.login(user.getUsername(),user.getPassword());

        licensedSteelGloves.addToWishlist();
        assertTrue(header.getWishlistBadge().exists(),"Product added to wishlist from homepage");

        licensedSteelGloves.clickOnRemoveFromWishlistButton();
        assertFalse(header.getWishlistBadge().exists(),"Product removed from wishlist from homepage");
    }
    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Removing a whislisted product from wishlist page and verified that dissapears from badge")
    @Severity(SeverityLevel.NORMAL)
    public void removing_a_product_from_wishlist_page_as_logged_in_user_verified_at_badge(User user) {

        loginModal.login(user.getUsername(),user.getPassword());


        licensedSteelGloves.addToWishlist();
        assertTrue(header.getWishlistBadge().exists(),"Product added to wishlist from homepage");

        header.clickOnWishlistIcon();
        wishlistPage.clickRemoveFromWishlistButton();

        assertFalse(header.getWishlistBadge().exists(),"Product removed from wishlist from homepage");
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Removing a whislisted product from home page and verified that dissapears from wishlist page")
    @Severity(SeverityLevel.NORMAL)
    public void removing_a_product_from_wishlist_verified_at_wishlist_page_as_logged_in_user(User user) {

        loginModal.login(user.getUsername(),user.getPassword());

        licensedSteelGloves.addToWishlist();
        assertTrue(header.getWishlistBadge().exists(),"Product added to wishlist from homepage");

        licensedSteelGloves.clickOnRemoveFromWishlistButton();
        header.clickOnWishlistIcon();

        assertFalse(wishlistPage.productIsDisplayedAndExists());
    }

}
