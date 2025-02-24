package com.example.order_services.services;

import com.example.order_services.dao.OrderItemRepository;
import com.example.order_services.entity.OrderItem;
import com.example.order_services.entity.OrderItemDTO;
import com.example.order_services.entity.OrderQuantityDTO;
import com.example.order_services.entity.ReduceItemOrderedDTO;
import com.example.order_services.feign.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServicesImpl implements OrderItemServices{

    @Autowired
    OrderItemRepository orderItemReository;

    @Autowired
    ProductInterface productInterface;

    @Override
    public ResponseEntity<String> removeItemFromOrder(int removeItemFromOrder) {
        Optional<OrderItem> orderItem=orderItemReository.findById(removeItemFromOrder);
        if(orderItem.isEmpty()) throw new IllegalArgumentException("Order Does Not Exists!");


        getReduceItemOrderDTOByOrderId(removeItemFromOrder);

        orderItemReository.delete(orderItem.get());
        return new ResponseEntity<>("Order Deleted SuccessFully!",HttpStatus.OK);
    }

    private void getReduceItemOrderDTOByOrderId(int removeItemFromOrder){
        OrderItem orderItem=orderItemReository.findById(removeItemFromOrder).get();
        List<OrderItemDTO> orderItemDTOS=new ArrayList<>();
            OrderItemDTO orderItemDTO=new OrderItemDTO();
            orderItemDTO.setProduct(orderItem.getProduct());
            orderItemDTO.setQuantity(orderItem.getQuantity());

        restoreStock(orderItemDTOS);
    }

    private void restoreStock(List<OrderItemDTO> orderItemDTO){
        productInterface.restoreStock(getReduceItemList(orderItemDTO));
    }

    private List<ReduceItemOrderedDTO> getReduceItemList(List<OrderItemDTO> orderItemDTOS){
        List<ReduceItemOrderedDTO> reduceItemOrderedDTOList=new ArrayList<>();
        for (OrderItemDTO orderItemDTO:orderItemDTOS){
            reduceItemOrderedDTOList.add(new ReduceItemOrderedDTO(orderItemDTO.getProduct(),orderItemDTO.getQuantity()));
        }
        return reduceItemOrderedDTOList;
    }

    @Override
    public ResponseEntity<OrderItem> changeOrderQuantity(OrderQuantityDTO orderQuantityDTO) {
        Optional<OrderItem> orderItem=orderItemReository.findById(orderQuantityDTO.getOrderItemId());
        if(orderItem.isEmpty() || orderItem.get().getOrder().getId()!=orderQuantityDTO.getOrderId())
            throw new IllegalArgumentException("Item not Found!");

        if(orderQuantityDTO.getNewQuantity()<0)throw new IllegalArgumentException("Invalid Quanity!");



        OrderItem updatedOrderItem=orderItem.get();
        updatedOrderItem.setQuantity(orderQuantityDTO.getNewQuantity());
        orderItemReository.save(updatedOrderItem);

        return new ResponseEntity<>(updatedOrderItem,HttpStatus.OK);


    }
}
