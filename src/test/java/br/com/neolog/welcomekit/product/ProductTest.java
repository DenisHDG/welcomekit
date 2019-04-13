package br.com.neolog.welcomekit.product;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.neolog.welcomekit.aplication.AbstractIntegrationTest;
import br.com.neolog.welcomekit.productCategory.ProductCategory;
import io.restassured.http.ContentType;

public class ProductTest
    extends
        AbstractIntegrationTest
{

    /**
     * Criado produto e apagado depois para não influenciar nos futuros testes
     */

    @Test
    public void shouldCreateProduct()
    {
        final String urlProduct = "/product/222";
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "Bala", 222, 5.0, 5.0, 5.0, productCategoryTest );
        given().contentType( ContentType.JSON ).body( productTest ).when().post( "/product" ).then().statusCode( 200 );
        final Product product = findProductByCode( urlProduct );
        assertThat( product ).isEqualTo( productTest );

    }

    @Test
    public void shouldReturnBadRequestWhenCreateProductWithCodeAlreadyExisting()
    {

        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "Bala", 50, 5.0, 5.0, 5.0, productCategoryTest );
        given().contentType( ContentType.JSON ).body( productTest ).when().post( "/product" ).then().statusCode( 400 );

    }

    @Test
    public void shouldReturnFindProductByCode()
    {
        final String url = "/product/255";
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "camiseta", 255, 2.50, 10.0, 10.0, productCategoryTest );
        final Product product = findProductByCode( url );
        assertThat( product ).isEqualTo( productTest );
    }

    @Test
    public void shouldReturnBadRequestWhenFindProductByCodeWithCodeNotExisting()
    {
        given().contentType( ContentType.JSON ).when().get( "/product/3000" ).then().statusCode( 400 );
    }

    @Test
    public void shouldUpdateProduct()
    {

        final String url = "/product/50";
        final Product productBeforeSave = findProductByCode( url );
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTestAfterChange = new Product( "teste de alteracao", 50, 6.0, 6.0, 6.0,
            productCategoryTest );
        given().contentType( ContentType.JSON ).body( productTestAfterChange ).when().put( "/product" ).then()
            .statusCode( 200 );
        final Product productAfterSave = findProductByCode( url );
        assertThat( productAfterSave ).isEqualTo( productTestAfterChange );
        given().contentType( ContentType.JSON ).body( productBeforeSave ).when().put( "/product" ).then().statusCode( 200 );

    }

    @Test
    public void shouldReturnBadRequestWhenUpdateProductWithCodeNotExisting()
    {

        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTestAfterChange = new Product( "teste de alteracao", 500, 6.0, 6.0, 6.0,
            productCategoryTest );
        given().contentType( ContentType.JSON ).body( productTestAfterChange ).when().put( "/product" ).then()
            .statusCode( 400 );
    }

    @Test
    public void shouldReturnFindAllProducts()
    {
        final String url = "/product";
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "camiseta", 255, 2.50, 10.0, 10.0, productCategoryTest );
        final Product productTest2 = new Product( "Celular", 50, 300.50, 12.0, 15.0, productCategoryTest );
        final List<Product> productListTest = new ArrayList<>();
        productListTest.add( productTest );
        productListTest.add( productTest2 );
        final List<Product> products = asList(
            given().get( url ).then().log().everything().contentType( ContentType.JSON ).extract().as( Product[].class ) );
        assertThat( products ).isEqualTo( ( productListTest ) );
    }

    @Test
    public void shouldReturnStatusBadRequestWhenDeletProductByCodeIncorrect()
    {
        final String urlProduct = "/product/796";
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "Ketchup", 796, 5.0, 5.0, 5.0, productCategoryTest );
        createProductForDeletTest( productTest, urlProduct );
        given().pathParam( "code", 798 ).when().delete( "/product/{code}" ).then().statusCode( 400 );
        given().pathParam( "code", 796 ).when().delete( "/product/{code}" ).then().statusCode( 200 );
    }

    @Test
    public void shouldReturnStatusOkWhenDeletProductByCode()
    {
        final String urlProduct = "/product/797";
        final ProductCategory productCategoryTest = new ProductCategory( 123, "denis" );
        final Product productTest = new Product( "Mostarda", 797, 5.0, 5.0, 5.0, productCategoryTest );
        createProductForDeletTest( productTest, urlProduct );
        given().pathParam( "code", 797 ).when().delete( "/product/{code}" ).then().statusCode( 200 );
    }

    public void createProductForDeletTest(
        final Product productTest,
        final String urlProduct )
    {
        given().contentType( ContentType.JSON ).body( productTest ).when().post( "/product" ).then().statusCode( 200 );
        final Product product = findProductByCode( urlProduct );
        assertThat( product ).isEqualTo( productTest );
    }

    public Product findProductByCode(
        String url )
    {
        if( url != null ) {
            return given().get( url ).then().log().everything().contentType( ContentType.JSON ).extract().body()
                .as( Product.class );
        }
        return null;
    }
}
