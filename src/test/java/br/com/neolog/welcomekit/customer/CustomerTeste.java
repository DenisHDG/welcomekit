package br.com.neolog.welcomekit.customer;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.neolog.welcomekit.PasswordEncrypt;
import br.com.neolog.welcomekit.aplication.AbstractIntegrationTest;
import io.restassured.http.ContentType;

public class CustomerTeste
    extends
        AbstractIntegrationTest
{

    @Autowired
    PasswordEncrypt passwordEncrypt;

    @Test
    public void shouldCreatecustomer()
    {

        final Customer customerTest = new Customer( "Banana", "banana.banininha@gmail.com", 31, "banana123" );
        given().contentType( ContentType.JSON ).body( customerTest ).when().post( "/customer" ).then().statusCode( 200 );
        given().pathParam( "email", "banana.banininha@gmail.com" ).when().delete( "/customer/{email}" ).then().statusCode( 200 );

    }

    @Test
    public void shouldReturnBadRuestWhenCreatecustomerWithCodeAlreadyExisting()
    {

        final String url = "/customer/banana.banininha@gmail.com";
        final Customer customerTest = new Customer( "Banana", "banana.banininha@gmail.com", 25, "banana123" );
        createcustomerForTest( customerTest, url );
        given().pathParam( "email", "banana.banininha@gmail.com" ).when().delete( "/customer/{email}" ).then().statusCode( 200 );

    }

    @Test
    public void shouldReturnBadRequestWhenFindcustomerByEmailNotFund()
    {
        final String url = "/customer/denis.goncalves.not.fund@gmail.com";
        given().contentType( ContentType.JSON ).when().get( url ).then().statusCode( 400 );
    }

    @Test
    public void shouldReturnFindcustomerByEmail()
    {
        final String url = "/customer/denis.goncalves@gmail.com";
        final Customer customerTest = new Customer( "Denis Gonçalves", "denis.goncalves@gmail.com", 31, "banana123" );
        final Customer customer = findcustomerByEmail( url );
        assertThat( customer ).isEqualTo( customerTest );
    }

    @Test
    public void shouldUpdatecustomerAndReturnStatusOk()
    {
        final String url = "/customer/ppp@gmail.com";
        final Customer customerTestAfter = new Customer( "Pedro Paulo Perez", "ppp@gmail.com", 20, "ppp123" );
        final Customer customerTest = new Customer( "Pedro Paulo Perez Pereira", "ppp@gmail.com", 25, "pppp123" );
        given().contentType( ContentType.JSON ).body( customerTest ).when().put( url ).then().statusCode( 200 );
        final Customer customerBeforeSave = findcustomerByEmail( url );
        assertThat( customerBeforeSave ).isEqualTo( customerTest );
        given().contentType( ContentType.JSON ).body( customerTestAfter ).when().put( url ).then().statusCode( 200 );

    }

    @Test
    public void shouldReturnBadRequestWhenUpdatecustomerWithEmailNotExists()
    {
        final String url = "/customer/ppppp@gmail.com";
        final Customer customerTest = new Customer( "Pedro Paulo Perez Pereira", "ppp@gmail.com", 25, "pppp123" );
        given().contentType( ContentType.JSON ).body( customerTest ).when().put( url ).then().statusCode( 400 );
    }

    @Test
    public void shouldReturnFindAllcustomers()
    {
        final String url = "/customer";
        final Customer customerTest = new Customer( "Denis Gonçalves", "denis.goncalves@gmail.com", 31, "banana123" );
        final Customer customerTest2 = new Customer( "Pedro Paulo Perez", "ppp@gmail.com", 20, "ppp123" );
        final List<Customer> customerListTest = new ArrayList<>();
        customerListTest.add( customerTest );
        customerListTest.add( customerTest2 );
        final List<Customer> customers = asList( given().get( url ).then().log().everything().contentType( ContentType.JSON )
            .extract().as( Customer[].class ) );
        assertThat( customers ).isEqualTo( customerListTest );
    }

    /**
     * Ação para verificar o retoruno quando passado email incorreto na
     * tentativa de deletar o client Ao final está sendo deletado corretamente
     * para não interferir nos outros testes
     */

    @Test
    public void shouldReturnStatusBadRequestWhenDeletcustomerByEmailIncorrectIncorrect()
    {
        final String url = "/customer/barry.allen@gmail.com";
        final Customer customerTest = new Customer( "Barry Allen", "barry.allen@gmail.com", 25, "flash123" );
        createcustomerForTest( customerTest, url );
        given().pathParam( "email", "tangamandapio@gmail.com" ).when().delete( "/customer/{email}" ).then().statusCode( 400 );
        given().pathParam( "email", "barry.allen@gmail.com" ).when().delete( "/customer/{email}" ).then().statusCode( 200 );
    }

    @Test
    public void shouldReturnStatusOkWhenDeletProductByCode()
    {
        final String url = "/customer/oliver.quen@gmail.com";
        final Customer customerTest = new Customer( "Oliver Quen", "oliver.quen@gmail.com", 30, "greenArrow132" );
        createcustomerForTest( customerTest, url );
        given().pathParam( "email", "oliver.quen@gmail.com" ).when().delete( "/customer/{email}" ).then().statusCode( 200 );
    }

    public Customer findcustomerByEmail(
        String url )
    {
        if( url != null ) {
            return given().get( url ).then().log().everything().contentType( ContentType.JSON ).extract().body()
                .as( Customer.class );
        }
        return null;
    }

    public void createcustomerForTest(
        final Customer customerTest,
        final String url )
    {

        final String passEncryptTest = passwordEncrypt.createCryptoPassMD5( customerTest.getPassword() );
        given().contentType( ContentType.JSON ).body( customerTest ).when().post( "/customer" ).then().statusCode( 200 );
        customerTest.setPassword( passEncryptTest );
        final Customer customer = findcustomerByEmail( url );
        assertThat( customer ).isEqualTo( customerTest );
    }
}
