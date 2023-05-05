package org.fastrackit.orders;

import org.fastrackit.DemoShopPage;
import org.fastrackit.config.BaseTestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CompleteOrderAsLoggedUser extends BaseTestConfig {
    @BeforeMethod
    public void setup(){
        DemoShopPage page = new DemoShopPage();
        page.openDemoShopApp();
    }

    

}
