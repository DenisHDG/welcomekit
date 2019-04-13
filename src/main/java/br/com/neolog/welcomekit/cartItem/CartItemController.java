package br.com.neolog.welcomekit.cartItem;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/item" )
public class CartItemController
{
    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItem> createCartItem(
        @RequestBody
        @Valid
        final CartItem cartItem )
    {
        cartItemService.create( cartItem );

        return new ResponseEntity<>( cartItem, HttpStatus.OK );
    }

}
