import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceModelTest {

    InvoiceModel model;

    @BeforeEach
    void setUp() {
        model = new InvoiceModel();
    }

    @Test
    void addItem() {
        model.addItem("test", 3.45, 4);
        assertEquals("test", model.invoiceItems.getFirst().getName());
        assertEquals(3.45, model.invoiceItems.getFirst().getPrice());
        assertEquals(4, model.invoiceItems.getFirst().getQuantity());

        model.addItem("test", 3.45);
        assertEquals("test", model.invoiceItems.getFirst().getName());
        assertEquals(3.45, model.invoiceItems.getFirst().getPrice());
        assertEquals(5, model.invoiceItems.getFirst().getQuantity());

        model.addItem("test", 5.67);
        assertEquals("test", model.invoiceItems.getFirst().getName());
        assertEquals(3.45, model.invoiceItems.getFirst().getPrice());
        assertEquals(5, model.invoiceItems.getFirst().getQuantity());

        assertEquals("test", model.invoiceItems.get(1).getName());
        assertEquals(5.67, model.invoiceItems.get(1).getPrice());
        assertEquals(1, model.invoiceItems.get(1).getQuantity());
    }

    @Test
    void addTitle() {
        model.addTitle("test");
        assertEquals("test", model.invoiceTitle);
    }

    @Test
    void addAddress() {
        model.addAddress("test");
        assertEquals("test", model.invoiceAddress);
    }

    @Test
    void generateInvoice() {
        model.addTitle("Test Invoice");
        model.addAddress("123 Test St\nTest, TS 12345");
        model.addItem("test", 3.45, 4);
        model.addItem("test", 3.45);
        model.addItem("test", 5.67);
        model.addItem("test test test test test test test test test", 5, 2);
        String invoice = model.generateInvoice();
        String expectedOutput =
                "|-----------------------------------------------------|\n" +
                "|                     Test Invoice                    |\n" +
                "|                                                     |\n" +
                "| 123 Test St                                         |\n" +
                "| Test, TS 12345                                      |\n" +
                "|                                                     |\n" +
                "|-----------------------------------------------------|\n" +
                "| Item                  | Qty |   Price   |   Total   |\n" +
                "|-----------------------|-----|-----------|-----------|\n" +
                "| test                  | 5   | $3.45     | $17.25    |\n" +
                "| test                  | 5   | $3.45     | $17.25    |\n" +
                "| test test test tes... | 5   | $3.45     | $17.25    |\n" +
                "|-----------------------------------------------------|\n" +
                "| Total:                                    $51.75    |\n" +
                "|-----------------------------------------------------|";
        assertEquals(expectedOutput, invoice);
    }
}