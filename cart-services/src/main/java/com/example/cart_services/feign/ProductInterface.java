package com.example.cart_services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCT-SERVICES/products")
public interface ProductInterface {

    @GetMapping("hasproduct/{id}")
    public ResponseEntity<Boolean> hasProduct(@PathVariable int id);
}
