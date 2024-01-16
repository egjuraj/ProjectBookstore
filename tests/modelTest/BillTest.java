package tests.modelTest;

import src.model.Bill;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BillTest {

    @Test
    public void testBillDetails() {
        // Create a sample bill
        LocalDate billDate = LocalDate.of(2023, 12, 31);
        Bill bill = new Bill("Librarian", "Supplier A", billDate, 500.0, 10);

        // Test bill details
        assertEquals("Librarian", bill.getlibrarianUsername());
        assertEquals(billDate, bill.getBillDate());
        assertEquals(500.0, bill.getTotal());
        assertEquals("Supplier A", bill.supplier);
        assertEquals(10, bill.quantitySold);
    }

    @Test
    public void testGetBillNo() {
        // Ensure bill number increments properly
        int initialBillNo = Bill.getBillNo();
        LocalDate billDate = LocalDate.now();
        Bill bill1 = new Bill("Librarian", "Supplier A", billDate, 500.0, 10);
        assertEquals(initialBillNo + 1, Bill.getBillNo());

        Bill bill2 = new Bill("Librarian", "Supplier B", billDate, 600.0, 15);
        assertEquals(initialBillNo + 2, Bill.getBillNo());
    }

    @Test
    public void testToString() {
        // Create a sample bill
        LocalDate billDate = LocalDate.of(2023, 12, 31);
        Bill bill = new Bill("Librarian", "Supplier A", billDate, 500.0, 10);

        // Verify the string representation of the bill
        String expected = "Bill \n" +
                "billName=Librarian\n" +
                " supplier=Supplier A\n" +
                " billDate=2023-12-31\n" +
                " price=500.0\n" +
                " quantitySold=10\n\n";
        assertEquals(expected, bill.toString());
    }
}
