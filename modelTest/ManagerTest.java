package modelTest;

import model.Manager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ManagerTest {

    @Test
    public void testGetAccessLevel() {
        Manager manager = new Manager("Jane", "Doe", "jane_doe", "pass123", "9876543210", "Manager", "70000");
        assertNull(manager.getAccess_level());
    }

    @Test
    public void testSetAccessLevel() {
        Manager manager = new Manager("Jane", "Doe", "jane_doe", "pass123", "9876543210", "Manager", "70000");
        manager.setAccess_level("SuperAdmin");
        assertEquals("SuperAdmin", manager.getAccess_level());
    }

    @Test
    public void testToString() {
        Manager manager = new Manager("Jane", "Doe", "jane_doe", "pass123", "9876543210", "Manager", "70000");
        manager.setAccess_level("SuperAdmin");
        assertEquals("Manager [access_level=SuperAdmin]", manager.toString());
    }
}
