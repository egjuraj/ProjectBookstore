package tests.modelTest;

import src.model.SoldBooks;
import org.junit.jupiter.api.Test;
import src.model.TheDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SoldBooksTest {

    @Test
    public void testSoldBooks() {
        String ISBN = "1234567890";
        String titleOfBook = "Test Book";
        int quantity = 5;
        double price = 50.0;

        SoldBooks soldBooks = new SoldBooks(ISBN, titleOfBook, quantity, price);

        // Verify the getters return the expected values
        assertEquals(ISBN, soldBooks.getISBN());
        assertEquals(titleOfBook, soldBooks.getTitleOfBook());
        assertEquals(quantity, soldBooks.getQuantity());
        assertEquals(price, soldBooks.getPrice());

        // Verify the date object is not null
        assertNotNull(soldBooks.getDate());

        // Verify toString method
   //   String expectedString = "SoldBooks [ISBN=" + ISBN + ", titleOfBook=" + titleOfBook +
          //      ", quantity=" + quantity + ", sellprice=" + price + ", sellDate=" +
           //     soldBooks.getDate() + "]";
       // assertEquals(expectedString, soldBooks.toString());
    }

    @Test
    public void testSetters() {
        SoldBooks soldBooks = new SoldBooks("123", "Book1", 10, 100.0);
        soldBooks.setBarcode("456");
        soldBooks.setName("Book2");
        soldBooks.setQuantity(20);
        soldBooks.setPrice(200.0);

        assertEquals("456", soldBooks.getISBN());
        assertEquals("Book2", soldBooks.getTitleOfBook());
        assertEquals(20, soldBooks.getQuantity());
        assertEquals(200.0, soldBooks.getPrice());
    }
    @Test
    public void testSetDate() {
        SoldBooks soldBooks = new SoldBooks("1234567890", "Test Book", 5, 50.0);
        TheDate date = new TheDate(1, 1, 2023);

        soldBooks.setDate(date);

        assertEquals(date, soldBooks.getDate());
    }

    @Test
    public void testToString() {
        String ISBN = "1234567890";
        String titleOfBook = "Test Book";
        int quantity = 5;
        double price = 50.0;
        TheDate date = new TheDate(1, 1, 2023);

        SoldBooks soldBooks = new SoldBooks(ISBN, titleOfBook, quantity, price);
        soldBooks.setDate(date);

        String expectedToString = "SoldBooks [ISBN=" + ISBN + ", titleOfBook=" + titleOfBook +
                ", quantity=" + quantity + ", sellprice=" + price + ", sellDate=" + date + "]";
        assertEquals(expectedToString, soldBooks.toString());
    }
}
