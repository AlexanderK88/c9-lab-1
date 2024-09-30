package se.lernia.lab;

public abstract class BaseDiscount implements Discount {

  protected Discount nextDiscount;

  public BaseDiscount(Discount nextDiscount){
    this.nextDiscount = nextDiscount;
  }

  protected abstract boolean isApplicable(Product product);
  protected abstract double calculateDiscount(Product product);
  protected abstract String getDiscountDescription();


  @Override
  public double apply(Product product) {
    double discountAmount = 0;

    if(isApplicable(product)) {
      discountAmount += calculateDiscount(product);
    }

    if (nextDiscount != null) {
      discountAmount += nextDiscount.apply(product);
    }
    return discountAmount;
  }

  @Override
  public String getDescription(Product product) {
    StringBuilder description = new StringBuilder();

    if (isApplicable(product)) {
      description.append(getDiscountDescription()).append(" ");
    }

    if (nextDiscount != null) {
      description.append(nextDiscount.getDescription(product));
    }

    return description.toString().trim();
  }
}
