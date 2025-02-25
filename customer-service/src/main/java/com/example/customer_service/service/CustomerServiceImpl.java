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
    public ResponseEntity<Boolean> hasCustomer(Integer id) {
        Optional<Boolean> customer=customerRepository.findCustomerById(id);
        if(customer.isEmpty() || !customer.get())return new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);
        return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> addCustomer(CustomerDTO customerDTO) {
        Customer customer=new Customer();
        customer.setFirst_name(customerDTO.getFirstName());
        customer.setLast_name(customerDTO.getLastName());
        customer.setUserId(customerDTO.getUserId());
        customer.setPhoneNo(customer.getPhoneNo());
        customerRepository.save(customer);
        return new ResponseEntity<>("Customer added Successfully!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(int id,CustomerDTO customerDTO) {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty())throw new IllegalArgumentException("Customer does not Exists!");
        Customer updatedCustomer=customer.get();
        System.out.println(customerDTO.getFirstName()+" "+customerDTO.getLastName());
        if(customerDTO.getFirstName()!=null && customerDTO.getFirstName().isEmpty())updatedCustomer.setFirst_name(customerDTO.getFirstName());
        if(customerDTO.getLastName()!=null && !customerDTO.getLastName().isEmpty())updatedCustomer.setLast_name(customerDTO.getLastName());
        if(customerDTO.getPhoneNo()!=null && !customerDTO.getPhoneNo().isEmpty())updatedCustomer.setPhoneNo(customerDTO.getPhoneNo());
        if(customerDTO.getPhoneNo()!=null && !customerDTO.getPhoneNo().isEmpty())updatedCustomer.setPhoneNo(customerDTO.getPhoneNo());

        customerRepository.save(updatedCustomer);
        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);

    }




}
