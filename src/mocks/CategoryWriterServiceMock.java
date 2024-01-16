package src.mocks;

import src.service.CategoryWriterService;
import src.model.Category;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CategoryWriterServiceMock implements CategoryWriterService {

    private ArrayList<Category> actualCategories;
    private ArrayList<Category> expectedCategories = new ArrayList<>();
    private boolean shouldThrowIOException = false;

    @Override
    public void writeCategoriesToFile(ArrayList<Category> categories, File file) throws IOException {
        this.actualCategories = categories;
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the writeCategoriesToFile method
        // You can add actual logic here if needed
    }

    @Override
    public ArrayList<Category> readCategoriesFromFile(File file) throws IOException, ClassNotFoundException {
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the readCategoriesFromFile method to return an empty list or a valid category list
        return new ArrayList<>(); // You can set up an actual list here if needed
    }

    public void expect(Category category) {
        this.expectedCategories.add(category);
    }

    public boolean verify() {
        return this.expectedCategories.equals(actualCategories);
    }

    public void setShouldThrowIOException(boolean shouldThrowIOException) {
        this.shouldThrowIOException = shouldThrowIOException;
    }
}
