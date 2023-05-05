package org.fastrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CompleteOrderPage extends Page{
    private final SelenideElement orderCompletionMessage = $(".text-center");

    public String getOrderCompletionMessage() {
        return this.orderCompletionMessage.text();
    }
}
