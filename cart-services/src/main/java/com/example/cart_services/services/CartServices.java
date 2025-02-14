package com.example.cart_services.services;


import com.example.cart_services.entity.Cart;
import com.example.cart_services.entity.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartServices {

    ResponseEntity<List<Cart>> getAllCartDetails(int id);

    ResponseEntity<Cart> getCartItem(int customerId, int cartId);
}
