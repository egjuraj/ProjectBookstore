package tests.systemTesting;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import src.view.RegisterView;
public class RegisterViewTest extends ApplicationTest {

    private static final String firstNameField = "#firstNameField";
    private static final String lastNameField = "#lastNameField";
    private static final String usernameField = "#usernameField";
    private static final String passwordField = "#passwordField";
    private static final String verifyField = "#verifyField";
    private static final String phoneField = "#PhoneField";
    private static final String professionDropDown = "#professionDropDown";
    private static final String signUpButton = "#buton";

    @Override
    public void start(Stage stage) {
        RegisterView registerView = new RegisterView(null);  // Replace null with an actual User object if needed
        Scene scene = registerView.showView(stage);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void test_registerUserWithMismatchedPasswords() {
        // Simulate entering user details with mismatched passwords
        clickOn(firstNameField).write("John");
        clickOn(lastNameField).write("Doe");
        clickOn(usernameField).write("john.doe");
        clickOn(passwordField).write("password");
        clickOn(verifyField).write("differentPassword");  // Mismatched password
        clickOn(phoneField).write("0691234567");
        clickOn(professionDropDown).clickOn("Librarian");

        // Click on the "Add User" button
        clickOn(signUpButton);

        // Verify that an error message is displayed
        Assertions.assertTrue(lookup(".alert").tryQuery().isPresent());
    }
    @Test
    void test_registerUserSuccesfuly() {
        // Simulate entering user details with mismatched passwords
        clickOn(firstNameField).write("John");
        clickOn(lastNameField).write("Doe");
        clickOn(usernameField).write("johndoe");
        clickOn(passwordField).write("12345");
        clickOn(verifyField).write("12345");  // Mismatched password
        clickOn(phoneField).write("0691234567");
        clickOn(professionDropDown).clickOn("Librarian");

        // Click on the "Add User" button
        clickOn(signUpButton);

        // Verify that an error message is displayed
        Assertions.assertTrue(lookup(".alert").tryQuery().isPresent());
    }

}
