package org.fastrackit.products;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;

@Feature("Add single product to basket")
public class AddMetalMouseToBasketTest extends BaseTestConfig {

    DemoShopPage page;
    Product practicalMetalMouse;
    ProductDetailsPage detailsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.practicalMetalMouse = new Product("7", "Practical Metal Mouse", "9.99");
        practicalMetalMouse.clickOnTheProductLink();

        this.detailsPage = new ProductDetailsPage();
        this.cartPage = new CartPage();
    }

    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void add_practical_metal_mouse_product_in_cart_number_of_products_in_cart_is_one() {
        detailsPage.clickOnTheAddToBasketButton();
        assertEquals(page.getHeader().getNumberOfProductsInCart(), "1", "Adding one product to cart, the cart badge is 1");
    }

    @Test
    public void add_two_practical_metal_mouse_products_in_cart() {

        detailsPage.clickOnTheAddToBasketButton();
        detailsPage.clickOnTheAddToBasketButton();

        assertEquals(page.getHeader().getNumberOfProductsInCart(), "2", "Adding two products to cart, the cart badge is 2");
    }

    @Test
    public void add_metal_mouse_to_cart_total_cost_is_correctly_added() {

        detailsPage.clickOnTheAddToBasketButton();

        page.getHeader().clickOnTheCartIcon();

        double totalCartCost = cartPage.getTotalCartCostBasedOnProducts();
        double totalCartAmount = cartPage.getTotalCartAmount();

        assertEquals(totalCartCost, 9.99, "The total products is 9.99");
        assertEquals(totalCartAmount, 9.99, "The cart total is 9.99");
    }

    @Test
    public void add_two_practical_metal_mouse_to_cart_total_cost_is_correctly_added() {
        detailsPage.clickOnTheAddToBasketButton();
        detailsPage.clickOnTheAddToBasketButton();

        page.getHeader().clickOnTheCartIcon();

        double totalCartCost = cartPage.getTotalCartCostBasedOnProducts();
        double totalCartAmount = cartPage.getTotalCartAmount();

        assertEquals(totalCartAmount, 19.98, "The cart total is 19.98");
        assertEquals(totalCartCost, 19.98, "The total products is 19.98");
    }
}
