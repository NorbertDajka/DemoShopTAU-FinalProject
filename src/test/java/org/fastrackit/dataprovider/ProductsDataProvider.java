package org.fastrackit.dataprovider;

import org.fastrackit.Product;
import org.testng.annotations.DataProvider;

public class ProductsDataProvider {

    @DataProvider(name = "allProducts")
    public Object[][] feedAllProductsData() {
        Product product1 = new Product("1", "Awesome Granite Chips", "15.99");
        Product product2 = new Product("2","Incredible Concrete Hat", "7.99");
        Product product3 = new Product("3","Awesome Metal Chair", "15.99");
        Product product4 = new Product("4","Practical Wooden Bacon", "29.99");
        Product product5 = new Product("5", "Awesome Soft Shirt", "29.99");
        Product product6 = new Product("6","Practical Wooden Bacon", "1.99");
        Product product7 = new Product("7","Practical Metal Mouse", "9.99");
        Product product8 = new Product("8","Licensed Steel Gloves", "14.99");
        Product product9 = new Product("9","Gorgeous Soft Pizza", "19.99");
        Product product10 = new Product("0","Refined Frozen Mouse", "9.99");


        return new Object[][] {
                {product1},{product2},{product3},{product4},{product5},{product6},{product7},{product8},{product9},{product10}
        };
    }


}
