package tests.modelTest;

import src.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User("John", "Doe", "johndoe", "password", "123456789", "Developer", "50000");
        String expected = "User [firstName=John, lastName=Doe, password=password, phone=123456789, " +
                "username=johndoe, profession=Developer, salary=50000, birthday=null, id=3]";
        assertEquals(expected, user.toString());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("123456789", user.getPhone());
        assertEquals("Developer", user.getProfession());
        assertEquals("50000", user.getSalary());
        assertEquals(3,user.getId());


        user.setFirstName("Alice");
        user.setLastName("Smith");
        user.setUsername("alicesmith");
        user.setPassword("newPassword");
        user.setPhone("987654321");
        user.setProfession("Manager");
        user.setSalary("60000");
        assertEquals("Alice", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("alicesmith", user.getUsername());
        assertEquals("newPassword", user.getPassword());
        assertEquals("987654321", user.getPhone());
        assertEquals("Manager", user.getProfession());
        assertEquals("60000", user.getSalary());
    }

    @Test
    public void testGetAndSetBirthday() {
        User user = new User("John", "Doe", "johndoe", "password", "123456789", "Developer", "50000");
        assertNull(user.getBirthday());

        user.setBirthday("1990-01-01");
        assertEquals("1990-01-01", user.getBirthday());
    }

    @Test
    public void testNrOfUsers() {
        User.setNrOfUsers(10);
        assertEquals(10, User.getNrOfUsers());
    }



}
