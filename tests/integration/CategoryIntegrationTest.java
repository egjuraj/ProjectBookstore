package tests.integration;

        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import src.Controller.CategoryController;
        import src.impl.CategoryFileHandler;
        import src.model.Category;

        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryIntegrationTest {

    private static final String TEST_FILE_PATH = "testCategory.bin";
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        // Initialize a temporary file for testing
        File testFile = new File(TEST_FILE_PATH);

        // Create a CategoryFileHandler with the temporary file
        CategoryFileHandler categoryFileHandler = new CategoryFileHandler();

        // Set up the controller with the CategoryFileHandler
        categoryController = new CategoryController(categoryFileHandler);
        categoryController.setFile(testFile);
    }

    @AfterEach
    void tearDown() {
        // Clean up: delete the temporary file after each test
        File testFile = new File(TEST_FILE_PATH);
        testFile.delete();
    }

    @Test
    void testAddCategory() throws IOException, ClassNotFoundException {
        // Prepare test data
        Category testCategory = new Category("Test");

        // Add a category through the controller
        categoryController.addCategory(testCategory);

        // Read categories from the file
        ArrayList<Category> readCategories = categoryController.getCategories();

        // Verify the integration by checking if the test category is in the list
        assertTrue(readCategories.contains(testCategory), "The testCategory is not found in readCategories");

        // Verify the controller's categories list is the same as the one read from the file
        assertEquals(readCategories, categoryController.getCategories(), "The lists are not equal");
    }
}
