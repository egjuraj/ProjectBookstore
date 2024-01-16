package tests.systemTesting;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import src.view.LoginView;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;
public class LoginViewTest extends ApplicationTest {


    @Override
    public void start(Stage stage) {
        LoginView loginView = new LoginView();
        Scene scene = loginView.showView(stage);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testLoginAndShowView() {
        // Interact with UI elements, e.g., login with valid credentials
        clickOn("#user").write("librarian");
        clickOn("#pass").write("librarian");
        clickOn("#login");

        // Verify that the Alert is visible after successful login
        FxAssert.verifyThat(".dialog-pane", isVisible());
    }

    @Test
    void testUnsuccessfulLogin() {
        // Locate username and password fields
        clickOn("#user").write("invalidUser");
        clickOn("#pass").write("invalidPassword");

        // Click on the login button
        clickOn("#login");

        // Verify that the error alert is displayed
        verifyThat(lookup(".alert"), isVisible());
    }

}
