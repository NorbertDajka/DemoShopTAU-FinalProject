package org.fastrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginModal {
    private SelenideElement modal = $(".modal-content");
    private SelenideElement userField = $("#user-name");
    private SelenideElement passwordField = $("#password");
    private SelenideElement submitButton = $("[type=submit]");
    private SelenideElement errorMsg = $(".error");

    public void fillInUsername(String user) {
        userField.sendKeys(user);
    }

    public void fillInPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSubmitButton() {
        submitButton.click();
        System.out.println("Clicked on the " + submitButton);
    }

    public boolean isErrorMsgDisplayed() {
        return this.errorMsg.exists() && this.errorMsg.isDisplayed();
    }

    public String getErrorMsg() {
        return errorMsg.text();
    }

    public boolean isDisplayed() {
        return this.modal.exists() && this.modal.isDisplayed();
    }
}

