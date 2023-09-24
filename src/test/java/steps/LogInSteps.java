package steps;

import constants.Titles;
import models.User;
import org.testng.Assert;
import screens.LoginScreen;

public class LogInSteps {

    private LogInSteps() {}

    public static void logIn(User user) {
        LoginScreen loginScreen = new LoginScreen();
        Assert.assertTrue(loginScreen.isScreenDisplayed(), "Login screen is not displayed");
        Assert.assertEquals(loginScreen.getTitle(), Titles.ALFA_TEST, "Alfa test title message is wrong");
        loginScreen.fillLogin(user.getLogin());
        loginScreen.fillPassword(user.getPassword());
        loginScreen.clickEnterButton();
    }
}
