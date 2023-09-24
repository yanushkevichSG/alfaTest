package helpers;

import constants.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Application;

public class Wait {
    private static final WebDriverWait wait = new WebDriverWait(Application.getDriver(), Timeouts.getDefaultTimeout());

    private Wait() {}

    public static void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeVisibleWithText(WebElement element) {
        wait.until(x -> !element.getText().isEmpty());
    }

    public static void waitForElementToBeInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
