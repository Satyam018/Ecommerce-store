package com.example.cart_services.services;


import com.example.cart_services.entity.Cart;
import com.example.cart_services.entity.CartItem;
import com.example.cart_services.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServicesImpl implements CartServices {



    @Autowired
    CartRepository cartRepository;

    @Override
    public ResponseEntity<List<Cart>> getAllCartDetails(int customerId) {
        List<Cart> carts=cartRepository.findByCustomerId(customerId).get();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> getCartItem(int customerId, int cartId) {
        Optional<Cart>cart=cartRepository.findByCustomerIdAndCartId(customerId,cartId);
        if (cart.isEmpty())throw new IllegalArgumentException("No Item Found!");
        return new ResponseEntity<>(cart.get(),HttpStatus.OK);
    }
}
