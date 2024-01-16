package src.service;

import src.model.Category;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface CategoryWriterService {
    void writeCategoriesToFile(ArrayList<Category> categories, File file) throws IOException;

    ArrayList<Category> readCategoriesFromFile(File file) throws IOException, ClassNotFoundException;
}
