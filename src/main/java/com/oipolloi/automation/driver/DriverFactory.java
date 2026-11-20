package com.oipolloi.automation.driver;

import com.oipolloi.automation.config.Config;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {}

    public static WebDriver createDriver() {
        if (DRIVER.get() != null) {
            quitDriver();
        }

        WebDriver driver;

        switch (Config.browser()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments(
                        "--disable-notifications",
                        "--disable-popup-blocking",
                        "--window-size=1440,1000",
                        "--no-sandbox",
                        "--disable-dev-shm-usage"
                );

                if (Config.headless()) {
                    options.addArguments("--headless=new");
                }

                driver = new ChromeDriver(options);
            }

            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();

                if (Config.headless()) {
                    options.addArguments("-headless");
                }

                driver = new FirefoxDriver(options);
            }

            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + Config.browser()
            );
        }

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ZERO);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));

            if (!Config.headless()) {
                driver.manage().window().setSize(new Dimension(1440, 1000));
            }

            DRIVER.set(driver);
            return driver;

        } catch (RuntimeException e) {
            driver.quit();
            throw e;
        }
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized for this thread.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                DRIVER.remove();
            }
        }
    }
}
