package com.example.order_services.feign;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("PRODUCT-SERVICES/products")
public interface ProductInterface {
}
