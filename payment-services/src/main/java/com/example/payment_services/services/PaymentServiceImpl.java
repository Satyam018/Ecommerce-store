package com.example.payment_services.services;

import com.example.payment_services.dao.PaymentRepository;
import com.example.payment_services.entity.Payment;
import com.example.payment_services.entity.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentServiceImpl implements  PaymentServices{

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<Boolean> makePayment(PaymentRequestDTO paymentRequestDTO) {
        Payment payment=new Payment();
        payment.setPaymentStatus("Success");
        payment.setAmount(payment.getAmount());
        payment.setOrderId(paymentRequestDTO.getOrderId());

        paymentRepository.save(payment);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Payment> getPaymentByOrderId(int orderId) {
       Optional<Payment> payment= paymentRepository.getPaymentByOrderId(orderId);
       if(payment.isEmpty())throw new IllegalArgumentException("No Payment Found!");
       return new ResponseEntity<>(payment.get(),HttpStatus.OK);
    }


}
