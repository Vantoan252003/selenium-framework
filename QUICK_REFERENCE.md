# 🎯 Lab 9 - Quick Reference Guide

## 📍 Project Location
```
/Users/nguyenvantoan/IdeaProjects/Lab9
```

## 🚀 Quick Commands

### Compile & Build
```bash
cd /Users/nguyenvantoan/IdeaProjects/Lab9
mvn clean compile    # Compile only
mvn clean install    # Compile + package
```

### Generate Excel Test Data
```bash
mvn exec:java -Dexec.mainClass="framework.utils.CreateTestDataExcel"
# Creates: src/test/resources/testdata/login_data.xlsx
```

### Run All Tests
```bash
mvn test
```

### Run Smoke Tests (Parallel, 2 threads)
```bash
mvn test -Dsuites=src/test/resources/testng-smoke.xml
```

### Run Regression Tests (Parallel, 3 threads)
```bash
mvn test -Dsuites=src/test/resources/testng-regression.xml
```

### Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=UserLoginTest
mvn test -Dtest=FlakySimulationTest
```

### Run Tests with Specific Environment
```bash
mvn test -Denv=dev          # Uses config-dev.properties
mvn test -Denv=staging      # Uses config-staging.properties
```

### Run Single Test Method
```bash
mvn test -Dtest=LoginTest#testValidLogin
```

---

## 📁 Key Files Reference

### Framework Files
| File | Purpose |
|------|---------|
| `BasePage.java` | Explicit wait methods (7 methods) |
| `BaseTest.java` | ThreadLocal WebDriver setup |
| `ConfigReader.java` | Environment configuration |
| `LoginPage.java` | SauceDemo login page object |
| `InventoryPage.java` | SauceDemo inventory page object |
| `CartPage.java` | SauceDemo cart page object |
| `CheckoutPage.java` | SauceDemo checkout page object |

### Utility Files
| File | Purpose |
|------|---------|
| `ExcelReader.java` | Read Excel test data |
| `JsonReader.java` | Read JSON test data |
| `TestDataFactory.java` | Generate random data (Faker) |
| `RetryAnalyzer.java` | Automatic test retry |
| `RetryListener.java` | Auto-attach retry to tests |

### Test Files
| File | Test Count |
|------|-----------|
| `LoginTest.java` | 3 tests |
| `LoginDDTTest.java` | 2 tests (data-driven) |
| `CartTest.java` | 3 tests |
| `UserLoginTest.java` | 1 test (JSON-driven) |
| `CheckoutTest.java` | 1 test (Faker) |
| `FlakySimulationTest.java` | 1 test (retry demo) |

### Configuration Files
| File | Purpose |
|------|---------|
| `config-dev.properties` | Dev environment config |
| `config-staging.properties` | Staging environment config |
| `testng-smoke.xml` | Smoke test suite (2 threads) |
| `testng-regression.xml` | Full regression suite (3 threads) |

### Data Files
| File | Content |
|------|---------|
| `login_data.xlsx` | Excel test data (3 sheets, 12 rows) |
| `users.json` | JSON test data (5 users) |

---

## 🔍 Test Class Overview

### LoginTest (3 tests)
```
✅ testValidLogin() - Standard user successful login
✅ testLockedOutLogin() - Locked user error case
✅ testInvalidPasswordLogin() - Wrong password error
```

### LoginDDTTest (2 tests with data)
```
✅ testSmokeLoginCases() - 3 different valid users
✅ testNegativeLoginCases() - 4 invalid login scenarios
```

### CartTest (3 tests)
```
✅ testAddToCart() - Add 1 item to cart
✅ testAddMultipleProductsAndVerifyInCart() - Verify cart
✅ testRemoveItemFromCart() - Remove item from cart
```

### UserLoginTest (1 test from JSON)
```
✅ testLoginWithJson() - Tests 5 users from JSON
```

### CheckoutTest (1 test with Faker)
```
✅ testCheckoutWithRandomData() - Uses random data
```

### FlakySimulationTest (1 test)
```
✅ testFlakyScenario() - Demonstrates retry mechanism
```

---

## ⚙️ Configuration Values

### Development Environment
```properties
# config-dev.properties
base.url=https://www.saucedemo.com
browser=chrome
explicit.wait=15 seconds
implicit.wait=5 seconds
retry.count=1
screenshot.path=target/screenshots/
```

### Staging Environment
```properties
# config-staging.properties
base.url=https://staging.saucedemo.com
browser=chrome
explicit.wait=20 seconds
implicit.wait=8 seconds
retry.count=2
screenshot.path=target/screenshots/
```

---

## 🎯 Execution Examples

### Example 1: Run Smoke Tests on Dev Environment
```bash
mvn test -Dsuites=src/test/resources/testng-smoke.xml -Denv=dev
# Expected: 2 threads, LoginTest (3) + LoginDDTTest (2) = 5 parallel tests
# Environment: dev (explicit.wait=15, retry.count=1)
```

### Example 2: Run Full Regression on Staging
```bash
mvn test -Dsuites=src/test/resources/testng-regression.xml -Denv=staging
# Expected: 3 threads, all 6 test classes = 11 parallel tests
# Environment: staging (explicit.wait=20, retry.count=2)
```

### Example 3: Test Retry Mechanism
```bash
mvn test -Dtest=FlakySimulationTest -Denv=dev
# Expected: Test fails, retries 1 time (retry.count=1), finally passes
# Output: [Retry] Đang chạy lại lần 1 cho test: testFlakyScenario
```

### Example 4: Test with Excel Data
```bash
mvn test -Dtest=LoginDDTTest
# Expected: LoginDDTTest runs 6 times total
# - testSmokeLoginCases[0], [1], [2] (3 data rows)
# - testNegativeLoginCases[0], [1], [2], [3] (4 data rows)
```

---

## 📊 Code Structure Quick View

```
Lab9/
├── src/main/java/framework/
│   ├── base/           (2 files: BasePage, BaseTest)
│   ├── config/         (1 file: ConfigReader)
│   ├── pages/          (4 files: LoginPage, InventoryPage, CartPage, CheckoutPage)
│   └── utils/          (7 files: Readers, Factory, Retry, Screenshot)
│
├── src/test/java/tests/
│   └── (6 test classes with 11+ test methods)
│
├── src/test/resources/
│   ├── config-dev.properties
│   ├── config-staging.properties
│   ├── testng-smoke.xml
│   ├── testng-regression.xml
│   └── testdata/
│       ├── login_data.xlsx (3 sheets)
│       └── users.json (5 users)
│
├── pom.xml            (Maven configuration)
├── README.md          (Project documentation)
├── COMPLETE_IMPLEMENTATION.md
├── VERIFICATION_CHECKLIST.md
├── CODE_EXAMPLES.md
└── FINAL_SUMMARY.md
```

---

## ✅ Quality Checklist

Run these to verify project quality:

### 1. Check No Thread.sleep()
```bash
grep -r "Thread.sleep" src/
# Expected output: (empty - no results)
```

### 2. Check No Hardcoded URLs
```bash
grep -r "https://" src/test/
# Expected output: (empty - no results in tests)
```

### 3. Check No driver.findElement in Tests
```bash
grep -r "driver.findElement" src/test/java/tests/
# Expected output: (empty - no results)
```

### 4. Compile Check
```bash
mvn clean compile -q
echo $?
# Expected output: 0 (success)
```

### 5. Dependency Check
```bash
mvn dependency:tree | grep -E "selenium|testng|poi"
# Expected output: All dependencies listed
```

---

## 🐛 Troubleshooting

### Problem: "Cannot resolve symbol" errors
**Solution:** Run `mvn clean install` first to download dependencies

### Problem: Tests not running
**Solution:** Ensure you're in `/Users/nguyenvantoan/IdeaProjects/Lab9` directory

### Problem: Excel file not found
**Solution:** Run `mvn exec:java -Dexec.mainClass="framework.utils.CreateTestDataExcel"`

### Problem: Tests timeout (older methods)
**Solution:** This shouldn't happen - project uses explicit waits throughout

### Problem: Cross-thread failures
**Solution:** Project uses ThreadLocal - shouldn't happen. Restart IDE if needed.

### Problem: Config not loading
**Solution:** Make sure files are in `src/test/resources/` not `src/main/resources/`

---

## 📈 Test Execution Flow

```
Start Test Run
    ↓
