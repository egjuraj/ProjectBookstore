package src.service;
import src.model.PurchasedBook;
import src.model.SoldBooks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface PurchasedWriterService {

        void writeSoldBooksToFile(ArrayList<SoldBooks> soldBooks, File file) throws IOException;

        ArrayList<SoldBooks> readSoldBooksFromFile(File file) throws IOException, ClassNotFoundException;

        void writePurchasedBooksToFile(ArrayList<PurchasedBook> purchasedBooks, File file) throws IOException;

        ArrayList<PurchasedBook> readPurchasedBooksFromFile(File file) throws IOException, ClassNotFoundException;

}
