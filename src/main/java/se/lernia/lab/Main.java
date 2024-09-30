package se.lernia.lab;

public class Main {
  public static void main(String[] args) {
    Discount discountChain = new FridayDiscount(new MilkDiscount(new QuantityDiscount(null)));

    Product milk = new Product("Milk", 20.0, 5);
    applyDiscountAndDisplay(milk, discountChain);

    Product bread = new Product("Bread", 15.0, 5);
    applyDiscountAndDisplay(bread, discountChain);

    Product juice = new Product("Juice", 10.0, 7);
    applyDiscountAndDisplay(juice, discountChain);

    Product smallMilk = new Product("Milk", 20.0, 3);
    applyDiscountAndDisplay(smallMilk, discountChain);

  }

  public static void applyDiscountAndDisplay(Product product, Discount discountChain) {
    double totalPrice = product.price() * product.quantity();
    double totalDiscount = discountChain.apply(product);
    String discountDescription = discountChain.getDescription(product);
    double finalPrice = Math.max(0, totalPrice - totalDiscount);

    System.out.println("Product: " + product.name());
    System.out.println("Unit Price: " + product.price() + " kr");
    System.out.println("Quantity: " + product.quantity());
    System.out.printf("Total Price: %.2f kr%n", totalPrice);
    System.out.printf("Discount Amount: %.2f kr%n", totalDiscount);
    System.out.printf("Final Price: %.2f kr%n", finalPrice);
    System.out.println("Discounts Applied:");
    displayDiscounts(discountDescription);
    System.out.println("-----------------------------------");
  }

  public static void displayDiscounts(String discountDescription) {
    String[] discounts = discountDescription.split("\\.\\s*");
    for (String discount : discounts) {
      if (!discount.isEmpty()) {
        System.out.println("- " + discount.trim() + ".");
      }
    }
  }
}
