package com.example.order_services.services;

import com.example.order_services.entity.Order;
import com.example.order_services.entity.OrderDTO;
import com.example.order_services.entity.OrderItem;
import com.example.order_services.entity.OrderQuantityDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderServices {


    ResponseEntity<List<Order>> getOrders();

    ResponseEntity<Order> getOrder(int orderid);

    ResponseEntity<String> addOrder(OrderDTO orderDTO);

    ResponseEntity<Order> changeOrderStatusDelivered(int id);

    ResponseEntity<String> deleteOrder(int id);

    ResponseEntity<Order> changeOrderQuantity(OrderQuantityDTO orderQuantityDTO);
}
