package com.example.cart_services.controllers;


import com.example.cart_services.entity.Cart;
import com.example.cart_services.entity.CartItem;
import com.example.cart_services.services.CartItemServices;
import com.example.cart_services.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartControllers {


    @Autowired
    CartServices cartServices;

    @Autowired
    CartItemServices cartItemServices;

    @GetMapping("customerid/{id}")
    public ResponseEntity<List<Cart>> getAllCartDetails(@PathVariable int id){
        return cartServices.getAllCartDetails(id);
    }

    @GetMapping("customerid/{customerid}/cartid{cartid}")
    public ResponseEntity<Cart> getCartItem(@PathVariable("customerid") int customerId,
                                                @PathVariable("cartid") int cartId){
        return cartServices.getCartItem(customerId,cartId);

    }







}
