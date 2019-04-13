package br.com.neolog.welcomekit.session;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/session" )
public class SessionController
{

    @Autowired
    private SessionService sessionServices;

    @PostMapping
    public ResponseEntity<String> login(
        @RequestBody
        @Valid
        final Credentials credentials )
    {

        final String token = sessionServices.createCustomerSession( credentials );
        return new ResponseEntity<>( token, HttpStatus.OK );
    }

}
