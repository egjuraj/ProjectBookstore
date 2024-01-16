package tests.test;


import src.Controller.UserController;
import src.mocks.UserWriterServiceMock;
import src.model.Book;
import src.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserControllerTest {

    private UserController userController;
    UserWriterServiceMock mockUser;
    private File testFile;


    @Before
    public void setUp() {
        mockUser=new UserWriterServiceMock();
        userController = new UserController(mockUser);
        userController.getUsers().clear();
    }

    @Test
    public void testLogin_InvalidUser() {
        // Creating a user for testing
        User user1 = new User("user1", "John", "Doe", "password1", "123456789", "Student", "1000");

        // Adding the user to the UserController manually
        userController.addUser(user1);

        // Test login with invalid credentials
        User loggedInUser = userController.login("invalidUser", "invalidPassword");
        assertNull(loggedInUser);
    }


    @Test
    public void testLogin_IncorrectUsername() {
        // Create a user for testing
        User user = new User("testUser", "John", "Doe", "password123", "123456789", "Student", "1000");
        userController.addUser(user);

        // Test login with incorrect username
        User loggedInUser = userController.login("invalidUser", "password123");
        assertNull(loggedInUser);
    }

    @Test
    public void testLogin_IncorrectPassword() {
        // Create a user for testing
        User user = new User("testUser", "John", "Doe", "password123", "123456789", "Student", "1000");
        userController.addUser(user);

        // Test login with incorrect password
        User loggedInUser = userController.login("testUser", "wrongPassword");
        assertNull(loggedInUser);
    }

    @Test
    public void testLogin_UserDoesNotExist() {
        // Test login when the user doesn't exist
        User loggedInUser = userController.login("nonExistingUser", "password");
        assertNull(loggedInUser);
    }
    @Test
    public void testAddUser() {
        // Creating a new user
        User newUser = new User("newUser", "New", "User", "password", "987654321", "Admin", "3000");

        // Adding the new user to the UserController
        userController.addUser(newUser);

        // Verify that the new user is added to the list of users
        ArrayList<User> users = userController.getUsers();
        assertTrue(users.contains(newUser));
    }
    @Test
    public void testDeleteUser() {
        // Create users for testing
        User user1 = new User("user1", "John", "Doe", "password1", "123456789", "Student", "1000");
        User user2 = new User("user2", "Jane", "Smith", "password2", "987654321", "Teacher", "2000");

        // Add users to the UserController
        userController.addUser(user1);
        userController.addUser(user2);

        // Verify that the users are added to the list of users
        assertEquals(2, userController.getUsers().size());

        // Delete user1
        userController.delete(user1);

        // Verify that user1 is deleted and user2 remains
        assertEquals(1, userController.getUsers().size());
        assertFalse(userController.getUsers().contains(user1));
        assertTrue(userController.getUsers().contains(user2));
    }
    @Test
    public void testPositionOfUser() {
        // Create users for testing
        User user1 = new User("user1", "John", "Doe", "password1", "123456789", "Student", "1000");
        User user2 = new User("user2", "Jane", "Smith", "password2", "987654321", "Teacher", "2000");

        // Add users to the UserController
        userController.addUser(user1);
        userController.addUser(user2);

        // Test positionOfUser for an existing user (user1)
        int positionUser1 = userController.positionOfUser(user1);
        assertEquals(0, positionUser1); // user1 is expected to be at position 0

        // Test positionOfUser for another existing user (user2)
        int positionUser2 = userController.positionOfUser(user2);
        assertEquals(1, positionUser2); // user2 is expected to be at position 1

        // Test positionOfUser for a user that does not exist in the list
        User nonExistingUser = new User("nonExisting", "Non", "Existing", "password", "111111111", "Guest", "500");
        int positionNonExistingUser = userController.positionOfUser(nonExistingUser);
        assertEquals(-1, positionNonExistingUser); // nonExistingUser should not be found in the list (-1 expected)
    }
    @Test
    public void testEditUser() {
        // Create users for testing
        User user1 = new User("user1", "John", "Doe", "password1", "123456789", "Student", "1000");
        User user2 = new User("user2", "Jane", "Smith", "password2", "987654321", "Teacher", "2000");

        // Add users to the UserController
        userController.addUser(user1);
        userController.addUser(user2);

        // Create an updated user
        User updatedUser = new User("user1", "Updated", "User", "updatedPassword", "999999999", "UpdatedProfession", "3000");

        // Edit the user1 with updatedUser
        userController.editUser(updatedUser, 0); // Assuming user1 is at index 0

        // Retrieve the updated user from the list
        User retrievedUser = userController.getUsers().get(0);

        // Verify that the user1 has been updated
        assertEquals("user1", retrievedUser.getFirstName());
        assertEquals("Updated", retrievedUser.getLastName());
        assertEquals("updatedPassword", retrievedUser.getPassword());
        assertEquals("999999999", retrievedUser.getPhone());
        assertEquals("UpdatedProfession", retrievedUser.getProfession());
        assertEquals("3000", retrievedUser.getSalary());
    }

    @Test
    public void testSignUp_ValidUser() {
        // Perform sign up with valid user details
        boolean signUpResult = userController.signUp(
                "John",
                "Doe",
                "john123",
                "password",
                "password",
                "123456789",
                "Student",
                "1000"
        );

        // Verify that sign up is successful
        assertTrue(signUpResult);

        // Retrieve the signed-up user from the list
        User signedUpUser = userController.getUsers().get(0);

        // Verify the details of the signed-up user
        assertEquals("John", signedUpUser.getFirstName());
        assertEquals("Doe", signedUpUser.getLastName());
        assertEquals("john123", signedUpUser.getUsername());
        assertEquals("password", signedUpUser.getPassword());
        assertEquals("123456789", signedUpUser.getPhone());
        assertEquals("Student", signedUpUser.getProfession());
        assertEquals("1000", signedUpUser.getSalary());
    }
    @Test
    public void testLogin_WithValidCredentials() {
        // Create a user for testing
        User user = new User("testUser", "John", "Doe", "password123", "123456789", "Student", "1000");
        userController.addUser(user);


        // Test login with correct username and password
        User loggedInUser = userController.login("Doe", "password123");

        // Assert that the returned user matches the added user
        //assertNotNull(loggedInUser);
       assertEquals("Doe", loggedInUser.getUsername());
       assertEquals("password123", loggedInUser.getPassword());

    }

    @Test
    public void testSignUp_PasswordMismatch() {
        // Perform sign up with mismatched passwords
        boolean signUpResult = userController.signUp(
                "Jane",
                "Smith",
                "jane123",
                "password",
                "differentpassword", // Intentional password mismatch
                "987654321",
                "Teacher",
                "2000"
        );

        // Verify that sign up fails due to password mismatch
        assertFalse(signUpResult);
        assertEquals(0, userController.getUsers().size()); // No user should be added
    }

    @Test
    public void testWriteUsers() {
        // Create tests.test users
        User user = new User("testUser", "John", "Doe", "password123", "123456789", "Student", "1000");
        // Add users to the controller
        userController.addUser(user);


        // Call the method under tests.test
        mockUser.expect(user);

        // Verify that the writeUsersToFile method was called with the expected arguments
        assertTrue(mockUser.verify());
    }
    @Test
    public void testWriteUserWithIOException() {

        // Create a sample book for testing
        User user = new User("testUser", "John", "Doe", "password123", "123456789", "Student", "1000");Book testBook = new Book("1234567890", "Test Book", "Category", "Supplier");

        // Add the tests.test book to the controller
        userController.addUser(user);

        // Set the flag to simulate an IOException
        mockUser.setShouldThrowIOException(true);

        // Call the method under tests.test
        userController.writeUsers();

        // Get the actual error message from the BookController
        String actualErrorMessage = userController.getErrorMessage();

        // Print the actual error message for further debugging
      //  System.out.println("Actual Error Message: " + actualErrorMessage);

        // Check if the actual error message contains the expected error description
        Assertions.assertTrue(actualErrorMessage != null && !actualErrorMessage.isEmpty());

      //  Assertions.assertTrue(actualErrorMessage.contains("Error writing books to file"),
        //        "Error message does not contain expected description for IOException");
    }
    @Test
    public void testReadBooksFromFileIOException() {

        // Simulate an IOException when reading books
        mockUser.setShouldThrowIOException(true);

        // Attempt to read books from file
        userController.readUsers();

        // Verify that an error message is set
        String errorMessage = userController.getErrorMessage();
        Assertions.assertTrue(errorMessage != null && !errorMessage.isEmpty());

    }
    @Test
   public void testSetFile() {


        File newFile = new File("newFile.bin");

        // Set the new file using setFile method
        userController.setFile(newFile);

        // Verify if the file in the BookController instance is updated
        File updatedFile = userController.filename; // Accessing the filename attribute directly

        Assertions.assertEquals(newFile, updatedFile,
                "File in BookController was not updated after setFile method");
    }

}

