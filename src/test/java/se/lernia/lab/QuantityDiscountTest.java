package se.lernia.lab;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class QuantityDiscountTest {

  @Test
  void testQuantityDiscountApplies() {
    Product product = new Product("Bread", 20.0, 5);
    Discount quantityDiscount = new QuantityDiscount(new NoDiscount());

    double discount = quantityDiscount.apply(product);
    assertThat(discount).isEqualTo(5 * 10.0);
  }

  @Test
  void testQuantityDiscountDoesNotApplyIfQuantityLessThanFive() {
    Product product = new Product("Juice", 20.0, 4);
    Discount quantityDiscount = new QuantityDiscount(new NoDiscount());

    double discount = quantityDiscount.apply(product);
    assertThat(discount).isEqualTo(0.0);
  }
}
