package org.fastrackit.dataprovider;

import org.testng.annotations.DataProvider;

public class KeyWordsDataProvider {
    @DataProvider(name = "searchKeywords")
    public Object[][] feedAllKeywordsData() {
        KeyWords awesomeGranite = new KeyWords("awesome ", "granite");
        KeyWords awesomeMetal = new KeyWords("awesome ", "metal");
        KeyWords softShirt = new KeyWords("soft ", "shirt");
        KeyWords softPizza = new KeyWords("soft ", "pizza");
        KeyWords concreteHat = new KeyWords("concrete ", "hat");

        return new Object[][]{
                {awesomeGranite}, {awesomeMetal}, {softShirt}, {softPizza}, {concreteHat}

        };
    }
}
