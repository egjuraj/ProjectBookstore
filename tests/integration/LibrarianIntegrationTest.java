package tests.integration;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import src.Controller.LibrarianController;
import src.impl.LibrarianFileHandler;
import src.model.Librarian;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibrarianIntegrationTest {

    private static final String TEST_FILE_NAME = "testLibrarian.bin";
    private LibrarianController librarianController;

    @BeforeEach
    void setUp(@TempDir File tempDir) {
        // Initialize a temporary file for testing
        File testFile = new File(tempDir, TEST_FILE_NAME);

        // Create a LibrarianFileHandler with the temporary file
        LibrarianFileHandler librarianFileHandler = new LibrarianFileHandler();

        // Set up the controller with the LibrarianFileHandler
        librarianController = new LibrarianController(librarianFileHandler);
        librarianController.setFile(testFile);
    }

    @AfterEach
    void tearDown() {
        // Clean up: delete the temporary file after each test
        File testFile = librarianController.getFile();
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testCreateBill() throws IOException, ClassNotFoundException {
        // Prepare test data
        Librarian testLibrarian = new Librarian("John", "Doe", "john_doe", "password123", "1234567890", "Librarian", "50000");

        // Add a Librarian through the controller
        librarianController.createBill(testLibrarian);


        // Read Librarians from the file
        ArrayList<Librarian> readLibrarians = librarianController.getCashier();

        // Verify the controller's Librarians list is the same as the one read from the file
        assertEquals(readLibrarians, librarianController.getCashier(), "The lists are not equal");
        // Verify the number of bills
        assertEquals(1, librarianController.getNrBills(), "The number of bills is incorrect");
    }
}
