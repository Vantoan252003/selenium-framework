package framework.base;

import framework.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    @BeforeMethod
    @Parameters({"browser", "env"})
    public void setUp(@Optional("chrome") String browser, @Optional("dev") String env) {
        System.setProperty("env", env);
        ConfigReader config = ConfigReader.getInstance();

        String configBrowser = config.getBrowser();
        if(configBrowser != null && !configBrowser.isEmpty()) {
            browser = configBrowser;
        }

        tlDriver.set(DriverFactory.createDriver(browser));

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
        getDriver().get(config.getBaseUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }

    private void takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String destination = ConfigReader.getInstance().getScreenshotPath() + testName + "_" + timestamp + ".png";
        try {
            File destFile = new File(destination);
            destFile.getParentFile().mkdirs();
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved to => " + destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
