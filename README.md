# Oi Polloi UI Automation

Selenium WebDriver + Java 21 + Maven + TestNG project using the Page Object Model (POM) for public storefront checks on [oipolloi.com](https://oipolloi.com/).

## Included coverage

The suite contains 16 UI tests covering:

1. Homepage loads and has a meaningful title.
2. Brand/logo link is visible and navigates home.
3. Free standard global shipping announcement is visible.
4. Shop navigation/dropdown is present and usable.
5. Clothing navigation from the Shop menu.
6. Footwear navigation from the Shop menu.
7. Accessories navigation from the Shop menu.
8. Brands navigation/dropdown and brand directory link.
9. Search control opens and is visible.
10. Search submits a product keyword and displays results.
11. Cart control opens the cart view/drawer.
12. Latest collection navigation.
13. Product cards are visible in the latest collection.
14. Story/Deck~Out navigation is available.
15. Latest collection shows product pricing.
16. Latest collection main content and product links are available.

The live storefront is a third-party site and can change its theme, content, or navigation. Locators are centralized in page objects. If Oi Polloi updates its Shopify theme, update the corresponding page-object locator rather than changing test assertions.

## Prerequisites

- JDK 21
- Apache Maven 3.9.9 or newer (Maven 3.9.x recommended)
- Google Chrome installed for local execution
- Internet access

Selenium Manager (bundled with Selenium 4) resolves the matching browser driver automatically. No separate ChromeDriver download or WebDriverManager dependency is required.

## Run

Run headed Chrome locally:

```bash
mvn clean test
```

Run headless Chrome:

```bash
mvn clean test -Pci
```

Override browser, URL, headless mode, or explicit wait timeout:

```bash
mvn clean test -Pci -Dbrowser=chrome -DbaseUrl=https://oipolloi.com -Dheadless=true -DtimeoutSeconds=20
```

Supported browser values in this starter: `chrome` and `firefox`.

Run a specific TestNG group:

```bash
mvn clean test -Pci -Dgroups=smoke
```

Run a single test class:

```bash
mvn clean test -Pci -Dtest=NavigationTests
```

## Project structure

```text
oipolloi-selenium-java/
├── .github/workflows/ui-tests.yml
├── src/
│   ├── main/java/com/oipolloi/automation/
│   │   ├── config/Config.java
│   │   ├── driver/DriverFactory.java
│   │   └── pages/
│   │       ├── BasePage.java
│   │       ├── HomePage.java
│   │       ├── CollectionPage.java
│   │       └── SearchPage.java
│   └── test/
│       ├── java/com/oipolloi/automation/tests/
│       │   ├── BaseTest.java
│       │   ├── HomePageTests.java
│       │   ├── NavigationTests.java
│       │   ├── SearchTests.java
│       │   └── ShoppingExperienceTests.java
│       └── resources/testng.xml
└── pom.xml
```

## Design and engineering practices

- **POM:** All selectors and browser interactions are in page objects. Test classes contain TestNG assertions.
- **Explicit waits:** Uses `WebDriverWait` and Selenium expected conditions; no fixed sleeps.
- **Thread-safe drivers:** `ThreadLocal<WebDriver>` supports TestNG parallel methods.
- **Isolation:** Each test gets a fresh browser session.
- **Selenium Manager:** Avoids pinned, stale driver binaries.
- **No real transactions:** Tests do not place orders or submit payment.
- **Resilient navigation:** Tests assert destination URL/content rather than relying on exact page titles where the storefront title may change.
- **Diagnostics:** Failed tests capture a screenshot under `target/failure-screenshots/`.

## Notes about live-site testing

This project performs read-only storefront navigation and search. Search results and collection inventory can vary by location, time, availability, cookies, and A/B experiments. The suite intentionally avoids customer account creation, cart mutations that may persist, and checkout/payment actions.

The homepage's observed navigation includes Shop All/Latest, Clothing, Footwear, Accessories, Brands, Search, Cart, and Deck~Out. See the current live site before adjusting any selectors.

## CI

GitHub Actions runs headless Chrome on pushes, pull requests, and manual dispatch. Maven dependencies are cached. Test reports and failure screenshots are uploaded even when a test fails.

Add this project to a GitHub repository and push the files to enable the workflow.
