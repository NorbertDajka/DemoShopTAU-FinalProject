package org.fastrackit.dataprovider;

import org.fastrackit.config.BaseTestConfig;

public class KeyWords extends BaseTestConfig {
    private final String firstPart;
    private final String secondPart;

    public KeyWords(String firstPart, String secondPart) {
        this.firstPart = firstPart;
        this.secondPart = secondPart;
    }

    public String getFirstPart() {
        return firstPart;
    }

    public String getSecondPart() {
        return secondPart;
    }
}
