package org.fastrackit.dataprovider;

import org.testng.annotations.DataProvider;


public class UserDataProvider {

    @DataProvider(name = "validUserDataProvider")
    public Object[][] feedUserDataProvider() {
        User beetleUser = new User("beetle", "choochoo");
        User dinoUser = new User("dino", "choochoo");
        User turtleUser = new User("turtle", "choochoo");

        return new Object[][] {
                {beetleUser},
                {dinoUser},
                {turtleUser}
        };
    }

    @DataProvider(name = "invalidUserDataProvider")
    public Object[][] feedInvalidUserDataProvider() {
        User lockedUser = new User("locked", "choochoo");
        lockedUser.setErrorMessage("The user has been locked out.");

        User invalidPass = new User("beetle", "choochoo1");
        invalidPass.setErrorMessage("Incorrect username or password!");

        User invalidUser = new User("test", "choochoo");
        invalidUser.setErrorMessage("Incorrect username or password!");

        return new Object[][] {
                {lockedUser},
                {invalidPass},
                {invalidUser}
        };
    }
}
