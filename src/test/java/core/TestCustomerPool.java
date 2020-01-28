package core;

import dtupay.DtuPayCustomerRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCustomerPool
{
    DtuPayCustomerRepresentation testCustomer =
            new DtuPayCustomerRepresentation("Test", "Customer", "1234", "4321");

    @BeforeEach
    public void initiate()
    {
        CustomerPool.getAll().clear();
        CustomerPool.create(testCustomer);
    }

    @DisplayName("Test CustomerPool.create()")
    @Test
    public void createCustomer()
    {
        boolean customerHasBeenCreated = CustomerPool.getAll().contains(testCustomer);
        assertTrue(customerHasBeenCreated);
    }

    @DisplayName("Test CustomerPool.getAll().contains")
    @Test
    public void getCustomer()
    {
        boolean customerExists = CustomerPool.getAll().contains(testCustomer);
        assertTrue(customerExists);
    }

    @DisplayName("Test CustomerPool.update()")
    @Test
    public void updateCustomer()
    {
        String newLastName = "Updated Last Name";
        testCustomer.setLastName(newLastName);
        boolean isUpdated = CustomerPool.update(testCustomer);
        assertTrue(isUpdated);
        assertEquals(newLastName, testCustomer.getLastName());
    }

    @DisplayName("Test CustomerPool.delete()")
    @Test
    public void deleteCustomer()
    {
        boolean isDeleted = CustomerPool.delete(testCustomer.getCprNumber());
        assertTrue(isDeleted);
    }
}
