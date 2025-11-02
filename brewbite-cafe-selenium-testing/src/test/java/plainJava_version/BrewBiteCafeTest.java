package plainJava_version;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

/**
 * Plain Java Selenium Tests - No Testing Framework Required
 * Run this as a regular Java application
 */
public class BrewBiteCafeTest {

    private static WebDriver driver;
    private static final String BASE_URL = "http://localhost:5173"; // Update with your URL
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("BREW & BITE CAFÉ - SELENIUM TEST EXECUTION");
        System.out.println("=".repeat(70));
        System.out.println();

        try {
            setUp();
            runAllTests();
            tearDown();
            printTestSummary();
        } catch (Exception e) {
            System.out.println("Fatal Error: " + e.getMessage());
            e.printStackTrace();
            if (driver != null) {
                driver.quit();
            }
        }
    }

    // Setup Method
    public static void setUp() {
        System.out.println("Setting up WebDriver...");
        // Set ChromeDriver path if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("WebDriver setup complete!\n");
    }

    // Teardown Method
    public static void tearDown() {
        if (driver != null) {
            System.out.println("\nClosing browser...");
            driver.quit();
            System.out.println("Browser closed.\n");
        }
    }

    // Run All Tests
    public static void runAllTests() {
        testNavigationBarLinks();
        testNavigationBarVisibility();
        testNavigationScrolling();
        testHeroSection();
        testMenuItemsCount();
        testMenuItemsContent();
        testMenuItemsImages();
        testAboutUsWordCount();
        testContactEmail();
        testContactPhone();
        testFooterCopyright();
        testReserveTableButton();
        testNavigationBackgroundColor();
        testHeroBackground();
        testMenuGridLayout();
        testHeadingFontConsistency();
        testReserveButtonHoverEffect();
    }

    // Print Test Summary
    public static void printTestSummary() {
        System.out.println("=".repeat(70));
        System.out.println("TEST EXECUTION SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("Total Tests: " + (passedTests + failedTests));
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
        System.out.println("Success Rate: " +
                String.format("%.2f", (passedTests * 100.0 / (passedTests + failedTests))) + "%");
        System.out.println("=".repeat(70));
    }

    // Navigate to page before each test
    public static void navigateToPage() {
        driver.get(BASE_URL);
    }

    // TC-01: Verify Navigation Bar Contains All Required Links
    public static void testNavigationBarLinks() {
        navigateToPage();
        System.out.println("TC-01: Verify Navigation Bar Contains All Required Links");

        try {
            WebElement navbar = driver.findElement(By.id("navbar"));
            assertTrue(navbar.isDisplayed(), "Navigation bar should be visible");

            WebElement homeLink = driver.findElement(By.id("nav-home"));
            WebElement menuLink = driver.findElement(By.id("nav-menu"));
            WebElement aboutLink = driver.findElement(By.id("nav-about"));
            WebElement contactLink = driver.findElement(By.id("nav-contact"));

            assertTrue(homeLink.isDisplayed(), "Home link should be present");
            assertTrue(menuLink.isDisplayed(), "Menu link should be present");
            assertTrue(aboutLink.isDisplayed(), "About Us link should be present");
            assertTrue(contactLink.isDisplayed(), "Contact link should be present");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-02: Verify Navigation Bar is Visible
    public static void testNavigationBarVisibility() {
        navigateToPage();
        System.out.println("TC-02: Verify Navigation Bar is Visible");

        try {
            WebElement navbar = driver.findElement(By.id("navbar"));
            assertTrue(navbar.isDisplayed(), "Navigation bar should be visible");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-03: Verify Navigation Links Scroll to Correct Sections
    public static void testNavigationScrolling() {
        navigateToPage();
        System.out.println("TC-03: Verify Navigation Links Scroll to Correct Sections");

        try {
            // Click Menu link
            WebElement menuLink = driver.findElement(By.id("nav-menu"));
            menuLink.click();
            Thread.sleep(1000);

            WebElement menuSection = driver.findElement(By.id("menu"));
            assertTrue(isElementInViewport(menuSection), "Menu section should be in viewport");

            // Click About Us link
            WebElement aboutLink = driver.findElement(By.id("nav-about"));
            aboutLink.click();
            Thread.sleep(1000);

            WebElement aboutSection = driver.findElement(By.id("about"));
            assertTrue(isElementInViewport(aboutSection), "About section should be in viewport");

            // Click Contact link
            WebElement contactLink = driver.findElement(By.id("nav-contact"));
            contactLink.click();
            Thread.sleep(1000);

            WebElement contactSection = driver.findElement(By.id("contact"));
            assertTrue(isElementInViewport(contactSection), "Contact section should be in viewport");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-04: Verify Hero Section Contains Café Name and Tagline
    public static void testHeroSection() {
        navigateToPage();
        System.out.println("TC-04: Verify Hero Section Contains Café Name and Tagline");

        try {
            WebElement heroSection = driver.findElement(By.id("home"));
            assertTrue(heroSection.isDisplayed(), "Hero section should be visible");

            WebElement cafeName = driver.findElement(By.id("cafe-name"));
            assertTrue(cafeName.getText().contains("Brew & Bite Café"),
                    "Café name should be present");

            WebElement tagline = driver.findElement(By.id("tagline"));
            assertFalse(tagline.getText().isEmpty(), "Tagline should be present");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-05: Verify Minimum 4 Menu Items are Displayed
    public static void testMenuItemsCount() {
        navigateToPage();
        System.out.println("TC-05: Verify Minimum 4 Menu Items are Displayed");

        try {
            scrollToElement(driver.findElement(By.id("menu")));

            List<WebElement> menuItems = driver.findElements(By.className("menu-item"));
            assertTrue(menuItems.size() >= 4,
                    "At least 4 menu items should be displayed. Found: " + menuItems.size());

            System.out.println("Found " + menuItems.size() + " menu items");
            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-06: Verify Each Menu Item Has Name and Price
    public static void testMenuItemsContent() {
        navigateToPage();
        System.out.println("TC-06: Verify Each Menu Item Has Name and Price");

        try {
            scrollToElement(driver.findElement(By.id("menu")));

            List<WebElement> menuItems = driver.findElements(By.className("menu-item"));

            for (WebElement item : menuItems) {
                WebElement name = item.findElement(By.className("menu-item-name"));
                WebElement price = item.findElement(By.className("menu-item-price"));

                assertFalse(name.getText().isEmpty(), "Menu item should have a name");
                assertFalse(price.getText().isEmpty(), "Menu item should have a price");
            }

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-07: Verify Each Menu Item Has an Image
    public static void testMenuItemsImages() {
        navigateToPage();
        System.out.println("TC-07: Verify Each Menu Item Has an Image");

        try {
            scrollToElement(driver.findElement(By.id("menu")));

            List<WebElement> menuItems = driver.findElements(By.className("menu-item"));

            for (WebElement item : menuItems) {
                WebElement image = item.findElement(By.className("menu-item-image"));
                assertTrue(image.isDisplayed(), "Each menu item should have an image");
            }

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-08: Verify About Us Section Contains Minimum 50 Words
    public static void testAboutUsWordCount() {
        navigateToPage();
        System.out.println("TC-08: Verify About Us Section Contains Minimum 50 Words");

        try {
            scrollToElement(driver.findElement(By.id("about")));

            WebElement aboutText = driver.findElement(By.id("about-text"));
            String text = aboutText.getText();
            int wordCount = text.split("\\s+").length;

            assertTrue(wordCount >= 50,
                    "About Us section should contain at least 50 words. Found: " + wordCount);

            System.out.println("Word count: " + wordCount);
            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-09: Verify Contact Section Displays Email Address
    public static void testContactEmail() {
        navigateToPage();
        System.out.println("TC-09: Verify Contact Section Displays Email Address");

        try {
            scrollToElement(driver.findElement(By.id("contact")));

            WebElement email = driver.findElement(By.id("contact-email"));
            String emailText = email.getText();

            assertTrue(emailText.contains("@"), "Email address should contain @ symbol");
            assertFalse(emailText.isEmpty(), "Email address should not be empty");

            System.out.println("Email: " + emailText);
            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-10: Verify Contact Section Displays Phone Number
    public static void testContactPhone() {
        navigateToPage();
        System.out.println("TC-10: Verify Contact Section Displays Phone Number");

        try {
            scrollToElement(driver.findElement(By.id("contact")));

            WebElement phone = driver.findElement(By.id("contact-phone"));
            String phoneText = phone.getText();

            assertFalse(phoneText.isEmpty(), "Phone number should not be empty");

            System.out.println("Phone: " + phoneText);
            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-11: Verify Footer Contains Copyright Text
    public static void testFooterCopyright() {
        navigateToPage();
        System.out.println("TC-11: Verify Footer Contains Copyright Text");

        try {
            scrollToElement(driver.findElement(By.id("footer")));

            WebElement footer = driver.findElement(By.id("footer"));
            WebElement copyright = driver.findElement(By.id("copyright-text"));

            assertTrue(footer.isDisplayed(), "Footer should be visible");
            String copyrightText = copyright.getText();
            assertTrue(copyrightText.contains("©") ||
                            copyrightText.toLowerCase().contains("copyright"),
                    "Footer should contain copyright text");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-12: Verify Reserve Table Button Exists in Hero Section
    public static void testReserveTableButton() {
        navigateToPage();
        System.out.println("TC-12: Verify Reserve Table Button Exists in Hero Section");

        try {
            WebElement heroSection = driver.findElement(By.id("home"));
            WebElement reserveButton = driver.findElement(By.id("reserve-button"));

            assertTrue(reserveButton.isDisplayed(), "Reserve Table button should be visible");
            assertTrue(reserveButton.isEnabled(), "Reserve Table button should be clickable");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-13: Verify Navigation Bar Has Distinct Background Color
    public static void testNavigationBackgroundColor() {
        navigateToPage();
        System.out.println("TC-13: Verify Navigation Bar Has Distinct Background Color");

        try {
            WebElement navbar = driver.findElement(By.id("navbar"));
            String navBgColor = navbar.getCssValue("background-color");

            WebElement body = driver.findElement(By.tagName("body"));
            String bodyBgColor = body.getCssValue("background-color");

            assertNotEquals(navBgColor, bodyBgColor,
                    "Navigation bar background should be different from page background");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-14: Verify Hero Section Has Background Color or Image
    public static void testHeroBackground() {
        navigateToPage();
        System.out.println("TC-14: Verify Hero Section Has Background Color or Image");

        try {
            WebElement heroSection = driver.findElement(By.id("home"));
            String bgColor = heroSection.getCssValue("background-color");
            String bgImage = heroSection.getCssValue("background-image");

            boolean hasBackground = !bgColor.equals("rgba(0, 0, 0, 0)") || !bgImage.equals("none");

            assertTrue(hasBackground, "Hero section should have background color or image");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-15: Verify Menu Items are Displayed in Grid/Card Layout
    public static void testMenuGridLayout() {
        navigateToPage();
        System.out.println("TC-15: Verify Menu Items are Displayed in Grid/Card Layout");

        try {
            scrollToElement(driver.findElement(By.id("menu")));

            WebElement menuContainer = driver.findElement(By.id("menu-container"));
            String displayValue = menuContainer.getCssValue("display");

            assertTrue(displayValue.contains("grid"), "Menu should use grid layout");

            List<WebElement> menuItems = driver.findElements(By.className("menu-item"));
            for (WebElement item : menuItems) {
                String boxShadow = item.getCssValue("box-shadow");
                assertFalse(boxShadow.equals("none"), "Menu items should have card-like styling");
            }

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-16: Verify Consistent Font Family for All Headings
    public static void testHeadingFontConsistency() {
        navigateToPage();
        System.out.println("TC-16: Verify Consistent Font Family for All Headings");

        try {
            List<WebElement> h1Elements = driver.findElements(By.tagName("h1"));
            List<WebElement> h2Elements = driver.findElements(By.tagName("h2"));

            String firstFont = null;

            if (!h1Elements.isEmpty()) {
                firstFont = h1Elements.get(0).getCssValue("font-family");
            }

            for (WebElement h2 : h2Elements) {
                String font = h2.getCssValue("font-family");
                if (firstFont == null) firstFont = font;
                assertEquals(font, firstFont, "All headings should have consistent font family");
            }

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // TC-17: Verify Reserve Table Button Has Hover Effect
    public static void testReserveButtonHoverEffect() {
        navigateToPage();
        System.out.println("TC-17: Verify Reserve Table Button Has Hover Effect");

        try {
            WebElement reserveButton = driver.findElement(By.id("reserve-button"));
            String initialColor = reserveButton.getCssValue("background-color");

            Actions actions = new Actions(driver);
            actions.moveToElement(reserveButton).perform();
            Thread.sleep(500);

            String hoverColor = reserveButton.getCssValue("background-color");

            assertNotEquals(initialColor, hoverColor, "Button color should change on hover");

            System.out.println("Result: PASSED ✓\n");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Result: FAILED ✗");
            System.out.println("Error: " + e.getMessage() + "\n");
            failedTests++;
        }
    }

    // ===== HELPER METHODS =====

    private static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isElementInViewport(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
                "var elem = arguments[0];" +
                        "var rect = elem.getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.top <= window.innerHeight);",
                element
        );
    }

    // Custom assertion methods
    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertEquals(String actual, String expected, String message) {
        if (!actual.equals(expected)) {
            throw new AssertionError(message + " Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void assertNotEquals(String actual, String expected, String message) {
        if (actual.equals(expected)) {
            throw new AssertionError(message + " Both values are: " + actual);
        }
    }
}
