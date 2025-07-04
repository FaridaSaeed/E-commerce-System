package model;

import policy.ExpirationPolicy;
import policy.ShippingPolicy;

public class Product implements Shippable {
    private final String name;
    private final double price;
    private int quantity;
    private final ExpirationPolicy expirationPolicy;
    private final ShippingPolicy shippingPolicy;

    public Product(String name, double price, int quantity,
                   ExpirationPolicy expirationPolicy,
                   ShippingPolicy shippingPolicy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationPolicy = expirationPolicy;
        this.shippingPolicy = shippingPolicy;
    }

    public boolean isExpired() {
        return expirationPolicy.isExpired();
    }

    public boolean isAvailable(int requestedQuantity) {
        return !isExpired() && quantity >= requestedQuantity;
    }

    public void reduceQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough stock.");
        }
        quantity -= amount;
    }

    public boolean requiresShipping() {
        return shippingPolicy.getWeight() > 0;
    }

    @Override
    public double getWeight() {
        return shippingPolicy.getWeight();
    }

    @Override
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
