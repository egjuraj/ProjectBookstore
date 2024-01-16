package tests.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Controller.UserController;
import src.impl.UserFileHandler;
import src.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserIntegrationTest {

    private static final String TEST_FILE_PATH = "testUser.bin";
    private UserController userController;

    @BeforeEach
    void setUp() {
        // Initialize a temporary file for testing
        File testFile = new File(TEST_FILE_PATH);

        // Create a UserFileHandler with the temporary file
        UserFileHandler userFileHandler = new UserFileHandler();

        // Set up the controller with the UserFileHandler
        userController = new UserController(userFileHandler);

        // Set the temporary file for the controller
        userController.setFile(testFile);
    }

    @AfterEach
    void tearDown() {
        // Clean up: delete the temporary file after each test
        File testFile = new File(TEST_FILE_PATH);
        testFile.delete();
    }

    @Test
    void testAddUser() throws IOException, ClassNotFoundException {
        // Prepare test data
        User testUser = new User("John", "Doe", "john_doe", "password", "123456789", "profession", "salary");

        // Add a user through the controller
        userController.addUser(testUser);

        // Read users from the file
        ArrayList<User> readUsers = userController.getUsers();

        // Verify the integration by checking if the test user is in the list
        assertTrue(readUsers.contains(testUser), "The testUser is not found in readUsers");

        // Verify the controller's users list is the same as the one read from the file
        assertEquals(readUsers, userController.getUsers(), "The lists are not equal");
    }

    @Test
    void testDeleteUser() throws IOException, ClassNotFoundException {
        // Prepare test data
        User testUser = new User("John", "Doe", "john_doe", "password", "123456789", "profession", "salary");

        // Add a user through the controller
        userController.addUser(testUser);

        // Delete the user through the controller
        userController.delete(testUser);
        assertTrue(userController.readUsers().isEmpty());

        // Read users from the file
        ArrayList<User> readUsers = userController.getUsers();

        // Verify the integration by checking if the test user is not in the list
        assertFalse(readUsers.contains(testUser), "The testUser is still found in readUsers");
    }


    @Test
    void testEditUser() throws IOException, ClassNotFoundException {
        // Prepare test data
        User testUser = new User("John", "Doe", "john_doe", "password", "123456789", "profession", "salary");

        // Add a user through the controller
        userController.addUser(testUser);
        assertNotNull(userController.readUsers());
      //  assertEquals("[User [firstName=John, lastName=Doe, password=password, phone=123456789, username=john_doe, profession=profession, salary=salary, birthday=null, id=0]]",userController.readUsers());
        // Edit the user through the controller
        User updatedUser = new User("User1", "User", "user1", "1234567", "06987654321", "Manager", "400");
        int index = userController.positionOfUser(testUser);
        userController.editUser(updatedUser, index);

        // Read users from the file
        ArrayList<User> readUsers = userController.getUsers();

        // Verify the integration by checking if the updated user is in the list
        assertTrue(readUsers.contains(updatedUser), "The updatedUser is not found in readUsers");
    }
}
