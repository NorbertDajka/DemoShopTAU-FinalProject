package org.fastrackit.Tests.addToCart;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.Body.LoginModal;
import org.fastrackit.Pages.DemoShopPage;
import org.fastrackit.Product.Product;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.User;
import org.fastrackit.dataprovider.UserDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("Logged user add to cart")
@Test(suiteName = "Add to cart")
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
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Adding a prodict to cart as logged in user verified that badge apeared and has text '1'")
    public void logged_user_add_product_to_cart(User user) {
        loginModal.login(user.getUsername(),user.getPassword());
        metalMouse.addToCart();

        assertTrue(page.getHeader().areAddedProductsInCart(), "Cart badge is displayed when products are added to cart.");
        assertEquals(page.getHeader().getNumberOfProductsInCart(), "1", "Logged in user and ads 1 product to cart.");
    }
}
