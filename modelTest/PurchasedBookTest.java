package modelTest;

import model.PurchasedBook;
import model.TheDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchasedBookTest {

    @Test
    public void testGetters() {
        TheDate date = new TheDate("01/01/2023");
        PurchasedBook purchasedBook = new PurchasedBook("1234567890", 5, date);

        assertEquals("1234567890", purchasedBook.getISBN());
        assertEquals(5, purchasedBook.getQuantity());
        assertEquals(date, purchasedBook.getBoughtDate());
    }

    @Test
    public void testToString() {
        TheDate date = new TheDate(1, 1, 2023);
        PurchasedBook purchasedBook = new PurchasedBook("1234567890", 5, date);

        assertEquals("PurchasedBook [ISBN=1234567890, quantity=5, purchasedDate=01/01/2023]", purchasedBook.toString());
    }
}
