package br.com.neolog.welcomekit.exceptions;

public class PasswordEncriptErrorException
    extends
        RuntimeException
{

    /**
     * Erro de encriptação de password
     */
    private static final long serialVersionUID = 2159810080588266563L;

    public PasswordEncriptErrorException(
        final String message )
    {
        super( message );
    }
}
