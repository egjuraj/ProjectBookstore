package modelTest;

import model.Librarian;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LibrarianTest {

    @Test
    public void testGetAccessLevel() {
        Librarian librarian = new Librarian("John", "Doe", "john_doe", "password123", "1234567890", "Librarian", "50000");
        assertNull(librarian.getAccess_level());
    }

    @Test
    public void testSetAccessLevel() {
        Librarian librarian = new Librarian("John", "Doe", "john_doe", "password123", "1234567890", "Librarian", "50000");
        librarian.setAccess_level("Admin");
        assertEquals("Admin", librarian.getAccess_level());
    }

    @Test
    public void testToString() {
        Librarian librarian = new Librarian("John", "Doe", "john_doe", "password123", "1234567890", "Librarian", "50000");
        librarian.setAccess_level("Admin");
        assertEquals("Librarian [access_level=Admin]", librarian.toString());
    }
}
