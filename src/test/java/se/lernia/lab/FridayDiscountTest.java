package se.lernia.lab;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class FridayDiscountTest {

  @Test
  void testFridayDiscountApplies() {
    LocalDate friday = LocalDate.of(2024, 9, 27);
    Product product = new Product("Juice", 10.0, 2);

    Discount fridayDiscount = new FridayDiscount(null, friday);
    double discount = fridayDiscount.apply(product);

    assertThat(discount).isEqualTo(10.0 * 2 * 0.10);
  }

  @Test
  void testFridayDiscountDoesNotApplyIfNotFriday() {
    LocalDate notFriday = LocalDate.of(2024, 9, 26);
    Product product = new Product("Juice", 10.0, 2);

    Discount fridayDiscount = new FridayDiscount(null, notFriday);
    double discount = fridayDiscount.apply(product);

    assertThat(discount).isEqualTo(0.0);
  }
}
