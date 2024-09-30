package se.lernia.lab;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class FridayDiscount extends BaseDiscount {

  public FridayDiscount(Discount nextDiscount) {
    super(nextDiscount);
  }

  @Override
  protected boolean isApplicable(Product product) {
    return LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY;
  }

  @Override
  protected double calculateDiscount(Product product) {
    return product.price() * product.quantity() * 0.10;
  }

  @Override
  protected String getDiscountDescription() {
    return "It's Friday! You get a 10% discount.";
  }
}
