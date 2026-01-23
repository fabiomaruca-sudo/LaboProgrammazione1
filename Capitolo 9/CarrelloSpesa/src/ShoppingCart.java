import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        if (items != null) {
            this.items = items;
        }
    }
    public double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total = total + item.getPrice();
        }
        return total;
    }
    public void addItem(Item item) {
        this.items.add(item);
    }
}
