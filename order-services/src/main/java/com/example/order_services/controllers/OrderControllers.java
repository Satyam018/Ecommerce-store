package com.example.order_services.controllers;


import com.example.order_services.entity.Order;
import com.example.order_services.entity.OrderDTO;
import com.example.order_services.entity.OrderItem;
import com.example.order_services.entity.OrderQuantityDTO;
import com.example.order_services.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderControllers {

    @Autowired
    OrderServices orderServices;



    @GetMapping("")
    public ResponseEntity<List<Order>> getOrders(){
        return orderServices.getOrders();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(int orderid){
        return orderServices.getOrder(orderid);
    }


    @PutMapping("/add")
    public ResponseEntity<String> addOrder(OrderDTO orderDTO){
        return orderServices.addOrder(orderDTO);
    }

    @PutMapping("updatestatus/delivered/{id}")
    public  ResponseEntity<Order> changeOrderStatusDelivered(@PathVariable int id){
        return orderServices.changeOrderStatusDelivered(id);
    }

    @PutMapping("updatequantity")
    public ResponseEntity<Order> changeOrderQuantity(@RequestBody OrderQuantityDTO orderQuantityDTO){
        return orderServices.changeOrderQuantity(orderQuantityDTO);
    }

    @DeleteMapping("deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id){
        return orderServices.deleteOrder(id);
    }
}
