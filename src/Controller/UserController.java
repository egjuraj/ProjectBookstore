package src.Controller;

import src.model.User;
import src.service.UserWriterService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserController {
	public ArrayList<User> users;
	private static final String file="product.bin";
	public File filename;
	private String errorMessage="";
	UserWriterService userWriterService;
	public UserController(UserWriterService userWriterService) {
		this.userWriterService = userWriterService;
		users = new ArrayList<>();
		filename = new File(file);
		/*if(file.exists()){
			readUsers();
		}
		else {
			writeUsers();
		}*/
	}
	/*public void readUsers() {
		try{
			FileInputStream fis= new FileInputStream(file);
			ObjectInputStream ois= new ObjectInputStream(fis);
			users=(ArrayList<User>)ois.readObject();
			fis.close();
			ois.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}*/
	/*
	private void writeUsers(){
		try{
			FileOutputStream fos= new FileOutputStream(file);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
			fos.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}*/
	public void writeUsers() {
		try {
			userWriterService.writeUsersToFile(users, new File(file));
		} catch (IOException e) {
			errorMessage=e.getMessage();
			System.out.println(errorMessage);
		}
	}
	public ArrayList<User> readUsers() {
		try {
			users = userWriterService.readUsersFromFile(new File(file));
		} catch (IOException | ClassNotFoundException e) {
			errorMessage = "Error reading books from file: " + e.getMessage();
			System.err.println(errorMessage);}
		return  users;
	}
	public void addUser(User u){
		this.users.add(u);
		writeUsers();
	}
	public void delete(User u) {
		this.users.remove(positionOfUser(u));
		writeUsers();
	}
	public boolean signUp(String firstName, String lastName, String username, String password,String verifyPassword,
			String phone, String profession, String salary){
		if(password.equals(verifyPassword)){
			User u = new User(firstName, lastName, username, password, phone,profession ,salary);
			this.addUser(u);
			writeUsers();
			return true;
		}
		return false;
	}
	public User login(String username, String password){
		System.out.println("Users:"+ this.users);
		for(User user: users){
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
				return user;
			}
		}
		return null;
	}
	
	public ArrayList<User> getUsers(){
		return this.users;
	}
	
	public void editUser(User updatedUser, int index){
		this.users.set(index, updatedUser);
		writeUsers();
	}
	
	public int positionOfUser(User currentUser) {
		for(int i=0 ; i<this.users.size(); i++) {
			if(currentUser.getUsername().equals(this.users.get(i).getUsername())){
				return i;
			}
		}
		return -1;
	}
	public void setFile(File file) {
		this.filename = file;
	}

	public String getErrorMessage(){
		return errorMessage;
	}

}
