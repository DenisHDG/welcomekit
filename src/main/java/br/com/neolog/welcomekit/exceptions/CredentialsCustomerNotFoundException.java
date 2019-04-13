package br.com.neolog.welcomekit.exceptions;

public class CredentialsCustomerNotFoundException
    extends
        RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 3454376860738519506L;

    public CredentialsCustomerNotFoundException(
        final String message )
    {
        super( message );
    }

}
