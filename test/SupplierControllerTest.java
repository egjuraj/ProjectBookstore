package test;

import Controller.SupplierController;
import mocks.SupplierWriterServiceMock;
import model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
class SupplierControllerTest {
    SupplierWriterServiceMock mockSupplier;
    SupplierController supplierController;

    @BeforeEach
    public void setUp() {
        mockSupplier=new SupplierWriterServiceMock();
        supplierController = new SupplierController(mockSupplier);
        supplierController.getSuppliers().clear();
    }

    @Test
    public void testAddSupplierToEmptyList() {
        // Equivalence Class: Empty Supplier List
        // Test adding a supplier when the list is initially empty
        // Create a new Supplier
        Supplier supplier = new Supplier("Supplier A", "Address A", "Contact A");
        supplierController.addSupplier(supplier);
        ArrayList<Supplier> suppliers = supplierController.getSuppliers();
        // Assertion to check if the supplier was added
        assertEquals(1, suppliers.size());
        assertTrue(suppliers.contains(supplier));
    }

    @Test
    public void testAddSupplierToNonEmptyList() {
        // Equivalence Class: Non-Empty Supplier List
        // Test adding a supplier when the list already contains elements
        // Create some initial suppliers in the list
        Supplier supplier1 = new Supplier("Supplier A", "Address A", "Contact A");
        Supplier supplier2 = new Supplier("Supplier B", "Address B", "Contact B");
        supplierController.addSupplier(supplier1);
        supplierController.addSupplier(supplier2);
        // Create a new Supplier
        Supplier newSupplier = new Supplier("Supplier C", "Address C", "Contact C");
        supplierController.addSupplier(newSupplier);
        ArrayList<Supplier> suppliers = supplierController.getSuppliers();
        // Assertions to check if the new supplier was added and list size increased
        assertEquals(3, suppliers.size());
        assertTrue(suppliers.contains(newSupplier));
    }

