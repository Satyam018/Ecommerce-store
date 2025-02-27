package com.example.payment_services.controllers;


import com.example.payment_services.entity.Payment;
import com.example.payment_services.entity.PaymentRequestDTO;
import com.example.payment_services.services.PaymentServices;
//import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
public class PaymentControllers {

    @Autowired
    PaymentServices paymentServices;


    @PostMapping("makepayment")
    public ResponseEntity<Boolean> makePayment(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return paymentServices.makePayment(paymentRequestDTO);
    }

    @PostMapping("getpaymentinfo/{orderid}")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable int orderid){
        return paymentServices.getPaymentByOrderId(orderid);
    }

}
