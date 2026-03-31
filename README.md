# Lab 9 - Selenium Framework with POM & Data-Driven Testing

## 📋 Cấu trúc Project
[![Test Status](https://github.com/{user}/{repo}/actions/workflows/selenium-full.yml/badge.svg)](https://github.com/{user}/{repo}/actions)
[![Allure Report](https://img.shields.io/badge/Allure-Report-orange)](https://{user}.github.io/{repo}/)
```
Lab9/
├── src/
│   ├── main/java/framework/
│   │   ├── base/
│   │   │   ├── BasePage.java          ✅ Explicit waits, fluent interface
│   │   │   └── BaseTest.java          ✅ ThreadLocal driver, parallel execution
│   │   ├── config/
│   │   │   └── ConfigReader.java      ✅ Singleton, multi-environment support
│   │   ├── pages/
│   │   │   ├── LoginPage.java         ✅ POM, fluent interface
│   │   │   ├── InventoryPage.java     ✅ POM
│   │   │   ├── CartPage.java          ✅ POM
│   │   │   └── CheckoutPage.java      ✅ POM
│   │   └── utils/
│   │       ├── ExcelReader.java       ✅ Read Excel test data
│   │       ├── JsonReader.java        ✅ Read JSON test data
│   │       ├── UserData.java          ✅ POJO with Jackson
│   │       ├── TestDataFactory.java   ✅ Java Faker for random data
│   │       ├── RetryAnalyzer.java     ✅ Flaky test retry
│   │       ├── RetryListener.java     ✅ Auto-attach retry
│   │       ├── ScreenshotUtil.java    ✅ Screenshot on failure
│   │       └── CreateTestDataExcel.java ✅ Generate Excel files
│   └── test/
│       ├── java/tests/
│       │   ├── LoginTest.java         ✅ 3 test cases
│       │   ├── LoginDDTTest.java      ✅ Data-Driven (2 providers)
│       │   ├── CartTest.java          ✅ 3 test cases
│       │   ├── UserLoginTest.java     ✅ JSON data provider
│       │   ├── CheckoutTest.java      ✅ TestDataFactory usage
│       │   └── FlakySimulationTest.java ✅ Retry simulation
│       └── resources/
│           ├── config-dev.properties      ✅ Development config
│           ├── config-staging.properties  ✅ Staging config
│           ├── testng-smoke.xml           ✅ Smoke suite (parallel)
│           ├── testng-regression.xml      ✅ Regression suite (parallel)
│           └── testdata/
│               ├── login_data.xlsx        ✅ Excel test data (3 sheets)
│               └── users.json             ✅ JSON user data (5 users)
└── pom.xml                             ✅ All dependencies configured

```

## ✅ Yêu cầu Bài Tập - Hoàn Thành

### Bài 1: BasePage & BaseTest
- ✅ `BasePage` với 7 methods (explicit waits, không Thread.sleep)
- ✅ `BaseTest` với ThreadLocal<WebDriver>
- ✅ Screenshot trên failure
- ✅ Hỗ trợ chạy song song (parallel)

### Bài 2: Page Object Model
- ✅ LoginPage, InventoryPage, CartPage, CheckoutPage
- ✅ Fluent Interface (@FindBy, trả về Page Object)
- ✅ 6+ test methods (LoginTest + CartTest)

### Bài 3: Data-Driven Testing với Excel
- ✅ ExcelReader với getCellValue() xử lý 4 kiểu cell
- ✅ login_data.xlsx (3 sheets: SmokeCases, NegativeCases, BoundaryCases)
- ✅ LoginDDTTest với @DataProvider

### Bài 4: JSON + Java Faker
- ✅ JsonReader, UserData POJO
- ✅ users.json (5 users)
- ✅ UserLoginTest với JSON @DataProvider
- ✅ TestDataFactory (Faker sinh random data)
- ✅ CheckoutTest sử dụng TestDataFactory

### Bài 5: ConfigReader + Multi-Environment
- ✅ ConfigReader Singleton
- ✅ config-dev.properties (explicit.wait=15)
- ✅ config-staging.properties (explicit.wait=20)
- ✅ System.setProperty("env", env) trong BaseTest.setUp()

### Bài 6: RetryAnalyzer + Flaky Test
- ✅ RetryAnalyzer implements IRetryAnalyzer
- ✅ RetryListener implements IAnnotationTransformer
- ✅ FlakySimulationTest (fail 2x, pass 3x)
- ✅ Đọc retry.count từ ConfigReader

### Bài 7: Refactor & Best Practices
- ✅ 0 `Thread.sleep()` trong cả project
- ✅ Tất cả locators ở Page Objects
- ✅ Tất cả test data từ file config/Excel/JSON
- ✅ `getDriver()` từ BaseTest, không new WebDriver trực tiếp
- ✅ Parallel execution support

## 🚀 Chạy Tests

### Smoke Tests (Parallel, 2 threads)
```bash
mvn test -Dsuites=src/test/resources/testng-smoke.xml
```

### Regression Tests (Parallel, 3 threads)
```bash
mvn test -Dsuites=src/test/resources/testng-regression.xml
```

### Chỉ định môi trường
```bash
mvn test -Denv=staging
# Output: "[ConfigReader] Đang dùng môi trường: staging"
```

### Chạy một test class
```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=UserLoginTest
mvn test -Dtest=FlakySimulationTest
```

## 📊 Metrics

| Mục | Số lượng |
|-----|---------|
| Test Classes | 6 |
| Test Methods | 15+ |
| Page Objects | 4 |
| Utility Classes | 8 |
| Excel Sheets | 3 |
| Test Data Records | 12+ |
| Configuration Files | 2 |
| Java Files (total) | 25+ |

## 🔒 Quality Checks

✅ **0 Thread.sleep()** - Tất cả dùng Explicit Wait  
✅ **No hardcoded URLs** - Đọc từ config-*.properties  
✅ **No driver.findElement() in tests** - Tất cả trong Page Objects  
✅ **ThreadLocal WebDriver** - An toàn cho parallel execution  
✅ **Singleton ConfigReader** - One instance per JVM  
✅ **Fluent Interface** - Chaining method calls  
✅ **Automatic Screenshot** - On test failure  
✅ **Automatic Retry** - Via RetryListener  
✅ **Multi-Environment** - Dev + Staging configs  

## 📝 Notes

- File Excel được sinh động bởi `CreateTestDataExcel.java` → `mvn exec:java`
- TestNG parallel execution: `parallel="methods" thread-count="3"`
- Retry tự động cho tất cả @Test via `RetryListener`
- Screenshot lưu ở `target/screenshots/` với timestamp

## 👨‍💻 Author
Generated for Lab 9 - Selenium Testing Framework Course

---
**Last Updated:** March 2026
