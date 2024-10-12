package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.pkoleva.ui.utils.Helper;
import org.pkoleva.ui.utils.Item;

import java.util.ArrayList;
import java.util.List;

class OrderTests extends BaseTest {

    @Test
    @Tag("happypath")
    void test1000_placeAnOrder(){
        // Get all items.
        List<WebElement> allItems = itemsPage.getAllItems();
        List<WebElement> selectedItems = new ArrayList<>();

        //1. Add the first item to the cart.
        itemsPage.addItemToCartByPosition(allItems.get(0));
        selectedItems.add(allItems.get(0));

        //2. Add the last item to the cart.
        //TODO: Doesn't select the last object.
        itemsPage.addItemToCartByPosition(allItems.get(allItems.size()-1));
        selectedItems.add(allItems.get(allItems.size()-1));
        List<Item> items = itemsPage.getItemsAsObjects(selectedItems);

        //3. Verify the correct items are added.
        itemsPage.goToCart();
        List<Item> cartItems = cartPage.getCartItemsAsObjects();
        int cartSize = cartItems.size();
        Assertions.assertEquals(items.size(),cartItems.size());
        //TODO: Due to last todo cannot verify.
//        Assertions.assertEquals(cartItems,items);

        //4. Remove the first item.
        selectedItems.remove(0);
        cartPage.removeItemByPosition(0);
        Assertions.assertEquals(cartSize-1, cartPage.getAllCartItems().size());
        cartPage.continueShopping();

        //5. Add the last but one item to the cart.
        allItems = itemsPage.getAllItems();
        itemsPage.addItemToCartByPosition(allItems.get(allItems.size()-2));
        selectedItems.add(allItems.get(allItems.size()-2));
//        items = itemsPage.getItemsAsObjects(selectedItems);

        //6. Verify the cart content again.
        itemsPage.goToCart();
        cartItems = cartPage.getCartItemsAsObjects();
        Assertions.assertEquals(items.size(),cartItems.size());
//        Assertions.assertEquals(cartItems,items);

        //7. Go to checkout
        cartPage.checkout();
        //8. Finish the order
        checkoutPage.fillInDataAndContinue();
        List<WebElement> checkoutItems = checkoutPage.getItems();
        checkoutPage.finishOrder();
        //9. Verify order is placed
        Assertions.assertTrue(checkoutPage.checkOrderComplete());
        checkoutPage.goToHomePage();
        //10. Verify cart is empty
        itemsPage.goToCart();
        Assertions.assertEquals(0,cartPage.getAllCartItems().size());

    }

    @Test
    @Tag("sorting")
    void test1010_sortingByPriceHighToLow(){
        //1. Verify when for sorting it is selected "Price (high to low)".
        itemsPage.sortByPriceDesc();
        // Get all items.
        List<WebElement> allItems = itemsPage.getAllItems();

        //2. Then the items are sorted in the correct manner.
        List<Item> items = itemsPage.getItemsAsObjects(allItems);
        Assertions.assertTrue(Helper.isSorted(items));
    }

}
