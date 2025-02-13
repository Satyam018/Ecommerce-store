package com.example.product_services.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {


    private String name;
    private String description;
    private int quantity;
    private double price;
    private String category;




}