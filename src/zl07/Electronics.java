package zl07;

public class Electronics extends Product {
    private int warranty;
    private final double VAT = 1.23;

    private Electronics(String name, int price, String category, int warranty) {
        super(name, price, category);
        this.warranty = warranty;
    }

    public static Electronics create(String name, int price, String category, int expiryDate) {
        return new Electronics(name, price, category, expiryDate);
    }

    @Override
    public String getDescription() {
        return "Elektornika " + super.getName() + ", " + super.getCategory();
    }

    @Override
    public double calculateFinalPrice() {
        return super.getPrice() * VAT;
    }
}
