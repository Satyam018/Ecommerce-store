package com.example.order_services.services;

import com.example.order_services.entity.OrderItem;
import com.example.order_services.entity.OrderQuantityDTO;
import org.springframework.http.ResponseEntity;

public interface OrderItemServices {
    ResponseEntity<String> removeItemFromOrder(int removeItemFromOrder);

    ResponseEntity<OrderItem> changeOrderQuantity(OrderQuantityDTO orderQuantityDTO);
}
