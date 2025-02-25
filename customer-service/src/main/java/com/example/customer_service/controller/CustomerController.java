package com.example.customer_service.controller;


import com.example.customer_service.entity.Customer;
import com.example.customer_service.entity.CustomerDTO;
import com.example.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return customerService.getAllCustomers();
    }


    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("hascustomer/{id}")
    public ResponseEntity<Boolean> hasCustomer(@PathVariable Integer id){
        return customerService.hasCustomer(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<Customer> addCustomer(@PathVariable int id,@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(id,customerDTO);
    }


}
