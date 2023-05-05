package org.fastrackit.search;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Feature;
import org.fastrackit.DemoShopPage;
import org.fastrackit.Footer;

import org.fastrackit.LoginModal;
import org.fastrackit.config.BaseTestConfig;
import org.fastrackit.dataprovider.KeyWords;
import org.fastrackit.dataprovider.KeyWordsDataProvider;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;


@Feature("Searchbar functionality")
public class SearchTest extends BaseTestConfig {
    DemoShopPage page;
    LoginModal loginModal;
    @BeforeMethod
    public void setup(){
        this.page = new DemoShopPage();
        page.openDemoShopApp();

        this.loginModal = new LoginModal();
    }
    @AfterMethod
    public void reset() {
        Selenide.refresh();
        page.getFooter().resetPage();
    }
    @Test(dataProviderClass = KeyWordsDataProvider.class, dataProvider = "searchKeywords")
    public void search_for_a_product_test_as_guest(KeyWords keyword){
        page.fillSearchBar(keyword.getFirstPart(),keyword.getSecondPart());
        page.submitSearch();

        assertEquals(page.productsDisplayed.size(),1,"One product found with the submitted keyword");
        String fullKeyword = (keyword.getFirstPart()+keyword.getSecondPart());
        page.productsDisplayed.get(0).shouldHave(Condition.text(fullKeyword));

    }

    @Test(dataProviderClass = KeyWordsDataProvider.class, dataProvider = "searchKeywords")
    public void search_for_a_product_test_as_logged_in_with_beetle_user(KeyWords keyword){
       loginModal.login("beetle","choochoo");

        page.fillSearchBar(keyword.getFirstPart(),keyword.getSecondPart());
        page.submitSearch();

        String fullKeyword = (keyword.getFirstPart()+keyword.getSecondPart());
        page.productsDisplayed.get(0).shouldHave(Condition.text(fullKeyword));
        assertEquals(page.productsDisplayed.size(),1,"One product found with the submitted keyword");
    }
    @Test(dataProviderClass = KeyWordsDataProvider.class, dataProvider = "searchKeywords")
    public void search_for_a_product_test_as_logged_in_with_dino_user(KeyWords keyword){

        loginModal.login("dino","choochoo");

        page.fillSearchBar(keyword.getFirstPart(),keyword.getSecondPart());
        page.submitSearch();


        String fullKeyword = (keyword.getFirstPart()+keyword.getSecondPart());
        page.productsDisplayed.get(0).shouldHave(Condition.text(fullKeyword));
        assertEquals(page.productsDisplayed.size(),1,"One product found with the submitted keyword");
    }
    @Test(dataProviderClass = KeyWordsDataProvider.class, dataProvider = "searchKeywords")
    public void search_for_a_product_test_as_logged_in_with_turtle_user(KeyWords keyword){

        loginModal.login("turtle","choochoo");

        page.fillSearchBar(keyword.getFirstPart(),keyword.getSecondPart());
        page.submitSearch();

        String fullKeyword = (keyword.getFirstPart()+keyword.getSecondPart());
        page.productsDisplayed.get(0).shouldHave(Condition.text(fullKeyword));
        assertEquals(page.productsDisplayed.size(),1,"One product found with the submitted keyword.Search successful!");
    }
}
