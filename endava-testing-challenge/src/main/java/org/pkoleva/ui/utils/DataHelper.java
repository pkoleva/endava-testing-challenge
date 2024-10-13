package org.pkoleva.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataHelper {


    public static List<Item> getItemsAsObjects(List<WebElement> elements, HashMap<String, By> properties){
        List<Item> items = new ArrayList<>();
        for(WebElement e : elements){
            Item i = Item.builder().build();
            i.setTitle(e.findElement(properties.get("itemName")).getText());
            i.setPrice(Double.valueOf(e.findElement(properties.get("itemPrice")).getText().substring(1)));
            items.add(i);
        }
        return items;
    }

//    public static boolean compareItems(List<Item> list1, List<Item> list2){
//        for(Item i: list1){
//            i.price==list2
//
//        }
//        return true;
//    }

    public static boolean isSorted(List<Item> list) {
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i).getPrice() < list.get(i + 1).getPrice()) {
                return false;
            }
        }
        return true;
    }
}
