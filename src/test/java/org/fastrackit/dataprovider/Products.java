package org.fastrackit.dataprovider;

public class Products {

    private final String name;
    private final String price;
    private final String productID;

    public Products(String name, String price, String productID) {
        this.name = name;
        this.price = price;
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getProductID() {
        return productID;
    }
}
