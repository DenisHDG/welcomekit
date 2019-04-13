package br.com.neolog.welcomekit.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.welcomekit.product.Product;

public interface CartItemRepository
    extends
        JpaRepository<CartItem,Integer>
{
    CartItem findCartItemRepositoryByProduct(
        Product product );

}
