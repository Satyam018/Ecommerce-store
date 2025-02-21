package com.example.order_services.dao;

import com.example.order_services.entity.Order1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order1,Integer> {


    @Query("SELECT COUNT(o) > 0 FROM Order1 o WHERE o.customer=:customerId")
    Optional<Boolean> findByCustomerId(@Param("customerId") int customerId);

    @Query("SELECT o FROM Order1 o WHERE o.customer=:customerId")
    Order1 getOrderByCustomerId(@Param("customerId") int customerId);
}
