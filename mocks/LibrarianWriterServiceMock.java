package mocks;

import Controller.LibrarianWriterService;
import model.Librarian;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LibrarianWriterServiceMock implements LibrarianWriterService {

    private Librarian actualCashier;
    private Librarian expectedCashier;
    private boolean shouldThrowIOException = false;

    @Override
    public void writeBillToFile(Librarian cashier, File file) throws IOException {
        this.actualCashier = cashier;
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the writeBillToFile method
        // You can add actual logic here if needed
    }

    @Override
    public ArrayList<Librarian> readBillsFromFile(File file) throws IOException, ClassNotFoundException {
        // Mocking the readBillsFromFile method to return an empty list or a valid list of cashiers
        return new ArrayList<>(); // You can set up an actual list here if needed
    }

    public void expect(Librarian cashier) {
        this.expectedCashier = cashier;
    }

    public boolean verify() {
        return this.expectedCashier.equals(actualCashier);
    }

    public void setShouldThrowIOException(boolean shouldThrowIOException) {
        this.shouldThrowIOException = shouldThrowIOException;
    }
}
