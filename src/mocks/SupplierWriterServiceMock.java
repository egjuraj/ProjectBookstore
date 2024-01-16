package src.mocks;

import src.service.SupplierWriterService;
import src.model.Supplier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SupplierWriterServiceMock implements SupplierWriterService {

    private ArrayList<Supplier> actualSuppliers;
    private ArrayList<Supplier> expectedSuppliers = new ArrayList<>();
    private boolean shouldThrowIOException = false;

    @Override
    public void writeSuppliersToFile(ArrayList<Supplier> suppliers, File file) throws IOException {
        this.actualSuppliers = suppliers;
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
    }

    @Override
    public ArrayList<Supplier> readSuppliersFromFile(File file) throws IOException, ClassNotFoundException {
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the readSuppliersFromFile method to return an empty list or a valid supplier list
        return new ArrayList<>(); // You can set up an actual list here if needed
    }

    public void expect(Supplier supplier) {
        this.expectedSuppliers.add(supplier);
    }

    public boolean verify() {
        return this.expectedSuppliers.equals(actualSuppliers);
    }

    public void setShouldThrowIOException(boolean shouldThrowIOException) {
        this.shouldThrowIOException = shouldThrowIOException;
    }
}
