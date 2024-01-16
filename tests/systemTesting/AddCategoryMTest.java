package tests.systemTesting;

        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import org.junit.jupiter.api.Test;
        import org.testfx.framework.junit5.ApplicationTest;
        import src.model.User;
        import src.view.AddCategoryM;

        import static org.testfx.api.FxAssert.verifyThat;
        import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class AddCategoryMTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        User currentUser = new User("testUser", "Test User", "tes12", "123456789", "0698196688", "Librarian", "500");
        AddCategoryM addCategoryM = new AddCategoryM(currentUser);
        Scene scene = addCategoryM.showView(stage);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void test_addCategory() {
        // Simulate entering valid category details
        clickOn("#CategoryField").write("Test");

        // Adding a slight delay to wait for GUI interactions to complete
        sleep(500);

        // Click on the "Add" button
        clickOn("#add");

        // Verify that the alert indicating successful addition is visible
        verifyThat(".alert", isVisible());
    }
}
