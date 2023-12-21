package mocks;

import Controller.BookWriterService;
import model.Book;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BookWriterServiceMock implements BookWriterService {

    private ArrayList<Book> actualBooks;
    private ArrayList<Book> expectedBooks = new ArrayList<>();
    private boolean shouldThrowIOException=false;

    @Override
    public void writeBooksToFile(ArrayList<Book> books, File file) throws IOException {
        this.actualBooks = books;
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
    }

    @Override
    public ArrayList<Book> readBooksFromFile(File file) throws IOException, ClassNotFoundException {
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the readBooksFromFile method to return an empty list or a valid book list
        return new ArrayList<>(); // You can set up an actual list here if needed
    }


    public void expect(Book book) {
        this.expectedBooks.add(book);
    }

    public boolean verify() {
        return this.expectedBooks.equals(actualBooks);
    }
    public void setShouldThrowIOException(boolean shouldThrowIOException) {
        this.shouldThrowIOException = shouldThrowIOException;
    }
}
