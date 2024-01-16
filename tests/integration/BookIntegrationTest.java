
package tests.integration;

        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import src.Controller.BookController;
        import src.impl.BookFileHandler;
        import src.model.Book;

        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;

        import static org.junit.jupiter.api.Assertions.*;

public class BookIntegrationTest {

    private static final String TEST_FILE_PATH = "testProduct.bin";
    private BookController bookController;

    @BeforeEach
    void setUp() {
        // Initialize a temporary file for testing
        File testFile = new File(TEST_FILE_PATH);

        // Create a BookFileHandler
        BookFileHandler bookFileHandler = new BookFileHandler();

        // Set up the controller with the BookFileHandler and set the file path
        bookController = new BookController(bookFileHandler);
        bookController.setFile(testFile);

    }

    @AfterEach
    void tearDown() {
        // Clean up: delete the temporary file after each test
        File testFile = new File(TEST_FILE_PATH);
        testFile.delete();
    }

    @Test
    void testAddBook() throws IOException, ClassNotFoundException {
        // Prepare test data
        Book testBook = new Book("1234567890", "Title", "Category", "Supplier");

        // Add a book through the controller
        bookController.addBooks(testBook);

        // Read books from the file
        ArrayList<Book> readBooks = bookController.getBooks();

        // Verify the integration by checking if the test book is in the list
        assertTrue(readBooks.contains(testBook), "The testBook is not found in readBooks");

        // Verify the controller's books list is the same as the one read from the file
        assertEquals(readBooks, bookController.getBooks(), "The lists are not equal");
    }

    @Test
    void testAddQuantity() throws IOException, ClassNotFoundException {
        // Prepare test data
        Book testBook = new Book("1234567890", "Title", "Category", "Supplier");

        // Add a book through the controller
        bookController.addBooks(testBook);
        // Add quantity through the controller
        bookController.addQuantity(0, 3);

        // Read books from the file
        ArrayList<Book> readBooks = bookController.getBooks();
       //Verify That the  ReadFile is not null.
        assertNotNull(bookController.readBooks());
        int expectedQuantity = 3; // 5 (initial) + 3 (added)
        // Verify the integration by checking if the quantity is updated
        assertEquals(expectedQuantity, readBooks.get(0).getStock(), "The quantity is not updated");
    }
}
