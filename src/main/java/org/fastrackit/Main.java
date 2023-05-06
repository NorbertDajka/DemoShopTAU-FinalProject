package org.fastrackit;

import org.fastrackit.Body.Header;
import org.fastrackit.Body.LoginModal;
import org.fastrackit.Pages.CartPage;
import org.fastrackit.Pages.DemoShopPage;
import org.fastrackit.Product.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Open page and login.
        DemoShopPage page = new DemoShopPage();
        page.openDemoShopApp();
        String greetingsMsg = page.getHeader().getGreetingsMsg();
        System.out.println("Validate that the greetings msg is: " + greetingsMsg);
        page.getHeader().clickOnTheLoginButton();
        LoginModal modal = new LoginModal();
        modal.fillInUsername("beetle");
        modal.fillInPassword("choochoo");
        modal.clickSubmitButton();
        Header header = new Header();
        String greetingsMessage = header.getGreetingsMsg();
        System.out.println("Validate that the greetings msg is: " + greetingsMessage);

        System.out.println("-------------------------------------------------");

        // Add a product to Basket
        CartPage cartDetails = new CartPage();

        Product metalMouseProduct = new Product("9", "Practical Metal Mouse","9.99");
        page.getHeader().clickOnTheCartIcon();
        List<Product> productsInCart = cartDetails.getProductsInCart();
        System.out.println("Verify that the product practical metal mouse is in the cart: " + productsInCart.get(0).getName());
        System.out.println("Verify that the product practical metal mouse price is 9.99: " + cartDetails.getProductsInCart().get(0).getPrice());
        System.out.println("Verify that the total price in cart is 9.99: " + cartDetails.getTotalCartCostBasedOnProducts());

        // Add 2 products to cart
        page.refresh();
        Product metalMouse2ndProduct = new Product("9", "Practical Metal Mouse","9.99");
        metalMouse2ndProduct.addToCart();
        Product softPizza = new Product("7", "Gorgeous Soft Pizza","19.99");
        CartPage cart2ProductsDetails = new CartPage();
        page.getHeader().clickOnTheCartIcon();
        System.out.println("Products in cart: " + cart2ProductsDetails.getNumberOfDistinctProducts());
    }
}

// Add two products to card test
// 1. Open Application
// 2. Add Metal mouse product to cart.
// 3. Add Gorgeous Soft Pizza to cart.
// 4. Navigate to the Cart page.
// 5. Validate that we have 2 products in cart.
// 6. Validate the sum of the 2 products.


// 1. (Click on the Basket Plus icon on the Metal mouse  product) Add Product Metal mouse to Basket.
// 2. The Header basket Icon will show one item in basket
// 3. (Click on the Basket Icon from the Header) Navigate to Cart page.
// 4. One Metal mouse product is in the basket.
// 5. Metal mouse price is 9.99$
// 6. Verify total cart amount


//1. Open Application. // Validate greetings msg
//2. Click on the Login button.
//3. Enter Username in the username field.
//4. Enter Password in the password field.
//5. Click on the Login button.
//- Validate that we logged in with the correct user.