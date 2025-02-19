package com.example.customer_service.service;

import com.example.customer_service.entity.Address;
import com.example.customer_service.entity.AddressDTO;
import com.example.customer_service.entity.UpdateAddressDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    ResponseEntity<String> addAddress(AddressDTO addressDTO);

    ResponseEntity<List<Address>> getAllAddress(int customerId);

    ResponseEntity<String> deleteAddressForCustomer(int customerId, int addressId);

    ResponseEntity<Address> updateAddress(UpdateAddressDTO updateAddressDTO);
}
