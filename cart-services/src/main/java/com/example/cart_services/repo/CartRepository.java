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

    @Query("SELECT c FROM cart WHERE customer.id=:customerId")
    Optional<List<Cart>> findByCustomerId(@Param("customerId") int customerId);

    @Query("SELECT c FROM cart WHERE customer.id=:customerId AND id =:cartId"  )
    Optional<Cart> findByCustomerIdAndCartId(@Param("customerId") int customerId, @Param("cartId")int cartId);
}
