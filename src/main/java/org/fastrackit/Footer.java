package org.fastrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Footer {
    private final SelenideElement resetButton = $(".fa-undo");

    public void resetPage(){
        this.resetButton.click();
    }
}
