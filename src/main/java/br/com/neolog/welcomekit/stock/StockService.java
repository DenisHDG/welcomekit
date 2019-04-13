package br.com.neolog.welcomekit.stock;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.exceptions.ProductNotFoundException;
import br.com.neolog.welcomekit.product.Product;
import br.com.neolog.welcomekit.product.ProductRepository;
import br.com.neolog.welcomekit.productCategory.ProductCategoryService;

@Component
public class StockService
{

    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;

    static final Logger logger = LogManager.getLogger( ProductCategoryService.class );

    public List<Stock> findAllStock()
    {
        return stockRepository.findAll();
    }

    public void create(
        Stock stock )
    {
        if( ! productRepository.existsByCode( stock.getProduct().getCode() ) ) {
            final String messageError = "Code " + stock.getProduct().getCode() + " dont exists in database!";
            logger.error( messageError );
            throw new ProductNotFoundException( messageError );
        }

        stock.setProduct( productRepository.findByCode( stock.getProduct().getCode() ) );
        stockRepository.save( stock );
        logger.info( "product " + stock.getProduct().getCode() + " create in stock with sucess!" );
    }

    public Stock findStockByProductCode(
        final int code )
    {
        if( ! productRepository.existsByCode( code ) ) {
            final String messageError = "Code" + code + "don't exsits in database!";
            logger.warn( messageError );
            throw new ProductNotFoundException( messageError );
        }
        final Product product = productRepository.findByCode( code );
        return stockRepository.findByProduct( product );
    }

    public void updateStockProduct(
        final Stock stock,
        final int productCode )
    {
        if( ! productRepository.existsByCode( productCode ) ) {
            final String messageError = "This product " + stock.getProduct().getCode() + " don't exists in database!";
            logger.warn( messageError );
            // throw new CustomerEmailNotFundException( messageError );

            throw new ProductNotFoundException( messageError );
        }

        if( ! stockRepository.existsByProductCode( productCode ) ) {
            final String messageError = "This product " + stock.getProduct().getCode() + " don't exists in database!";
            logger.warn( messageError );
            throw new ProductNotFoundException( messageError );
        }

        final Product product = productRepository.findByCode( productCode );

        final Stock stockPersisted = stockRepository.findByProduct( product );
        if( stockPersisted == null ) {
            stockRepository.save( new Stock( product, stock.getQuantity() ) );
            logger.info( "Stock updade with sucess!" );
            return;
        }
        stockPersisted.setQuantity( stockPersisted.getQuantity() + stock.getQuantity() );
        stockRepository.save( stockPersisted );
        logger.info( "Stock updade with sucess!" );
    }

    public void deleteStockForProductCode(
        int productCode )
    {
        if( ! stockRepository.existsByProductCode( productCode ) ) {
            final String messageError = "This product " + productCode + " don't exists in database!";
            logger.warn( messageError );
            throw new ProductNotFoundException( messageError );
        }
        final Product product = productRepository.findByCode( productCode );
        final Stock stock = stockRepository.findByProduct( product );
        stockRepository.delete( stock );

    }
}