Set System property (env)
    ↓
BaseTest.setUp() called
    ↓
Load ConfigReader (based on env)
    ↓
Initialize WebDriver (ThreadLocal)
    ↓
Navigate to baseUrl (from config)
    ↓
Execute Test Method
    ↓
If PASS → BaseTest.tearDown()
If FAIL → Take screenshot → BaseTest.tearDown()
    ↓
driver.quit() + tlDriver.remove()
    ↓
RetryListener checks: Should retry?
    ↓
If YES → Restart from setUp()
If NO → Mark test result (PASSED/FAILED)
    ↓
TestNG Report Updated
```

---

## 💡 Tips & Tricks

### Tip 1: Run Tests in GUI IDE
```bash
# Open project in IntelliJ IDEA
# Right-click on test class → Run
# Results shown in IDE with colors
```

### Tip 2: Debug a Single Test
```bash
mvn test -Dtest=LoginTest#testValidLogin -X
# -X shows debug output
```

### Tip 3: Change Implicit Wait
```bash
# Edit config-dev.properties
implicit.wait=10
mvn test
```

### Tip 4: Disable Screenshots
```bash
# Comment out takeScreenshot() call in BaseTest.tearDown()
```

### Tip 5: Run Tests Sequentially
```bash
# Edit testng-smoke.xml or testng-regression.xml
# Change: parallel="methods" thread-count="3"
# To: parallel="none"
mvn test -Dsuites=src/test/resources/testng-regression.xml
```

---

## 📚 Documentation Files

| File | Contains |
|------|----------|
| `README.md` | Project overview & structure |
| `COMPLETE_IMPLEMENTATION.md` | Detailed implementation details |
| `VERIFICATION_CHECKLIST.md` | Requirement verification |
| `CODE_EXAMPLES.md` | Code samples & best practices |
| `FINAL_SUMMARY.md` | Project completion summary |
| **THIS FILE** | Quick reference guide |

---

## 🎓 Learning Path

1. **Day 1:** Read README.md & FINAL_SUMMARY.md
2. **Day 2:** Study BasePage & BaseTest (CODE_EXAMPLES.md)
3. **Day 3:** Review Page Objects (LoginPage, etc.)
4. **Day 4:** Understand data-driven testing (Excel, JSON, Faker)
5. **Day 5:** Learn configuration management (ConfigReader)
6. **Day 6:** Study retry mechanism (RetryAnalyzer)
7. **Day 7:** Run all tests and review TestNG report

---

## 🚀 Next Steps

1. **Run Tests:** Start with smoke tests
2. **Review Results:** Check TestNG HTML report
3. **Study Code:** Read page objects and base classes
4. **Modify Data:** Change Excel/JSON data and re-run
5. **Extend:** Add more tests following the pattern

---

## 📞 Quick Support

**For issues with:**
- **Compilation:** Run `mvn clean install`
- **Tests not running:** Check test class in `src/test/java/tests/`
- **Configuration:** Check properties files in `src/test/resources/`
- **Data files:** Run Excel generator if missing
- **Dependencies:** Run `mvn dependency:tree`

---

**Last Updated:** March 31, 2026  
**Status:** ✅ Ready for Production  
**Maintenance:** Low (Self-documenting code)
