package Controller;

import model.Bill;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface BillWriterService {


    ArrayList<Bill> readBillsFromFile(File file) throws IOException, ClassNotFoundException;

    void writeBillToFile(ArrayList<Bill> bills, File file) throws IOException;
}
