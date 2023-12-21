package Controller;

import model.Supplier;

import java.io.*;
import java.util.ArrayList;


public class SupplierController {
	private static final String file = "supplier.bin";
	private File filename;
	private ArrayList<Supplier> suppliers;
	SupplierWriterService supplierWriterService;
	String errorMessages="";
	
	public SupplierController(SupplierWriterService supplierWriterService) {
		this.supplierWriterService=supplierWriterService;
		suppliers=new ArrayList<>();
		filename=new File(file);
	/*	if(filename.exists()) {
			readSuppliers();
		} else {
			writeSuppliers();
		}*/
	}
/*
	private void writeSuppliers() {
		try {
			FileOutputStream fos=new FileOutputStream(filename);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(suppliers);
			oos.close();fos.close();
		} catch (Exception e) {
			System.err.println("Be Careful");
		}
	}*/
public void writeSuppliers() {
	try {
		supplierWriterService.writeSuppliersToFile(suppliers, new File(file));
	} catch (IOException e) {
		errorMessages="Error writing suppliers to file: " + e.getMessage();
		System.err.println(errorMessages);
	}
}


	/*private void readSuppliers() {
		try {
			FileInputStream fis=new FileInputStream(filename);
			ObjectInputStream ois=new ObjectInputStream(fis);
			suppliers=(ArrayList<Supplier>)ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			System.err.println("File not found!!!");
		}
	}*/
	public void readSuppliers() {
		try {
			suppliers = supplierWriterService.readSuppliersFromFile(new File(file));
		} catch (IOException | ClassNotFoundException e) {
			errorMessages="Error reading suppliers from file: " + e.getMessage();
			System.err.println(errorMessages);
		}}
	
	public ArrayList<Supplier> getSuppliers() {
		return suppliers;
	}


	public void addSupplier(Supplier supplier) {
		suppliers.add(supplier);
		writeSuppliers();}
	public boolean RegisterSupplier(String s) {
		for(Supplier s1: suppliers) {
			if(s1.getnameOfSupplier().equals(s))
				return true;}
		return false;}
	
	public int getPosition(Supplier supplier) {
		for(int i=0; i<suppliers.size(); i++) {
			if(suppliers.get(i).getnameOfSupplier().equals(supplier.getnameOfSupplier()))
				return i;}
		return -1;}
	
	public void deleteSupplier(int position) {
		suppliers.remove(position); 
		writeSuppliers();
	}
	public String getErrorMessages(){
		return errorMessages;
	}
}