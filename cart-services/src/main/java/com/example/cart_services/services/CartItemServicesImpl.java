package com.example.cart_services.services;


import com.example.cart_services.entity.Cart;
import com.example.cart_services.repo.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServicesImpl implements CartItemServices{

    @Autowired
    CartItemRepository cartItemRepository;


}
