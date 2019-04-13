package br.com.neolog.welcomekit.cartItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.ThreadCustomer;
import br.com.neolog.welcomekit.cart.CartRepository;
import br.com.neolog.welcomekit.cart.CartService;
import br.com.neolog.welcomekit.customer.Customer;

@Component
public class CartItemService
{
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;

    private Customer customer = ThreadCustomer.getCustomer();

    public void create(
        final CartItem cartItem )
    {

        /*
         * if( cartRepository.existsByCustomerId( customer.getId() ) ) {
         * cartService.create( cartItem, customer ); }
         */

        updateCartItem( cartItem );
        cartService.update( customer );

    }

    public void updateCartItem(
        final CartItem cartItem )
    {
        final CartItem cartItemUpdate = cartItemRepository.findCartItemRepositoryByProduct( cartItem.getProductId() );
        cartItemUpdate.setQuantity( cartItemUpdate.getQuantity() + cartItem.getQuantity() );
        cartItemRepository.save( cartItemUpdate );
    }

}
