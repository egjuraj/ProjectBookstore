
package tests.test;

import src.Controller.BookController;
import src.service.BookWriterService;
import src.mocks.BookWriterServiceMock;
import src.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;



public class BookControllerTest {

    @Mock
    private BookWriterService bookWriterServiceMock;


    @InjectMocks
    private BookController bookController;



    @BeforeEach
    public void setUp() {
        bookWriterServiceMock = new BookWriterServiceMock();
        bookController = new BookController(bookWriterServiceMock);
        bookController.getBooks().clear();

    }


    @Test
    public void testUseISBN() {
        // Create a new book and add it to the controller
        Book book = new Book("1234567890", "Title", "Category", "Supplier");
        bookController.addBooks(book);
        // Test with an existing ISBN

        assertTrue(bookController.useISBN("1234567890"));

        // Test with a non-existent ISBN
        assertFalse(bookController.useISBN("9999999999"));

    }

    @Test
    public void testAddBook() {
        // we dont have book
        assertEquals(0, bookController.getBooks().size());
        //add book in a empty list
        Book book = new Book("1234567890", "Title", "Category", "Supplier");
        bookController.addBooks(book);

        assertEquals(1, bookController.getBooks().size());
        //Add book in existing list
        Book newBook = new Book("1434567890", "Title", "Category", "Supplier");
        bookController.addBooks(newBook);

        assertEquals(2, bookController.getBooks().size());

    }

    @Test
    void testGetPosition() {

        // A book with an ISBN that exists in the list of books.
        Book existingBook = new Book("1234567890", "Title", "Category", "Supplier");
        bookController.addBooks(existingBook);
        assertEquals(0, bookController.getPosition(existingBook)); // Should return the index 0
        // A book with an ISBN that does not exist in the list of books.
        Book nonExistingBook = new Book("9999999999", "Title", "Category", "Supplier");
        assertEquals(-1, bookController.getPosition(nonExistingBook)); // Should return -1
    }

    @Test
    void deleteBookTest() {
        //Delete existing book
        Book existingBook = new Book("1234567890", "Title", "Category", "Supplier");
        bookController.addBooks(existingBook);

        bookController.deleteBook(existingBook);
        assertEquals(0, bookController.getBooks().size()); // The list should be empty after deletion

        //Delete non existing book
        Book nonExistingBook = new Book("9999999999", "Title", "Category", "Supplier");

        bookController.deleteBook(nonExistingBook);
        assertEquals(0, bookController.getBooks().size());

    }

    @Test
    public void testAddQuantity() {
        Book existingBook = new Book("1234567890", "Title", "Category", "Supplier");
        bookController.addBooks(existingBook);

        // Adding positive quantity
        int initialStock = existingBook.getStock();
        bookController.addQuantity(0, 5);
        assertEquals(initialStock + 5, existingBook.getStock());

        // Adding negative quantity
        bookController.addQuantity(0, -10);
        assertEquals(0, existingBook.getStock()); // Stock shouldn't be negative

        // Adding quantity at invalid position
        bookController.addQuantity(5, 10); // Invalid position
        assertEquals(0, existingBook.getStock()); // Stock remains unchanged
        //Adding invalid position
        bookController.addQuantity(-1,2);
    }

    @Test
    public void testWriteBooks() throws IOException {
        // Mock the BookWriter interface
        BookWriterServiceMock mockedBookWriter = new BookWriterServiceMock();
//        File testFile = new File("testFile.bin");
        // Create a tests.test instance of BookController with the mocked BookWriter
        BookController bookController = new BookController(mockedBookWriter);

        // Create a sample book for testing
        Book testBook = new Book("1234567890", "Test Book", "Category", "Supplier");

        // Add the tests.test book to the controller
        bookController.addBooks(testBook);

        // Call the method under tests.test
        bookController.writeBooks();

        // Verify that the writeBooksToFile method was called with the expected arguments
        mockedBookWriter.expect(testBook);
        Assertions.assertTrue(mockedBookWriter.verify());
//        verify(mockedBookWriter, times(1)).writeBooksToFile(expectedBooks, testFile);
    }
    @Test
    public void testWriteBooksWithIOException() {
        // Mock the BookWriter interface
        BookWriterServiceMock mockedBookWriter = new BookWriterServiceMock();

        // Create a tests.test instance of BookController with the mocked BookWriter
        BookController bookController = new BookController(mockedBookWriter);

        // Create a sample book for testing
        Book testBook = new Book("1234567890", "Test Book", "Category", "Supplier");

        // Add the tests.test book to the controller
        bookController.addBooks(testBook);

        // Set the flag to simulate an IOException
        mockedBookWriter.setShouldThrowIOException(true);

        // Call the method under tests.test
        bookController.writeBooks();

        // Get the actual error message from the BookController
        String actualErrorMessage = bookController.getErrorMessage();

        // Print the actual error message for further debugging
        System.out.println("Actual Error Message: " + actualErrorMessage);

        // Check if the actual error message contains the expected error description
        Assertions.assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty(),
                "No error message was set in BookController for IOException");

        Assertions.assertTrue(actualErrorMessage.contains("Error writing books to file"),
                "Error message does not contain expected description for IOException");
    }
    @Test
    void testAddBooksAndWriteToFile() {
        BookWriterServiceMock mockedBookWriter=new BookWriterServiceMock();
        BookController bookController=new BookController(mockedBookWriter);
        Book testBook = new Book("1234567890", "Test Book", "Category", "Supplier");
        bookController.addBooks(testBook);

        // Verify if the book was added to the controller
        Assertions.assertTrue(bookController.getBooks().contains(testBook));

        // Write books to a file
        bookController.writeBooks();

        // Verify if writeBooksToFile method was called with the expected book
        mockedBookWriter.expect(testBook);
        Assertions.assertTrue(mockedBookWriter.verify());
    }
    @Test
    void testReadBooksFromFileIOException() {
        BookWriterServiceMock mockedBookWriter=new BookWriterServiceMock();
        BookController bookController=new BookController(mockedBookWriter);
        // Simulate an IOException when reading books
        mockedBookWriter.setShouldThrowIOException(true);

        // Attempt to read books from file
        bookController.readBooks();

        // Verify that an error message is set
        String errorMessage = bookController.getErrorMessage();
        Assertions.assertTrue(errorMessage != null && !errorMessage.isEmpty());

        }


    @Test
    void testSetFile() {


         BookWriterServiceMock mockedBookWriter= new BookWriterServiceMock();
         BookController bookController=new BookController(mockedBookWriter);

            File newFile = new File("newFile.bin");

            // Set the new file using setFile method
            bookController.setFile(newFile);

            // Verify if the file in the BookController instance is updated
            File updatedFile = bookController.filename; // Accessing the filename attribute directly

            Assertions.assertEquals(newFile, updatedFile,
                    "File in BookController was not updated after setFile method");
        }
}





