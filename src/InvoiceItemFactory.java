public class InvoiceItemFactory {

    public static InvoiceItem createInvoiceItem(String name, double price) {
        return new InvoiceItem(name, price);
    }

    public static InvoiceItem createInvoiceItem(String name, double price, int quantity) {
        return new InvoiceItem(name, price, quantity);
    }
}
