package br.com.neolog.welcomekit.productCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository
    extends
        JpaRepository<ProductCategory,Integer>
{

    ProductCategory findByCode(
        int code );

    boolean existsByCode(
        int code );

}
