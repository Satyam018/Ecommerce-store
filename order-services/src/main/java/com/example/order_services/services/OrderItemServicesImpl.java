package com.example.order_services.services;

import com.example.order_services.dao.OrderItemRepository;
import com.example.order_services.entity.OrderItem;
import com.example.order_services.entity.OrderQuantityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemServicesImpl implements OrderItemServices{

    @Autowired
    OrderItemRepository orderItemReository;

    @Override
    public ResponseEntity<String> removeItemFromOrder(int removeItemFromOrder) {
        Optional<OrderItem> orderItem=orderItemReository.findById(removeItemFromOrder);
        if(orderItem.isEmpty()) throw new IllegalArgumentException("Order Does Not Exists!");

        orderItemReository.delete(orderItem.get());
        return new ResponseEntity<>("Order Deleted SuccessFully!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderItem> changeOrderQuantity(OrderQuantityDTO orderQuantityDTO) {
        Optional<OrderItem> orderItem=orderItemReository.findById(orderQuantityDTO.getOrderItemId());
        if(orderItem.isEmpty() || orderItem.get().getOrder().getId()!=orderQuantityDTO.getOrderId())
            throw new IllegalArgumentException("Item not FOund!");

        if(orderQuantityDTO.getNewQuantity()<0)throw new IllegalArgumentException("Invalid Quanity!");

//        add saga

        OrderItem updatedOrderItem=orderItem.get();
        updatedOrderItem.setQuantity(orderQuantityDTO.getNewQuantity());
        orderItemReository.save(updatedOrderItem);

        return new ResponseEntity<>(updatedOrderItem,HttpStatus.OK);


    }
}
