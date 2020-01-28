package core;

import dtupay.DtuPayCustomerRepresentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerPool
{
    private static Map<String, DtuPayCustomerRepresentation> customers = new HashMap<>();

    public static DtuPayCustomerRepresentation get(String cpr)
    {
        return customers.get(cpr);
    }

    public static Collection<DtuPayCustomerRepresentation> getAll()
    {
        return customers.values();
    }

    public static boolean create(DtuPayCustomerRepresentation c)
    {
        if (!customers.containsKey(c.getCprNumber()))
        {
            customers.put(c.getCprNumber(), c);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean update(DtuPayCustomerRepresentation c)
    {
        if (!customers.containsKey(c.getCprNumber()))
        {
            return false;
        }
        else
        {
            customers.replace(c.getCprNumber(), c);
            return true;
        }
    }

    public static boolean delete(String cpr)
    {
        if (!customers.containsKey(cpr))
        {
            return false;
        }
        else
        {
            customers.remove(cpr);
            return true;
        }
    }
}
