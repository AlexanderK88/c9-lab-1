# Discount Chain Application

This Java application demonstrates the use of the **Decorator Pattern** to create a flexible discount system for products. The system applies various discounts, such as discounts based on the product's type, quantity, or day of the week (e.g., a Friday discount). This application is structured with clear separation of concerns, allowing for reusable and extendable components.

## Features

- **FridayDiscount**: A discount applied on Fridays, offering 10% off the total price.
- **MilkDiscount**: A 5% discount applied only to products named "Milk."
- **QuantityDiscount**: A discount that gives 10kr off per product if the quantity is 5 or more.
- **NoDiscount**: A Null Object pattern to handle cases where no further discounts apply, preventing null checks in the discount chain.

## Project Structure

```
src/main/java/se/lernia/lab/
├── BaseDiscount.java
├── Discount.java
├── FridayDiscount.java
├── Main.java
├── MilkDiscount.java
├── NoDiscount.java
├── Product.java
├── QuantityDiscount.java

src/test/java/se/lernia/lab/
├── DiscountTest.java
├── FridayDiscountTest.java
├── MainTest.java
├── MilkDiscountTest.java
├── ProductTest.java
├── QuantityDiscountTest.java
```

## Example Output

Here’s an example of how the application works when running with several products:

```
Product: Milk
Unit Price: 30.0 kr
Quantity: 5
Total Price: 150.00 kr
Discount Amount: 57.50 kr
Final Price: 92.50 kr
Discounts Applied:
- 5% Milk Discount applied.
- Quantity Discount of 10 kr per product applied.
-----------------------------------
Product: Bread
Unit Price: 20.0 kr
Quantity: 5
Total Price: 100.00 kr
Discount Amount: 50.00 kr
Final Price: 50.00 kr
Discounts Applied:
- Quantity Discount of 10 kr per product applied.
-----------------------------------
```

## Design Patterns Used

### 1. **Decorator Pattern**:
Each discount type is implemented as a decorator that wraps another discount. This allows multiple discounts to be chained together dynamically. For example, `MilkDiscount` can be combined with `QuantityDiscount` to apply both discounts to eligible products.

### 2. **Null Object Pattern**:
The `NoDiscount` class is used to avoid `null` checks in the discount chain. When no more discounts apply, the system uses `NoDiscount`, which does nothing but keeps the flow consistent.

## Testing

- **Unit Tests**: Each discount class has its own set of tests that validate its behavior. Tests ensure that each discount is correctly applied or skipped depending on the product's attributes.
- **Mutation Tests**: PIT (Pitest) is used to measure the quality of the unit tests by introducing small changes (mutations) in the code and checking if the tests catch these changes.

### Test Cases

- **MilkDiscountTest**: Tests whether the discount applies only to products named "Milk."
- **QuantityDiscountTest**: Ensures that the quantity discount applies correctly for products with a quantity of 5 or more.
- **FridayDiscountTest**: Validates that the Friday discount applies only on Fridays and when combined with other discounts.
- **MainTest**: Ensures the `applyDiscountAndDisplay` method outputs the correct results.
- **ProductTest**: Verifies that the `Product` class correctly handles its attributes and returns values as expected.

## How It Works

1. **Product Class**: Holds information about the product such as name, price, and quantity.
2. **Discount Interface**: Defines the `apply` and `getDescription` methods for applying discounts and getting discount descriptions.
3. **BaseDiscount Class**: An abstract class that handles chaining discounts and provides the base logic for applying and describing discounts.
4. **Discount Chain**: Discounts are chained together, and each discount applies if applicable, passing the remaining price down the chain.

### Example Discount Chain

```java
Discount discountChain = new FridayDiscount(new MilkDiscount(new QuantityDiscount(new NoDiscount())));
```

In this chain:
- `QuantityDiscount` applies first if the product quantity is 5 or more.
- `MilkDiscount` applies if the product is named "Milk."
- `FridayDiscount` applies only if today is Friday.
