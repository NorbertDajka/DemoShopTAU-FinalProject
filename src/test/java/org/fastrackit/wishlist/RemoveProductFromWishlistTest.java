package org.fastrackit.wishlist;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.fastrackit.*;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.UserDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.fastrackit.dataprovider.*;



public class RemoveProductFromWishlistTest extends BaseTestConfig {
    @AfterMethod
    public void cleanup(){
        Footer footer = new Footer();
        footer.resetPage();
    }
    public static void login(String username,String password){
        LoginModal loginpage = new LoginModal();
        loginpage.fillInUsername(username);
        loginpage.fillInPassword(password);
        loginpage.clickSubmitButton();
    }
    @BeforeMethod()
    public void setup(){
        DemoShopPage page = new DemoShopPage();
        page.openDemoShopApp();

        Header header = new Header();
        header.clickOnTheLoginButton();





    }
    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Removing a whislisted product from home page and verified that dissapears from badge")
    public void removing_a_product_from_wishlist_verified_at_badge(User user) {

        login(user.getUsername(),user.getPassword());

        Product licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        licensedSteelGloves.addToWishlist();

        licensedSteelGloves.clickOnRemoveFromWishlistButton();

        Header header = new Header();

        Assert.assertFalse(header.getWishlistBadge().exists(),"Product removed from wishlist from homepage");
    }
    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Removing a whislisted product from wishlist page and verified that dissapears from badge")
    public void removing_a_product_from_wishlist_page_verified_at_badge(User user) {

        login(user.getUsername(),user.getPassword());

        Product licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        licensedSteelGloves.addToWishlist();

        Header header = new Header();
        header.clickOnWishlistIcon();

        WishlistPage wishlistPage = new WishlistPage(licensedSteelGloves);
        wishlistPage.getWishlistProductRemoveFromWishlistButton();

        Assert.assertFalse(header.getWishlistBadge().exists(),"Product removed from wishlist from homepage");
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Removing a whislisted product from home page and verified that dissapears from wishlist page")
    public void removing_a_product_from_wishlist_verified_at_wishlist_page(User user) {

        login(user.getUsername(),user.getPassword());

        Product licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        licensedSteelGloves.addToWishlist();

        licensedSteelGloves.clickOnRemoveFromWishlistButton();

        Header header = new Header();
        header.clickOnWishlistIcon();

        WishlistPage wishlist = new WishlistPage(licensedSteelGloves);

        Assert.assertFalse(wishlist.isDisplayedAndExists());

    }
    //NEXT verify if dissapears from whislist page
}
