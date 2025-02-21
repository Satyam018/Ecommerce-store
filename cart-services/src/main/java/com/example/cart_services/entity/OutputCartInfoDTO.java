package com.example.cart_services.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputCartInfoDTO {

    int cartId;
    int customerId;

    List<CartItem> cartItems;


    public int getCartId() {
        return cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
