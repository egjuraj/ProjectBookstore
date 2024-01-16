package tests.systemTesting;


import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import src.model.User;
import src.view.AddBillView;

import java.time.LocalDate;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class AddBillViewTest extends ApplicationTest {

    private AddBillView addBillView;

    @Override
    public void start(Stage primaryStage) {
        User testUser = new User("TestUser", "LastName", "testuser", "password", "123456789", "profession", "salary");
        addBillView = new AddBillView(testUser);
        Scene scene = addBillView.showView(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    void testAddBillFunctionality() {
        // Interact with UI components
        clickOn("#nameField").write("TestBook");
        clickOn("#categoryField").clickOn("Fiction");
        clickOn("#supField").write("TestSupplier");
        clickOn("#quantField").write("5");
        clickOn("#priceField").write("20.0");
        clickOn("#dateP").type(KeyCode.valueOf(LocalDate.now().toString()));

        clickOn("#create");

        // Verify the expected changes
        verifyThat("#nameField", hasText(""));
        verifyThat("#categoryField", hasText(""));
        verifyThat("#supField", hasText(""));
        verifyThat("#quantField", hasText(""));
        verifyThat("#priceField", hasText(""));
        verifyThat("#dateP", hasText(""));

        // Verify that an alert is shown
        verifyThat(lookup(".dialog-pane"), hasText("The bill was created successfully."));

        // You may need to adjust the above verifications based on your actual UI behavior and structure
    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
    }
}
