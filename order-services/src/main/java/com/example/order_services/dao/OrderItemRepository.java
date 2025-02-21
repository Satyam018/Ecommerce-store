package com.example.order_services.dao;


import com.example.order_services.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    @Query("SELECT i FROM OrderItem i WHERE i.order.id=:orderId")
    Optional<List<OrderItem>> findByOrderId(@Param(("orderId")) int id);
}
