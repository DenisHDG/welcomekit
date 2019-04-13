package br.com.neolog.welcomekit.productCategory;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.exceptions.ConflictUnicElementException;
import br.com.neolog.welcomekit.exceptions.ProductNotFoundException;

@Component
public class ProductCategoryService
{

    private static final Logger logger = LogManager.getLogger( ProductCategoryService.class );

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public void create(
        final ProductCategory productCategory )
    {
        if( productCategory == null ) {
            return;
        }
        if( productCategoryRepository.existsByCode( productCategory.getCode() ) ) {
            final String messageError = "Category " + productCategory
                + " do not create, that category already exists in the database";
            logger.warn( messageError );
            throw new ConflictUnicElementException( messageError );

        }
        productCategoryRepository.save( productCategory );
        logger.info( "Creating category", productCategory.getCode() );
    }

    public void deleteByCode(
        final int code )
    {
        if( ! productCategoryRepository.existsByCode( code ) ) {
            final String messageError = "Code " + code + "don't exists in database!";
            logger.warn( messageError );
            throw new ProductNotFoundException( messageError );
        }

        final ProductCategory productCategory = productCategoryRepository.findByCode( code );
        productCategoryRepository.delete( productCategory );
        logger.info( "Category deleted with sucess!" );
    }

    public ProductCategory findByCode(
        final int code )
    {
        if( ! productCategoryRepository.existsByCode( code ) ) {
            final String messageError = "Codigo " + code + " não existe in database!";
            logger.warn( messageError );
            throw new ProductNotFoundException( messageError );
        }
        return productCategoryRepository.findByCode( code );
    }

    public List<ProductCategory> findAllCategories()
    {
        final List<ProductCategory> listProductCategoryList = productCategoryRepository.findAll();
        return listProductCategoryList;
    }

    public void update(
        final ProductCategory productCategory )
    {

        if( ! productCategoryRepository.existsByCode( productCategory.getCode() ) ) {
            final String messageError = "Update fail " + " code product don't exists!";
            logger.warn( messageError );
            throw new ProductNotFoundException( messageError );
        }

        final ProductCategory productCategoryToUpdate = productCategoryRepository.findByCode( productCategory.getCode() );
        productCategoryToUpdate.setName( productCategory.getName() );
        productCategoryRepository.save( productCategoryToUpdate );
        logger.info( "Category save with sucess" );

    }
}
