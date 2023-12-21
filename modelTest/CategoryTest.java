package modelTest;

import model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    @Test
    public void testCategoryInitialization() {
        // Create a category object
        Category category = new Category("Science");

        // Check if category is initialized correctly
        assertEquals("Science", category.toString());
    }

    @Test
    public void testToString() {
        // Create a category object
        Category category = new Category("Fiction");

        // Verify the string representation of the category
        assertEquals("Fiction", category.toString());
    }

    @Test
    public void testSerialVersionUID() {
        // Check if serialVersionUID is properly set
        long expectedSerialVersionUID = -2564026012494993767L;
        assertEquals(expectedSerialVersionUID, Category.serialVersionUID);
    }
}
