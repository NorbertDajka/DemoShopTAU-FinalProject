package org.fastrackit;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class Page {
    String demoShopUrl = "https://fasttrackit-test.netlify.app/#/";
    public Page() {
        System.out.println("Opened a new page.");
    }

    public void openDemoShopApp() {

        open(demoShopUrl);
    }

    public void refresh() {
        Selenide.refresh();
    }
}
