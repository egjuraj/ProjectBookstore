package modelTest;

import model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SupplierTest {

    @Test
    public void testSupplierWithNameOnly() {
        String name = "Supplier A";
        Supplier supplier = new Supplier(name);

        assertEquals(name, supplier.getnameOfSupplier());
        assertNull(supplier.getEmail());
        assertNull(supplier.getPhone());
    }

    @Test
    public void testSupplierWithAllDetails() {
        String name = "Supplier B";
        String email = "supplierb@example.com";
        String phone = "1234567890";
        Supplier supplier = new Supplier(name, email, phone);

        assertEquals(name, supplier.getnameOfSupplier());
        assertEquals(email, supplier.getEmail());
        assertEquals(phone, supplier.getPhone());
    }

    @Test
    public void testSetEmail() {
        String name = "Supplier C";
        Supplier supplier = new Supplier(name);
        String email = "supplierc@example.com";

        supplier.setEmail(email);

        assertEquals(email, supplier.getEmail());
    }

    @Test
    public void testSetPhone() {
        String name = "Supplier D";
        Supplier supplier = new Supplier(name);
        String phone = "9876543210";

        supplier.setPhone(phone);

        assertEquals(phone, supplier.getPhone());
    }
}
