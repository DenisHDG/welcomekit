package br.com.neolog.welcomekit.session;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.PasswordEncrypt;
import br.com.neolog.welcomekit.customer.Customer;
import br.com.neolog.welcomekit.customer.CustomerRepository;
import br.com.neolog.welcomekit.exceptions.CredentialsCustomerNotFoundException;
import br.com.neolog.welcomekit.productCategory.ProductCategoryService;

@Component
public class SessionService
{

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncrypt passwordEcrypt;

    static final Logger logger = LogManager.getLogger( ProductCategoryService.class );

    public String createCustomerSession(
        final Credentials credentials )
    {
        final Customer customer = verifyCredentialsLogin( credentials );
        if( customer == null ) {
            final String messageError = "Credentials not found, verify your credentials";
            logger.error( messageError );
            throw new CredentialsCustomerNotFoundException( messageError );
        }

        final String token = createTokenAuthentication();
        final LocalDateTime localDateTime = LocalDateTime.now().plusDays( 1 );
        final Session session = new Session( customer, token, localDateTime );
        sessionRepository.save( session );
        return token;

    }

    private Customer verifyCredentialsLogin(
        final Credentials credentials )
    {

        if( ! customerRepository.existsByEmail( credentials.getEmail() ) ) {
            logger.info( "Custumer " + credentials.getEmail() + " don't exists in database!" );
            return null;
        }

        final Customer customer = customerRepository.findByEmail( credentials.getEmail() );
        final String passCredentialsEncrypt = passwordEcrypt.createCryptoPassMD5( credentials.getPassword() );

        if( ! customer.getPassword().equals( passCredentialsEncrypt ) ) {
            logger.info( "password not equals" );
            return null;
        }

        return customer;

    }

    public String createTokenAuthentication()
    {
        final UUID uuid = UUID.randomUUID();
        final String random = uuid.toString();
        return passwordEcrypt.createCryptoPassMD5( random );
    }

}
