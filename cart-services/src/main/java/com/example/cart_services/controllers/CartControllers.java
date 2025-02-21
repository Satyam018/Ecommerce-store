package com.example.cart_services.controllers;



import com.example.cart_services.entity.*;
import com.example.cart_services.feign.CustomerInterface;
import com.example.cart_services.feign.ProductInterface;
import com.example.cart_services.services.CartItemServices;
import com.example.cart_services.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartControllers {


    @Autowired
    CartServices cartServices;

    @Autowired
    CartItemServices cartItemServices;



    @GetMapping()
    public ResponseEntity<List<Cart>> getAllCartDetails(){
        return cartServices.getAllCartDetails();
    }

    @GetMapping("customer/{customerid}")
    public ResponseEntity<Cart> getCart(@PathVariable("customerid") int customerId){
        return cartServices.getCart(customerId);

    }

    @GetMapping("cartdetails/{customerId}")
    public ResponseEntity<OutputCartInfoDTO> getCartDetailsByCustomerId(@PathVariable int customerId){
        return cartServices.getCartDetailsByCustomerId(customerId);
    }

    @GetMapping("cartitem/{id}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable("id") int cartItemId){
        return cartItemServices.getCartItemDTO(cartItemId);
    }

    @PostMapping("addcart/{customerid}")
    public ResponseEntity<String> createCart(@PathVariable("customerid") int customerId){
        return cartServices.createCart(customerId);
    }

    @PostMapping("addcartitem")
    public ResponseEntity<String> addCartItem(@RequestBody AddCartItemsDTO addCartItemsDTO){
      return cartItemServices.addCartItem(addCartItemsDTO);
    }
    @DeleteMapping("clearcart/{customerId}")
    public ResponseEntity<String> clearCarts(@PathVariable("id") int customerId ){
        return cartServices.clearCart(customerId);
    }

    @DeleteMapping("deleteitem/{cartItemId}")
    public ResponseEntity<String> deleteItem(@PathVariable int cartItemId){
        return cartItemServices.deleteItem(cartItemId);
    }

    @PostMapping("updatecartitem")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody UpdateCartItemDTO updateCartItemDTO){
        return cartItemServices.updateCartItem(updateCartItemDTO);
    }




}
