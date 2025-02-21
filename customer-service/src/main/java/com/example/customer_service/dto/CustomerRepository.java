package com.example.customer_service.dto;


import com.example.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {

    @Query("SELECT COUNT (c) > 0 FROM Customer c WHERE c.id=:id")
    Optional<Boolean> findCustomerById(@Param("id")int id);
}
