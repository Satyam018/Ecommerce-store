package com.example.cart_services.repo;


import com.example.cart_services.entity.Cart;
import com.example.cart_services.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    @Query("SELECT i FROM CartItem i WHERE i.cart.id=:cartid")
    Optional<List<CartItem>> getAllItemsByCart(@Param("cartid")int cartId);
}
