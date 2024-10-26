public class InvoiceItem {

    private String name;
    private int quantity;
    private double price;

    public InvoiceItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public InvoiceItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
