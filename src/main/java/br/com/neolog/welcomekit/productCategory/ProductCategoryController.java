package br.com.neolog.welcomekit.productCategory;

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
@RequestMapping( path = "/category" )
public class ProductCategoryController
{

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategory> create(
        @RequestBody
        @Valid
        final ProductCategory productCategory )
    {
        productCategoryService.create( productCategory );
        return new ResponseEntity<>( productCategory, HttpStatus.OK );
    }

    @DeleteMapping( path = "/{code}" )
    public ResponseEntity<ProductCategory> delete(
        @PathVariable( value = "code" )
        final int code )
    {
        productCategoryService.deleteByCode( code );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @GetMapping( path = "/{code}" )
    public ResponseEntity<ProductCategory> findByCode(
        @PathVariable( value = "code" )
        final int code )
    {
        return ResponseEntity.ok( productCategoryService.findByCode( code ) );
    }

    @PutMapping
    public ResponseEntity<ProductCategory> update(
        @RequestBody
        @Valid
        final ProductCategory productCategory )
    {
        productCategoryService.update( productCategory );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> findAllCategories()
    {
        return new ResponseEntity<List<ProductCategory>>( productCategoryService.findAllCategories(),
            HttpStatus.OK );
    }
}
