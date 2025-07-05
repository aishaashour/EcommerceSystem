package service;

import model.Shippable;

import java.util.List;

public class ShippingService {

    public static void ship(List<Shippable> items) {
        if (items.isEmpty())
            return;

        System.out.println("** Shipment notice **");

        double totalWeight = 0.0;

        for (Shippable item : items) {
            double weight = item.getWeight();
            totalWeight += weight;
            System.out.printf("1x %s %.0fg%n", item.getName(), weight);
        }

        System.out.printf("Total package weight %.1fkg%n \n", totalWeight / 1000.0);
    }
}
