package org.fastrackit;

public class ProductInCart {
    private int quantity;
    private double totalPrice;
    private double unitPrice;
    private String productName;

    public ProductInCart(Product product, int quantity) {
        this.productName = product.getName();
        this.unitPrice = Double.parseDouble(product.getPrice());
        this.totalPrice = unitPrice * quantity;
        this.quantity = quantity;
    }
}
