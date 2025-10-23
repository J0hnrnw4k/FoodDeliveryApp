import java.util.*;

public class Order {
    private final String orderID;
    private final Customer customer;
    private final ArrayList<FoodItem> items;
    private double totalAmount;
    private double discountedAmount;

    public Order(Customer customer, ArrayList<FoodItem> items) {
        this.customer = customer;
        this.items = new ArrayList<>(items);
        this.orderID = "ORD_" + UUID.randomUUID().toString().substring(0, 6);
        computeTotals();
    }

    private void computeTotals() {
        totalAmount = 0;
        for (FoodItem f : items) totalAmount += f.getPrice();
        applyDiscount();
    }

    private void applyDiscount() {
        double rate = 0;
        if (totalAmount > 200) rate = 0.15;
        else if (totalAmount > 100) rate = 0.10;
        else if (totalAmount > 50)  rate = 0.05;
        discountedAmount = totalAmount * (1 - rate);
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Food Items:");
        for (FoodItem f : items) {
            System.out.printf(" - %s, $%.2f%n", f.getName(), f.getPrice());
        }
        System.out.printf("Total Amount: $%.2f%n", totalAmount);
        System.out.printf("Discounted Amount: $%.2f%n", discountedAmount);
    }


    public int computePrepTime(int chefs) {
        if (items.isEmpty()) return 0;
        if (chefs <= 1) { int sum=0; for (FoodItem f: items) sum += f.getPrepTime(); return sum; }
        List<Integer> jobs = new ArrayList<>();
        for (FoodItem f: items) jobs.add(f.getPrepTime());
        jobs.sort(Collections.reverseOrder());
        int[] load = new int[chefs];
        for (int t : jobs) {
            int i = 0; for (int c=1;c<chefs;c++) if (load[c] < load[i]) i = c;
            load[i] += t;
        }
        int max = 0; for (int l : load) if (l > max) max = l; return max;
    }

    public double getDiscountedAmount() { return discountedAmount; }
}
