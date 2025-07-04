import model.*;
import policy.*;
import Service.CheckoutService;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Product cheese = new Product("Cheese", 100, 5,
                new Expiration(new Date(System.currentTimeMillis() + 86400000)),
                new WeightedShipping(0.2));

        Product biscuits = new Product("Biscuits", 150, 5,
                new Expiration(new Date(System.currentTimeMillis() + 86400000)),
                new WeightedShipping(0.7));

        Product tv = new Product("TV", 300, 2,
                new NoExpiration(),
                new WeightedShipping(5.0));

        Product scratchCard = new Product("ScratchCard", 50, 10,
                new NoExpiration(),
                new NoShipping());

        Customer customer = new Customer("Farida", 1000);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        CheckoutService service = new CheckoutService();
        service.checkout(customer, cart);
    }
}
