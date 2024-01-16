package src.impl;


import src.service.CategoryWriterService;
import src.model.Category;

import java.io.*;
import java.util.ArrayList;

public class CategoryFileHandler implements CategoryWriterService {

    @Override
    public void writeCategoriesToFile(ArrayList<Category> categories, File file) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(categories);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written!!!");
        } catch (IOException e) {
            System.err.println("Problem with writing object");
        }
    }

    @Override
    public ArrayList<Category> readCategoriesFromFile(File file) throws IOException, ClassNotFoundException {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            categories = (ArrayList<Category>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.err.println("Be careful!");
        }
        return categories;
    }
}
