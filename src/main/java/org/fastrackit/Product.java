package org.fastrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Product {

    private final SelenideElement productCard;
    private final SelenideElement productLink;
    private final SelenideElement addToBasketButton;
    private final SelenideElement addToFavoritesButton;

    private final SelenideElement removeFromWishListButton;
    private String name;
    private String price;

    public Product(String productId, String name, String price) {
        String selector = "[href='#/product/" + productId + "']"; // this also works.
        String productSelector = String.format("[href='#/product/%s']", productId);
        this.productLink = $(productSelector);
        this.productCard = this.productLink.parent().parent();
        this.addToBasketButton = productCard.$(".fa-cart-plus");
        this.addToFavoritesButton = productCard.$("div:nth-of-type(6) [data-icon='heart']");
        this.removeFromWishListButton = productCard.$(".fa-heart-broken");
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void clickOnTheProductLink() {
        productLink.click();
    }

    public void addToCart() {
        System.out.println("Clicked on the " + addToBasketButton + " on " + name);
        this.addToBasketButton.scrollTo();
        this.addToBasketButton.click();
    }
    public void addToWishlist() {
        this.addToFavoritesButton.scrollTo();
        this.addToFavoritesButton.click();
    }

    public boolean isRemoveFromWishlistButtonAppeared() {
        return this.removeFromWishListButton.isDisplayed() && this.removeFromWishListButton.exists();
    }
    @Override
    public String toString() {
        return name;
    }
}
