package br.com.neolog.welcomekit.exceptions;

public class ProductNotFoundException
    extends
        RuntimeException

{

    /**
     * 
     */
    private static final long serialVersionUID = 7830727742535014635L;

    public ProductNotFoundException(
        final String message )
    {
        super( message );
    }

}
