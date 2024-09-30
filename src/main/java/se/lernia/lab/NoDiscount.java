package se.lernia.lab;

public class NoDiscount implements Discount {

  @Override
  public double apply(Product product) {
    return 0;
  }

  @Override
  public String getDescription(Product product) {
    return "";
  }
}
