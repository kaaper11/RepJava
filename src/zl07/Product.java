package zl07;

public abstract class Product implements Sellable {
    private String name;
    private int price;
    private String category;

    public Product(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    protected String getName() {
        return name;
    }

    protected String getCategory() {
        return category;
    }

    protected int getPrice() {
        return price;
    }


    public abstract String getDescription();
}
