package src.mocks;

import src.service.UserWriterService;
import src.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserWriterServiceMock implements UserWriterService {

    private ArrayList<User> actualUsers;
    private ArrayList<User> expectedUsers = new ArrayList<>();
    private boolean shouldThrowIOException = false;

    @Override
    public void writeUsersToFile(ArrayList<User> users, File file) throws IOException {
        this.actualUsers = users;
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
    }

    @Override
    public ArrayList<User> readUsersFromFile(File file) throws IOException, ClassNotFoundException {
        if (shouldThrowIOException) {
            throw new IOException("Simulated IOException");
        }
        // Mocking the readUsersFromFile method to return an empty list or a valid user list
        return new ArrayList<>(); // You can set up an actual list here if needed
    }

    public void expect(User user) {
        this.expectedUsers.add(user);
    }

    public boolean verify() {
        return this.expectedUsers.equals(actualUsers);
    }

    public void setShouldThrowIOException(boolean shouldThrowIOException) {
        this.shouldThrowIOException = shouldThrowIOException;
    }
}
