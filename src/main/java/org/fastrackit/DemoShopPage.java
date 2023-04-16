package org.fastrackit;

public class DemoShopPage extends Page{
    private Header header;
    private Footer footer;

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
}
