import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class InvoiceItemTest {

    InvoiceItem item;
    InvoiceItem item2;

    @BeforeEach
    void setUp() {
        item = new InvoiceItem("test", 3.45, 4);
        item2 = new InvoiceItem("test2", 5.67);
    }

    @Test
    void getName() {
        assertEquals("test", item.getName());
        assertEquals("test2", item2.getName());
    }

    @Test
    void getQuantity() {
        assertEquals(4, item.getQuantity());
        assertEquals(1, item2.getQuantity());
    }

    @Test
    void getPrice() {
        assertEquals(3.45, item.getPrice());
        assertEquals(5.67, item2.getPrice());
    }

    @Test
    void getTotalPrice() {
        assertEquals(13.80, item.getTotalPrice());
        assertEquals(5.67, item2.getTotalPrice());
    }

    @Test
    void addQuantity() {
        item.addQuantity(2);
        assertEquals(6, item.getQuantity());
    }
}