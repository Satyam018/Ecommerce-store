package com.example.customer_service.controller;

import com.example.customer_service.entity.Address;
import com.example.customer_service.entity.AddressDTO;
import com.example.customer_service.entity.UpdateAddressDTO;
import com.example.customer_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PutMapping("add")
    public ResponseEntity<String> addAddress(@RequestBody AddressDTO addressDTO){
        return addressService.addAddress(addressDTO);
    }

    @GetMapping("{customerid}")
    public ResponseEntity<List<Address>> getAllAddress(@PathVariable(name = "customerid") int customerId){
        return addressService.getAllAdress(customerId);
    }

    @DeleteMapping("deleteaddress/customer/{customerId}/address/{addressId}")
    public ResponseEntity<String> deleteAddressForCustomer(@PathVariable int customerId,
                                                           @PathVariable int addressId){
        return addressService.deleteAddressForCustomer(customerId,addressId);
    }

    @PutMapping("updateaddress")
    public ResponseEntity<Address> updateAddress(@RequestBody UpdateAddressDTO updateAddressDTO){
        return addressService.updateAddress(updateAddressDTO);
    }

}
