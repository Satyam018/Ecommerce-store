package com.example.customer_service.service;

import com.example.customer_service.dto.AddressRepository;
import com.example.customer_service.dto.CustomerRepository;
import com.example.customer_service.entity.Address;
import com.example.customer_service.entity.AddressDTO;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.entity.UpdateAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseEntity<String> addAddress(AddressDTO addressDTO) {

        Optional<Customer> customer = customerRepository.findById(addressDTO.getCustomerId());
        if (customer.isEmpty()) throw new IllegalArgumentException("Customer Does Not Exists!");

        Address address = new Address();
        address.setStreetAddress(addressDTO.getStreetAddress());
        address.setCity(addressDTO.getCity());
        address.setProvince(addressDTO.getProvince());
        address.setPostcode(addressDTO.getPostcode());
        address.setCustomer(customer.get());

        addressRepository.save(address);
        return new ResponseEntity<>("Address Added Succesfully!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Address>> getAllAddress(int customerId) {
        List<Address> addresses = addressRepository.getAddressByCustomerId(customerId);

        return new ResponseEntity<>(addresses, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> deleteAddressForCustomer(int customerId, int addressId) {

        Optional<Address> address = addressRepository.getAddressCustomerAndAddressId(customerId, addressId);

        System.out.println("Addresss Isd" + address.get().getId());
        if (address.isEmpty()) throw new IllegalArgumentException("Address cannot be Deleted!");

        addressRepository.deleteCustomerAddress(customerId, addressId);
        return new ResponseEntity<>("Address Deleted Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Address> updateAddress(UpdateAddressDTO updateAddressDTO) {
        Optional<Address> address = addressRepository.findById(updateAddressDTO.getId());
        if (address.isEmpty()) throw new IllegalArgumentException("Update cannot be Implemented!");
        Address updatedAddress = address.get();
        if (updateAddressDTO.getStreetAddress() != null && !updateAddressDTO.getStreetAddress().isEmpty())
            updatedAddress.setStreetAddress(updateAddressDTO.getStreetAddress());
        if (updateAddressDTO.getCity() != null && !updateAddressDTO.getCity().isEmpty())
            updatedAddress.setCity(updateAddressDTO.getCity());
        if (updateAddressDTO.getProvince() != null && !updateAddressDTO.getProvince().isEmpty())
            updatedAddress.setProvince(updateAddressDTO.getProvince());
        if (updateAddressDTO.getPostcode() != null && !updateAddressDTO.getPostcode().isEmpty())
            updatedAddress.setPostcode(updateAddressDTO.getPostcode());

        addressRepository.save(updatedAddress);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

}
