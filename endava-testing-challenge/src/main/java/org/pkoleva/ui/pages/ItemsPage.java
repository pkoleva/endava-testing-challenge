package org.pkoleva.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pkoleva.ui.utils.DataHelper;
import org.pkoleva.ui.utils.Item;

import java.util.HashMap;
import java.util.List;

public class ItemsPage extends BasePage{


    public ItemsPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait);
    }

    private HashMap<String, By> itemProperties = new HashMap<>();

    //locators
    private By item = By.className("inventory_item_description");
    private By addToCartBtn = By.tagName("button");
    private By filterSelect = By.className("product_sort_container");

    public List<WebElement> getAllItems(){
        return driver.findElements(item);
    }



    public WebElement addItemToCartByPosition(WebElement item) {
        var button = item.findElement(addToCartBtn);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        return button;
    }

    public void goToCart() {
        clickButton(cartBtn);
    }

    public void sortByPriceDesc() {
        Select dropdown = new Select(driver.findElement(filterSelect));
        dropdown.selectByValue("hilo");
    }

    public List<Item> getItemsAsObjects(List<WebElement> elements){
        itemProperties.put("itemName",By.className("inventory_item_name"));
        itemProperties.put("itemPrice",By.className("inventory_item_price"));
        return DataHelper.getItemsAsObjects(elements,itemProperties);
    }

}
