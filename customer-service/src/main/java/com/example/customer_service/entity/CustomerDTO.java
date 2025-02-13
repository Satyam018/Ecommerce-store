package com.example.customer_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String phoneNo;
}
