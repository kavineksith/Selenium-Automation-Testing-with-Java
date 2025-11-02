# ☕ Brew & Bite Café – Automated Testing Project

## 📋 Overview
This project demonstrates end-to-end automated testing for the **Brew & Bite Café** landing page using **Selenium WebDriver**.  
It includes three test implementation approaches:
1. **Plain Java (Basic Selenium Tests)**
2. **JUnit 5 (Framework-based Tests)**
3. **TestNG (Suite-based Tests)**

The project also includes a **React landing page** used as the web application under test.

---

## 🗂 Project Structure

```
BrewBite_Cafe_Testing/
│
├── cafe-landing-page/              # React landing page
│   ├── src/
│   ├── public/
│   └── package.json
│
├── cafe-selenium-tests/            # Selenium automation module
│   ├── pom.xml
│   ├── src/
│   │   ├── main/java/
│   │   │   └── com/brewbite/pages/         # Page Object Model classes
│   │   └── test/java/
│   │       └── com/brewbite/tests/
│   │           ├── PlainJavaTests/         # Plain Java test files
│   │           ├── JUnitTests/             # JUnit 5 tests
│   │           └── TestNGTests/            # TestNG tests
│   └── src/test/resources/
│       └── testng.xml                      # TestNG suite file
│
├── Screenshots/                    # Test evidence screenshots
├── Reports/                        # Execution and assessment reports
└── test_data.json                  # Input data for automation
```

---

## ⚙️ Environment Setup

### Prerequisites
- **Node.js v18+** (for React app)
- **Java 11+**
- **Maven 3.8+**
- **Google Chrome** browser installed

---

## 🧠 Section 1 – React Landing Page

### 🏗 Setup and Run the Web Application
1. Navigate to the landing page directory:
   ```bash
   cd cafe-landing-page
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```
4. Access the page at:
   ```
   http://localhost:5173/
   ```

The Selenium tests will target this local URL.

---

## 🧪 Section 2 – Selenium Automated Testing

### Common Setup
1. Navigate to the automation directory:
   ```bash
   cd cafe-selenium-tests
   ```
2. Build dependencies:
   ```bash
   mvn clean compile
   ```
3. Make sure Chrome is available and up to date.

---

### ✅ Option 1: Plain Java Test Execution
Basic Selenium tests implemented using only `Selenium WebDriver` (no testing framework).

**Location:**  
`src/test/java/com/brewbite/tests/PlainJavaTests/`

**Run Command:**
```bash
mvn exec:java -Dexec.mainClass="com.brewbite.tests.PlainJavaTests.LandingPageTest"
```

**Notes:**
- Demonstrates simple navigation, validation, and interaction.
- Ideal for foundational understanding before framework integration.

---

### ✅ Option 2: JUnit 5 Test Execution
Framework-based tests using **JUnit Jupiter** annotations.

**Location:**  
`src/test/java/com/brewbite/tests/JUnitTests/`

**Run Command:**
```bash
mvn -Dtest=com.brewbite.tests.JUnitTests.* test
```

**Features:**
- Uses `@BeforeEach`, `@AfterEach`, and `@Test`.
- Includes assertions with `Assertions` class.
- Automatically managed browser setup and teardown.

---

### ✅ Option 3: TestNG Test Execution
Suite-based tests using **TestNG**.

**Location:**  
`src/test/java/com/brewbite/tests/TestNGTests/`  
`src/test/resources/testng.xml`

**Run Command:**
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

**Sample `testng.xml`:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="BrewBiteTestSuite">
    <test name="LandingPageTests">
        <classes>
            <class name="com.brewbite.tests.TestNGTests.LandingPageTest"/>
        </classes>
    </test>
</suite>
```

**Features:**
- Parallel execution and grouping support.
- Best suited for test suites and regression sets.

---

## ⚠️ Troubleshooting

| Issue | Cause | Solution |
|--------|--------|-----------|
| ChromeDriver error | Mismatch with browser version | Ensure Chrome is updated; WebDriverManager handles most cases automatically |
| Page not loading | React app not running | Run `npm run dev` before executing tests |
| Test not found | Incorrect class or package reference | Check package names and update `testng.xml` or `mvn` command accordingly |
