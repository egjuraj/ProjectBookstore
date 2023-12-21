package test;

import Controller.PurchasedController;
import model.PurchasedBook;
import model.TheDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchasedControllerTest {
    private PurchasedController purchasedController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        purchasedController = new PurchasedController();
        purchasedController.getPurchasedTable().clear();
    }

    @Test
    public void testAddBook() {
        // Creating a sample date for testing
        TheDate testDate = new TheDate(12, 12, 2023); // Example date (day, month, year)

        // Adding a book to the purchased list
        purchasedController.addBook(0, "ISBN123", 5); // Assuming position 0 in the book controller, ISBN123, quantity 5

        // Getting the purchased books list
        ArrayList<PurchasedBook> purchasedBooks = purchasedController.getPurchasedTable();

        // Verifying the book is added to the purchased books list
        assertEquals(1, purchasedBooks.size(), "Number of purchased books should be 1");

        // Getting the first book added (assuming only one book is added for testing)
        PurchasedBook addedBook = purchasedBooks.get(0);

        // Verifying the details of the added book
        assertEquals("ISBN123", addedBook.getISBN(), "ISBN does not match");
        assertEquals(5, addedBook.getQuantity(), "Quantity does not match");
      // assertEquals(testDate.toString(), addedBook.getBoughtDate().toString(), "Date does not match");
    }
    @Test
    public void testAddBook_ValidISBNAndQuantity() {
        int initialSize = purchasedController.getPurchasedTable().size(); // Get initial size
        purchasedController.addBook(0, "ISBN123", 5); // Valid ISBN and quantity
        assertEquals(initialSize + 1, purchasedController.getPurchasedTable().size(),
                "Purchased book not added when providing valid ISBN and quantity");
    }

    // Test for invalid ISBN
    @Test
    public void testAddBook_InvalidISBN() {
        int initialSize = purchasedController.getPurchasedTable().size(); // Get initial size
        purchasedController.addBook(0, "", 5); // Invalid empty ISBN
        assertEquals(initialSize, purchasedController.getPurchasedTable().size(),
                "Purchased book added with invalid ISBN");
    }

    // Test for invalid quantity (e.g., negative, zero)
    @Test
    public void testAddBook_InvalidQuantity() {
        int initialSize = purchasedController.getPurchasedTable().size(); // Get initial size
        purchasedController.addBook(0, "ISBN123", -1); // Invalid negative quantity
        assertEquals(initialSize, purchasedController.getPurchasedTable().size(),
                "Purchased book added with invalid negative quantity");
    }

    // Test for border cases (e.g., maximum/minimum quantity)
    @Test
    public void testAddBook_BorderCases() {
        purchasedController.addBook(0, "ISBN123", 0); // Boundary case: zero quantity
        purchasedController.addBook(0, "ISBN123", Integer.MAX_VALUE); // Boundary case: maximum quantity
        assertEquals(1, purchasedController.getPurchasedTable().size(),
                "Purchased book added with boundary quantity");
    }
    @Test
    public void testAddBook_NullISBN_NegativePosition() {
        int initialSize = purchasedController.getPurchasedTable().size(); // Get initial size
        purchasedController.addBook(0,null,-5); // Null ISBN and negative position
        assertEquals(initialSize, purchasedController.getPurchasedTable().size(),
                "Purchased book added with null ISBN and negative position");
    }

}