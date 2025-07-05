import model.CartItem;
import model.Customer;
import model.Product;
import model.Shippable;
import service.Cart;
import service.CheckoutService;
import service.ShippingService;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // this is just test for successful checkout case
        //you can change quantity or balance or expiry-date to check error handling

        Product cheese = new Product("Cheese", 100, 10, 300, LocalDate.of(2025, 7, 17), true);
        Product tv = new Product("TV", 300, 5, 1500, null, true);
        Product scratchCard = new Product("Scratch Card", 50, 100, 100, null, false);
        Product biscuits = new Product("Biscuits", 150, 2, 70, LocalDate.of(2025, 7, 5), true);

        Customer customer = new Customer("Ahmed", 1000);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(tv, 1);
        cart.add(scratchCard, 1);
        cart.add(biscuits, 1);

        System.out.println("\n");
        System.out.println("Hello " + customer.getName() + ";)");
        System.out.println("Welcome to Aisha's e-commerce\n");
        CheckoutService.checkout(customer, cart);
    }
}