package br.com.neolog.welcomekit.customer;

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
@RequestMapping( path = "/customer" )
public class CustomerController
{

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
        @RequestBody
        @Valid
        final Customer customer )
    {
        customerService.createCustomer( customer );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @DeleteMapping( path = "/{email}" )
    public ResponseEntity<Customer> deleteCustomerByEmail(
        @PathVariable( value = "email" )
        final String email )
    {
        customerService.deleteCustomer( email );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @GetMapping( path = "/{email}" )
    public ResponseEntity<Customer> findCustomerByEmail(
        @PathVariable( value = "email" )
        final String email )
    {
        return ResponseEntity.ok( customerService.findCustomerByEmail( email ) );

    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomer()
    {
        return new ResponseEntity<List<Customer>>( customerService.findAllCustomers(), HttpStatus.OK );
    }

    @PutMapping( path = "/{email}" )
    public ResponseEntity<Customer> updateCustomerByEmail(
        @RequestBody
        @Valid
        final Customer customer,
        @PathVariable( value = "email" )
        final String email )
    {
        customerService.updateCustomerByEmail( customer, email );
        return new ResponseEntity<>( HttpStatus.OK );

    }
}
