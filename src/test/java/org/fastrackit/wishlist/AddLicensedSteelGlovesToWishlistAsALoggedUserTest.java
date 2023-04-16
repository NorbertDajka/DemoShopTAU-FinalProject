package org.fastrackit.wishlist;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.fastrackit.dataprovider.*;

import static org.testng.Assert.*;


@Feature("Wishlist functionality as a logged in user")
public class AddLicensedSteelGlovesToWishlistAsALoggedUserTest extends BaseTestConfig {


    @AfterMethod
     public void cleanup(){
        Footer footer = new Footer();
        footer.resetPage();
    }
    public static void login(String username,String password){
        DemoShopPage page = new DemoShopPage();
        page.openDemoShopApp();

        Header header = new Header();
        header.clickOnTheLoginButton();

        LoginModal loginpage = new LoginModal();
        loginpage.fillInUsername(username);
        loginpage.fillInPassword(password);
        loginpage.clickSubmitButton();

    }

      @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
      @Description("Adding to wishlist logged in with different users badge functionality check")
        public void add_products_to_wishlist_as_a_logged_in_user_verified_by_badge(User user){
        login(user.getUsername(),user.getPassword());

        Product licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        licensedSteelGloves.addToWishlist();

        Header header = new Header();
        assertTrue(header.wishlistBadge.exists(),"Wishlist badge appeared.Product added successfully to the wishlist");

    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("Adding to wishlist logged in with different users wishlist page check")
    public void add_products_to_wishlist_as_a_logged_in_user_verified_on_wishlist_page(User user){
        login(user.getUsername(),user.getPassword());

        Product licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        licensedSteelGloves.addToWishlist();

        Header header = new Header();
        header.clickOnWishlistIcon();

        WishlistPage wishlist = new WishlistPage(licensedSteelGloves);

        assertTrue(wishlist.isDisplayedAndExists(),"Wishlist page contains one product");
        assertEquals(wishlist.getWishListProductCardName(),wishlist.getInWishlistProductName(),"Product in wishlist is same as the one we added on the main page");

    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Description("By adding a product the the wishlist the icon and the function of the button should change to remove")
    public void adding_to_the_wishlist_should_change_the_button(User user){
        login(user.getUsername(),user.getPassword());

        Product licensedSteelGloves = new Product("8","Licensed Steel Gloves","14.99");
        licensedSteelGloves.addToWishlist();

        assertTrue(licensedSteelGloves.isRemoveFromWishlistButtonAppeared(),"The add to wishlist button for " + licensedSteelGloves.getName() + " have changed to remove product from wishlist");
    }
}
