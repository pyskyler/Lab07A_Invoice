public class invoiceItemFactory {

    public static invoiceItem createInvoiceItem(String name, double price) {
        return new invoiceItem(name, price);
    }
}
