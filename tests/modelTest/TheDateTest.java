package tests.modelTest;

import src.model.TheDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheDateTest {

    @Test
    public void testConstructorWithStringDate() {
        TheDate date = new TheDate("20/12/2023");

        assertEquals(20, date.getDay());
        assertEquals(12, date.getMonth());
        assertEquals(2023, date.getYear());
    }

    @Test
    public void testSettersAndGetters() {
        TheDate date = new TheDate(15, 5, 2025);

        assertEquals(15, date.getDay());
        assertEquals(5, date.getMonth());
        assertEquals(2025, date.getYear());

        date.setDay(25);
        date.setMonth(9);
        date.setY(2030);

        assertEquals(25, date.getDay());
        assertEquals(9, date.getMonth());
        assertEquals(2030, date.getYear());
    }

    @Test
    public void testToStringFormat() {
        TheDate date = new TheDate(10, 3, 2022);

        assertEquals("10/03/2022", date.toString());
    }
}
