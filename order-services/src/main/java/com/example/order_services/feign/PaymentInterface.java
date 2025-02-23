package com.example.order_services.feign;


import com.example.order_services.entity.PaymentRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("PAYMENT-SERVICES/payment")
public interface PaymentInterface {

    @PostMapping("makepayment")
    public ResponseEntity<Boolean> makePayment(@RequestBody PaymentRequestDTO paymentRequestDTO);
}
