package org.fastrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WishlistPage extends Page{

    private final SelenideElement wishListProductCard = $("div .card");
    private final SelenideElement wishListProductCardTitle = $("div .card .card-link");

    Product product;

    public WishlistPage(Product product) {
        this.product = product;
    }

    public String getWishListProductCardName() {
        return wishListProductCardTitle.text();
    }
    public String getInWishlistProductName(){return product.getName();
    }
    public boolean isDisplayedAndExists(){
        return wishListProductCard.isDisplayed()&& wishListProductCard.exists();

    }
}
