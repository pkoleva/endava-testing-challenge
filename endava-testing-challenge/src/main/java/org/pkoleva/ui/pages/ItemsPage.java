package org.pkoleva.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pkoleva.ui.utils.Helper;
import org.pkoleva.ui.utils.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsPage extends BasePage{


    public ItemsPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait);
    }

    private HashMap<String, By> itemProperties = new HashMap<>();

    //locators
    private By item = By.xpath("//div[@class='inventory_item']");
    private By addToCartBtn = By.xpath("//button[text()='Add to cart']");
    private By filterSelect = By.className("product_sort_container");

    public List<WebElement> getAllItems(){
        return driver.findElements(item);
    }



    public void addItemToCartByPosition(WebElement item) {
        var button = item.findElement(addToCartBtn);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
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
        return Helper.getItemsAsObjects(elements,itemProperties);
    }

}
