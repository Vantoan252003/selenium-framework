package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.LoginPage;

public class LoginDDTTest extends BaseTest {

    @DataProvider(name = "smokeData")
    public Object[][] getSmokeData() {
        return new Object[][] {
            {ConfigReader.getInstance().getUsername(), ConfigReader.getInstance().getPassword(), "inventory", "Login voi standard user"},
            {"problem_user", ConfigReader.getInstance().getPassword(), "inventory", "Login voi problem user"},
            {"performance_glitch_user", ConfigReader.getInstance().getPassword(), "inventory", "Login voi glitch user"}
        };
    }

    @DataProvider(name = "negativeData")
    public Object[][] getNegativeData() {
        return new Object[][] {
            {ConfigReader.getInstance().getUsername(), "wrong_password", "Username and password do not match", "Wrong password"},
            {"locked_out_user", ConfigReader.getInstance().getPassword(), "locked out", "Locked user"},
            {"", ConfigReader.getInstance().getPassword(), "Username is required", "Empty username"},
            {ConfigReader.getInstance().getUsername(), "", "Password is required", "Empty password"}
        };
    }

    @Test(dataProvider = "smokeData", description = "Smoke test login - success cases")
    public void testSmokeLoginCases(String username, String password, String expected, String description) {
        LoginPage loginPage = new LoginPage(getDriver());
        if (expected.equals("inventory")) {
            Assert.assertTrue(loginPage.login(username, password).isLoaded(), description);
        }
    }

    @Test(dataProvider = "negativeData", description = "Negative test login - failure cases")
    public void testNegativeLoginCases(String username, String password, String expected, String description) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure(username, password);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error not displayed for: " + description);
    }
}
