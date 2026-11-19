package com.oipolloi.automation.config;

public final class Config {
    private Config() {}

    public static String baseUrl() {
        return System.getProperty("baseUrl", "https://oipolloi.com").replaceAll("/+$", "");
    }

    public static String browser() {
        return System.getProperty("browser", "chrome").toLowerCase();
    }

    public static boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    public static long timeoutSeconds() {
        return Long.parseLong(System.getProperty("timeoutSeconds", "12"));
    }
}
