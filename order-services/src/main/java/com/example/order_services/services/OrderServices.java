package com.example.order_services.services;

import com.example.order_services.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderServices {


    ResponseEntity<List<Order1>> getOrders();

    ResponseEntity<List<OutputOrderDTO>> getOrdersDetails();

    ResponseEntity<Order1> getOrder(int orderid);

    ResponseEntity<String> addOrder(OrderDTO orderDTO);

    ResponseEntity<Order1> changeOrderStatusDelivered(int id);

    ResponseEntity<String> deleteOrder(int id);


}
