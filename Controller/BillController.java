package Controller;

import model.Bill;

import java.io.*;
import java.util.ArrayList;

public class BillController {
    private final BillWriterService billWriterService;
    private Bill bill;

    private ArrayList<Bill> bills;

    private static final String file="bill.bin";
    public File filename;
    private String errorMessage="";


   /* public BillController(Bill bill) {
        this.bill=bill;
        bills = new ArrayList<>();
        file = new File("bills.bin");
        if(file.exists())  readBill();

    */


    public BillController(BillWriterService billWriterService) {
        this.bills = new ArrayList<>();
        this.billWriterService = billWriterService;
      filename=new File(file);
      /*  if (file.exists()) {
            readBill();
        }*/
    }
/*
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

    }*/
    public void readBill() {
    try {
        bills = billWriterService.readBillsFromFile(new File(file));
    } catch (IOException | ClassNotFoundException e) {
        errorMessage="Error reading bills: " + e.getMessage();
        System.out.println(errorMessage);
    }}
    public void writeFile() {
        try {

            billWriterService.writeBillToFile(bills, new File(file)); // Use BookWriterService
        } catch (IOException e) {
            errorMessage= "Error writing books to file: " + e.getMessage();
            System.err.println(errorMessage);
        }
    }

/*
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
    }*/

    public void addBill(Bill bill) {
        bills.add(bill);
        writeFile();
    }
    public ArrayList<Bill> getBills() {
        return bills;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}