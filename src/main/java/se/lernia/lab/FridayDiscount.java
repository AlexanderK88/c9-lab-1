package se.lernia.lab;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class FridayDiscount extends BaseDiscount {

  private final LocalDate currentDate;

  public FridayDiscount(Discount nextDiscount) {
    this(nextDiscount, LocalDate.now());
  }

  public FridayDiscount(Discount nextDiscount, LocalDate currentDate) {
    super(nextDiscount);
    this.currentDate = currentDate;
  }

  @Override
  protected boolean isApplicable(Product product) {
    return currentDate.getDayOfWeek() == DayOfWeek.FRIDAY;
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
