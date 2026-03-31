package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.CartPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;

public class CartTest extends BaseTest {

    @Test
    public void testAddToCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login(ConfigReader.getInstance().getUsername(), ConfigReader.getInstance().getPassword());

        inventoryPage.addFirstItemToCart();
        Assert.assertEquals(inventoryPage.getCartItemCount(), 1, "Giỏ hàng không có 1 sản phẩm.");
    }

    @Test
    public void testAddMultipleProductsAndVerifyInCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login(ConfigReader.getInstance().getUsername(), ConfigReader.getInstance().getPassword());

        inventoryPage.addFirstItemToCart();
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertEquals(cartPage.getItemCount(), 1, "Cart Page không có 1 item.");
    }

    @Test
    public void testRemoveItemFromCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login(ConfigReader.getInstance().getUsername(), ConfigReader.getInstance().getPassword());

        CartPage cartPage = inventoryPage.addFirstItemToCart().goToCart();
        Assert.assertEquals(cartPage.getItemCount(), 1);

        cartPage.removeFirstItem();
        Assert.assertEquals(cartPage.getItemCount(), 0, "Giỏ hàng không rỗng sau khi xóa.");
    }
}
