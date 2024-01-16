package src.service;


import src.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface UserWriterService {
    void writeUsersToFile(ArrayList<User> users, File file) throws IOException;

    ArrayList<User> readUsersFromFile(File file) throws IOException, ClassNotFoundException;
}
