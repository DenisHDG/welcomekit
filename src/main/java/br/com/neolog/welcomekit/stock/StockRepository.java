package br.com.neolog.welcomekit.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neolog.welcomekit.product.Product;

@Repository
public interface StockRepository
    extends
        JpaRepository<Stock,Integer>
{

    Stock findByProduct(
        Product product );

    boolean existsByProduct(
        Product product );

    boolean existsByProductCode(
        int productCode );

}
