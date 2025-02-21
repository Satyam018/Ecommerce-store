package com.example.cart_services.entity;

public class UpdateCartItemDTO {

    int cartItemId;

    private int quantity;
    private int product_id;

    public int getCartItemId() {
        return cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProduct_id() {
        return product_id;
    }
}
