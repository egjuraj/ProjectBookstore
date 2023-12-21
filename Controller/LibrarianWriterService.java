package Controller;

import model.Librarian;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface LibrarianWriterService {
    void writeBillToFile(Librarian cashier, File file) throws IOException;

    ArrayList<Librarian> readBillsFromFile(File file) throws IOException, ClassNotFoundException;
}
