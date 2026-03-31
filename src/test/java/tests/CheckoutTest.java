package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.CartPage;
import framework.pages.CheckoutPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.TestDataFactory;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCheckoutWithRandomData() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login(ConfigReader.getInstance().getUsername(), ConfigReader.getInstance().getPassword());

        inventoryPage.addFirstItemToCart();
        CartPage cartPage = inventoryPage.goToCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        Map<String, String> checkoutData = TestDataFactory.randomCheckoutData();
        System.out.println("Sử dụng dữ liệu ngẫu nhiên để Checkout: " + checkoutData);

        checkoutPage.enterCheckoutInfo(
            checkoutData.get("firstName"),
            checkoutData.get("lastName"),
            checkoutData.get("postalCode")
        );
        checkoutPage.clickContinue();

        // Assert can be anything that proves success. For SAUCEDEMO, standard check is URL or new title
        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-step-two"),
                "Không điều hướng đến step two thành công.");
    }
}
