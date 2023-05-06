package org.fastrackit.Pages;

import com.codeborne.selenide.SelenideElement;
import org.fastrackit.Body.Page;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutSummaryPage extends Page {
    private final SelenideElement selectedPaymentMethod = $(".row > div:nth-of-type(1) > div:nth-of-type(2)");
    private final SelenideElement completeOrderButton = $("[href='#/checkout-complete']");

    public String getSelectedPaymentMethod() {
        return this.selectedPaymentMethod.text();
    }
    public void completeOrder(){
        this.completeOrderButton.click();
    }
}
