package se.lernia.lab;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

  @Test
  void testProductCreation() {
    Product product = new Product("Bread", 20.0, 3);

    assertThat(product.name()).isEqualTo("Bread");
    assertThat(product.price()).isEqualTo(20.0);
    assertThat(product.quantity()).isEqualTo(3);
  }

  @Test
  void testProductPriceCalculation() {
    Product product = new Product("Milk", 30.0, 5);

    double totalPrice = product.price() * product.quantity();
    assertThat(totalPrice).isEqualTo(150.0);
  }
}
