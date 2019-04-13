package br.com.neolog.welcomekit.productCategory;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import br.com.neolog.welcomekit.aplication.AbstractIntegrationTest;
import br.com.neolog.welcomekit.product.Product;
import io.restassured.http.ContentType;

public class ProductCategoryTest extends AbstractIntegrationTest {

	@Test
	public void shouldFindProductCategoryByCode() {
		ProductCategory productCategoryTest = new ProductCategory(123, "denis");
		ProductCategory productCategory = given().get("/category/123").then().log().everything().extract().body()
				.as(ProductCategory.class);
		assertThat(productCategory).isEqualTo(productCategoryTest);
	}

	@Test
	public void shouldReturnBadRequesteWhenFindProductCategoryByCodeWNotExists() {

		given().contentType(ContentType.JSON).when().get("/category/321").then().statusCode(400);
	}

	@Test
	public void shouldReturnBadRequestWhenCreateProductCategoryWithCodeAlreadyExisting() {
		final ProductCategory productCategoryTest = new ProductCategory(123, "Assados");
		given().contentType(ContentType.JSON).body(productCategoryTest).when().post("/category").then().statusCode(400);
	}

	@Test
	public void shouldReturnFindAllCategory() {
		final String url = "/category";
		final ProductCategory productCategoryTest = new ProductCategory(123, "denis");
		final List<ProductCategory> productCategoryListTest = asList(given().get(url).then().log().everything()
				.contentType(ContentType.JSON).extract().as(ProductCategory[].class));

		assertThat(productCategoryListTest.contains(productCategoryTest));

	}

	@Test
	public void shouldUpdateProductCategory() {
		final String url = "/category/123";
		final ProductCategory productCategoryTest = new ProductCategory(123, "denis");
		given().contentType(ContentType.JSON).body(productCategoryTest).when().put(url).then().statusCode(200);
		final ProductCategory productBeforeSave = findProductCategoryByCode(url);
		assertThat(productBeforeSave).isEqualTo(productCategoryTest);

	}

	@Test
	public void shouldReturnBadRequestWhenUpdateProductCategoryWithCodeNotExisting() {
		final String url = "/category/321";
		final ProductCategory productCategoryTest = new ProductCategory(321, "denis");
		given().contentType(ContentType.JSON).body(productCategoryTest).when().put(url).then().statusCode(400);

	}

	@Test
	public void sholdReturnStatusBadRequestWhenDeletProductByCodeIncorrect() {
		final String url = "/category/322";
		final ProductCategory productCategoryTest = new ProductCategory(322, "Grelhados");
		createProducCategoryForTest(productCategoryTest, url);

		given().pathParam("code", 321).when().delete("/category/{code}").then().statusCode(400);
	}

	@Test
	public void sholdReturnStatusOkWhenDeletProductByCode() {
		final String url = "/category/311";
		final ProductCategory productCategoryTest = new ProductCategory(311, "Cozidos");
		createProducCategoryForTest(productCategoryTest, url);

		given().pathParam("code", 311).when().delete("/category/{code}").then().statusCode(200);
	}

	public void backChanges(Product productBeforeSave) {
	}

	public void createProducCategoryForTest(final ProductCategory productCategoryTest, final String url) {

		given().contentType(ContentType.JSON).body(productCategoryTest).when().post("/category").then().statusCode(200);
		final ProductCategory productCategory = findProductCategoryByCode(url);
		assertThat(productCategory).isEqualTo(productCategoryTest);

	}

	public ProductCategory findProductCategoryByCode(String url) {
		if (url != null) {
			return given().get(url).then().log().everything().extract().body().as(ProductCategory.class);
		}
		return null;
	}

}
