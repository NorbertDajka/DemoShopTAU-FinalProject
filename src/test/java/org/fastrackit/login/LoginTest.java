package org.fastrackit.login;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.User;
import org.fastrackit.dataprovider.UserDataProvider;
import org.fastrackit.DemoShopPage;
import org.fastrackit.LoginModal;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("User Login")
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
    public void userCanLoginOnDemoShopPage(User user) {
        loginModal.login(user.getUsername(),user.getPassword());

        assertEquals(page.getHeader().getGreetingsMsg(), user.getExpectedGreetingsMsg(), "Greetings message is : " + user.getExpectedGreetingsMsg());
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "invalidUserDataProvider")
    public void userCanNotLoginOnDemoShopPageWithInvalidUser(User user) {

        loginModal.login(user.getUsername(), user.getPassword());

        assertTrue(loginModal.isDisplayed());
        assertTrue(loginModal.isErrorMsgDisplayed(), "Login error message is displayed.");
        assertEquals(loginModal.getErrorMsg(), user.getErrorMessage());
        assertEquals(page.getHeader().getGreetingsMsg(), user.getDefaultGreetingsMsg(), "Greetings message is : Hello guest!");
    }
}
