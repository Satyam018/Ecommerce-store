package com.example.customer_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phoneNo;
    private String password;

    @CreationTimestamp
    private Date createdOn;

}
