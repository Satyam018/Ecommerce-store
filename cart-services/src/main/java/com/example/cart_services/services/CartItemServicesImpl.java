package com.example.cart_services.services;



import com.example.cart_services.entity.*;
import com.example.cart_services.feign.ProductInterface;
import com.example.cart_services.repo.CartItemRepository;
import com.example.cart_services.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServicesImpl implements CartItemServices{

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductInterface productInterface;

    @Autowired
    CartRepository cartRepository;


    @Override
    public ResponseEntity<CartItem> getCartItemDTO(int cartItemId) {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty())throw new IllegalArgumentException("No Cart Item found!");

        return new ResponseEntity<>(cartItem.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addCartItem(AddCartItemsDTO addCartItemsDTO) {
        Optional<Cart> cart=cartRepository.findById(addCartItemsDTO.getCartId());
        if(cart.isEmpty())throw new IllegalArgumentException("Cart Not Found!");
        if(!hasProduct(addCartItemsDTO.getProduct_id()))throw new IllegalArgumentException("Product Not Found!");
        CartItem cartItem=new CartItem();
        cartItem.setProduct_id(addCartItemsDTO.getProduct_id());
        cartItem.setQuantity(addCartItemsDTO.getQuantity());
        cartItem.setCart(cart.get());
        cartItemRepository.save(cartItem);
        return new ResponseEntity<>("Cart Item added Successfully!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartItem> updateCartItem(UpdateCartItemDTO updateCartItemDTO) {
        Optional<CartItem> cartItem=cartItemRepository.findById(updateCartItemDTO.getCartItemId());
        if(cartItem.isEmpty())throw new IllegalArgumentException("Cart Item Not Found!");
        if(!hasProduct(updateCartItemDTO.getProduct_id()))throw new IllegalArgumentException("Product Not Found!");

        CartItem updatedCartItem=cartItem.get();
        updatedCartItem.setQuantity(updateCartItemDTO.getQuantity());
        cartItemRepository.save(updatedCartItem);
        return new ResponseEntity<>(updatedCartItem,HttpStatus.OK);
    }



    private boolean hasProduct(int productId){
        ResponseEntity<Boolean> productResponse=productInterface.hasProduct(productId);
        return productResponse.getBody();
    }

    @Override
    public ResponseEntity<String> deleteItem(int cartItemId) {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        System.out.println("cartItemId"+ cartItem);
        if(cartItem.isEmpty())throw new IllegalArgumentException("No Cart Item found!");

        cartItemRepository.delete(cartItem.get());
        return new ResponseEntity<>("CartItem Deleted Successfully!",HttpStatus.OK);
    }


}
