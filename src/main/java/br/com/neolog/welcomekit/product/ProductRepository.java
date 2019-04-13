package br.com.neolog.welcomekit.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
    extends
        JpaRepository<Product,Integer>
{

    Product findByCode(
        int code );

    Product findByName(
        String name );

    boolean existsByCode(
        int code );

    Product findById(
        int id );
}
