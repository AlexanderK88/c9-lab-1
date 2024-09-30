package se.lernia.lab;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class FridayDiscountTest {

  @Test
  void testFridayDiscountApplies() {
    LocalDate friday = LocalDate.of(2024, 9, 27);
    Product product = new Product("Juice", 15.0, 2);

    Discount fridayDiscount = new FridayDiscount(null, friday);

    double discount = fridayDiscount.apply(product);
    assertThat(discount).isEqualTo(15.0 * 2 * 0.10);

    String description = fridayDiscount.getDescription(product);
    assertThat(description).isEqualTo("It's Friday! You get a 10% discount.");
  }

  @Test
  void testFridayDiscountDoesNotApplyIfNotFriday() {
    LocalDate nonFriday = LocalDate.of(2024, 9, 28);
    Product product = new Product("Juice", 15.0, 2);

    Discount fridayDiscount = new FridayDiscount(null, nonFriday);

    double discount = fridayDiscount.apply(product);
    assertThat(discount).isEqualTo(0.0);

    String description = fridayDiscount.getDescription(product);
    assertThat(description).isEmpty();
  }


  @Test
  void testFridayDiscountWithNextDiscount() {
    LocalDate friday = LocalDate.of(2024, 9, 27);
    Product product = new Product("Juice", 15.0, 6);

    Discount discountChain = new FridayDiscount(new QuantityDiscount(null), friday);

    double discount = discountChain.apply(product);
    assertThat(discount).isEqualTo((15.0 * 6 * 0.10) + (6 * 10));

    String description = discountChain.getDescription(product);
    assertThat(description).isEqualTo("It's Friday! You get a 10% discount. Quantity Discount of 10 kr per product applied.");
  }
}
