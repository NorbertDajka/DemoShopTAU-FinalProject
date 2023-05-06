package org.fastrackit.Tests.addToCart;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.Body.LoginModal;
import org.fastrackit.Pages.CartPage;
import org.fastrackit.Pages.DemoShopPage;
import org.fastrackit.Pages.ProductDetailsPage;
import org.fastrackit.Product.Product;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.ProductsDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
@Feature("Add all products one by one to cart")
@Test(suiteName = "Add to cart")
public class AddingAllProductsToCartTest extends BaseTestConfig {
    DemoShopPage page;
    ProductDetailsPage detailsPage;
    CartPage cartPage;
    LoginModal loginModal;
    @BeforeMethod
    public void setup() {
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.detailsPage = new ProductDetailsPage();
        this.cartPage = new CartPage();
        this.loginModal = new LoginModal();
    }
    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }

    @Test(dataProviderClass = ProductsDataProvider.class, dataProvider = "allProducts")
    @Description("Adding all products to cart as guest")
    @Severity(SeverityLevel.TRIVIAL)
    public void guest_add_all_products_to_cart_verified_at_badge(Product product) {
        product.addToCart();

        assertEquals(page.getHeader().getNumberOfProductsInCart(), "1", "Adding one product to cart, the cart badge is 1");
    }
    @Test(dataProviderClass = ProductsDataProvider.class, dataProvider = "allProducts")
    @Description("Adding all products to cart logged in with beetle user")
    @Severity(SeverityLevel.TRIVIAL)
    public void beetle_user_add_all_products_to_cart_verified_at_badge(Product product) {
        loginModal.login("beetle","choochoo");
        product.addToCart();

        assertEquals(page.getHeader().getNumberOfProductsInCart(), "1", "Adding one product to cart, the cart badge is 1");
    }
    @Test(dataProviderClass = ProductsDataProvider.class, dataProvider = "allProducts")
    @Description("Adding all products to cart as dino user")
    @Severity(SeverityLevel.TRIVIAL)
    public void dino_user_add_all_products_to_cart_verified_at_badge(Product product) {
        loginModal.login("dino","choochoo");
        product.addToCart();

        assertEquals(page.getHeader().getNumberOfProductsInCart(), "1", "Adding one product to cart, the cart badge is 1");
    }
    @Test(dataProviderClass = ProductsDataProvider.class, dataProvider = "allProducts")
    @Description("Adding all products to cart as turtle user")
    @Severity(SeverityLevel.TRIVIAL)
    public void turtle_user_add_all_products_to_cart_verified_at_badge(Product product) {
        loginModal.login("turtle","choochoo");
        product.addToCart();

        assertEquals(page.getHeader().getNumberOfProductsInCart(), "1", "Adding one product to cart, the cart badge is 1");
    }




}
