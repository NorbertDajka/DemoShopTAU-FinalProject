package org.fastrackit.addToCart;

import io.qameta.allure.Feature;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.User;
import org.fastrackit.dataprovider.UserDataProvider;
import org.fastrackit.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("Add to cart")
public class LoggedUserAddToCartFromProductsListTest extends BaseTestConfig {
    DemoShopPage page;

    @BeforeMethod
    public void setup() {
        this.page = new DemoShopPage();
        page.openDemoShopApp();
    }

    @AfterMethod
    public void cleanup() {
        System.out.println("Cleaning up after the test.");
        Footer footer = new Footer();
        footer.resetPage();
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    public void loggedUserAddProductToCart(User user) {
        page.getHeader().clickOnTheLoginButton();
        LoginModal loginModal = new LoginModal();
        loginModal.fillInUsername(user.getUsername());
        loginModal.fillInPassword(user.getPassword());
        loginModal.clickSubmitButton();

        Product metalMouse = new Product("7", "Practical Metal Mouse", "9.99");
        metalMouse.addToCart();

        boolean areProductsAdded = page.getHeader().areAddedProductsInCart();
        assertTrue(areProductsAdded, "Cart badge is displayed when products are added to cart.");
        String numberOfProductsInCart = page.getHeader().getNumberOfProductsInCart();
        assertEquals(numberOfProductsInCart, "1", "Logged in user and ads 1 product to cart.");

    }
}
