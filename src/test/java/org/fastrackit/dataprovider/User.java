package org.fastrackit.dataprovider;

public class User {

    private final String username;
    private final String password;
    private final String expectedGreetingsMsg;
    private final String defaultGreetingsMsg = "Hello guest!";
    private String errorMessage = "";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.expectedGreetingsMsg = "Hi " + username + "!";
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedGreetingsMsg() {
        return expectedGreetingsMsg;
    }

    public String getDefaultGreetingsMsg() {
        return defaultGreetingsMsg;
    }

    @Override
    public String toString() {
        return "Test run with: " + username;
    }
}
