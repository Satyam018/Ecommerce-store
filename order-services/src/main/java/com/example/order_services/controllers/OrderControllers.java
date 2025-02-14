package com.example.order_services.controllers;


import com.example.order_services.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderControllers {

    @Autowired
    OrderServices orderServices;
}
