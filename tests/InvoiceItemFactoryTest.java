import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceItemFactoryTest {

    @Test
    void createInvoiceItem() {
        InvoiceItem item = InvoiceItemFactory.createInvoiceItem("test", 3.45);
        assertEquals("test", item.getName());
        assertEquals(3.45, item.getPrice());

        InvoiceItem item2 = InvoiceItemFactory.createInvoiceItem("test2", 5.67, 3);
        assertEquals("test2", item2.getName());
        assertEquals(5.67, item2.getPrice());
        assertEquals(3, item2.getQuantity());
    }
}