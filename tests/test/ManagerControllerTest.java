package tests.test;

import src.Controller.LibrarianController;
import src.Controller.ManagerController;
import src.model.Manager;
import src.model.ProfitFromBooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class ManagerControllerTest {
    private ManagerController managerController;


    @BeforeEach
    public void setUp() {
        ArrayList<ProfitFromBooks> profitFromBooks = new ArrayList<>();
        managerController = new ManagerController(profitFromBooks);

        // Creating and adding managers using the addManager method
        managerController.addManager(new Manager("Elisa", "Gjuraj", "el", "1234", "06888", "manager", "111"));
        managerController.addManager(new Manager("El", "Gjuj", "e", "1234", "06888", "manager", "111"));
    }


    @Test

    public void testAddItem() {
        //assertNotNull("ManagerController should not be null", managerController);


        // Create a ProfitFromBooks object using the constructor
        ProfitFromBooks item = new ProfitFromBooks("Elisa", 4, 4.5);

        // Add the item using the addItem method
        managerController.addItem(item);
        assertEquals(1, managerController.getProfitFromBooks());

        // Verify if the added item exists in the list (if applicable)
        // assertTrue(managerController.getProfitItemsList().contains(item));
        // Perform assertions or verifications based on your tests.test requirements
    }

    @Test
    public void testGet() {
        ArrayList<Manager> retrievedManagers = managerController.getManager();

        // assertNotNull(retrievedManagers, "Retrieved managers list should not be null");
        assertEquals(2, retrievedManagers.size());
    }

    @Test
    public void testSetManager() {
        //assertNotNull(managerController, "ManagerController should not be null");

        // Creating a new list of managers
        ArrayList<Manager> newManagersList = new ArrayList<>();
        newManagersList.add(new Manager("Elisa", "Gjuraj", "el", "1234", "06888", "manager", "111"));
        newManagersList.add(new Manager("El", "Gjuj", "e", "1234", "06888", "manager", "111"));

        // Setting the new list of managers using setManager()
        managerController.setManager(newManagersList);

        // Retrieving the managers after setting the new list
        ArrayList<Manager> retrievedManagers = managerController.getManager();

        //  assertNotNull(retrievedManagers, "Retrieved managers list should not be null");
        assertEquals(2, retrievedManagers.size());

    }

    @ParameterizedTest
    @CsvSource({
            "5, OK",
            "3, FAIL",
            "7, OK",
            "1, FAIL"
    })
    public void testCheckLibrarianPerformance(int nrBills, String expected) {
        LibrarianController librarian = new LibrarianController(null);
        librarian.setNrBills(nrBills);

        String result = managerController.checkLibrarianPerformance(librarian);
        assertEquals(expected, result);
    }
    @Test
    public void testSetProfitFromBooks() {
        assertNotNull(managerController, "ManagerController should not be null");

        // Set profit from books
        managerController.setProfitFromBooks(10);

        // Get and assert the value set by setProfitFromBooks()
        assertEquals(10, managerController.getProfitFromBooks());
    }
}
