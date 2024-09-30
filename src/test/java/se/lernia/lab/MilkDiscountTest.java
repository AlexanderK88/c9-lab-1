package se.lernia.lab;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MilkDiscountTest {

  @Test
  void testMilkDiscountApplies() {
    Product milk = new Product("Milk", 30.0, 3);
    Discount milkDiscount = new MilkDiscount(new NoDiscount());

    double discount = milkDiscount.apply(milk);
    assertThat(discount).isEqualTo(30 * 3 * 0.05);
  }

  @Test
  void testMilkDiscountDoesNotApplyToNonMilkProduct() {
    Product bread = new Product("Bread", 20.0, 2);
    Discount milkDiscount = new MilkDiscount(new NoDiscount());

    double discount = milkDiscount.apply(bread);
    assertThat(discount).isEqualTo(0.0);
  }

  @Test
  void testMilkDiscountDescription() {
    Product milk = new Product("Milk", 30.0, 3);
    Discount milkDiscount = new MilkDiscount(new NoDiscount());

    String description = milkDiscount.getDescription(milk);
    assertThat(description).isEqualTo("5% Milk Discount applied.");
  }

  @Test
  void testMilkDiscountChainedWithNextDiscount() {
    Product milk = new Product("Milk", 30.0, 5);
    Discount discountChain = new MilkDiscount(new QuantityDiscount(new NoDiscount()));

    double discount = discountChain.apply(milk);
    assertThat(discount).isEqualTo((30 * 5 * 0.05) + (5 * 10));

    String description = discountChain.getDescription(milk);
    assertThat(description).isEqualTo("5% Milk Discount applied. Quantity Discount of 10 kr per product applied.");
  }
}
