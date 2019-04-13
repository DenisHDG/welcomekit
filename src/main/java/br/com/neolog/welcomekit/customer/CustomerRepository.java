package br.com.neolog.welcomekit.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
    extends
        JpaRepository<Customer,Integer>
{

    Customer findByEmail(
        String email );

    Customer findByName(
        String name );

    Customer findById(
        int id );

    boolean existsByEmail(
        String email );

}
