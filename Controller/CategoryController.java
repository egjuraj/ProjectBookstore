package Controller;


import model.Category;

import java.io.*;
import java.util.ArrayList;

	public class CategoryController {
		private ArrayList<Category> categories;
		private static final String file="category.bin";
		public File filename;
		 CategoryWriterService categoryWriterService;
		private String errorMessage = "";
		
		public CategoryController(CategoryWriterService categoryWriterService) {
			this.categoryWriterService=categoryWriterService;
			categories=new ArrayList<>();
			filename=new File(file);
			/*if(filename.exists()) {
				readCategory();
			} else {
				writeCategory();
			}*/
		}
/*
		public void writeCategory() {
			try {
				FileOutputStream fos=new FileOutputStream(filename);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(categories);
				oos.close();fos.close();
			} catch (FileNotFoundException e) {
				System.err.println("File cannot be written!!!");
			} catch (IOException e) {
				System.err.println("Problem with writing object");
			}
		}*/

		public void writeCategory() {
			try {
				categoryWriterService.writeCategoriesToFile(categories, new File(file));
			} catch (IOException e) {
				errorMessage = "Error writing categories to file: " + e.getMessage();
				System.err.println(errorMessage);
			}
		}
		/*private void readCategory() {
			try {
				FileInputStream fis=new FileInputStream(filename);
				ObjectInputStream ois=new ObjectInputStream(fis);
				categories=(ArrayList<Category>)ois.readObject();
				ois.close();
				fis.close();
			} catch (Exception e) {
				System.err.println("Be careful!");
			}
		}*/
		public void readCategory() {
			try {
				categories = categoryWriterService.readCategoriesFromFile(new File(file));
			} catch (IOException | ClassNotFoundException e) {
				errorMessage = "Error reading categories from file: " + e.getMessage();
				System.err.println(errorMessage);
			}}



		public ArrayList<Category> getCategories() {
			return categories;
		}
	    

		public void addCategory(Category c) {
			if (c == null) {
				throw new NullPointerException("Null category provided");
			}
			categories.add(c);
			writeCategory();
		}

		public String getErrorMessage() {
			return errorMessage;
		}
		public void setFile(File file) {
			this.filename = file;
		}
		
	}


