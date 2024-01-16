package src.Controller;

import src.model.Librarian;
import src.service.LibrarianWriterService;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;


public class LibrarianController{

	private ArrayList<Librarian> cashier;
	File file;
	private int nrBills=0;
	 LibrarianWriterService librarianWriterService;

	public LibrarianController(LibrarianWriterService librarianWriterService) {
		cashier = new ArrayList<Librarian>();
		this.librarianWriterService=librarianWriterService;
	}

	/*public void createBill(Librarian cashier) throws FileNotFoundException, IOException{
		file = new File("data.bin");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(cashier);
		fos.close();
		oos.close();
		nrBills++;
	}*/
	public void createBill(Librarian cashier) throws IOException {
		librarianWriterService.writeBillToFile(cashier, getFile());
		nrBills++;
	}

	public File getFile() {
		return this.file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public ArrayList<Librarian> getCashier() {
		return cashier;
	}

	public void setCashier(ArrayList<Librarian> cashier) {
		this.cashier = cashier;
	}
	
	public void setNrBills(int nrBills) {
		this.nrBills = nrBills;
	}
	
	public int getNrBills() {
		return this.nrBills;
	}
	
}
