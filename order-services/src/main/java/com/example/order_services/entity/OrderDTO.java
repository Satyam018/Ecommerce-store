package com.example.order_services.entity;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {


    private Double totalPrice;
    private int customer;
    private int address;

    @OneToMany
    private List<OrderItem> orderItems;
}
