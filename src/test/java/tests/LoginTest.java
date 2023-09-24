package tests;

import constants.ErrorMessage;
import constants.Titles;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MainScreen;
import steps.LogInSteps;

public class LoginTest extends BaseTest {
    private final User defaultUser = User.defaultUser();
    private final User invalidUser = User.invalidUser();


    @Test
    public void validLoginTest() {
        LogInSteps.logIn(defaultUser);
        MainScreen mainScreen = new MainScreen();
        Assert.assertTrue(mainScreen.isScreenDisplayed(), "Main screen is not displayed");
        Assert.assertTrue(mainScreen.isSuccessMessageDisplayed(), "Success message is not displayed");
        Assert.assertEquals(mainScreen.getSuccessMessage(), Titles.ALFA_TEST_SUCCESS,"Success message is wrong");
    }

    @Test
    public void invalidLoginTest() {
        LogInSteps.logIn(invalidUser);
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.waitForProgressBarDissapear();
        Assert.assertEquals(loginScreen.getErrorMessage(), ErrorMessage.WRONG_DATA);
    }
}
