package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerRepository;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.entity.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers=customerRepository.findAll();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Integer id) {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty())throw new IllegalArgumentException("Customer does not Exists!");
        return new ResponseEntity<>(customer.get(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addCustomer(CustomerDTO customerDTO) {
        Customer customer=new Customer();
        customer.setFirst_name(customerDTO.getFirst_name());
        customer.setLast_name(customerDTO.getLast_name());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customerRepository.save(customer);
        return new ResponseEntity<>("Customer added Successfully!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(int id,CustomerDTO customerDTO) {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty())throw new IllegalArgumentException("Customer does not Exists!");
        Customer updatedCustomer=customer.get();
        if(!customerDTO.getFirst_name().isEmpty())updatedCustomer.setFirst_name(customerDTO.getFirst_name());
        if(!customerDTO.getLast_name().isEmpty())updatedCustomer.setLast_name(customerDTO.getLast_name());
        if(!customerDTO.getEmail().isEmpty())updatedCustomer.setEmail(customerDTO.getEmail());
        if(!customerDTO.getPassword().isEmpty())updatedCustomer.setPassword(customerDTO.getPassword());
        if(!customerDTO.getPhoneNo().isEmpty())updatedCustomer.setPhoneNo(customerDTO.getPhoneNo());

        customerRepository.save(updatedCustomer);
        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);

    }


}
