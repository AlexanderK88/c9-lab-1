package se.lernia.lab;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class DiscountTest {

  @Test
  void testCombinedMilkAndQuantityDiscount() {
    Product milk = new Product("Milk", 30.0, 5);
    Discount combinedDiscount = new MilkDiscount(new QuantityDiscount(new NoDiscount()));

    double totalDiscount = combinedDiscount.apply(milk);
    assertThat(totalDiscount).isEqualTo((30 * 5 * 0.05) + (5 * 10));
  }

  @Test
  void testFridayAndMilkDiscount() {
    LocalDate friday = LocalDate.of(2024, 9, 27);
    Product milk = new Product("Milk", 30.0, 5);
    Discount combinedDiscount = new FridayDiscount(new MilkDiscount(new NoDiscount()), friday);

    double totalDiscount = combinedDiscount.apply(milk);
    assertThat(totalDiscount).isEqualTo((30 * 5 * 0.10) + (30 * 5 * 0.05));
  }
}
