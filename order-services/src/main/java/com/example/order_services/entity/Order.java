package com.example.order_services.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private int id;

    @CurrentTimestamp
    private Date orderDate;
    private String orderStatus;

    private Double totalPrice;

    private int customer;
    private int address;

    @OneToMany
    private List<OrderItem> orderItems;
}
