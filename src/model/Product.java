package model;

import java.time.LocalDate;

public class Product implements Expirable, Shippable {
    protected String name;
    protected double price;
    protected int quantity;
    protected double weight;
    protected LocalDate expiryDate;
    protected Boolean ship;


    public Product(String name, double price, int quantity,double weight,LocalDate expiryDate, boolean ship) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.expiryDate = expiryDate;
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount <= quantity) {
            this.quantity -= amount;
        }
    }

    public boolean isAvailable(int requested) {
        return quantity >= requested;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        if (expiryDate == null)
            return false; // non-expirable
        return expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public boolean isShippable() {
        return ship;
    }
}
