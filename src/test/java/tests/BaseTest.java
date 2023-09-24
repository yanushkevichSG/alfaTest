package tests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.Application;
import utilities.DriverSettings;

public abstract class BaseTest {
    private static Application application;
    private static AndroidDriver driver;

    @BeforeMethod()
    public void startTheApp() {
        DriverSettings driverSettings = new DriverSettings();
        driver = new AndroidDriver(driverSettings.getConnectionURL(), driverSettings.getCapabilities());
        application = new Application(driver);
    }

    @AfterMethod
    public void closeTheApp() {
        application.quit();
    }
}
