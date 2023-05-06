package org.fastrackit.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.fastrackit.Body.Page;
import org.fastrackit.Product.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage extends Page {

    private List<Product> productsInCart = new ArrayList<>();
    private final ElementsCollection distinctProducts = $$(".row a");
    private final SelenideElement cartTotalAmount = $(".amount-total .amount");
    private final SelenideElement checkoutButton = $("[href='#/checkout-info']");


    public int getNumberOfDistinctProducts() {
        return distinctProducts.size();
    }

    public int getTotalProductsInCart() {
        int totalProducts = 0;
        for (SelenideElement product : distinctProducts) {
            SelenideElement row = product.parent().parent();
            SelenideElement div = row.$("div");
            String numberOfProductsFromType = div.text();
            totalProducts += Integer.parseInt(numberOfProductsFromType);
        }
        return totalProducts;
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public double getTotalCartCostBasedOnProducts() {
        double totalCartCost = 0.0;
        for (SelenideElement product : distinctProducts) {
            SelenideElement row = product.parent().parent();

            String productPrice = row.$(".col-md-auto", 1).text().replace("$", "");
            double pricePerProduct = Double.parseDouble(productPrice);

            String numberOfProductsFromType = row.$("div").text().replace("$", "");
            double productsFromType = Double.parseDouble(numberOfProductsFromType);

            totalCartCost += (productsFromType * pricePerProduct);
        }
        NumberFormat format = new DecimalFormat("#0.00");
        return Double.parseDouble(format.format(totalCartCost));
    }

    public double getTotalCartAmount() {
        String totalWithoutCurrency = cartTotalAmount.text().replace("$", "");
        return Double.parseDouble(totalWithoutCurrency);
    }
    public void checkout(){
        this.checkoutButton.click();
    }
}
