package src.impl;

import src.service.PurchasedWriterService;
import src.model.PurchasedBook;
import src.model.SoldBooks;

import java.io.*;
import java.util.ArrayList;
public class PurchasedFileHandler implements PurchasedWriterService {

    // Implementing writeSoldBooksToFile method
    @Override
    public void writeSoldBooksToFile(ArrayList<SoldBooks> soldBooks, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(soldBooks);
        } catch (IOException e) {
            System.err.println("Be careful!");
            throw e; // Rethrow the exception if needed
        }
    }

    // Implementing readSoldBooksFromFile method
    @Override
    public ArrayList<SoldBooks> readSoldBooksFromFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<SoldBooks>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Be careful!");
            throw e; // Rethrow the exception if needed
        }
    }

    // Implementing writePurchasedBooksToFile method
    @Override
    public void writePurchasedBooksToFile(ArrayList<PurchasedBook> purchasedBooks, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(purchasedBooks);
        } catch (IOException e) {
            System.err.println("Be careful!");
            throw e; // Rethrow the exception if needed
        }
    }

    // Implementing readPurchasedBooksFromFile method
    @Override
    public ArrayList<PurchasedBook> readPurchasedBooksFromFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<PurchasedBook>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Be careful!");
            throw e; // Rethrow the exception if needed
        }
    }
}
