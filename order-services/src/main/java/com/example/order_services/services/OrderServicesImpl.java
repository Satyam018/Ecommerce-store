package com.example.order_services.services;

import com.example.order_services.dao.OrderRepository;
import com.example.order_services.entity.Order;
import com.example.order_services.entity.OrderDTO;
import com.example.order_services.entity.OrderItem;
import com.example.order_services.entity.OrderQuantityDTO;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServicesImpl implements OrderServices {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> getOrder(int orderid) {
        Optional<Order> order = orderRepository.findById(orderid);
//        double totalPrice=0;
//        for (OrderItem orderItem: orderDTO.getOrderItems()){
////            totalPrice+=orderItem.getQuantity()*orderItem
//        }
        if (order.isEmpty()) throw new IllegalArgumentException("Order Not Found!");
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addOrder(OrderDTO orderDTO) {
        Order order = new Order();

        order.setOrderStatus("Ordered");
        order.setOrderItems(orderDTO.getOrderItems());
        order.setAddress(orderDTO.getAddress());
        order.setCustomer(orderDTO.getCustomer());


        orderRepository.save(order);
        return new ResponseEntity<>("Order Placed Succesfully!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> changeOrderStatusDelivered(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) throw new IllegalArgumentException("Order Not Found!");
        Order updatedOrder = order.get();
        updatedOrder.setOrderStatus("Delivered");
        orderRepository.save(updatedOrder);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteOrder(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) throw new IllegalArgumentException("Order Not Found!");
        orderRepository.deleteById(id);
        return new ResponseEntity<>("Order Deleted Successfully!", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Order> changeOrderQuantity(OrderQuantityDTO orderQuantityDTO) {
        Optional<Order> order = orderRepository.findById(orderQuantityDTO.getOrderId());
        if (order.isEmpty() || order.get().getOrderItems().get(orderQuantityDTO.getOrderItemId()) == null)
            throw new IllegalArgumentException("Order Not Found!");
        Order updatedOrder=order.get();
        OrderItem updatedOrderItem = updatedOrder.getOrderItems().get(orderQuantityDTO.getOrderItemId());

        updatedOrderItem.setQuantity(orderQuantityDTO.getNewQuantity());
        orderRepository.save(updatedOrder);
        return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
    }


}
