package br.com.neolog.welcomekit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.exceptions.PasswordEncriptErrorException;

@Component
public class PasswordEncrypt
{

    public String createCryptoPassMD5(
        final String passwordToHash )
    {
        final String messageError = "Error encrypted password";
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance( "MD5" );
            messageDigest.update( passwordToHash.getBytes() );
            final byte[] bytes = messageDigest.digest();
            final StringBuilder stringBuilder = new StringBuilder();
            for( int i = 0; i < bytes.length; i++ ) {
                stringBuilder.append( Integer.toString( ( bytes[ i ] & 0xff ) + 0x100, 16 ).substring( 1 ) );
            }
            return stringBuilder.toString();
        } catch( NoSuchAlgorithmException e ) {
            throw new PasswordEncriptErrorException( messageError );
        }
    }
}
