package com.example.cart_services.services;


import com.example.cart_services.entity.Cart;
import com.example.cart_services.entity.CartItem;
import com.example.cart_services.entity.OutputCartInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartServices {

    ResponseEntity<List<Cart>> getAllCarts();

    ResponseEntity<List<OutputCartInfoDTO>> getAllCartDetails();

    ResponseEntity<Cart> getCart(int customerId);

    ResponseEntity<String> clearCart(int cartId);

    ResponseEntity<String> createCart(int customerId);

    ResponseEntity<OutputCartInfoDTO> getCartDetailsByCustomerId(int customerId);

}
