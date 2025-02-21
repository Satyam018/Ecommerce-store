package com.example.cart_services.repo;

import com.example.cart_services.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query("SELECT c FROM Cart c WHERE c.customerId= :customerId")
    Optional<Cart> findByCustomerId(@Param("customerId") int customerId);

    @Query("SELECT c.id FROM Cart c WHERE c.customerId= :customerId")
    Optional<Integer> findCartByCustomerId(int customerId);

    @Query("SELECT COUNT(c)>0 FROM Cart c WHERE c.customerId= :customerId")
    Optional<Boolean> doesCartExists(@Param("customerId")int  customerId);

//

}
