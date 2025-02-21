package com.example.product_services.Dao;

import com.example.product_services.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT COUNT(p) > 0  FROM Product p WHERE p.id=:productId ")
    boolean getProductById( @Param(("productId")) int productId);
}
