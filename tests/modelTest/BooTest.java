package tests.modelTest;

import src.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNotNull;
import  static org.junit.Assert.assertNull;

public class BooTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        // Create a new Book object before each tests.test
        book = new Book("1234567890", "Test Book", "Category", "Supplier");
    }

    @Test
    public void testGettersAndSetters() {
        // Test the getters and setters of the Book class
        assertEquals("1234567890", book.getISBN());
        assertEquals("Test Book", book.getTitleOfBook());
        assertEquals("Category", book.getCategory());
        assertEquals("Supplier", book.getSupplier());

        book.setISBN("0987654321");
        book.setTitleOfBookame("New Title");
        book.setCategory("New Category");
        book.setSupplier("New Supplier");

        assertEquals("0987654321", book.getISBN());
        assertEquals("New Title", book.getTitleOfBook());
        assertEquals("New Category", book.getCategory());
        assertEquals("New Supplier", book.getSupplier());
    }

    @Test
    public void testStock() {
        // Test the stock-related methods of the Book class
        assertEquals(0, book.getStock());

        book.setStock(10);
        assertEquals(10, book.getStock());

        book.setStock(-5); // Setting negative stock should be allowed
        assertEquals(-5, book.getStock());
    }

    @Test
    public void testToString() {
        // Test the toString method of the Book class
        String expectedString = "Book [ISBN=1234567890, titleOfBook=Test Book, category=Category, supplier=Supplier, stock=0]";
        assertEquals(expectedString, book.toString());
    }


    @Test
    public void testDefaultConstructor() {
        // Test the default constructor
        Book book = new Book();
        assertNotNull(book); // Ensure the object is not null
        assertEquals(0, book.getStock()); // Ensure stock is initialized to 0 or default value
        assertNull(book.getISBN()); // Ensure ISBN is null or default value
        assertNull(book.getTitleOfBook()); // Ensure titleOfBook is null or default value
    }

    @Test
    public void testParameterizedConstructor() {
        // Test the parameterized constructor
        Book book = new Book("1234567890", "Test Book", 5);
        assertNotNull(book); // Ensure the object is not null
        assertEquals("1234567890", book.getISBN());
        assertEquals("Test Book", book.getTitleOfBook());
        assertEquals(5, book.getStock());
    }
}
