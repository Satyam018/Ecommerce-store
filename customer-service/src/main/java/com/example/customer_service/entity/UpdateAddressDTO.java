package com.example.customer_service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressDTO {
    private int id;
    private String streetAddress;
    private String city;
    private String province;
    private String postcode;
}
