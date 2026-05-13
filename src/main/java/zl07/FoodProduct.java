package zl07;

public class FoodProduct extends Product {
    private int expiryDate;
    private final double VAT = 1.05;

    private FoodProduct(String name, int price, String category, int expiryDate) {
        super(name, price, category);
        this.expiryDate = expiryDate;
    }

    public static FoodProduct create(String name, int price, String category, int expiryDate) {
        return new FoodProduct(name, price, category, expiryDate);
    }

    @Override
    public String getDescription() {
        return "Jedzenie " + super.getName() + ", " + super.getCategory();
    }

    @Override
    public double calculateFinalPrice() {
        return super.getPrice() * VAT;
    }
}
