package Service;

import model.*;
import java.util.*;

//cart checkout logic
public class CheckoutService {
    private final ShippingService shippingService = new ShippingService();
    private static final double SHIPPING_FEE = 30.0;

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty.");

        double subtotal = 0;
        List<Shippable> toShip = new ArrayList<>();

        for (CartItem ci : cart.getItems()) {
            Product p = ci.getProduct();
            if (!p.isAvailable(ci.getQuantity())) {
                throw new IllegalStateException(p.getName() + " unavailable or expired.");
            }

            subtotal += p.getPrice() * ci.getQuantity();

            if (p.requiresShipping()) {
                for (int i = 0; i < ci.getQuantity(); i++) {
                    toShip.add(p);
                }
            }
        }

        double shipping = toShip.isEmpty() ? 0 : SHIPPING_FEE;
        double total = subtotal + shipping;

        if (!customer.canPay(total)) {
            throw new IllegalStateException("Insufficient balance.");
        }

        customer.pay(total);
        for (CartItem ci : cart.getItems()) {
            ci.getProduct().reduceQuantity(ci.getQuantity());
        }

        shippingService.ship(toShip);

        System.out.println("** Checkout receipt **");
        for (CartItem ci : cart.getItems()) {
            System.out.printf("%dx %s\t%.0f%n",
                    ci.getQuantity(),
                    ci.getProduct().getName(),
                    ci.getProduct().getPrice() * ci.getQuantity());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal\t%.0f%n", subtotal);
        System.out.printf("Shipping\t%.0f%n", shipping);
        System.out.printf("Amount\t\t%.0f%n", total);
        System.out.printf("Remaining balance: %.0f%n", customer.getBalance());
    }
}
