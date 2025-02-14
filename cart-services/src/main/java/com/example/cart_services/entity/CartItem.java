package com.example.cart_services.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    private int id;

    private int quantity;

    private int product_id;


}
