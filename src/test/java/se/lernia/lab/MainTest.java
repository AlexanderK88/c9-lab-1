package se.lernia.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStream));
  }

  @Test
  void testApplyDiscountAndDisplay() {
    Product milk = new Product("Milk", 30.0, 5);
    Discount discountChain = new FridayDiscount(new MilkDiscount(new QuantityDiscount(new NoDiscount())));

    Main.applyDiscountAndDisplay(milk, discountChain);

    String output = outputStream.toString().trim();

    assertThat(output).contains("Product: Milk");
    assertThat(output).contains("Unit Price: 30.0 kr");
    assertThat(output).contains("Quantity: 5");
    assertThat(output).contains("Total Price: 150.00 kr");
    assertThat(output).contains("Discount Amount: 57.50 kr");
    assertThat(output).contains("Final Price: 92.50 kr");
    assertThat(output).contains("Discounts Applied:");
    assertThat(output).contains("- 5% Milk Discount applied.");
    assertThat(output).contains("- Quantity Discount of 10 kr per product applied.");

    assertThat(output).contains("-----------------------------------");
  }

  @Test
  void testDisplayDiscounts() {
    String discountDescription = "5% Milk Discount applied. Quantity Discount of 10 kr per product applied.";

    Main.displayDiscounts(discountDescription);

    String output = outputStream.toString().trim();

    assertThat(output).contains("- 5% Milk Discount applied.");
    assertThat(output).contains("- Quantity Discount of 10 kr per product applied.");
  }
}
