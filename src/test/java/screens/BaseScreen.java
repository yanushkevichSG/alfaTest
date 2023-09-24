package screens;

import helpers.Wait;
import org.openqa.selenium.By;
import utilities.Application;

public abstract class BaseScreen {
    private final String screenLabelXpath;
    private final String progressBarClassName = "android.widget.ProgressBar";

    protected BaseScreen() {
        this.screenLabelXpath = "//*[contains(@resource-id, 'tvTitle')]";
    }

    protected BaseScreen(String elementPath) {
        this.screenLabelXpath = elementPath;
    }

    public boolean isScreenDisplayed() {
        return Application.getDriver().findElement(By.xpath(screenLabelXpath)).isDisplayed();
    }

    public void waitForProgressBarDissapear() {
        Wait.waitForElementToBeInvisible(Application.getDriver().findElement(By.className(progressBarClassName)));
    }
}
