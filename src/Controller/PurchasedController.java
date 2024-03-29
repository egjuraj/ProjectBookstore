package src.Controller;

import src.model.PurchasedBook;
import src.model.SoldBooks;
import src.model.TheDate;
import src.service.BookWriterService;
import src.service.PurchasedWriterService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PurchasedController {
	private PurchasedWriterService purchasedWriterService;
	private BookWriterService bookWriterServiceMock;
	private static final String soldfile = "sold.bin";
	private static final String purchasedfile = "purchase.bin";
	private File soldfilename;
	private File purchasefilename;
	private ArrayList<SoldBooks> soldTable;
	private ArrayList<PurchasedBook> purchasedTable;
	String errorMessages;

	public PurchasedController(PurchasedWriterService purchasedWriterService,BookWriterService bookWriterServiceMock) {
		this.purchasedWriterService = purchasedWriterService;
        this.bookWriterServiceMock=bookWriterServiceMock;
		soldTable = new ArrayList<>();
		purchasedTable = new ArrayList<>();
		soldfilename = new File(soldfile);
		purchasefilename = new File(purchasedfile);
	}
	public ArrayList<PurchasedBook> getPurchasedTable() {
		return purchasedTable;
	}

	public void writeSoldBooks() {
		try {
			purchasedWriterService.writeSoldBooksToFile(soldTable, soldfilename);
		} catch (IOException e) {
             errorMessages="Error writing sold books: " + e.getMessage();
			System.err.println(errorMessages);
		}}
	public ArrayList<SoldBooks> readSoldBooks() {
		try {
			soldTable = purchasedWriterService.readSoldBooksFromFile(soldfilename);
		} catch (IOException | ClassNotFoundException e) {
			errorMessages="Error reading sold books: " + e.getMessage();
			System.err.println(errorMessages);}
		return soldTable;
	}
	public void writePurchasedBooks() {
		try {
			purchasedWriterService.writePurchasedBooksToFile(purchasedTable, purchasefilename);
		} catch (IOException e) {
			errorMessages="Error writing purchased books: " + e.getMessage();
			System.err.println(errorMessages);
		}
	}
	public ArrayList<PurchasedBook> readPurchasedBooks() {
		try {
			purchasedTable = purchasedWriterService.readPurchasedBooksFromFile(purchasefilename);
		} catch (IOException | ClassNotFoundException e) {
			errorMessages="Error reading purchased books: " + e.getMessage();
			System.err.println(errorMessages);}
	return  purchasedTable;
	}

	public void addBook(int position,String ISBN, int quantity) {
		if (ISBN != null && !ISBN.isEmpty() && quantity > 0) {
			// Only add the book if both ISBN and quantity are valid
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			TheDate purchasedDate = new TheDate(sdf.format(new Date()));
			purchasedTable.add(new PurchasedBook(ISBN, quantity, purchasedDate));
			writePurchasedBooks();
			BookController pc = new BookController(bookWriterServiceMock);

			pc.addQuantity(position, quantity);
		}}

	public String getErrorMessage() {
		return errorMessages;
	}
	public void setPurchasedFile (File file){
		this.purchasefilename=file;
	}

	public File getPurchasedFile(){
		return purchasefilename;
	}



/*
import src.model.PurchasedBook;
import src.model.SoldBooks;
import src.model.TheDate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PurchasedController {
	private PurchasedWriterService purchasedWriterService;
	private BookWriterService bookWriterServiceMock;
	private static final String soldfile = "sold.bin";
	private static final String purchasedfile = "purchase.bin";
	private File soldfilename;
	private File purchasefilename;
	ArrayList<SoldBooks> soldTable;
	ArrayList<PurchasedBook> purchasedTable;

	public PurchasedController() {
		soldTable = new ArrayList<>();
		purchasedTable = new ArrayList<>();
		soldfilename = new File(soldfile);
		purchasefilename = new File(purchasedfile);
		if (soldfilename.exists()) {
			readSoldBooks();
		} else {
			writeSoldBooks();
		}
		if (purchasefilename.exists()) {
			readPurchasedBooks();
		} else {
			writePurchasedBooks();
		}
	}

	public ArrayList<SoldBooks> getSoldTable() {
		return soldTable;
	}

	public ArrayList<PurchasedBook> getPurchasedTable() {
		return purchasedTable;
	}

	private void writeSoldBooks() {
		try {
			FileOutputStream fos = new FileOutputStream(soldfilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(soldTable);
			oos.close();
			fos.close();
		} catch (Exception e) {
			System.err.println("Be careful!");
		}
	}


	private void readSoldBooks() {
		try {
			FileInputStream fis = new FileInputStream(soldfilename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			soldTable = (ArrayList<SoldBooks>) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			System.err.println("Be Careful!");
		}
	}

	private void writePurchasedBooks() {
		try {
			FileOutputStream fos = new FileOutputStream(purchasefilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(purchasedTable);
			oos.close();
			fos.close();
		} catch (Exception e) {
			System.err.println("Be careful!");
		}
	}

	private void readPurchasedBooks() {
		try {
			FileInputStream fis = new FileInputStream(purchasefilename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			purchasedTable = (ArrayList<PurchasedBook>) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			System.err.println("Be careful!");
		}
	}

	/*public void addBook(int position, String ISBN, int quantity) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TheDate purchasedDate = new TheDate(sdf.format(new Date()));
		purchasedTable.add(new PurchasedBook(ISBN, quantity, purchasedDate));
		BookController pc = new BookController();
		pc.addQuantity(position,quantity);
		writePurchasedBooks();
	}*/
	/*
	public void addBook(int position,String ISBN, int quantity) {
		if (ISBN != null && !ISBN.isEmpty() && quantity > 0) {
			// Only add the book if both ISBN and quantity are valid
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			TheDate purchasedDate = new TheDate(sdf.format(new Date()));
			purchasedTable.add(new PurchasedBook(ISBN, quantity, purchasedDate));
			writePurchasedBooks();
			BookController pc = new BookController(bookWriterServiceMock);

			pc.addQuantity(position, quantity);
		}}*/
}