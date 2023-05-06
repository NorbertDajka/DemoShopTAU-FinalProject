package org.fastrackit.Pages;

import com.codeborne.selenide.SelenideElement;
import org.fastrackit.Body.Page;
import org.fastrackit.Product.Product;

import static com.codeborne.selenide.Selenide.$;

public class WishlistPage extends Page {

    private final SelenideElement wishListProductCard = $("div .card");
    private final SelenideElement wishListProductCardTitle = $("div .card .card-link");
    private final SelenideElement wishListProductAddToCartButton =$("div .card .fa-cart-plus");

    Product product;

    public WishlistPage(Product product) {
        this.product = product;
    }

    public String getWishListProductCardName() {
        return wishListProductCardTitle.text();
    }
    public String getInWishlistProductName(){return product.getName();
    }
    public void clickRemoveFromWishlistButton() {
        this.product.clickOnRemoveFromWishlistButton();
    }
    public Product getProduct() {
        return product;
    }

    public boolean productIsDisplayedAndExists(){
        return wishListProductCard.isDisplayed()&& wishListProductCard.exists();
    }
    public void addProductToCart(Product product){
        this.wishListProductAddToCartButton.click();

    }
}
