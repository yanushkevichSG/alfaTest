package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.Application;

public abstract class BaseTest {
    private static Application application;

    @BeforeMethod()
    public void startTheApp() {
        application = new Application();
    }

    @AfterMethod
    public void closeTheApp() {
        application.quit();
    }
}
