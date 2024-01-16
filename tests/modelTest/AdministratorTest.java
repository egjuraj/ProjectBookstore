package tests.modelTest;

import src.model.Administrator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    @Test
    public void testGetSetAccessLevel() {
        Administrator admin = new Administrator("John", "Doe", "johndoe", "password", "123456789", "IT", "50000");

        // Set and get access level
        admin.setAccessLevel("Superuser");
        assertEquals("Superuser", admin.getAccessLevel());

        // Set access level to null and verify it is set properly
        admin.setAccessLevel(null);
        assertNull(admin.getAccessLevel());
    }

    @Test
    public void testToString() {
        Administrator admin = new Administrator("John", "Doe", "johndoe", "password", "123456789", "IT", "50000");

        // Default toString() representation before setting access level
        assertEquals("Administrator [accessLevel=null]", admin.toString());

        // Set access level
        admin.setAccessLevel("Admin");
        assertEquals("Administrator [accessLevel=Admin]", admin.toString());
    }

    @Test
    public void testConstructor() {
        Administrator admin = new Administrator("John", "Doe", "johndoe", "password", "123456789", "IT", "50000");

        // Verify all fields are set correctly in the constructor
        assertEquals("John", admin.getFirstName());
        assertEquals("Doe", admin.getLastName());
        assertEquals("johndoe", admin.getUsername());
        assertEquals("password", admin.getPassword());
        assertEquals("123456789", admin.getPhone());
        assertEquals("IT", admin.getProfession());
        assertEquals("50000", admin.getSalary());
    }
}
