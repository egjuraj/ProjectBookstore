package tests.test;
import src.Controller.LibrarianController;
import src.mocks.LibrarianWriterServiceMock;
import src.model.Librarian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LibrarianControllerTest {

    private LibrarianController librarianController;
    private LibrarianWriterServiceMock mockLibrarianWriterService;
    private File testFile;

    @BeforeEach
    void setUp() {
        mockLibrarianWriterService = new LibrarianWriterServiceMock();
        librarianController = new LibrarianController(mockLibrarianWriterService);
        testFile = new File("testData.bin");
        librarianController.setFile(testFile);
    }

    @Test
    void testCreateBill() throws IOException {
        Librarian librarian = new Librarian("John", "Doe", "john_doe", "password123", "1234567890", "Librarian", "50000");
        // Set expectations in the mock
        mockLibrarianWriterService.expect(librarian);

        // Call createBill method
        librarianController.createBill(librarian);

        // Verify if the expected cashier was written
        Assertions.assertTrue(mockLibrarianWriterService.verify());
    }
    @Test
    void testSetFileAndGetFile() {
        File testFile = new File("testData.bin");

        // Set file using setFile method
        librarianController.setFile(testFile);

        // Check if the retrieved file matches the set file
        Assertions.assertEquals(testFile, librarianController.getFile());
    }

    @Test
    void testSetAndGetCashier() {
        ArrayList<Librarian> testCashiers = new ArrayList<>();
        // Add tests.test Librarian objects to the list

        // Set and get cashier using setCashier and getCashier methods
        librarianController.setCashier(testCashiers);
        ArrayList<Librarian> retrievedCashiers = librarianController.getCashier();

        // Check if the retrieved cashier list matches the set list
        Assertions.assertEquals(testCashiers, retrievedCashiers);
    }
    @Test
    void testSetAndGetNrBills() {
        int testNrBills = 10;

        // Set and get nrBills using setNrBills and getNrBills methods
        librarianController.setNrBills(testNrBills);
        int retrievedNrBills = librarianController.getNrBills();

        // Check if the retrieved nrBills matches the set value
        Assertions.assertEquals(testNrBills, retrievedNrBills);
    }

}
