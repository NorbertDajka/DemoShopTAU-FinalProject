package org.fastrackit.Tests.orders;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.Body.Header;
import org.fastrackit.Body.LoginModal;
import org.fastrackit.Pages.*;
import org.fastrackit.Product.Product;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.ProductsDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
@Test(suiteName = "Order completion")
@Feature("Logged in order completion - Turtle user")
public class CompleteOrderAsTurtleUserTest extends BaseTestConfig {

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

        loginModal.login("turtle","choochoo");
    }
    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }
    @Test(dataProviderClass = ProductsDataProvider.class,dataProvider = "allProducts")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Completing and order as turtle user selecting cash payment")
    public void completing_an_order_as_turtle_user_with_cash_on_delivery_payment_option(Product product){

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
    @Severity(SeverityLevel.CRITICAL)
    @Description("Completing and order as turtle user selecting card payment")
    public void completing_an_order_as_turtle_user_with_credit_card_payment_option(Product product){

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
    @Severity(SeverityLevel.CRITICAL)
    @Description("Completing and order as turtle user selecting paypal payment")
    public void completing_an_order_as_turtle_user_with_paypal_payment_option(Product product){

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
