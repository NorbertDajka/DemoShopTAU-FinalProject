package org.fastrackit.Tests.login;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.User;
import org.fastrackit.dataprovider.UserDataProvider;
import org.fastrackit.Pages.DemoShopPage;
import org.fastrackit.Body.LoginModal;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("Login with valid and invalid credentials")
@Test(suiteName = "Login")
public class LoginTest extends BaseTestConfig {
    DemoShopPage page;
    LoginModal loginModal;

    @BeforeMethod
    public void setup() {
        page = new DemoShopPage();
        page.openDemoShopApp();

        this.loginModal = new LoginModal();
    }

    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "validUserDataProvider")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logging in with valid credentials verifying that greeting message appeared with user name")
    public void user_can_login_on_demoshop_page_with_valid_credentials(User user) {
        loginModal.login(user.getUsername(),user.getPassword());

        assertEquals(page.getHeader().getGreetingsMsg(), user.getExpectedGreetingsMsg(), "Greetings message is : " + user.getExpectedGreetingsMsg());
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "invalidUserDataProvider")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Loggin in with invalid user an error message should appear")
    public void user_cannot_login_with_invalid_credentials_on_demoshop_page(User user) {

        loginModal.login(user.getUsername(), user.getPassword());

        assertTrue(loginModal.isDisplayed());
        assertTrue(loginModal.isErrorMsgDisplayed(), "Login error message is displayed.");
        assertEquals(loginModal.getErrorMsg(), user.getErrorMessage());
        assertEquals(page.getHeader().getGreetingsMsg(), user.getDefaultGreetingsMsg(), "Greetings message is : Hello guest!");
    }
}
