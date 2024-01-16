package src.impl;


import src.service.LibrarianWriterService;
import src.model.Librarian;

import java.io.*;
import java.util.ArrayList;
public class LibrarianFileHandler implements LibrarianWriterService {

    @Override
    public void writeBillToFile(Librarian cashier, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(cashier);
        } catch (IOException e) {
            System.err.println("Error writing Librarian to file: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ArrayList<Librarian> readBillsFromFile(File file) throws IOException, ClassNotFoundException {
        ArrayList<Librarian> librarians = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Librarian librarian = (Librarian) ois.readObject();
            librarians.add(librarian);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading Librarian from file: " + e.getMessage());
            throw e;
        }
        return librarians;
    }

}
