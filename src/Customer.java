import java.util.*;

public class Customer {
    private final String customerID;
    private final String name;
    private final ArrayList<FoodItem> cart = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
        this.customerID = "CUST_" + UUID.randomUUID().toString().substring(0, 6);
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }

    public void addToCart(FoodItem item) {
        cart.add(item);
        System.out.println(item.getName() + " added to cart.");
    }

    public void viewCart() {
        System.out.println("Cart Contents:");
        for (FoodItem item : cart) {
            System.out.printf(" - %s, $%.2f, %d mins%n",
                    item.getName(), item.getPrice(), item.getPrepTime());
        }
    }

    public ArrayList<FoodItem> getCart() { return cart; }
}
