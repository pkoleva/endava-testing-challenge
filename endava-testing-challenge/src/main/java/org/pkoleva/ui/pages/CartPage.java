package org.pkoleva.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pkoleva.ui.utils.DataHelper;
import org.pkoleva.ui.utils.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartPage extends BasePage{


    public CartPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait);
    }

    //locators
    private By items = By.className("cart_item");
    private By removeBtn = By.xpath("//button[text()='Remove']");
    private By continueShoppingBtn = By.id("continue-shopping");
    private By checkoutBtn = By.id("checkout");

    //methods
    public boolean removeItemByPosition(int index) {
        WebElement element = getAllCartItems().get(index);
        element.findElement(removeBtn).click();
        return element.isDisplayed();
    }

    public void continueShopping(){
        clickButton(continueShoppingBtn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(continueShoppingBtn));
    }

    public void checkout(){
        clickButton(checkoutBtn);
    }

    public List<WebElement> getAllCartItems(){
        return driver.findElements(items);
    }

    public List<Item> getCartItemsAsObjects(){
        HashMap<String, By> itemProperties = new HashMap<>();
        List<WebElement> elements = getAllCartItems();
        itemProperties.put("itemName",By.className("inventory_item_name"));
        itemProperties.put("itemPrice",By.className("inventory_item_price"));
        return DataHelper.getItemsAsObjects(elements,itemProperties);
    }
}
