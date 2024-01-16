package src.mocks;
import src.service.PurchasedWriterService;
import src.model.PurchasedBook;
import src.model.SoldBooks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PurchasedWriterServiceMock  implements PurchasedWriterService {

    private ArrayList<SoldBooks> actualSoldBooks;
    private ArrayList<PurchasedBook> actualPurchasedBooks;
    private ArrayList<SoldBooks> expectedSoldBooks = new ArrayList<>();
    private ArrayList<PurchasedBook> expectedPurchasedBooks = new ArrayList<>();
    private boolean shouldThrowIOException = false;

    @Override
    public void writeSoldBooksToFile(ArrayList<SoldBooks> soldBooks, File file) throws IOException {
        this.actualSoldBooks = soldBooks;
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
    }

    @Override
    public ArrayList<SoldBooks> readSoldBooksFromFile(File file) throws IOException, ClassNotFoundException {
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the readSoldBooksFromFile method to return an empty list or a valid sold books list
        return new ArrayList<>(); // You can set up an actual list here if needed
    }

    @Override
    public void writePurchasedBooksToFile(ArrayList<PurchasedBook> purchasedBooks, File file) throws IOException {
        this.actualPurchasedBooks = purchasedBooks;
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
    }

    @Override
    public ArrayList<PurchasedBook> readPurchasedBooksFromFile(File file) throws IOException, ClassNotFoundException {
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the readPurchasedBooksFromFile method to return an empty list or a valid purchased books list
        return new ArrayList<>(); // You can set up an actual list here if needed
    }

    public void expectSoldBooks(ArrayList<SoldBooks> soldBooks) {
        this.expectedSoldBooks.addAll(soldBooks);
    }

    public void expectPurchasedBooks(ArrayList<PurchasedBook> purchasedBooks) {
        this.expectedPurchasedBooks.addAll(purchasedBooks);
    }

    public boolean verifySoldBooks() {
        return this.expectedSoldBooks.equals(actualSoldBooks);
    }

    public boolean verifyPurchasedBooks() {
        return this.expectedPurchasedBooks.equals(actualPurchasedBooks);
    }

    public void setShouldThrowIOException(boolean shouldThrowIOException) {
        this.shouldThrowIOException = shouldThrowIOException;
    }
}
