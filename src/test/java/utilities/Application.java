package utilities;

import constants.Timeouts;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Application {
    private static AppiumDriver appiumDriver;

    public Application() {
        createDriver();
    }

    private static void setImplicitlyWaitToDriver(Duration duration) {
        getDriver().manage().timeouts().implicitlyWait(duration.getSeconds(), TimeUnit.SECONDS);
    }

    private static AppiumDriver createDriver() {
        DriverSettings driverSettings = new DriverSettings();
        appiumDriver = new AppiumDriver(driverSettings.getConnectionURL(), driverSettings.getCapabilities());
        setImplicitlyWaitToDriver(Timeouts.getImplicitTimeout());
        return appiumDriver;
    }

    public static AppiumDriver getDriver() {
        if(appiumDriver == null) {
            appiumDriver = createDriver();
        }
        return appiumDriver;
    }

    public void quit() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
