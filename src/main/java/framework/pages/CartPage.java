package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(xpath = "(//button[text()='Remove'])[1]")
    private WebElement firstRemoveButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> itemNames;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        if (!isElementVisible(By.cssSelector(".cart_item"))) {
            return 0;
        }
        return cartItems.size();
    }

    public CartPage removeFirstItem() {
        if (getItemCount() > 0) {
            waitAndClick(firstRemoveButton);
        }
        return this;
    }

    public CheckoutPage goToCheckout() {
        waitAndClick(checkoutButton);
        return new CheckoutPage(driver);
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        if (getItemCount() > 0) {
            for (WebElement e : itemNames) {
                names.add(getText(e));
            }
        }
        return names;
    }
}
