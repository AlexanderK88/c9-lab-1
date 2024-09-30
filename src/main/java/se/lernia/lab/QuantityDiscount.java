package se.lernia.lab;

public class QuantityDiscount extends BaseDiscount{

  public QuantityDiscount(Discount nextDiscount) {
    super(nextDiscount);
  }

  @Override
  protected boolean isApplicable(Product product) {
    return product.quantity() >= 5;
  }

  @Override
  protected double calculateDiscount(Product product) {
    return product.quantity() * 10.00;
  }

  @Override
  protected String getDiscountDescription() {
    return "Quantity Discount of 10 kr per product applied.";
  }
}
