package com.example.order_services.feign;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("CUSTOMER-SERVICE/customers")
public interface CustomerInterface {

}
