package src.impl;

import src.service.SupplierWriterService;
import src.model.Supplier;

import java.io.*;
import java.util.ArrayList;
public class SupplierFileHandler implements SupplierWriterService {
    @Override
    public void writeSuppliersToFile(ArrayList<Supplier> suppliers, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(suppliers);
        } catch (IOException e) {
            System.err.println("Error writing suppliers to file: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ArrayList<Supplier> readSuppliersFromFile(File file) throws IOException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            suppliers = (ArrayList<Supplier>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading suppliers from file: " + e.getMessage());
            throw e;
        }
        return suppliers;
    }
}