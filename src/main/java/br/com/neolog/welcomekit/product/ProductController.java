package br.com.neolog.welcomekit.product;

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
@RequestMapping( path = "/product" )
public class ProductController
{

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(
        @RequestBody
        @Valid
        final Product product )
    {
        productService.createProduct( product );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @DeleteMapping( path = "/{code}" )
    public ResponseEntity<Product> deletProductByCode(
        @PathVariable( value = "code" )
        final int code )
    {
        productService.deleteProductByCode( code );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @GetMapping( path = "/{code}" )
    public ResponseEntity<Product> findProductByCode(
        @PathVariable( value = "code" )
        final int code )
    {
        return ResponseEntity.ok( productService.findProductByCode( code ) );
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(
        @RequestBody
        @Valid
        final Product product )
    {
        productService.updateProductByCode( product );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProduct()
    {
        return new ResponseEntity<List<Product>>( productService.findAllProduct(), HttpStatus.OK );
    }
}
