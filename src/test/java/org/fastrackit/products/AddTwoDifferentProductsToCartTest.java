package org.fastrackit.products;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.CartPage;
import org.fastrackit.DemoShopPage;
import org.fastrackit.Footer;
import org.fastrackit.Product;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "Two different products in suite.")
@Feature("Add two products to basket")
public class AddTwoDifferentProductsToCartTest extends BaseTestConfig {
    DemoShopPage page;

    @BeforeTest
    public void setup() {
        page = new DemoShopPage();
        page.openDemoShopApp();
    }

    @AfterTest
    public void closeBrowser() {
        Selenide.closeWindow();
    }

    @BeforeMethod
    public void prerequisites() {
        Product metalMouse = new Product("7", "Practical Metal Mouse", "9.99");
        metalMouse.addToCart();
        Product softPizza = new Product("9", "Gorgeous Soft Pizza", "19.99");
        softPizza.addToCart();
    }

    @AfterMethod
    public void cleanup() {
        System.out.println("Cleaning up after the test.");
        page.openDemoShopApp();
        Footer footer = new Footer();
        footer.resetPage();
    }

    @Test
    public void adding_metal_mouse_and_gorgeous_pizza_to_cart_two_products_are_in_cart() {
        String numberOfProductsInCart = page.getHeader().getNumberOfProductsInCart();
        assertEquals(numberOfProductsInCart, "2", "Adding 2 different products in cart.");
    }

    @Test
    public void adding_metal_mouse_and_gorgeous_pizza_to_cart_navigate_to_cart_page_two_products_are_in_cart() {
        page.getHeader().clickOnTheCartIcon();
        CartPage cartPage = new CartPage();
        int numberOfDistinctProducts = cartPage.getNumberOfDistinctProducts();
        int totalProductsInCart = cartPage.getTotalProductsInCart();

        assertEquals(numberOfDistinctProducts, 2, "Adding 2 different products to cart");
        assertEquals(totalProductsInCart, 2, "Total products in cart are 2");
    }

    @Test(description = "Adding metal mouse and gorgeous pizza products to cart, total cost is correctly added")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When adding a product to cart, the total cost of the product is correctly calculated int the Cart page.")
    public void adding_metal_mouse_and_gorgeous_pizza_products_to_cart_total_cost_is_correctly_added() {
        page.getHeader().clickOnTheCartIcon();
        CartPage cartPage = new CartPage();
        double totalCartCost = cartPage.getTotalCartCostBasedOnProducts();
        double totalCartAmount = cartPage.getTotalCartAmount();
        assertEquals(totalCartCost, 29.98, "The total products is 29.98");
        assertEquals(totalCartAmount, 29.98, "The cart total is 29.98");
    }
}
