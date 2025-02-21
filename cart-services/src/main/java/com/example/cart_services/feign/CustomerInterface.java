package com.example.cart_services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CUSTOMER-SERVICE/customers")
public interface CustomerInterface {

    @GetMapping("hascustomer/{id}")
    public ResponseEntity<Boolean> hasCustomer(@PathVariable Integer id);
}
