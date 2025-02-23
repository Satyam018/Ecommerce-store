package com.example.payment_services.services;


import com.example.payment_services.entity.Payment;
import com.example.payment_services.entity.PaymentRequestDTO;
import org.springframework.http.ResponseEntity;

public interface PaymentServices {


    ResponseEntity<Boolean> makePayment(PaymentRequestDTO paymentRequestDTO);

    ResponseEntity<Payment> getPaymentByOrderId(int orderid);
}
