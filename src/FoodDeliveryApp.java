import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FoodDeliveryApp {
    private final ArrayList<FoodItem> foodItems = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private double totalRevenue = 0;

    public FoodDeliveryApp() {
        System.out.println("Welcome to the Online Food Delivery System!");
        try { loadFoodItemsFromFile("data/Food_Items.csv"); }
        catch (IOException e) { System.out.println("Error loading menu: " + e.getMessage()); }
    }

    private void loadFoodItemsFromFile(String filename) throws IOException {
        for (String line : Files.readAllLines(Paths.get(filename))) {
            line = line.trim(); if (line.isEmpty()) continue;
            String[] p = line.split(",", -1);
            if (p.length < 3) continue;
            foodItems.add(new FoodItem(p[0].trim(),
                    Double.parseDouble(p[1].trim()),
                    Integer.parseInt(p[2].trim())));
        }
    }

    public void displayFoodItems() {
        System.out.println("Available Food Items:");
        for (FoodItem f : foodItems) System.out.println(" - " + f);
    }

    public void registerCustomer(Customer c) {
        customers.add(c);
        System.out.println("Customer Registered: " + c.getName());
    }

    public FoodItem findFoodItemByName(String name) {
        for (FoodItem f : foodItems) if (f.getName().equalsIgnoreCase(name)) return f;
        return null;
    }

    public void addToCart(Customer c, String name) {
        FoodItem f = findFoodItemByName(name);
        if (f == null) System.out.println("Error: The food item is not available.");
        else c.addToCart(f);
    }

    public void placeOrder(Customer c) {
        Order o = new Order(c, c.getCart());
        o.displayOrderDetails();
        int prep = o.computePrepTime(2);
        System.out.printf("Total Preparation Time: %d minutes (using 2 chefs)%n", prep);
        totalRevenue += o.getDiscountedAmount();
        System.out.printf("Total Revenue: $%.2f%n", totalRevenue);
    }
}
