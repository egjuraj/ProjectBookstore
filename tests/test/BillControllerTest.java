package tests.test;

import src.Controller.BillController;
import src.mocks.BillServiceMock;
import src.model.Bill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


class BillControllerTest {
    BillServiceMock mockBillWriterService;
    private BillController billController;
    private File testFile;

    @BeforeEach
    void setUp() {
        mockBillWriterService = new BillServiceMock();
        billController = new BillController(mockBillWriterService);
        testFile = new File("testBillFile.bin");
    }

    @Test
    public void testAddBill() {
        Bill bill = new Bill("Librarian", "Supplier", LocalDate.now(), 100.0, 5);

        billController.addBill(bill);

        ArrayList<Bill> bills = billController.getBills();
        assertTrue(bills.contains(bill));
    }
    @Test
    void testWriteBill() {
        Bill testBill = new Bill("Librarian", "Supplier", LocalDate.now(), 100.0, 5);
        billController.addBill(testBill);
        // Set expectations in the mock
        mockBillWriterService.expect(testBill);

        // Call the writeBill method
        billController.writeFile();

        // Verify if the expected bill was written
        Assertions.assertTrue(mockBillWriterService.verify());
    }
    @Test
    public void testWriteBooksWithIOException() {
        // Mock the BookWriter interface


        // Create a sample book for testing
        Bill testBill = new Bill("Librarian", "Supplier", LocalDate.now(), 100.0, 5);
        billController.addBill(testBill);
        // Set the flag to simulate an IOException
        mockBillWriterService.setShouldThrowIOException(true);

        // Call the method under tests.test
        billController.writeFile();

        // Get the actual error message from the BookController
        String actualErrorMessage = billController.getErrorMessage();

        // Print the actual error message for further debugging
        System.out.println("Actual Error Message: " + actualErrorMessage);

        // Check if the actual error message contains the expected error description
        Assertions.assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty(),
                "No error message was set in BookController for IOException");

        Assertions.assertTrue(actualErrorMessage.contains("Error writing books to file"),
                "Error message does not contain expected description for IOException");
    }

    @Test
    void testReadBooksFromFileIOException() {

        // Simulate an IOException when reading books
        mockBillWriterService.setShouldThrowIOException(true);

        // Attempt to read books from file
        billController.readBill();

        // Verify that an error message is set
        String errorMessage = billController.getErrorMessage();
        Assertions.assertTrue(errorMessage != null && !errorMessage.isEmpty());

    }
    @Test
    void TestSetFile(){



            File newFile = new File("newFile.bin");

            // Set the new file using setFile method
            billController.setFile(newFile);

            // Verify if the file in the BookController instance is updated
            File updatedFile = billController.filename; // Accessing the filename attribute directly

            Assertions.assertEquals(newFile, updatedFile,
                    "File in BookController was not updated after setFile method");

    }

}