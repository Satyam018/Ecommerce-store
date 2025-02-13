package com.example.customer_service.service;

import com.example.customer_service.entity.Address;
import com.example.customer_service.entity.AddressDTO;
import com.example.customer_service.entity.UpdateAddressDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {
    ResponseEntity<String> addAddress(AddressDTO addressDTO);

    ResponseEntity<List<Address>> getAllAdress(int customerId);

    ResponseEntity<String> deleteAddressForCustomer(int customerId, int addressId);

    ResponseEntity<Address> updateAddress(UpdateAddressDTO updateAddressDTO);
}
