package tests.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Controller.PurchasedController;
import src.mocks.BookWriterServiceMock;
import src.mocks.PurchasedWriterServiceMock;
import src.model.PurchasedBook;
import src.model.SoldBooks;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class PurchasedControllerTest {

    private PurchasedController purchasedController;
    private PurchasedWriterServiceMock purchasedWriterServiceMock;
    private BookWriterServiceMock bookWriterServiceMock;

    @BeforeEach
    void setUp() {
        purchasedWriterServiceMock = new PurchasedWriterServiceMock();
        bookWriterServiceMock = new BookWriterServiceMock();
        purchasedController = new PurchasedController(purchasedWriterServiceMock, bookWriterServiceMock);
    }

    @Test
    void testAddBook() {
        // Mock data
        int position = 1;
        String ISBN = "1234567890";
        int quantity = 5;

        // Call the addBook method
        purchasedController.addBook(position, ISBN, quantity);

        // Verify that the PurchasedController has the expected purchasedTable
        assertEquals(1, purchasedController.getPurchasedTable().size());
        PurchasedBook addedBook = purchasedController.getPurchasedTable().get(0);
        assertEquals(ISBN, addedBook.getISBN());
        assertEquals(quantity, addedBook.getQuantity());
    }

    @Test
    void testWritePurchasedBooksWithIOException() {
        // Set the flag to simulate an IOException
        purchasedWriterServiceMock.setShouldThrowIOException(true);

        // Call the method under test
        purchasedController.writePurchasedBooks();

        // Get the actual error message from the PurchasedController
        String actualErrorMessage = purchasedController.getErrorMessage();

        // Check if the actual error message contains the expected error description
        assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty(),
                "No error message was set in PurchasedController for IOException");

        assertTrue(actualErrorMessage.contains("Error writing purchased books"),
                "Error message does not contain expected description for IOException");
    }

    @Test
    void testReadPurchasedBooksWithIOException() {
        // Set the flag to simulate an IOException
        purchasedWriterServiceMock.setShouldThrowIOException(true);

        // Call the method under test
        purchasedController.readPurchasedBooks();

        // Get the actual error message from the PurchasedController
        String actualErrorMessage = purchasedController.getErrorMessage();

        // Check if the actual error message contains the expected error description
        assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty(),
                "No error message was set in PurchasedController for IOException");

        assertTrue(actualErrorMessage.contains("Error reading purchased books"),
                "Error message does not contain expected description for IOException");
    }

    @Test
    void testReadSoldBooksWithClassNotFoundException() {
        // Set the flag to simulate a ClassNotFoundException
        purchasedWriterServiceMock.setShouldThrowIOException(true);

        // Call the method under test
        purchasedController.readSoldBooks();

        // Get the actual error message from the PurchasedController
        String actualErrorMessage = purchasedController.getErrorMessage();

        // Check if the actual error message contains the expected error description
        assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty(),
                "No error message was set in PurchasedController for ClassNotFoundException");

        assertTrue(actualErrorMessage.contains("Error reading sold books"),
                "Error message does not contain expected description for ClassNotFoundException");
    }
    @Test
    void testWriteSoldBooksWithIOException() {
        // Set the flag to simulate an IOException
        purchasedWriterServiceMock.setShouldThrowIOException(true);

        // Call the method under test
        purchasedController.writeSoldBooks();

        // Get the actual error message from the PurchasedController
        String actualErrorMessage = purchasedController.getErrorMessage();

        // Check if the actual error message contains the expected error description
        assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty(),
                "No error message was set in PurchasedController for IOException");

        assertTrue(actualErrorMessage.contains("Error writing sold books"),
                "Error message does not contain expected description for IOException");

        // Ensure the expected method call was made to the mock
        assertTrue(purchasedWriterServiceMock.verifySoldBooks());
    }


    @Test
    void testWriteSoldBooksWithEmptyList() {
        // Mock an empty list of sold books
        ArrayList<SoldBooks> soldBooks = new ArrayList<>();

        // Set the expected sold books in the mock
        purchasedWriterServiceMock.expectSoldBooks(soldBooks);

        // Call the method under test
        purchasedController.writeSoldBooks();

        // Ensure the expected method call was made to the mock
        assertTrue(purchasedWriterServiceMock.verifySoldBooks());
    }

    @Test
    public void testSetFile() {


        File newFile = new File("newFile.bin");

        // Set the new file using setFile method
        purchasedController.setPurchasedFile(newFile);

        // Verify if the file in the BookController instance is updated
        File updatedFile = purchasedController.getPurchasedFile(); // Accessing the filename attribute directly

        Assertions.assertEquals(newFile, updatedFile,
                "File in PurchasedController was not updated after setFile method");
    }
}
