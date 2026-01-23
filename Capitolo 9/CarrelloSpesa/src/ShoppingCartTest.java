import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private ShoppingCart cart;
    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    @DisplayName("Should return the correct sum of all items added to the cart")
    void shouldSumAllItemPricesCorrectly() {
        //ARRANGE
        Item bread = new Item("Bread", 1.50);
        Item milk =  new Item("Milk", 2.20);
        double expectedTotal = 3.70;
        //ACT
        cart.addItem(bread);
        cart.addItem(milk);
        double actualTotal = cart.getTotalPrice();
        //ASSERTS
        assertEquals(expectedTotal, actualTotal, 0.0001, "The total price calculation failed");

    }
    @Test
    void shouldAddItemToCollectionWhenItemIsValid() {
        Item validItem = new Item("Laptop", 1200.0);

        cart.addItem(validItem);
        assertTrue(cart.getItems().contains(validItem), "Il carrello dovrebbe contenere l'item aggiunto");
        assertEquals(1, cart.getItems().size(),"Il carrello dovrebbe avere esattamente 1 elemento");
    }
}