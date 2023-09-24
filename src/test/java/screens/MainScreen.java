package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Application;

public class MainScreen extends BaseScreen {
    private final WebElement successMsg = Application.getDriver().findElement(By.xpath("//*[contains(@text, 'Вход в Alfa-Test выполнен')]"));

    public MainScreen() {
        super("//*[contains(@resource-id, 'nav_host_fragment_content_main')]");
    }

    public boolean isSuccessMessageDisplayed() {
        return successMsg.isDisplayed();
    }

    public String getSuccessMessage() {
        return successMsg.getText();
    }
}
