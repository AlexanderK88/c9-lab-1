package se.lernia.lab;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class QuantityDiscountTest {

  @Test
  void testQuantityDiscountApplies() {
    Product product = new Product("Bread", 15.0, 5);
    Discount quantityDiscount = new QuantityDiscount(null);

    double discount = quantityDiscount.apply(product);
    assertThat(discount).isEqualTo(5 * 10.0);
  }

  @Test
  void testQuantityDiscountDoesNotApplyIfQuantityLessThanFive() {
    Product product = new Product("Juice", 10.0, 4);
    Discount quantityDiscount = new QuantityDiscount(null);

    double discount = quantityDiscount.apply(product);
    assertThat(discount).isEqualTo(0.0); // No discount because quantity < 5
  }
}
