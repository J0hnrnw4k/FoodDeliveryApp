public class FoodItem {
    private final String name;
    private final double price;
    private final int prepTime;

    public FoodItem(String name, double price, int prepTime) {
        this.name = name;
        this.price = price;
        this.prepTime = prepTime;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getPrepTime() { return prepTime; }

    @Override
    public String toString() {
        return String.format("%s, $%.2f, %d mins", name, price, prepTime);
    }
}


