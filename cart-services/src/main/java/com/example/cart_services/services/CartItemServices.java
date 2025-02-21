package com.example.cart_services.services;


import com.example.cart_services.entity.AddCartItemsDTO;
import com.example.cart_services.entity.CartItem;
import com.example.cart_services.entity.OutputCartInfoDTO;
import com.example.cart_services.entity.UpdateCartItemDTO;
import org.springframework.http.ResponseEntity;

public interface CartItemServices {
    ResponseEntity<CartItem> getCartItemDTO(int cartItemId);

    ResponseEntity<String> deleteItem(int cartItemId);

    ResponseEntity<String> addCartItem(AddCartItemsDTO addCartItemsDTO);

    ResponseEntity<CartItem> updateCartItem(UpdateCartItemDTO updateCartItemDTO);

}
