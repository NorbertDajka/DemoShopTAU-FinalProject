package org.fastrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutInfoPage extends Page {

    private final SelenideElement firstNameField = $("#first-name");
    private final SelenideElement lastNameField = $("#last-name");
    private final SelenideElement addressField = $("#address");

    private final SelenideElement creditCardRadioButton = $("div:nth-of-type(2) > .form-check-input");
    private final SelenideElement payPalRadioButton = $("div:nth-of-type(3) > .form-check-input");
    private final SelenideElement continueButton = $(".btn-success");

    public void fillFirstNameForm(String firstName) {
        this.firstNameField.sendKeys(firstName);
    }

    public void fillLastNameForm(String lastName) {
        this.lastNameField.sendKeys(lastName);
    }

    public void fillAdressForm(String address) {
        this.addressField.sendKeys(address);
    }

    public void selectCreditCardPaymentMethod() {
        this.creditCardRadioButton.click();
    }
    public void selectPayPalPaymentMethod() {
        this.payPalRadioButton.click();
    }

    public void clickOnContinueCheckoutButton() {
        this.continueButton.click();
    }

    public void fillCheckoutInfoAndSubmit(String firstname, String lastname, String adress) {
        this.fillFirstNameForm(firstname);
        this.fillLastNameForm(lastname);
        this.fillAdressForm(adress);
        this.clickOnContinueCheckoutButton();
    }
}
