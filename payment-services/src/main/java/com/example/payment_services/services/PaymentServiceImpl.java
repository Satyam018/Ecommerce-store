package com.example.payment_services.services;

import com.example.payment_services.dao.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements  PaymentServices{

    @Autowired
    PaymentRepository paymentRepository;
}
