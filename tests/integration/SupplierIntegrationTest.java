
package tests.integration;

        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import src.Controller.SupplierController;
        import src.impl.SupplierFileHandler;
        import src.model.Supplier;

        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertFalse;
        import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupplierIntegrationTest {

    private static final String TEST_FILE_PATH = "testSupplier.bin";
    private SupplierController supplierController;

    @BeforeEach
    void setUp() {
        // Initialize a temporary file for testing
        File testFile = new File(TEST_FILE_PATH);

        // Create a SupplierFileHandler with the temporary file
        SupplierFileHandler supplierFileHandler = new SupplierFileHandler();

        // Set up the controller with the SupplierFileHandler
        supplierController = new SupplierController(supplierFileHandler);

        // Set the temporary file for the controller
        supplierController.setFile(testFile);
    }

    @AfterEach
    void tearDown() {
        // Clean up: delete the temporary file after each test
        File testFile = new File(TEST_FILE_PATH);
        testFile.delete();
    }

    @Test
    void testAddSupplier() throws IOException, ClassNotFoundException {
        // Prepare test data
        Supplier testSupplier = new Supplier("TestSupplier");

        // Add a supplier through the controller
        supplierController.addSupplier(testSupplier);

        // Read suppliers from the file
        ArrayList<Supplier> readSuppliers = supplierController.getSuppliers();

        // Verify the integration by checking if the test supplier is in the list
        assertTrue(readSuppliers.contains(testSupplier), "The testSupplier is not found in readSuppliers");

        // Verify the controller's suppliers list is the same as the one read from the file
        assertEquals(readSuppliers, supplierController.getSuppliers(), "The lists are not equal");
    }

    @Test
    void testDeleteSupplier() throws IOException, ClassNotFoundException {
        // Prepare test data
        Supplier testSupplier = new Supplier("TestSupplier");

        // Add a supplier through the controller
        supplierController.addSupplier(testSupplier);

        // Delete the supplier through the controller
        supplierController.deleteSupplier(0);

        // Read suppliers from the file
        ArrayList<Supplier> readSuppliers = supplierController.getSuppliers();

        // Verify the integration by checking if the test supplier is not in the list
        assertFalse(readSuppliers.contains(testSupplier), "The testSupplier is still found in readSuppliers");
    }
}
