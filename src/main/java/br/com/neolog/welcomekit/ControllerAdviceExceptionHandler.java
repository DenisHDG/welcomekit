package br.com.neolog.welcomekit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.neolog.welcomekit.exceptions.CodeCategoryNotFoundException;
import br.com.neolog.welcomekit.exceptions.ConflictUnicElementException;
import br.com.neolog.welcomekit.exceptions.CredentialsCustomerNotFoundException;
import br.com.neolog.welcomekit.exceptions.CustomerEmailNotFundException;
import br.com.neolog.welcomekit.exceptions.ProductCategoryNotFoundException;
import br.com.neolog.welcomekit.exceptions.ProductNotFoundException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler
{

    @ExceptionHandler( ConflictUnicElementException.class )
    public final ResponseEntity<Object> handleConflictUnicElementException(
        final ConflictUnicElementException e,
        final WebRequest request )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( ProductCategoryNotFoundException.class )
    public final ResponseEntity<Object> handleCodeProductNotFundException(
        final ProductCategoryNotFoundException e,
        final WebRequest request )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CodeCategoryNotFoundException.class )
    public final ResponseEntity<Object> handlerCodeCategoryNotFundException(
        final CodeCategoryNotFoundException e,
        final WebRequest request )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CustomerEmailNotFundException.class )
    public final ResponseEntity<Object> handlerCustumerEmailNotFundException(
        final CustomerEmailNotFundException e,
        final WebRequest request )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( ProductNotFoundException.class )
    public final ResponseEntity<Object> handlerCustumerEmailNotFundException(
        final ProductNotFoundException e,
        final WebRequest request )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CredentialsCustomerNotFoundException.class )
    public final ResponseEntity<Object> handlerCredentialsCustomerNotFoundException(
        final CredentialsCustomerNotFoundException e,
        final WebRequest request )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }

}