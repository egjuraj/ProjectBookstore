package tests.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import src.Controller.BookController;
import src.Controller.PurchasedController;
import src.impl.BookFileHandler;
import src.impl.PurchasedFileHandler;
import src.model.PurchasedBook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchasedIntegrationTest {

    private static final String TEST_SOLD_FILE_NAME = "testSold.bin";
    private static final String TEST_PURCHASED_FILE_NAME = "testPurchased.bin";
    private PurchasedController purchasedController;
    BookController bookController;

    @BeforeEach
    void setUp(@TempDir File tempDir) {
        // Initialize temporary files for testing
        File testSoldFile = new File(tempDir, TEST_SOLD_FILE_NAME);
        File testPurchasedFile = new File(tempDir, TEST_PURCHASED_FILE_NAME);

        // Create a PurchasedFileHandler with the temporary files
        PurchasedFileHandler purchasedFileHandler = new PurchasedFileHandler();
        BookFileHandler bookFileHandler = new BookFileHandler();
        bookController=new BookController(bookFileHandler);
        // Set up the controller with the PurchasedFileHandler
        purchasedController = new PurchasedController(purchasedFileHandler, bookFileHandler);
        purchasedController.setPurchasedFile(testPurchasedFile);
    }

    @AfterEach
    void tearDown() {
        // Clean up: delete the temporary files after each test
        File testPurchasedFile = purchasedController.getPurchasedFile();


        if (testPurchasedFile.exists()) {
            testPurchasedFile.delete();
        }
    }

    @Test
    void testAddBook() throws IOException, ClassNotFoundException {
        // Prepare test data
        String ISBN = "1234567890";
        int quantity = 5;

        // Add a book through the controller
        purchasedController.addBook(0, ISBN, quantity);


        ArrayList<PurchasedBook> readPurchasedBooks = purchasedController.getPurchasedTable();
        // Read SoldBooks and PurchasedBooks from the files

        // Verify the added book in PurchasedBooks
        assertEquals(1, readPurchasedBooks.size(), "One book should be added to PurchasedBooks");
        assertEquals(ISBN, readPurchasedBooks.get(0).getISBN(), "ISBN should match");
    }
}
