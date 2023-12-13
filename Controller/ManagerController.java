package Controller;

import model.Manager;
import model.ProfitFromBooks;

import java.io.File;
import java.util.ArrayList;


public class ManagerController {

	private ArrayList<Manager> manager;
	private final ArrayList<ProfitFromBooks> ProfitFromBooks;
	private ArrayList<LibrarianController> librarianController;
	private int nrItems=0;
	File file;
	
	public ManagerController(ArrayList<model.ProfitFromBooks> profitFromBooks) {
		ProfitFromBooks = profitFromBooks;
		manager = new ArrayList<Manager>();
	}

	public ArrayList<Manager> getManager() {
		return manager;
	}

	public void setManager(ArrayList<Manager> manager) {
		this.manager = manager;
	}
	
	public int getProfitFromBooks() {
		return this.nrItems;
	}
	
	public void setProfitFromBooks(int nr) {
		nrItems = nr;
	}
	
	public void addItem(ProfitFromBooks i) {
		ProfitFromBooks.add(i);
		nrItems++;
	}
	
	public String checkLibrarianPerformance(LibrarianController c) {
	  
		if(c.getNrBills()>=5)
		  return "OK";
		else return "FAIL";
	}
	
}
