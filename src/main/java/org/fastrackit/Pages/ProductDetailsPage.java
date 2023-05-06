package org.fastrackit.Pages;

import com.codeborne.selenide.SelenideElement;
import org.fastrackit.Body.Page;

import static com.codeborne.selenide.Selenide.$;

public class ProductDetailsPage extends Page {

    private final SelenideElement addToBasketButton = $(".fa-cart-plus");

    public void clickOnTheAddToBasketButton() {
        this.addToBasketButton.click();
    }
}
