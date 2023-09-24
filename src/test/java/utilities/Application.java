package utilities;

import constants.Timeouts;
import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Application {
    private static AppiumDriver appiumDriver;

    public Application(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.setImplicitlyWaitToDriver(Timeouts.getImplicitTimeout());
    }

    private void setImplicitlyWaitToDriver(Duration duration) {
        this.getDriver().manage().timeouts().implicitlyWait(duration.getSeconds(), TimeUnit.SECONDS);
    }

    public static AppiumDriver getDriver() {
        return appiumDriver;
    }

    public void quit() {
        if (this.getDriver() != null) {
            this.getDriver().quit();
        }
    }
}
