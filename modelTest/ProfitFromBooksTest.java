package modelTest;

import model.ProfitFromBooks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfitFromBooksTest {

    @Test
    public void testGettersAndSetters() {
        ProfitFromBooks profit = new ProfitFromBooks("Book Title", 10, 25.5);
        assertEquals("Book Title", profit.setTitleOfBook());
        profit.setTitleOfBook("New Title");
        assertEquals("New Title", profit.setTitleOfBook());

        assertEquals(10, profit.getQuantity());
        profit.setQuantity(20);
        assertEquals(20, profit.getQuantity());

        assertEquals(25.5, profit.getPrice());
        profit.setPrice(30.75);
        assertEquals(30.75, profit.getPrice());
    }

    @Test
    public void testCalculateTotalIncome() {
        ProfitFromBooks profit = new ProfitFromBooks("Book Title", 10, 25.5);
        assertEquals(255.0, profit.calculateTotalIncome(10, 25.5), 0.01);
    }

    @Test
    public void testToString() {
        ProfitFromBooks profit = new ProfitFromBooks("Book Title", 10, 25.5);
        assertEquals("ProfitFromBooks [titleOfBook=Book Title, quantity=10, price=25.5]", profit.toString());
    }
}
