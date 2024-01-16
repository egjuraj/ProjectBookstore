package tests.systemTesting;


import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import src.model.User;
import src.view.AddSupplier;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
public class AddSupplierTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        User currentUser = new User("testUser", "Test User", "tes12", "123456789", "0698196688","Librarian","500");
        AddSupplier addSupplier = new AddSupplier(currentUser);
        Scene scene = addSupplier.showView(stage);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void test_addSupplier() {
        // Simulate entering valid supplier details
        clickOn("#nameField").write("SupplierName");
        clickOn("#emailField").write("supplier@example.com");
        clickOn("#phoneField").write("0691234567");

        // Click on the "Next" button
        clickOn("#next");

        // Verify that the alert indicating successful addition is visible
        verifyThat(".alert", isVisible());
    }

}

