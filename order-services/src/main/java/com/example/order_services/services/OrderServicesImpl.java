package com.example.order_services.services;

import com.example.order_services.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServicesImpl implements OrderServices{
    @Autowired
    OrderRepository orderRepository;
}
