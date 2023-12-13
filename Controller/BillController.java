package Controller;

import model.Bill;

import java.io.*;
import java.util.ArrayList;

public class BillController {
    private ArrayList<Bill> bills;
    private final File file;
    private  Bill bill;

    public BillController(Bill bill) {
        bills = new ArrayList<>();
        file = new File("bills.bin");
        if(file.exists())  readBill();
        
    }

    private void readBill() {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            bills = (ArrayList<Bill>) ois.readObject();
            fis.close();
            ois.close();
        } catch(Exception i) {
            System.out.println(i.getMessage());
        }

    }

 
    public void addbill(Bill bill) {
        bills.add(bill);
        writeFile(bill);
    }

    public void writeFile(Bill bill) {
        try {
            File f = new File("bill");
            boolean mkdir = f.mkdir();
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(f+ "/BillForClient.txt"), true));
            pw.println(bill.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error! File cant be found!!!");
        }
    }

}