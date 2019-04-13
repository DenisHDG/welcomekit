package br.com.neolog.welcomekit.product;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.exceptions.CodeCategoryNotFoundException;
import br.com.neolog.welcomekit.exceptions.ProductCategoryNotFoundException;
import br.com.neolog.welcomekit.productCategory.ProductCategory;
import br.com.neolog.welcomekit.productCategory.ProductCategoryRepository;
import br.com.neolog.welcomekit.productCategory.ProductCategoryService;
import br.com.neolog.welcomekit.stock.StockRepository;
import br.com.neolog.welcomekit.stock.StockService;

@Component
public class ProductService
{

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockService stockService;

    static final Logger logger = LogManager.getLogger( ProductCategoryService.class );

    public void createProduct(
        final Product product )
    {

        if( productRepository.existsByCode( product.getCode() ) ) {
            final String messageError = "Code product " + product.getCode() + "alread exists in datebase!";
            logger.warn( messageError );
            throw new ProductCategoryNotFoundException( messageError );
        }

        final ProductCategory productCategory = productCategoryRepository
            .findByCode( product.getProductCategory().getCode() );
        product.setProductCategory( productCategory );
        productRepository.save( product );
        logger.info( "Product " + product.getName() + " registred with sucess!" );
    }

    public void deleteProductByCode(
        final int code )
    {
        if( ! productRepository.existsByCode( code ) ) {
            final String messageError = "Code " + code + "code don't exists in database";
            logger.warn( messageError );
            throw new ProductCategoryNotFoundException( messageError );

        }
        final Product product = productRepository.findByCode( code );
        productRepository.delete( product );
        logger.info( "Product " + product.getName() + " deleted with sucess!" );
    }

    public void updateProductByCode(
        final Product product )
    {
        if( ! productRepository.existsByCode( product.getCode() ) ) {
            final String messageError = "Product code " + product.getCode() + " don't exists in database";
            logger.warn( messageError );
            throw new ProductCategoryNotFoundException( messageError );
        }
        if( ! productCategoryRepository.existsByCode( product.getProductCategory().getCode() ) ) {
            final String messageError = "new code product category " + product.getProductCategory().getCode()
                + "updade not realized";
            logger.warn( messageError );
            throw new CodeCategoryNotFoundException( messageError );
        }
        final ProductCategory productCategory = productCategoryRepository
            .findByCode( ( product.getProductCategory().getCode() ) );
        final Product newProduct = productRepository.findByCode( product.getCode() );
        newProduct.setName( product.getName() );
        newProduct.setPrice( product.getPrice() );
        newProduct.setProductCategory( productCategory );
        newProduct.setVolume( product.getVolume() );
        newProduct.setWeight( product.getWeight() );
        productRepository.save( newProduct );
        logger.info( "Product updade with sucess!" );
    }

    public List<Product> findAllProduct()
    {
        final List<Product> listProduct = productRepository.findAll();
        return listProduct;
    }

    public Product findProductByCode(
        final int code )
    {

        if( ! productRepository.existsByCode( code ) ) {
            final String messageError = "Product " + code + "don't exists";
            logger.error( messageError );
            throw new ProductCategoryNotFoundException( messageError );
        }
        return productRepository.findByCode( code );
    }
}