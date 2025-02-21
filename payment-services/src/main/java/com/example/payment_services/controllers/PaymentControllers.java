package com.example.payment_services.controllers;


import com.example.payment_services.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentControllers {

    @Autowired
    PaymentServices paymentServices;
}
