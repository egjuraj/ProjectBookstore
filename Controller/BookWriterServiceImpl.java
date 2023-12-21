package Controller;

import model.Book;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BookWriterServiceImpl implements BookWriterService {




    public void writeBooksToFile(ArrayList<Book> books, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(books);
        }
    }

    @Override
    public ArrayList<Book> readBooksFromFile(File file) throws IOException, ClassNotFoundException {
        return null;
    }


}
