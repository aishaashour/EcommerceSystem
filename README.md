# EcommerceSystem

This project is a simple object-oriented (Java-based e-commerce system), built as part of the Fawry Quantum Internship Challenge. It supports product definition, cart management, order checkout, shipping, and expiry validation.

# Features

- Define products with name, price, quantity, and weight
- Support for expirable products (e.g., Cheese, Biscuits)
- Support for shippable products (e.g., TV, Cheese)
- Products can be added to cart with limited available quantity
- Checkout system with:
    - Subtotal calculation
    - Shipping fee based on weight (10 EGP per kg)
    - Balance deduction
    - Product quantity reduction
    - Expiry check
    - Insufficient balance and stock error handling
- Shipping service prints shipment notice
- Console-based interface for interaction

# Product Types
- product may be Expirable or Shippable or both or none.

# Sample Usage

```java
// this is just test for successful checkout case
//you can change quantity or balance or expiry-date to check error handling
Product cheese = new Product("Cheese", 100, 10, 300, LocalDate.of(2025, 7, 17), true);
Product tv = new Product("TV", 300, 5, 1500, null, true);
Product scratchCard = new Product("Scratch Card", 50, 100, 200, null, false);
Product biscuits = new Product("Biscuits", 150, 2, 70, LocalDate.of(2025, 7, 5), true);

Customer customer = new Customer("Ahmed", 1000);

Cart cart = new Cart();
cart.add(cheese, 2);
cart.add(tv, 1);
cart.add(scratchCard, 1);
cart.add(biscuits, 1);

System.out.println("\n");
System.out.println("Hello " + customer.getName() + ";)\n");
System.out.println("Welcome to Aisha's e-commerce\n");
CheckoutService.checkout(customer, cart);

```
# Sample output

    Hello Ahmed;)
    Welcome to Aisha's e-commerce
    ** Shipment notice **
    1x Cheese 300g
    1x Cheese 300g
    1x TV 1500g
    1x Biscuits 70g
    Total package weight 2.2kg
    ** Checkout receipt **
    2x Cheese 200
    1x TV 300
    1x Scratch Card 50
    1x Biscuits 150
    -----------------------------
    Subtotal 700
    Shipping 22
    Total Amount 722
    Ahmed's Remaining Balance 278

# Sample output: if product is expired 

    Hello Ahmed;)
    Welcome to Aisha's e-commerce
    Error: Sorry Product Cheese is expired.

# Sample output: if Insufficient customer balance

    Hello Ahmed;)
    Welcome to Aisha's e-commerce
    Error: Insufficient customer balance.

# Sample output: if out of stock

    Hello Ahmed;)
    Welcome to Aisha's e-commerce
    Error: Sorry Not enough stock for Cheese