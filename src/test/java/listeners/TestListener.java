package listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Application;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        screenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        screenshot();
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        screenshot();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        screenshot();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] screenshot() {
        return ((TakesScreenshot) Application.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}