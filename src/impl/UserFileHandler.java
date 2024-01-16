package src.impl;

import src.service.UserWriterService;
import src.model.User;

import java.io.*;
import java.util.ArrayList;

public class UserFileHandler implements UserWriterService {

    @Override
    public void writeUsersToFile(ArrayList<User> users, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error writing users to file: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ArrayList<User> readUsersFromFile(File file) throws IOException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
            throw e;
        }
        return users;
    }
}