package se.lernia.lab;

public abstract class BaseDiscount implements Discount {

  protected Discount nextDiscount;

  public BaseDiscount(Discount nextDiscount) {
    this.nextDiscount = nextDiscount;
  }

  protected abstract boolean isApplicable(Product product);

  protected abstract double calculateDiscount(Product product);

  protected abstract String getDiscountDescription();


  @Override
  public double apply(Product product) {
    double discountAmount = 0;

    if (isApplicable(product)) {
      discountAmount += calculateDiscount(product);
    }
    return discountAmount + nextDiscount.apply(product);
  }

  @Override
  public String getDescription(Product product) {
    StringBuilder description = new StringBuilder();

    if (isApplicable(product)) {
      description.append(getDiscountDescription());
    }
    return description.append(nextDiscount.getDescription(product)).toString().trim();
  }
}
