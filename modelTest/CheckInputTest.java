package modelTest;

import model.CheckInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckInputTest {

    @Test
    public void testCheckUsername_Valid() {
        CheckInput checkInput = new CheckInput();
        assertTrue(checkInput.checkUsername("JohnDoe"));
    }

    @Test
    public void testCheckUsername_Invalid() {
        CheckInput checkInput = new CheckInput();
        assertFalse(checkInput.checkUsername("JD"));
    }

    @Test
    public void testCheckPassword_Valid() {
        CheckInput checkInput = new CheckInput();
        assertTrue(checkInput.checkPassword("StrongPass12"));
    }

    @Test
    public void testCheckPassword_Invalid() {
        CheckInput checkInput = new CheckInput();
        assertFalse(checkInput.checkPassword("Weak"));
    }

    @Test
    public void testCheckEmail_Valid() {
        CheckInput checkInput = new CheckInput();
        assertTrue(checkInput.checkEmail("test@example.com"));
    }

    @Test
    public void testCheckEmail_Invalid() {
        CheckInput checkInput = new CheckInput();
        assertFalse(checkInput.checkEmail("invalid-email"));
    }

    @Test
    public void testCheckPhone_Valid() {
        CheckInput checkInput = new CheckInput();
        assertTrue(checkInput.checkPhone("0678954321"));
    }

    @Test
    public void testCheckPhone_Invalid() {
        CheckInput checkInput = new CheckInput();
        assertFalse(checkInput.checkPhone("0123456789"));
    }

    @Test
    public void testCheckSalary() {
        CheckInput checkInput = new CheckInput();
        assertTrue(checkInput.checksalary("AnyValueHere"));
    }
}
