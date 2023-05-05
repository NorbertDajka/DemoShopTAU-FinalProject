package org.fastrackit.orders;

import com.codeborne.selenide.Selenide;
import org.fastrackit.*;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.ProductsDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CompleteOrderAsDinoUserTest extends BaseTestConfig {

    DemoShopPage page;
    Header header;
    CartPage cartPage;
    CheckoutInfoPage checkout;
    CheckoutSummaryPage summaryPage;
    CompleteOrderPage completeOrderPage;
    LoginModal loginModal;
    @BeforeMethod
    public void setup(){
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.header = new Header();
        this.cartPage = new CartPage();
        this.checkout = new CheckoutInfoPage();
        this.summaryPage = new CheckoutSummaryPage();
        this.completeOrderPage = new CompleteOrderPage();
        this.loginModal = new LoginModal();

        loginModal.login("dino","choochoo");
    }
    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }
    @Test(dataProviderClass = ProductsDataProvider.class,dataProvider = "allProducts")
    public void completing_an_order_as_beetle_user_with_cash_on_delivery_payment_option(Product product){

        product.addToCart();

        header.clickOnTheCartIcon();
        assertEquals(cartPage.getTotalProductsInCart(),1,"There is one product in cart!");

        cartPage.checkout();
        checkout.fillCheckoutInfoAndSubmit("John","Connor","Baker Street 221B");

        assertEquals(summaryPage.getSelectedPaymentMethod(),"Cash on delivery","payment method selection OK");

        summaryPage.completeOrder();

        assertEquals(completeOrderPage.getOrderCompletionMessage(),"Thank you for your order!","Order Comepleted with success");
    }

    @Test(dataProviderClass = ProductsDataProvider.class,dataProvider = "allProducts")
    public void completing_an_order_as_beetle_user_with_credit_card_payment_option(Product product){

        product.addToCart();

        header.clickOnTheCartIcon();

        assertEquals(cartPage.getTotalProductsInCart(),1,"There is one product in cart!");

        cartPage.checkout();
        checkout.fillCheckoutInfoAndSubmit("John","Connor","Baker Street 221B");

        assertEquals(summaryPage.getSelectedPaymentMethod(),"Credit card","payment method selection OK");

        summaryPage.completeOrder();

        assertEquals(completeOrderPage.getOrderCompletionMessage(),"Thank you for your order!","Order Comepleted with success");

    }
    @Test(dataProviderClass = ProductsDataProvider.class,dataProvider = "allProducts")
    public void completing_an_order_as_beetle_user_with_paypal_payment_option(Product product){

        product.addToCart();
        header.clickOnTheCartIcon();

        assertEquals(cartPage.getTotalProductsInCart(),1,"There is one product in cart!");

        cartPage.checkout();
        checkout.fillCheckoutInfoAndSubmit("John","Connor","Baker Street 221B");

        assertEquals(summaryPage.getSelectedPaymentMethod(),"PayPal","payment method selection OK");

        summaryPage.completeOrder();

        assertEquals(completeOrderPage.getOrderCompletionMessage(),"Thank you for your order!","Order Comepleted with success");
    }
}
