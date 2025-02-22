package com.example.payment_services.dao;


import com.example.payment_services.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {


    @Query("SELECT p FROM Payment p WHERE p.orderId=:orderId")
    Optional<Payment> getPaymentByOrderId(@Param("orderId") int orderId);
}
