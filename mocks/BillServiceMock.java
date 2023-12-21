package mocks;

import Controller.BillWriterService;
import model.Bill;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BillServiceMock implements BillWriterService {

    private ArrayList<Bill> actualBills;
    private ArrayList<Bill> expectedBills = new ArrayList<>();
    private boolean shouldThrowIOException = false;

@Override
    public void writeBillToFile(ArrayList<Bill> bills, File file) throws IOException {
        this.actualBills = bills;
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
    }

    @Override
    public ArrayList<Bill> readBillsFromFile(File file) throws IOException, ClassNotFoundException {
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the readBillsFromFile method to return an empty list or a valid bill list
        return new ArrayList<>(); // You can set up an actual list here if needed
    }

    public void expect(Bill bill) {
        this.expectedBills.add(bill);
    }

    public boolean verify() {
        return this.expectedBills.equals(actualBills);
    }

    public void setShouldThrowIOException(boolean shouldThrowIOException) {
        this.shouldThrowIOException = shouldThrowIOException;
    }
}
