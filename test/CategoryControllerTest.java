package test;

import Controller.CategoryController;
import mocks.CategoryWriterServiceMock;
import model.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryControllerTest {
    private CategoryWriterServiceMock mockedCategory;
private CategoryController categoryController;
private File testFile;

@BeforeEach
public void setUp() {
     mockedCategory=new CategoryWriterServiceMock();
    categoryController = new CategoryController(mockedCategory);
    categoryController.getCategories().clear(); // Clear categories for a clean start
}

    @Test

    public void testAddCategoryToEmptyList() {
        // Equivalence Class: Empty Supplier List
        // Test adding a supplier when the list is initially empty
        // Create a new Supplier
        Category category = new Category("Romanc");
        categoryController.addCategory(category);
        ArrayList<Category> categories = categoryController.getCategories();
        // Assertion to check if the supplier was added
        Assert.assertEquals(1, categories.size());
        Assertions.assertTrue(categories.contains(category));
    }
    @Test
    public void testAddNullCategory()
        {
        assertThrows(NullPointerException.class, () -> {
            categoryController.addCategory(null);
        });
}
    @Test
    public void testAddDuplicateCategory() {

        Category category = new Category("Fant");

        categoryController.addCategory(category);
        ArrayList<Category> categoryBefore = categoryController.getCategories();

        // Adding the same supplier again
        categoryController.addCategory(category);
        ArrayList<Category> categoryAfter = categoryController.getCategories();

        // Assertions to check that the supplier count remains the same
        assertEquals(categoryBefore.size(), categoryAfter.size());
    }
    @Test
    public void testAddNullCategoryThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            categoryController.addCategory(null);
        });

        // Ensure the categories list remains empty
        ArrayList<Category> categories = categoryController.getCategories();
        assertTrue(categories.isEmpty(), "Null category addition did not throw NullPointerException");}

    @Test
    void testWriteCategory() {
        Category category = new Category("Romanc");
        categoryController.addCategory(category);
        // Set expectations in the mock
        mockedCategory.expect(category);


        // Verify if the expected category was written
        Assertions.assertTrue(mockedCategory.verify());
    }
    @Test
    void testReadCategoryFromFileIOException() {

        // Simulate an IOException when reading books
        mockedCategory.setShouldThrowIOException(true);

        // Attempt to read books from file
        categoryController.readCategory();

        // Verify that an error message is set
        String errorMessage = categoryController.getErrorMessage();
        Assertions.assertTrue(errorMessage != null && !errorMessage.isEmpty());

    }
    @Test
    public void testWriteBooksWithIOException() {

        // Create a sample book for testing
        Category category = new Category("Romanc");
        // Add the test book to the controller
        categoryController.addCategory(category);

        // Set the flag to simulate an IOException
        mockedCategory.setShouldThrowIOException(true);

        // Call the method under test
        categoryController.writeCategory();

        // Get the actual error message from the BookController
        String actualErrorMessage = categoryController.getErrorMessage();

        // Print the actual error message for further debugging
        System.out.println("Actual Error Message: " + actualErrorMessage);

        // Check if the actual error message contains the expected error description
        Assertions.assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty(),
                "No error message was set in BookController for IOException");

        //Assertions.assertTrue(actualErrorMessage.contains("Error writing books to file"));
    }
    @Test
    void testSetFile() {



        File newFile = new File("newFile.bin");

        // Set the new file using setFile method
        categoryController.setFile(newFile);

        // Verify if the file in the BookController instance is updated
        File updatedFile = categoryController.filename; // Accessing the filename attribute directly

        Assertions.assertEquals(newFile, updatedFile,
                "File in BookController was not updated after setFile method");
    }


}