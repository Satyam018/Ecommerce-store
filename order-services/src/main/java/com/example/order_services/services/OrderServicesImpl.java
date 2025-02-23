package com.example.order_services.services;

import com.example.order_services.dao.OrderItemRepository;
import com.example.order_services.dao.OrderRepository;
import com.example.order_services.entity.*;
import com.example.order_services.feign.PaymentInterface;
import com.example.order_services.feign.ProductInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServicesImpl implements OrderServices {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemReository;

    @Autowired
    ProductInterface productInterface;

    @Autowired
    PaymentInterface paymentInterface;

    @Override
    public ResponseEntity<List<Order1>> getOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OutputOrderDTO>> getOrdersDetails() {
        List<OutputOrderDTO> outputOrderDTOS=new ArrayList<>();
        List<Order1> orders=orderRepository.findAll();

        for (Order1 order:orders){
            Optional<List<OrderItem>> orderItems=orderItemReository.findByOrderId(order.getId());

            OutputOrderDTO orderDTO=new OutputOrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setOrderStatus(order.getOrderStatus());
            orderDTO.setCustomer(order.getCustomer());
            orderDTO.setAddress(order.getAddress());
            if(orderItems.isPresent())orderDTO.setOrderItems(orderItems.get());
            else orderDTO.setOrderItems(new ArrayList<>());
        }

        return new ResponseEntity<>(outputOrderDTOS,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Order1> getOrder(int orderid) {
        Optional<Order1> order = orderRepository.findById(orderid);
        if (order.isEmpty()) throw new IllegalArgumentException("Order Not Found!");
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<String> addOrder(OrderDTO orderDTO) {
        Order1 order=createOrderIfNotExist(orderDTO);

        //adding saga Pattern
        try {

            if(!checkingandReducingProductStock(orderDTO))return new ResponseEntity<>("Product Out of Stock",HttpStatus.OK);
            if(!makePayment())return new ResponseEntity<>("Cannot make Payment!",HttpStatus.OK);
            for (OrderItemDTO orderItemDTO:orderDTO.getOrderItems()){
                OrderItem orderItem=new OrderItem();
                orderItem.setProduct(orderItemDTO.getProduct());
                orderItem.setQuantity(orderItemDTO.getQuantity());
                orderItem.setOrder(order);
                orderItemReository.save(orderItem);
            }
            return new ResponseEntity<>("Order Placed Succesfully!", HttpStatus.OK);

        }catch (Exception e){
            //remove from stock list;
            restoreStock(orderDTO.getOrderItems());
            return new ResponseEntity<>("Cannot Place Order",HttpStatus.OK);
        }


    }

    private boolean makePayment(){
//        if(paymentInterface.makePayment())
        return true;
    }

    private void restoreStock(List<OrderItemDTO> orderItemDTO){
        productInterface.restoreStock(getReduceItemList(orderItemDTO));
    }
    private boolean checkingandReducingProductStock(OrderDTO orderDTO){
        return  productInterface.reduceItemOrder(getReduceItemList(orderDTO.getOrderItems())).getBody();
    }

    private List<ReduceItemOrderedDTO> getReduceItemList(List<OrderItemDTO> orderItemDTOS){
        List<ReduceItemOrderedDTO> reduceItemOrderedDTOList=new ArrayList<>();
        for (OrderItemDTO orderItemDTO:orderItemDTOS){
            reduceItemOrderedDTOList.add(new ReduceItemOrderedDTO(orderItemDTO.getProduct(),orderItemDTO.getQuantity()));
        }
        return reduceItemOrderedDTOList;
    }
    private Order1 createOrderIfNotExist(OrderDTO orderDTO){
        Optional<Boolean> hasOrder=orderRepository.findByCustomerId(orderDTO.getCustomer());
        if(hasOrder.isEmpty() || !hasOrder.get()){
            Order1 order = new Order1();
            order.setOrderStatus("Ordered");
            order.setAddress(orderDTO.getAddress());
            order.setCustomer(orderDTO.getCustomer());
            orderRepository.save(order);
            return order;
        }else{
            return orderRepository.getOrderByCustomerId(orderDTO.getCustomer());
        }
    }


    @Override
    public ResponseEntity<String> deleteOrder(int id) {
        Optional<Order1> order = orderRepository.findById(id);
        if (order.isEmpty()) throw new IllegalArgumentException("Order Not Found!");

//        add Saga
        try{
            getReduceItemOrderDTOByOrderId(id);
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return new ResponseEntity<>("Order Deleted Successfully!", HttpStatus.OK);

    }

    private void getReduceItemOrderDTOByOrderId(int orderId){
        Optional<List<OrderItem>> orderItems=orderItemReository.findByOrderId(orderId);
        List<OrderItemDTO> orderItemDTOS=new ArrayList<>();
        for (OrderItem orderItem:orderItems.get()){
            OrderItemDTO orderItemDTO=new OrderItemDTO();
            orderItemDTO.setProduct(orderItem.getProduct());
            orderItemDTO.setQuantity(orderItem.getQuantity());
        }
        restoreStock(orderItemDTOS);
    }

    @Override
    public ResponseEntity<Order1> changeOrderStatusDelivered(int id) {
        Optional<Order1> order = orderRepository.findById(id);
        if (order.isEmpty()) throw new IllegalArgumentException("Order Not Found!");
        Order1 updatedOrder = order.get();
        updatedOrder.setOrderStatus("Delivered");
        getReduceItemOrderDTOByOrderId(id);
        orderRepository.save(updatedOrder);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }






}
