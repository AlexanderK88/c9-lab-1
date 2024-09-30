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

  @Test
  void testQuantityDiscountDescription() {
    Product product = new Product("Bread", 20.0, 5);
    Discount quantityDiscount = new QuantityDiscount(new NoDiscount());

    String description = quantityDiscount.getDescription(product);
    assertThat(description).isEqualTo("Quantity Discount of 10 kr per product applied.");
  }

  @Test
  void testQuantityDiscountChainedWithNextDiscount() {
    Product product = new Product("Bread", 20.0, 5);
    Discount discountChain = new QuantityDiscount(new NoDiscount());

    double discount = discountChain.apply(product);
    assertThat(discount).isEqualTo(5 * 10.0);

    String description = discountChain.getDescription(product);
    assertThat(description).isEqualTo("Quantity Discount of 10 kr per product applied.");
  }

  @Test
  void testQuantityDiscountChainedWithOtherDiscount() {
    Product product = new Product("Milk", 30.0, 5);
    Discount discountChain = new QuantityDiscount(new MilkDiscount(new NoDiscount()));

    double discount = discountChain.apply(product);
    assertThat(discount).isEqualTo((30.0 * 5 * 0.05) + (5 * 10.0));

    String description = discountChain.getDescription(product);
    assertThat(description).isEqualTo("Quantity Discount of 10 kr per product applied. 5% Milk Discount applied.");
  }
}
