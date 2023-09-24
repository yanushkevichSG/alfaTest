package screens;

import helpers.Wait;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Application;

public class LoginScreen extends BaseScreen {
    private final WebElement title = Application.getDriver().findElement(By.id("tvTitle"));
    private final WebElement loginInput = Application.getDriver().findElement(By.id("etUsername"));
    private final WebElement passwordInput = Application.getDriver().findElement(By.id("etPassword"));
    private final WebElement enterBtn = Application.getDriver().findElement(By.id("btnConfirm"));
    private final WebElement errorMsg = Application.getDriver().findElement(By.id("tvError"));

    public String getTitle() {
        return title.getText();
    }

    @Step("Fill login with login name : {0}")
    public void fillLogin(String login) {
        loginInput.clear();
        loginInput.sendKeys(login);
    }

    @Step("Fill password with password : {0}")
    public void fillPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public String getEnteredPassword() {
        return passwordInput.getAttribute("text");
    }

    @Step("Click on enter button")
    public void clickEnterButton() {
        enterBtn.click();
    }

    @Step("Getting error message")
    public String getErrorMessage() {
        Wait.waitForElementToBeVisibleWithText(errorMsg);
        return errorMsg.getText();
    }
}
