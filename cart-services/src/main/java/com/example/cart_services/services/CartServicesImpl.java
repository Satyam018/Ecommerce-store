package com.example.cart_services.services;


import com.example.cart_services.entity.Cart;
import com.example.cart_services.entity.CartItem;
import com.example.cart_services.entity.OutputCartInfoDTO;
import com.example.cart_services.feign.CustomerInterface;
import com.example.cart_services.repo.CartItemRepository;
import com.example.cart_services.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServicesImpl implements CartServices {


    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CustomerInterface customerInterface;

    @Override
    public ResponseEntity<List<Cart>> getAllCartDetails() {
        List<Cart> carts = cartRepository.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> getCart(int customerId) {
        Optional<Cart> cart = cartRepository.findByCustomerId(customerId);
        if (cart.isEmpty()) throw new IllegalArgumentException("No Cart Found!");
        return new ResponseEntity<>(cart.get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> createCart(int customerId) {
        Optional<Boolean> doesCartExists = cartRepository.doesCartExists(customerId);
        if (!hasCustomer(customerId)) throw new IllegalArgumentException("Customer Does Not Exists!");
        if (doesCartExists.isPresent() && doesCartExists.get())
            return new ResponseEntity<>("Cart Already Exists!", HttpStatus.OK);
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cartRepository.save(cart);
        return new ResponseEntity<>("Cart created Succesfully!", HttpStatus.OK);
    }



    @Override
    public ResponseEntity<String> clearCart(int customerId) {
        Optional<Integer> cartId = cartRepository.findCartByCustomerId(customerId);

        if (!hasCustomer(customerId)) throw new IllegalArgumentException("Customer Does Not Exists!");
        if (cartId.isEmpty()) throw new IllegalArgumentException("Cart Does not Exists!");

        Optional<List<CartItem>> cartItems = cartItemRepository.getAllItemsByCart(cartId.get());
        if (cartItems.isPresent()) {
            List<CartItem> deleteCartItem = cartItems.get();
            for (CartItem deleteItem : deleteCartItem) {
                cartItemRepository.delete(deleteItem);
            }
        }

        return new ResponseEntity<>("All Items Deleted!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OutputCartInfoDTO> getCartDetailsByCustomerId(int customerId) {
        Optional<Integer> cartId = cartRepository.findCartByCustomerId(customerId);

        if (!hasCustomer(customerId)) throw new IllegalArgumentException("Customer Does Not Exists!");
        if (cartId.isEmpty()) throw new IllegalArgumentException("Cart Does not Exists!");
        Optional<List<CartItem>> cartItems = cartItemRepository.getAllItemsByCart(cartId.get());

        OutputCartInfoDTO outputCartInfoDTO=new OutputCartInfoDTO();
        outputCartInfoDTO.setCustomerId(customerId);
        outputCartInfoDTO.setCartId(cartId.get());
        if(cartItems.isPresent())outputCartInfoDTO.setCartItems(cartItems.get());
        else outputCartInfoDTO.setCartItems(new ArrayList<>());

        System.out.println(outputCartInfoDTO);
        return new ResponseEntity<>(outputCartInfoDTO,HttpStatus.OK);

    }

    private boolean hasCustomer(int customerId) {
        ResponseEntity<Boolean> hasCustomer = customerInterface.hasCustomer(customerId);
        return hasCustomer.getBody();
    }


}
