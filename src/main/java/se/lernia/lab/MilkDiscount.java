package se.lernia.lab;

public class MilkDiscount extends BaseDiscount {

  public MilkDiscount(Discount nextDiscount) {
    super(nextDiscount != null ? nextDiscount : new NoDiscount());
  }

  @Override
  protected boolean isApplicable(Product product) {
    return product.name().equalsIgnoreCase("Milk");
  }

  @Override
  protected double calculateDiscount(Product product) {
    return product.price() * product.quantity() * 0.05;
  }

  @Override
  protected String getDiscountDescription() {
    return "5% Milk Discount applied.";
  }
}

