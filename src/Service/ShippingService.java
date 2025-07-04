package Service;

import model.Shippable;

import java.util.*;

// Handles collecting and printing shippable items
public class ShippingService {
    public void ship(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> countMap = new LinkedHashMap<>();
        Map<String, Double> weightMap = new HashMap<>();

        for (Shippable s : items) {
            countMap.put(s.getName(), countMap.getOrDefault(s.getName(), 0) + 1);
            weightMap.put(s.getName(), s.getWeight());
            totalWeight += s.getWeight();
        }

        for (String name : countMap.keySet()) {
            int count = countMap.get(name);
            double weight = weightMap.get(name);
            System.out.printf("%dx %s\t%.0fg%n", count, name, weight * count * 1000);
        }

        System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
    }
}
