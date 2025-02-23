package com.example.order_services.feign;


import com.example.order_services.entity.ReduceItemOrderedDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("PRODUCT-SERVICES/products")
public interface ProductInterface {


    @PostMapping("reduceitemorder")
    public ResponseEntity<Boolean> reduceItemOrder(@RequestBody List<ReduceItemOrderedDTO> reduceItemOrderedDTO);

    @PostMapping("restoreStock")
    public ResponseEntity<String> restoreStock(@RequestBody List<ReduceItemOrderedDTO> reduceItemOrderedDTO);


    }
