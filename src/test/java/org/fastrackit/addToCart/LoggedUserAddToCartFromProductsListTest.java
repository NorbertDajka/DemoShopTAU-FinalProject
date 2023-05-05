package org.fastrackit.addToCart;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.User;
import org.fastrackit.dataprovider.UserDataProvider;
import org.fastrackit.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("Add to cart")
public class LoggedUserAddToCartFromProductsListTest extends BaseTestConfig {
    DemoShopPage page;
    LoginModal loginModal;
    Product metalMouse;
    @BeforeMethod
    public void setup() {
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.loginModal = new LoginModal();
        this.metalMouse= new Product("7", "Practical Metal Mouse", "9.99");
    }
    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    public void loggedUserAddProductToCart(User user) {
        loginModal.login(user.getUsername(),user.getPassword());
        metalMouse.addToCart();

        assertTrue(page.getHeader().areAddedProductsInCart(), "Cart badge is displayed when products are added to cart.");
        assertEquals(page.getHeader().getNumberOfProductsInCart(), "1", "Logged in user and ads 1 product to cart.");
    }
}
