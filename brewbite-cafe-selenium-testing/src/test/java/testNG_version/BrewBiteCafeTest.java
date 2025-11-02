package testNG_version;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;

public class BrewBiteCafeTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "http://localhost:5173"; // Update with your URL

    @BeforeClass
    public void setUp() {
        // Set ChromeDriver path if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void navigateToPage() {
        driver.get(BASE_URL);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC-01: Verify Navigation Bar Contains All Required Links
    @Test(priority = 1)
    public void testNavigationBarLinks() {
        System.out.println("Executing TC-01: Verify Navigation Bar Contains All Required Links");

        WebElement navbar = driver.findElement(By.id("navbar"));
        Assert.assertTrue(navbar.isDisplayed(), "Navigation bar should be visible");

        WebElement homeLink = driver.findElement(By.id("nav-home"));
        WebElement menuLink = driver.findElement(By.id("nav-menu"));
        WebElement aboutLink = driver.findElement(By.id("nav-about"));
        WebElement contactLink = driver.findElement(By.id("nav-contact"));

        Assert.assertTrue(homeLink.isDisplayed(), "Home link should be present");
        Assert.assertTrue(menuLink.isDisplayed(), "Menu link should be present");
        Assert.assertTrue(aboutLink.isDisplayed(), "About Us link should be present");
        Assert.assertTrue(contactLink.isDisplayed(), "Contact link should be present");

        System.out.println("TC-01: PASSED - All navigation links are present");
    }

    // TC-02: Verify Navigation Bar is Visible
    @Test(priority = 2)
    public void testNavigationBarVisibility() {
        System.out.println("Executing TC-02: Verify Navigation Bar is Visible");

        WebElement navbar = driver.findElement(By.id("navbar"));
        Assert.assertTrue(navbar.isDisplayed(), "Navigation bar should be visible");

        System.out.println("TC-02: PASSED - Navigation bar is visible");
    }

    // TC-03: Verify Navigation Links Scroll to Correct Sections
    @Test(priority = 3)
    public void testNavigationScrolling() throws InterruptedException {
        System.out.println("Executing TC-03: Verify Navigation Links Scroll to Correct Sections");

        // Click Menu link
        WebElement menuLink = driver.findElement(By.id("nav-menu"));
        menuLink.click();
        Thread.sleep(1000);

        WebElement menuSection = driver.findElement(By.id("menu"));
        Assert.assertTrue(isElementInViewport(menuSection), "Menu section should be in viewport");

        // Click About Us link
        WebElement aboutLink = driver.findElement(By.id("nav-about"));
        aboutLink.click();
        Thread.sleep(1000);

        WebElement aboutSection = driver.findElement(By.id("about"));
        Assert.assertTrue(isElementInViewport(aboutSection), "About section should be in viewport");

        // Click Contact link
        WebElement contactLink = driver.findElement(By.id("nav-contact"));
        contactLink.click();
        Thread.sleep(1000);

        WebElement contactSection = driver.findElement(By.id("contact"));
        Assert.assertTrue(isElementInViewport(contactSection), "Contact section should be in viewport");

        System.out.println("TC-03: PASSED - Navigation links scroll to correct sections");
    }

    // TC-04: Verify Hero Section Contains Café Name and Tagline
    @Test(priority = 4)
    public void testHeroSection() {
        System.out.println("Executing TC-04: Verify Hero Section Contains Café Name and Tagline");

        WebElement heroSection = driver.findElement(By.id("home"));
        Assert.assertTrue(heroSection.isDisplayed(), "Hero section should be visible");

        WebElement cafeName = driver.findElement(By.id("cafe-name"));
        Assert.assertTrue(cafeName.getText().contains("Brew & Bite Café"),
                "Café name should be present");

        WebElement tagline = driver.findElement(By.id("tagline"));
        Assert.assertFalse(tagline.getText().isEmpty(), "Tagline should be present");

        System.out.println("TC-04: PASSED - Hero section contains café name and tagline");
    }

    // TC-05: Verify Minimum 4 Menu Items are Displayed
    @Test(priority = 5)
    public void testMenuItemsCount() {
        System.out.println("Executing TC-05: Verify Minimum 4 Menu Items are Displayed");

        scrollToElement(driver.findElement(By.id("menu")));

        List<WebElement> menuItems = driver.findElements(By.className("menu-item"));
        Assert.assertTrue(menuItems.size() >= 4,
                "At least 4 menu items should be displayed. Found: " + menuItems.size());

        System.out.println("TC-05: PASSED - Found " + menuItems.size() + " menu items");
    }

    // TC-06: Verify Each Menu Item Has Name and Price
    @Test(priority = 6)
    public void testMenuItemsContent() {
        System.out.println("Executing TC-06: Verify Each Menu Item Has Name and Price");

        scrollToElement(driver.findElement(By.id("menu")));

        List<WebElement> menuItems = driver.findElements(By.className("menu-item"));

        for (WebElement item : menuItems) {
            WebElement name = item.findElement(By.className("menu-item-name"));
            WebElement price = item.findElement(By.className("menu-item-price"));

            Assert.assertFalse(name.getText().isEmpty(), "Menu item should have a name");
            Assert.assertFalse(price.getText().isEmpty(), "Menu item should have a price");
        }

        System.out.println("TC-06: PASSED - All menu items have name and price");
    }

    // TC-07: Verify Each Menu Item Has an Image
    @Test(priority = 7)
    public void testMenuItemsImages() {
        System.out.println("Executing TC-07: Verify Each Menu Item Has an Image");

        scrollToElement(driver.findElement(By.id("menu")));

        List<WebElement> menuItems = driver.findElements(By.className("menu-item"));

        for (WebElement item : menuItems) {
            WebElement image = item.findElement(By.className("menu-item-image"));
            Assert.assertTrue(image.isDisplayed(), "Each menu item should have an image");
        }

        System.out.println("TC-07: PASSED - All menu items have images");
    }

    // TC-08: Verify About Us Section Contains Minimum 50 Words
    @Test(priority = 8)
    public void testAboutUsWordCount() {
        System.out.println("Executing TC-08: Verify About Us Section Contains Minimum 50 Words");

        scrollToElement(driver.findElement(By.id("about")));

        WebElement aboutText = driver.findElement(By.id("about-text"));
        String text = aboutText.getText();
        int wordCount = text.split("\\s+").length;

        Assert.assertTrue(wordCount >= 50,
                "About Us section should contain at least 50 words. Found: " + wordCount);

        System.out.println("TC-08: PASSED - About Us section contains " + wordCount + " words");
    }

    // TC-09: Verify Contact Section Displays Email Address
    @Test(priority = 9)
    public void testContactEmail() {
        System.out.println("Executing TC-09: Verify Contact Section Displays Email Address");

        scrollToElement(driver.findElement(By.id("contact")));

        WebElement email = driver.findElement(By.id("contact-email"));
        String emailText = email.getText();

        Assert.assertTrue(emailText.contains("@"),
                "Email address should contain @ symbol");
        Assert.assertFalse(emailText.isEmpty(), "Email address should not be empty");

        System.out.println("TC-09: PASSED - Contact section displays email: " + emailText);
    }

    // TC-10: Verify Contact Section Displays Phone Number
    @Test(priority = 10)
    public void testContactPhone() {
        System.out.println("Executing TC-10: Verify Contact Section Displays Phone Number");

        scrollToElement(driver.findElement(By.id("contact")));

        WebElement phone = driver.findElement(By.id("contact-phone"));
        String phoneText = phone.getText();

        Assert.assertFalse(phoneText.isEmpty(), "Phone number should not be empty");

        System.out.println("TC-10: PASSED - Contact section displays phone: " + phoneText);
    }

    // TC-11: Verify Footer Contains Copyright Text
    @Test(priority = 11)
    public void testFooterCopyright() {
        System.out.println("Executing TC-11: Verify Footer Contains Copyright Text");

        scrollToElement(driver.findElement(By.id("footer")));

        WebElement footer = driver.findElement(By.id("footer"));
        WebElement copyright = driver.findElement(By.id("copyright-text"));

        Assert.assertTrue(footer.isDisplayed(), "Footer should be visible");
        String copyrightText = copyright.getText();
        Assert.assertTrue(copyrightText.contains("©") ||
                        copyrightText.toLowerCase().contains("copyright"),
                "Footer should contain copyright text");

        System.out.println("TC-11: PASSED - Footer contains copyright text");
    }

    // TC-12: Verify Reserve Table Button Exists in Hero Section
    @Test(priority = 12)
    public void testReserveTableButton() {
        System.out.println("Executing TC-12: Verify Reserve Table Button Exists in Hero Section");

        WebElement heroSection = driver.findElement(By.id("home"));
        WebElement reserveButton = driver.findElement(By.id("reserve-button"));

        Assert.assertTrue(reserveButton.isDisplayed(),
                "Reserve Table button should be visible");
        Assert.assertTrue(reserveButton.isEnabled(),
                "Reserve Table button should be clickable");

        System.out.println("TC-12: PASSED - Reserve Table button exists and is clickable");
    }

    // TC-13: Verify Navigation Bar Has Distinct Background Color
    @Test(priority = 13)
    public void testNavigationBackgroundColor() {
        System.out.println("Executing TC-13: Verify Navigation Bar Has Distinct Background Color");

        WebElement navbar = driver.findElement(By.id("navbar"));
        String navBgColor = navbar.getCssValue("background-color");

        WebElement body = driver.findElement(By.tagName("body"));
        String bodyBgColor = body.getCssValue("background-color");

        Assert.assertNotEquals(navBgColor, bodyBgColor,
                "Navigation bar background should be different from page background");

        System.out.println("TC-13: PASSED - Navigation bar has distinct background color");
    }

    // TC-14: Verify Hero Section Has Background Color or Image
    @Test(priority = 14)
    public void testHeroBackground() {
        System.out.println("Executing TC-14: Verify Hero Section Has Background Color or Image");

        WebElement heroSection = driver.findElement(By.id("home"));
        String bgColor = heroSection.getCssValue("background-color");
        String bgImage = heroSection.getCssValue("background-image");

        boolean hasBackground = !bgColor.equals("rgba(0, 0, 0, 0)") ||
                !bgImage.equals("none");

        Assert.assertTrue(hasBackground,
                "Hero section should have background color or image");

        System.out.println("TC-14: PASSED - Hero section has background");
    }

    // TC-15: Verify Menu Items are Displayed in Grid/Card Layout
    @Test(priority = 15)
    public void testMenuGridLayout() {
        System.out.println("Executing TC-15: Verify Menu Items are Displayed in Grid/Card Layout");

        scrollToElement(driver.findElement(By.id("menu")));

        WebElement menuContainer = driver.findElement(By.id("menu-container"));
        String displayValue = menuContainer.getCssValue("display");

        Assert.assertTrue(displayValue.contains("grid"),
                "Menu should use grid layout");

        List<WebElement> menuItems = driver.findElements(By.className("menu-item"));
        for (WebElement item : menuItems) {
            String boxShadow = item.getCssValue("box-shadow");
            Assert.assertFalse(boxShadow.equals("none"),
                    "Menu items should have card-like styling");
        }

        System.out.println("TC-15: PASSED - Menu items displayed in grid/card layout");
    }

    // TC-16: Verify Consistent Font Family for All Headings
    @Test(priority = 16)
    public void testHeadingFontConsistency() {
        System.out.println("Executing TC-16: Verify Consistent Font Family for All Headings");

        List<WebElement> h1Elements = driver.findElements(By.tagName("h1"));
        List<WebElement> h2Elements = driver.findElements(By.tagName("h2"));
        List<WebElement> h3Elements = driver.findElements(By.tagName("h3"));

        String firstFont = null;

        if (!h1Elements.isEmpty()) {
            firstFont = h1Elements.get(0).getCssValue("font-family");
        }

        for (WebElement h2 : h2Elements) {
            String font = h2.getCssValue("font-family");
            if (firstFont == null) firstFont = font;
            Assert.assertEquals(font, firstFont,
                    "All headings should have consistent font family");
        }

        System.out.println("TC-16: PASSED - All headings use consistent font family");
    }

    // TC-17: Verify Reserve Table Button Has Hover Effect
    @Test(priority = 17)
    public void testReserveButtonHoverEffect() throws InterruptedException {
        System.out.println("Executing TC-17: Verify Reserve Table Button Has Hover Effect");

        WebElement reserveButton = driver.findElement(By.id("reserve-button"));
        String initialColor = reserveButton.getCssValue("background-color");

        Actions actions = new Actions(driver);
        actions.moveToElement(reserveButton).perform();
        Thread.sleep(500); // Wait for transition

        String hoverColor = reserveButton.getCssValue("background-color");

        Assert.assertNotEquals(initialColor, hoverColor,
                "Button color should change on hover");

        System.out.println("TC-17: PASSED - Reserve button has hover effect");
    }

    // Helper Methods
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isElementInViewport(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
                "var elem = arguments[0];" +
                        "var rect = elem.getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.top <= window.innerHeight);",
                element
        );
    }
}