    @Test
    public void testAddExistingSupplier() {
        // Equivalence Class: Supplier Object already exists in the list
        // Test adding a supplier that already exists in the list
        // Create a new Supplier
        Supplier supplier = new Supplier("Supplier A", "Address A", "Contact A");

        supplierController.addSupplier(supplier);
        ArrayList<Supplier> suppliersBefore = supplierController.getSuppliers();

        // Adding the same supplier again
        supplierController.addSupplier(supplier);
        ArrayList<Supplier> suppliersAfter = supplierController.getSuppliers();

        // Assertions to check that the supplier count remains the same
        assertEquals(suppliersBefore.size(), suppliersAfter.size());
    }
    @Test
    public void testDeleteSupplierAtValidPosition() {
        // Equivalence Class: Valid Positions
        // Test deleting a supplier at a valid position within the list range

        // Create some initial suppliers in the list
        Supplier supplier1 = new Supplier("Supplier A", "Address A", "Contact A");
        Supplier supplier2 = new Supplier("Supplier B", "Address B", "Contact B");

        supplierController.addSupplier(supplier1);
        supplierController.addSupplier(supplier2);

        // Delete the supplier at index 1 (second position)
        supplierController.deleteSupplier(1);
        ArrayList<Supplier> suppliers = supplierController.getSuppliers();

        // Assertions to check if the supplier was removed and the list size decreased
        assertEquals(1, suppliers.size());
        assertFalse(suppliers.contains(supplier2));
    }
    @Test
    public void testDeleteSupplierFromEmptyListandInvalidPosition() {
        // Equivalence Class: Empty Supplier List// Test deleting from an initially empty list// Try to delete from an empty list
       // Deleting from any position in an empty lis// Assertion to ensure the list remains empty
        assertThrows(IndexOutOfBoundsException.class, () -> {
            supplierController.deleteSupplier(0);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            supplierController.deleteSupplier(-1); // Attempting to delete from an invalid position (-1)
        });
    }
    @Test
    public void testGetPositionForExistingSupplier() {
        // Equivalence Class: Existing Supplier
        // Test getting position for an existing supplier in the list
        // Add suppliers to the list
        Supplier existingSupplier1 = new Supplier("Supplier1");
        Supplier existingSupplier2 = new Supplier("Supplier2");
        supplierController.getSuppliers().add(existingSupplier1);
        supplierController.getSuppliers().add(existingSupplier2);

        int expectedPosition = 1; // Supplier2 is expected at index 1 in the list

        int actualPosition = supplierController.getPosition(existingSupplier2);

        assertEquals(expectedPosition, actualPosition);
    }
    @Test
    public void testGetPositionForNonExistingSupplier() {
        // Equivalence Class: Non-Existing Supplier
        // Test getting position for a supplier not present in the list

        Supplier nonExistingSupplier = new Supplier("NonExistingSupplier");

        int expectedPosition = -1; // Supplier not found in the list, expecting -1

        int actualPosition = supplierController.getPosition(nonExistingSupplier);

        assertEquals(expectedPosition, actualPosition);
    }
    @Test
    public void testGetPositionInEmptyList() {
        // Equivalence Class: Empty List
        // Test getting position for a supplier in an empty list

        // Ensure the list is empty (already done in setUp method)

        Supplier supplierInEmptyList = new Supplier("SupplierX");

        int expectedPosition = -1; // Supplier not found in the empty list, expecting -1

        int actualPosition = supplierController.getPosition(supplierInEmptyList);

        assertEquals(expectedPosition, actualPosition);
    }
    @ParameterizedTest
    @CsvSource({
            "ExistingSupplier, true",
            "NonExistingSupplier, false",
            "NewSupplier, false"
    })
    public void testRegisterSupplier(String supplierName, boolean expectedRegistrationStatus) {
        // Equivalence Classes: Existing Supplier, Non-Existing Supplier, Empty List

        // Add suppliers to the list if needed
        if (supplierName.equals("ExistingSupplier")) {
            Supplier existingSupplier = new Supplier("ExistingSupplier");
            supplierController.getSuppliers().add(existingSupplier);
        }

        boolean isRegistered = supplierController.RegisterSupplier(supplierName);

        if (expectedRegistrationStatus) {
            assertTrue(isRegistered, "Supplier '" + supplierName + "' should be registered.");
        } else {
            assertFalse(isRegistered, "Supplier '" + supplierName + "' should not be registered.");
        }
    }


    @Test
    public void testWriteBooks() throws IOException {
        // Mock the BookWriter interface
        Supplier existingSupplier1 = new Supplier("Supplier1");

        // Create a sample book for testing


        // Add the test book to the controller
        supplierController.addSupplier(existingSupplier1);

        // Call the method under test
        supplierController.writeSuppliers();

        // Verify that the writeBooksToFile method was called with the expected arguments
        mockSupplier.expect(existingSupplier1);
        Assertions.assertTrue(mockSupplier.verify());
//        verify(mockedBookWriter, times(1)).writeBooksToFile(expectedBooks, testFile);
    }

    @Test
    public void testWriteBooksWithIOException() {

        Supplier existingSupplier1 = new Supplier("Supplier1");


        // Add the test book to the controller
        supplierController.addSupplier(existingSupplier1);

        // Set the flag to simulate an IOException
        mockSupplier.setShouldThrowIOException(true);

        // Call the method under test
        supplierController.writeSuppliers();

        // Get the actual error message from the BookController
        String actualErrorMessage = supplierController.getErrorMessages();

        // Print the actual error message for further debugging
        System.out.println("Actual Error Message: " + actualErrorMessage);

        // Check if the actual error message contains the expected error description
        Assertions.assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty(),
                "No error message was set in BookController for IOException");


    }
    @Test
    void testReadBooksFromFileIOException() {

        // Simulate an IOException when reading books
        mockSupplier.setShouldThrowIOException(true);

        // Attempt to read books from file
        supplierController.readSuppliers();

        // Verify that an error message is set
        String errorMessage = supplierController.getErrorMessages();
        Assertions.assertTrue(errorMessage != null && !errorMessage.isEmpty());

    }

}

