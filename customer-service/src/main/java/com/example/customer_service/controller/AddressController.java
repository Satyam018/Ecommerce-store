package com.example.customer_service.controller;

import com.example.customer_service.entity.Address;
import com.example.customer_service.entity.AddressDTO;
import com.example.customer_service.entity.DeleteAddressDTO;
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

    @PostMapping("add")
    public ResponseEntity<String> addAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.addAddress(addressDTO);
    }

    @GetMapping("{customerid}")
    public ResponseEntity<List<Address>> getAllAddress(@PathVariable(name = "customerid") int customerId) {
        return addressService.getAllAddress(customerId);
    }

    @DeleteMapping("deleteaddress")
    public ResponseEntity<String> deleteAddressForCustomer(@RequestBody DeleteAddressDTO deleteAddressDTO) {
        return addressService.deleteAddressForCustomer(deleteAddressDTO.getCustomerId(), deleteAddressDTO.getAddressId());
    }

    @PostMapping("updateaddress")
    public ResponseEntity<Address> updateAddress(@RequestBody UpdateAddressDTO updateAddressDTO) {
        return addressService.updateAddress(updateAddressDTO);
    }

}
