package org.fastrackit.Pages;

import com.codeborne.selenide.SelenideElement;
import org.fastrackit.Body.Page;

import static com.codeborne.selenide.Selenide.$;

public class CompleteOrderPage extends Page {
    private final SelenideElement orderCompletionMessage = $(".text-center");

    public String getOrderCompletionMessage() {
        return this.orderCompletionMessage.text();
    }
}
