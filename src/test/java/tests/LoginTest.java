package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login(ConfigReader.getInstance().getUsername(), ConfigReader.getInstance().getPassword());
        Assert.assertTrue(inventoryPage.isLoaded(), "Trang Inventory không được tải thành công.");
    }

    @Test
    public void testLockedOutLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("locked_out_user", ConfigReader.getInstance().getPassword());
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Lỗi không hiển thị.");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"), "Sai thông báo lỗi.");
    }

    @Test
    public void testInvalidPasswordLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure(ConfigReader.getInstance().getUsername(), "wrong_pass");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Lỗi không hiển thị.");
    }
}
