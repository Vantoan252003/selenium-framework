package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".inventory_item button")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isElementVisible(By.cssSelector(".title")) && getText(driver.findElement(By.cssSelector(".title"))).equalsIgnoreCase("Products");
    }

    public InventoryPage addFirstItemToCart() {
        if (!addToCartButtons.isEmpty()) {
            waitAndClick(addToCartButtons.get(0));
        }
        return this;
    }

    public InventoryPage addItemByName(String name) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", name);
        waitAndClick(driver.findElement(By.xpath(xpath)));
        return this;
    }

    public int getCartItemCount() {
        if (isElementVisible(By.cssSelector(".shopping_cart_badge"))) {
            return Integer.parseInt(getText(cartBadge));
        }
        return 0;
    }

    public CartPage goToCart() {
        waitAndClick(cartLink);
        return new CartPage(driver);
    }
}
