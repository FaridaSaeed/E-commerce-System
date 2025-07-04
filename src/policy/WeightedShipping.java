package policy;

public class WeightedShipping implements ShippingPolicy {
    private final double weight;  // in kg

    public WeightedShipping(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
