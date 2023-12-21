package Controller;

import model.Book;

import java.io.*;
import java.util.ArrayList;


public class BookController {
	
	private ArrayList<Book> books;
	BookWriterService bookWriterService;
	private static final String file="product.bin";
	public File filename;
	private String errorMessage="";

	public BookController(BookWriterService bookWriterService) {
		this.bookWriterService = bookWriterService;
		books=new ArrayList<>();
		filename=new File(file);
//		if(filename.exists()) {
//			readBooks();
//		} else {
//			writeBooks();
//		}
	}
	public void setFile(File file) {
		this.filename = file;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}

	public void addBooks(Book product) {
		books.add(product);
		writeBooks();
	}
	public void writeBooks() {
		try {
			bookWriterService.writeBooksToFile(books, new File(file)); // Use BookWriterService
		} catch (IOException e) {
			errorMessage= "Error writing books to file: " + e.getMessage();
			System.err.println(errorMessage);
		}
	}
	/*public void writeBooks() {
	bookWriterService.writeBooksToFile(books);
	}*/
/*	public void writeBooks() {
		try {
			FileOutputStream fos=new FileOutputStream(filename);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(books);
			oos.close();fos.close();
		} catch (Exception e) {
			System.err.println("Be Careful!");
		}
	}
*/
	/*public void readBooks() {
		try {
			FileInputStream fis=new FileInputStream(filename);
			ObjectInputStream ois=new ObjectInputStream(fis);
			books=((ArrayList<Book>)ois.readObject());
			ois.close();
			fis.close();
		} catch (Exception e) {
			System.err.println("Be Careful!");
		} 
	}
	*/

	public void readBooks() {
		try {
			books = bookWriterService.readBooksFromFile(new File(file));
		} catch (IOException | ClassNotFoundException e) {
			errorMessage = "Error reading books from file: " + e.getMessage();
			System.err.println(errorMessage);
		}}
	public boolean useISBN(String ISBN) {
		for(Book  b: books) {
			if((b.getISBN()).equals(ISBN)) {
				return true;
			}
		}			
		return false;
	}
	

	public int getPosition(Book book) {
		
		for(int i=0; i<books.size(); i++)	{
			if(books.get(i).getISBN().equals(book.getISBN()))
				return i;	}	
		return -1;
	}
	
	
	
	public void deleteBook(Book book) {
	   books.remove(book);
		writeBooks();
	}
	
	
	
	
	/*public void addQuantity(int pos, int quantity) {
	books.get(pos).setStock(books.get(pos).getStock()+quantity);
		writeBooks();
	}*/
	public void addQuantity(int pos, int quantity) {
		if (pos >= 0 && pos < books.size()) {
			int currentStock = books.get(pos).getStock();
			int newStock = currentStock + quantity;
			if (newStock < 0) {
				newStock = 0; // Set stock to 0 if the resulting stock would be negative
			}
			books.get(pos).setStock(newStock);
			writeBooks();
		} else {
			System.err.println("Invalid position or index out of bounds");
		}
	}



	}







