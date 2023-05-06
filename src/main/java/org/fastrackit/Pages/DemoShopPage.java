package org.fastrackit.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.fastrackit.Body.Footer;
import org.fastrackit.Body.Header;
import org.fastrackit.Page;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DemoShopPage extends Page {
    private Header header;
    private Footer footer;
    private final SelenideElement searchBar =$("#input-search");
    private final SelenideElement searchButton =$(".col-md-auto .btn-light");
    public ElementsCollection productsDisplayed = $$(".row a");            ;


    public DemoShopPage() {
        this.header = new Header();
        this.footer = new Footer();
    }

    public Footer getFooter() {
        return footer;
    }

    public Header getHeader() {
        return header;
    }

    public void fillSearchBar(String keyword1,String keyword2) {this.searchBar.sendKeys(keyword1,keyword2);
    }
    public void submitSearch(){this.searchButton.click();}

    public List<String> getProductsDisplayedName() {
        return this.productsDisplayed.texts();
    }
public void clearSearch(){
        this.searchBar.clear();
}

}
