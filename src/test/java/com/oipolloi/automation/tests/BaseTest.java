package com.oipolloi.automation.tests;

import com.oipolloi.automation.driver.DriverFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.createDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            saveFailureScreenshot(result.getName());
        }
        DriverFactory.quitDriver();
    }

    private void saveFailureScreenshot(String testName) {
        try {
            Path directory = Path.of("target", "failure-screenshots");
            Files.createDirectories(directory);
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            Path destination = directory.resolve(testName + "-" + timestamp + ".png");
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(destination, screenshot);
            System.err.println("Failure screenshot saved: " + destination.toAbsolutePath());
        } catch (IOException | RuntimeException e) {
            System.err.println("Could not save failure screenshot: " + e.getMessage());
        }
    }
}
