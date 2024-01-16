package src.impl;


        import src.service.BillWriterService;
        import src.model.Bill;

        import java.io.*;
        import java.util.ArrayList;
public class BillFileHandler implements BillWriterService {
    @Override
    public ArrayList<Bill> readBillsFromFile(File file) throws IOException, ClassNotFoundException {
        ArrayList<Bill> bills;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            bills = (ArrayList<Bill>) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bills = new ArrayList<>();
        }
        return bills;
    }

    @Override
    public void writeBillToFile(ArrayList<Bill> bills, File file) throws IOException {
        try {
            if (!file.exists()) {
                file.mkdirs();
            }

            File outputFile = new File(file, "BillForClient.txt");

            try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile, true))) {
                pw.println(bills.toString());
            }
        } catch (IOException e) {
            System.out.println("Error! Unable to write to the file: " + e.getMessage());
        }
    }
}
