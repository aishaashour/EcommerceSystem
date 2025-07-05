package model;

public class Customer {
    protected String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void deduct(double amount) {
        balance -= amount;
    }


}
