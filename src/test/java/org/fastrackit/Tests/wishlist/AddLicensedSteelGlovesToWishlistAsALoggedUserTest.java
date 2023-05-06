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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.fastrackit.dataprovider.*;

import static org.testng.Assert.*;


@Feature("Adding to wishlist as logged in user")
@Test(suiteName = "Wishlist")
public class AddLicensedSteelGlovesToWishlistAsALoggedUserTest extends BaseTestConfig {
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
      @Description("Adding to wishlist logged in with different users validation on badge")
      @Severity(SeverityLevel.NORMAL)
        public void add_products_to_wishlist_as_a_logged_in_user_verified_on_badge(User user){
        loginModal.login(user.getUsername(), user.getPassword());

        licensedSteelGloves.addToWishlist();

        assertTrue(header.wishlistBadge.exists(),"Wishlist badge appeared.Product added successfully to the wishlist");
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Adding to wishlist logged in with different users wishlist page check")
    @Severity(SeverityLevel.NORMAL)
    public void add_products_to_wishlist_as_a_logged_in_user_verified_on_wishlist_page(User user){
        loginModal.login(user.getUsername(), user.getPassword());

        licensedSteelGloves.addToWishlist();

        header.clickOnWishlistIcon();

        assertTrue(wishlistPage.productIsDisplayedAndExists(),"Wishlist page contains one product");
        assertEquals(wishlistPage.getWishListProductCardName(),wishlistPage.getInWishlistProductName(),"Product in wishlist is same as the one we added on the main page");

    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("By adding a product the the wishlist the icon and the function of the button should change to remove")
    @Severity(SeverityLevel.NORMAL)
    public void adding_to_the_wishlist_should_change_the_button_logged_in_user(User user){

        loginModal.login(user.getUsername(), user.getPassword());

        licensedSteelGloves.addToWishlist();

        assertTrue(licensedSteelGloves.isRemoveFromWishlistButtonAppeared(),"The add to wishlist button for " + licensedSteelGloves.getName() + " have changed to remove product from wishlist");
    }
}
