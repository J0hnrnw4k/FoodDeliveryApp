public class Main {
    public static void main(String[] args) {
        FoodDeliveryApp app = new FoodDeliveryApp();
        app.displayFoodItems();

        Customer alice = new Customer("Alice Smith");
        app.registerCustomer(alice);

        app.addToCart(alice, "Pizza");
        app.addToCart(alice, "Burger");

        alice.viewCart();
        app.placeOrder(alice);

        app.addToCart(alice, "Sushi");
    }
}

