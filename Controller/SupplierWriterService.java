package Controller;

import model.Supplier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface SupplierWriterService {
    void writeSuppliersToFile(ArrayList<Supplier> suppliers, File file) throws IOException;

    ArrayList<Supplier> readSuppliersFromFile(File file) throws IOException, ClassNotFoundException;
}
