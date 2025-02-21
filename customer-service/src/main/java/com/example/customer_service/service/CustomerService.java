package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerRepository;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.entity.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {


    ResponseEntity<List<Customer>> getAllCustomers();

    ResponseEntity<Customer> getCustomerById(Integer id);

    ResponseEntity<String> addCustomer(CustomerDTO customerDTO);

    ResponseEntity<Customer> updateCustomer(int id,CustomerDTO customerDTO);

    ResponseEntity<Boolean> hasCustomer(Integer id);
}
