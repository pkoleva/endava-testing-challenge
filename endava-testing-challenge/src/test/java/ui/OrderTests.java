package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.pkoleva.ui.utils.DataHelper;
import org.pkoleva.ui.utils.Item;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.MethodName.class)
@Tag("ui")
class OrderTests extends BaseTest {

    @Test
    @Tag("happypath")
    void test1000_placeAnOrder(){
        // Get all items.
        List<WebElement> allItems = itemsPage.getAllItems();
        List<Item> allItemsAsObjects = itemsPage.getItemsAsObjects(allItems);
        List<Item> selectedItems = new ArrayList<>();

        //1. Add the first item to the cart.
        itemsPage.addItemToCartByPosition(allItems.get(0));
        selectedItems.add(allItemsAsObjects.get(0));

        //2. Add the last item to the cart.
        itemsPage.addItemToCartByPosition(allItems.get(allItems.size()-1));
        selectedItems.add(allItemsAsObjects.get(allItemsAsObjects.size()-1));

        //3. Verify the correct items are added.
        itemsPage.goToCart();
        List<Item> cartItems = cartPage.getCartItemsAsObjects();
        int cartSize = cartItems.size();
        Assertions.assertEquals(selectedItems.size(),cartItems.size());
        Assertions.assertTrue(selectedItems.get(0).equals(cartItems.get(0)));
        Assertions.assertTrue(selectedItems.get(1).equals(cartItems.get(1)));

        //4. Remove the first item.
        selectedItems.remove(0);
        cartPage.removeItemByPosition(0);
        Assertions.assertEquals(cartSize-1, cartPage.getAllCartItems().size());
        cartPage.continueShopping();

        //5. Add the last but one item to the cart.
        allItems = itemsPage.getAllItems();
        itemsPage.addItemToCartByPosition(allItems.get(allItems.size()-2));
        selectedItems.add(allItemsAsObjects.get(allItemsAsObjects.size()-2));


        //6. Verify the cart content again.
        itemsPage.goToCart();
        cartItems = cartPage.getCartItemsAsObjects();
        Assertions.assertEquals(selectedItems.size(),cartItems.size());
        Assertions.assertTrue(selectedItems.get(0).equals(cartItems.get(0)));
        Assertions.assertTrue(selectedItems.get(1).equals(cartItems.get(1)));

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
        Assertions.assertTrue(DataHelper.isSorted(items));
    }

}
