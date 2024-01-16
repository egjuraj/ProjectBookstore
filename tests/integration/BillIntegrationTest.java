package tests.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Controller.BillController;
import src.impl.BillFileHandler;
import src.model.Bill;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillIntegrationTest {

    private static final String TEST_FILE_PATH = "testBill.bin";
    private BillController billController;

    @BeforeEach
    void setUp() {
        // Initialize a temporary file for testing
        File testfile=new File(TEST_FILE_PATH);
        // Create a BillFileHandler with the temporary file
        BillFileHandler billFileHandler = new BillFileHandler();

        // Set up the controller with the BillFileHandler
        billController = new BillController(billFileHandler);
        billController.setFile(testfile);
    }

    @AfterEach
    void tearDown() {
        // Clean up: delete the temporary file after each test
        File testFile = new File(TEST_FILE_PATH);
        testFile.delete();
    }

    @Test
    void testAddBill() throws IOException, ClassNotFoundException {
        // Prepare test data
        Bill testBill = new Bill("Librarian", "Supplier", LocalDate.now(), 100.0, 5);

        // Add a bill through the controller
        billController.addBill(testBill);

        // Read bills from the file
        ArrayList<Bill> readBills = billController.getBills();

        // Verify the integration by checking if the test bill is in the list
        assertTrue(readBills.contains(testBill), "The testBill is not found in readBills");

        // Verify the controller's bills list is the same as the one read from the file
        assertEquals(readBills, billController.getBills(), "The lists are not equal");
    }
}
