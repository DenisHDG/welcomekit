package br.com.neolog.welcomekit.stock;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/stock" )
public class StockController
{
    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> findAllStock()
    {
        return new ResponseEntity<List<Stock>>( stockService.findAllStock(), HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<Stock> create(
        @RequestBody
        @Valid
        final Stock stock )
    {
        stockService.create( stock );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @GetMapping( path = "/{code}" )
    public ResponseEntity<Stock> findStockByProduct(
        @PathVariable( value = "code" )
        final int code )
    {
        final Stock stock = stockService.findStockByProductCode( code );
        return new ResponseEntity<Stock>( stock, HttpStatus.OK );
    }

    @PutMapping( path = "/{code}" )
    public ResponseEntity<Stock> updateStockProduct(
        @RequestBody
        @Valid
        final Stock stock,
        @PathVariable( value = "code" )
        final int productCode )
    {
        stockService.updateStockProduct( stock, productCode );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @DeleteMapping( path = "/{code}" )
    public ResponseEntity<Stock> deleteStockForProductCode(
        @PathVariable( value = "code" )
        final int productCode )
    {
        stockService.deleteStockForProductCode( productCode );
        return new ResponseEntity<>( HttpStatus.OK );
    }

}
