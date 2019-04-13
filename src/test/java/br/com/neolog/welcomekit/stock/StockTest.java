package br.com.neolog.welcomekit.stock;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mockito.InjectMocks;

import br.com.neolog.welcomekit.aplication.AbstractIntegrationTest;
import br.com.neolog.welcomekit.product.Product;
import br.com.neolog.welcomekit.productCategory.ProductCategory;
import io.restassured.http.ContentType;

public class StockTest
    extends
        AbstractIntegrationTest
{

    @InjectMocks
    StockService stockService;

    @Test
    public void sholdReturnStatusOkWhenCreateProductInStock()
    {
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "Celular", 50, 300.50, 12.0, 15.0, productCategoryTest );
        final Stock stockTest = new Stock( productTest, 500 );
        given().contentType( ContentType.JSON ).body( stockTest ).when().post( "/stock" ).then().statusCode( 200 );
        final Stock stock = given().get( "/stock/50" ).then().log().everything().extract().body().as( Stock.class );
        assertThat( stock ).isEqualTo( stockTest );
        given().pathParam( "code", 50 ).when().delete( "/stock/{code}" ).then().statusCode( 200 );

    }

    @Test
    public void sholdReturnStockFindByProductCode()
    {
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "camiseta", 255, 2.50, 10.0, 10.0, productCategoryTest );
        final Stock stockTest = new Stock( productTest, 0 );
        final Stock stock = given().get( "/stock/255" ).then().log().everything().extract().body().as( Stock.class );
        assertThat( stock ).isEqualTo( stockTest );

    }

    @Test
    public void sholdReturnBadRequestStockFindByProductCodeNotExisting()
    {
        given().contentType( ContentType.JSON ).when().get( "/stock/33" ).then().statusCode( 400 );
    }

    /**
     * Após validacao de que a atulização estar correta, atualizamos novamente
     * para não atrapalhar os outros testes
     */

    @Test
    public void sholdUpdateStockByProductCode()
    {

        final String url = "/stock/255";
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "camiseta", 255, 2.50, 10.0, 10.0, productCategoryTest );
        final Stock stockTest = new Stock( productTest, 51 );
        given().contentType( ContentType.JSON ).body( stockTest ).when().put( url ).then().statusCode( 200 );

        final Stock stock = given().get( url ).then().log().everything().extract().body().as( Stock.class );
        assertThat( stock ).isEqualTo( stockTest );

        afterTestBackConfigurationStock();
    }

    @Test
    public void sholdReturnBadRequestWhenUpdateStockByProductCodeNotExisting()
    {
        final String url = "/stock/256";
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "camiseta", 255, 2.50, 10.0, 10.0, productCategoryTest );
        Stock stockTest = new Stock( productTest, 51 );
        given().contentType( ContentType.JSON ).body( stockTest ).when().put( url ).then().statusCode( 400 );

    }

    public void afterTestBackConfigurationStock()
    {

        final String url = "/stock/255";
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "camiseta", 255, 2.50, 10.0, 10.0, productCategoryTest );
        Stock stockTest = new Stock( productTest, - 51 );

        given().contentType( ContentType.JSON ).body( stockTest ).when().put( url ).then().statusCode( 200 );

        Stock stock = given().get( url ).then().log().everything().extract().body().as( Stock.class );

        Stock stockBeforeChange = given().get( url ).then().log().everything().extract().body().as( Stock.class );
        assertThat( stock ).isEqualTo( stockBeforeChange );

    }
}
