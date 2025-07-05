package service;
import model.CartItem;
import model.Product;

import java.util.*;

public class Cart {
    List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
//        if (!product.isAvailable(quantity)) {
//            throw new RuntimeException("Product out of stock: " + product.getName());
//        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
