package br.com.neolog.welcomekit.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.cart.Cart.Status;
import br.com.neolog.welcomekit.cartItem.CartItem;
import br.com.neolog.welcomekit.customer.Customer;
import br.com.neolog.welcomekit.product.Product;

@Component
public class CartService
{

    @Autowired
    private CartRepository cartRepository;

    public void create(
        final CartItem cartItem,
        final Customer customer )
    {

        final Product product = cartItem.getProductId();
        final double totalQuantity = cartItem.getQuantity() + product.getPrice();
        final Cart cart = new Cart( Status.Active, totalQuantity, customer );
        cartRepository.save( cart );
    }

    public void update(
        final Customer cutomer )
    {

    }
}
