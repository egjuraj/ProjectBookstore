package Controller;
import model.Book;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public interface BookWriterService {
    //void writeBooksToFile(ArrayList<Book> books);
    void writeBooksToFile(ArrayList<Book> books, File file) throws IOException;

    ArrayList<Book> readBooksFromFile(File file)throws IOException, ClassNotFoundException;
}

