package tests;

import constants.ErrorMessage;
import io.qameta.allure.Issue;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import screens.LoginScreen;
import utilities.Fake;

public class FieldValidationTest extends BaseTest {
    private final User defaultUser = User.defaultUser();
    private final String maxLengthLogin = Fake.generateLetters(65);
    private final String maxLengthPassword = Fake.generateNumber(65);

    @Test
    @Issue("link")
    @Ignore
    public void loginValidationWithInvalidLoginFieldTest() {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.fillLogin(Fake.notValidLoginDataRegex());
        Assert.assertEquals(loginScreen.getErrorMessage(), ErrorMessage.EXCEPT_VALUE);
    }

    @Test
    public void loginWithOnlyLoginTest() {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.fillLogin(defaultUser.getLogin());
        loginScreen.clickEnterButton();
        Assert.assertEquals(loginScreen.getErrorMessage(), ErrorMessage.WRONG_DATA);
    }

    @Test
    public void loginWithOnlyPasswordTest() {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.fillPassword(defaultUser.getPassword());
        loginScreen.clickEnterButton();
        Assert.assertEquals(loginScreen.getErrorMessage(), ErrorMessage.WRONG_DATA);
    }

    @Test
    @Issue("link")
    @Ignore
    public void loginMaxLengthTest() {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.fillLogin(maxLengthLogin);
        loginScreen.fillPassword(defaultUser.getPassword());
        Assert.assertEquals(loginScreen.getErrorMessage(), ErrorMessage.MAX_LENGTH_LOGIN);
    }

    @Test
    @Issue("link")
    @Ignore
    public void passwordMaxLengthTest() {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.fillLogin(defaultUser.getLogin());
        loginScreen.fillPassword(maxLengthPassword);
        Assert.assertEquals(loginScreen.getEnteredPassword().length(), 50);
        Assert.assertEquals(loginScreen.getErrorMessage(), ErrorMessage.MAX_LENGTH_PASSWORD);
    }
}
