package service;

import model.Product;
import model.Shippable;
import model.Customer;
import service.Cart;
import model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private static final double SHIPPING_RATE_PER_KG = 10.0; //shipping per kg

    public static void checkout(Customer customer, Cart cart) {
        List<CartItem> items = cart.getItems();             //customer cart

        if (items.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        double subtotal = 0.0;
        double totalShippingWeight = 0.0;
        List<Shippable> shippableItems = new ArrayList<>(); //shippable items

        for (CartItem item : items) {
            Product product = item.getProduct();
            int qty = item.getQuantity();

            // Stock check
            if (!product.isAvailable(qty)) {
                System.out.println("Error: Sorry Not enough stock for " + product.getName());
                return;
            }

            // Expiry check
            if (product.isExpired()) {
                System.out.println("Error: Sorry Product " + product.getName() + " is expired.");
                return;
            }

            subtotal += product.getPrice() * qty;

            if (product.isShippable()) {
                totalShippingWeight += product.getWeight() * qty;

                // Add to shipping list
                for (int i = 0; i < qty; i++) {
                    shippableItems.add(product);
                }
            }
        }

        double shippingFee = totalShippingWeight > 0 ? SHIPPING_RATE_PER_KG*(totalShippingWeight/1000) : 0;
        double totalAmount = subtotal + shippingFee;

        // customer balance check
        if (customer.getBalance() < totalAmount) {
            System.out.println("Error: Insufficient customer balance.");
            return;
        }

        // Deduct balance and reduce product quantity
        customer.deduct(totalAmount);
        for (CartItem item : items) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        // Shipment notice
        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        // Checkout receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            Product product = item.getProduct();
            double itemTotal = product.getPrice() * item.getQuantity();
            System.out.println(item.getQuantity() + "x " + product.getName() + " " + (int)itemTotal);
        }
        System.out.println("-----------------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFee);
        System.out.printf("Total Amount %.0f%n", totalAmount);
        System.out.printf(customer.getName()+ "'s Remaining Balance %.0f%n", customer.getBalance());

        // Clear cart after successful checkout
        cart.clear();
    }
}
