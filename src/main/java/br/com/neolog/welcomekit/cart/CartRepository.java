package br.com.neolog.welcomekit.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.welcomekit.customer.Customer;

public interface CartRepository
    extends
        JpaRepository<Cart,Integer>
{

    Cart findByCustomer(
        Customer customer );
}
