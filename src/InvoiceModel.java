import java.util.ArrayList;

public class InvoiceModel {

    ArrayList<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
    String invoiceTitle;
    String invoiceAddress;


    public InvoiceModel() {
    }

    public void addItem(String name, double price) {
        for (InvoiceItem item : invoiceItems) {
            if (item.getName().equals(name) || item.getPrice() == price) {
                item.addQuantity(1);
                return;
            }
        }
        invoiceItems.add(new InvoiceItem(name, price));

    }

    public void addItem(String name, double price, int quantity) {
        for (InvoiceItem item : invoiceItems) {
            if (item.getName().equals(name) && item.getPrice() == price) {
                item.addQuantity(quantity);
                return;
            }
        }
        invoiceItems.add(new InvoiceItem(name, price, quantity));
    }


    public String generateInvoice() {
        final int INVOICE_WIDTH = 53;
        final String INVOICE_LINE = "|-----------------------------------------------------|\n";
        final String INVOICE_SPACE = "|                                                     |\n";

        double total = 0;

        StringBuilder invoice = new StringBuilder();
        invoice.append(INVOICE_LINE);
        invoice.append("|" + centerString(INVOICE_WIDTH, invoiceTitle) + "|\n");
        invoice.append(INVOICE_SPACE);
        invoice.append(String.format("| %-51s |\n", invoiceAddress));
        invoice.append(INVOICE_SPACE);
        invoice.append(String.format("| %-21s | %-3s | %-9s | %-9s |\n", "Name", "Qty", "Price", "Total"));
        invoice.append("|-----------------------|-----|-----------|-----------|\n");
        for (InvoiceItem item : invoiceItems) {
            String name = item.getName();
            if (name.length() > 21) {
                name = name.substring(0, 18) + "...";
            }

            invoice.append(String.format("| %-21s | %-3d | $%-8.2f | $%-8.2f |\n", name, item.getQuantity(), item.getPrice(), item.getTotalPrice()));
            total += item.getTotalPrice();
        }
        invoice.append(INVOICE_LINE);
        invoice.append(String.format("| Total:                                    $%-8.2f |\n", total ));
        invoice.append(INVOICE_LINE);

        return invoice.toString();

    }

    private static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void addTitle(String title) {
        invoiceTitle = title;
    }

    public void addAddress(String address) {
        invoiceAddress = address;
    }
}
