package org.fastrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductDetailsPage extends Page {

    private final SelenideElement addToBasketButton = $(".fa-cart-plus");

    public void clickOnTheAddToBasketButton() {
        this.addToBasketButton.click();
    }
}
