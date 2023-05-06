package org.fastrackit.Tests.addToCart;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.Pages.CartPage;
import org.fastrackit.Pages.DemoShopPage;
import org.fastrackit.Pages.ProductDetailsPage;
import org.fastrackit.Product.Product;
import org.fastrackit.config.BaseTestConfig;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "Add to cart")
@Feature("Add two products to cart")
public class AddTwoDifferentProductsToCartTest extends BaseTestConfig {
    DemoShopPage page;
    Product practicalMetalMouse;
    Product softPizza;
    ProductDetailsPage detailsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.practicalMetalMouse = new Product("7", "Practical Metal Mouse", "9.99");
        practicalMetalMouse.addToCart();

        this.softPizza = new Product("9", "Gorgeous Soft Pizza", "19.99");
        softPizza.addToCart();


        this.detailsPage = new ProductDetailsPage();
        this.cartPage = new CartPage();
    }
    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Added two different products verified on badge that two products appeared")
    public void adding_metal_mouse_and_gorgeous_pizza_to_cart_two_products_are_in_cart() {
        assertEquals(page.getHeader().getNumberOfProductsInCart(), "2", "Adding 2 different products in cart.");
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Added two different products verified on cart page that two different products appeared")
    public void adding_metal_mouse_and_gorgeous_pizza_to_cart_navigate_to_cart_page_two_products_are_in_cart() {
        page.getHeader().clickOnTheCartIcon();

        int numberOfDistinctProducts = cartPage.getNumberOfDistinctProducts();
        int totalProductsInCart = cartPage.getTotalProductsInCart();

        assertEquals(numberOfDistinctProducts, 2, "Adding 2 different products to cart");
        assertEquals(totalProductsInCart, 2, "Total products in cart are 2");
    }

    @Test(description = "Adding metal mouse and gorgeous pizza products to cart, total cost is correctly added")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("When adding a product to cart, the total cost of the product is correctly calculated int the Cart page.")
    public void adding_metal_mouse_and_gorgeous_pizza_products_to_cart_total_cost_is_correctly_added() {
        page.getHeader().clickOnTheCartIcon();

        double totalCartCost = cartPage.getTotalCartCostBasedOnProducts();
        double totalCartAmount = cartPage.getTotalCartAmount();

        assertEquals(totalCartCost, 29.98, "The total products is 29.98");
        assertEquals(totalCartAmount, 29.98, "The cart total is 29.98");
    }
}
