package org.fastrackit.dataprovider;

import org.testng.annotations.DataProvider;

public class ProductsDataProvider {

    @DataProvider(name = "allProducts")
    public Object[][] feedAllProductsData() {
        Products awesomeGranitChips = new Products("Awesome Granite Chips","15.99","1");

        return new Object[][] {
                {awesomeGranitChips},

        };
    }


}
