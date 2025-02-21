package com.example.order_services.controllers;


import com.example.order_services.entity.*;
import com.example.order_services.services.OrderItemServices;
import com.example.order_services.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderControllers {

    @Autowired
    OrderServices orderServices;

    @Autowired
    OrderItemServices orderItemServices;

    @GetMapping("orders")
    public ResponseEntity<List<Order1>> getOrders(){
        return orderServices.getOrders();
    }

    @GetMapping()
    public ResponseEntity<List<OutputOrderDTO>> getOrdersDetails() {
        return orderServices.getOrdersDetails();

    }


    @GetMapping("order/{id}")
    public ResponseEntity<Order1> getOrder(int orderid){
        return orderServices.getOrder(orderid);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addOrder(OrderDTO orderDTO){
        return orderServices.addOrder(orderDTO);
    }

    @DeleteMapping("deleteOrder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id){
        return orderServices.deleteOrder(id);
    }

    @DeleteMapping("deleteOrderItem/{orderItemId}")
    public ResponseEntity<String> removeItemFromOrder(@PathVariable int removeItemFromOrder){
        return orderItemServices.removeItemFromOrder(removeItemFromOrder);
    }

    @PutMapping("updatestatus/delivered/{orderId}")
    public  ResponseEntity<Order1> changeOrderStatusDelivered(@PathVariable int orderId){
        return orderServices.changeOrderStatusDelivered(orderId);
    }

    @PutMapping("updatequantity")
    public ResponseEntity<OrderItem> changeOrderQuantity(@RequestBody OrderQuantityDTO orderQuantityDTO){
        return orderItemServices.changeOrderQuantity(orderQuantityDTO);
    }




}
