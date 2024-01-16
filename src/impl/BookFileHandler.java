package src.impl;


import src.service.BookWriterService;
import src.model.Book;

import java.io.*;
import java.util.ArrayList;

public class BookFileHandler implements BookWriterService {

    @Override
    public void writeBooksToFile(ArrayList<Book> books, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.err.println("Error writing books to file: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ArrayList<Book> readBooksFromFile(File file) throws IOException, ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            books = (ArrayList<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading books from file: " + e.getMessage());
            throw e;
        }
        return books;
    }
}
