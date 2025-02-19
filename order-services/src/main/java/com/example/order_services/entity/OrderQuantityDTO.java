package com.example.order_services.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQuantityDTO {
    int orderId;
    int orderItemId;
    int newQuantity;

}


