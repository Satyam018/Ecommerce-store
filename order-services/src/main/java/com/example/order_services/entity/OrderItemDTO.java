package com.example.order_services.entity;

public class OrderItemDTO {

    private int quantity;
    private int product;

    public int getQuantity() {
        return quantity;
    }

    public int getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
