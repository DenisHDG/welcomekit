package br.com.neolog.welcomekit.customer;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.PasswordEncrypt;
import br.com.neolog.welcomekit.exceptions.CustomerEmailNotFundException;
import br.com.neolog.welcomekit.productCategory.ProductCategoryService;

@Component
public class CustomerService
{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncrypt hasPassword;

    private static final Logger logger = LogManager.getLogger( ProductCategoryService.class );

    public void createCustomer(
        final Customer customer )
    {

        if( customerRepository.existsByEmail( customer.getEmail() ) ) {
            final String messageError = "Customer " + customer.getEmail()
                + " alread in database, \n Customer don't registred";
            logger.error( messageError );
            throw new CustomerEmailNotFundException( messageError );

        }

        final String passwordCrypto = hasPassword.createCryptoPassMD5( customer.getPassword() );
        customer.setPassword( passwordCrypto );
        customerRepository.save( customer );
        logger.info( "Customer registred with sucess!" );
    }

    public void deleteCustomer(
        final String email )
    {
        if( ! customerRepository.existsByEmail( email ) ) {
            final String messageError = "Customer " + email + " dont existi in database!";
            logger.error( messageError );
            throw new CustomerEmailNotFundException( messageError );
        }
        final Customer customer = customerRepository.findByEmail( email );
        customerRepository.delete( customer );
    }

    public Customer findCustomerByEmail(
        final String email )
    {
        if( ! customerRepository.existsByEmail( email ) ) {
            final String messageError = "Customer " + email + " dont existi in database!";
            logger.error( messageError );
            throw new CustomerEmailNotFundException( messageError );
        }
        return customerRepository.findByEmail( email );
    }

    public List<Customer> findAllCustomers()
    {
        final List<Customer> listCustomers = customerRepository.findAll();
        return listCustomers;
    }

    public void updateCustomerByEmail(
        final Customer customer,
        final String email )
    {
        if( ! customerRepository.existsByEmail( email ) ) {
            final String messageError = "Customer " + email + " dont existi in database!";
            logger.error( messageError );
            throw new CustomerEmailNotFundException( messageError );
        }
        final Customer customerSave = customerRepository.findByEmail( email );
        customerSave.setName( customer.getName() );
        customerSave.setAge( customer.getAge() );
        customerSave.setPassword( customer.getPassword() );
        customerRepository.save( customerSave );
        logger.info( "Customer " + email + " updade with sucess!" );
    }
}
