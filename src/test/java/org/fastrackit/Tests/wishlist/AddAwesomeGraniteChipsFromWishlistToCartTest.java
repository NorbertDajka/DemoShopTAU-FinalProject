package org.fastrackit.Tests.wishlist;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.Body.Header;
import org.fastrackit.Body.LoginModal;
import org.fastrackit.Pages.CartPage;
import org.fastrackit.Pages.DemoShopPage;
import org.fastrackit.Pages.WishlistPage;
import org.fastrackit.Product.Product;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.User;
import org.fastrackit.dataprovider.UserDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
@Test(suiteName = "Wishlist")
@Feature("Adding products from wishlist to cart as logged in user")
public class AddAwesomeGraniteChipsFromWishlistToCartTest extends BaseTestConfig {
    DemoShopPage page;
    LoginModal loginModal;
    Header header;
    Product awesomeGraniteChips;
    WishlistPage wishlistPage;
    CartPage cartPage;
    @BeforeMethod
    public void setup(){
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.loginModal = new LoginModal();
        this.awesomeGraniteChips = new Product("1", "Awesome Granite Chips", "15.99");
        this.header = new Header();
        this.wishlistPage = new WishlistPage(awesomeGraniteChips);
        this.cartPage = new CartPage();
    }
    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Adding Awesome Granite Chips from wishlist to cart verified at cart badge")
    @Severity(SeverityLevel.NORMAL)

    public void add_awesome_granite_chips_from_wishlist_to_cart_as_logged_in_user_verified_on_badge(User user) {

        loginModal.login(user.getUsername(), user.getPassword());
        awesomeGraniteChips.addToWishlist();
        header.clickOnWishlistIcon();
        wishlistPage.addProductToCart(awesomeGraniteChips);

        assertTrue(header.areAddedProductsInCart());
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Adding from wishlist to cart verified on Cartpage")
    @Severity(SeverityLevel.NORMAL)
    public void add_awesome_granite_chips_from_wishlist_to_cart_verify_on_cart_page(User user) {

        loginModal.login(user.getUsername(), user.getPassword());
        awesomeGraniteChips.addToWishlist();
        header.clickOnWishlistIcon();
        wishlistPage.addProductToCart(awesomeGraniteChips);
        header.clickOnTheCartIcon();

        assertEquals(cartPage.getNumberOfDistinctProducts(), 1, "One product is in cart!");

    }
}

