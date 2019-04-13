package br.com.neolog.welcomekit;

import br.com.neolog.welcomekit.customer.Customer;

public class ThreadCustomer
{

    private static ThreadLocal<Customer> threadCustomer = new ThreadLocal<>();

    public static Customer getCustomer()
    {
        return threadCustomer.get();
    }

    public static void setCustomer(
        final Customer customer )
    {
        threadCustomer.set( customer );
    }

    public static void removeCustomer()
    {
        threadCustomer.remove();
    }

}
