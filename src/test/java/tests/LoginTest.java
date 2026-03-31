package tests;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isLoaded(), "Trang Inventory không được tải thành công.");
    }

    @Test
    public void testLockedOutLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Lỗi không hiển thị.");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"), "Sai thông báo lỗi.");
    }

    @Test
    public void testInvalidPasswordLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("standard_user", "wrong_pass");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Lỗi không hiển thị.");
    }
}
