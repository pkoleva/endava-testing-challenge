package org.pkoleva.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Helper {


    public static List<Item> getItemsAsObjects(List<WebElement> elements, HashMap<String, By> properties){
        List<Item> items = new ArrayList<>();
        Item i = new Item();
        for(WebElement e : elements){
            i.title = e.findElement(properties.get("itemName")).getText();
            i.price = Double.valueOf(e.findElement(properties.get("itemPrice")).getText().substring(1));
            items.add(i);
        }
        return items;
    }

    public static boolean isSorted(List<Item> list) {
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i).price < list.get(i + 1).price) {
                return false;
            }
        }
        return true;
    }
}
