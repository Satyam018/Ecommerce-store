package com.example.cart_services.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    private int id;

    private int customerId;

    @OneToMany
    @JoinColumn(name = "cart_item_id",nullable = false)
    private int cartItemId;


}
